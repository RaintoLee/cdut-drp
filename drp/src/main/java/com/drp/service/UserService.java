package com.drp.service;

import java.util.List;
import com.drp.models.RoleSrcVO;
import com.drp.models.UserRoleVO;
import com.drp.models.UserVO;

/**
 * Created by LiuLi on 2018/3/22. 人员相关的service接口
 */
public interface UserService {

	UserVO getUserByUserName(String userName);

	int updateUserVO(UserVO userVO);

	void insertOneUser(UserVO userVO) throws Exception;

	UserVO getUserBasicInfoByUsrId(String usr_id);
	
	void updateEmailByUsrId(String usr_id, String usr_email);
	
	void updateUserInfoByUsrId(UserVO userVO) throws Exception;
	
	void updatePwdByUsrId(String usr_id, String use_pwd);

	List<String> getRoleIdByUsrId(String usrId);
	
	List<RoleSrcVO> getSrcListByUsrId(String usrId);
	
	void insertOneNewRole(String usr_id, String role_id);
	
	UserRoleVO getRoleByUsrIdAndRoleId(String usr_id, String role_id);

	Integer getCountByUserEmail(String newEmail);
	
}
