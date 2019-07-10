package com.drp.annotations;

import java.lang.annotation.*;
/*
 *  有这个注解则说明该方法不需要判断登录
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoNeedLogin {
}
