package com.gh.appserver.security.mgt;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import javax.mail.Session;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.appserver.web.common.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.gh.appserver.security.authz.annotation.Logical;
import com.gh.appserver.security.authz.annotation.RequiresAuthentication;
import com.gh.appserver.security.authz.annotation.RequiresPermissions;
import com.gh.appserver.security.authz.annotation.RequiresUser;

/**
 * 权限管理器 注入系统资源访问规则 注入过滤器 注入资源访问权限失败URL，登录URL 提供对外： hasPermissions(url) 内部
 * 
 * @author kevin
 * 
 */
public class MySecurityManager implements Filter, AuthorizationCheck{
	private AuthenticatingRealm authenticatingRealm;

	private Map<String, AccessControlFilter> filters;

	private Map<String, AccessControlFilter> filterChainDefinitions = new LinkedHashMap<>();

	public static String loginUrl;
	private String successUrl;
	private String unauthorizedUrl;
	
	

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}

	private static transient final Logger log = LoggerFactory.getLogger(SecurityManager.class);

	/**
	 * 
	 * 注入规则 权限规则,路径+过滤器
	 * 匹配规则：hash.get(url)是否命中（为了速度），没有命中就顺序遍历LIST（注意带**的startWith判断）
	 * 一旦匹配到就getBean("filterName")，执行对应的isAccessAllowed||onAccessDenied，返回true表示执行成功
	 * 配置注意：粒度小的在前 /home=user /authenticated=authc /permission=authc
	 * /static/**=anon /**=anon
	 */
	// 权限控制器
	public void setFilterChainDefinitions(String definitions) {
		log.info("初始化过滤规则>>>>>>>>>>>>"+definitions);
		System.out.println("中文");
		Scanner scanner = new Scanner(definitions);
		while (scanner.hasNextLine()) {
			String rawLine = scanner.nextLine();
			String line = clean(rawLine);
			if (line == null || line.startsWith(COMMENT_POUND) || line.startsWith(COMMENT_SEMICOLON)) {
				// skip empty lines and comments:
				continue;
			}
			// 解析URL=Filter
			String urlMappingItem[] = line.split("=");
			String filterUrl = clean(urlMappingItem[0]);
			String filterString = clean(urlMappingItem[1]);
			String filterItem[] = filterString.split(",");
			AccessControlFilter filter = null;
			for (String filterName : filterItem) {
				filterName = clean(filterName);
				if (filter == null) {
					filter = filters.get(filterName);
				} else {
					AccessControlFilter filterNew = filters.get(filterName);
					try {
						filter.addFilter(filterNew);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					filter = filterNew;
				}
			}
			if (filter != null) {
				filterChainDefinitions.put(filterUrl, filter);
			}
		}
	}

	public static String clean(String in) {
		String out = in;
		if (in != null) {
			out = in.trim();
			if (out.equals("")) {
				out = null;
			}
		}
		return out;
	}

	public static final String COMMENT_POUND = "#";
	public static final String COMMENT_SEMICOLON = ";";
	public static final String SECTION_PREFIX = "[";
	public static final String SECTION_SUFFIX = "]";

	// public static void main(String[] args) {
	// String ss = "#jkjkj;\n/home=user,auth\n/home2=user1,u2,u3";
	// SecurityManager sManager = new SecurityManager();
	// sManager.setFilterChainDefinitions(ss);
	// System.out.println(ss);
	// }

	/**
	 * 注入过滤器
	 * 
	 * @param filters
	 */
	public void setFilters(Map<String, AccessControlFilter> filters) {
		this.filters = filters;
	}

	public boolean checkPermissions(String url, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 先精准匹配（哈希快速查找）
		// AccessControlFilter filter = filterChainDefinitions.get(url);
		// if (filter != null) {
		// filter.doFilter(request, response);
		// } else {
		// 没有精准匹配则模糊匹配
		boolean isAllow = true;
		for (Map.Entry<String, AccessControlFilter> entry : filterChainDefinitions.entrySet()) {
			String urlItem = entry.getKey().replace("*", "");
			if (url.startsWith(urlItem)) {
				isAllow = entry.getValue().doFilter(request, response);
				if(!isAllow){
					//如果拒绝退出循环体
					break;
				}
			}
		}
		// }
		return isAllow;
	}

	public static void main(String args[]) {
		String aString = "/page/updateItem*=authc,roles[数据管理员]";
		String bString = aString.replace("*", "");
		System.out.println(bString);

		String content = "runoob";

		String pattern = ".*runoob.*";

		boolean isMatch = Pattern.matches(pattern, content);

		System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
		
		String line = clean("ass sss");
		System.out.println(line);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = prepareServletRequest(req,res);
		HttpServletResponse response = prepareServletResponse(req,res);

		String  url=request.getServletPath().toString();
		//请求的路径
        log.info("filter-url>>>>>>>>>>>>>>>>>>>>{}",url);
        try {
			boolean isAllow = checkPermissions(url, request, response);
			if(isAllow){
				chain.doFilter(req, res);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
    protected HttpServletRequest prepareServletRequest(ServletRequest request, ServletResponse response) {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest http = (HttpServletRequest) request;
            return http;
        }
        return null;
    }
    
    protected HttpServletResponse prepareServletResponse(ServletRequest request, ServletResponse response) {
        if (response instanceof HttpServletResponse) {
        	HttpServletResponse http = (HttpServletResponse) response;
            return http;
        }
        return null;
    }

	@Override
	public boolean checkRequiresUser(RequiresUser requiresUser, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession  session = request.getSession();
		if(session!=null){
			Object object = session.getAttribute(CommonConstant.SESSION_USEROBJ_KEY);
			if(object instanceof UserSession){
				UserSession userSession = (UserSession)object;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkRequiresAuthentication(RequiresAuthentication requiresAuthentication,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession  session = request.getSession();
		if(session!=null){
			Object object = session.getAttribute(CommonConstant.SESSION_USEROBJ_KEY);
			if(object instanceof UserSession){
				UserSession userSession = (UserSession)object;
				if(userSession.isLogin()){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean checkRequiresPermissions(RequiresPermissions requiresPermissions, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession  session = request.getSession();
		if(session!=null){
			Object object = session.getAttribute(CommonConstant.SESSION_USEROBJ_KEY);
			if(object instanceof UserSession){
				UserSession userSession = (UserSession)object;
				if(userSession.isLogin()){
					//检查权限
					String[] hasPermissions = requiresPermissions.value();
					Set myPermissions = new HashSet();
					Logical logical = requiresPermissions.logical();
					if(logical==Logical.AND){
						for(String perm:hasPermissions){
							if(myPermissions.contains(perm)){
								continue;
							}else{
								return true;
							}
						}
					}else{
						for(String perm:hasPermissions){
							if(myPermissions.contains(perm)){
								return true;
							}
						}
					}
					
				}
			}
		}
		return false;
	}

	public void setAuthenticatingRealm(AuthenticatingRealm authenticatingRealm) {
		this.authenticatingRealm = authenticatingRealm;
	}

	public AuthenticatingRealm getAuthenticatingRealm() {
		return authenticatingRealm;
	}
	
	
	
	

}
