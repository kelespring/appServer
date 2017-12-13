package com.gh.appserver.security.mgt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gh.appserver.security.authz.annotation.RequiresAuthentication;
import com.gh.appserver.security.authz.annotation.RequiresPermissions;
import com.gh.appserver.security.authz.annotation.RequiresUser;

/**
 * 权限判断接口
 * @author kevin
 *
 */
public interface AuthorizationCheck {
	//判断是否记住密码或登录
	boolean checkRequiresUser(RequiresUser requiresUser,HttpServletRequest request, HttpServletResponse response);
	//判断是否登录
	boolean checkRequiresAuthentication(RequiresAuthentication requiresAuthentication,HttpServletRequest request, HttpServletResponse response);
	//判断是否拥有权限
	boolean checkRequiresPermissions(RequiresPermissions requiresPermissions,HttpServletRequest request, HttpServletResponse response);


}
