package com.gh.appserver.security.authc;

import java.io.Serializable;

public interface AuthenticationToken extends Serializable{
    Object getPrincipal();//用户信息
    Object getCredentials();//密码信息
}
