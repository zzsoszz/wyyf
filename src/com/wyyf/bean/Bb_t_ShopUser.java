package com.wyyf.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;
/**
 * 商家用户表
 * @author Administrator
 */

@Entity
@Table(name = "bb_t_shopuser")
@MyTable(tableName = "bb_t_shopuser",pkName="bb_st_id",anotherName="bb")
public class Bb_t_ShopUser {
	@Id
	@Column(length=32)private String bb_st_id;//主键ID--无用--只用做区分数据的唯一性
	@Column(length=32)private String bb_st_userid;//ae_t_sysuesr 【FK】用户表外键
	@Column(length=50)private String bb_st_area;//面积介绍
	@Column(length=20)private String bb_st_phone1;//电话1
	@Column(length=20)private String bb_st_phone2;//电话2
	@Column(length =10)private double bb_nm_jjpm;//竞价排名，设置数值，用户点击扣除，类似百度竞价
	@Column(length =10)private int bb_nm_clicks;//点击总数
	@Column(length=1000)private String bb_st_shopinfo;//店铺介绍
	//相关文件存储  ag_t_file  
	
	@Column(length =500)private String bb_st_remark;//备注，备用字段
	@Column(length =32)private String bb_st_addUserId;//创建人员ID  
	private Date bb_dt_addDate;//创建时间
	@Column(length =32)private String bb_st_updUserId;//修改人员ID 
	/**
	 * 构造函数
	 */
	public Bb_t_ShopUser() {}
	/**
	 * 自定义主键ID
	 * @param ae_st_id 主键ID
	 */
	public Bb_t_ShopUser(String bb_st_id) {
		this.bb_st_id = bb_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Bb_t_ShopUser(boolean isAutoId) {
		if(isAutoId){
			this.bb_st_id =StringUtils.getUUID32();
		}
	}
	public String getBb_st_id() {
		return bb_st_id;
	}
	public void setBb_st_id(String bb_st_id) {
		this.bb_st_id = bb_st_id;
	}
	public String getBb_st_userid() {
		return bb_st_userid;
	}
	public void setBb_st_userid(String bb_st_userid) {
		this.bb_st_userid = bb_st_userid;
	}
	public String getBb_st_area() {
		return bb_st_area;
	}
	public void setBb_st_area(String bb_st_area) {
		this.bb_st_area = bb_st_area;
	}
	public String getBb_st_phone1() {
		return bb_st_phone1;
	}
	public void setBb_st_phone1(String bb_st_phone1) {
		this.bb_st_phone1 = bb_st_phone1;
	}
	public String getBb_st_phone2() {
		return bb_st_phone2;
	}
	public void setBb_st_phone2(String bb_st_phone2) {
		this.bb_st_phone2 = bb_st_phone2;
	}
	public double getBb_nm_jjpm() {
		return bb_nm_jjpm;
	}
	public void setBb_nm_jjpm(double bb_nm_jjpm) {
		this.bb_nm_jjpm = bb_nm_jjpm;
	}
	public int getBb_nm_clicks() {
		return bb_nm_clicks;
	}
	public void setBb_nm_clicks(int bb_nm_clicks) {
		this.bb_nm_clicks = bb_nm_clicks;
	}
	public String getBb_st_shopinfo() {
		return bb_st_shopinfo;
	}
	public void setBb_st_shopinfo(String bb_st_shopinfo) {
		this.bb_st_shopinfo = bb_st_shopinfo;
	}
	public String getBb_st_remark() {
		return bb_st_remark;
	}
	public void setBb_st_remark(String bb_st_remark) {
		this.bb_st_remark = bb_st_remark;
	}
	public String getBb_st_addUserId() {
		return bb_st_addUserId;
	}
	public void setBb_st_addUserId(String bb_st_addUserId) {
		this.bb_st_addUserId = bb_st_addUserId;
	}
	public Date getBb_dt_addDate() {
		return bb_dt_addDate;
	}
	public void setBb_dt_addDate(Date bb_dt_addDate) {
		this.bb_dt_addDate = bb_dt_addDate;
	}
	public String getBb_st_updUserId() {
		return bb_st_updUserId;
	}
	public void setBb_st_updUserId(String bb_st_updUserId) {
		this.bb_st_updUserId = bb_st_updUserId;
	}
	 
}
