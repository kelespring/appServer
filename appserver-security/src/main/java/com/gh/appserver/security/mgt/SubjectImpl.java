package com.gh.appserver.security.mgt;

import java.util.Set;

import com.gh.appserver.security.authc.AuthenticationInfo;
import com.gh.appserver.security.credential.CredentialsMatcher;
import com.gh.appserver.security.exception.AuthenticationException;

public class SubjectImpl implements Subject{

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
	
	/**
	 * 调用Realm获取用户信息，不成功抛出异常！
	 */
	@Override
	public void login(AuthenticationToken token) throws AuthenticationException {
		AuthenticatingRealm authenticatingRealm = SecurityUtils.getSecurityManager().getAuthenticatingRealm();
		AuthenticationInfo authenticationInfo = authenticatingRealm.doGetAuthenticationInfo(token);//获取认证信息
		//密码认证
		authenticationInfo.getPrincipal();//用户名
		authenticationInfo.getCredentials();//密码
		//盐
		CredentialsMatcher credentialsMatcher = authenticatingRealm.getCredentialsMatcher();
		credentialsMatcher.doCredentialsMatch(token, authenticationInfo);
		//加密
	}

	@Override
	public void logout() {
	}

}
