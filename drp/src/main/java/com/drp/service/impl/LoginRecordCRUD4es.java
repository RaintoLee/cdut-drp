package com.drp.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.elasticsearch.search.sort.SortOrder;

import com.drp.models.Data;
import com.drp.models.LoginRecordVO;

public class LoginRecordCRUD4es extends RecordServiceImpl4es<LoginRecordVO>{
	
	/*
	 * 根据LoginId创建ES的索引
	 */
	@Override
	public void createDocument(TransportClient client,LoginRecordVO loginRecordVO) throws IOException{
		IndexResponse response = client.prepareIndex("record", "login", loginRecordVO.getLoginId())
										.setSource(XContentFactory.jsonBuilder()
												.startObject()
													.field("userId", loginRecordVO.getUserId())
													.field("loginTime", loginRecordVO.getLoginTime())
													.field("location", loginRecordVO.getLocation())
													.field("isdelete", "0")
												.endObject())
										.get();
		System.out.println("创建document完成");
		System.out.println(response.getResult()); 
	}
	
	/*
	 * 根据loginId更新登录信息
	 */
	@Override
	public void updateDocument(TransportClient client,LoginRecordVO loginRecordVO) throws IOException{
		UpdateResponse response = client.prepareUpdate("record", "login", loginRecordVO.getLoginId())
									.setDoc(XContentFactory.jsonBuilder()
											.startObject()
												.field("userId", loginRecordVO.getUserId())
												.field("loginTime", loginRecordVO.getLoginTime())
												.field("location", loginRecordVO.getLocation())
												.field("isdelete", "0")
											.endObject())
									.get();
		System.out.println("创建document完成");
		System.out.println(response.getResult()); 
	}
	
	/*
	 * 根据loginId逻辑删除登录记录
	 */
	@Override
	public void deleteDocument(TransportClient client,LoginRecordVO loginRecordVO) throws IOException{
		UpdateResponse response = client.prepareUpdate("record", "login", loginRecordVO.getLoginId())
				.setDoc(XContentFactory.jsonBuilder()
						.startObject()
							.field("isdelete", "1")
						.endObject())
				.get();
		System.out.println("创建document完成");
		System.out.println(response.getResult()); 
	}
	
	/*
	 *根据loginId获取登录记录 
	 */
	@Override
	public void getDocument(TransportClient client,LoginRecordVO loginRecordVO) throws IOException{
		GetResponse response = client.prepareGet("record", "login", loginRecordVO.getLoginId()).get();
		System.out.println("创建document完成");
		System.out.println(response.getSourceAsString()); 
	}
	
	/*
	 * 根据loginId物理删除登录记录
	 */
	public void realDeleteDocument(TransportClient client, LoginRecordVO loginRecordVO) throws IOException{
		DeleteResponse response = client.prepareDelete("record", "login", loginRecordVO.getLoginId()).get();
		System.out.println("创建document完成");
		System.out.println(response.getResult()); 
	}
	
	/*
	 * 搜索
	 */
	@Override
	public Data executeSearch(TransportClient client, LoginRecordVO loginRecord) throws IOException{
		Data data = new Data();
		if("".equals(loginRecord.getPage()) || loginRecord.getPage() == null){
			loginRecord.setPage(0);
			AggregationBuilder aggregation = AggregationBuilders
					.terms("count_by_isdelete")
					.field("isdelete");
			SearchResponse response = client.prepareSearch("record")
					.setTypes("login")
					.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
					.addSort("loginTime", SortOrder.DESC)
					.addAggregation(aggregation)
					.get();
			Terms count_by_isdelete = response.getAggregations().get("count_by_isdelete");
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
		}
		SearchResponse response = client.prepareSearch("record")
				.setTypes("login")
//				.setQuery(QueryBuilders.matchAllQuery()) //查询所有
				.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
        		.setFrom(loginRecord.getPage()*10).setSize(10)
        		.addSort("loginTime", SortOrder.DESC)
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


