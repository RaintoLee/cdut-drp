package com.drp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drp.models.UserVO;
import com.drp.models.WorksPageVO;
import com.drp.models.WorksUserVO;
import com.drp.models.WorksVO;

/**
 * 作品相关的DAO层接口类
 * @author curry
 *
 */

public interface WorksDAO {
	
	void insertOneWorks(@Param("worksVO") WorksVO worksVO);
	
	List<WorksUserVO> getWorksListByUsrId(@Param("worksPageVO") WorksPageVO worksPageVO);
	
	WorksUserVO getWorksDetailsByWorksId(@Param("works_id") String works_id);
	
	List<WorksUserVO> getWorksInfoByWorksName(@Param("worksPageVO") WorksPageVO worksPageVO);
	
	WorksUserVO findWorksInfoByorksName(@Param("works_name") String works_name);
	
	void updateWorksInfoByWorksId(@Param("worksVO") WorksVO worksVO);

}
