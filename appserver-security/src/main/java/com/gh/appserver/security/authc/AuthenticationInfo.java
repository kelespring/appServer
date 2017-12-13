package com.gh.appserver.security.authc;

import java.io.Serializable;

/**
 * 目前只支持字符串
 * @author kevin
 *
 */
public interface AuthenticationInfo extends Serializable{
	//用户信息userName
    Object getPrincipal();
    //密码password
    Object getCredentials();
}
