package com.drp.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ChangeUserInfoVO {

	public String changeId;		//记录id
	public Date birthTime;		//记录生成时间
	public UserVO before;		//修改前内容
	public UserVO after;		//修改后内容
	public String userId;		//用户id
	public String isdelete;		//是否删除
	public Integer page;
	
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getChangeId() {
		return changeId;
	}
	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getBirthTime() {
		return birthTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}
	public UserVO getBefore() {
		return before;
	}
	public void setBefore(UserVO before) {
		this.before = before;
	}
	public UserVO getAfter() {
		return after;
	}
	public void setAfter(UserVO after) {
		this.after = after;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	
	
}
