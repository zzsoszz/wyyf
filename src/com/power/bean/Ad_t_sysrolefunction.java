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
 * 角色权限关系表
 * @author shuang
 */
@Entity
@Table(name = "ad_t_sysrolefunction")
@MyTable(tableName = "ad_t_sysrolefunction",pkName="ad_st_id",anotherName="ad")
public class Ad_t_sysrolefunction implements Serializable {
	private static final long serialVersionUID = 1000000104L;
	@Id
	@Column(length =32)private String ad_st_id;//ID   uuid.hex  length="32"
	@Column(length =32,nullable=false) private String ac_st_id;//角色ID   length="32"【PK】
	@Column(length =32,nullable=false)private String aa_st_id;//权限ID  length="32".【PK】
	/**
	 * 构造函数
	 */
	public Ad_t_sysrolefunction() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Ad_t_sysrolefunction(String ad_st_id) {
		this.ad_st_id = ad_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Ad_t_sysrolefunction(boolean isAutoId) {
		if(isAutoId){
			this.ad_st_id =StringUtils.getUUID32();
		}
	}
    @Override
	public String toString() {
        return new ToStringBuilder(this).append("ad_st_id", getAd_st_id()).toString();
    }
    @Override
	public boolean equals(Object other) {
        if ( !(other instanceof Ad_t_sysrolefunction) ) return false;
        Ad_t_sysrolefunction castOther = (Ad_t_sysrolefunction) other;
        return new EqualsBuilder().append(this.getAd_st_id(), castOther.getAd_st_id()).isEquals();
    }
    @Override
	public int hashCode() {
        return new HashCodeBuilder().append(getAd_st_id()).toHashCode();
    }
	public String getAd_st_id() {
		return ad_st_id;
	}
	public void setAd_st_id(String adStId) {
		ad_st_id = adStId;
	}
	public String getAc_st_id() {
		return ac_st_id;
	}
	public void setAc_st_id(String acStId) {
		ac_st_id = acStId;
	}
	public String getAa_st_id() {
		return aa_st_id;
	}
	public void setAa_st_id(String aaStId) {
		aa_st_id = aaStId;
	}
     
}

