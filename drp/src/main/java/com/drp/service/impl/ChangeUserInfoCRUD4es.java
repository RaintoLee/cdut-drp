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
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;

import com.drp.models.BrowsingRecordVO;
import com.drp.models.ChangeUserInfoVO;
import com.drp.models.Data;

public class ChangeUserInfoCRUD4es extends RecordServiceImpl4es<ChangeUserInfoVO>{

	@Override
	public void createDocument(TransportClient client, ChangeUserInfoVO changeUserInfoVO) throws IOException{
		IndexResponse response = client.prepareIndex("record", "changeUserInfo", changeUserInfoVO.getChangeId())
										.setSource(XContentFactory.jsonBuilder()
												.startObject()
													.field("userId", changeUserInfoVO.getUserId())
													.field("birthTime", changeUserInfoVO.getBirthTime())
													.field("after", changeUserInfoVO.getAfter())
													.field("before", changeUserInfoVO.getBefore())
													.field("isdelete", "0")
												.endObject())
										.get();
		System.out.println("创建document完成");
		System.out.println(response.getResult()); 
	}
	
	@Override
	public void updateDocument(TransportClient client, ChangeUserInfoVO changeUserInfoVO) throws IOException{
		UpdateResponse response = client.prepareUpdate("record", "changeUserInfo", changeUserInfoVO.getChangeId())
										.setDoc(XContentFactory.jsonBuilder()
											.startObject()
												.field("userId", changeUserInfoVO.getUserId())
												.field("birthTime", changeUserInfoVO.getBirthTime())
												.field("after", changeUserInfoVO.getAfter())
												.field("before", changeUserInfoVO.getBefore())
											.endObject())
										.get();
		System.out.println(response.getResult());  
	}
	
	@Override
	public void deleteDocument(TransportClient client, ChangeUserInfoVO changeUserInfoVO) throws IOException{
		UpdateResponse response = client.prepareUpdate("record", "changeUserInfo", changeUserInfoVO.getChangeId())
				.setDoc(XContentFactory.jsonBuilder()
					.startObject()
						.field("isdelete", "1")
					.endObject())
				.get();
		System.out.println(response.getResult());  
	}
	
	@Override
	public void getDocument(TransportClient client, ChangeUserInfoVO changeUserInfoVO) throws IOException{
		GetResponse response = client.prepareGet("record", "changeUserInfo", changeUserInfoVO.getChangeId()).get();
		System.out.println(response.getSourceAsString());
	}
	
	public void realDeleteDocument(TransportClient client, ChangeUserInfoVO changeUserInfoVO) throws IOException{
		DeleteResponse response = client.prepareDelete("record", "changeUserInfo", changeUserInfoVO.getChangeId()).get();
		System.out.println(response.getResult());
	}
	
	/*
	 * 搜索
	 */
	@Override
	public Data executeSearch(TransportClient client, ChangeUserInfoVO changeUserInfoVO) throws IOException{
		Data data = new Data();
			AggregationBuilder aggregation = AggregationBuilders
					.terms("count_by_isdelete")
					.field("isdelete");
			SearchResponse response1 = client.prepareSearch("record")
					.setTypes("changeUserInfo")
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
				.setTypes("changeUserInfo")
//				.setQuery(QueryBuilders.matchAllQuery()) //查询所有
				.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
        		.setFrom(changeUserInfoVO.getPage()*10).setSize(10)
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
