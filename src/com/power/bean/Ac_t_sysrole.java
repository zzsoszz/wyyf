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
 * 系统角色表
 * @author shuang
 */
@Entity
@Table(name = "ac_t_sysrole")
@MyTable(tableName = "ac_t_sysrole",pkName="ac_st_id",anotherName="ac")
public class Ac_t_sysrole implements Serializable {
	private static final long serialVersionUID = 1000000103L;
	@Id
	@Column(length =32)private String ac_st_id;//角色ID  uuid.hex length="32"
	@Column(length =50,nullable=false)private String ac_st_name;//角色名称length="50"
	@Column(length =50,nullable=false,unique=true)private String ac_st_code;//角色码值length="50"
	@Column(length =256)private String ac_st_describe;//角色描述length="50"
	@Column(length =1)private String ac_st_type;//0:超级管理角色;1:经办角色length="1"
	@Column(length =500)private String ac_st_remark;//备注，备用字段
	@Column(length =32)private String ac_st_addUserId;//创建人员ID  
	private Date ac_dt_addDate;//创建时间
	@Column(length =32)private String ac_st_updUserId;//修改人员ID 
	private Date ac_dt_updDate;//修改时间
	
	/**
	 * 构造函数
	 */
	public Ac_t_sysrole() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Ac_t_sysrole(String ac_st_id) {
		this.ac_st_id = ac_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Ac_t_sysrole(boolean isAutoId) {
		if(isAutoId){
			this.ac_st_id =StringUtils.getUUID32();
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("ac_st_id", getAc_st_id()).toString();
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Ac_t_sysrole)) {
			return false;
		}
		final Ac_t_sysrole castOther = (Ac_t_sysrole) other;
		return new EqualsBuilder().append(getAc_st_id(), castOther.getAc_st_id()).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getAc_st_id()).toHashCode();
	}
	public String getAc_st_id() {
		return ac_st_id;
	}
	public void setAc_st_id(String acStId) {
		ac_st_id = acStId;
	}
	public String getAc_st_name() {
		return ac_st_name;
	}
	public void setAc_st_name(String acStName) {
		ac_st_name = acStName;
	}
	public String getAc_st_code() {
		return ac_st_code;
	}
	public void setAc_st_code(String acStCode) {
		ac_st_code = acStCode;
	}
	public String getAc_st_describe() {
		return ac_st_describe;
	}
	public void setAc_st_describe(String acStDescribe) {
		ac_st_describe = acStDescribe;
	}
	public String getAc_st_type() {
		return ac_st_type;
	}
	public void setAc_st_type(String acStType) {
		ac_st_type = acStType;
	}
	public String getAc_st_remark() {
		return ac_st_remark;
	}
	public void setAc_st_remark(String acStRemark) {
		ac_st_remark = acStRemark;
	}
	public String getAc_st_addUserId() {
		return ac_st_addUserId;
	}
	public void setAc_st_addUserId(String acStAddUserId) {
		ac_st_addUserId = acStAddUserId;
	}
	public Date getAc_dt_addDate() {
		return ac_dt_addDate;
	}
	public void setAc_dt_addDate(Date acDtAddDate) {
		ac_dt_addDate = acDtAddDate;
	}
	public String getAc_st_updUserId() {
		return ac_st_updUserId;
	}
	public void setAc_st_updUserId(String acStUpdUserId) {
		ac_st_updUserId = acStUpdUserId;
	}
	public Date getAc_dt_updDate() {
		return ac_dt_updDate;
	}
	public void setAc_dt_updDate(Date acDtUpdDate) {
		ac_dt_updDate = acDtUpdDate;
	}
	 
}
