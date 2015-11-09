package com.power.bean;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;

/**
 * 用户登录记录表
 * @author shuang
 */
@Entity
@Table(name = "aj_t_userlogin")
@MyTable(tableName = "aj_t_userlogin",pkName="aj_st_id",anotherName="aj")
public class Aj_t_UserLogin implements Serializable {
	private static final long serialVersionUID = 1000000110L;
	@Id
	@Column(length =32)private String aj_st_id;//ID  
	@Column(length =32)private String aj_st_objid;//对象ID
	@Column(length =32)private String aj_st_objname;//对象所在表名
	@Column(length =1)private String aj_st_status;//在线状态， 1 在线 2离线  
	@Column(length =20)private String aj_st_loginip;//登录IP
	private Date aj_dt_logintime;//登录时间
	@Column(length =40)private String aj_st_sessionid;//登录相关SESSIONID
	@Column(length =200)private String aj_st_clientinfo;//客户端信息
	private Date aj_dt_logouttime;//离线时间
	/**
	 * 构造函数
	 */
	public Aj_t_UserLogin() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Aj_t_UserLogin(String aj_st_id) {
		this.aj_st_id = aj_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Aj_t_UserLogin(boolean isAutoId) {
		if(isAutoId){
			this.aj_st_id =StringUtils.getUUID32();
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("aj_st_id", getAj_st_id()).toString();
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Aj_t_UserLogin)) {
			return false;
		}
		final Aj_t_UserLogin castOther = (Aj_t_UserLogin) other;
		return new EqualsBuilder().append(getAj_st_id(), castOther.getAj_st_id()).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getAj_st_id()).toHashCode();
	}
	public String getAj_st_id() {
		return aj_st_id;
	}
	public void setAj_st_id(String aj_st_id) {
		this.aj_st_id = aj_st_id;
	}
	public String getAj_st_objid() {
		return aj_st_objid;
	}
	public void setAj_st_objid(String aj_st_objid) {
		this.aj_st_objid = aj_st_objid;
	}
	public String getAj_st_objname() {
		return aj_st_objname;
	}
	public void setAj_st_objname(String aj_st_objname) {
		this.aj_st_objname = aj_st_objname;
	}
	public String getAj_st_status() {
		return aj_st_status;
	}
	public void setAj_st_status(String aj_st_status) {
		this.aj_st_status = aj_st_status;
	}
	public String getAj_st_loginip() {
		return aj_st_loginip;
	}
	public void setAj_st_loginip(String aj_st_loginip) {
		this.aj_st_loginip = aj_st_loginip;
	}
	public String getAj_st_sessionid() {
		return aj_st_sessionid;
	}
	public void setAj_st_sessionid(String aj_st_sessionid) {
		this.aj_st_sessionid = aj_st_sessionid;
	}
	public String getAj_st_clientinfo() {
		return aj_st_clientinfo;
	}
	public void setAj_st_clientinfo(String aj_st_clientinfo) {
		this.aj_st_clientinfo = aj_st_clientinfo;
	}
	public Date getAj_dt_logouttime() {
		return aj_dt_logouttime;
	}
	public void setAj_dt_logouttime(Date aj_dt_logouttime) {
		this.aj_dt_logouttime = aj_dt_logouttime;
	}
	public Date getAj_dt_logintime() {
		return aj_dt_logintime;
	}
	public void setAj_dt_logintime(Date aj_dt_logintime) {
		this.aj_dt_logintime = aj_dt_logintime;
	}
	
}
