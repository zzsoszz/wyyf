package com.wyyf.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lys.utils.StringUtils;
import com.lys.utils.annotation.MyTable;
/**
 * 师傅表==技师表
 * @author Administrator
 */

@Entity
@Table(name = "ba_t_labour")
@MyTable(tableName = "ba_t_labour",pkName="ba_st_id",anotherName="ba")
public class Ba_t_Labour {
	
	@Id
	@Column(length=32)private String ba_st_id;//主键ID--无用-只用做区分数据的唯一性
	@Column(length=32)private String ba_st_userid;//ae_t_sysuesr ae【FK】用户表外键
	@Column(length=10)private String ba_st_type;
	//YANFANG 1=验房师 
	//SHEJISHi   2=个性设计   3=金牌设计
	//GONGZHANG （1商户装修，2商品 房装修3别墅装修）
	//SHIFU   7=木工   8=漆工   9=水电工   10=瓦工  11=打孔师傅 12=铲墙师傅  15安装师傅（收费治理）  13=收费监理  14=空气监测
	
	
	@Column(length=10)private int ba_st_grade;//总星级
	@Column(length=500)private String ba_st_team_intro;//团队介绍
	@Column(length=10)private String ba_st_teamnum;//团队人数
	@Column(length=250)private String ba_st_price;//收费相关描述
	@Column(length =500)private String ba_st_remark;//备注，备用字段
	@Column(length =32)private String ba_st_addUserId;//创建人员ID  
	private Date ba_dt_addDate;//创建时间
	@Column(length =32)private String ba_st_updUserId;//修改人员ID 
	/**
	 * 构造函数
	 */
	public Ba_t_Labour() {}
	/**
	 * 自定义主键ID
	 * @param ae_st_id 主键ID
	 */
	public Ba_t_Labour(String ba_st_id) {
		this.ba_st_id = ba_st_id;
	}
	/**
	 * 是否自动创建id，ID长度为32UUID
	 * @param isAutoId true=是/false=否
	 */
	public Ba_t_Labour(boolean isAutoId) {
		if(isAutoId){
			this.ba_st_id =StringUtils.getUUID32();
		}
	}
	public String getBa_st_id() {
		return ba_st_id;
	}
	public void setBa_st_id(String ba_st_id) {
		this.ba_st_id = ba_st_id;
	}
	public String getBa_st_userid() {
		return ba_st_userid;
	}
	public void setBa_st_userid(String ba_st_userid) {
		this.ba_st_userid = ba_st_userid;
	}
	public String getBa_st_type() {
		return ba_st_type;
	}
	public void setBa_st_type(String ba_st_type) {
		this.ba_st_type = ba_st_type;
	}
	public int getBa_st_grade() {
		return ba_st_grade;
	}
	public void setBa_st_grade(int ba_st_grade) {
		this.ba_st_grade = ba_st_grade;
	}
	public String getBa_st_team_intro() {
		return ba_st_team_intro;
	}
	public void setBa_st_team_intro(String ba_st_team_intro) {
		this.ba_st_team_intro = ba_st_team_intro;
	}
	public String getBa_st_price() {
		return ba_st_price;
	}
	public void setBa_st_price(String ba_st_price) {
		this.ba_st_price = ba_st_price;
	}
	public String getBa_st_remark() {
		return ba_st_remark;
	}
	public void setBa_st_remark(String ba_st_remark) {
		this.ba_st_remark = ba_st_remark;
	}
	public String getBa_st_addUserId() {
		return ba_st_addUserId;
	}
	public void setBa_st_addUserId(String ba_st_addUserId) {
		this.ba_st_addUserId = ba_st_addUserId;
	}
	public Date getBa_dt_addDate() {
		return ba_dt_addDate;
	}
	public void setBa_dt_addDate(Date ba_dt_addDate) {
		this.ba_dt_addDate = ba_dt_addDate;
	}
	public String getBa_st_updUserId() {
		return ba_st_updUserId;
	}
	public void setBa_st_updUserId(String ba_st_updUserId) {
		this.ba_st_updUserId = ba_st_updUserId;
	}
	public String getBa_st_teamnum() {
		return ba_st_teamnum;
	}
	public void setBa_st_teamnum(String ba_st_teamnum) {
		this.ba_st_teamnum = ba_st_teamnum;
	}
	 
}
