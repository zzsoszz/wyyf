package com.lys.utils.myexception;

/**
 * 权限异常
 * 
 * @author shuang
 */
public class MytwoException extends Exception {
	private static final long serialVersionUID = -13345478422L;
	public MytwoException() {
		super();
	}
	public MytwoException(String msg) {
		super(msg);
	}
	public MytwoException(String msg, Throwable cause) {
		super(msg, cause);
	}
	public MytwoException(Throwable cause) {
		super(cause);
	}

}
