package com.drp.models;

/**
 * 角色实体类
 * 
 * @author curry
 *
 */

public class RoleVO {

	private String role_id;
	private String role_name;
	private int role_isvalid_flg;

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public int getRole_isvalid_flg() {
		return role_isvalid_flg;
	}

	public void setRole_isvalid_flg(int role_isvalid_flg) {
		this.role_isvalid_flg = role_isvalid_flg;
	}

}
