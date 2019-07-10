package com.drp.models;

import java.util.Date;

/**
 * 用户角色实体类
 * 
 * @author curry
 *
 */

public class UserRoleVO {

	/**
	 * usr2role_id  类主键ID
	 * usr_id       用户ID
	 * role_id      角色ID
	 * create_date  申请时间
	 * update_date  更新时间
	 * data_isvalid_flg 数据是否有效
	 * usr_nm       用户名
	 * createDate   申请时间的字符串
	 */
	private String usr2role_id;
	private String usr_id;
	private String role_id;
	private Date create_date;
	private Date update_date;
	private int data_isvalid_flg;
	private String usr_nm;
	private String createDate;

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUsr2role_id() {
		return usr2role_id;
	}

	public void setUsr2role_id(String usr2role_id) {
		this.usr2role_id = usr2role_id;
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public int getData_isvalid_flg() {
		return data_isvalid_flg;
	}

	public void setData_isvalid_flg(int data_isvalid_flg) {
		this.data_isvalid_flg = data_isvalid_flg;
	}

	public String getUsr_nm() {
		return usr_nm;
	}

	public void setUsr_nm(String usr_nm) {
		this.usr_nm = usr_nm;
	}

}
