package com.drp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drp.models.BrowsingRecordPageVO;
import com.drp.models.BrowsingRecordVO;
import com.drp.models.PageVO;
import com.drp.models.UserVO;

public interface BrowsingDAO {
	
	void insertOneBrowsingRecord(@Param("browsingRecordVO") BrowsingRecordVO browsingRecordVO);
	
	List<BrowsingRecordVO> getBrowsingRecordByUsrId(@Param("browsingRecordPageVO") BrowsingRecordPageVO browsingRecordPageVO);

	List<UserVO> getPublicKey(PageVO pageVO);

	void addPageviews(String works_id);

}
