package com.gh.appserver.security.mgt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccessControlFilter extends FilterChain{
	//验证权限
	boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response, Object mappedValue) throws Exception;
	//验证失败处理
	boolean onAccessDenied(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
