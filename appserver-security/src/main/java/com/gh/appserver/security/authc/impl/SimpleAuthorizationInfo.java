package com.gh.appserver.security.authc.impl;
import java.util.Set;
import com.gh.appserver.security.authc.AuthorizationInfo;
/**
 * 权限信息（realm返回）
 * @author kevin
 *
 */
public class SimpleAuthorizationInfo implements AuthorizationInfo {
	protected Set<String> stringPermissions;

	@Override
	public Set<String> getStringPermissions() {
		return stringPermissions;
	}
	
	public void setStringPermissions(Set<String> stringPermissions) {
        this.stringPermissions = stringPermissions;
    }

}
