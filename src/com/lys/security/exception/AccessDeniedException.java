package com.lys.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 权限异常
 * @author shuang
 */
public class AccessDeniedException extends AuthenticationException {
	private static final long serialVersionUID = -367607871218869773L;

	public AccessDeniedException(String msg) {
		super(msg);
	}

	public AccessDeniedException(String msg, Throwable t) {
		super(msg, t);
	}

}
