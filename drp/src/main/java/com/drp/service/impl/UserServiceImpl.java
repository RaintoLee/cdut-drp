package com.drp.service.impl;

import com.drp.controller.ESRecord;
import com.drp.dao.DownloadDAO;
import com.drp.dao.UserDAO;
import com.drp.models.ChangeUserInfoVO;
import com.drp.models.LoadRecordVO;
import com.drp.models.RoleSrcVO;
import com.drp.models.UserRoleVO;
import com.drp.models.UserVO;
import com.drp.service.UserService;
import com.drp.util.UUidUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LiuLi on 2018/3/22. 人员相关的service实现类
 */

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	DownloadDAO downloadDAO;
	
	/*
	 * 通过用户名查找用户信息
	 * @see com.drp.service.UserService#getUserByUserName(java.lang.String)
	 */
	public UserVO getUserByUserName(String userName) {
		return userDAO.getUserByUserName(userName);
	}

	public int updateUserVO(UserVO userVO) {
		return userDAO.updateVoByPrimaryKey(userVO);
	}

	/*
	 * 往数据库插入一个user的信息
	 * @see com.drp.service.UserService#insertOneUser(com.drp.models.UserVO)
	 */
	@Override
	public void insertOneUser(UserVO userVO) throws Exception {
		// userId是随机生成的
		String userId = UUidUtil.getUUID();
		// usr2roleId随机生成
		String usr2roleId = UUidUtil.getUUID();
		userVO.setUsr_id(userId);
		UserRoleVO userRoleVO = new UserRoleVO();
		userRoleVO.setUsr2role_id(usr2roleId);
		userRoleVO.setUsr_id(userId);
		userRoleVO.setRole_id("1");
		userRoleVO.setCreate_date(new Date());
		userRoleVO.setUpdate_date(new Date());
		// 把用户角色信息设置为有效
		userRoleVO.setData_isvalid_flg(1);
		// 插入用户角色信息到用户角色表
		ESRecord<UserVO> record = new ESRecord<UserVO>();
		record.create(userVO);
		userDAO.insertOneUsrRoleInfo(userRoleVO);
		// 插入用户信息到用户表
		userDAO.insertOneUser(userVO);
	}
	
	/*
	 * 通过usr_id获取用户基本信息
	 */
	public UserVO getUserBasicInfoByUsrId(String usr_id) {
		return userDAO.getUserBasicInfoByUsrId(usr_id);
	}

	@Override
	public void updateEmailByUsrId(String usr_id, String usr_email) {
		UserVO userVO = new UserVO();
		userVO.setUsr_id(usr_id);
		userVO.setUsr_email(usr_email);
		userDAO.updateEmailByUsrId(userVO);
	}

	@Override
	public void updateUserInfoByUsrId(UserVO userVO) throws Exception {
		UserVO before = userDAO.getUserBasicInfoByUsrId(userVO.getUsr_id());
		UserVO after = userVO;
		//用户信息修改记录
		ChangeUserInfoVO changeUserInfoVO = new ChangeUserInfoVO();
		changeUserInfoVO.setBefore(before);
		changeUserInfoVO.setAfter(after);
		changeUserInfoVO.setBirthTime(new Date());
		changeUserInfoVO.setChangeId(UUidUtil.getUUID());
		changeUserInfoVO.setIsdelete("0");
		changeUserInfoVO.setUserId(userVO.getUsr_id());
		ESRecord<ChangeUserInfoVO> record = new ESRecord<ChangeUserInfoVO>();
		record.create(changeUserInfoVO);
		//用户信息修改
		ESRecord<UserVO> record1 = new ESRecord<UserVO>();
		record1.update(userVO);
		userDAO.updateUserInfoByUsrId(userVO);
		
	}

	@Override
	public void updatePwdByUsrId(String usr_id, String use_pwd) {
		UserVO userVO = new UserVO();
		userVO.setUsr_id(usr_id);
		userVO.setUsr_pwd(use_pwd);
		userDAO.updatePwdByUsrId(userVO);
	}

	@Override
	public List<String> getRoleIdByUsrId(String usrId) {
		return userDAO.getRoleIdByUsrId(usrId);
	}

	@Override
	public List<RoleSrcVO> getSrcListByUsrId(String usrId) {
		// 通过usrId查询出roleId
		List<String> roleIdList = getRoleIdByUsrId(usrId);
		List<RoleSrcVO> roleSrcVOList = new ArrayList<RoleSrcVO>();
		for (int i = 0 ; i < roleIdList.size() ; i++) {
			// 通过roleId查询出角色对应的资源信息
			List<RoleSrcVO> roleSrcVOResult = userDAO.getRoleSrcInfoByRoleId(roleIdList.get(i));
			for (int j = 0 ; j < roleSrcVOResult.size() ; j++) {
				roleSrcVOList.add(roleSrcVOResult.get(j));
			}
		}
		return roleSrcVOList;
	}
	
	@Override
	public void insertOneNewRole(String usr_id, String role_id) {
		UserRoleVO userRoleVO = new UserRoleVO();
		userRoleVO.setUsr2role_id(UUidUtil.getUUID());
		userRoleVO.setUsr_id(usr_id);
		userRoleVO.setRole_id(role_id);
		userRoleVO.setCreate_date(new Date());
		userRoleVO.setUpdate_date(new Date());
		userRoleVO.setData_isvalid_flg(0);
		userDAO.insertOneUsrRoleInfo(userRoleVO);
	}
	
	@Override
	public UserRoleVO getRoleByUsrIdAndRoleId(String usr_id, String role_id) {
		UserRoleVO userRoleVO = new UserRoleVO();
		userRoleVO.setUsr_id(usr_id);
		userRoleVO.setRole_id(role_id);
		return userDAO.getRoleByUsrIdAndRoleId(userRoleVO);
	}

	@Override
	public Integer getCountByUserEmail(String newEmail) {
		return userDAO.getCountByUserEmail(newEmail);
	}

}
