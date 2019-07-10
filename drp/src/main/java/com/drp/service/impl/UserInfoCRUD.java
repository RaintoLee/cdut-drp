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
import com.drp.models.LoginRecordVO;
import com.drp.models.UserVO;

public class UserInfoCRUD extends RecordServiceImpl<UserVO>{

	@Override
	public void createDocument(UserVO userVO) throws IOException{
//		IndexResponse response = client.prepareIndex("info", "user", userVO.getUsr_id())
//										.setSource(XContentFactory.jsonBuilder()
//												.startObject()
//												.field("acct_bal", userVO.getAcct_bal())
//												.field("pay_pwd", userVO.getPay_pwd())
//												.field("usr_addr", userVO.getUsr_addr())
//												.field("usr_ava_src", userVO.getUsr_ava_src())
//												.field("usr_cert_cate", userVO.getUsr_cert_cate())
//												.field("usr_cert_num", userVO.getUsr_cert_num())
//												.field("usr_certpic_src", userVO.getUsr_certpic_src())
//												.field("usr_email", userVO.getUsr_email())
//												.field("gender", userVO.getUsr_gender())
//												.field("usr_isMembership", userVO.getUsr_isMembership())
//												.field("usr_isvalid_flg", userVO.getUsr_isvalid_flg())
//												.field("usr_nicknm", userVO.getUsr_nicknm())
//												.field("usr_nm", userVO.getUsr_nm())
//												.field("usr_phone", userVO.getUsr_phone())
//												.field("usr_pub_key", userVO.getUsr_pub_key())
//												.field("usr_pwd", userVO.getUsr_pwd())
//												.field("usr_qlfy", userVO.getUsr_qlfy())
//												.field("usr_qlfypic_src", userVO.getUsr_qlfypic_src())
//												.field("usr_rgst_dt", userVO.getUsr_rgst_dt())
//												.field("usr_rgst_tm", userVO.getUsr_rgst_tm())
//												.field("usr_stat", userVO.getUsr_stat())
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
			Date date = new Date();
			DateFormat dFormat1 = new SimpleDateFormat("yyyyMMdd"); //HH表示24小时制；  
			String date1 = dFormat1.format(date);
			FileOutputStream out = new FileOutputStream("/var/lib/tomcat8/log4es/" + date1, true);
			
			DateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //HH表示24小时制；  
			String formatDate1 = dFormat.format(userVO.getUsr_rgst_dt());
			String formatDate2 = dFormat.format(userVO.getUsr_rgst_tm());
			StringBuffer sb = new StringBuffer();
			sb.append("createDocument,info,user,")
				.append(userVO.getUsr_id()).append(",")
				.append(userVO.getAcct_bal()).append(",")
				.append(userVO.getPay_pwd()).append(",")
				.append(userVO.getUsr_addr()).append(",")
				.append(userVO.getUsr_ava_src()).append(",")
				.append(userVO.getUsr_cert_cate()).append(",")
				.append(userVO.getUsr_cert_num()).append(",")
				.append(userVO.getUsr_certpic_src()).append(",")
				.append(userVO.getUsr_email()).append(",")
				.append(userVO.getUsr_gender()).append(",")
				.append(userVO.getUsr_isMembership()).append(",")
				.append(userVO.getUsr_isvalid_flg()).append(",")
				.append(userVO.getUsr_nicknm()).append(",")
				.append(userVO.getUsr_nm()).append(",")
				.append(userVO.getUsr_phone()).append(",")
				.append(userVO.getUsr_pub_key()).append(",")
				.append(userVO.getUsr_pwd()).append(",")
				.append(userVO.getUsr_qlfy()).append(",")
				.append(userVO.getUsr_qlfypic_src()).append(",")
				.append(formatDate1).append(",")
				.append(formatDate2).append(",")
				.append(userVO.getUsr_stat()).append(",")
				.append("0").append("\n");
			out.write(sb.toString().getBytes());
		}
	}
	
	@Override
	public void updateDocument(UserVO userVO) throws IOException{
//		UpdateResponse response = client.prepareUpdate("info", "user", userVO.getUsr_id())
//										.setDoc(XContentFactory.jsonBuilder()
//											.startObject()
//												.field("usr_addr", userVO.getUsr_addr())
//												.field("usr_cert_cate", userVO.getUsr_cert_cate())
//												.field("usr_cert_num", userVO.getUsr_cert_num())
//												.field("gender", userVO.getUsr_gender())
//												.field("usr_nicknm", userVO.getUsr_nicknm())
//												.field("usr_phone", userVO.getUsr_phone())
//												.field("usr_qlfy", userVO.getUsr_qlfy())
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
			sb.append("updateDocument,info,user,")
				.append(userVO.getUsr_id()).append(",")
				.append(userVO.getUsr_addr()).append(",")
				.append(userVO.getUsr_cert_cate()).append(",")
				.append(userVO.getUsr_cert_num()).append(",")
				.append(userVO.getUsr_gender()).append(",")
				.append(userVO.getUsr_nicknm()).append(",")
				.append(userVO.getUsr_phone()).append(",")
				.append(userVO.getUsr_qlfy()).append("\n");
			out.write(sb.toString().getBytes());
		}
	}
	
	@Override
	public void deleteDocument(UserVO userVO) throws IOException{
//		UpdateResponse response = client.prepareUpdate("info", "user", userVO.getUsr_id())
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
			sb.append("deleteDocument,info,user,")
				.append(userVO.getUsr_id()).append(",")
				.append("1").append("\n");
			out.write(sb.toString().getBytes());
		}
	}
	
//	@Override
//	public void getDocument(TransportClient client, UserVO userVO) throws IOException{
//		GetResponse response = client.prepareGet("info", "user", userVO.getUsr_id()).get();
//		System.out.println(response.getSourceAsString());
//	}
//	
//	public void realDeleteDocument(TransportClient client, UserVO userVO) throws IOException{
//		DeleteResponse response = client.prepareDelete("info", "user", userVO.getUsr_id()).get();
//		System.out.println(response.getResult());
//	}
	
	/*
	 * 搜索
	 */
	@Override
	public Data executeSearch(TransportClient client, UserVO userVO) throws IOException{
		Data data = new Data();
		if(!"".equals(userVO.getUsr_nm()) && null != userVO.getUsr_nm()){
			SearchResponse response = client.prepareSearch("info")
					.setTypes("user")
					.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
					.setQuery(QueryBuilders.matchQuery("usr_nm", userVO.getUsr_nm()))
					.get();
			SearchHit[] searchHits = response.getHits().getHits();
			List<Object> searchHit = new ArrayList<Object>();
			for(int i = 0; i < searchHits.length; i++) {
				System.out.println(searchHits[i].getSourceAsString()); 
				searchHit.add(searchHits[i].getSource());
			}
			data.setDataOne(searchHit);
			return data;
		}else{
			AggregationBuilder aggregation = AggregationBuilders
					.terms("count_by_isdelete")
					.field("isdelete");
			SearchResponse response1 = client.prepareSearch("info")
					.setTypes("user")
					.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
					.addSort("usr_rgst_dt", SortOrder.DESC)
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
					.setTypes("user")
					.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
					.addSort("usr_rgst_dt", SortOrder.DESC)
					.setFrom(userVO.getPage()).setSize(10)
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
