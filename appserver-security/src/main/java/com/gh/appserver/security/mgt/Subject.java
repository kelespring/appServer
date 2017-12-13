package com.gh.appserver.security.mgt;

import java.util.Set;

import com.gh.appserver.security.exception.AuthenticationException;

public interface Subject {
    //获取认证信息
    Object getPrincipal();
    //获取授权信息
    Set<String> getPermissions();
    //登录
    void login(AuthenticationToken token) throws AuthenticationException;
    //登出
    void logout();
}
