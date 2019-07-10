package com.drp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.drp.annotations.NoNeedLogin;
import com.drp.models.ProtocolVO;
import com.drp.models.UserVO;
import com.drp.models.WorksVO;
import com.drp.service.ProtocolService;
import com.drp.service.UserService;
import com.drp.service.WorksService;

import net.sf.json.JSONObject;
/**
 * 协议相关的controller
 * @author curry
 *
 */
@Controller
@RequestMapping("/protocol/")
public class ProtocolController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProtocolService protocolService;
	
	Logger logger = Logger.getLogger(ProtocolController.class);

	/**
	 * 跳转平台协议页面
	 * 
	 */
	@NoNeedLogin
	@RequestMapping(value = "protocolPlatform", method = RequestMethod.GET)
	public ModelAndView protocolPlatform(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("protocolPlatformActive", "active");
		ModelAndView mav = new ModelAndView("/protocol/protocolPlatform", modelMap);
		return mav;
	}
	
	
	 /**
	  * 跳转到查看协议1
	  */
	@RequestMapping(value = "checkProtocol", method = RequestMethod.GET)
	public ModelAndView checkProtocol(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	      modelMap.put("checkProtocolActive","active");
	      ModelAndView mav = new ModelAndView("/protocol/checkProtocol", modelMap);
	      return mav;
	}
	
	 /**
	  * 跳转到查看协议2
	  */
	@RequestMapping(value = "checkProtocol2", method = RequestMethod.GET)
	public ModelAndView checkProtocol2(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	      modelMap.put("checkProtocol2Active","active");
	      ModelAndView mav = new ModelAndView("/protocol/checkProtocol2", modelMap);
	      return mav;
	}
	
	/**
	 * 跳转到代理协议页面
	 * 
	 */
	@NoNeedLogin
	@RequestMapping(value = "protocolAgent", method = RequestMethod.GET)
	public ModelAndView protocolAgent(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("protocolAgentActive", "active");
		ModelAndView mav = new ModelAndView("/protocol/protocolAgent", modelMap);
		return mav;
	}
	
	 /**
	  * 跳转普通用户授权协议页面
	  * 
	  */
	@RequestMapping(value = "protocolImpower", method = RequestMethod.GET)
	public ModelAndView protocolImpower(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	      modelMap.put("protocolImpowerActive","active");
	      ModelAndView mav = new ModelAndView("/protocol/protocolImpower", modelMap);
	      return mav;
	}

	/*
	 * 通过agmt_id返回甲方和乙方的名字
	 */
	@RequestMapping(value = "getProtocolContentNameByAgmtId", method = RequestMethod.POST)
	public @ResponseBody JSONObject getProtocolContentNameByAgmtId(String agmt_id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        
		ProtocolVO protocolVO = protocolService.getProtocolDetailsByAgmtId(agmt_id);
		UserVO userVO1 = userService.getUserBasicInfoByUsrId(protocolVO.getParta_id());
		UserVO userVO2 = userService.getUserBasicInfoByUsrId(protocolVO.getPartb_id());
        JSONObject jsonObject = new JSONObject();
        //甲方姓名
        jsonObject.put("usra_name", userVO1.getUsr_nm());
        //乙方姓名
        jsonObject.put("usrb_name", userVO2.getUsr_nm());
        //usr_id
        jsonObject.put("usr_id", protocolVO.getUsr_id());
        
        String session =  (String) request.getSession().getAttribute("accountId");
        jsonObject.put("session", session);
        return jsonObject;
	}
}
