package com.drp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drp.models.DownloadRecordVO;
import com.drp.service.DownloadService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 下载记录的Controller
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/downloads/")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    Logger logger = Logger.getLogger(BasicController.class);
    
    
    /*
     * 到下载记录界面
     */
    @RequestMapping(value = "downloadsList", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        modelMap.put("downloadsListActive","active");
        ModelAndView mav = new ModelAndView("/downloads/downloadsLists", modelMap);
        return mav;
    }

    /*
     * 获取当前用户的下载记录
     */
    @RequestMapping(value = "downloadsListData", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject hotelsListData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String iDisplayStart = request.getParameter("iDisplayStart");
        String iDisplayLength = request.getParameter("iDisplayLength");
        String sEcho = request.getParameter("sEcho");
        int start = 0;
        int offset = 10;
        if (iDisplayStart != null && iDisplayStart.trim().length() > 0) {
            start = Integer.parseInt(iDisplayStart);
        }

        if (iDisplayLength != null && iDisplayLength.trim().length() > 0) {
            offset = Integer.parseInt(iDisplayLength);
        }
        
        String usrId = (String) request.getSession().getAttribute("accountId");
        //首先查询总记录条数
        List<DownloadRecordVO> allVOs = downloadService.getDownloadRecordByUsrId(usrId, 0, 9999);  

        //然后根据分页值，查询所请求的页的记录
        JSONObject data = new JSONObject();
        JSONArray jarray = new JSONArray();
        List<DownloadRecordVO> vosList = downloadService.getDownloadRecordByUsrId(usrId, start, offset);


        for (int i = 0; i < vosList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            DownloadRecordVO downloadRecordVO = vosList.get(i);
            String downloadTime = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (downloadRecordVO.getDownload_time() != null) {
            	downloadTime = simpleDateFormat.format(downloadRecordVO.getDownload_time());
            }
            jsonObject.put("works_name", downloadRecordVO.getWorksUserVO().getWorks_name());
            jsonObject.put("usr_nm", downloadRecordVO.getWorksUserVO().getUserVO().getUsr_nm());
            jsonObject.put("works_cate", downloadRecordVO.getWorksUserVO().getWorks_cate());
            jsonObject.put("works_hdgt", downloadRecordVO.getWorksUserVO().getWorks_hdgt());
            jsonObject.put("works_spec", downloadRecordVO.getWorksUserVO().getWorks_spec());
            jsonObject.put("works_pageviews", downloadRecordVO.getWorksUserVO().getWorks_pageviews() == null ? "" : downloadRecordVO.getWorksUserVO().getWorks_pageviews());
            jsonObject.put("download_time", downloadTime);

            jarray.add(jsonObject);
        }

        data.put("sEcho", sEcho);

        //iTotalRecords 字段是本次查询所获得的记录条数，或者说，当前页面能够显示的记录条数
        data.put("iTotalRecords", 10);

        //iTotalDisplayRecords 字段是总记录条数，前端js要用于分页
        data.put("iTotalDisplayRecords", allVOs.size());
        data.put("aaData", jarray);
        return data;
    }
   
}