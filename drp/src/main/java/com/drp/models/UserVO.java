package com.drp.models;

/**
 * 人员信息基本信息
 * 
 * @author curry
 *
 */
public class UserVO extends BaseVO {
	
	/*
	 * `usr_id` varchar(64) NOT NULL COMMENT '人员序号，暂定uuid生成',
  `usr_nm` varchar(120) NOT NULL COMMENT '用户名，用户昵称、邮箱、手机',
  `usr_pwd` varchar(20) NOT NULL COMMENT '密码',
  `usr_gender` tinyint(2) DEFAULT NULL COMMENT '人员性别，0表示未知的性别，1表示男性，2表示女性，9表示未说明的性别',
  `usr_email` varchar(40) NOT NULL COMMENT '人员email，注册时激活账户和修改密码需要邮箱',
  `usr_phone` varchar(15) NOT NULL COMMENT '人员手机号',
  `usr_isMembership` tinyint(2) DEFAULT NULL COMMENT '是否是会员，0表示不是会员，1表示是会员',
  `usr_rgst_dt` date DEFAULT NULL COMMENT '注册日期',
  `usr_rgst_tm` datetime DEFAULT NULL COMMENT '注册时间',
  `usr_qlfy` varchar(2) DEFAULT NULL COMMENT '人员资质',
  `usr_qlfypic_src` varchar(255) DEFAULT NULL COMMENT '人员资质证书图片地址',
  `usr_stat` tinyint(2) DEFAULT NULL COMMENT '当前状态，00不在线，01在线',
  `usr_cert_cate` tinyint(2) DEFAULT NULL COMMENT '证件类型，0表示身份证，1表示户口簿，2表示护照，X其他证件',
  `usr_cert_num` varchar(20) DEFAULT NULL COMMENT '证件号码，',
  `usr_certpic_src` varchar(255) DEFAULT NULL COMMENT '存在图片服务器的url地址',
  `usr_addr` varchar(255) DEFAULT NULL COMMENT '人员地址',
  `usr_nicknm` varchar(60) DEFAULT NULL COMMENT '人员真实姓名',
  `usr_ava_src` varchar(200) DEFAULT NULL COMMENT '人员头像地址',
  `acct_bal` decimal(18,2) DEFAULT NULL COMMENT '平台账户余额',
  `pay_pwd` varchar(20) DEFAULT NULL COMMENT '平台账户支付密码',
  `usr_isvalid_flg` tinyint(2) DEFAULT NULL COMMENT '人员数据是否有效',
	 */

	private String usr_id = "";
	private String usr_nm = "";
	private String usr_pwd = "";
	private String usr_phone = "";
	private String usr_email = "";
	private String usr_nicknm = "";
	private String usr_cert_num = "";
	private int usr_qlfy;
	private int usr_cert_cate;
	private String usr_addr = "";
	private int usr_gender;
	private String usr_pub_key;
	//操作用页码
	public Integer page;
	
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getUsr_pub_key() {
		return usr_pub_key;
	}

	public void setUsr_pub_key(String usr_pub_key) {
		this.usr_pub_key = usr_pub_key;
	}

	public int getUsr_gender() {
		return usr_gender;
	}

	public void setUsr_gender(int usr_gender) {
		this.usr_gender = usr_gender;
	}
	
	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	public String getUsr_nm() {
		return usr_nm;
	}

	public void setUsr_nm(String usr_nm) {
		this.usr_nm = usr_nm;
	}

	public String getUsr_pwd() {
		return usr_pwd;
	}

	public void setUsr_pwd(String usr_pwd) {
		this.usr_pwd = usr_pwd;
	}

	public String getUsr_phone() {
		return usr_phone;
	}

	public void setUsr_phone(String usr_phone) {
		this.usr_phone = usr_phone;
	}

	public String getUsr_email() {
		return usr_email;
	}

	public void setUsr_email(String usr_email) {
		this.usr_email = usr_email;
	}
	
	public String getUsr_nicknm() {
		return usr_nicknm;
	}

	public void setUsr_nicknm(String usr_nicknm) {
		this.usr_nicknm = usr_nicknm;
	}
	
	public String getUsr_cert_num() {
		return usr_cert_num;
	}

	public void setUsr_cert_num(String usr_cert_num) {
		this.usr_cert_num = usr_cert_num;
	}
	
	public int getUsr_qlfy() {
		return usr_qlfy;
	}

	public void setUsr_qlfy(int usr_qlfy) {
		this.usr_qlfy = usr_qlfy;
	}

	public int getUsr_cert_cate() {
		return usr_cert_cate;
	}

	public void setUsr_cert_cate(int usr_cert_cate) {
		this.usr_cert_cate = usr_cert_cate;
	}
	
	public String getUsr_addr() {
		return usr_addr;
	}

	public void setUsr_addr(String usr_addr) {
		this.usr_addr = usr_addr;
	}

}
