//package com.drp.service.impl;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.xcontent.XContentFactory;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.springframework.stereotype.Service;
//
//import com.drp.models.Employee;
//
//public class EmployeeRecordCRUD extends RecordServiceImpl<Employee>{
//
//	@Override
//	public void createDocument(TransportClient client, Employee e) throws IOException {
////		Employee e = (Employee) t;
//		IndexResponse response = client.prepareIndex("company", "employee", e.getId().toString())
//				.setSource(XContentFactory.jsonBuilder()
//						.startObject()
//						.field("name",e.getName())
//						.field("salary",e.getSalary())
//						.endObject())
//				.get();
//		System.out.println("创建document完成");
//		System.out.println(response.getResult()); 
//		
//	}
//
//	/**
//	 * 删除信息示例
//	 */
//	@Override
//	public void deleteDocument(TransportClient client, Employee t) throws IOException {
//		DeleteResponse response = client.prepareDelete("company", "employee", "1").get();
//		System.out.println(response.getResult());  
//	}
//
//	/**
//	 * 更新信息示例
//	 */
//	@Override
//	public void updateDocument(TransportClient client, Employee t) throws IOException {
//		UpdateResponse response = client.prepareUpdate("company", "employee", "1") 
//				.setDoc(XContentFactory.jsonBuilder()
//							.startObject()
//								.field("position", "technique manager")
//							.endObject())
//				.get();
//		System.out.println(response.getResult());  
//	}
//
//	/**
//	 * 获取信息示例
//	 */
//	@Override
//	public void getDocument(TransportClient client, Employee t) throws IOException {
//		GetResponse response = client.prepareGet("company", "employee", "1").get();
//		System.out.println(response.getSourceAsString()); 
//	}
//	
//	/**
//	 * 搜索示例
//	 * @return 
//	 */
////	public ArrayList<Employee> executeSearch(TransportClient client , Employee t) throws Exception{
////		SearchResponse response = client.prepareSearch("company")
////				.setTypes("employee")
////				.setQuery(QueryBuilders.matchQuery("name", t.getName()))
////				.setPostFilter(QueryBuilders.rangeQuery("age").from(30).to(40))
////				.setFrom(0).setSize(1)
////				.get();
////		
////		SearchHit[] searchHits = response.getHits().getHits();
////		for(int i = 0; i < searchHits.length; i++) {
////			System.out.println(searchHits[i].getSourceAsString()); 
////			Employee e = new Employee();
////		}
////		return null;
////	}
//}
