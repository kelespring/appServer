package com.gh.appserver.security.mgt;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 权限管理器
 * 注入系统资源访问规则
 * 注入过滤器
 * 注入资源访问权限失败URL，登录URL
 * 提供对外：
 * hasPermissions(url)
 * 内部
 * @author kevin
 * 
 */
public class SecurityManager {
	
    private Map<String, AccessControlFilter> filters;

    private Map<String, AccessControlFilter> filterChainDefinitions = new LinkedHashMap<>();
	
	private String loginUrl;
    private String successUrl;
    private String unauthorizedUrl;

	
    private static transient final Logger log = LoggerFactory.getLogger(SecurityManager.class);
    

	/**
	 * 权限规则,路径+过滤器
	 * 匹配规则：hash.get(url)是否命中（为了速度），没有命中就顺序遍历LIST（注意带**的startWith判断）
	 * 一旦匹配到就getBean("filterName")，执行对应的isAccessAllowed||onAccessDenied，返回true表示执行成功
	 * 配置注意：粒度小的在前
	 * /home=user
	 * /authenticated=authc
	 * /permission=authc
	 * /static/**=anon
	 * /**=anon
	 */
	//权限控制器
	public void setFilterChainDefinitions(String definitions) {
		Scanner scanner = new Scanner(definitions);
        while (scanner.hasNextLine()) {
            String rawLine = scanner.nextLine();
            String line = clean(rawLine);
            if (line == null || line.startsWith(COMMENT_POUND) || line.startsWith(COMMENT_SEMICOLON)) {
                //skip empty lines and comments:
                continue;
            }
            //解析URL=Filter
            String urlMappingItem[] = line.split("=");
            String filterString = urlMappingItem[1];
            String filterItem[] = filterString.split(",");
            AccessControlFilter filter;
            for(String filterName:filterItem){
            	 filter = filters.get(urlMappingItem[1]);
            	 filter.doFilter(request, response, chain);
            }
            filters.put(urlMappingItem[0], filters.get(urlMappingItem[1]));
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
    
    public static void main(String[] args) {
		String ss = "#jkjkj;\n/home=user,auth\n/home2=user1,u2,u3";
		SecurityManager sManager =new SecurityManager();
		sManager.setFilterChainDefinitions(ss);
		System.out.println(ss);
	}
    
    
    /**
     * 注入过滤器
     * @param filters
     */
    public void setFilters(Map<String, AccessControlFilter> filters) {
        this.filters = filters;
    }
}
