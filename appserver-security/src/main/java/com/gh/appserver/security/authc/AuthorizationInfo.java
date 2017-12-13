package com.gh.appserver.security.authc;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * 用户授权接口
 * 
 * @author kevin
 *
 */
public interface AuthorizationInfo extends Serializable {
	/**
	 * 获取用户权限信息
	 * {"user:view","user:add","user:edit","user:delete"}
	 * @return
	 */
	Collection<String> getStringPermissions();
}
