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
 * 日志记录表--系统记录
 * @author shuang
 */

@Entity
@Table(name = "ah_t_log")
@MyTable(tableName = "ah_t_log",pkName="ah_st_id",anotherName="ah")
public class Ah_t_log implements  Serializable {
	private static final long serialVersionUID = 1000000108L;
	@Id
	@Column(length =32)private String ah_st_id;//ID  
	@Column(length =2,nullable=false)private String ah_st_type;//日志类型。1.登录2.业务操作3.注销
	@Column(length =200)private String ah_st_className;//操作模版。SysfunctionAction.initSysfunction
	@Column(length =1000)private String ah_st_context;//日志内容。
	@Column(length =20)private String ah_st_ip;//操作人IP。
	@Column(length =500)private String ah_st_remark;//备注，备用字段
	@Column(length =32)private String ah_st_addUserId;//操作人员ID  
	private Date ah_dt_addDate;//操作时间
	@Column(length =32)private String ah_st_updUserId;//修改人员ID 
	private Date ah_dt_updDate;//修改时间
	/**
	 * 构造函数
	 */
	public Ah_t_log() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Ah_t_log(String ah_st_id) {
		this.ah_st_id = ah_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Ah_t_log(boolean isAutoId) {
		if(isAutoId){
			this.ah_st_id =StringUtils.getUUID32();
		}
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("ah_st_id", getAh_st_id()).toString();
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Ah_t_log)) {
			return false;
		}
		final Ah_t_log castOther = (Ah_t_log) other;
		return new EqualsBuilder().append(getAh_st_id(),castOther.getAh_st_id()).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getAh_st_id()).toHashCode();
	}
	public String getAh_st_id() {
		return ah_st_id;
	}
	public void setAh_st_id(String ahStId) {
		ah_st_id = ahStId;
	}
	public String getAh_st_type() {
		return ah_st_type;
	}
	public void setAh_st_type(String ahStType) {
		ah_st_type = ahStType;
	}
	public String getAh_st_className() {
		return ah_st_className;
	}
	public void setAh_st_className(String ahStClassName) {
		ah_st_className = ahStClassName;
	}
	public String getAh_st_context() {
		return ah_st_context;
	}
	public void setAh_st_context(String ahStContext) {
		ah_st_context = ahStContext;
	}
	public String getAh_st_ip() {
		return ah_st_ip;
	}
	public void setAh_st_ip(String ahStIp) {
		ah_st_ip = ahStIp;
	}
	public String getAh_st_remark() {
		return ah_st_remark;
	}
	public void setAh_st_remark(String ahStRemark) {
		ah_st_remark = ahStRemark;
	}
	public String getAh_st_addUserId() {
		return ah_st_addUserId;
	}
	public void setAh_st_addUserId(String ahStAddUserId) {
		ah_st_addUserId = ahStAddUserId;
	}
	public Date getAh_dt_addDate() {
		return ah_dt_addDate;
	}
	public void setAh_dt_addDate(Date ahDtAddDate) {
		ah_dt_addDate = ahDtAddDate;
	}
	public String getAh_st_updUserId() {
		return ah_st_updUserId;
	}
	public void setAh_st_updUserId(String ahStUpdUserId) {
		ah_st_updUserId = ahStUpdUserId;
	}
	public Date getAh_dt_updDate() {
		return ah_dt_updDate;
	}
	public void setAh_dt_updDate(Date ahDtUpdDate) {
		ah_dt_updDate = ahDtUpdDate;
	}
	 
}
