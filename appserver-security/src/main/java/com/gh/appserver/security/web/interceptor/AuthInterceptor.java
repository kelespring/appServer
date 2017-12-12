package com.gh.appserver.security.web.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gh.appserver.security.mgt.MySecurityManager;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	private MySecurityManager securityManager;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        String  url=request.getServletPath().toString();
		//请求的路径
        System.out.println("filter>>>>>>>>>>>>>>>>>>>>");
        return securityManager.checkPermissions(url, request, response);
	}
	
	public void setSecurityManager(MySecurityManager securityManager) {
		this.securityManager = securityManager;
	}
	
}
