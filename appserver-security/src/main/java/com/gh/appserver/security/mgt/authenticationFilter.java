package com.gh.appserver.security.mgt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.appserver.web.common.CommonConstant;

public class authenticationFilter extends AbstractAccessControlFilter {
	private Object mappedValue;

	@Override
	public boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response, Object mappedValue)
			throws Exception {
		// 已经登陆
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(CommonConstant.SESSION_USEROBJ_KEY);
		if(obj!=null){
			if(obj instanceof UserSession){
				UserSession userSession = (UserSession)obj;
				if(userSession.isLogin()){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean onAccessDenied(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (isAjax(request)) {
			
		}else{
			
		}
		return false;
	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, AccessControlFilter chain) throws Exception {
		boolean isAllowed = isAccessAllowed(request,response,mappedValue) && onAccessDenied(request,response);
		if(isAllowed){
			chain.doFilter(request, response, chain);
		}
	}

}
