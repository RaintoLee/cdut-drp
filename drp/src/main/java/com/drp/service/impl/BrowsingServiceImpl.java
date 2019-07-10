package com.drp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.dao.BrowsingDAO;
import com.drp.models.BrowsingRecordPageVO;
import com.drp.models.BrowsingRecordVO;
import com.drp.models.PageVO;
import com.drp.models.UserVO;
import com.drp.service.BrowsingService;
import com.drp.util.UUidUtil;

/**
 * 浏览记录相关的Service接口层实现类
 * @author Administrator
 *
 */

@Service("browsingService")
public class BrowsingServiceImpl implements BrowsingService {
	
	@Autowired
	private BrowsingDAO browsingDAO;

	@Override
	public BrowsingRecordVO insertOneBrowsingRecord(String usr_id, String works_id) {
		BrowsingRecordVO browsingRecordVO = new BrowsingRecordVO();
		browsingRecordVO.setBrowse_id(UUidUtil.getUUID());
		browsingRecordVO.setUsr_id(usr_id);
		browsingRecordVO.setWorks_id(works_id);
		browsingRecordVO.setBrowsing_time(new Date());
		browsingDAO.insertOneBrowsingRecord(browsingRecordVO);
		browsingDAO.addPageviews(works_id);
		return browsingRecordVO;
	}

	@Override
	public List<BrowsingRecordVO> getBrowsingRecordByUsrId(String usr_id, int start, int offset) {
		BrowsingRecordPageVO browsingRecordPageVO = new BrowsingRecordPageVO();
		browsingRecordPageVO.setUsr_id(usr_id);
		PageVO pageVO = new PageVO();
		pageVO.setIndex(start);
		pageVO.setPageSize(offset);
		browsingRecordPageVO.setPageVO(pageVO);
		return browsingDAO.getBrowsingRecordByUsrId(browsingRecordPageVO);
	}

	@Override
	public List<UserVO> getPublicKey(int start, int offset) {
		// TODO Auto-generated method stub
		PageVO pageVO = new PageVO();
		pageVO.setIndex(start);
		pageVO.setPageSize(offset);
		return browsingDAO.getPublicKey(pageVO);
	}

}
