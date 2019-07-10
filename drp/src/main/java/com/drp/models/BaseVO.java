package com.drp.models;

import java.util.Date;

/**
 * 人员的其它信息
 * 
 * @author curry
 *
 */
public class BaseVO {

	// 是否是会员，0表示不是会员，1表示是会员
	protected int usr_isMem;
	protected Date usr_rgst_dt;
	protected Date usr_rgst_tm;
	// 人员资质证书图片地址
	protected String usr_qlfypic_src = "";
	// 当前状态，00不在线，01在线
	protected int usr_stat;
	// 证件图片存放地址
	protected String usr_certpic_src = "";
	// 人员头像地址
	protected String usr_ava_src = "";
	// 平台账户余额
	protected float acct_bal;
	// 平台账户支付密码
	protected String pay_pwd = "";
	// 人员数据是否有效
	protected int usr_isvalid_flg;

	public int getUsr_isMembership() {
		return usr_isMem;
	}

	public void setUsr_isMembership(int usr_isMem) {
		this.usr_isMem = usr_isMem;
	}

	public Date getUsr_rgst_dt() {
		return usr_rgst_dt;
	}

	public void setUsr_rgst_dt(Date usr_rgst_dt) {
		this.usr_rgst_dt = usr_rgst_dt;
	}

	public Date getUsr_rgst_tm() {
		return usr_rgst_tm;
	}

	public void setUsr_rgst_tm(Date usr_rgst_tm) {
		this.usr_rgst_tm = usr_rgst_tm;
	}

	public String getUsr_qlfypic_src() {
		return usr_qlfypic_src;
	}

	public void setUsr_qlfypic_src(String usr_qlfypic_src) {
		this.usr_qlfypic_src = usr_qlfypic_src;
	}

	public int getUsr_stat() {
		return usr_stat;
	}

	public void setUsr_stat(int usr_stat) {
		this.usr_stat = usr_stat;
	}

	public String getUsr_certpic_src() {
		return usr_certpic_src;
	}

	public void setUsr_certpic_src(String usr_certpic_src) {
		this.usr_certpic_src = usr_certpic_src;
	}

	public String getUsr_ava_src() {
		return usr_ava_src;
	}

	public void setUsr_ava_src(String usr_ava_src) {
		this.usr_ava_src = usr_ava_src;
	}

	public float getAcct_bal() {
		return acct_bal;
	}

	public void setAcct_bal(float acct_bal) {
		this.acct_bal = acct_bal;
	}

	public String getPay_pwd() {
		return pay_pwd;
	}

	public void setPay_pwd(String pay_pwd) {
		this.pay_pwd = pay_pwd;
	}

	public int getUsr_isvalid_flg() {
		return usr_isvalid_flg;
	}

	public void setUsr_isvalid_flg(int usr_isvalid_flg) {
		this.usr_isvalid_flg = usr_isvalid_flg;
	}
	
}
