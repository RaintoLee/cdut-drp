package com.drp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drp.annotations.NoNeedLogin;
import com.drp.models.AgentOrderRecordVO;
import com.drp.models.BrowsingRecordVO;
import com.drp.models.BuyOrderRecordVO;
import com.drp.models.ChangeUserInfoVO;
import com.drp.models.ChangeWorksInfoVO;
import com.drp.models.Data;
import com.drp.models.LoadRecordVO;
import com.drp.models.LoginRecordVO;
import com.drp.models.ProtocolVO;
import com.drp.models.UserVO;
import com.drp.models.WorksUserVO;
import com.drp.models.WorksVO;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/search")
public class DECController {

	@NoNeedLogin
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("LoginRecord", "active");
		ModelAndView mav = new ModelAndView("/drc/LoginRecord", modelMap);
		return mav;
	}
	
	@RequestMapping(value = "/loginRecord", method = RequestMethod.POST)
	@NoNeedLogin
	@ResponseBody
	public Data searchLoginRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestBody LoginRecordVO loginRecordVO) throws Exception {
		Data data = new Data();
		ESRecord<LoginRecordVO> record = new ESRecord<LoginRecordVO>();
		data = record.search(loginRecordVO);
		return data;
	}
	
	@NoNeedLogin
	@RequestMapping(value = "/browsing", method = RequestMethod.GET)
	public ModelAndView browsingRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("LoginRecord", "active");
		ModelAndView mav = new ModelAndView("/drc/BrowsingRecord", modelMap);
		return mav;
	}
	
	@RequestMapping(value = "/browsingRecord", method = RequestMethod.POST)
	@NoNeedLogin
	@ResponseBody
	public Data searchBrowsingRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestBody BrowsingRecordVO browsingRecordVO) throws Exception {
		Data data = new Data();
		ESRecord<BrowsingRecordVO> record = new ESRecord<BrowsingRecordVO>();
		data = record.search(browsingRecordVO);
		return data;
	}
	
	@NoNeedLogin
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView uploadRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("LoginRecord", "active");
		ModelAndView mav = new ModelAndView("/drc/uploadRecord", modelMap);
		return mav;
	}
	
	@NoNeedLogin
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView downloadRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("LoginRecord", "active");
		ModelAndView mav = new ModelAndView("/drc/downloadRecord", modelMap);
		return mav;
	}
	
	@RequestMapping(value = "/loadRecord", method = RequestMethod.POST)
	@NoNeedLogin
	@ResponseBody
	public Data searchUploadRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestBody LoadRecordVO LoadRecordVO) throws Exception {
		Data data = new Data();
		ESRecord<LoadRecordVO> record = new ESRecord<LoadRecordVO>();
		data = record.search(LoadRecordVO);
		return data;
	}
	
	@NoNeedLogin
	@RequestMapping(value = "/agent", method = RequestMethod.GET)
	public ModelAndView agentRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("LoginRecord", "active");
		ModelAndView mav = new ModelAndView("/drc/agentRecord", modelMap);
		return mav;
	}
	
	@RequestMapping(value = "/agentRecord", method = RequestMethod.POST)
	@NoNeedLogin
	@ResponseBody
	public Data searchAgentRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestBody AgentOrderRecordVO agentOrderRecordVO) throws Exception {
		Data data = new Data();
		ESRecord<AgentOrderRecordVO> record = new ESRecord<AgentOrderRecordVO>();
		data = record.search(agentOrderRecordVO);
		return data;
	}
	
	@NoNeedLogin
	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	public ModelAndView buyRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("LoginRecord", "active");
		ModelAndView mav = new ModelAndView("/drc/buyRecord", modelMap);
		return mav;
	}
	
	@RequestMapping(value = "/buyRecord", method = RequestMethod.POST)
	@NoNeedLogin
	@ResponseBody
	public Data searchBuyRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestBody BuyOrderRecordVO buyOrderRecordVO) throws Exception {
		Data data = new Data();
		ESRecord<BuyOrderRecordVO> record = new ESRecord<BuyOrderRecordVO>();
		data = record.search(buyOrderRecordVO);
		return data;
	}
	
	@NoNeedLogin
	@RequestMapping(value = "/userModify", method = RequestMethod.GET)
	public ModelAndView userModifyRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("LoginRecord", "active");
		ModelAndView mav = new ModelAndView("/drc/userModifyRecord", modelMap);
		return mav;
	}
	
	@RequestMapping(value = "/userModifyRecord", method = RequestMethod.POST)
	@NoNeedLogin
	@ResponseBody
	public Data searchUserModifyRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestBody ChangeUserInfoVO changeUserInfoVO) throws Exception {
		Data data = new Data();
		ESRecord<ChangeUserInfoVO> record = new ESRecord<ChangeUserInfoVO>();
		data = record.search(changeUserInfoVO);
		return data;
	}
	
	@NoNeedLogin
	@RequestMapping(value = "/worksModify", method = RequestMethod.GET)
	public ModelAndView worksModifyRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("LoginRecord", "active");
		ModelAndView mav = new ModelAndView("/drc/worksModifyRecord", modelMap);
		return mav;
	}
	
	@RequestMapping(value = "/worksModifyRecord", method = RequestMethod.POST)
	@NoNeedLogin
	@ResponseBody
	public Data searchWorksModifyRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestBody ChangeWorksInfoVO changeWorksInfoVO) throws Exception {
		Data data = new Data();
		ESRecord<ChangeWorksInfoVO> record = new ESRecord<ChangeWorksInfoVO>();
		data = record.search(changeWorksInfoVO);
		return data;
	}
	
	@NoNeedLogin
	@RequestMapping(value = "/works", method = RequestMethod.GET)
	public ModelAndView works(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("LoginRecord", "active");
		ModelAndView mav = new ModelAndView("/drc/worksDetail", modelMap);
		return mav;
	}
	
	@RequestMapping(value = "/worksDetail", method = RequestMethod.POST)
	@NoNeedLogin
	@ResponseBody
	public Data searchWorks(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestBody WorksVO worksVO) throws Exception {
		Data data = new Data();
		ESRecord<WorksVO> record = new ESRecord<WorksVO>();
		data = record.search(worksVO);
		return data;
	}
	
	@NoNeedLogin
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView user(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("LoginRecord", "active");
		ModelAndView mav = new ModelAndView("/drc/userDetail", modelMap);
		return mav;
	}
	
	@RequestMapping(value = "/userDetail", method = RequestMethod.POST)
	@NoNeedLogin
	@ResponseBody
	public Data searchUser(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestBody UserVO userVO) throws Exception {
		Data data = new Data();
		ESRecord<UserVO> record = new ESRecord<UserVO>();
		data = record.search(userVO);
		return data;
	}
	
	@NoNeedLogin
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView order(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("LoginRecord", "active");
		ModelAndView mav = new ModelAndView("/drc/orderDetail", modelMap);
		return mav;
	}
	
	@RequestMapping(value = "/orderDetail", method = RequestMethod.POST)
	@NoNeedLogin
	@ResponseBody
	public Data searchOrder(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestBody ProtocolVO protocolVO) throws Exception {
		Data data = new Data();
		ESRecord<ProtocolVO> record = new ESRecord<ProtocolVO>();
		data = record.search(protocolVO);
		return data;
	}
	
	@RequestMapping(value = "/authorPage", method = RequestMethod.POST)
	@NoNeedLogin
	@ResponseBody
	public Data searchUserInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody UserVO userVO) throws Exception {
		Data data = new Data();
		Data data1 = new Data();
		ESRecord<UserVO> record = new ESRecord<UserVO>();
		data = record.search(userVO);
		data1.setDataOne(data.getDataOne());
		ESRecord<WorksUserVO> record1 = new ESRecord<WorksUserVO>();
		WorksUserVO worksUserVO = new WorksUserVO();
		worksUserVO.setUsr_name(userVO.getUsr_nm());
		data = record1.search(worksUserVO);
		data1.setDatatwo(data.getDataTwo());
		if(!"".equals(data.getMsg()) && null != data.getMsg()){
			data1.setMsg(data.getMsg());
		}
		return data1;
	}
}
