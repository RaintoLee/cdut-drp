package com.drp.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import com.drp.models.AgentOrderRecordVO;
import com.drp.models.BrowsingRecordVO;
import com.drp.models.Data;

public class AgentOrderRecordCRUD extends RecordServiceImpl<AgentOrderRecordVO>{
	@Test
	public void main(){
		try{
			AgentOrderRecordVO agentOrderRecordVO = new AgentOrderRecordVO();
			agentOrderRecordVO.setAgentId("AgentId");
			agentOrderRecordVO.setBirthTime(new Date());
			agentOrderRecordVO.setIsdelete("IsDelete");
			agentOrderRecordVO.setOrderId("OrderId");
			agentOrderRecordVO.setType("type");
			agentOrderRecordVO.setUserId("UserId");
			agentOrderRecordVO.setWorksId("WorksId");
			createDocument(agentOrderRecordVO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void createDocument(AgentOrderRecordVO agentOrderRecordVO) throws IOException{
//		IndexResponse response = client.prepareIndex("record", "agentOrder", agentOrderRecordVO.getOrderId())
//										.setSource(XContentFactory.jsonBuilder()
//												.startObject()
//												.field("userId", agentOrderRecordVO.getUserId())
//												.field("agentId", agentOrderRecordVO.getAgentId())
//												.field("worksId", agentOrderRecordVO.getWorksId())
//												.field("type", agentOrderRecordVO.getType())
//												.field("birthTime", agentOrderRecordVO.getBirthTime())
//												.field("isdelete", "0")
//											.endObject())
//										.get();
//		System.out.println("创建document完成");
//		System.out.println(response.getResult()); 
//		FileOutputStream out = new FileOutputStream(this.getClass().getClassLoader().getResource("").getPath().toString(), true);
		//数据写入文件
		File file = new File("/var/lib/tomcat8/log4es");
		System.out.println("/var/lib/tomcat8/log4es");
		if(!file.exists()){
			file.mkdir();
		}
		if(file.isDirectory()){
			Date date = new Date();
			DateFormat dFormat1 = new SimpleDateFormat("yyyyMMdd"); //HH表示24小时制；  
			String date1 = dFormat1.format(date);
			FileOutputStream out = new FileOutputStream("/var/lib/tomcat8/log4es/" + date1, true);

			DateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //HH表示24小时制；  
			String formatDate = dFormat.format(agentOrderRecordVO.getBirthTime());
			StringBuffer sb = new StringBuffer();
			sb.append("createDocument,record,agentOrder,")
				.append(agentOrderRecordVO.getOrderId()).append(",")
				.append(agentOrderRecordVO.getUserId()).append(",")
				.append(agentOrderRecordVO.getAgentId()).append(",")
				.append(agentOrderRecordVO.getWorksId()).append(",")
				.append(agentOrderRecordVO.getType()).append(",")
				.append(formatDate).append(",")
				.append(agentOrderRecordVO.getIsdelete()).append("\n");
			out.write(sb.toString().getBytes());
		}
		
		//从文件读入数据
//		FileInputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\测试\\新建文本文档.txt");
//		InputStreamReader reader = new InputStreamReader(in);
//		BufferedReader br = new BufferedReader(reader);
//		String data = null;
//		Integer line = 0;
//		StringBuffer s = new StringBuffer();
//		while((data = br.readLine()) != null && line < 10000){
//			s.append(data);
//			data = s.toString();
//			String[] dataOne = data.split(",");
//			line++;
//			for(Integer j = 0 ; j<dataOne.length ; j++)
//				System.out.println(dataOne[j]);
//			System.out.println("完成10000条传输");
//		}
	}
	
	@Override
	public void updateDocument(AgentOrderRecordVO agentOrderRecordVO) throws IOException{
//		UpdateResponse response = client.prepareUpdate("record", "agentOrder", agentOrderRecordVO.getOrderId())
//										.setDoc(XContentFactory.jsonBuilder()
//											.startObject()
//												.field("type", agentOrderRecordVO.getType())
//											.endObject())
//										.get();
//		System.out.println(response.getResult());  
		File file = new File("/var/lib/tomcat8/log4es");
		System.out.println("/var/lib/tomcat8/log4es");
		if(!file.exists()){
			file.mkdir();
		}
		if(file.isDirectory()){
			Date date = new Date();
			DateFormat dFormat1 = new SimpleDateFormat("yyyyMMdd"); //HH表示24小时制；  
			String date1 = dFormat1.format(date);
			FileOutputStream out = new FileOutputStream("/var/lib/tomcat8/log4es/" + date1, true);
			
			StringBuffer sb = new StringBuffer();
			sb.append("updateDocument,record,agentOrder,")
				.append(agentOrderRecordVO.getOrderId()).append(",")
				.append(agentOrderRecordVO.getType()).append(";");
			out.write(sb.toString().getBytes());
		}
	}
	
	@Override
	public void deleteDocument(AgentOrderRecordVO agentOrderRecordVO) throws IOException{
//		UpdateResponse response = client.prepareUpdate("record", "agentOrder", agentOrderRecordVO.getOrderId())
//				.setDoc(XContentFactory.jsonBuilder()
//					.startObject()
//						.field("isdelete", "1")
//					.endObject())
//				.get();
//		System.out.println(response.getResult());  
		File file = new File("/var/lib/tomcat8/log4es");
		System.out.println("/var/lib/tomcat8/log4es");
		if(!file.exists()){
			file.mkdir();
		}
		if(file.isDirectory()){
			Date date = new Date();
			DateFormat dFormat1 = new SimpleDateFormat("yyyyMMdd"); //HH表示24小时制；  
			String date1 = dFormat1.format(date);
			FileOutputStream out = new FileOutputStream("/var/lib/tomcat8/log4es/" + date1, true);
			
			StringBuffer sb = new StringBuffer();
			sb.append("deleteDocument,record,agentOrder,")
				.append(agentOrderRecordVO.getOrderId()).append(",")
				.append("1").append(";");
			out.write(sb.toString().getBytes());
		}
	}
	
//	@Override
//	public void getDocument(TransportClient client, AgentOrderRecordVO agentOrderRecordVO) throws IOException{
//		GetResponse response = client.prepareGet("record", "agentOrder", agentOrderRecordVO.getOrderId()).get();
//		System.out.println(response.getSourceAsString());
//	}
	
//	public void realDeleteDocument(TransportClient client, AgentOrderRecordVO agentOrderRecordVO) throws IOException{
//		DeleteResponse response = client.prepareDelete("record", "agentOrder", agentOrderRecordVO.getOrderId()).get();
//		System.out.println(response.getResult());
//	}
	
	/*
	 * 搜索
	 */
	@Override
	public Data executeSearch(TransportClient client, AgentOrderRecordVO agentOrderRecordVO) throws IOException{
		Data data = new Data();
			AggregationBuilder aggregation = AggregationBuilders
					.terms("count_by_isdelete")
					.field("isdelete");
			SearchResponse response1 = client.prepareSearch("record")
					.setTypes("agentOrder")
					.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
					.addSort("birthTime", SortOrder.DESC)
					.setFrom(0).setSize(10000)
					.addAggregation(aggregation)
					.get();
			Terms count_by_isdelete = response1.getAggregations().get("count_by_isdelete");
			long count = 0;
			for (Terms.Bucket entry : count_by_isdelete.getBuckets()) {
				String key = (String) entry.getKey(); // bucket key
				long docCount = entry.getDocCount(); // Doc count
				System.out.println("key " + key + " doc_count " + docCount);
				if("0".equals(key) || "0" == key){
					count = docCount;
				}
			}
			data.setDatatwo(count);
		SearchResponse response = client.prepareSearch("record")
				.setTypes("agentOrder")
				.setQuery(QueryBuilders.matchAllQuery()) //查询所有
				.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
        		.setFrom(agentOrderRecordVO.getPage()*10).setSize(10)
        		.addSort("birthTime", SortOrder.DESC)
				.get();
		SearchHit[] searchHits = response.getHits().getHits();
		List<Object> searchHit = new ArrayList<Object>();
		for(int i = 0; i < searchHits.length; i++) {
			System.out.println(searchHits[i].getSourceAsString()); 
			searchHit.add(searchHits[i].getSource());
		}
		data.setDataOne(searchHit);
		return data;
	}
}
