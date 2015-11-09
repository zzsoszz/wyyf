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
 * 码表
 * @author shuang
 */

@Entity
@Table(name = "ab_t_code")
@MyTable(tableName = "ab_t_code",pkName="ab_st_id",anotherName="ab")
public class Ab_t_code implements  Serializable {
	private static final long serialVersionUID = 1000000102L;
	@Id
	@Column(length =32)private String ab_st_id;//码表ID  uuid.hex length="32"
	@Column(length =32,nullable=false,unique=true)private String ab_st_mark;//码值唯一标识, 例如，SEX、SEX_1、SEX_2
	@Column(length =100)private String ab_st_value;//码值 的数字表示，例如：1，2，3。。。。。、sex
	@Column(length =100)private String ab_st_name;//码值的中文表示， 例如：是、否      、性别
	@Column(length =1)private String ab_st_type;//码值类型， 例如 ：1 ，代表系统码， 2 代表业务码，3代表公用码,0代表根节点4其他
	@Column(length =256)private String ab_st_describe;//码表的中文描述length="256"
	@Column(length =32)private String ab_st_parent;//节点的父节点length="32"
	@Column(length =4)private int ab_nm_orderno;//在同一级节点中的序号length="4"
	@Column(length =4)private int ab_nm_jdnum;//在所有节点中的级号length="4"
	@Column(length =4)private int ab_nm_openorclose;//节点是否打开或者关闭0 关闭，1打开。
	@Column(length =500)private String ab_st_sysmark;//系统标识，32*10+9 最多支持10级节点。用户具体一批数据的控制
	@Column(length =500)private String ab_st_remark;//备注，备用字段
	@Column(length =32)private String ab_st_addUserId;//创建人员ID  
	private Date ab_dt_addDate;//创建时间
	@Column(length =32)private String ab_st_updUserId;//修改人员ID 
	private Date ab_dt_updDate;//修改时间
	
	@Column(length =1)private String ab_st_isuserset;//设置是否允许用户设置  1:允许  0:不允许
	/**
	 * 构造函数
	 */
	public Ab_t_code() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Ab_t_code(String ab_st_id) {
		this.ab_st_id = ab_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Ab_t_code(boolean isAutoId) {
		if(isAutoId){
			this.ab_st_id =StringUtils.getUUID32();
		}
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("ab_st_id", getAb_st_id()).toString();
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Ab_t_code)) {
			return false;
		}
		final Ab_t_code castOther = (Ab_t_code) other;
		return new EqualsBuilder().append(getAb_st_id(),castOther.getAb_st_id()).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getAb_st_id()).toHashCode();
	}
	public String getAb_st_isuserset() {
		return ab_st_isuserset;
	}
	public void setAb_st_isuserset(String abStIsuserset) {
		ab_st_isuserset = abStIsuserset;
	}
	public String getAb_st_id() {
		return ab_st_id;
	}
	public void setAb_st_id(String abStId) {
		ab_st_id = abStId;
	}
	public String getAb_st_mark() {
		return ab_st_mark;
	}
	public void setAb_st_mark(String abStMark) {
		ab_st_mark = abStMark;
	}
	public String getAb_st_value() {
		return ab_st_value;
	}
	public void setAb_st_value(String abStValue) {
		ab_st_value = abStValue;
	}
	public String getAb_st_name() {
		return ab_st_name;
	}
	public void setAb_st_name(String abStName) {
		ab_st_name = abStName;
	}
	public String getAb_st_type() {
		return ab_st_type;
	}
	public void setAb_st_type(String abStType) {
		ab_st_type = abStType;
	}
	public String getAb_st_describe() {
		return ab_st_describe;
	}
	public void setAb_st_describe(String abStDescribe) {
		ab_st_describe = abStDescribe;
	}
	public String getAb_st_parent() {
		return ab_st_parent;
	}
	public void setAb_st_parent(String abStParent) {
		ab_st_parent = abStParent;
	}
	public int getAb_nm_orderno() {
		return ab_nm_orderno;
	}
	public void setAb_nm_orderno(int abNmOrderno) {
		ab_nm_orderno = abNmOrderno;
	}
	public int getAb_nm_jdnum() {
		return ab_nm_jdnum;
	}
	public void setAb_nm_jdnum(int abNmJdnum) {
		ab_nm_jdnum = abNmJdnum;
	}
	public int getAb_nm_openorclose() {
		return ab_nm_openorclose;
	}
	public void setAb_nm_openorclose(int abNmOpenorclose) {
		ab_nm_openorclose = abNmOpenorclose;
	}
	public String getAb_st_sysmark() {
		return ab_st_sysmark;
	}
	public void setAb_st_sysmark(String abStSysmark) {
		ab_st_sysmark = abStSysmark;
	}
	public String getAb_st_remark() {
		return ab_st_remark;
	}
	public void setAb_st_remark(String abStRemark) {
		ab_st_remark = abStRemark;
	}
	public String getAb_st_addUserId() {
		return ab_st_addUserId;
	}
	public void setAb_st_addUserId(String abStAddUserId) {
		ab_st_addUserId = abStAddUserId;
	}
	public Date getAb_dt_addDate() {
		return ab_dt_addDate;
	}
	public void setAb_dt_addDate(Date abDtAddDate) {
		ab_dt_addDate = abDtAddDate;
	}
	public String getAb_st_updUserId() {
		return ab_st_updUserId;
	}
	public void setAb_st_updUserId(String abStUpdUserId) {
		ab_st_updUserId = abStUpdUserId;
	}
	public Date getAb_dt_updDate() {
		return ab_dt_updDate;
	}
	public void setAb_dt_updDate(Date abDtUpdDate) {
		ab_dt_updDate = abDtUpdDate;
	}
	
}
