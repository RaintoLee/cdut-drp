package com.drp.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drp.annotations.NoNeedLogin;
import com.drp.models.*;
import com.drp.service.BrowsingService;
import com.drp.service.UserService;
import com.drp.service.WorksService;
import com.drp.util.AddressByIpUtil;
import com.drp.util.IpAddressUtil;
import com.drp.util.RandomUtil;
import com.drp.util.SendEmailUtil;
import com.drp.util.UUidUtil;
import com.drp.util.UpdatePageviews;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 人员相关的controller类
 * 1、页面跳转
 * 2、加载菜单
 * 3、用户注册
 * 4、用户登录
 * 5、用户信息修改
 * 6、作品展示
 * @author curry
 *
 */
@Controller
@RequestMapping("/")
public class BasicController {

	@Autowired
	private UserService userService;
	@Autowired
	private WorksService worksService;
	@Autowired
	private BrowsingService browsingService;
	
	Logger logger = Logger.getLogger(BasicController.class);
	
/*********************************************************************************************************************************/	
/*********************************************************************************************************************************/
/*******************************************************页面跳转开始*****************************************************************/
/*********************************************************************************************************************************/	
/*********************************************************************************************************************************/
	/*
	 * 到主界面
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("indexActive", "active");
		ModelAndView mav = new ModelAndView("index", modelMap);
		return mav;
	}

	/*
	 * 到搜索信息界面
	 */
	@RequestMapping(value = "recommendInfo", method = RequestMethod.GET)
	public ModelAndView recommendInfo(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, WorksUserVO worksUserVO) {
		request.getSession().setAttribute("msg", worksUserVO.getMsg());
		modelMap.put("indexActive", "active");
		ModelAndView mav = new ModelAndView("recommendInfo", modelMap);
		return mav;
	}
	
	/*
	 * 到作品的详情界面
	 */
	@RequestMapping(value = "recommendWorksDetails", method = RequestMethod.GET)
	public ModelAndView recommendWorksDetails(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		modelMap.put("indexActive", "active");
		ModelAndView mav = new ModelAndView("recommendWorksDetails", modelMap);
		return mav;
	}
	
	/*
	 * 退出登录
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		request.getSession().removeAttribute("accountId");
		ModelAndView mav = new ModelAndView("login", modelMap);
		return mav;
	}

	/*
	 * 到登录界面
	 */
	@NoNeedLogin
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("login", modelMap);
		return mav;
	}

	/*
	 * 到注册界面
	 */
	@NoNeedLogin
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("register", modelMap);
		return mav;
	}
	
	/*
	 * 到基本资料界面
	 */
	@RequestMapping(value = "userBasicInfo", method = RequestMethod.GET)
	public ModelAndView userBasicInfo(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("userBasicInfoActive", "active");
		ModelAndView mav = new ModelAndView("user/userBasicInfo", modelMap);
		return mav;
	}

	/*
	 * 到修改邮箱界面
	 */
	@RequestMapping(value = "modifyEmail", method = RequestMethod.GET)
	public ModelAndView modifyEmail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("detailedInformationActive", "active");
		ModelAndView mav = new ModelAndView("user/modifyEmail", modelMap);
		return mav;
	}

	/*
	 * 到修改密码界面
	 */
	@RequestMapping(value = "modifyPassword", method = RequestMethod.GET)
	public ModelAndView modifyPassword(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("detailedInformationActive", "active");
		ModelAndView mav = new ModelAndView("user/modifyPassword", modelMap);
		return mav;
	}
	
	/*
	 * 我的足迹界面
	 */
	@RequestMapping(value = "browsingHistory", method = RequestMethod.GET)
	public ModelAndView browsingHistory(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("browsingHistoryActive", "active");
		ModelAndView mav = new ModelAndView("user/browsingHistory", modelMap);
		return mav;
	}
	
    /*
	 * 添加实名认证页面
	 * 
	 */
    @RequestMapping(value = "certification", method = RequestMethod.GET)
    public ModelAndView certification(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        modelMap.put("certificationActive","active");
        ModelAndView mav = new ModelAndView("user/certification", modelMap);
        return mav;
    }
	
    /*
	 * 到角色申请页面
	 */
	@RequestMapping(value = "charactorChange", method = RequestMethod.GET)
	public ModelAndView charactorChange(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("charactorChangeActive", "active");
		ModelAndView mav = new ModelAndView("user/charactorChange", modelMap);
		return mav;
	}
	
	/*
	 * 到作者详情页面
	 */
	@RequestMapping(value = "authortheme", method = RequestMethod.GET)
	public ModelAndView Authortheme(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("charactorChangeActive", "active");
		ModelAndView mav = new ModelAndView("user/authortheme", modelMap);
		return mav;
	}
/*********************************************************************************************************************************/
/*******************************************************加载菜单开始*****************************************************************/
/*********************************************************************************************************************************/	
	/*
	 * 加载菜单
	 */
	@RequestMapping(value = "/loadMenu", method = RequestMethod.POST)
	public @ResponseBody JSONObject loadMenu(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String usrId = (String) request.getSession().getAttribute("accountId");
		List<RoleSrcVO> roleSrcList = userService.getSrcListByUsrId(usrId);
		// 获取role_id
		List<String> roleIdList = userService.getRoleIdByUsrId(usrId);
		JSONObject result = new JSONObject();
		JSONArray jArray = new JSONArray();
		for (int i = 0 ; i < roleSrcList.size() ; i++) {
			JSONObject temp = new JSONObject();
			RoleSrcVO roleSrcVO = roleSrcList.get(i);
			temp.put("src_name", roleSrcVO.getSrcVO().getSrc_name());
			temp.put("src_url", roleSrcVO.getSrcVO().getSrc_url());
			temp.put("src_menu_flag", roleSrcVO.getSrcVO().getSrc_menu_flag());
			jArray.add(temp);
		}
		result.put("src", jArray);
		for (int j = 0 ; j < roleIdList.size() ; j++) {
			result.put("roleId" + ( j + 1 ), roleIdList.get(j));
		}
		return result;
	}
/*********************************************************************************************************************************/
/*******************************************************用户注册开始*****************************************************************/
/**
 * @throws Exception *******************************************************************************************************************************/	
	/*
	 * 用户注册
	 * 验证用户是否存在
	 */
	@NoNeedLogin
	@RequestMapping(value = "/validUserIsExist", method = RequestMethod.POST)
	public @ResponseBody JsonResult validUserIsExist(@RequestBody UserVO userVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		JsonResult jsonResult = new JsonResult();
		// 通过页面传过来的用户名查找用户信息
		UserVO userTemp = userService.getUserByUserName(userVO.getUsr_nm());
		if (userTemp != null) {
			jsonResult.setResult(1);
		} else {
			// 用户不存在，直接注册user信息
			userService.insertOneUser(userVO);
			//向用户角色表中插入普通用户信息
			jsonResult.setResult(0);
		}
		return jsonResult;
	}
	
	/*
	 * 验证邮箱是否存在
	 */
	@RequestMapping(value = "validEmailIsExist", method = RequestMethod.POST)
	public @ResponseBody Data validEmailIsExist(String newEmail, String password, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		String userId = (String) request.getSession().getAttribute("accountId");
		//查询用户密码，判断是否本人操作
		UserVO userVO = userService.getUserBasicInfoByUsrId(userId);
		//查询是否已经有人使用了该邮箱
		Integer count = 0;
		count = userService.getCountByUserEmail(newEmail);
		Data data = new Data();
		if (newEmail.equals(userVO.getUsr_email())) {
			data.setDataOne(0);
			data.setMsg("邮箱未修改");
		} else if(!password.equals(userVO.getUsr_pwd())){
			data.setDataOne(0);
			data.setMsg("邮箱已经被人使用");
		} else {
			data.setDataOne(1);
		}
		return data;
	}

	/*
	 * 发送邮箱验证码
	 */
	@RequestMapping(value = "sendValidCode", method = RequestMethod.POST)
	public @ResponseBody void sendValidCode(String newEmail, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws Exception {
		int validCode = RandomUtil.getRandom();
		request.getSession().setAttribute("validCode", String.valueOf(validCode));
		SendEmailUtil.sendMail(newEmail, "您的验证码是" + validCode);
	}

	/*
	 * 验证输入的邮箱验证码是否正确
	 */
	@RequestMapping(value = "subModifiedEmail", method = RequestMethod.POST)
	public @ResponseBody Data subModifiedEmail(String validCode, String newEmail, String password, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		String verifyCode = (String) request.getSession().getAttribute("validCode");
		String usrId = (String) request.getSession().getAttribute("accountId");
		Data data = new Data();
		UserVO userVO = userService.getUserBasicInfoByUsrId(usrId);
		if(userVO.getUsr_pwd().equals(password) || userVO.getUsr_pwd() == password){
			if (validCode.equals(verifyCode)) {
				userService.updateEmailByUsrId(usrId, newEmail);
				data.setDataOne(1);
				data.setMsg("修改成功");
			} else {
				data.setDataOne(0);
				data.setMsg("验证码错误");
			}
		} else {
			data.setMsg("密码错误");
		}
		
		return data;
	}
	
/*********************************************************************************************************************************/
/*******************************************************用户登录开始*****************************************************************/
/**********************************************************************************************************************************/	
	
	/*
	 * 用户登录
	 * 提交登录信息
	 */
	@NoNeedLogin
	@RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
	public @ResponseBody JSONObject loginSubmit(@RequestBody UserVO userVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		
			logger.debug("输入的账号是：" + userVO.getUsr_nm() + " 输入的密码是：" + userVO.getUsr_pwd());
			UserVO usertemp = userService.getUserByUserName(userVO.getUsr_nm());
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("usr_id", usertemp.getUsr_id());
	        jsonObject.put("usr_nm", usertemp.getUsr_nm());
	        jsonObject.put("usr_pwd", usertemp.getUsr_pwd());
	        jsonObject.put("usr_phone", usertemp.getUsr_phone());
	        jsonObject.put("usr_email", usertemp.getUsr_email());
	        jsonObject.put("usr_nicknm", usertemp.getUsr_nicknm());
	        jsonObject.put("usr_cert_num", usertemp.getUsr_cert_num());
	        jsonObject.put("usr_qlfy", usertemp.getUsr_qlfy());
	        jsonObject.put("usr_gender", usertemp.getUsr_gender());
	        jsonObject.put("usr_cert_cate", usertemp.getUsr_cert_cate());
	        jsonObject.put("usr_addr", usertemp.getUsr_addr());
	        jsonObject.put("usr_pub_key", usertemp.getUsr_pub_key());
	        request.getSession().setAttribute("accountId", usertemp.getUsr_id());
	        ESRecord<LoginRecordVO> record = new ESRecord<LoginRecordVO>();
	        LoginRecordVO loginRecord = new LoginRecordVO();
	        loginRecord.setIsdelete("0");
	        loginRecord.setLoginId(UUidUtil.getUUID());
	        loginRecord.setLoginTime(new Date());
	        loginRecord.setUserId(usertemp.getUsr_id());
	        String location = IpAddressUtil.getIpAdrress2(request);
	        String address = "";
			try {
				address = AddressByIpUtil.getAddresses("ip="+location+"&ak="+"H1DOkgEyoqePW8Z6ahp6GPQq8excwvh0", "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			System.out.println(address);
			// 输出结果为：广东省,广州市,越秀区
	        loginRecord.setLocation(address);
	        record.create(loginRecord);
		return jsonObject;
	}
	
/*********************************************************************************************************************************/
/*******************************************************用户信息开始*****************************************************************/
/*********************************************************************************************************************************/	
	/*
	 * 显示用户基本资料
	 */
	@RequestMapping(value = "showUserBasicInfo", method = RequestMethod.POST)
	public @ResponseBody JSONObject showUserBasicInfo(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        
        String userId = (String) request.getSession().getAttribute("accountId");
        UserVO userVO = userService.getUserBasicInfoByUsrId(userId);
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("usr_id", userVO.getUsr_id());
        jsonObject.put("usr_nm", userVO.getUsr_nm());
        jsonObject.put("usr_pwd", userVO.getUsr_pwd());
        jsonObject.put("usr_phone", userVO.getUsr_phone());
        jsonObject.put("usr_email", userVO.getUsr_email());
        jsonObject.put("usr_nicknm", userVO.getUsr_nicknm());
        jsonObject.put("usr_cert_num", userVO.getUsr_cert_num());
        jsonObject.put("usr_qlfy", userVO.getUsr_qlfy());
        jsonObject.put("usr_gender", userVO.getUsr_gender());
        jsonObject.put("usr_cert_cate", userVO.getUsr_cert_cate());
        jsonObject.put("usr_addr", userVO.getUsr_addr());
        
        return jsonObject;
	}

	/*
	 * 用户信息修改
	 * 更新人员基本信息到数据库中
	 */
	@RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
	public @ResponseBody JsonResult updateUserInfo(@RequestBody UserVO userVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		JsonResult jsonResult = new JsonResult();
		String usrId = (String) request.getSession().getAttribute("accountId");
		userVO.setUsr_id(usrId);
		userService.updateUserInfoByUsrId(userVO);
		jsonResult.setResult(1);
		return jsonResult;
	}
	
	/*
	 * 用户性别修改
	 * 更新人员基本信息到数据库中
	 */
	@RequestMapping(value = "updateGender", method = RequestMethod.POST)
	public @ResponseBody JsonResult updateGenderInfo(@RequestBody ChangeUserInfoVO changeUserInfoVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		JsonResult jsonResult = new JsonResult();
		String usrId = (String) request.getSession().getAttribute("accountId");
		changeUserInfoVO.setUserId(usrId);
		changeUserInfoVO.setBirthTime(new Date());
		changeUserInfoVO.setChangeId(UUidUtil.getUUID());
		ESRecord<ChangeUserInfoVO> record = new ESRecord<ChangeUserInfoVO>();
		record.create(changeUserInfoVO);
		jsonResult.setResult(1);
		return jsonResult;
	}
	
	/*
	 * 提交身份变更 
	 */
	@RequestMapping(value = "applyChangeRole", method = RequestMethod.POST)
	public @ResponseBody JsonResult applyChangeRole(@RequestBody UserRoleVO userRoleVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		String usrId = (String) request.getSession().getAttribute("accountId");
		JsonResult jsonResult = new JsonResult();
	    UserRoleVO usrRoleResult = userService.getRoleByUsrIdAndRoleId(usrId, userRoleVO.getRole_id());
		if (usrRoleResult == null) {
			userService.insertOneNewRole(usrId, userRoleVO.getRole_id());
			jsonResult.setResult(1);
		}
		return jsonResult;
	}

	/*
	 * 修改密码，发送验证码
	 */
	@RequestMapping(value = "sendModifyPwdValidCode", method = RequestMethod.POST)
	public @ResponseBody Data sendModifyPwdValidCode(String oldPwd, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		Data data = new Data();
		int validCode = RandomUtil.getRandom();
		String usrId = (String) request.getSession().getAttribute("accountId");
		UserVO userVO = userService.getUserBasicInfoByUsrId(usrId);
		if (oldPwd.equals(userVO.getUsr_pwd())) {
			// 如果前端输入的旧密码和数据库查询出来的密码一样就发送邮箱验证码
			request.getSession().setAttribute("validCode", String.valueOf(validCode));
			SendEmailUtil.sendMail(userVO.getUsr_email(), "您的验证码是" + validCode);
			data.setDataOne(1);
			data.setDatatwo(validCode);
			data.setMsg("发送成功");
		} else {
			data.setDataOne(0);
			data.setMsg("发送失败，密码错误");
		}
		return data;
	}

	/*
	 * 验证修改密码界面输入的邮箱验证码是否正确
	 */
	@RequestMapping(value = "subModifiedPwd", method = RequestMethod.POST)
	public @ResponseBody Data subModifiedPwd(String newPwd, String validCode, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		String verifyCode = (String) request.getSession().getAttribute("validCode");
		String usrId = (String) request.getSession().getAttribute("accountId");
		Data data = new Data();

		if (validCode.equals(verifyCode)) {
			userService.updatePwdByUsrId(usrId, newPwd);
			data.setDataOne(1);
			data.setMsg("修改成功");
		} else {
			data.setDataOne(0);
			data.setMsg("验证码错误");
		}
		return data;
	}

/*********************************************************************************************************************************/
/*******************************************************作品展示开始*****************************************************************/
/*********************************************************************************************************************************/	
	/*
	 * 显示推荐作品信息
	 */
	@RequestMapping(value = "showRecommendInfo", method = RequestMethod.POST)
	public @ResponseBody Data showRecommendInfo(@RequestBody WorksUserVO worksUserVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, TableVO tableVO) {
		int start = 0;
		int offset = 10;
		if (tableVO.getStart() != null && "".equals(tableVO.getStart())) {
			start = tableVO.getStart();
		}
		if (tableVO.getOffset() != null && "".equals(tableVO.getOffset())) {
			offset = tableVO.getOffset();
		}
		ESRecord<WorksUserVO> record = new ESRecord<WorksUserVO>();
		Data data = new Data();
		try {
			data = record.search(worksUserVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 首先查询总记录条数
//		List<WorksUserVO> allVOs = worksService.getWorksInfoByWorksName(works_name, 0, 9999);
//		// 然后根据分页值，查询所请求的页的记录
//		JSONObject data = new JSONObject();
//		JSONArray jarray = new JSONArray();
//		List<WorksUserVO> vosList = worksService.getWorksInfoByWorksName(works_name, start, offset);
//		for (int i = 0; i < vosList.size(); i++) {
//			JSONObject jsonObject = new JSONObject();
//			WorksUserVO worksUserVO = vosList.get(i);
//			jsonObject.put("works_name", worksUserVO.getWorks_name());
//			jsonObject.put("usr_nm", worksUserVO.getUserVO().getUsr_nm());
//			jsonObject.put("works_cate", worksUserVO.getWorks_cate());
//			jsonObject.put("works_spec", worksUserVO.getWorks_spec());
//			jsonObject.put("works_hdgt", worksUserVO.getWorks_hdgt());
//			jsonObject.put("works_pageviews",
//					worksUserVO.getWorks_pageviews() == null ? "" : worksUserVO.getWorks_pageviews());
//			jsonObject.put("works_id", worksUserVO.getWorks_id());
//			jarray.add(jsonObject);
//		}
//		data.put("sEcho", sEcho);
//		// iTotalRecords 字段是本次查询所获得的记录条数，或者说，当前页面能够显示的记录条数
//		data.put("iTotalRecords", 10);
//		// iTotalDisplayRecords 字段是总记录条数，前端js要用于分页
//		data.put("iTotalDisplayRecords", allVOs.size());
//		data.put("aaData", jarray);
		return data;
	}

	/*
	 * 显示推荐作品的详情
	 */

	@RequestMapping(value = "showRecommendWorksDetails", method = RequestMethod.POST)
	public @ResponseBody JSONObject showRecommendWorksDetails(String works_id, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
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
		String usrId = (String) request.getSession().getAttribute("accountId");
		// 添加 浏览记录到浏览记录表中
		BrowsingRecordVO browsingRecordVO = browsingService.insertOneBrowsingRecord(usrId, works_id);
		WorksUserVO works = worksService.getWorksDetailsByWorksId(works_id);
		browsingRecordVO.setWorksUserVO(works);
		ESRecord<BrowsingRecordVO> crud = new ESRecord<BrowsingRecordVO>();
		crud.create(browsingRecordVO);
//		UpdatePageviews.updatePageviews(works);
//		Employee e = new Employee();
//		e.setId(1);
//		e.setName("Tom");
//		e.setSalary(5000);
//		crud.create(e);
		return jsonObject;
	}
	
	/*
	 * 实时添加浏览记录数量
	 */
	@RequestMapping(value = "Pageviews", method = RequestMethod.POST)
	public @ResponseBody JSONObject Pageviews(String works_id, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception{
		WorksUserVO works = worksService.getWorksDetailsByWorksId(works_id);
		UpdatePageviews.updatePageviews(works);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("浏览记录数量添加完成", "1");
		return jsonObject;
	}
	
	/*
	 * 显示浏览记录（我的足迹）
	 */
	@RequestMapping(value = "showBrowsingInfo", method = RequestMethod.POST)
	public @ResponseBody Data showBrowsingInfo(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap, TableVO tableVO) {
		int start = 0;
		int offset = 10;
		if (tableVO.getStart() != null && "".equals(tableVO.getStart())) {
			start = tableVO.getStart();
		}
		if (tableVO.getOffset() != null && "".equals(tableVO.getOffset())) {
			offset = tableVO.getOffset();
		}
		String usrId = (String) request.getSession().getAttribute("accountId");
		// 首先查询总记录条数
		List<BrowsingRecordVO> allVOs = browsingService.getBrowsingRecordByUsrId(usrId, 0, 9999);
		// 然后根据分页值，查询所请求的页的记录
		Data data = new Data();
		List<BrowsingRecordVO> brVO = new ArrayList<BrowsingRecordVO>();
		List<BrowsingRecordVO> vosList = browsingService.getBrowsingRecordByUsrId(usrId, start, offset);
		for (int i = 0; i < vosList.size(); i++) {
			BrowsingRecordVO browsingRecordVO = vosList.get(i);
			String browsingTime = null;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (browsingRecordVO.getBrowsing_time() != null) {
				browsingTime = simpleDateFormat.format(browsingRecordVO.getBrowsing_time());
			}
			brVO.add(browsingRecordVO);
		}
		data.setDataOne(brVO);
		return data;
	}
	
	
	

	/*
	 * 到生成密钥界面
	 */
	@NoNeedLogin
	@RequestMapping(value = "/generateKey", method = RequestMethod.GET)
	public ModelAndView generateKey(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("generateKeyActive", "active");
		ModelAndView mav = new ModelAndView("generateKey", modelMap);
		return mav; 
	}
	
	/*
	 * 到公钥搜索界面
	 */
	@RequestMapping(value = "publicKey", method = RequestMethod.GET)
	public ModelAndView publicKey(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("publicKeyActive", "active");
		ModelAndView mav = new ModelAndView("publicKey", modelMap);
		return mav;
	}
	
	/*
	 * 显示公钥管理
	 */
    @RequestMapping(value = "getPublicKey", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getPublicKey(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
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

        //首先查询总记录条数
        List<UserVO> allVOs = browsingService.getPublicKey(0, 9999);

        //然后根据分页值，查询所请求的页的记录
        JSONObject data = new JSONObject();
        JSONArray jarray = new JSONArray();
        List<UserVO> vosList = browsingService.getPublicKey(start, offset);


        for (int i = 0; i < vosList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            UserVO userVO = vosList.get(i);
           
            
            

            jsonObject.put("usr_nm", userVO.getUsr_nm());
            jsonObject.put("usr_id", userVO.getUsr_id());
            jsonObject.put("public_key", userVO.getUsr_pub_key());
            
            jsonObject.put("download_key", userVO.getUsr_pub_key());
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
	 * 显示作者详情页面
	 */
    @RequestMapping(value = "authorPage", method = RequestMethod.POST)
    @ResponseBody
    public Data authorPage(HttpServletRequest request, HttpServletResponse response, @RequestBody UserVO userVO) throws Exception {
    	
    	Data data = new Data();
    	UserVO user = userService.getUserByUserName(userVO.getUsr_nm());
    	if(null == userVO.getPage() || "".equals(userVO.getPage())){
    		userVO.setPage(0);
    	}
    	List<WorksUserVO> worksUserVO = worksService.getWorksListByUsrId(user.getUsr_id(), userVO.getPage(), 10);
    	data.setDatatwo(worksUserVO);
    	List<Object> search = new ArrayList<Object>();
    	search.add(user);
    	data.setDataOne(search);
//    	DECController record = new DECController();
//    	data = record.searchUserInfo(request, response, userVO);
    	return data;
    }
}