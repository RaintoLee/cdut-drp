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

import com.drp.models.Data;
import com.drp.models.WorksUserVO;
import com.drp.util.SearchWorksByES;

public class WorksInfoCRUD extends RecordServiceImpl<WorksUserVO>{

	@Override
	public void createDocument(WorksUserVO worksUserVO) throws IOException{
//		IndexResponse response = client.prepareIndex("info", "works", worksUserVO.getWorks_id())
//										.setSource(XContentFactory.jsonBuilder()
//												.startObject()
//												.field("works_id", worksUserVO.getWorks_id())
//												.field("discount", worksUserVO.getDiscount())
//												.field("keywords", worksUserVO.getKeywords())
//												.field("type", worksUserVO.getType())
//												.field("usr_nm", worksUserVO.getUsr_name())
//												.field("usr_id", worksUserVO.getUsr_id())
//												.field("works_agent_qty", worksUserVO.getWorks_agent_qty())
//												.field("works_auth_qty", worksUserVO.getWorks_auth_qty())
//												.field("works_cate", worksUserVO.getWorks_cate())
//												.field("works_ciphertext", worksUserVO.getWorks_ciphertext())
//												.field("works_format", worksUserVO.getWorks_format())
//												.field("works_hdgt", worksUserVO.getWorks_hdgt())
//												.field("works_ischrg", worksUserVO.getWorks_ischrg())
//												.field("works_issu_dt", worksUserVO.getWorks_issu_dt())
//												.field("works_issu_tm", worksUserVO.getWorks_issu_tm())
//												.field("works_isvalid_flg", worksUserVO.getWorks_isvalid_flg())
//												.field("works_memo", worksUserVO.getWorks_memo())
//												.field("works_name", worksUserVO.getWorks_name())
//												.field("works_pageviews", worksUserVO.getWorks_pageviews())
//												.field("works_recinfo_src", worksUserVO.getWorks_recinfo_src())
//												.field("works_rgst_dt", worksUserVO.getWorks_rgst_dt())
//												.field("works_rgst_tm", worksUserVO.getWorks_rgst_tm())
//												.field("works_secr_lvl", worksUserVO.getWorks_secr_lvl())
//												.field("works_spec", worksUserVO.getWorks_spec())
//												.field("works_src", worksUserVO.getWorks_src())
//												.field("works_store_src", worksUserVO.getWorks_store_src())
//												.field("works_sym_key", worksUserVO.getWorks_sym_key())
//												.field("works_theme", worksUserVO.getWorks_theme())
//												.field("isdelete", "0")
//											.endObject())
//										.get();
//		System.out.println("创建document完成");
//		System.out.println(response.getResult()); 
		File file = new File("/var/lib/tomcat8/log4es");
		System.out.println("/var/lib/tomcat8/log4es");
		if(!file.exists()){
			file.mkdir();
		}
		if(file.isDirectory()){
//		File file = new File("E:/DRP/log4es");
//		System.out.println("E:/DRP/log4es");
//		if(!file.exists()){
//			file.mkdir();
//		}
//		if(file.isDirectory()){
			Date date = new Date();
			DateFormat dFormat1 = new SimpleDateFormat("yyyyMMdd"); //HH表示24小时制；  
			String date1 = dFormat1.format(date);
			FileOutputStream out = new FileOutputStream("/var/lib/tomcat8/log4es/" + date1, true);
//			FileOutputStream out = new FileOutputStream("E:/DRP/log4es/" + date1, true);
			DateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //HH表示24小时制；  
			worksUserVO.setWorks_issu_dt(worksUserVO.getWorks_rgst_dt());
			worksUserVO.setWorks_issu_tm(worksUserVO.getWorks_rgst_tm());
			String formatDate1 = dFormat.format(worksUserVO.getWorks_issu_dt());
			String formatDate2 = dFormat.format(worksUserVO.getWorks_issu_tm());
			String formatDate3 = dFormat.format(worksUserVO.getWorks_rgst_dt());
			String formatDate4 = dFormat.format(worksUserVO.getWorks_rgst_tm());
			StringBuffer sb = new StringBuffer();
			sb.append("createDocument,info,works,")
				.append(worksUserVO.getWorks_id()).append(",")
				.append(worksUserVO.getWorks_id()).append(",")
				.append(worksUserVO.getDiscount()).append(",")
				.append(worksUserVO.getKeywords()).append(",")
				.append(worksUserVO.getType()).append(",")
				.append(worksUserVO.getUsr_name()).append(",")
				.append(worksUserVO.getUsr_id()).append(",")
				.append(worksUserVO.getWorks_agent_qty()).append(",")
				.append(worksUserVO.getWorks_auth_qty()).append(",")
				.append(worksUserVO.getWorks_cate()).append(",")
				.append(worksUserVO.getWorks_ciphertext()).append(",")
				.append(worksUserVO.getWorks_format()).append(",")
				.append(worksUserVO.getWorks_hdgt()).append(",")
				.append(worksUserVO.getWorks_ischrg()).append(",")
				.append(formatDate1).append(",")
				.append(formatDate2).append(",")
				.append(worksUserVO.getWorks_isvalid_flg()).append(",")
				.append(worksUserVO.getWorks_memo()).append(",")
				.append(worksUserVO.getWorks_name()).append(",")
				.append(worksUserVO.getWorks_pageviews()).append(",")
				.append(worksUserVO.getWorks_recinfo_src()).append(",")
				.append(formatDate3).append(",")
				.append(formatDate4).append(",")
				.append(worksUserVO.getWorks_secr_lvl()).append(",")
				.append(worksUserVO.getWorks_spec()).append(",")
				.append(worksUserVO.getWorks_src()).append(",")
				.append(worksUserVO.getWorks_store_src()).append(",")
				.append(worksUserVO.getWorks_sym_key()).append(",")
				.append(worksUserVO.getWorks_theme()).append(",")
				.append("0").append("\n");
			out.write(sb.toString().getBytes());
		}
	}
	
	@Override
	public void updateDocument(WorksUserVO worksUserVO) throws IOException{
//		UpdateResponse response = client.prepareUpdate("info", "works", worksUserVO.getWorks_id())
//										.setDoc(XContentFactory.jsonBuilder()
//											.startObject()
//											.field("works_hdgt", worksUserVO.getWorks_hdgt())
//											.field("works_ischrg", worksUserVO.getWorks_ischrg())
//											.field("works_memo", worksUserVO.getWorks_memo())
//											.field("works_name", worksUserVO.getWorks_name())
//											.field("works_secr_lvl", worksUserVO.getWorks_secr_lvl())
//											.endObject())
//										.get();
//		System.out.println(response.getResult());  
		File file = new File("/var/lib/tomcat8/log4es");
		System.out.println("/var/lib/tomcat8/log4es");
		if(!file.exists()){
			file.mkdir();
		}
		if(file.isDirectory()){
//		File file = new File("E:/DRP/log4es");
//		System.out.println("E:/DRP/log4es");
//		if(!file.exists()){
//			file.mkdir();
//		}
//		if(file.isDirectory()){
			Date date = new Date();
			DateFormat dFormat1 = new SimpleDateFormat("yyyyMMdd"); //HH表示24小时制；  
			String date1 = dFormat1.format(date);
			FileOutputStream out = new FileOutputStream("/var/lib/tomcat8/log4es/" + date1, true);
//			FileOutputStream out = new FileOutputStream("E:/DRP/log4es/" + date1, true);
			DateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //HH表示24小时制；  
			
			StringBuffer sb = new StringBuffer();
			sb.append("updateDocument,info,works,")
				.append(worksUserVO.getWorks_id()).append(",")
				.append(worksUserVO.getWorks_hdgt()).append(",")
				.append(worksUserVO.getWorks_ischrg()).append(",")
				.append(worksUserVO.getWorks_memo()).append(",")
				.append(worksUserVO.getWorks_name()).append(",")
				.append(worksUserVO.getWorks_isvalid_flg()).append(",")
				.append(worksUserVO.getWorks_secr_lvl()).append("\n");
			out.write(sb.toString().getBytes());
		}
	}
	
	@Override
	public void deleteDocument(WorksUserVO worksUserVO) throws IOException{
//		UpdateResponse response = client.prepareUpdate("info", "works", worksUserVO.getWorks_id())
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
			sb.append("deleteDocument,info,works,")
				.append(worksUserVO.getWorks_id()).append(",")
				.append("1").append("\n");
			out.write(sb.toString().getBytes());
		}
	}
	
//	@Override
//	public void getDocument(TransportClient client, WorksUserVO worksUserVO) throws IOException{
//		GetResponse response = client.prepareGet("info", "works", worksUserVO.getWorks_id()).get();
//		System.out.println(response.getSourceAsString());
//	}
//	
//	public void realDeleteDocument(TransportClient client, WorksUserVO worksUserVO) throws IOException{
//		DeleteResponse response = client.prepareDelete("info", "works", worksUserVO.getWorks_id()).get();
//		System.out.println(response.getResult());
//	}
	
	/*
	 * 搜索
	 */
	@Override
	public Data executeSearch(TransportClient client, WorksUserVO worksUserVO) throws IOException{
		if("".equals(worksUserVO.getPage()) || worksUserVO.getPage() == null){
			worksUserVO.setPage(0);
		}
		//如果搜索内容不为空
		if(!"".equals(worksUserVO.getMsg()) && null != worksUserVO.getMsg()){
			Data data = new Data();
			data = SearchWorksByES.searchWorksByES(client, worksUserVO.getMsg(), worksUserVO.getPage());
			return data;
		}else{
			Data data = new Data();
			AggregationBuilder aggregation = AggregationBuilders
					.terms("count_by_isdelete")
					.field("isdelete");
			SearchResponse response1 = client.prepareSearch("info")
					.setTypes("works")
					.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
					.addSort("works_rgst_tm", SortOrder.DESC)
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
			SearchResponse response = client.prepareSearch("info")
					.setTypes("works")
	                .setQuery(QueryBuilders.matchQuery("isdelete", "0"))
					.addSort("works_rgst_tm", SortOrder.DESC)
	        		.setFrom(worksUserVO.getPage()).setSize(10)
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
}
