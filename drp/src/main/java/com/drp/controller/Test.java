package com.drp.controller;

import static org.junit.Assert.*;

import java.net.InetAddress;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.drp.models.Employee;

public class Test {

	@org.junit.Test
	public void test() throws Exception {
//		Employee e= new Employee();
//		e.setId(1);
//		e.setName("tom");
//		e.setSalary(1000);
// 		CRUDController<Employee> crud = new CRUDController<Employee>();
//		crud.create(e);
		
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		createEmployee(client); 
//		getEmployee(client);
//		updateEmployee(client); 
//		deleteEmployee(client); 
		
		client.close();
	}
	
	/**
	 * 创建员工信息（创建一个document）
	 * @param client
	 */
	private static void createEmployee(TransportClient client) throws Exception {
		IndexResponse response = client.prepareIndex("company", "employee", "1")
				.setSource(XContentFactory.jsonBuilder()
						.startObject()
							.field("name", "jack")
							.field("age", 27)
							.field("position", "technique")
							.field("country", "china")
							.field("join_date", "2017-01-01")
							.field("salary", 10000)
						.endObject())
				.get();
		System.out.println(response.getResult()); 
	}

}
