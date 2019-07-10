package com.drp.models;

public class TableVO {

	
	private Integer start;		//起始位置
	private Integer offset;		//每页条数
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
}
