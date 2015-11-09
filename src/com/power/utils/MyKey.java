package com.power.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.power.utils.PowerStatic.CacheKey;

/**
 * 缓存key----------自定义注解
 * @author shuang
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyKey {
	/**
	 * key--不能为空
	 * @return
	 */
	public CacheKey key();
}