package com.drp.controller;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drp.annotations.NoNeedLogin;
import com.drp.models.BrowsingRecordVO;
import com.drp.models.Data;
import com.drp.models.Employee;
import com.drp.models.WorksUserVO;
import com.drp.service.RecordService;
import com.drp.service.impl.AgentOrderRecordCRUD;
import com.drp.service.impl.BrowsingRecordCRUD;
import com.drp.service.impl.BuyOrderRecordCRUD;
import com.drp.service.impl.ChangeUserInfoCRUD;
import com.drp.service.impl.ChangeWorksInfoCRUD;
import com.drp.service.impl.RecordServiceImpl;
import com.drp.service.impl.UserInfoCRUD;
import com.drp.service.impl.WorksInfoCRUD;
import com.drp.service.impl.LoadRecordCRUD;
import com.drp.service.impl.LoginRecordCRUD;
import com.drp.service.impl.OrderInfoCRUD;
import com.sun.mail.handlers.text_html;

@Controller
@RequestMapping("/record")
public class ESRecord<T> {

	/*
	 * 创建记录
	 */
	@SuppressWarnings({ "resource", "unchecked", "hiding" })
	@NoNeedLogin
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public <T> void create(T t) throws Exception {
		//首先创建ES连接
//		Settings settings = Settings.builder()
//				.put("cluster.name", "elasticsearch")
//				.build();
//		TransportClient client = new PreBuiltTransportClient(settings)
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
//		System.out.println("准备创建document");
		RecordServiceImpl<T> crud = new RecordServiceImpl<T>();
		Integer type = crud.CRUDServiceImpl(t);
		RecordService<T> cs = null;
		//根据接收到的类类型，生成相应的Record实体
		//type=1 browsingRecord
		//type=2 EmployeeRecord
		//type=3 LoginRecord
		//type=4 AgentOrderRecord
		//type=5 BuyOrderRecord
		//type=6 LoadRecord
		//type=7 ChangeUserInfoRecord
		//type=8 ChangeWorksInfoRecord
		//type=9 UserVO
		//type=10 protocolVO
		//type=11 WorksUserVO
		if(type == 1){cs = (RecordService<T>) new BrowsingRecordCRUD();}
		if(type == 3){cs = (RecordService<T>) new LoginRecordCRUD();}
		if(type == 4){cs = (RecordService<T>) new AgentOrderRecordCRUD();}
		if(type == 5){cs = (RecordService<T>) new BuyOrderRecordCRUD();}
		if(type == 6){cs = (RecordService<T>) new LoadRecordCRUD();}
		if(type == 7){cs = (RecordService<T>) new ChangeUserInfoCRUD();}
		if(type == 8){cs = (RecordService<T>) new ChangeWorksInfoCRUD();}
		if(type == 9){cs = (RecordService<T>) new UserInfoCRUD();}
		if(type == 10){cs = (RecordService<T>) new OrderInfoCRUD();}
		if(type == 11){cs = (RecordService<T>) new WorksInfoCRUD();}
		
		try {
			if(!"".equals(t) && null != t && type !=0)
			cs.createDocument(t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		//操作执行完成后，关闭client
		}
	}
	/*
	 * 修改记录
	 */
	@SuppressWarnings({ "resource", "unchecked", "hiding" })
	@NoNeedLogin
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public <T> void update(T t) throws Exception {
		//首先创建ES连接
//		Settings settings = Settings.builder()
//				.put("cluster.name", "elasticsearch")
//				.build();
//		TransportClient client = new PreBuiltTransportClient(settings)
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
//		System.out.println("准备创建document");
		RecordServiceImpl<T> crud = new RecordServiceImpl<T>();
		Integer type = crud.CRUDServiceImpl(t);
		RecordService<T> cs = null;
		//根据接收到的类类型，生成相应的Record实体
		//type=1 browsingRecord
		//type=2 EmployeeRecord
		//type=3 LoginRecord
		//type=4 AgentOrderRecord
		//type=5 BuyOrderRecord
		//type=6 LoadRecord
		//type=7 ChangeUserInfoRecord
		//type=8 ChangeWorksInfoRecord
		//type=9 UserVO
		//type=10 protocolVO
		//type=11 WorksUserVO
		if(type == 1){cs = (RecordService<T>) new BrowsingRecordCRUD();}
		if(type == 3){cs = (RecordService<T>) new LoginRecordCRUD();}
		if(type == 4){cs = (RecordService<T>) new AgentOrderRecordCRUD();}
		if(type == 5){cs = (RecordService<T>) new BuyOrderRecordCRUD();}
		if(type == 6){cs = (RecordService<T>) new LoadRecordCRUD();}
		if(type == 7){cs = (RecordService<T>) new ChangeUserInfoCRUD();}
		if(type == 8){cs = (RecordService<T>) new ChangeWorksInfoCRUD();}
		if(type == 9){cs = (RecordService<T>) new UserInfoCRUD();}
		if(type == 10){cs = (RecordService<T>) new OrderInfoCRUD();}
		if(type == 11){cs = (RecordService<T>) new WorksInfoCRUD();}
		
		try {
			if(!"".equals(t) && null != t && type !=0)
			cs.updateDocument(t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		//操作执行完成后，关闭client
		}
	}
	
	/*
	 * 删除记录
	 */
	@SuppressWarnings({ "resource", "unchecked", "hiding" })
	@NoNeedLogin
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public <T> void delete(T t) throws Exception {
		//首先创建ES连接
//		Settings settings = Settings.builder()
//				.put("cluster.name", "elasticsearch")
//				.build();
//		TransportClient client = new PreBuiltTransportClient(settings)
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
//		System.out.println("准备创建document");
		RecordServiceImpl<T> crud = new RecordServiceImpl<T>();
		Integer type = crud.CRUDServiceImpl(t);
		RecordService<T> cs = null;
		//根据接收到的类类型，生成相应的Record实体
		//type=1 browsingRecord
		//type=2 EmployeeRecord
		//type=3 LoginRecord
		//type=4 AgentOrderRecord
		//type=5 BuyOrderRecord
		//type=6 LoadRecord
		//type=7 ChangeUserInfoRecord
		//type=8 ChangeWorksInfoRecord
		//type=9 UserVO
		//type=10 protocolVO
		//type=11 WorksUserVO
		if(type == 1){cs = (RecordService<T>) new BrowsingRecordCRUD();}
		if(type == 3){cs = (RecordService<T>) new LoginRecordCRUD();}
		if(type == 4){cs = (RecordService<T>) new AgentOrderRecordCRUD();}
		if(type == 5){cs = (RecordService<T>) new BuyOrderRecordCRUD();}
		if(type == 6){cs = (RecordService<T>) new LoadRecordCRUD();}
		if(type == 7){cs = (RecordService<T>) new ChangeUserInfoCRUD();}
		if(type == 8){cs = (RecordService<T>) new ChangeWorksInfoCRUD();}
		if(type == 9){cs = (RecordService<T>) new UserInfoCRUD();}
		if(type == 10){cs = (RecordService<T>) new OrderInfoCRUD();}
		if(type == 11){cs = (RecordService<T>) new WorksInfoCRUD();}
		
		try {
			if(!"".equals(t) && null != t && type !=0)
			cs.deleteDocument(t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		//操作执行完成后，关闭client
		}
	}
	
	/*
	 * 创建记录
	 */
	@SuppressWarnings({ "resource", "unchecked", "hiding" })
	@NoNeedLogin
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public <T> void get(T t) throws Exception {
		//首先创建ES连接
//		Settings settings = Settings.builder()
//				.put("cluster.name", "elasticsearch")
//				.build();
//		TransportClient client = new PreBuiltTransportClient(settings)
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
//		System.out.println("准备创建document");
		RecordServiceImpl<T> crud = new RecordServiceImpl<T>();
		Integer type = crud.CRUDServiceImpl(t);
		RecordService<T> cs = null;
		//根据接收到的类类型，生成相应的Record实体
		//type=1 browsingRecord
		//type=2 EmployeeRecord
		//type=3 LoginRecord
		//type=4 AgentOrderRecord
		//type=5 BuyOrderRecord
		//type=6 LoadRecord
		//type=7 ChangeUserInfoRecord
		//type=8 ChangeWorksInfoRecord
		//type=9 UserVO
		//type=10 protocolVO
		//type=11 WorksUserVO
		if(type == 1){cs = (RecordService<T>) new BrowsingRecordCRUD();}
		if(type == 3){cs = (RecordService<T>) new LoginRecordCRUD();}
		if(type == 4){cs = (RecordService<T>) new AgentOrderRecordCRUD();}
		if(type == 5){cs = (RecordService<T>) new BuyOrderRecordCRUD();}
		if(type == 6){cs = (RecordService<T>) new LoadRecordCRUD();}
		if(type == 7){cs = (RecordService<T>) new ChangeUserInfoCRUD();}
		if(type == 8){cs = (RecordService<T>) new ChangeWorksInfoCRUD();}
		if(type == 9){cs = (RecordService<T>) new UserInfoCRUD();}
		if(type == 10){cs = (RecordService<T>) new OrderInfoCRUD();}
		if(type == 11){cs = (RecordService<T>) new WorksInfoCRUD();}
		
		try {
			if(!"".equals(t) && null != t && type !=0)
			cs.getDocument(t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		//操作执行完成后，关闭client
		}
	}
	
	
	/*
	 * 搜索记录
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	@NoNeedLogin
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public  Data search(T t) throws Exception {
		//创建ES连接
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
		System.out.println("准备创建document");
		RecordServiceImpl<T> crud = new RecordServiceImpl<T>();
		Integer type = crud.CRUDServiceImpl(t);
		RecordService<T> cs = null;
		Data data = new Data();
		//根据接收到的类类型，生成相应的Record实体
				//type=1 browsingRecord
				//type=2 EmployeeRecord
				//type=3 LoginRecord
				//type=4 AgentOrderRecord
				//type=5 BuyOrderRecord
				//type=6 LoadRecord
				//type=7 ChangeUserInfoRecord
				//type=8 ChangeWorksInfoRecord
				if(type == 1){cs = (RecordService<T>) new BrowsingRecordCRUD();}
//				if(type == 2){cs = (RecordService<T>) new EmployeeRecordCRUD();}
				if(type == 3){cs = (RecordService<T>) new LoginRecordCRUD();}
				if(type == 4){cs = (RecordService<T>) new AgentOrderRecordCRUD();}
				if(type == 5){cs = (RecordService<T>) new BuyOrderRecordCRUD();}
				if(type == 6){cs = (RecordService<T>) new LoadRecordCRUD();}
				if(type == 7){cs = (RecordService<T>) new ChangeUserInfoCRUD();}
				if(type == 8){cs = (RecordService<T>) new ChangeWorksInfoCRUD();}
				if(type == 9){cs = (RecordService<T>) new UserInfoCRUD();}
				if(type == 10){cs = (RecordService<T>) new OrderInfoCRUD();}
				if(type == 11){cs = (RecordService<T>) new WorksInfoCRUD();}
				
		try {
			if(!"".equals(client) && null != client && !"".equals(t) && null != t && type !=0)
			data = cs.executeSearch(client, t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		//操作执行完成后，关闭client
		client.close();
		}
		return data;
	}
/*********************************测试***************************************/
	/*
	 * 创建document
	 */
//	@NoNeedLogin
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Employee e= new Employee();
//		e.setId(1);
//		e.setName("tom");
//		e.setSalary(1000);
//		CRUDController<Employee> crud = new CRUDController<Employee>();
//		crud.create(e);
//	}
	/*
	 * 修改浏览记录记录
	 */
	@SuppressWarnings({ "resource", "unchecked", "hiding" })
	@NoNeedLogin
	@RequestMapping(value = "/updatePageviews", method = RequestMethod.POST)
	public void updatePageviews(WorksUserVO worksUserVO) throws Exception {
		//首先创建ES连接
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
		System.out.println("准备创建document");
		RecordServiceImpl<T> crud = new RecordServiceImpl<T>();
		RecordService<T> cs = (RecordService<T>) new WorksInfoCRUD();
		
		try {
			if(!"".equals(client) && null != client && !"".equals(worksUserVO) && null != worksUserVO )
			cs.updatePageViews(client, worksUserVO);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		//操作执行完成后，关闭client
		client.close();
		}
	}
	
}
