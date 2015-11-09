package com.wyyf.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;
/**
 * 评价表
 * @author Administrator
 */
@Entity
@Table(name = "be_t_assess")
@MyTable(tableName = "be_t_assess",pkName="be_st_id",anotherName="be")
public class Be_t_Assess {
	@Id
	@Column(length=32)private String be_st_id;//主键ID
	@Column(length=32)private String be_st_fbgid;// 评价对象 ae【FK】--评价人
	@Column(length=32)private String be_st_jbgid;// 被评价对象 ae【FK】--被评价
	@Column(length=32)private String be_st_bgid;// 商品评价对象 bg【FK】--被评价
	@Column(length=32)private String be_st_bfid;//被评价的申请单ID
	@Column(length=50)private String be_st_content;//评价内容
	@Column(length=10)private int be_nm_manner;//服务态度星级
	@Column(length=10)private int be_nm_integrity;//服务诚信星级
	@Column(length=10)private int be_nm_quality;//服务质量星级
	@Column(length=10)private int be_nm_shopquality;//商品星级
	@Column(length =500)private String be_st_remark;//备注，备用字段
	@Column(length =32)private String be_st_addUserId;//创建人员ID  
	private Date be_dt_addDate;//创建时间
	@Column(length =32)private String be_st_updUserId;//修改人员ID 
	@Column(length=32)private int pjbs;// 评价标示
	public int getPjbs() {
		return pjbs;
	}
	public void setPjbs(int pjbs) {
		this.pjbs = pjbs;
	}
	/**
	 * 构造函数
	 */
	public Be_t_Assess() {}
	/**
	 * 自定义主键ID
	 * @param ae_st_id 主键ID
	 */
	public Be_t_Assess(String be_st_id) {
		this.be_st_id = be_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Be_t_Assess(boolean isAutoId) {
		if(isAutoId){
			this.be_st_id =StringUtils.getUUID32();
		}
	}
	public String getBe_st_id() {
		return be_st_id;
	}
	public void setBe_st_id(String be_st_id) {
		this.be_st_id = be_st_id;
	}
	public String getBe_st_fbgid() {
		return be_st_fbgid;
	}
	public void setBe_st_fbgid(String be_st_fbgid) {
		this.be_st_fbgid = be_st_fbgid;
	}
	public String getBe_st_jbgid() {
		return be_st_jbgid;
	}
	public void setBe_st_jbgid(String be_st_jbgid) {
		this.be_st_jbgid = be_st_jbgid;
	}
	public String getBe_st_content() {
		return be_st_content;
	}
	public void setBe_st_content(String be_st_content) {
		this.be_st_content = be_st_content;
	}
	public int getBe_nm_manner() {
		return be_nm_manner;
	}
	public void setBe_nm_manner(int be_nm_manner) {
		this.be_nm_manner = be_nm_manner;
	}
	public int getBe_nm_integrity() {
		return be_nm_integrity;
	}
	public void setBe_nm_integrity(int be_nm_integrity) {
		this.be_nm_integrity = be_nm_integrity;
	}
	public int getBe_nm_quality() {
		return be_nm_quality;
	}
	public void setBe_nm_quality(int be_nm_quality) {
		this.be_nm_quality = be_nm_quality;
	}
	public String getBe_st_remark() {
		return be_st_remark;
	}
	public void setBe_st_remark(String be_st_remark) {
		this.be_st_remark = be_st_remark;
	}
	public String getBe_st_addUserId() {
		return be_st_addUserId;
	}
	public void setBe_st_addUserId(String be_st_addUserId) {
		this.be_st_addUserId = be_st_addUserId;
	}
	public Date getBe_dt_addDate() {
		return be_dt_addDate;
	}
	public void setBe_dt_addDate(Date be_dt_addDate) {
		this.be_dt_addDate = be_dt_addDate;
	}
	public String getBe_st_updUserId() {
		return be_st_updUserId;
	}
	public void setBe_st_updUserId(String be_st_updUserId) {
		this.be_st_updUserId = be_st_updUserId;
	}
	public int getBe_nm_shopquality() {
		return be_nm_shopquality;
	}
	public void setBe_nm_shopquality(int be_nm_shopquality) {
		this.be_nm_shopquality = be_nm_shopquality;
	}
	public String getBe_st_bgid() {
		return be_st_bgid;
	}
	public void setBe_st_bgid(String be_st_bgid) {
		this.be_st_bgid = be_st_bgid;
	}
	public String getBe_st_bfid() {
		return be_st_bfid;
	}
	public void setBe_st_bfid(String be_st_bfid) {
		this.be_st_bfid = be_st_bfid;
	}
	 
}
