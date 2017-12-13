package com.gh.appserver.security.authc.impl;

import com.gh.appserver.security.authc.SaltedAuthenticationInfo;
import com.google.common.io.ByteSource;

/**
 * 认证信息
 * 
 * @author kevin
 *
 */
public class SimpleAuthenticationInfo implements SaltedAuthenticationInfo {
	protected Object principals;
	
    protected Object credentials;


	@Override
	public ByteSource getCredentialsSalt() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
