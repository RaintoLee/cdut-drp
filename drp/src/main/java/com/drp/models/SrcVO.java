package com.drp.models;

/**
 * 资源实体类
 * 
 * @author curry
 *
 */

public class SrcVO {

	private String src_id;
	private String src_name;
	private String src_url;
	private String src_menu_flag;
	private int src_isvalid_flg;

	public String getSrc_id() {
		return src_id;
	}

	public void setSrc_id(String src_id) {
		this.src_id = src_id;
	}

	public String getSrc_name() {
		return src_name;
	}

	public void setSrc_name(String src_name) {
		this.src_name = src_name;
	}

	public String getSrc_url() {
		return src_url;
	}

	public void setSrc_url(String src_url) {
		this.src_url = src_url;
	}
	
	public String getSrc_menu_flag() {
		return src_menu_flag;
	}

	public void setSrc_menu_flag(String src_menu_flag) {
		this.src_menu_flag = src_menu_flag;
	}

	public int getSrc_isvalid_flg() {
		return src_isvalid_flg;
	}

	public void setSrc_isvalid_flg(int src_isvalid_flg) {
		this.src_isvalid_flg = src_isvalid_flg;
	}

}
