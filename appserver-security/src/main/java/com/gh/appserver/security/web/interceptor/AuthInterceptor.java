package com.gh.appserver.security.web.interceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.gh.appserver.security.authz.annotation.RequiresAuthentication;
import com.gh.appserver.security.authz.annotation.RequiresPermissions;
import com.gh.appserver.security.authz.annotation.RequiresUser;
import com.gh.appserver.security.mgt.MySecurityManager;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = LogManager.getLogger(AuthInterceptor.class);

	private MySecurityManager securityManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 判断当前资源是否有注解
		String url = request.getServletPath().toString();
		// 请求的路径
		System.out.println("filter>>>>>>>>>>>>>>>>>>>>");

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
//		RequiresGuest requiresGuest = method.getAnnotation(RequiresGuest.class);
		RequiresUser requiresUser = method.getAnnotation(RequiresUser.class);
		RequiresAuthentication requiresAuthentication = method.getAnnotation(RequiresAuthentication.class);
		RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
		
		//查看是否记住密码或已登录
		if (null != requiresUser) {
			securityManager.checkRequiresUser(requiresUser, request, response);
		}
		
		//查看是否登录认证
		if (null != requiresAuthentication) {
			securityManager.checkRequiresAuthentication(requiresAuthentication, request, response);
		}
		
		//查看是否需要鉴权
		if (null != requiresPermissions) {
			securityManager.checkRequiresPermissions(requiresPermissions, request, response);
		}
		
		
		
		return true;
	}

}
