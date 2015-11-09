package com.lys.system.dictionary;

import com.lys.mvc.biz.BaseBiz;
import com.lys.utils.MessageSourceHelper;
import com.power.biz.CommonBiz;

/**
 * 一个存储系统 部分重要属性 值 的类
 * @author lys
 *
 */
public class SysStatic {
	/**
	 * 系统属性
	 */
	public static String fileUploadPathSet;//文件上传后的根目录的设置路径 ， 在InitSystem中配置上传地址
	public static String fileUploadPathDefault;//文件上传后的根目录的默认路径 ， 在InitSystem中配置上传地址
	public static String driverClass;//驱动
	public static String jdbcUrl;//连接地址
	public static String user;//用户
	public static String password;//密码
	public static String dbName;//当前使用的数据库名,默认是MYSQL
	public static String hbm2ddlAuto;//系统启动方式
	public static String adminUsername;//超级管理员帐号
	public static String adminPassword;//超级管理员密码
	/**
	 * 各种数据库名
	 */
	public static String MYSQL="MYSQL";
	public static String ORACLE="ORACLE";
	/**
	 * 其他
	 */
	public static MessageSourceHelper i18nmessage;//读取国际化语言文件对象，在系统初始化赋值
	public static CommonBiz commonBizCache;//读取缓存字典对象
	public static BaseBiz baseBiz;//读取数据操作对象
}
