package com.drp.models;

import java.util.Date;

/**
 * 作品信息实体类
 * 
 * @author curry
 *
 */

public class WorksVO {
	/*
	 * `works_id` varchar(20) NOT NULL COMMENT '作品ID', 
	 * `usr_id` varchar(20) DEFAULT NULL COMMENT '自生成外键，人员信息表主键', 
	 * `works_name` varchar(32) DEFAULT NULL COMMENT '作品名称', 
	 * `works_spec` varchar(255) DEFAULT NULL COMMENT '作品大小', 
	 * `works_format` tinyint(2) DEFAULT NULL COMMENT 'avi、jpg、MP4、mp3、doc、txt....', 
	 * `works_cate` tinyint(2) DEFAULT NULL COMMENT '音频3，视频2，文档1，图片0', 
	 * `works_memo` text COMMENT '作品摘要',
	 * `works_ischrg` tinyint(2) DEFAULT NULL COMMENT '00表示免费作品，01表示收费作品',
	 * `works_hdgt` decimal(14,2) DEFAULT NULL COMMENT '作品预估费用', 
	 * `works_rgst_dt` date DEFAULT NULL COMMENT '作品注册日期', 
	 * `works_rgst_tm` datetime DEFAULT NULL COMMENT '作品注册时间', 
	 * `works_issu_dt` date DEFAULT NULL COMMENT '作品发布日期',
	 * `works_issu_tm` datetime DEFAULT NULL COMMENT '作品发布时间', 
	 * `works_pageviews` varchar(20) DEFAULT NULL COMMENT '作品浏览量', 
	 * `works_src` varchar(64) DEFAULT NULL COMMENT '作品来源', 
	 * `works_store_src` varchar(200) DEFAULT NULL COMMENT '作品存储地址', 
	 * `works_recinfo_src` varchar(200) DEFAULT NULL COMMENT'作品记录信息地址',
	 * `works_theme` varchar(32) DEFAULT NULL COMMENT '作品主题',
	 * `works_sym_key` varchar(32) DEFAULT NULL COMMENT '作品对称密钥',
	 * `works_secr_lvl` tinyint(2) DEFAULT NULL COMMENT '作品安全等级',
	 * `works_agent_qty` varchar(2) DEFAULT NULL COMMENT '作品代理数目0-表示未代理，1-表示已代理', 
	 * `works_auth_qty` varchar(2) DEFAULT NULL COMMENT '作品授权数目 0-表示未授权，1-表示已授权', 
	 * `works_isvalid_flg` tinyint(2) DEFAULT NULL COMMENT '作品数据是否有效 0表示无效，1表示有效',
	 */
	private String works_id;
	private String usr_id;
	private String works_name;
	private String works_spec;
	private String works_format;
	private String works_cate;
	private String works_memo;
	private int works_ischrg;
	private double works_hdgt;
	private Date works_rgst_dt;
	private Date works_rgst_tm;
	private Date works_issu_dt;
	private Date works_issu_tm;
	private String works_pageviews;
	private String works_src;
	private String works_store_src;
	private String works_recinfo_src;
	private String works_theme;
	private String works_sym_key;
	private int works_secr_lvl;
	private String works_agent_qty;
	private String works_auth_qty;
	private int works_isvalid_flg;
	private String works_ciphertext;
	private String usr_name;
	public String keywords;
	public String type;
	public String discount;
	public String isdelete;

	public String getWorks_ciphertext() {
		return works_ciphertext;
	}

	public void setWorks_ciphertext(String works_ciphertext) {
		this.works_ciphertext = works_ciphertext;
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

	public String getWorks_name() {
		return works_name;
	}

	public void setWorks_name(String works_name) {
		this.works_name = works_name;
	}

	public String getWorks_spec() {
		return works_spec;
	}

	public void setWorks_spec(String works_spec) {
		this.works_spec = works_spec;
	}

	public String getWorks_format() {
		return works_format;
	}

	public void setWorks_format(String works_format) {
		this.works_format = works_format;
	}

	public String getWorks_cate() {
		return works_cate;
	}

	public void setWorks_cate(String works_cate) {
		this.works_cate = works_cate;
	}

	public String getWorks_memo() {
		return works_memo;
	}

	public void setWorks_memo(String works_memo) {
		this.works_memo = works_memo;
	}

	public int getWorks_ischrg() {
		return works_ischrg;
	}

	public void setWorks_ischrg(int works_ischrg) {
		this.works_ischrg = works_ischrg;
	}

	public double getWorks_hdgt() {
		return works_hdgt;
	}

	public void setWorks_hdgt(double works_hdgt) {
		this.works_hdgt = works_hdgt;
	}

	public Date getWorks_rgst_dt() {
		return works_rgst_dt;
	}

	public void setWorks_rgst_dt(Date works_rgst_dt) {
		this.works_rgst_dt = works_rgst_dt;
	}

	public Date getWorks_rgst_tm() {
		return works_rgst_tm;
	}

	public void setWorks_rgst_tm(Date works_rgst_tm) {
		this.works_rgst_tm = works_rgst_tm;
	}

	public Date getWorks_issu_dt() {
		return works_issu_dt;
	}

	public void setWorks_issu_dt(Date works_issu_dt) {
		this.works_issu_dt = works_issu_dt;
	}

	public Date getWorks_issu_tm() {
		return works_issu_tm;
	}

	public void setWorks_issu_tm(Date works_issu_tm) {
		this.works_issu_tm = works_issu_tm;
	}

	public String getWorks_pageviews() {
		return works_pageviews;
	}

	public void setWorks_pageviews(String works_pageviews) {
		this.works_pageviews = works_pageviews;
	}

	public String getWorks_src() {
		return works_src;
	}

	public void setWorks_src(String works_src) {
		this.works_src = works_src;
	}

	public String getWorks_store_src() {
		return works_store_src;
	}

	public void setWorks_store_src(String works_store_src) {
		this.works_store_src = works_store_src;
	}

	public String getWorks_recinfo_src() {
		return works_recinfo_src;
	}

	public void setWorks_recinfo_src(String works_recinfo_src) {
		this.works_recinfo_src = works_recinfo_src;
	}

	public String getWorks_theme() {
		return works_theme;
	}

	public void setWorks_theme(String works_theme) {
		this.works_theme = works_theme;
	}

	public String getWorks_sym_key() {
		return works_sym_key;
	}

	public void setWorks_sym_key(String works_sym_key) {
		this.works_sym_key = works_sym_key;
	}

	public int getWorks_secr_lvl() {
		return works_secr_lvl;
	}

	public void setWorks_secr_lvl(int works_secr_lvl) {
		this.works_secr_lvl = works_secr_lvl;
	}

	public String getWorks_agent_qty() {
		return works_agent_qty;
	}

	public void setWorks_agent_qty(String works_agent_qty) {
		this.works_agent_qty = works_agent_qty;
	}

	public String getWorks_auth_qty() {
		return works_auth_qty;
	}

	public void setWorks_auth_qty(String works_auth_qty) {
		this.works_auth_qty = works_auth_qty;
	}

	public int getWorks_isvalid_flg() {
		return works_isvalid_flg;
	}

	public void setWorks_isvalid_flg(int works_isvalid_flg) {
		this.works_isvalid_flg = works_isvalid_flg;
	}

	public String getUsr_name() {
		return usr_name;
	}

	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
}
