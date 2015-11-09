package com.lys.security.encryption.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.lys.security.encryption.Encoder;
import com.lys.system.filter.SafetyFilter;


/**
 * AES加密工具实现类(支持逆转算法)
 * @author shuang
 */
public class AESEncoder implements Encoder {
	/**
	 * 示例
	 * @param args
	 */
	public static void main(String[] args) {
		/*String x="oBBb1jpj77s65YZx7ScIcSgpt_dQ^"+System.currentTimeMillis();
		System.out.println("加密前："+x);
		Encoder aes=new AESEncoder();
		String xh=aes.encrypt(x);
		System.out.println("加密后："+xh);
		String xj=aes.decrypt(xh);
		System.out.println("解密后："+xj);
		System.out.println(xj.substring(0, xj.indexOf("^"))+"  "+xj.substring( xj.indexOf("^")+1));
		*/
		long xx=(System.currentTimeMillis() - Long.valueOf("1412820086536")) / (1000  );
		if(xx>7200){
			System.out.println(123);
		}else{
			System.out.println(321);
		}
		System.out.println((System.currentTimeMillis() - Long.valueOf("1412820086536")) / (1000  ));
	}
	/**
	 * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符
	 */
	private final static String KEY = "_mf$GJ#df@$FsG)1";//$
	
	/**
	 * 解密
	 */
	@Override
	public String decrypt(String password) {
		return decrypt(password,KEY);
	}
	/**
	 * 解密
	 */
	@Override
	public String decrypt(String password, String key) {
		if (null == key || "".equals(key) || key.length() != 16)
			return null;
		try {
			byte[] raw = key.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = hex2byte(password);
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			} catch (Exception e) {
				SafetyFilter.logger.info(e.getMessage());
				return null;
			}
		} catch (Exception ex) {
			SafetyFilter.logger.info(ex.getMessage());
			return null;
		}
	}

	@Override
	public String encrypt(String password, String key) {
		return execute(password, key);
	}

	@Override
	public String encrypt(String password) {
		return execute(password, KEY);
	}

	/**
	 * 加密逻辑
	 * @param password 密码
	 * @param key 钥匙
	 * @return String
	 */
	private String execute(String password, String key) {
		if (null == password || "".equals(password))
			password = DEFAULT_PASSWORD;
		if (null == key || "".equals(key))
			return null;
		byte[] raw;
		try {
			raw = KEY.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(password.trim().getBytes());
			return byte2hex(encrypted).toLowerCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 16进制转byte
	 * @param strhex
	 * @return byte[]
	 */
	private byte[] hex2byte(String strhex) {
		if (strhex == null) {
			return null;
		}
		int l = strhex.length();
		if (l % 2 == 1) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
					16);
		}
		return b;
	}

	/**
	 * byte转16进制
	 * @param b 字节数组
	 * @return String
	 */
	private String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

}
