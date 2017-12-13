package com.gh.appserver.security.authc;

import com.google.common.io.ByteSource;

public interface SaltedAuthenticationInfo {
	ByteSource getCredentialsSalt();
}
