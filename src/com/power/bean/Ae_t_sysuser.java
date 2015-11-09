package com.power.bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;

/**
 * 系统用户表
 * @author shuang
 */
@Entity
@Table(name = "ae_t_sysuser")
@MyTable(tableName = "ae_t_sysuser",pkName="ae_st_id",anotherName="ae")
public class Ae_t_sysuser implements UserDetails {
	private static final long serialVersionUID = 1000000105L;
	
	@Transient private List<Ag_t_file> fileList;//图片List
	//======业务数据=========
	@Column(length=1)private String ae_st_type;//用户类型表 1、普通用户 2、平台管理员 3、 商家 4、师傅  5. 设计师   6工长
	
	@Column(length=25)private String ae_st_name;//姓名
	@Column(length=25)private String ae_st_tell;//电话（手机）
	@Column(length=100)private String ae_st_header;//商家店铺图片，用户头像存入了 ag_t_file表中了。查询的时候  (select ag_st_url from ag_t_file where ag_st_objid = a.ae_st_id) ag_st_url
	@Column(length=1)private String ae_st_sex;//性别
	@Column(length=10)private int ae_nm_age;//年龄 
	@Column(length=100)private String ae_st_address;//详细地址
	@Column(length=255)private String ae_st_intro;//简介
	@Column(length =50)private String ae_st_shsheng;//省
	@Column(length =50)private String ae_st_shshi;//市
	@Column(length =50)private String ae_st_shxian;//县/区
	@Column(length =50)private String ae_st_jiedao;//乡/镇/街道 
	@Column(length =10)private double ae_nm_zjnum;//资金余额
	@Column(length =50)private String ae_st_loginflag;//登录标示符
	@Column(length =50)private String uid;//qq 3
	@Column(length =50)private String xl;//新浪  1
	@Column(length =50)private String wx;//微信  2
	@Column(length=100)private double mb;//米币
	@Column(length=100)private String emll;//邮箱
	@Column(length=100)private String vouchers;//米币
	// ===========业务内的基本属性==========
	@Id
	@Column(length=32)private String ae_st_id;//用户ID    
	@Column(length=32)private String ae_st_objid;//对应的对象ID（存储各种类型用户表对应的id）    【PK】
	@Column(length=50)private String ae_st_objtype;//对应的对象（存储各种类型用户表,表名）    
	@Column(length=20)private String ae_st_nickName;//用户昵称
	@Column(length=1)private String ae_st_userkind;//用户类别 1 超级管理员,2普通管理员,3用户 ,4.开发者
	@Column(length=1,nullable=false)private String ae_st_lockstate;//账户锁定状态：1正常，2账户停用3.注销
	@Column(length=500)private String ae_st_description;//用户描述length="50"
	private Date ae_dt_lastlogontime;//上次登陆时间length="11"
	private Date ae_dt_lastlogofftime;//上次离开时间length="11"
	@Column(length=20)private String ae_st_lastlogonIp;// 上次登录IP
	@Column(length=256)private String ae_st_logoffreason;// 离开原因(注销帐号、停用 原因) length="50"
	@Column(length=8)private int ae_nm_loginedcount;//已登陆次数length="8"
	@Column(length=11)private Date ae_dt_passwdlastchange;//密码上次修改时间length="11"
	@Column(length =500)private String ae_st_remark;//备注，备用字段
	@Column(length =32)private String ae_st_addUserId;//创建人员ID  
	private Date ae_dt_addDate;//创建时间
	@Column(length =32)private String ae_st_updUserId;//修改人员ID 
	private Date ae_dt_updDate;//修改时间
	// ===========安全架构security3.1 特别属性===========
	@Column(length=50,unique = true)private String username;//后台用户名帐号（用户登录时的用户名帐号）length="50"
	@Column(length=50)private String password; // 密码
	@Transient private boolean accountNonExpired = true; // 帐号是否过期
	@Transient private boolean accountNonLocked = true; // 帐号是否锁定
	@Transient private boolean credentialsNonExpired = true; // 凭据非过期
	@Transient private boolean enabled = true; // 是否可用
	@Transient private Collection<? extends GrantedAuthority> authorities; // 权限集合
	@Transient private Map<String,String> functions; // 自定义权限集合，用于springmvc拦截器权限控制
	@Transient String yzm;//验证码
	
	
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	/**
	 * 构造函数
	 */
	public Ae_t_sysuser() {}
	/**
	 * 自定义主键ID
	 * @param ae_st_id 主键ID
	 */
	public Ae_t_sysuser(String ae_st_id) {
		this.ae_st_id = ae_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Ae_t_sysuser(boolean isAutoId) {
		if(isAutoId){
			this.ae_st_id =StringUtils.getUUID32();
		}
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("ae_st_id", getAe_st_id()).toString();
	}
	// 获取当前登录用户详细信息必须重写hashCode和equals方法
	@Override
	public int hashCode() {
		return this.getUsername().hashCode();
	}
	@Override
	public boolean equals(Object object) {
		boolean flag = false;
		if (object instanceof UserDetails) {
			UserDetails user = (UserDetails) object;
			if (user.getUsername().equals(this.getUsername()))
				flag = true;
		}
		return flag;
	}
	public String getAe_st_id() {
		return ae_st_id;
	}
	public void setAe_st_id(String aeStId) {
		ae_st_id = aeStId;
	}
	public String getAe_st_objid() {
		return ae_st_objid;
	}
	public void setAe_st_objid(String aeStObjid) {
		ae_st_objid = aeStObjid;
	}
	public String getAe_st_objtype() {
		return ae_st_objtype;
	}
	public void setAe_st_objtype(String aeStObjtype) {
		ae_st_objtype = aeStObjtype;
	}
	public String getAe_st_userkind() {
		return ae_st_userkind;
	}
	public void setAe_st_userkind(String aeStUserkind) {
		ae_st_userkind = aeStUserkind;
	}
	public String getAe_st_lockstate() {
		return ae_st_lockstate;
	}
	public void setAe_st_lockstate(String aeStLockstate) {
		ae_st_lockstate = aeStLockstate;
	}
	public String getAe_st_description() {
		return ae_st_description;
	}
	public void setAe_st_description(String aeStDescription) {
		ae_st_description = aeStDescription;
	}
	public Date getAe_dt_lastlogontime() {
		return ae_dt_lastlogontime;
	}
	public void setAe_dt_lastlogontime(Date aeDtLastlogontime) {
		ae_dt_lastlogontime = aeDtLastlogontime;
	}
	public Date getAe_dt_lastlogofftime() {
		return ae_dt_lastlogofftime;
	}
	public void setAe_dt_lastlogofftime(Date aeDtLastlogofftime) {
		ae_dt_lastlogofftime = aeDtLastlogofftime;
	}
	public String getAe_st_logoffreason() {
		return ae_st_logoffreason;
	}
	public void setAe_st_logoffreason(String aeStLogoffreason) {
		ae_st_logoffreason = aeStLogoffreason;
	}
	public int getAe_nm_loginedcount() {
		return ae_nm_loginedcount;
	}
	public void setAe_nm_loginedcount(int aeNmLoginedcount) {
		ae_nm_loginedcount = aeNmLoginedcount;
	}
	public Date getAe_dt_passwdlastchange() {
		return ae_dt_passwdlastchange;
	}
	public void setAe_dt_passwdlastchange(Date aeDtPasswdlastchange) {
		ae_dt_passwdlastchange = aeDtPasswdlastchange;
	}
	public String getAe_st_remark() {
		return ae_st_remark;
	}
	public void setAe_st_remark(String aeStRemark) {
		ae_st_remark = aeStRemark;
	}
	public String getAe_st_addUserId() {
		return ae_st_addUserId;
	}
	public void setAe_st_addUserId(String aeStAddUserId) {
		ae_st_addUserId = aeStAddUserId;
	}
	public Date getAe_dt_addDate() {
		return ae_dt_addDate;
	}
	public void setAe_dt_addDate(Date aeDtAddDate) {
		ae_dt_addDate = aeDtAddDate;
	}
	public String getAe_st_updUserId() {
		return ae_st_updUserId;
	}
	public void setAe_st_updUserId(String aeStUpdUserId) {
		ae_st_updUserId = aeStUpdUserId;
	}
	public Date getAe_dt_updDate() {
		return ae_dt_updDate;
	}
	public void setAe_dt_updDate(Date aeDtUpdDate) {
		ae_dt_updDate = aeDtUpdDate;
	}
	@Override
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public Map<String, String> getFunctions() {
		return functions;
	}
	public String getAe_st_type() {
		return ae_st_type;
	}
	public void setAe_st_type(String ae_st_type) {
		this.ae_st_type = ae_st_type;
	}
	public String getAe_st_name() {
		return ae_st_name;
	}
	public void setAe_st_name(String ae_st_name) {
		this.ae_st_name = ae_st_name;
	}
	public String getAe_st_tell() {
		return ae_st_tell;
	}
	public void setAe_st_tell(String ae_st_tell) {
		this.ae_st_tell = ae_st_tell;
	}
	
	public String getAe_st_header() {
		return ae_st_header;
	}
	public void setAe_st_header(String ae_st_header) {
		this.ae_st_header = ae_st_header;
	}
	public String getAe_st_sex() {
		return ae_st_sex;
	}
	public void setAe_st_sex(String ae_st_sex) {
		this.ae_st_sex = ae_st_sex;
	}
	public int getAe_nm_age() {
		return ae_nm_age;
	}
	public void setAe_nm_age(int ae_nm_age) {
		this.ae_nm_age = ae_nm_age;
	}
	public String getAe_st_address() {
		return ae_st_address;
	}
	public void setAe_st_address(String ae_st_address) {
		this.ae_st_address = ae_st_address;
	}
	public String getAe_st_intro() {
		return ae_st_intro;
	}
	public void setAe_st_intro(String ae_st_intro) {
		this.ae_st_intro = ae_st_intro;
	}
	public String getAe_st_shsheng() {
		return ae_st_shsheng;
	}
	public void setAe_st_shsheng(String ae_st_shsheng) {
		this.ae_st_shsheng = ae_st_shsheng;
	}
	public String getAe_st_shshi() {
		return ae_st_shshi;
	}
	public void setAe_st_shshi(String ae_st_shshi) {
		this.ae_st_shshi = ae_st_shshi;
	}
	public String getAe_st_shxian() {
		return ae_st_shxian;
	}
	public void setAe_st_shxian(String ae_st_shxian) {
		this.ae_st_shxian = ae_st_shxian;
	}
	public String getAe_st_jiedao() {
		return ae_st_jiedao;
	}
	public void setAe_st_jiedao(String ae_st_jiedao) {
		this.ae_st_jiedao = ae_st_jiedao;
	}
	public double getAe_nm_zjnum() {
		return ae_nm_zjnum;
	}
	public void setAe_nm_zjnum(double ae_nm_zjnum) {
		this.ae_nm_zjnum = ae_nm_zjnum;
	}
	public String getAe_st_nickName() {
		return ae_st_nickName;
	}
	public void setAe_st_nickName(String ae_st_nickName) {
		this.ae_st_nickName = ae_st_nickName;
	}
	public String getAe_st_lastlogonIp() {
		return ae_st_lastlogonIp;
	}
	public void setAe_st_lastlogonIp(String ae_st_lastlogonIp) {
		this.ae_st_lastlogonIp = ae_st_lastlogonIp;
	}
	public void setFunctions(Map<String, String> functions) {
		this.functions = functions;
	}
	public String getAe_st_loginflag() {
		return ae_st_loginflag;
	}
	public void setAe_st_loginflag(String ae_st_loginflag) {
		this.ae_st_loginflag = ae_st_loginflag;
	}
	public List<Ag_t_file> getFileList() {
		return fileList;
	}
	public void setFileList(List<Ag_t_file> fileList) {
		this.fileList = fileList;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getEmll() {
		return emll;
	}
	public void setEmll(String emll) {
		this.emll = emll;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public double getMb() {
		return mb;
	}
	public void setMb(double mb) {
		this.mb = mb;
	}
	public String getVouchers() {
		return vouchers;
	}
	
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	public String getWx() {
		return wx;
	}
	public void setWx(String wx) {
		this.wx = wx;
	}
	public void setVouchers(String vouchers) {
		this.vouchers = vouchers;
	}
	
}
