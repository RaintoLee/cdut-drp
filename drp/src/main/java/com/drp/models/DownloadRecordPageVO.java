package com.drp.models;

/**
 * 下载记录分页封装类
 * @author Administrator
 *
 */

public class DownloadRecordPageVO extends DownloadRecordVO {

	private PageVO pageVO;

	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}
	
}
