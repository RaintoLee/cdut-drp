package com.drp.service;

import java.util.List;

import com.drp.models.BrowsingRecordVO;
import com.drp.models.UserVO;

/**
 * 浏览记录相关的Service层的接口类
 * @author Administrator
 *
 */

public interface BrowsingService {
	
	BrowsingRecordVO insertOneBrowsingRecord(String usr_id, String works_id);
	
	List<BrowsingRecordVO> getBrowsingRecordByUsrId(String usr_id, int start, int offset);

	List<UserVO> getPublicKey(int start, int offset);

}
