package com.drp.models;

/**
 * 人员信息基本信息
 * 
 * @author curry
 *
 */
public class ProtocolVO {
	
	private String receive_flg= "";//支付状态
	private String agmt_id = "";   //代理协议id
	private String usr_id = "";   //作者id
	private String parta_id = "";  //甲方id
	private String partb_id = "";  //乙方id
	private String works_id = "";  //作品id
	private String protocol_deadline = "";  //代理期限
	private String agent_num = "";  //代理数量
	private Double auth_fee = 1.0;   //授权费用
	private String parta_patio = "";//甲方收费比例
	private String partb_patio = "";//已方收费比例
	private int info_isvalid_flg = 0;//信息数据是否有效
	private int protocol_type = 0;//协议类型
	private int role_id = 0;//发起方的角色
	
	private String deskey = "";//已方收费比例
	
	public Integer page;
	
	
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getDeskey() {
		return deskey;
	}
	public void setDeskey(String deskey) {
		this.deskey = deskey;
	}
	
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public void setAgent_num(String agent_num) {
		this.agent_num = agent_num;
	}
	public String getAgmt_id() {
		return agmt_id;
	}
	public String setAgmt_id(String agmt_id) {
		return this.agmt_id = agmt_id;
	}
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getParta_id() {
		return parta_id;
	}
	public void setParta_id(String parta_id) {
		this.parta_id = parta_id;
	}
	public String getPartb_id() {
		return partb_id;
	}
	public void setPartb_id(String partb_id) {
		this.partb_id = partb_id;
	}
	public String getWorks_id() {
		return works_id;
	}
	public void setWorks_id(String works_id) {
		this.works_id = works_id;
	}
	
	public String getProtocol_deadline() {
		return protocol_deadline;
	}
	public String getReceive_flg() {
		return receive_flg;
	 }
	 public void setReceive_flg(String receive_flg) {
		 this.receive_flg = receive_flg;
	 }
	public void setProtocol_deadline(String protocol_deadline) {
		this.protocol_deadline = protocol_deadline;
	}
	public String getAgent_num() {
		return agent_num;
	}
	public void setAgent_qty(String agent_num) {
		this.agent_num = agent_num;
	}
	public Double getAuth_fee() {
		return auth_fee;
	}
	public void setAuth_fee(Double auth_fee) {
		this.auth_fee = auth_fee;
	}
	public String getParta_patio() {
		return parta_patio;
	}
	public void setParta_patio(String parta_patio) {
		this.parta_patio = parta_patio;
	}
	public String getPartb_patio() {
		return partb_patio;
	}
	public void setPartb_patio(String partb_patio) {
		this.partb_patio = partb_patio;
	}
	public int getInfo_isvalid_flg() {
		return info_isvalid_flg;
	}
	public void setInfo_isvalid_flg(int info_isvalid_flg) {
		this.info_isvalid_flg = info_isvalid_flg;
	}
	public int getProtocol_type() {
		return protocol_type;
	}
	public void setProtocol_type(int protocol_type) {
		this.protocol_type = protocol_type;
	}
	
}
