package com.lys.security.encryption.impl;

import org.springframework.security.authentication.encoding.Md4PasswordEncoder;
import com.lys.security.encryption.Encoder;


/**
 * MD4加密工具实现类(不支持逆转算法)
 * @author shuang
 */
public class MD4Encoder implements Encoder {
	@Override
	public String decrypt(String password, String key) {
		throw new UnsupportedOperationException("Not supported the mehtod");//不支持该方法抛出UnsupportedOperationException异常
	}
	@Override
	public String decrypt(String password) {
		throw new UnsupportedOperationException("Not supported the mehtod");//不支持该方法抛出UnsupportedOperationException异常
	}
	@Override
	public String encrypt(String password) {
		return new Md4PasswordEncoder().encodePassword(password, null);//使用SPRING SECURITY3里的MD4实现类
	}
	@Override
	public String encrypt(String password, String salt) {
		return new Md4PasswordEncoder().encodePassword(password, salt);//使用SPRING SECURITY3里的MD4实现类
	}

}
