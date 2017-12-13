package com.gh.appserver.security.mgt;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractAccessControlFilter implements AccessControlFilter {
	protected Object mappedValue;
	
	protected FilterChain chain;

	/**
     * 判断ajax请求
     * 
     * @param request
     * @return
     */
    protected boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

	public Object getMappedValue() {
		return mappedValue;
	}

	public void setMappedValue(Object mappedValue) {
		this.mappedValue = mappedValue;
	}

	@Override
	public void addFilter(FilterChain chain) {
		this.chain = chain;
	}
	
	/**
	 * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
	 * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
	 */
	@Override
	public boolean doFilter(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean isAccess = isAccessAllowed(request,response,mappedValue) || onAccessDenied(request,response);
		if(!isAccess){
			if(chain!=null){
				//如果后续还有过滤器继续执行
				return chain.doFilter(request, response);
			}
		}
		//返回true表示放行，false表示验证失败了并且已经处理错误
		return isAccess;
	}
	
	 protected void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 	String loginUrl = MySecurityManager.loginUrl;
		 	String ethod = request.getMethod();
	        String queryString = request.getQueryString();
	        String requestURI = request.getRequestURI();
	        
	        String  url=request.getServletPath().toString();
	        if(StringUtils.isNotEmpty(queryString)){
	        	url+="&"+queryString;
	        }
			String contextPath=request.getContextPath();
			//被拦截，重定向到login界面
            response.sendRedirect(response.encodeRedirectURL(contextPath+loginUrl+"?ReturnUrl="
                    + URLEncoder.encode(url,"UTF-8"))); 

	       // redirectToLogin(request, response);
	    }
    
}
