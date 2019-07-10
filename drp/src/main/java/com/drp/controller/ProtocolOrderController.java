package com.drp.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drp.annotations.NoNeedLogin;
import com.drp.models.JsonResult;
import com.drp.models.ProtocolVO;
import com.drp.models.UserVO;
import com.drp.models.WorksUserVO;
import com.drp.models.WorksVO;
import com.drp.service.DownloadService;
import com.drp.service.ProtocolService;
import com.drp.service.UserService;
import com.drp.service.WorksService;
import com.drp.util.DownloadByUrlUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 订单相关的controller
 * 
 * @author curry
 *
 */
@Controller
@RequestMapping("/order/")
public class ProtocolOrderController {

	@Autowired
	private WorksService worksService;

	@Autowired
	private ProtocolService protocolService;
	@Autowired
	private DownloadService downloadService;
	@Autowired
	private UserService userService;

	Logger logger = Logger.getLogger(ProtocolOrderController.class);

/************************************* 页面跳转开始 ****************************************************************/
	/*
	 * 1.跳转订单填写页面
	 */
	@NoNeedLogin
	@RequestMapping(value = "orderFillingOfAgent", method = RequestMethod.GET)
	public ModelAndView orderFillingOfAgent(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		modelMap.put("orderFillingOfAgentActive", "active");
		ModelAndView mav = new ModelAndView("/order/orderFillingOfAgent", modelMap);
		return mav;
	}

	/*
	 * 5.跳转代理商申请代理状态页面
	 */
	@RequestMapping(value = "orderStatuOfAgent", method = RequestMethod.GET)
	public ModelAndView orderStatuOfAgent(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("orderStatuOfAgentActive", "active");
		ModelAndView mav = new ModelAndView("/order/orderStatuOfAgent", modelMap);
		return mav;
	}

	/*
	 * 25.跳转作者-作品的代理订单页面
	 * 
	 */
	@RequestMapping(value = "orderStatuOfAgentByAuthor", method = RequestMethod.GET)
	public ModelAndView orderStatuOfAgentByAuthor(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		modelMap.put("orderStatuOfAgentByAuthorActive", "active");
		ModelAndView mav = new ModelAndView("/order/orderStatuOfAgentByAuthor", modelMap);
		return mav;
	}

	/*
	 * 26.跳转作者-邀请代理交易状态页面
	 * 
	 */
	@RequestMapping(value = "orderStatuOfMyVisit", method = RequestMethod.GET)
	public ModelAndView orderStatuOfMyVisit(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		modelMap.put("oorderStatuOfMyVisitActive", "active");
		ModelAndView mav = new ModelAndView("/order/orderStatuOfMyVisit", modelMap);
		return mav;
	}

	/*
	 * 27.跳转邀请订单填写页面
	 * 
	 */
	@RequestMapping(value = "agentVisit", method = RequestMethod.GET)
	public ModelAndView agentVisit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("agentVisitActive", "active");
		ModelAndView mav = new ModelAndView("/order/agentVisit", modelMap);
		return mav;
	}

	/*
	 * 28.跳转邀请订单填写页面
	 * 
	 */
	@RequestMapping(value = "orderFillingOfVisit", method = RequestMethod.GET)
	public ModelAndView orderFillingOfVisit(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		modelMap.put("orderFillingOfVisitActive", "active");
		ModelAndView mav = new ModelAndView("/order/orderFillingOfVisit", modelMap);
		return mav;
	}

	/*
	 * 9.跳转作者作品代理列表页面
	 */
	@RequestMapping(value = "orderListOfAgentByAuthor", method = RequestMethod.GET)
	public ModelAndView orderListOfAgentByAuthor(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		modelMap.put("orderListOfAgentByAuthorActive", "active");
		ModelAndView mav = new ModelAndView("/order/orderListOfAgentByAuthor", modelMap);
		return mav;
	}

	/*
	 * 11.跳转普通用户作品购买提交订单页面
	 */
	@RequestMapping(value = "orderFillingOfBuy", method = RequestMethod.GET)
	public ModelAndView orderFillingOfBuy(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("orderFillingOfBuyActive", "active");
		ModelAndView mav = new ModelAndView("/order/orderFillingOfBuy", modelMap);
		return mav;
	}

	/*
	 * 12.跳转普通用户订单状态页面
	 */
	@RequestMapping(value = "orderStatuOfBuy", method = RequestMethod.GET)
	public ModelAndView orderStatuOfBuy(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("orderStatuOfBuyActive", "active");
		ModelAndView mav = new ModelAndView("/order/orderStatuOfBuy", modelMap);
		return mav;
	}

	/*
	 * 15.跳转我的代理
	 */
	@RequestMapping(value = "myAgent", method = RequestMethod.GET)
	public ModelAndView myAgent(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("myAgentActive", "active");
		ModelAndView mav = new ModelAndView("/order/myAgent", modelMap);
		return mav;
	}

	/*
	 * 17.跳转我的订单
	 * 
	 */
	@RequestMapping(value = "myOrder", method = RequestMethod.GET)
	public ModelAndView myOrder(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("myOrderActive", "active");
		ModelAndView mav = new ModelAndView("/order/myOrder", modelMap);
		return mav;
	}

	/*
	 * 19.跳转作品的购买订单页面
	 */
	@RequestMapping(value = "orderListByAuthorAboutWorks", method = RequestMethod.GET)
	public ModelAndView orderListByAuthorAboutWorks(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		modelMap.put("orderListByAuthorAboutWorksActive", "active");
		ModelAndView mav = new ModelAndView("/order/orderListByAuthorAboutWorks", modelMap);
		return mav;
	}

	/*
	 * 22.跳转作品的购买订单页面
	 * 
	 */
	@RequestMapping(value = "orderStatuOfBuyByAuthor", method = RequestMethod.GET)
	public ModelAndView orderStatuOfBuyByAuthor(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		modelMap.put("orderStatuOfBuyByAuthorActive", "active");
		ModelAndView mav = new ModelAndView("/order/orderStatuOfBuyByAuthor", modelMap);
		return mav;
	}

	/*
	 * 1.返回作品详情
	 */
	@RequestMapping(value = "findWorksInfo", method = RequestMethod.POST)
	public @ResponseBody JSONObject findWorksInfo(String works_id, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		WorksVO worksVO = worksService.getWorksDetailsByWorksId(works_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("usr_id", worksVO.getUsr_id());
		jsonObject.put("works_id", worksVO.getWorks_id());
		jsonObject.put("works_name", worksVO.getWorks_name());
		jsonObject.put("works_hdgt", worksVO.getWorks_hdgt());
		jsonObject.put("works_spec", worksVO.getWorks_spec());
		jsonObject.put("works_format", worksVO.getWorks_format());
		jsonObject.put("works_cate", worksVO.getWorks_cate());
		jsonObject.put("works_memo", worksVO.getWorks_memo());
		return jsonObject;
	}

	/*
	 * 2.代理商申请代理时：向协议表插入数据
	 */
	@RequestMapping(value = "insertProtocolContent", method = RequestMethod.POST)
	public @ResponseBody JSONObject insertProtocolContent(@RequestBody ProtocolVO protocolVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		JSONObject jsonObject = new JSONObject();
		// 代理商申请时，usr_id和partb_id都是当前session
		String usrb_Id = (String) request.getSession().getAttribute("accountId");
		protocolVO.setUsr_id(usrb_Id);
		protocolVO.setPartb_id(usrb_Id);
		protocolService.insertProtocolContent(protocolVO);
		// 获取上面插入协议表数据的协议ID
		String agmt_id = protocolService.getAgmtId();
		jsonObject.put("agmt_id", agmt_id);
		return jsonObject;
	}

	/*
	 * 4.作者邀请代理时：向协议表插入数据
	 */
	@RequestMapping(value = "insertApplyProtocolContent", method = RequestMethod.POST)
	public @ResponseBody JSONObject insertApplyProtocolContent(@RequestBody ProtocolVO protocolVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		JSONObject jsonObject = new JSONObject();
		// 作者邀请时，usr_id和parta_id都是当前session
		String usra_Id = (String) request.getSession().getAttribute("accountId");
		protocolVO.setUsr_id(usra_Id);
		protocolVO.setParta_id(usra_Id);
		protocolService.insertProtocolContent(protocolVO);
		// 获取上面插入协议表数据的协议ID
		String agmt_id = protocolService.getAgmtId();
		jsonObject.put("agmt_id", agmt_id);
		return jsonObject;
	}

	/*
	 * 7.更新协议isvalid状态值
	 */
	@RequestMapping(value = "getInfoIsvalidFlg", method = RequestMethod.POST)
	public @ResponseBody JsonResult getInfoIsvalidFlg(String agmt_id, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		JsonResult jsonResult = new JsonResult();
		protocolService.getInfoIsvalidFlg(agmt_id);
		jsonResult.setResult(1);
		return jsonResult;
	}

	/*
	 * 8.更新协议表状态值
	 */
	@RequestMapping(value = "updateInfoisValidFlg", method = RequestMethod.POST)
	public @ResponseBody JsonResult updateInfoisValidFlg(@RequestBody ProtocolVO protocolVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		JsonResult jsonResult = new JsonResult();
		protocolService.updateInfoisValidFlg(protocolVO);
		jsonResult.setResult(1);
		return jsonResult;
	}

	/*
	 * 下载密钥
	 */
	@RequestMapping(value = "downloadDeskey", method = RequestMethod.POST)
	public @ResponseBody JSONObject downloadDeskey(@RequestBody ProtocolVO protocolVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		JSONObject obj = new JSONObject();
		String agm_id = protocolVO.getAgmt_id();
		ProtocolVO pt = protocolService.getProtocolDetailsByAgmtId(agm_id);
		String deskey = pt.getDeskey();
		obj.put("deskey", deskey);
		return obj;
	}

	/*
	 * 下载文件
	 */
	@RequestMapping(value = "downloadFile", method = RequestMethod.POST)
	public @ResponseBody JSONObject getFileAdd(@RequestBody ProtocolVO protocolVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		JSONObject obj = new JSONObject();
		String agmt_id = protocolVO.getAgmt_id();
		ProtocolVO pv = protocolService.getProtocolDetailsByAgmtId(agmt_id);
		String works_id = pv.getWorks_id();
		WorksVO work = worksService.getWorksDetailsByWorksId(works_id);
		downloadService.insertOneDownloadRecord((String)request.getSession().getAttribute("accountId"), work.getWorks_id(), request);
	    String photoUrl = work.getWorks_store_src();   //文件URL地址                                     
	    String fileName = work.getWorks_name();     //为下载的文件命名
	    //String filePath = this.getClass().getResource("").getPath();      //保存目录
	    FileSystemView fsv = FileSystemView.getFileSystemView();
	    String filePath=fsv.getHomeDirectory().toString(); 
	    File file = DownloadByUrlUtil.saveUrlAs(photoUrl, filePath , fileName,"GET");  
		obj.put("works_store_src", work.getWorks_store_src());
		obj.put("works_name", work.getWorks_name());
		return obj;
	}

	/*
	 * 6.根据当前协议ID查找对应协议表数据中的状态值
	 */
	@RequestMapping(value = "getProtocolDetailsByAgmtId", method = RequestMethod.POST)
	public @ResponseBody JSONObject getProtocolDetailsByAgmtId(String agmt_id, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		JSONObject jsonObject = new JSONObject();
		ProtocolVO protocolVO = protocolService.getProtocolDetailsByAgmtId(agmt_id);
		String works_id = protocolVO.getWorks_id();
		WorksUserVO worksUser = worksService.getWorksDetailsByWorksId(works_id);
		String works_name = worksUser.getWorks_name();
		jsonObject.put("works_name", works_name);
		jsonObject.put("info_isvalid_flg", protocolVO.getInfo_isvalid_flg());
		jsonObject.put("usr_nm", worksUser.getUserVO().getUsr_nm());
		jsonObject.put("protocol_deadline", protocolVO.getProtocol_deadline());
		jsonObject.put("partb_patio", protocolVO.getPartb_patio());
		jsonObject.put("receive_flg", protocolVO.getReceive_flg());
		return jsonObject;
	}

	/*
	 * 10.根据当前works_id查找我的作品的代理详情
	 */
	@RequestMapping(value = "getProtocolDetailByWorksId", method = RequestMethod.POST)
	public @ResponseBody JSONObject getProtocolDetailByWorksId(@RequestBody ProtocolVO protocolVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		// 当前操作的是作者，作者是parta
		String parta_Id = (String) request.getSession().getAttribute("accountId");
		protocolVO.setParta_id(parta_Id);
		// 第一步：根据works_id获取协议表信息
		List<ProtocolVO> allVOs = protocolService.getProtocolDetailByWorksId(protocolVO, 0, 999);
		JSONObject data = new JSONObject();
		JSONArray jarray = new JSONArray();
		for (int i = 0; i < allVOs.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			ProtocolVO protocolVO1 = allVOs.get(i);
			jsonObject.put("agmt_id", protocolVO1.getAgmt_id());
			UserVO userVO = userService.getUserBasicInfoByUsrId(protocolVO1.getPartb_id());
			String usr_nm = userVO.getUsr_nm();
			jsonObject.put("usr_nm", usr_nm);
			jsonObject.put("parta_patio", protocolVO1.getParta_patio());
			jsonObject.put("partb_patio", protocolVO1.getPartb_patio());
			jsonObject.put("info_isvalid_flg", protocolVO1.getInfo_isvalid_flg());
			jarray.add(jsonObject);
		}
		data.put("aaData", jarray);
		return data;
	}

	/*
	 * 13.授权功能：向协议表插入授权数据
	 */
	@RequestMapping(value = "insertImpowerProtocolContent", method = RequestMethod.POST)
	public @ResponseBody JSONObject insertImpowerProtocolContent(@RequestBody ProtocolVO protocolVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		JSONObject jsonObject = new JSONObject();
		String usra_Id = (String) request.getSession().getAttribute("accountId");
		// 授权时，甲方为普通用户
		protocolVO.setParta_id(usra_Id);
		protocolVO.setUsr_id(usra_Id);
		protocolService.insertImpowerProtocolContent(protocolVO);
		// 获取上面插入协议表数据的协议ID
		String agmt_id = protocolService.getAgmtId();
		jsonObject.put("agmt_id", agmt_id);
		return jsonObject;
	}

	/*
	 * 14.代理商:显示我的代理列表
	 */
	@RequestMapping(value = "MyAgentListData", method = RequestMethod.POST)
	public @ResponseBody JSONObject MyAgentListData(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
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
		// 首先查询总记录条数
		List<ProtocolVO> allVOs = protocolService.getMyAgentList(usrId, 0, 9999);
		// 然后根据分页值，查询所请求的页的记录
		JSONObject data = new JSONObject();
		JSONArray jarray = new JSONArray();
		List<ProtocolVO> vosList = protocolService.getMyAgentList(usrId, start, offset);
		for (int i = 0; i < vosList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			ProtocolVO protocolVO = vosList.get(i);
			WorksVO worksVO = worksService.getWorksDetailsByWorksId(protocolVO.getWorks_id());
			String works_name = worksVO.getWorks_name();
			String works_cate = worksVO.getWorks_cate();
			UserVO userVO = userService.getUserBasicInfoByUsrId(worksVO.getUsr_id());
			String usr_nm = userVO.getUsr_nm();
			jsonObject.put("works_name", works_name);
			jsonObject.put("usr_nm", usr_nm);
			jsonObject.put("works_cate", works_cate);
			jsonObject.put("agmt_id", protocolVO.getAgmt_id());
			jsonObject.put("partb_patio", protocolVO.getPartb_patio());
			jsonObject.put("info_isvalid_flg", protocolVO.getInfo_isvalid_flg());
			jarray.add(jsonObject);
		}
		data.put("sEcho", sEcho);
		// iTotalRecords 字段是本次查询所获得的记录条数，或者说，当前页面能够显示的记录条数
		data.put("iTotalRecords", 10);
		// iTotalDisplayRecords 字段是总记录条数，前端js要用于分页
		data.put("iTotalDisplayRecords", allVOs.size());
		data.put("aaData", jarray);
		return data;
	}

	/*
	 * 14（2）.代理商:显示来自作者的代理邀请
	 */
	@RequestMapping(value = "receiveAgentListData", method = RequestMethod.POST)
	public @ResponseBody JSONObject receiveAgentListData(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
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
		// 首先查询总记录条数
		List<ProtocolVO> allVOs = protocolService.getReceiveAgentListData(usrId, 0, 9999);
		// 然后根据分页值，查询所请求的页的记录
		JSONObject data = new JSONObject();
		JSONArray jarray = new JSONArray();
		List<ProtocolVO> vosList = protocolService.getReceiveAgentListData(usrId, start, offset);
		for (int i = 0; i < vosList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			ProtocolVO protocolVO = vosList.get(i);
			WorksVO worksVO = worksService.getWorksDetailsByWorksId(protocolVO.getWorks_id());
			String works_name = worksVO.getWorks_name();
			String works_cate = worksVO.getWorks_cate();
			UserVO userVO = userService.getUserBasicInfoByUsrId(worksVO.getUsr_id());
			String usr_nm = userVO.getUsr_nm();
			jsonObject.put("works_name", works_name);
			jsonObject.put("usr_nm", usr_nm);
			jsonObject.put("works_cate", works_cate);
			jsonObject.put("agmt_id", protocolVO.getAgmt_id());
			jsonObject.put("partb_patio", protocolVO.getPartb_patio());
			jsonObject.put("info_isvalid_flg", protocolVO.getInfo_isvalid_flg());
			jarray.add(jsonObject);
		}
		data.put("sEcho", sEcho);
		// iTotalRecords 字段是本次查询所获得的记录条数，或者说，当前页面能够显示的记录条数
		data.put("iTotalRecords", 10);
		// iTotalDisplayRecords 字段是总记录条数，前端js要用于分页
		data.put("iTotalDisplayRecords", allVOs.size());
		data.put("aaData", jarray);
		return data;
	}

	/*
	 * 16.普通用户：显示我的订单
	 */
	@RequestMapping(value = "MyOrderListData", method = RequestMethod.POST)
	public @ResponseBody JSONObject MyOrderListData(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
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
		// 首先查询总记录条数
		List<ProtocolVO> allVOs = protocolService.getMyOrderListData(usrId, 0, 9999);
		// 然后根据分页值，查询所请求的页的记录
		JSONObject data = new JSONObject();
		JSONArray jarray = new JSONArray();
		List<ProtocolVO> vosList = protocolService.getMyOrderListData(usrId, start, offset);
		for (int i = 0; i < vosList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			ProtocolVO protocolVO = vosList.get(i);
			String part_a_id = protocolVO.getParta_id();
			UserVO part_a = userService.getUserBasicInfoByUsrId(part_a_id);
			String part_b_id = protocolVO.getPartb_id();
			UserVO part_b = userService.getUserBasicInfoByUsrId(part_b_id);
			WorksVO worksVO = worksService.getWorksDetailsByWorksId(protocolVO.getWorks_id());
			String works_name = worksVO.getWorks_name();
			String works_cate = worksVO.getWorks_cate();
			UserVO userVO = userService.getUserBasicInfoByUsrId(worksVO.getUsr_id());
			String usr_nm = userVO.getUsr_nm();
			jsonObject.put("works_name", works_name);
			jsonObject.put("part_b", part_b.getUsr_nm());
			jsonObject.put("part_a", part_a.getUsr_nm());
			jsonObject.put("agmt_id", protocolVO.getAgmt_id());
			jsonObject.put("receive_flg", protocolVO.getReceive_flg());
			jsonObject.put("partb_patio", protocolVO.getPartb_patio());
			jsonObject.put("info_isvalid_flg", protocolVO.getInfo_isvalid_flg());
			jsonObject.put("auth_fee", protocolVO.getAuth_fee());
			jsonObject.put("protocol_deadline", protocolVO.getProtocol_deadline());
			jarray.add(jsonObject);
		}
		data.put("sEcho", sEcho);
		// iTotalRecords 字段是本次查询所获得的记录条数，或者说，当前页面能够显示的记录条数
		data.put("iTotalRecords", 10);
		// iTotalDisplayRecords 字段是总记录条数，前端js要用于分页
		data.put("iTotalDisplayRecords", allVOs.size());
		data.put("aaData", jarray);
		return data;
	}

	/*
	 * 18.更新协议receive_flg状态值
	 */
	@RequestMapping(value = "updateReceiveFlg", method = RequestMethod.POST)
	public @ResponseBody JsonResult updateReceiveFlg(String agmt_id, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		JsonResult jsonResult = new JsonResult();
		protocolService.updateReceiveFlg(agmt_id);
		jsonResult.setResult(1);
		return jsonResult;
	}

	/*
	 * 20.test:代理列表
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public @ResponseBody JSONObject list(String works_id, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("works_id", works_id);
		return jsonObject;
	}

	/*
	 * 21.普通用户：显示作品订单
	 */
	@RequestMapping(value = "getWorksOrderList", method = RequestMethod.POST)
	public @ResponseBody JSONObject getWorksOrderList(String works_id, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
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
		// 首先查询总记录条数
		List<ProtocolVO> allVOs = protocolService.getWorksOrderList(works_id, 0, 9999);
		// 然后根据分页值，查询所请求的页的记录
		JSONObject data = new JSONObject();
		JSONArray jarray = new JSONArray();
		List<ProtocolVO> vosList = protocolService.getWorksOrderList(works_id, start, offset);
		for (int i = 0; i < vosList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			ProtocolVO protocolVO = vosList.get(i);
			UserVO userVO = userService.getUserBasicInfoByUsrId(protocolVO.getUsr_id());
			String usr_nm = userVO.getUsr_nm();
			jsonObject.put("usr_nm", usr_nm);
			jsonObject.put("agmt_id", protocolVO.getAgmt_id());
			jsonObject.put("info_isvalid_flg", protocolVO.getInfo_isvalid_flg());
			jsonObject.put("auth_fee", protocolVO.getAuth_fee());
			jsonObject.put("protocol_deadline", protocolVO.getProtocol_deadline());
			jsonObject.put("UserPK", userVO.getUsr_pub_key());
			jarray.add(jsonObject);
		}
		WorksVO worksVO = worksService.getWorksDetailsByWorksId(works_id);
		String works_name = worksVO.getWorks_name();
		data.put("works_name", works_name);
		data.put("sEcho", sEcho);
		// iTotalRecords 字段是本次查询所获得的记录条数，或者说，当前页面能够显示的记录条数
		data.put("iTotalRecords", 10);
		// iTotalDisplayRecords 字段是总记录条数，前端js要用于分页
		data.put("iTotalDisplayRecords", allVOs.size());
		data.put("aaData", jarray);
		return data;
	}

	/*
	 * 23.作者用户：关于此作品我邀请的代理
	 */
	@RequestMapping(value = "getAgentListFromMeByWorksId", method = RequestMethod.POST)
	public @ResponseBody JSONObject getAgentListFormMeByWorksId(String works_id, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
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
		// 我邀请的代理,所以usrId=session
		String usrId = (String) request.getSession().getAttribute("accountId");
		// 首先查询总记录条数
		List<ProtocolVO> allVOs = protocolService.getAgentListFromMeByWorksId(usrId, works_id, 0, 9999);
		// 然后根据分页值，查询所请求的页的记录
		JSONObject data = new JSONObject();
		JSONArray jarray = new JSONArray();
		List<ProtocolVO> vosList = protocolService.getAgentListFromMeByWorksId(usrId, works_id, start, offset);
		for (int i = 0; i < vosList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			ProtocolVO protocolVO = vosList.get(i);
			UserVO userVO = userService.getUserBasicInfoByUsrId(protocolVO.getPartb_id());
			String usr_nm = userVO.getUsr_nm();
			jsonObject.put("usr_nm", usr_nm);
			jsonObject.put("agmt_id", protocolVO.getAgmt_id());
			jsonObject.put("info_isvalid_flg", protocolVO.getInfo_isvalid_flg());
			jsonObject.put("parta_patio", protocolVO.getParta_patio());
			jsonObject.put("protocol_deadline", protocolVO.getProtocol_deadline());
			jarray.add(jsonObject);
		}
		WorksVO worksVO = worksService.getWorksDetailsByWorksId(works_id);
		String works_name = worksVO.getWorks_name();
		data.put("works_name", works_name);
		data.put("sEcho", sEcho);
		// iTotalRecords 字段是本次查询所获得的记录条数，或者说，当前页面能够显示的记录条数
		data.put("iTotalRecords", 10);
		// iTotalDisplayRecords 字段是总记录条数，前端js要用于分页
		data.put("iTotalDisplayRecords", allVOs.size());
		data.put("aaData", jarray);
		return data;
	}

	/*
	 * 24.作者用户：关于此作品收到的代理申请
	 */
	@RequestMapping(value = "getAgentListFromAgentByWorksId", method = RequestMethod.POST)
	public @ResponseBody JSONObject getAgentListFromAgentByWorksId(String works_id, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
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
		// 我邀请的代理,所以usrId != session
		String usrId = (String) request.getSession().getAttribute("accountId");
		// 首先查询总记录条数
		List<ProtocolVO> allVOs = protocolService.getAgentListFromAgentByWorksId(usrId, works_id, 0, 9999);
		// 然后根据分页值，查询所请求的页的记录
		JSONObject data = new JSONObject();
		JSONArray jarray = new JSONArray();
		List<ProtocolVO> vosList = protocolService.getAgentListFromAgentByWorksId(usrId, works_id, start, offset);
		for (int i = 0; i < vosList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			ProtocolVO protocolVO = vosList.get(i);
			UserVO userVO = userService.getUserBasicInfoByUsrId(protocolVO.getPartb_id());
			String usr_nm = userVO.getUsr_nm();
			jsonObject.put("usr_nm", usr_nm);
			jsonObject.put("agmt_id", protocolVO.getAgmt_id());
			jsonObject.put("info_isvalid_flg", protocolVO.getInfo_isvalid_flg());
			jsonObject.put("parta_patio", protocolVO.getParta_patio());
			jsonObject.put("protocol_deadline", protocolVO.getProtocol_deadline());
			jarray.add(jsonObject);
		}
		WorksVO worksVO = worksService.getWorksDetailsByWorksId(works_id);
		String works_name = worksVO.getWorks_name();
		data.put("works_name", works_name);
		data.put("sEcho", sEcho);
		// iTotalRecords 字段是本次查询所获得的记录条数，或者说，当前页面能够显示的记录条数
		data.put("iTotalRecords", 10);
		// iTotalDisplayRecords 字段是总记录条数，前端js要用于分页
		data.put("iTotalDisplayRecords", allVOs.size());
		data.put("aaData", jarray);
		return data;
	}

	/*
	 * 29.读取作品、代理商信息
	 */
	@RequestMapping(value = "findWorksAgentInfo", method = RequestMethod.POST)
	public @ResponseBody JSONObject findWorksAgentInfo(@RequestBody WorksVO worksVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		WorksUserVO worksUser = worksService.getWorksDetailsByWorksId(worksVO.getWorks_id());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("works_name", worksUser.getWorks_name());
		jsonObject.put("works_hdgt", worksUser.getWorks_hdgt());
		jsonObject.put("usr_id", worksUser.getUsr_id());
		UserVO user = userService.getUserBasicInfoByUsrId(worksVO.getUsr_id());
		jsonObject.put("usr_nm", user.getUsr_nm());
		return jsonObject;
	}

	/*
	 * 30.读取代理商列表
	 */
	@RequestMapping(value = "agentList", method = RequestMethod.POST)
	public @ResponseBody JSONObject getAgentList(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
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
		// 首先查询总记录条数
		List<String> allVOs = protocolService.getAgentList(0, 9999);
		// 然后根据分页值，查询所请求的页的记录
		JSONObject data = new JSONObject();
		JSONArray jarray = new JSONArray();
		List<String> vosList = protocolService.getAgentList(start, offset);
		for (int i = 0; i < vosList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			String usr_id = vosList.get(i);
			UserVO userVO = userService.getUserBasicInfoByUsrId(usr_id);
			String usr_nm = userVO.getUsr_nm();
			jsonObject.put("usr_nm", usr_nm);
			jsonObject.put("usr_id", usr_id);
			data.put("iTotalRecords", 10);
			jarray.add(jsonObject);
		}
		data.put("sEcho", sEcho);
		// iTotalRecords 字段是本次查询所获得的记录条数，或者说，当前页面能够显示的记录条数
		data.put("iTotalRecords", 10);
		// iTotalDisplayRecords 字段是总记录条数，前端js要用于分页
		data.put("iTotalDisplayRecords", allVOs.size());
		data.put("aaData", jarray);
		return data;
	}

	/*
	 * 31.作者上传修改后的作品密钥
	 * 
	 */
	@RequestMapping(value = "uploadkey", method = RequestMethod.POST)
	public @ResponseBody JsonResult uploadkey(@RequestBody ProtocolVO protocolVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		JsonResult rest = new JsonResult();
		String agmt_id = protocolVO.getAgmt_id();
		String deskey = protocolVO.getDeskey();
		protocolService.updateDeskey(agmt_id, deskey);
		rest.setResult(1);
		return rest;
	}
}
