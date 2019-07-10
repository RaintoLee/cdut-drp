package com.drp.models;

/**
 * 浏览记录分页的封装类
 * @author Administrator
 *
 */

public class BrowsingRecordPageVO extends BrowsingRecordVO{

	private PageVO pageVO;

	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}
}
