package com.drp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.drp.models.ProtocolVO;
import com.drp.models.UserRoleVO;
import com.drp.models.WorksUserVO;
import com.drp.models.WorksVO;

/**
 * 管理员相关的Service层接口类
 * @author curry
 *
 */
public interface AdminService {
	
    //修改角色权限
	void changeDataIsvalidFlg(UserRoleVO userRoleVO);
	
	//读取角色申请列表
	List<UserRoleVO> getCharManageList(int start, int offset);
	
	//读取作品审核列表
	List<WorksUserVO> getWorksManageList(int start, int offset);

	//修改作品权限
	void changeWorksIsvalidFlg(WorksUserVO worksVO, HttpServletRequest request) throws Exception;


}
