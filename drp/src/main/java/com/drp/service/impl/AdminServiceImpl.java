package com.drp.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.controller.ESRecord;
import com.drp.dao.AdminDAO;
import com.drp.dao.WorksDAO;
import com.drp.models.ChangeWorksInfoVO;
import com.drp.models.LoadRecordVO;
import com.drp.models.PageVO;
import com.drp.models.UserRolePageVO;
import com.drp.models.UserRoleVO;
import com.drp.models.WorksPageVO;
import com.drp.models.WorksUserVO;
import com.drp.models.WorksVO;
import com.drp.service.AdminService;
import com.drp.util.UUidUtil;

import jxl.read.biff.Record;

/**
 * 管理员相关的service层接口实现类
 * @author curry
 *
 */

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private WorksDAO worksDAO;
	//修改角色权限
	@Override
	public void changeDataIsvalidFlg(UserRoleVO userRoleVO) {
		adminDAO.changeDataIsvalidFlg(userRoleVO);
	}
	
	//获取角色审核列表
	 @Override
	 public List<UserRoleVO> getCharManageList(int start, int offset){
		 PageVO pageVO = new PageVO();
		 UserRolePageVO userRolePageVO = new UserRolePageVO();
	     pageVO.setIndex(start);
	     pageVO.setPageSize(offset);
		 userRolePageVO.setPageVO(pageVO);
		 return adminDAO.getCharManageList(userRolePageVO);
	 }
	 
	 //获取作品审核列表
	 @Override
	 public List<WorksUserVO> getWorksManageList(int start, int offset) {
		WorksPageVO worksPageVO = new WorksPageVO();
		PageVO pageVO = new PageVO();
		pageVO.setIndex(start);
		pageVO.setPageSize(offset);
		worksPageVO.setPageVO(pageVO);
		return adminDAO.getWorksManageList(worksPageVO);
	}

	 //修改作品权限
	 @Override
	 public void changeWorksIsvalidFlg(WorksUserVO worksVO, HttpServletRequest request) throws Exception {
		WorksUserVO worksUserVO =  worksDAO.getWorksDetailsByWorksId(worksVO.getWorks_id());
		adminDAO.changeWorksIsvalidFlg(worksVO);
		//修改作品记录
		ESRecord<ChangeWorksInfoVO> record = new ESRecord<ChangeWorksInfoVO>();
		ChangeWorksInfoVO changeWorksInfoVO = new ChangeWorksInfoVO();
		changeWorksInfoVO.setBefore(worksUserVO.getWorks_isvalid_flg());
		changeWorksInfoVO.setAfter(worksVO.getWorks_isvalid_flg());
		changeWorksInfoVO.setBirthTime(new Date());
		changeWorksInfoVO.setUserId((String) request.getSession().getAttribute("accountId"));
		changeWorksInfoVO.setChangeId(UUidUtil.getUUID());
		changeWorksInfoVO.setWorksId(worksVO.getWorks_id());
		changeWorksInfoVO.setIsdelete("0");
		record.create(changeWorksInfoVO);
		//修改作品
		ESRecord<WorksUserVO> record1 = new ESRecord<WorksUserVO>();
		worksVO.setWorks_hdgt(worksUserVO.getWorks_hdgt());
		worksVO.setWorks_ischrg(worksUserVO.getWorks_ischrg());
		worksVO.setWorks_memo(worksUserVO.getWorks_memo());
		worksVO.setWorks_name(worksUserVO.getWorks_name());
		worksVO.setWorks_secr_lvl(worksUserVO.getWorks_secr_lvl());
		record1.update(worksVO);
	}
}
