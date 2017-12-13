package com.gh.appserver.security.mgt;

import java.util.Set;

import com.gh.appserver.security.exception.AuthenticationException;

public class SecurityUtils {
	public static MySecurityManager securityManager;

	public static void setSecurityManager(MySecurityManager securityManager) {
		SecurityUtils.securityManager = securityManager;
	}
	
	

	public static MySecurityManager getSecurityManager() {
		return securityManager;
	}



	public static Subject getSubject() {
		Subject subject = ThreadContext.getSubject();
		if (subject == null) {
			//新建一个匿名subject
			new Subject() {
				
				@Override
				public void logout() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void login(AuthenticationToken token) throws AuthenticationException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public Object getPrincipal() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Set<String> getPermissions() {
					// TODO Auto-generated method stub
					return null;
				}
			};
			ThreadContext.bind(subject);
		}
		return subject;
	}
}
