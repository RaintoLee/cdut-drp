package com.drp.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LoginRecordVO {

	public String loginId;		//登录记录ID
	public String userId;		//用户ID
	public Date loginTime;		//登录时间
	public String location;		//登录地点
	public String isdelete;		//是否删除
	public Integer page;
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getLoginTime() {
		return loginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	
	
}
