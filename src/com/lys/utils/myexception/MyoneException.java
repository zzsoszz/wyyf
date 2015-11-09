package com.lys.utils.myexception;

/**
 * 权限异常
 * 
 * @author shuang
 */
public class MyoneException extends Exception {
	private static final long serialVersionUID = -13345478421L;
	public MyoneException() {
		super();
	}
	public MyoneException(String msg) {
		super(msg);
	}
	public MyoneException(String msg, Throwable cause) {
		super(msg, cause);
	}
	public MyoneException(Throwable cause) {
		super(cause);
	}

}
