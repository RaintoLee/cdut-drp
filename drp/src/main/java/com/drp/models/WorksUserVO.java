package com.drp.models;

/**
 * 作品和人员信息的封装类
 * @author Administrator
 *
 */

public class WorksUserVO extends WorksVO {
	
	private UserVO userVO;
	private Integer page;
	private String msg;
	
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	
}
