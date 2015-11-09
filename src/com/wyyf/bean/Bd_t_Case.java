package com.wyyf.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;
/**
 * 师傅的案例表。
 * @author Administrator
 */

@Entity
@Table(name = "bd_t_case")
@MyTable(tableName = "bd_t_case",pkName="bd_st_id",anotherName="bd")
public class Bd_t_Case {
//	@Transient private List<Ag_t_file> fileList;//案例图片List
	@Id
	@Column(length=32)private String bd_st_id;//主键ID
	@Column(length=32)private String bd_st_bbid;//Ba_t_Labour 【FK】师傅表外键，每个案例对应一个师傅
	@Column(length=50)private String bd_st_name;//案例名称--标题
	@Column(length=50)private String bd_st_area;//面积 单位米
	private Date bd_dt_time;//案例发生时间 
	@Column(length=10)private int bd_nm_orderno;//序号
	@Column(length=50)private String bd_st_money;//装修总费用
	@Column(length=1)private String bd_st_isfb;//案例是否发布
	@Column(length =500)private String bd_st_remark;//备注，备用字段
	@Column(length =32)private String bd_st_addUserId;//创建人员ID  
	private Date bd_dt_addDate;//创建时间
	@Column(length =32)private String bd_st_updUserId;//修改人员ID 
	private Date bd_dt_updDate;//更新时间
	/**
	 * 构造函数
	 */
	public Bd_t_Case() {}
	/**
	 * 自定义主键ID
	 * @param ae_st_id 主键ID
	 */
	public Bd_t_Case(String bd_st_id) {
		this.bd_st_id = bd_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bd_t_Case(boolean isAutoId) {
		if(isAutoId){
			this.bd_st_id =StringUtils.getUUID32();
		}
	}
	public String getBd_st_id() {
		return bd_st_id;
	}
	public void setBd_st_id(String bd_st_id) {
		this.bd_st_id = bd_st_id;
	}
	public String getBd_st_bbid() {
		return bd_st_bbid;
	}
	public void setBd_st_bbid(String bd_st_bbid) {
		this.bd_st_bbid = bd_st_bbid;
	}
	public String getBd_st_name() {
		return bd_st_name;
	}
	public void setBd_st_name(String bd_st_name) {
		this.bd_st_name = bd_st_name;
	}
	public String getBd_st_area() {
		return bd_st_area;
	}
	public void setBd_st_area(String bd_st_area) {
		this.bd_st_area = bd_st_area;
	}
	public Date getBd_dt_time() {
		return bd_dt_time;
	}
	public void setBd_dt_time(Date bd_dt_time) {
		this.bd_dt_time = bd_dt_time;
	}
	public String getBd_st_money() {
		return bd_st_money;
	}
	public void setBd_st_money(String bd_st_money) {
		this.bd_st_money = bd_st_money;
	}
	public String getBd_st_remark() {
		return bd_st_remark;
	}
	public void setBd_st_remark(String bd_st_remark) {
		this.bd_st_remark = bd_st_remark;
	}
	public String getBd_st_addUserId() {
		return bd_st_addUserId;
	}
	public void setBd_st_addUserId(String bd_st_addUserId) {
		this.bd_st_addUserId = bd_st_addUserId;
	}
	public Date getBd_dt_addDate() {
		return bd_dt_addDate;
	}
	public void setBd_dt_addDate(Date bd_dt_addDate) {
		this.bd_dt_addDate = bd_dt_addDate;
	}
	public String getBd_st_updUserId() {
		return bd_st_updUserId;
	}
	public void setBd_st_updUserId(String bd_st_updUserId) {
		this.bd_st_updUserId = bd_st_updUserId;
	}
	public String getBd_st_isfb() {
		return bd_st_isfb;
	}
	public void setBd_st_isfb(String bd_st_isfb) {
		this.bd_st_isfb = bd_st_isfb;
	}
	public int getBd_nm_orderno() {
		return bd_nm_orderno;
	}
	public void setBd_nm_orderno(int bd_nm_orderno) {
		this.bd_nm_orderno = bd_nm_orderno;
	}
	public Date getBd_dt_updDate() {
		return bd_dt_updDate;
	}
	public void setBd_dt_updDate(Date bd_dt_updDate) {
		this.bd_dt_updDate = bd_dt_updDate;
	}
	 
}
