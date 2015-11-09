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
 * 文件表--存储所有文件的表
 * @author shuang
 */

@Entity
@Table(name = "ag_t_file")
@MyTable(tableName = "ag_t_file",pkName="ag_st_id",anotherName="ag")
public class Ag_t_file implements  Serializable {
	private static final long serialVersionUID = 1000000107L;
	@Id
	@Column(length =32)private String ag_st_id;//文件表ID  uuid.hex length="32"
	@Column(length =32,nullable=false)private String ag_st_objid;//文件归属ID, 例如(项目的文件、人员的头像等所有对应的主表ID等等)*【PK】
	@Column(length=50)private String ag_st_objtype;//对应的对象（存储各种类型用户表,表名）    
	@Column(length =10,nullable=false)private String ag_st_mark;//自定义文件标识，用于区分文件分类*
	@Column(length =4)private int ag_nm_orderno;//序号length="4"*
	@Column(length =100)private String ag_st_url;//文件 的存储地址*
	@Column(length =1)private String ag_st_type;//文件 类型,1.图片2.文档3.视频4.音乐5.其他*
	@Column(length =50)private String ag_st_source;//文件来源 表名，来源哪张表
	@Column(length =100)private String ag_st_format;//文件格式，例如：jpg,png,tmp,exe,无,txt,doc....*
	@Column(length =100)private String ag_st_title;//文件标题
	@Column(length =100)private String ag_st_summary;//文件摘要
	@Column(length =400)private String ag_st_clickurl;//文件点击链接（主要针对图片）
	@Column(length =10)private int ag_nm_height;//文件属性--高 px（如果是图片）
	@Column(length =10)private int ag_nm_width;//文件属性--宽px（如果是图片）
	@Column(length =20)private long ag_nm_size;//文件属性--大小 kb*
	@Column(length =512)private String ag_st_describe;//文件的中文描述length="256"
	@Column(length =500)private String ag_st_remark;//备注，备用字段
	@Column(length =32)private String ag_st_addUserId;//创建人员ID  
	private Date ag_dt_addDate;//创建时间
	@Column(length =32)private String ag_st_updUserId;//修改人员ID 
	private Date ag_dt_updDate;//修改时间
	/**
	 * 构造函数
	 */
	public Ag_t_file() {}
	/**
	 * 自定义主键ID
	 * @param userid 主键ID
	 */
	public Ag_t_file(String ag_st_id) {
		this.ag_st_id = ag_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Ag_t_file(boolean isAutoId) {
		if(isAutoId){
			this.ag_st_id =StringUtils.getUUID32();
		}
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("ag_st_id", getAg_st_id()).toString();
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Ag_t_file)) {
			return false;
		}
		final Ag_t_file castOther = (Ag_t_file) other;
		return new EqualsBuilder().append(getAg_st_id(),castOther.getAg_st_id()).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getAg_st_id()).toHashCode();
	}
	public String getAg_st_id() {
		return ag_st_id;
	}
	public void setAg_st_id(String agStId) {
		ag_st_id = agStId;
	}
	public String getAg_st_objid() {
		return ag_st_objid;
	}
	public void setAg_st_objid(String agStObjid) {
		ag_st_objid = agStObjid;
	}
	public String getAg_st_objtype() {
		return ag_st_objtype;
	}
	public void setAg_st_objtype(String agStObjtype) {
		ag_st_objtype = agStObjtype;
	}
	public String getAg_st_mark() {
		return ag_st_mark;
	}
	public void setAg_st_mark(String agStMark) {
		ag_st_mark = agStMark;
	}
	public int getAg_nm_orderno() {
		return ag_nm_orderno;
	}
	public void setAg_nm_orderno(int agNmOrderno) {
		ag_nm_orderno = agNmOrderno;
	}
	public String getAg_st_url() {
		return ag_st_url;
	}
	public void setAg_st_url(String agStUrl) {
		ag_st_url = agStUrl;
	}
	public String getAg_st_type() {
		return ag_st_type;
	}
	public void setAg_st_type(String agStType) {
		ag_st_type = agStType;
	}
	public String getAg_st_source() {
		return ag_st_source;
	}
	public void setAg_st_source(String agStSource) {
		ag_st_source = agStSource;
	}
	public String getAg_st_format() {
		return ag_st_format;
	}
	public void setAg_st_format(String agStFormat) {
		ag_st_format = agStFormat;
	}
	public String getAg_st_title() {
		return ag_st_title;
	}
	public void setAg_st_title(String agStTitle) {
		ag_st_title = agStTitle;
	}
	public int getAg_nm_height() {
		return ag_nm_height;
	}
	public void setAg_nm_height(int agNmHeight) {
		ag_nm_height = agNmHeight;
	}
	public int getAg_nm_width() {
		return ag_nm_width;
	}
	public void setAg_nm_width(int agNmWidth) {
		ag_nm_width = agNmWidth;
	}
 
	public String getAg_st_describe() {
		return ag_st_describe;
	}
	public void setAg_st_describe(String agStDescribe) {
		ag_st_describe = agStDescribe;
	}
	public String getAg_st_remark() {
		return ag_st_remark;
	}
	public void setAg_st_remark(String agStRemark) {
		ag_st_remark = agStRemark;
	}
	public String getAg_st_addUserId() {
		return ag_st_addUserId;
	}
	public void setAg_st_addUserId(String agStAddUserId) {
		ag_st_addUserId = agStAddUserId;
	}
	public Date getAg_dt_addDate() {
		return ag_dt_addDate;
	}
	public void setAg_dt_addDate(Date agDtAddDate) {
		ag_dt_addDate = agDtAddDate;
	}
	public String getAg_st_updUserId() {
		return ag_st_updUserId;
	}
	public void setAg_st_updUserId(String agStUpdUserId) {
		ag_st_updUserId = agStUpdUserId;
	}
	public Date getAg_dt_updDate() {
		return ag_dt_updDate;
	}
	public void setAg_dt_updDate(Date agDtUpdDate) {
		ag_dt_updDate = agDtUpdDate;
	}
	public String getAg_st_summary() {
		return ag_st_summary;
	}
	public void setAg_st_summary(String agStSummary) {
		ag_st_summary = agStSummary;
	}
	public String getAg_st_clickurl() {
		return ag_st_clickurl;
	}
	public void setAg_st_clickurl(String agStClickurl) {
		ag_st_clickurl = agStClickurl;
	}
	public long getAg_nm_size() {
		return ag_nm_size;
	}
	public void setAg_nm_size(long agNmSize) {
		ag_nm_size = agNmSize;
	}
	
	
}
