package com.drp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.drp.models.WorksPageVO;
import com.drp.models.WorksUserVO;
import com.drp.models.WorksVO;

/**
 * 作品相关的Service层接口类
 * @author curry
 *
 */

public interface WorksService {
	
	void insertOneWorks(WorksUserVO worksVO, HttpServletRequest request) throws Exception;
	
	List<WorksUserVO> getWorksListByUsrId(String usr_id, int start, int offset);
	
	WorksUserVO getWorksDetailsByWorksId(String works_id);
	
	List<WorksUserVO> getWorksInfoByWorksName(String works_name, int start, int offset);
	
	WorksUserVO findWorksInfoByorksName(@Param("works_name") String works_name);
	
	void updateWorksInfoByWorksId(WorksVO worksVO) throws Exception;

}
