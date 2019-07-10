package com.drp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.drp.models.DownloadRecordVO;

/**
 * 下载记录的接口
 * @author Administrator
 *
 */

public interface DownloadService {
	
	void insertOneDownloadRecord(String usr_id, String works_id, HttpServletRequest request) throws Exception;
    
    List<DownloadRecordVO> getDownloadRecordByUsrId(String usr_id, int start, int offset);
}
