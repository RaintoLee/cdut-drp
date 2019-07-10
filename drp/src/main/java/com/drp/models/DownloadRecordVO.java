package com.drp.models;

import java.util.Date;

/**
 * 作品下载的人员作品封装类
 * 
 * @author Administrator
 *
 */

public class DownloadRecordVO {

	/*
	 * `record_id` varchar(20) NOT NULL COMMENT '下载记录id', `usr_id` varchar(20)
	 * DEFAULT NULL COMMENT '下载作品的人员id 人员信息表中的主键', `works_id` varchar(20)
	 * DEFAULT NULL COMMENT '作品id 作品信息表中的主键', `download_time` datetime DEFAULT
	 * NULL COMMENT '作品下载时间',
	 */
	private String record_id;
	private Date download_time;
	private String usr_id;
	private String works_id;
	private WorksUserVO worksUserVO;

	public String getWorks_id() {
		return works_id;
	}

	public void setWorks_id(String works_id) {
		this.works_id = works_id;
	}

	public String getRecord_id() {
		return record_id;
	}

	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}

	public Date getDownload_time() {
		return download_time;
	}

	public void setDownload_time(Date download_time) {
		this.download_time = download_time;
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
