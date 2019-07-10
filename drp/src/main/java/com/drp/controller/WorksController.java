package com.drp.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.drp.models.JsonResult;
import com.drp.models.LoadRecordVO;
import com.drp.models.ProtocolVO;
import com.drp.models.WorksUserVO;
import com.drp.models.WorksVO;
import com.drp.service.DownloadService;
import com.drp.service.WorksService;
import com.drp.util.AddressByIpUtil;
import com.drp.util.FastDFSUtil;
import com.drp.util.FastDfs;
import com.drp.util.IpAddressUtil;
import com.drp.util.ReadIo2StrUtil;
import com.drp.util.TempFileDelete;
import com.drp.util.TempFileSave;
import com.drp.util.UUidUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 作品相关的controller
 * @author curry
 *
 */
@Controller
@RequestMapping("/works/")
public class WorksController {
	
	@Autowired
	private WorksService worksService;
	
	@Autowired
	private DownloadService downloadService;
	
	Logger logger = Logger.getLogger(WorksController.class);
/**************************************页面跳转开始************************************************/
	/*
	 * 到作品管理页面
	 */
	@RequestMapping(value = "myWorks", method = RequestMethod.GET)
    public ModelAndView myWorks(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        modelMap.put("myWorksActive","active");
        ModelAndView mav = new ModelAndView("/works/myWorks", modelMap);
        return mav;
    }
	
	/*
	 * 到作品注册页面
	 */
	@RequestMapping(value = "worksRegister", method = RequestMethod.GET)
    public ModelAndView workRegister(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        modelMap.put("workRegisterActive","active");
        ModelAndView mav = new ModelAndView("/works/worksRegister", modelMap);
        return mav;
    }
	
	/*
	 * 到作品详情页面
	 */
	@RequestMapping(value = "showWorksDetails", method = RequestMethod.GET)
    public ModelAndView showWorksDetails(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("myWorksActive","active");
        ModelAndView mav = new ModelAndView("/works/showWorksDetails", modelMap);
        return mav;
    }
	
	/*
	 * 到修改作品信息页面
	 */
	@RequestMapping(value = "updateWorksInfo", method = RequestMethod.GET)
    public ModelAndView updateWorksInfo(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("updateWorksInfoActive","active");
        ModelAndView mav = new ModelAndView("works/updateWorksInfo", modelMap);
        return mav;
	}
	
/**************************************作品上传/下载开始************************************************/
	/*
	 * 作品上传
	 */
	@RequestMapping(value = "worksUpload", method = RequestMethod.POST)
	public @ResponseBody JsonResult workUpload(@RequestParam MultipartFile file,
			WorksUserVO worksVO, HttpServletRequest request) throws Exception {
		logger.debug("========================================");	
		logger.debug("works_ischrg: " + worksVO.getWorks_ischrg());
		logger.debug("========================================");
		
		JsonResult jsonResult = new JsonResult();
		FastDfs fastdfs = new FastDfs();
		String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		String storageAddr = fastdfs.uploadFile(file.getBytes(),ext);
		logger.debug("========================================");
		logger.debug("该文件的存放地址是：" + storageAddr + " 上传的文件是：" + fileName + "." + ext);
		logger.debug("========================================");
		worksVO.setWorks_isvalid_flg(0);
		worksVO.setWorks_store_src(storageAddr);
		// 把session中的usr_id传给worksVO
		worksVO.setUsr_id((String) request.getSession().getAttribute("accountId"));
		worksService.insertOneWorks(worksVO, request);
		jsonResult.setResult(1);
		return jsonResult;
	}
	
	/*
     * 作品下载
     */
	@RequestMapping(value = "downLoadWorks", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downLoadWorks(String worksId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
        
    	WorksUserVO worksUserVO = worksService.getWorksDetailsByWorksId(worksId);
        String storeStr = worksUserVO.getWorks_store_src();
        String groupName = storeStr.split("/")[0];
        String remoteFileName = storeStr.substring(storeStr.indexOf("/") + 1);
        String localFileName = "drp-" + worksUserVO.getWorks_name() + "." + worksUserVO.getWorks_format();
        FastDFSUtil fastDFSUtil = new FastDFSUtil();
        String usrId = (String) request.getSession().getAttribute("accountId");
        downloadService.insertOneDownloadRecord(usrId, worksId, request);
    	return fastDFSUtil.download_bytes(groupName, remoteFileName, localFileName);
    }
    
/**************************************作品管理开始************************************************/
	/*
	 * 显示作品管理
	 */
    @RequestMapping(value = "worksListData", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject worksListData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String iDisplayStart = request.getParameter("iDisplayStart");
        String iDisplayLength = request.getParameter("iDisplayLength");
        String sEcho = request.getParameter("sEcho");
        String usrId = (String) request.getSession().getAttribute("accountId");
        int start = 0;
        int offset = 10;
        if (iDisplayStart != null && iDisplayStart.trim().length() > 0) {
            start = Integer.parseInt(iDisplayStart);
        }
        if (iDisplayLength != null && iDisplayLength.trim().length() > 0) {
            offset = Integer.parseInt(iDisplayLength);
        }
        //首先查询总记录条数
        List<WorksUserVO> allVOs = worksService.getWorksListByUsrId(usrId, 0, 9999);
        //然后根据分页值，查询所请求的页的记录
        JSONObject data = new JSONObject();
        JSONArray jarray = new JSONArray();
        List<WorksUserVO> vosList = worksService.getWorksListByUsrId(usrId, start, offset);
        for (int i = 0; i < vosList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            WorksUserVO worksUserVO = vosList.get(i);
            jsonObject.put("works_name", worksUserVO.getWorks_name());
            jsonObject.put("usr_nm", worksUserVO.getUserVO().getUsr_nm());
            jsonObject.put("works_cate", worksUserVO.getWorks_cate());
            jsonObject.put("works_pageviews", worksUserVO.getWorks_pageviews() == null ? "" : worksUserVO.getWorks_pageviews());
            jsonObject.put("works_spec", worksUserVO.getWorks_spec());
            jsonObject.put("works_id", worksUserVO.getWorks_id());
            jsonObject.put("works_isvalid_flg", worksUserVO.getWorks_isvalid_flg());
            jsonObject.put("works_sym_key", worksUserVO.getWorks_sym_key());
            jsonObject.put("userPublicKey", worksUserVO.getWorks_sym_key());
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

    /*
	 * 返回作品详情
	 */
	@RequestMapping(value = "worksDetails", method = RequestMethod.POST)
	public @ResponseBody JSONObject worksDetails(String works_id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        WorksUserVO worksUser = worksService.getWorksDetailsByWorksId(works_id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String rgstTime = null;
        String issuTime = null;
        if (worksUser.getWorks_rgst_tm() != null) {
        	rgstTime = sdf.format(worksUser.getWorks_rgst_tm());
        }
        if (worksUser.getWorks_issu_tm() != null) {
        	issuTime = sdf.format(worksUser.getWorks_issu_tm());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("works_name", worksUser.getWorks_name());
        jsonObject.put("usr_nm", worksUser.getUserVO().getUsr_nm());
        jsonObject.put("works_cate", worksUser.getWorks_cate());
        jsonObject.put("works_format", worksUser.getWorks_format());
        jsonObject.put("works_spec", worksUser.getWorks_spec());
        jsonObject.put("works_theme", worksUser.getWorks_theme());
        jsonObject.put("works_memo", worksUser.getWorks_memo());
        jsonObject.put("works_hdgt", worksUser.getWorks_hdgt());
        jsonObject.put("works_rgst_tm", rgstTime);
        jsonObject.put("works_issu_tm", issuTime);
        jsonObject.put("works_pageviews", worksUser.getWorks_pageviews());
        jsonObject.put("works_secr_lvl", worksUser.getWorks_secr_lvl());
        return jsonObject;
	}

    /*
	 * 显示作品基本资料
	 */
	@RequestMapping(value = "worksLists", method = RequestMethod.POST)
	public @ResponseBody JSONObject worksLists(String works_id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        WorksVO worksVO = worksService.getWorksDetailsByWorksId(works_id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("works_name", worksVO.getWorks_name());
        jsonObject.put("works_memo", worksVO.getWorks_memo());
        jsonObject.put("works_ischrg", worksVO.getWorks_ischrg());
        jsonObject.put("works_hdgt", worksVO.getWorks_hdgt());
        jsonObject.put("works_secr_lvl", worksVO.getWorks_secr_lvl());
        return jsonObject;
	}
	
	/*
	 *更新作品基本信息到数据库中
	 */
	@RequestMapping(value = "updateWorksDetails", method = RequestMethod.POST)
	public @ResponseBody JsonResult updateWorksDetails(WorksVO worksVO, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		JsonResult jsonResult = new JsonResult();
		worksService.updateWorksInfoByWorksId(worksVO);
		jsonResult.setResult(1);
		return jsonResult;
	}
	
}
