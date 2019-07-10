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

import com.drp.models.BrowsingRecordVO;
import com.drp.models.Data;
import com.drp.models.Employee;
import com.drp.models.WorksUserVO;
import com.drp.service.RecordService4es;
import com.drp.service.impl.AgentOrderRecordCRUD4es;
import com.drp.service.impl.BrowsingRecordCRUD4es;
import com.drp.service.impl.BuyOrderRecordCRUD4es;
import com.drp.service.impl.ChangeUserInfoCRUD4es;
import com.drp.service.impl.ChangeWorksInfoCRUD4es;
import com.drp.service.impl.LoadRecordCRUD4es;
import com.drp.service.impl.LoginRecordCRUD4es;
import com.drp.service.impl.OrderInfoCRUD4es;
import com.drp.service.impl.RecordServiceImpl4es;
import com.drp.service.impl.UserInfoCRUD4es;
import com.drp.service.impl.WorksInfoCRUD4es;
import com.sun.mail.handlers.text_html;

@Controller
public class ESRecord4es<T> {

	/*
	 * 创建记录
	 */
	@SuppressWarnings({ "resource", "unchecked", "hiding" })
	public <T> void create(T t) throws Exception {
		//首先创建ES连接
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
		System.out.println("准备创建document");
		RecordServiceImpl4es<T> crud = new RecordServiceImpl4es<T>();
		Integer type = crud.CRUDServiceImpl(t);
		RecordService4es<TransportClient, T> cs = null;
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
		if(type == 1){cs = (RecordService4es<TransportClient, T>) new BrowsingRecordCRUD4es();}
		if(type == 3){cs = (RecordService4es<TransportClient, T>) new LoginRecordCRUD4es();}
		if(type == 4){cs = (RecordService4es<TransportClient, T>) new AgentOrderRecordCRUD4es();}
		if(type == 5){cs = (RecordService4es<TransportClient, T>) new BuyOrderRecordCRUD4es();}
		if(type == 6){cs = (RecordService4es<TransportClient, T>) new LoadRecordCRUD4es();}
		if(type == 7){cs = (RecordService4es<TransportClient, T>) new ChangeUserInfoCRUD4es();}
		if(type == 8){cs = (RecordService4es<TransportClient, T>) new ChangeWorksInfoCRUD4es();}
		if(type == 9){cs = (RecordService4es<TransportClient, T>) new UserInfoCRUD4es();}
		if(type == 10){cs = (RecordService4es<TransportClient, T>) new OrderInfoCRUD4es();}
		if(type == 11){cs = (RecordService4es<TransportClient, T>) new WorksInfoCRUD4es();}
		
		try {
			if(!"".equals(client) && null != client && !"".equals(t) && null != t && type !=0)
			cs.createDocument(client, t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		//操作执行完成后，关闭client
		client.close();
		}
	}
	/*
	 * 修改记录
	 */
	@SuppressWarnings({ "resource", "unchecked", "hiding" })
	public <T> void update(T t) throws Exception {
		//首先创建ES连接
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
		System.out.println("准备创建document");
		RecordServiceImpl4es<T> crud = new RecordServiceImpl4es<T>();
		Integer type = crud.CRUDServiceImpl(t);
		RecordService4es<TransportClient, T> cs = null;
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
		if(type == 1){cs = (RecordService4es<TransportClient, T>) new BrowsingRecordCRUD4es();}
		if(type == 3){cs = (RecordService4es<TransportClient, T>) new LoginRecordCRUD4es();}
		if(type == 4){cs = (RecordService4es<TransportClient, T>) new AgentOrderRecordCRUD4es();}
		if(type == 5){cs = (RecordService4es<TransportClient, T>) new BuyOrderRecordCRUD4es();}
		if(type == 6){cs = (RecordService4es<TransportClient, T>) new LoadRecordCRUD4es();}
		if(type == 7){cs = (RecordService4es<TransportClient, T>) new ChangeUserInfoCRUD4es();}
		if(type == 8){cs = (RecordService4es<TransportClient, T>) new ChangeWorksInfoCRUD4es();}
		if(type == 9){cs = (RecordService4es<TransportClient, T>) new UserInfoCRUD4es();}
		if(type == 10){cs = (RecordService4es<TransportClient, T>) new OrderInfoCRUD4es();}
		if(type == 11){cs = (RecordService4es<TransportClient, T>) new WorksInfoCRUD4es();}
		
		try {
			if(!"".equals(client) && null != client && !"".equals(t) && null != t && type !=0)
			cs.updateDocument(client, t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		//操作执行完成后，关闭client
		client.close();
		}
	}
	
	/*
	 * 删除记录
	 */
	@SuppressWarnings({ "resource", "unchecked", "hiding" })
	public <T> void delete(T t) throws Exception {
		//首先创建ES连接
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
		System.out.println("准备创建document");
		RecordServiceImpl4es<T> crud = new RecordServiceImpl4es<T>();
		Integer type = crud.CRUDServiceImpl(t);
		RecordService4es<TransportClient, T> cs = null;
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
		if(type == 1){cs = (RecordService4es<TransportClient, T>) new BrowsingRecordCRUD4es();}
		if(type == 3){cs = (RecordService4es<TransportClient, T>) new LoginRecordCRUD4es();}
		if(type == 4){cs = (RecordService4es<TransportClient, T>) new AgentOrderRecordCRUD4es();}
		if(type == 5){cs = (RecordService4es<TransportClient, T>) new BuyOrderRecordCRUD4es();}
		if(type == 6){cs = (RecordService4es<TransportClient, T>) new LoadRecordCRUD4es();}
		if(type == 7){cs = (RecordService4es<TransportClient, T>) new ChangeUserInfoCRUD4es();}
		if(type == 8){cs = (RecordService4es<TransportClient, T>) new ChangeWorksInfoCRUD4es();}
		if(type == 9){cs = (RecordService4es<TransportClient, T>) new UserInfoCRUD4es();}
		if(type == 10){cs = (RecordService4es<TransportClient, T>) new OrderInfoCRUD4es();}
		if(type == 11){cs = (RecordService4es<TransportClient, T>) new WorksInfoCRUD4es();}
		
		try {
			if(!"".equals(client) && null != client && !"".equals(t) && null != t && type !=0)
			cs.deleteDocument(client, t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		//操作执行完成后，关闭client
		client.close();
		}
	}
	
	/*
	 * 创建记录
	 */
	@SuppressWarnings({ "resource", "unchecked", "hiding" })
	public <T> void get(T t) throws Exception {
		//首先创建ES连接
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
		System.out.println("准备创建document");
		RecordServiceImpl4es<T> crud = new RecordServiceImpl4es<T>();
		Integer type = crud.CRUDServiceImpl(t);
		RecordService4es<TransportClient, T> cs = null;
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
		if(type == 1){cs = (RecordService4es<TransportClient, T>) new BrowsingRecordCRUD4es();}
		if(type == 3){cs = (RecordService4es<TransportClient, T>) new LoginRecordCRUD4es();}
		if(type == 4){cs = (RecordService4es<TransportClient, T>) new AgentOrderRecordCRUD4es();}
		if(type == 5){cs = (RecordService4es<TransportClient, T>) new BuyOrderRecordCRUD4es();}
		if(type == 6){cs = (RecordService4es<TransportClient, T>) new LoadRecordCRUD4es();}
		if(type == 7){cs = (RecordService4es<TransportClient, T>) new ChangeUserInfoCRUD4es();}
		if(type == 8){cs = (RecordService4es<TransportClient, T>) new ChangeWorksInfoCRUD4es();}
		if(type == 9){cs = (RecordService4es<TransportClient, T>) new UserInfoCRUD4es();}
		if(type == 10){cs = (RecordService4es<TransportClient, T>) new OrderInfoCRUD4es();}
		if(type == 11){cs = (RecordService4es<TransportClient, T>) new WorksInfoCRUD4es();}
		
		try {
			if(!"".equals(client) && null != client && !"".equals(t) && null != t && type !=0)
			cs.getDocument(client, t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		//操作执行完成后，关闭client
		client.close();
		}
	}
	
	
	/*
	 * 搜索记录
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public  Data search(T t) throws Exception {
		//创建ES连接
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
		System.out.println("准备创建document");
		RecordServiceImpl4es<T> crud = new RecordServiceImpl4es<T>();
		Integer type = crud.CRUDServiceImpl(t);
		RecordService4es<TransportClient, T> cs = null;
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
				if(type == 1){cs = (RecordService4es<TransportClient, T>) new BrowsingRecordCRUD4es();}
				if(type == 3){cs = (RecordService4es<TransportClient, T>) new LoginRecordCRUD4es();}
				if(type == 4){cs = (RecordService4es<TransportClient, T>) new AgentOrderRecordCRUD4es();}
				if(type == 5){cs = (RecordService4es<TransportClient, T>) new BuyOrderRecordCRUD4es();}
				if(type == 6){cs = (RecordService4es<TransportClient, T>) new LoadRecordCRUD4es();}
				if(type == 7){cs = (RecordService4es<TransportClient, T>) new ChangeUserInfoCRUD4es();}
				if(type == 8){cs = (RecordService4es<TransportClient, T>) new ChangeWorksInfoCRUD4es();}
				if(type == 9){cs = (RecordService4es<TransportClient, T>) new UserInfoCRUD4es();}
				if(type == 10){cs = (RecordService4es<TransportClient, T>) new OrderInfoCRUD4es();}
				if(type == 11){cs = (RecordService4es<TransportClient, T>) new WorksInfoCRUD4es();}
				
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
	@RequestMapping(value = "/updatePageviews", method = RequestMethod.POST)
	public void updatePageviews(WorksUserVO worksUserVO) throws Exception {
		//首先创建ES连接
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
		System.out.println("准备创建document");
		RecordServiceImpl4es<T> crud = new RecordServiceImpl4es<T>();
		RecordService4es<TransportClient, T> cs = (RecordService4es<TransportClient, T>) new WorksInfoCRUD4es();
		
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
