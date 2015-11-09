package com.wyyf.bean;


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
 * 需求收集表--来自微信、PC、其他设备提交上来的 需求支持内容存储
 * @author shuang
 */
@Entity
@Table(name = "bk_t_helpcollect")
@MyTable(tableName = "bk_t_helpcollect",pkName="bk_st_id",anotherName="bk")
public class Bk_t_HelpsCollect implements Serializable {
	private static final long serialVersionUID = 1000000211L;
	@Id
	@Column(length =32)private String  bk_st_id;//ID  
	@Column(length =2)private String bk_st_type;//类型 1.意见2.其他
	@Column(length =40)private String bk_st_title;//标题
	@Column(length =1000)private String bk_st_content;//内容
	@Column(length =30)private String bk_st_phone;//客户联系电话
	@Column(length =1)private String bk_st_stutas;//状态 1.打开 0 关闭  
	@Column(length =1)private String bk_st_source;//来源 1.APP 2.PC端
	@Column(length =1)private String bk_st_isdel;//是否删除  0否 1 是
	@Column(length =500)private String bk_st_remark;//备注，备用字段
	@Column(length =32)private String bk_st_addUserId;//创建人员ID  
	private Date bk_dt_addDate;//创建时间
	@Column(length =32)private String bk_st_updUserId;//修改人员ID 
	private Date bk_dt_updDate;//修改时间
	
	/**
	 * 构造函数
	 */
	public Bk_t_HelpsCollect() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Bk_t_HelpsCollect(String bk_st_id) {
		this.bk_st_id = bk_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bk_t_HelpsCollect(boolean isAutoId) {
		if(isAutoId){
			this.bk_st_id =StringUtils.getUUID32();
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("bk_st_id", getBk_st_id()).toString();
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Bk_t_HelpsCollect)) {
			return false;
		}
		final Bk_t_HelpsCollect castOther = (Bk_t_HelpsCollect) other;
		return new EqualsBuilder().append(getBk_st_id(), castOther.getBk_st_id()).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getBk_st_id()).toHashCode();
	}
	public String getBk_st_id() {
		return bk_st_id;
	}
	public void setBk_st_id(String bkStId) {
		bk_st_id = bkStId;
	}
	public String getBk_st_type() {
		return bk_st_type;
	}
	public void setBk_st_type(String bkStType) {
		bk_st_type = bkStType;
	}
	public String getBk_st_title() {
		return bk_st_title;
	}
	public void setBk_st_title(String bkStTitle) {
		bk_st_title = bkStTitle;
	}
	public String getBk_st_content() {
		return bk_st_content;
	}
	public void setBk_st_content(String bkStContent) {
		bk_st_content = bkStContent;
	}
	public String getBk_st_phone() {
		return bk_st_phone;
	}
	public void setBk_st_phone(String bkStPhone) {
		bk_st_phone = bkStPhone;
	}
	public String getBk_st_stutas() {
		return bk_st_stutas;
	}
	public void setBk_st_stutas(String bkStStutas) {
		bk_st_stutas = bkStStutas;
	}
	public String getBk_st_source() {
		return bk_st_source;
	}
	public void setBk_st_source(String bkStSource) {
		bk_st_source = bkStSource;
	}
	public String getBk_st_isdel() {
		return bk_st_isdel;
	}
	public void setBk_st_isdel(String bkStIsdel) {
		bk_st_isdel = bkStIsdel;
	}
	public String getBk_st_remark() {
		return bk_st_remark;
	}
	public void setBk_st_remark(String bkStRemark) {
		bk_st_remark = bkStRemark;
	}
	public String getBk_st_addUserId() {
		return bk_st_addUserId;
	}
	public void setBk_st_addUserId(String bkStAddUserId) {
		bk_st_addUserId = bkStAddUserId;
	}
	public Date getBk_dt_addDate() {
		return bk_dt_addDate;
	}
	public void setBk_dt_addDate(Date bkDtAddDate) {
		bk_dt_addDate = bkDtAddDate;
	}
	public String getBk_st_updUserId() {
		return bk_st_updUserId;
	}
	public void setBk_st_updUserId(String bkStUpdUserId) {
		bk_st_updUserId = bkStUpdUserId;
	}
	public Date getBk_dt_updDate() {
		return bk_dt_updDate;
	}
	public void setBk_dt_updDate(Date bkDtUpdDate) {
		bk_dt_updDate = bkDtUpdDate;
	}
	 
	 
}
