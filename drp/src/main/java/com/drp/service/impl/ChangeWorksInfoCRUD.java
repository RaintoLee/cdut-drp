package com.drp.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import com.drp.models.ChangeUserInfoVO;
import com.drp.models.ChangeWorksInfoVO;
import com.drp.models.Data;

public class ChangeWorksInfoCRUD extends RecordServiceImpl<ChangeWorksInfoVO>{

	@Override
	public void createDocument(ChangeWorksInfoVO changeWorksInfoVO) throws IOException{
//		IndexResponse response = client.prepareIndex("record", "changeUserInfo", changeWorksInfoVO.getChangeId())
//										.setSource(XContentFactory.jsonBuilder()
//												.startObject()
//													.field("userId", changeWorksInfoVO.getUserId())
//													.field("birthTime", changeWorksInfoVO.getBirthTime())
//													.field("worksId", changeWorksInfoVO.getWorksId())
//													.field("after", changeWorksInfoVO.getAfter())
//													.field("before", changeWorksInfoVO.getBefore())
//													.field("isdelete", "0")
//												.endObject())
//										.get();
//		System.out.println("创建document完成");
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
			
			DateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //HH表示24小时制；  
			String formatDate = dFormat.format(changeWorksInfoVO.getBirthTime());
			StringBuffer sb = new StringBuffer();
			sb.append("createDocument,record,changeWorksInfo,")
				.append(changeWorksInfoVO.getChangeId()).append(",")
				.append(changeWorksInfoVO.getUserId()).append(",")
				.append(formatDate).append(",")
				.append(changeWorksInfoVO.getWorksId()).append(",")
				.append(changeWorksInfoVO.getAfter()).append(",")
				.append(changeWorksInfoVO.getBefore()).append(",")
				.append("0").append("\n");
			out.write(sb.toString().getBytes());
		}
	}
	
	@Override
	public void updateDocument(ChangeWorksInfoVO changeWorksInfoVO) throws IOException{
//		UpdateResponse response = client.prepareUpdate("record", "changeUserInfo", changeWorksInfoVO.getChangeId())
//										.setDoc(XContentFactory.jsonBuilder()
//											.startObject()
//												.field("userId", changeWorksInfoVO.getUserId())
//												.field("worksId", changeWorksInfoVO.getWorksId())
//												.field("birthTime", changeWorksInfoVO.getBirthTime())
//												.field("after", changeWorksInfoVO.getAfter())
//												.field("before", changeWorksInfoVO.getBefore())
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
			
			DateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //HH表示24小时制；  
			String formatDate = dFormat.format(changeWorksInfoVO.getBirthTime());
			StringBuffer sb = new StringBuffer();
			sb.append("updateDocument,record,changeWorksInfo,")
				.append(changeWorksInfoVO.getChangeId()).append(",")
				.append(changeWorksInfoVO.getUserId()).append(",")
				.append(changeWorksInfoVO.getWorksId()).append(",")
				.append(formatDate).append(",")
				.append(changeWorksInfoVO.getAfter()).append(",")
				.append(changeWorksInfoVO.getBefore()).append("\n");
			out.write(sb.toString().getBytes());
		}
	}
	
	@Override
	public void deleteDocument(ChangeWorksInfoVO changeWorksInfoVO) throws IOException{
//		UpdateResponse response = client.prepareUpdate("record", "changeUserInfo", changeWorksInfoVO.getChangeId())
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
			
			DateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //HH表示24小时制；  
			String formatDate = dFormat.format(changeWorksInfoVO.getBirthTime());
			StringBuffer sb = new StringBuffer();
			sb.append("deleteDocument,record,changeWorksInfo,")
				.append(changeWorksInfoVO.getChangeId()).append(",")
				.append("1").append("\n");
			out.write(sb.toString().getBytes());
		}
	}
	
//	@Override
//	public void getDocument(TransportClient client, ChangeWorksInfoVO changeWorksInfoVO) throws IOException{
//		GetResponse response = client.prepareGet("record", "changeUserInfo", changeWorksInfoVO.getChangeId()).get();
//		System.out.println(response.getSourceAsString());
//	}
//	
//	public void realDeleteDocument(TransportClient client, ChangeWorksInfoVO changeWorksInfoVO) throws IOException{
//		DeleteResponse response = client.prepareDelete("record", "changeUserInfo", changeWorksInfoVO.getChangeId()).get();
//		System.out.println(response.getResult());
//	}
	
	/*
	 * 搜索
	 */
	@Override
	public Data executeSearch(TransportClient client, ChangeWorksInfoVO changeWorksInfoVO) throws IOException{
		Data data = new Data();
			AggregationBuilder aggregation = AggregationBuilders
					.terms("count_by_isdelete")
					.field("isdelete");
			SearchResponse response1 = client.prepareSearch("record")
					.setTypes("changeWorksInfo")
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
				.setTypes("changeWorksInfo")
//				.setQuery(QueryBuilders.matchAllQuery()) //查询所有
				.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
        		.setFrom(changeWorksInfoVO.getPage()*10).setSize(10)
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
