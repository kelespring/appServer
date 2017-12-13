package com.gh.appserver.security.authz.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @RequiresUser  
 * 表示当前Subject已经身份验证或者通过记住我登录的
 * @author kevin
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresUser {

}
