package com.drp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drp.models.ProtocolPageVO;
import com.drp.models.ProtocolVO;
import com.drp.models.UserRolePageVO;
import com.drp.models.UserVO;


/**
 * 协议相关的DAO层接口类
 * @author curry
 *
 */
public interface ProtocolDAO {
	
	//向协议表插入数据
	void insertProtocolContent(@Param("protocolVO") ProtocolVO protocolVO);

	//查找协议表内容
	ProtocolVO getProtocolDetailsByAgmtId(@Param("agmt_id") String agmt_id);


	
	//查找协议表状态值-后台版
	 String getInfoIsvalidFlg(@Param("agmt_id") String agmt_id);

	 //更新协议表状态值-后台版
	 void updateInfoIsvalidFlg(@Param("isval") String isval, @Param("agmt_id") String agmt_id);
	
	List<ProtocolVO> getProtocolDetailByWorksId(@Param("protocolPageVO") ProtocolPageVO protocolPageVO);

	//向协议表插入授权数据
	void insertImpowerProtocolContent(@Param("protocolVO") ProtocolVO protocolVO);
	
	//代理商：获取我的代理列表
	 List<ProtocolVO> getMyAgentList(@Param("protocolPageVO") ProtocolPageVO protocolPageVO);
	 
	//普通用户：显示我的订单
	 List<ProtocolVO> getMyOrderListData(@Param("protocolPageVO") ProtocolPageVO protocolPageVO);
	
	//更新购买状态值
	 void updateReceiveFlg(@Param("agmt_id") String agmt_id, @Param("receive_flg") String receive_flg);
	 
	//作者：显示作品订单
	 List<ProtocolVO> getWorksOrderList(@Param("protocolPageVO") ProtocolPageVO protocolPageVO);
	 
	//作者：显示我邀请的代理
	List<ProtocolVO> getAgentListFromMeByWorksId(@Param("protocolPageVO") ProtocolPageVO protocolPageVO);
	
	//作者：显示我收到的代理申请
	List<ProtocolVO> getAgentListFromAgentByWorksId(@Param("protocolPageVO") ProtocolPageVO protocolPageVO);

	//代理商：获取作者的代理邀请
	List<ProtocolVO> getReceiveAgentListData(@Param("protocolPageVO") ProtocolPageVO protocolPageVO);
	//更新协议表状态值-前端版
	void updateInfoisValidFlg(@Param("protocolVO") ProtocolVO protocolVO);
	//通过协议id插入作品密钥
	void updateDeskey(@Param("agmt_id") String agmt_id, @Param("deskey") String deskey);

}
