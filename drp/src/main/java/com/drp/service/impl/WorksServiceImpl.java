package com.drp.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.controller.ESRecord;
import com.drp.dao.UserDAO;
import com.drp.dao.WorksDAO;
import com.drp.models.ChangeWorksInfoVO;
import com.drp.models.LoadRecordVO;
import com.drp.models.PageVO;
import com.drp.models.UserVO;
import com.drp.models.WorksPageVO;
import com.drp.models.WorksUserVO;
import com.drp.models.WorksVO;
import com.drp.service.UserService;
import com.drp.service.WorksService;
import com.drp.util.AddressByIpUtil;
import com.drp.util.IpAddressUtil;
import com.drp.util.UUidUtil;

/**
 * 作品相关的service层接口实现类
 * @author curry
 *
 */

@Service("worksService")
public class WorksServiceImpl implements WorksService {

	@Autowired
	private WorksDAO worksDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	/*
	 * 向作品表插入一条作品信息
	 * (non-Javadoc)
	 * @see com.drp.service.WorksService#insertOneWorks(com.drp.models.WorksVO)
	 */
	@Override
	public void insertOneWorks(WorksUserVO worksVO, HttpServletRequest request) throws Exception {
		worksVO.setWorks_id(UUidUtil.getUUID());
		Date date = new Date();
		worksVO.setWorks_rgst_dt(date);
		worksVO.setWorks_rgst_tm(date);
		worksVO.setWorks_issu_dt(date);
		worksVO.setWorks_issu_tm(date);
		worksVO.setWorks_pageviews("0");
		worksVO.setDiscount("0");
		worksVO.setType(worksVO.getWorks_cate());
		worksVO.setWorks_agent_qty("0");
		worksVO.setWorks_auth_qty("0");
		//根据作品加密位数定义安全等级，待修改
		worksVO.setWorks_secr_lvl(0);
		worksDAO.insertOneWorks(worksVO);
		//作品上传记录
		ESRecord<LoadRecordVO> record = new ESRecord<LoadRecordVO>();
		LoadRecordVO loadRecord = new LoadRecordVO();
		String address = IpAddressUtil.getIpAdrress2(request);
        String location = "";
		try {
			location = AddressByIpUtil.getAddresses("ip="+address+"&ak="+"H1DOkgEyoqePW8Z6ahp6GPQq8excwvh0", "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(location);
		loadRecord.setFileType("0");
		loadRecord.setIsdelete("0");
		loadRecord.setLoadId(UUidUtil.getUUID());
		loadRecord.setLoadTime(date);
		loadRecord.setType("0");
		loadRecord.setLocation(location);
		loadRecord.setUserId(worksVO.getUsr_id());
		loadRecord.setWorksId(worksVO.getWorks_id());
		record.create(loadRecord);
		//作品注册
		ESRecord<WorksUserVO> record1 = new ESRecord<WorksUserVO>();
		WorksUserVO worksUserVO = new WorksUserVO();
		worksUserVO = (WorksUserVO) worksVO;
		String usr_id = request.getSession().getAttribute("accountId").toString();
		String userName = userDAO.getUserNameByUsrId(usr_id);
		worksUserVO.setUsr_name(userName);
		worksUserVO.setUsr_id(usr_id);
		record1.create(worksUserVO);
	}

	/*
	 * 作品显示
	 */
	@Override
	public List<WorksUserVO> getWorksListByUsrId(String usr_id, int start, int offset) {
		WorksPageVO worksPageVO = new WorksPageVO();
		PageVO pageVO = new PageVO();
		worksPageVO.setUsr_id(usr_id);
		pageVO.setIndex(start);
		pageVO.setPageSize(offset);
		worksPageVO.setPageVO(pageVO);
		return worksDAO.getWorksListByUsrId(worksPageVO);
	}
	
	/**
	 * 作品详情显示
	 */

	@Override
	public WorksUserVO getWorksDetailsByWorksId(String works_id) {
		return worksDAO.getWorksDetailsByWorksId(works_id);
	}
	
	/**
	 * 协议模块查询符合条件的作品详情显示
	 */

	@Override
	public WorksUserVO findWorksInfoByorksName(String works_name) {
		return worksDAO.findWorksInfoByorksName(works_name);
	}
	
	
	/**
	 *获取推荐作品信息
	 */
	
	@Override
	public List<WorksUserVO> getWorksInfoByWorksName(String works_name, int start, int offset) {
		WorksPageVO worksPageVO = new WorksPageVO();
		worksPageVO.setWorks_name(works_name);
		PageVO pageVO = new PageVO();
		pageVO.setIndex(start);
		pageVO.setPageSize(offset);
		worksPageVO.setPageVO(pageVO);
		return worksDAO.getWorksInfoByWorksName(worksPageVO);
	}
	@Override
	public void updateWorksInfoByWorksId(WorksVO worksVO) throws Exception {
		WorksVO before = worksDAO.getWorksDetailsByWorksId(worksVO.getWorks_id());
		worksDAO.updateWorksInfoByWorksId(worksVO);
		WorksUserVO worksUserVO = new WorksUserVO();
		worksUserVO = (WorksUserVO) worksVO;
		//作品信息修改
		ESRecord<WorksUserVO> record = new ESRecord<WorksUserVO>();
		record.update(worksUserVO);
		//作品信息修改记录
		ChangeWorksInfoVO changeWorksInfoVO = new ChangeWorksInfoVO();
		changeWorksInfoVO.setBefore(before.getWorks_isvalid_flg());
		changeWorksInfoVO.setAfter(worksVO.getWorks_isvalid_flg());
		ESRecord<ChangeWorksInfoVO> record1 = new ESRecord<ChangeWorksInfoVO>();
		record1.create(changeWorksInfoVO);
	}
}
