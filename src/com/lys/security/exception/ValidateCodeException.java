package com.lys.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码错误异常
 * 
 * @author shuang
 * @email 124010356@qq.com
 * @create 2012.04.28
 */
public class ValidateCodeException extends AuthenticationException {
	private static final long serialVersionUID = 7719985584046462189L;
	public ValidateCodeException(String msg) {
		super(msg);
	}
	public ValidateCodeException(String msg, Throwable t) {
		super(msg, t);
	}
}
