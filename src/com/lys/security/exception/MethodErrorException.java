package com.lys.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 请求方法错误异常
 * @author shuang
 */
public class MethodErrorException extends AuthenticationException {
	private static final long serialVersionUID = -3676078732818869773L;

	public MethodErrorException(String msg) {
		super(msg);
	}

	public MethodErrorException(String msg, Throwable t) {
		super(msg, t);
	}

}
