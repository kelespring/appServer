package com.gh.appserver.security.authz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户鉴权
 * @RequiresPermissions (value={“user:a”, “user:b”}, logical= Logical.OR) 
 * @author kevin
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissions {

	String[] value();

	Logical logical() default Logical.AND;
}
