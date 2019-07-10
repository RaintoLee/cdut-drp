package com.drp.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LoadRecordVO {

	public String loadId;			//操作记录Id
	public String userId;			//操作用户Id
	public Date loadTime;			//操作书时间
	public String location;			//操作地点
	public String type;				//操作类型  0 上传  1下载
	public String fileType;			//文件类型  0 作品  1秘钥
	public String worksId;			//作品Id
	public String isdelete;			//
	
	public Integer page;
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getLoadId() {
		return loadId;
	}
	public void setLoadId(String loadId) {
		this.loadId = loadId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getLoadTime() {
		return loadTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public void setLoadTime(Date loadTime) {
		this.loadTime = loadTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getWorksId() {
		return worksId;
	}
	public void setWorksId(String worksId) {
		this.worksId = worksId;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	
	
}
