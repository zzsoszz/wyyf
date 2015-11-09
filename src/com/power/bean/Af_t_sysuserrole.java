package com.power.bean;


import java.io.Serializable;

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
 * 用户角色关系表
 * @author shuang
 */
@Entity
@Table(name = "af_t_sysuserrole")
@MyTable(tableName = "af_t_sysuserrole",pkName="af_st_id",anotherName="af")
public class Af_t_sysuserrole implements Serializable {
	private static final long serialVersionUID = 1000000106L;
	@Id
	@Column(length =32)private String af_st_id;//uuid.hex length="32"
	@Column(length=32,nullable=false)private String ae_st_id;//用户ID length="32"【PK】
	@Column(length=32,nullable=false)private String ac_st_id;//角色ID length="32"【PK】
	/**
	 * 构造函数
	 */
	public Af_t_sysuserrole() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Af_t_sysuserrole(String af_st_id) {
		this.af_st_id = af_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Af_t_sysuserrole(boolean isAutoId) {
		if(isAutoId){
			this.af_st_id =StringUtils.getUUID32();
		}
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("af_st_id", getAf_st_id()).toString();
	}
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Af_t_sysuserrole))
			return false;
		Af_t_sysuserrole castOther = (Af_t_sysuserrole) other;
		return new EqualsBuilder().append(this.getAf_st_id(),castOther.getAf_st_id()).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getAf_st_id()).toHashCode();
	}
	public String getAf_st_id() {
		return af_st_id;
	}
	public void setAf_st_id(String afStId) {
		af_st_id = afStId;
	}
	public String getAe_st_id() {
		return ae_st_id;
	}
	public void setAe_st_id(String aeStId) {
		ae_st_id = aeStId;
	}
	public String getAc_st_id() {
		return ac_st_id;
	}
	public void setAc_st_id(String acStId) {
		ac_st_id = acStId;
	}
	 
}
