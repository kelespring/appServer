package com.gh.appserver.security.mgt;

import com.gh.appserver.security.authc.AuthenticationInfo;
import com.gh.appserver.security.authc.AuthorizationInfo;
import com.gh.appserver.security.credential.CredentialsMatcher;
import com.gh.appserver.security.exception.AuthenticationException;

abstract class AuthenticatingRealm {
	
    /**
     * 加密算法器
     * 通过申请登录用户的密码和给到的盐，通过预设算法去匹配用户真实的密码。
     */
    private CredentialsMatcher credentialsMatcher;
    
	 /**
	  * 获取用户授权信息
	 * @param principals
	 * @return
	 */
	abstract protected AuthorizationInfo doGetAuthorizationInfo(Object principals);
	
	/**
	 * 获取用户认证信息
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	abstract protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException;
	
	
	/**
	 * 注入加密算法器
	 * @param credentialsMatcher
	 */
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        this.credentialsMatcher = credentialsMatcher;
    }

	public CredentialsMatcher getCredentialsMatcher() {
		return credentialsMatcher;
	}
	
	

}
