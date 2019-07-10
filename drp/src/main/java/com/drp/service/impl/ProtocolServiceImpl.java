package com.drp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.controller.ESRecord;
import com.drp.dao.ProtocolDAO;
import com.drp.dao.UserDAO;
import com.drp.models.AgentOrderRecordVO;
import com.drp.models.BuyOrderRecordVO;
import com.drp.models.PageVO;
import com.drp.models.ProtocolPageVO;
import com.drp.models.ProtocolVO;
import com.drp.models.UserRolePageVO;
import com.drp.models.UserVO;
import com.drp.models.WorksPageVO;
import com.drp.service.ProtocolService;
import com.drp.util.UUidUtil;
import com.sun.mail.iap.Protocol;

/**
 * 协议相关的service层接口实现类
 * @author curry
 *
 */
@Service("protocolService")
public class ProtocolServiceImpl implements ProtocolService{
	@Autowired
	public static String agmt_id;
	@Autowired
	private ProtocolDAO protocolDao;
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void insertProtocolContent(ProtocolVO protocolVO) throws Exception {
		agmt_id = UUidUtil.getUUID();
		protocolVO.setAgmt_id(agmt_id);
		protocolDao.insertProtocolContent(protocolVO);
		//代理交易记录
		AgentOrderRecordVO agentOrderRecordVO = new AgentOrderRecordVO();
		agentOrderRecordVO.setAgentId(protocolVO.getPartb_id());
		agentOrderRecordVO.setBirthTime(new Date());
		agentOrderRecordVO.setIsdelete("0");
		agentOrderRecordVO.setOrderId(UUidUtil.getUUID());
		agentOrderRecordVO.setType("0");
		agentOrderRecordVO.setUserId(protocolVO.getUsr_id());
		agentOrderRecordVO.setWorksId(protocolVO.getWorks_id());
		ESRecord<AgentOrderRecordVO> record = new ESRecord<AgentOrderRecordVO>();
		record.create(agentOrderRecordVO);
		//代理订单注册
		ESRecord<ProtocolVO> record1 = new ESRecord<ProtocolVO>();
		record1.create(protocolVO);
	}

	@Override
	public String getAgmtId() {
		return agmt_id;
	}

	//查找协议表状态值
	@Override
	public ProtocolVO getProtocolDetailsByAgmtId(String agmt_id){
		return protocolDao.getProtocolDetailsByAgmtId(agmt_id);
	}
	
	//更新协议表状态值
	@Override
	public void updateInfoisValidFlg(ProtocolVO protocolVO) throws Exception{
		protocolDao.updateInfoisValidFlg(protocolVO);
		//代理协议订单修改
		ESRecord<ProtocolVO> record = new ESRecord<ProtocolVO>();
		record.update(protocolVO);
		//代理订单修改记录
		ProtocolVO protocol1 = protocolDao.getProtocolDetailsByAgmtId(protocolVO.getAgmt_id());
		AgentOrderRecordVO agentOrderRecordVO = new AgentOrderRecordVO();
		agentOrderRecordVO.setAgentId(protocol1.getPartb_id());
		agentOrderRecordVO.setBirthTime(new Date());
		agentOrderRecordVO.setIsdelete("0");
		agentOrderRecordVO.setOrderId(UUidUtil.getUUID());
		agentOrderRecordVO.setType(String.valueOf(protocol1.getInfo_isvalid_flg()));
		agentOrderRecordVO.setUserId(protocol1.getUsr_id());
		agentOrderRecordVO.setWorksId(protocol1.getWorks_id());
		ESRecord<AgentOrderRecordVO> record1 = new ESRecord<AgentOrderRecordVO>();
		record1.create(agentOrderRecordVO);
	}
	
	
	//根据works_id查询协议表信息
	 @Override
	 public List<ProtocolVO> getProtocolDetailByWorksId(ProtocolVO protocolVO,int start, int offset){
	  ProtocolPageVO protocolPageVO = new ProtocolPageVO();
	  protocolPageVO.setWorks_id(protocolVO.getWorks_id());
	  protocolPageVO.setProtocol_type(protocolVO.getProtocol_type());
	  protocolPageVO.setParta_id(protocolVO.getParta_id());
	  PageVO pageVO = new PageVO();
	  pageVO.setIndex(start);
	  pageVO.setPageSize(offset);
	  protocolPageVO.setPageVO(pageVO);
	  return protocolDao.getProtocolDetailByWorksId(protocolPageVO);
	 }
	
	 //查找协议表状态并修改值
	 @Override
	 public void getInfoIsvalidFlg(String agmt_id){
	  String infoIsValidflg = protocolDao.getInfoIsvalidFlg(agmt_id);
	  int infoIsValidflg1 = Integer.parseInt(infoIsValidflg);
	  int isvalid = infoIsValidflg1 + 1;
	  String isval=String.valueOf(isvalid);
	  protocolDao.updateInfoIsvalidFlg(isval, agmt_id);
	 }
	 
	//授权协议
	@Override
	public void insertImpowerProtocolContent(ProtocolVO protocolVO) throws Exception {
		agmt_id = UUidUtil.getUUID();
		protocolVO.setAgmt_id(agmt_id);
		protocolDao.insertImpowerProtocolContent(protocolVO);
		//授权协议订单注册
		ESRecord<ProtocolVO> record1 = new ESRecord<ProtocolVO>();
		record1.create(protocolVO);
		//授权协议生成记录
		BuyOrderRecordVO buyOrderRecordVO = new BuyOrderRecordVO();
		buyOrderRecordVO.setBirthTime(new Date());
		buyOrderRecordVO.setCustomerId(protocolVO.getPartb_id());
		buyOrderRecordVO.setIsdelete("0");
		buyOrderRecordVO.setOrderId(protocolVO.getAgmt_id());
		buyOrderRecordVO.setType("0");
		buyOrderRecordVO.setUserId(protocolVO.getUsr_id());
		buyOrderRecordVO.setWorksId(protocolVO.getWorks_id());
		ESRecord<BuyOrderRecordVO> record = new ESRecord<BuyOrderRecordVO>();
		record.create(buyOrderRecordVO);
	}
	
	//代理商：获取我的代理列表
	 @Override
	 public List<ProtocolVO> getMyAgentList(String usr_id, int start, int offset){
	  ProtocolPageVO protocolPageVO = new ProtocolPageVO();
	  PageVO pageVO = new PageVO();
	  protocolPageVO.setUsr_id(usr_id);
	  pageVO.setIndex(start);
	  pageVO.setPageSize(offset);
	  protocolPageVO.setPageVO(pageVO);
	  return protocolDao.getMyAgentList(protocolPageVO);
	 }
	 
	 
	 /**
	  * 修改为显示所有订单，包括自己为卖家和自己为卖家的
	  * 
	  */
	//普通用户：显示我的订单
	 @Override
	 public List<ProtocolVO> getMyOrderListData(String usr_id, int start, int offset){
	  ProtocolPageVO protocolPageVO = new ProtocolPageVO();
	  PageVO pageVO = new PageVO();
	  protocolPageVO.setUsr_id(usr_id);
	  pageVO.setIndex(start);
	  pageVO.setPageSize(offset);
	  protocolPageVO.setPageVO(pageVO);
	  return protocolDao.getMyOrderListData(protocolPageVO);
	 }
	 
//	//普通用户：显示我的订单
//	 @Override
//	 public List<ProtocolVO> getMyOrderListData(String usr_id, int start, int offset){
//	  ProtocolPageVO protocolPageVOa = new ProtocolPageVO();
//	  ProtocolPageVO protocolPageVOb = new ProtocolPageVO();
//	  PageVO pageVO = new PageVO();
//	 
//	  protocolPageVOa.setParta_id(usr_id);
//	  protocolPageVOb.setPartb_id(usr_id);
//	  pageVO.setIndex(start);
//	  pageVO.setPageSize(offset);
//	  protocolPageVOa.setPageVO(pageVO);
//	  protocolPageVOb.setPageVO(pageVO);
//	  
//	  
//	  List<ProtocolVO> a=protocolDao.getMyOrderListData(protocolPageVOa);
//	  List<ProtocolVO> b=protocolDao.getMyOrderListData(protocolPageVOb);
//	  a.addAll(b);
//	  return a;
//	 }

	 
	 
	 
	 
	//直接修改购买状态为1
	 @Override
	 public void updateReceiveFlg(String agmt_id) throws Exception {
	  String receive_flg = "1";
	  protocolDao.updateReceiveFlg(agmt_id, receive_flg);
	  ProtocolVO protocolVO = new ProtocolVO();
	  protocolVO.setAgmt_id(agmt_id);
	  protocolVO.setReceive_flg(receive_flg);
	  //授权协议订单修改
	  ESRecord<ProtocolVO> record = new ESRecord<ProtocolVO>();
	  record.update(protocolVO);
	  ProtocolVO protocol1 = protocolDao.getProtocolDetailsByAgmtId(agmt_id);
	  //授权协议交易状态改变记录
	  BuyOrderRecordVO buyOrderRecordVO = new BuyOrderRecordVO();
	  buyOrderRecordVO.setBirthTime(new Date());
	  buyOrderRecordVO.setCustomerId(protocol1.getPartb_id());
	  buyOrderRecordVO.setIsdelete("0");
	  buyOrderRecordVO.setOrderId(protocol1.getAgmt_id());
	  buyOrderRecordVO.setType(protocol1.getReceive_flg());
	  buyOrderRecordVO.setUserId(protocol1.getUsr_id());
	  buyOrderRecordVO.setWorksId(protocol1.getWorks_id());
	  ESRecord<BuyOrderRecordVO> record1 = new ESRecord<BuyOrderRecordVO>();
	  record1.create(buyOrderRecordVO);
	 }
	 
	//作者：显示作品订单
	 @Override
	 public List<ProtocolVO> getWorksOrderList(String works_id, int start, int offset){
	  ProtocolPageVO protocolPageVO = new ProtocolPageVO();
	  PageVO pageVO = new PageVO();
	  protocolPageVO.setWorks_id(works_id);;
	  pageVO.setIndex(start);
	  pageVO.setPageSize(offset);
	  protocolPageVO.setPageVO(pageVO);
	  return protocolDao.getWorksOrderList(protocolPageVO);
	 }
	 
	//作者用户：关于此作品我邀请的代理
	 @Override
	 public List<ProtocolVO> getAgentListFromMeByWorksId(String usrId,String works_id, int start, int offset){
	  ProtocolPageVO protocolPageVO = new ProtocolPageVO();
	  PageVO pageVO = new PageVO();
	  protocolPageVO.setWorks_id(works_id);
	  protocolPageVO.setUsr_id(usrId);;
	  pageVO.setIndex(start);
	  pageVO.setPageSize(offset);
	  protocolPageVO.setPageVO(pageVO);
	  return protocolDao.getAgentListFromMeByWorksId(protocolPageVO);
	 }
	 
	//作者用户：关于此作品收到的代理申请
	 @Override
	 public List<ProtocolVO> getAgentListFromAgentByWorksId(String usrId,String works_id, int start, int offset){
	  ProtocolPageVO protocolPageVO = new ProtocolPageVO();
	  PageVO pageVO = new PageVO();
	  protocolPageVO.setWorks_id(works_id);
	  protocolPageVO.setUsr_id(usrId);;
	  pageVO.setIndex(start);
	  pageVO.setPageSize(offset);
	  protocolPageVO.setPageVO(pageVO);
	  return protocolDao.getAgentListFromAgentByWorksId(protocolPageVO);
	 }
	 
	//读取代理商列表
	 @Override
	 public List<String> getAgentList(int start, int offset){
	  UserRolePageVO userRolePageVO = new UserRolePageVO();
	  PageVO pageVO = new PageVO();
	  pageVO.setIndex(start);
	  pageVO.setPageSize(offset);
	  userRolePageVO.setPageVO(pageVO);
	  return userDAO.getAgentList(userRolePageVO);
	 }

	//代理商：获取作者的代理邀请
	@Override
	public List<ProtocolVO> getReceiveAgentListData(String usr_id, int start, int offset) {
		 ProtocolPageVO protocolPageVO = new ProtocolPageVO();
		  PageVO pageVO = new PageVO();
		  protocolPageVO.setUsr_id(usr_id);
		  pageVO.setIndex(start);
		  pageVO.setPageSize(offset);
		  protocolPageVO.setPageVO(pageVO);
		  return protocolDao.getReceiveAgentListData(protocolPageVO);
	}

	@Override
	public void updateDeskey(String agmt_id, String deskey) {

//		ProtocolVO protocol = new ProtocolVO();
//		protocol.setAgmt_id(agmt_id);
//		protocol.setDeskey(deskey);
		
		protocolDao.updateDeskey(agmt_id,deskey);
		
	}
	 
}
