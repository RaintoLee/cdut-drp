package com.drp.dao;

import com.drp.models.RoleSrcVO;
import com.drp.models.UserRolePageVO;
import com.drp.models.UserRoleVO;
import com.drp.models.UserVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by LiuLi on 2018/3/22. 人员相关的dao接口
 */

public interface UserDAO {
	
	UserVO getUserByUserName(@Param("userName") String userName);

	int updateVoByPrimaryKey(@Param("userVO") UserVO userVO);

	void insertOneUser(@Param("userVO") UserVO userVO);
	
	UserVO getUserBasicInfoByUsrId(@Param("usr_id") String usr_id);
	
	void updateEmailByUsrId(@Param("userVO") UserVO userVO);
	
	void updateUserInfoByUsrId(@Param("userVO") UserVO userVO);
	
	void updatePwdByUsrId(@Param("userVO") UserVO userVO);

	List<String> getRoleIdByUsrId(@Param("usrId") String usrId);

	List<RoleSrcVO> getRoleSrcInfoByRoleId(@Param("roleId") String roleId);
	
	void insertOneUsrRoleInfo(@Param("userRoleVO") UserRoleVO userRoleVO);
	
	UserRoleVO getRoleByUsrIdAndRoleId(@Param("userRoleVO") UserRoleVO userRoleVO);

	List<String> getAgentList(@Param("userRolePageVO") UserRolePageVO userRolePageVO);

	String getUserNameByUsrId(String usr_id);

	Integer getCountByUserEmail(String newEmail);
	
}
