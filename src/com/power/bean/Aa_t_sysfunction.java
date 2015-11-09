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
 * 系统权限（菜单）表
 * @author shuang
 */

@Entity
@Table(name = "aa_t_sysfunction")
@MyTable(tableName = "aa_t_sysfunction",pkName="aa_st_id",anotherName="aa")
public class Aa_t_sysfunction implements  Serializable {
	private static final long serialVersionUID = 1000000101L;// 01+=a+ 
	@Id
	@Column(length =32)private String aa_st_id;//权限ID  uuid.hex length="32"
	@Column(length =100)private String aa_st_title;//节点的名字
	@Column(length =32)private String aa_st_parent;//节点的父节点length="32"
	@Column(length=200,nullable=false,unique=true)private String aa_st_mark;//权限标识--唯一 length="256"  
	@Column(length=200,nullable=false,unique=true)private String aa_st_classUrl;//权限类+方法名路径--唯一 length="256" ，例如：SysfunctionAction.initSysfunction
	@Column(length =200)private String aa_st_url;//权限相关的URL
	@Column(length =512)private String aa_st_describe;//权限中文描述length="256"
	@Column(length =3)private String aa_st_type;//结点类型：根节点0，主菜单是1，子菜单是2，3是3级菜单，其他是4 length="3" ，目前只支持3级
	@Column(length =1)private String aa_st_active;//是否可用，0=不可用 1=可用length="1"
	@Column(length =500)private String aa_st_sysmark;//系统标识，32*10+9 最多支持10级节点。用户具体一批数据的控制
	@Column(length =200)private String aa_st_image;//图标设置
	@Column(length =1)private String aa_st_islog;//是否记录日志 
	@Column(length =4)private int aa_nm_orderno;//在同一级节点中的序号length="4"
	@Column(length =4)private int aa_nm_jdnum;//在所有节点中的级号length="4"
	@Column(length =4)private int aa_nm_openorclose;//节点是否打开或者关闭0 关闭，1打开。
	@Column(length =500)private String aa_st_remark;//备注，备用字段
	@Column(length =32)private String aa_st_addUserId;//创建人员ID  
	private Date aa_dt_addDate;//创建时间
	@Column(length =32)private String aa_st_updUserId;//修改人员ID 
	private Date aa_dt_updDate;//修改时间
	/**
	 * 构造函数
	 */
	public Aa_t_sysfunction() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Aa_t_sysfunction(String aa_st_id) {
		this.aa_st_id = aa_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Aa_t_sysfunction(boolean isAutoId) {
		if(isAutoId){
			this.aa_st_id =StringUtils.getUUID32();
		}
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("aa_st_id", getAa_st_id()).toString();
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Aa_t_sysfunction)) {
			return false;
		}
		final Aa_t_sysfunction castOther = (Aa_t_sysfunction) other;
		return new EqualsBuilder().append(getAa_st_id(),castOther.getAa_st_id()).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getAa_st_id()).toHashCode();
	}
	public String getAa_st_id() {
		return aa_st_id;
	}
	public void setAa_st_id(String aaStId) {
		aa_st_id = aaStId;
	}
	public String getAa_st_title() {
		return aa_st_title;
	}
	public void setAa_st_title(String aaStTitle) {
		aa_st_title = aaStTitle;
	}
	public String getAa_st_islog() {
		return aa_st_islog;
	}
	public void setAa_st_islog(String aaStIslog) {
		aa_st_islog = aaStIslog;
	}
	public String getAa_st_parent() {
		return aa_st_parent;
	}
	public void setAa_st_parent(String aaStParent) {
		aa_st_parent = aaStParent;
	}
	public String getAa_st_mark() {
		return aa_st_mark;
	}
	public void setAa_st_mark(String aaStMark) {
		aa_st_mark = aaStMark;
	}
	public String getAa_st_classUrl() {
		return aa_st_classUrl;
	}
	public void setAa_st_classUrl(String aaStClassUrl) {
		aa_st_classUrl = aaStClassUrl;
	}
	public String getAa_st_url() {
		return aa_st_url;
	}
	public void setAa_st_url(String aaStUrl) {
		aa_st_url = aaStUrl;
	}
	public String getAa_st_describe() {
		return aa_st_describe;
	}
	public void setAa_st_describe(String aaStDescribe) {
		aa_st_describe = aaStDescribe;
	}
	public String getAa_st_type() {
		return aa_st_type;
	}
	public void setAa_st_type(String aaStType) {
		aa_st_type = aaStType;
	}
	public String getAa_st_active() {
		return aa_st_active;
	}
	public void setAa_st_active(String aaStActive) {
		aa_st_active = aaStActive;
	}
	public String getAa_st_sysmark() {
		return aa_st_sysmark;
	}
	public void setAa_st_sysmark(String aaStSysmark) {
		aa_st_sysmark = aaStSysmark;
	}
	public String getAa_st_image() {
		return aa_st_image;
	}
	public void setAa_st_image(String aaStImage) {
		aa_st_image = aaStImage;
	}
	public int getAa_nm_orderno() {
		return aa_nm_orderno;
	}
	public void setAa_nm_orderno(int aaNmOrderno) {
		aa_nm_orderno = aaNmOrderno;
	}
	public int getAa_nm_jdnum() {
		return aa_nm_jdnum;
	}
	public void setAa_nm_jdnum(int aaNmJdnum) {
		aa_nm_jdnum = aaNmJdnum;
	}
	public int getAa_nm_openorclose() {
		return aa_nm_openorclose;
	}
	public void setAa_nm_openorclose(int aaNmOpenorclose) {
		aa_nm_openorclose = aaNmOpenorclose;
	}
	public String getAa_st_remark() {
		return aa_st_remark;
	}
	public void setAa_st_remark(String aaStRemark) {
		aa_st_remark = aaStRemark;
	}
	public String getAa_st_addUserId() {
		return aa_st_addUserId;
	}
	public void setAa_st_addUserId(String aaStAddUserId) {
		aa_st_addUserId = aaStAddUserId;
	}
	public Date getAa_dt_addDate() {
		return aa_dt_addDate;
	}
	public void setAa_dt_addDate(Date aaDtAddDate) {
		aa_dt_addDate = aaDtAddDate;
	}
	public String getAa_st_updUserId() {
		return aa_st_updUserId;
	}
	public void setAa_st_updUserId(String aaStUpdUserId) {
		aa_st_updUserId = aaStUpdUserId;
	}
	public Date getAa_dt_updDate() {
		return aa_dt_updDate;
	}
	public void setAa_dt_updDate(Date aaDtUpdDate) {
		aa_dt_updDate = aaDtUpdDate;
	}
	
}
