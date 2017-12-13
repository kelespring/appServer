package com.gh.appserver.security.credential;

import com.gh.appserver.security.authc.AuthenticationInfo;
import com.gh.appserver.security.mgt.AuthenticationToken;

public interface CredentialsMatcher {
	boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info);
}
