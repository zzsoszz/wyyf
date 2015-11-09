package com.wyyf.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lys.utils.annotation.MyTable;
@Entity
@Table(name = "br_t_video")
@MyTable(tableName = "br_t_video",pkName="br_st_id",anotherName="br")
public class Br_t_video {

	@Id
	@Column(length =32)private String br_st_id;//视频表ID  uuid.hex length="32"
	@Column(length =5055)private String br_st_url;//视频连接
	@Column(length =955)private String br_st_name;//视频名字
	@Column(length =32)private String br_st_userid;//上传人
	private Date br_st_addDate;//创建时间
	public String getBr_st_id() {
		return br_st_id;
	}
	public void setBr_st_id(String br_st_id) {
		this.br_st_id = br_st_id;
	}
	public String getBr_st_url() {
		return br_st_url;
	}
	public void setBr_st_url(String br_st_url) {
		this.br_st_url = br_st_url;
	}
	public String getBr_st_name() {
		return br_st_name;
	}
	public void setBr_st_name(String br_st_name) {
		this.br_st_name = br_st_name;
	}
	public String getBr_st_userid() {
		return br_st_userid;
	}
	public void setBr_st_userid(String br_st_userid) {
		this.br_st_userid = br_st_userid;
	}
	public Date getBr_st_addDate() {
		return br_st_addDate;
	}
	public void setBr_st_addDate(Date br_st_addDate) {
		this.br_st_addDate = br_st_addDate;
	}
	
	
}
