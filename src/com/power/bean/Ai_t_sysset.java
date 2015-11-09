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
 * 系统设置表--用于存储系统不容易修改的参数等
 * @author shuang
 */
@Entity
@Table(name = "ai_t_sysset")
@MyTable(tableName = "ai_t_sysset",pkName="ai_st_id",anotherName="ai")
public class Ai_t_sysset implements Serializable {
	private static final long serialVersionUID = 1000000109L;
	@Id
	@Column(length =32)private String  ai_st_id;//ID  uuid.hex length="32"
	@Column(length =500)private String ai_st_content;//设置的内容
	@Column(length =50)private String ai_st_type;//类型
	@Column(length =1)private String ai_st_isEnable;//是否启用
	@Column(length =500)private String ai_st_remark;//备注，备用字段
	@Column(length =32)private String ai_st_addUserId;//创建人员ID  
	private Date ai_dt_addDate;//创建时间
	@Column(length =32)private String ai_st_updUserId;//修改人员ID 
	private Date ai_dt_updDate;//修改时间
	/**
	 * 构造函数
	 */
	public Ai_t_sysset() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Ai_t_sysset(String ai_st_id) {
		this.ai_st_id = ai_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Ai_t_sysset(boolean isAutoId) {
		if(isAutoId){
			this.ai_st_id =StringUtils.getUUID32();
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("ai_st_id", getAi_st_id()).toString();
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Ai_t_sysset)) {
			return false;
		}
		final Ai_t_sysset castOther = (Ai_t_sysset) other;
		return new EqualsBuilder().append(getAi_st_id(), castOther.getAi_st_id()).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getAi_st_id()).toHashCode();
	}
	public String getAi_st_id() {
		return ai_st_id;
	}
	public void setAi_st_id(String aiStId) {
		ai_st_id = aiStId;
	}
	public String getAi_st_content() {
		return ai_st_content;
	}
	public void setAi_st_content(String aiStContent) {
		ai_st_content = aiStContent;
	}
	public String getAi_st_type() {
		return ai_st_type;
	}
	public void setAi_st_type(String aiStType) {
		ai_st_type = aiStType;
	}
	public String getAi_st_isEnable() {
		return ai_st_isEnable;
	}
	public void setAi_st_isEnable(String aiStIsEnable) {
		ai_st_isEnable = aiStIsEnable;
	}
	public String getAi_st_remark() {
		return ai_st_remark;
	}
	public void setAi_st_remark(String aiStRemark) {
		ai_st_remark = aiStRemark;
	}
	public String getAi_st_addUserId() {
		return ai_st_addUserId;
	}
	public void setAi_st_addUserId(String aiStAddUserId) {
		ai_st_addUserId = aiStAddUserId;
	}
	public Date getAi_dt_addDate() {
		return ai_dt_addDate;
	}
	public void setAi_dt_addDate(Date aiDtAddDate) {
		ai_dt_addDate = aiDtAddDate;
	}
	public String getAi_st_updUserId() {
		return ai_st_updUserId;
	}
	public void setAi_st_updUserId(String aiStUpdUserId) {
		ai_st_updUserId = aiStUpdUserId;
	}
	public Date getAi_dt_updDate() {
		return ai_dt_updDate;
	}
	public void setAi_dt_updDate(Date aiDtUpdDate) {
		ai_dt_updDate = aiDtUpdDate;
	}
	
}
