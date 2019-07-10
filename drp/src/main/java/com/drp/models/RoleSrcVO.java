package com.drp.models;

import java.util.Date;

/**
 * 角色资源实体类
 * 
 * @author curry
 *
 */

public class RoleSrcVO {

	private String role2src_id;
	private String role_id;
	private String src_id;
	private Date update_date;
	private Date create_date;
	private int data_isvalid_flg;
	private SrcVO srcVO;

	public String getRole2src_id() {
		return role2src_id;
	}

	public void setRole2src_id(String role2src_id) {
		this.role2src_id = role2src_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getSrc_id() {
		return src_id;
	}

	public void setSrc_id(String src_id) {
		this.src_id = src_id;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public int getData_isvalid_flg() {
		return data_isvalid_flg;
	}

	public void setData_isvalid_flg(int data_isvalid_flg) {
		this.data_isvalid_flg = data_isvalid_flg;
	}

	public SrcVO getSrcVO() {
		return srcVO;
	}

	public void setSrcVO(SrcVO srcVO) {
		this.srcVO = srcVO;
	}

}
