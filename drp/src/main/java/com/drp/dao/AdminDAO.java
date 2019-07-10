package com.drp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drp.models.UserRolePageVO;
import com.drp.models.UserRoleVO;
import com.drp.models.WorksPageVO;
import com.drp.models.WorksUserVO;
import com.drp.models.WorksVO;

public interface AdminDAO {

	//修改角色权限
	void changeDataIsvalidFlg(@Param("userRoleVO") UserRoleVO userRoleVO );
	
	//显示角色申请列表
	List<UserRoleVO> getCharManageList(@Param("userRolePageVO") UserRolePageVO userRolePageVO);
	
	//显示作品审核列表
	List<WorksUserVO> getWorksManageList(@Param("worksPageVO") WorksPageVO worksPageVO);

	//修改作品权限
	void changeWorksIsvalidFlg(@Param("worksVO") WorksVO worksVO);
}

