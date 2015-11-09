package com.lys.security.encryption.impl;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import com.lys.security.encryption.Encoder;


/**
 * MD5加密工具实现类(不支持逆转算法)
 * @author shuang
 */
public class MD5Encoder implements Encoder {
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
		return new Md5PasswordEncoder().encodePassword(password, null);//使用SPRING SECURITY3里的MD5实现类
	}
	@Override
	public String encrypt(String password, String salt) {
		return new Md5PasswordEncoder().encodePassword(password, salt);//使用SPRING SECURITY3里的MD5实现类
	}

	public static void main(String[] args) {
		MD5Encoder e=new MD5Encoder();
		String val=e.encrypt("123456","admin");
		System.out.println(val);
	}
}
