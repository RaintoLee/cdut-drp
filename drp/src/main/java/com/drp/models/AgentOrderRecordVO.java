package com.drp.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AgentOrderRecordVO {

	public String orderId;		//交易记录Id
	public String userId;		//作品作者Id
	public String agentId;		//代理商Id
	public String worksId;		//作品Id
	public Date birthTime;		//记录生成时间
	public String isdelete;		//
	public String type;			//订单状态
								//0 双方都未签署协议 1其中一方签署了协议 2双方都签署了协议
								//3 拒绝签署协议 4取消了订单 5已付款 6已确认收款
	public Integer page;
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getWorksId() {
		return worksId;
	}
	public void setWorksId(String worksId) {
		this.worksId = worksId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getBirthTime() {
		return birthTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
