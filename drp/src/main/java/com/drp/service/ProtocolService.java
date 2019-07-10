package com.drp.service;
import java.util.List;

import com.drp.models.ProtocolVO;

/**
 * 协议相关的Service层接口类
 * @author curry
 *
 */

public interface ProtocolService {

	//向数据库协议表插入数据
	void insertProtocolContent(ProtocolVO protocolVO) throws Exception;
	
	//代理商申请代理：将刚插入协议表中的协议ID返回给前台
	public String getAgmtId();
	
	//代理商申请代理：查找协议表状态值
	ProtocolVO getProtocolDetailsByAgmtId(String agmt_id);
	
	//直接修改状态值+1
	 public void getInfoIsvalidFlg(String agmt_id);

	//代理商申请代理：更新协议表状态值
	void updateInfoisValidFlg(ProtocolVO protocolVO) throws Exception;
	
	//作者查看作品的代理情况：根据works_id查找信息
	List<ProtocolVO> getProtocolDetailByWorksId(ProtocolVO protocolVO,int start, int offset);
	
	//向数据库协议表插入授权数据
	void insertImpowerProtocolContent(ProtocolVO protocolVO) throws Exception;
	
	//代理商：获取我的代理列表
	List<ProtocolVO> getMyAgentList(String usrId, int start, int offset);
	
	//普通用户：显示我的订单
	 List<ProtocolVO> getMyOrderListData(String usrId, int start, int offset);
	 
	//直接修改购买状态为1
	 public void updateReceiveFlg(String agmt_id) throws Exception;
	
	// 作者：显示作品订单
	 List<ProtocolVO> getWorksOrderList(String works_id, int start, int offset);
	 
	// 作者用户：关于此作品我邀请的代理
	List<ProtocolVO> getAgentListFromMeByWorksId(String usrId,String works_id, int start, int offset);
	
	// 作者用户：关于此作品收到的代理申请
	List<ProtocolVO> getAgentListFromAgentByWorksId(String usrId,String works_id, int start, int offset);
	
	// 读取代理商列表 
	List<String> getAgentList(int start, int offset);

	//代理商：获取我的代理邀请
	List<ProtocolVO> getReceiveAgentListData(String usrId, int start, int offset);
	//通过协议id插入作品密钥
	public void updateDeskey(String agmt_id,String deskey);
}
