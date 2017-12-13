package com.gh.appserver.security.authz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @RequiresGuest 
 * 表示当前Subject没有身份验证或通过记住我登录过，即是游客身份
 * @author kevin
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresGuest {

}
