package com.drp.util;

import com.drp.controller.ESRecord4es;
import com.drp.models.WorksUserVO;

public class UpdatePageviews {

	public static void updatePageviews(WorksUserVO worksUserVO) throws Exception{
		Integer n = 0;
		String pageviews = worksUserVO.getWorks_pageviews();
		n.parseInt(pageviews);
		n = n + 1 ;
		worksUserVO.setWorks_pageviews(n.toString());
		ESRecord4es<WorksUserVO> crud = new ESRecord4es<WorksUserVO>();
		crud.updatePageviews(worksUserVO);
	}
}
