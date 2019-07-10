package com.drp.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 浏览记录的人员作品封装类
 * 
 * @author Administrator
 *
 */

public class BrowsingRecordVO {

	/*
	 * `browse_id` varchar(20) NOT NULL COMMENT '浏览记录ID', `works_id` varchar(20)
	 * DEFAULT NULL COMMENT '作品id 作品信息表中的主键', `user_id` varchar(20) DEFAULT NULL
	 * COMMENT '下载作品的人员id 人员信息表中的主键', `browsing_time` datetime DEFAULT NULL
	 * COMMENT '作品浏览时间', `timeOut` String DEFAULT NULL
	 * COMMENT '浏览时间超时判定'
	 */

	private String browse_id;

	private Date browsing_time;
	private String works_id;
	private String usr_id;
	private WorksUserVO worksUserVO;
	private String timeOut;
	private String isdelete;
	private Integer page;
	

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getBrowse_id() {
		return browse_id;
	}

	public void setBrowse_id(String browse_id) {
		this.browse_id = browse_id;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getBrowsing_time() {
		return browsing_time;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public void setBrowsing_time(Date browsing_time) {
		this.browsing_time = browsing_time;
	}

	public String getWorks_id() {
		return works_id;
	}

	public void setWorks_id(String works_id) {
		this.works_id = works_id;
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	public WorksUserVO getWorksUserVO() {
		return worksUserVO;
	}

	public void setWorksUserVO(WorksUserVO worksUserVO) {
		this.worksUserVO = worksUserVO;
	}
}