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

import com.drp.models.AgentOrderRecordVO;
import com.drp.models.BrowsingRecordVO;
import com.drp.models.Data;

public class AgentOrderRecordCRUD4es extends RecordServiceImpl4es<AgentOrderRecordVO>{

	@Override
	public void createDocument(TransportClient client, AgentOrderRecordVO agentOrderRecordVO) throws IOException{
		IndexResponse response = client.prepareIndex("record", "agentOrder", agentOrderRecordVO.getOrderId())
										.setSource(XContentFactory.jsonBuilder()
												.startObject()
												.field("userId", agentOrderRecordVO.getUserId())
												.field("agentId", agentOrderRecordVO.getAgentId())
												.field("worksId", agentOrderRecordVO.getWorksId())
												.field("type", agentOrderRecordVO.getType())
												.field("birthTime", agentOrderRecordVO.getBirthTime())
												.field("isdelete", "0")
											.endObject())
										.get();
		System.out.println("创建document完成");
		System.out.println(response.getResult()); 
	}
	
	@Override
	public void updateDocument(TransportClient client, AgentOrderRecordVO agentOrderRecordVO) throws IOException{
		UpdateResponse response = client.prepareUpdate("record", "agentOrder", agentOrderRecordVO.getOrderId())
										.setDoc(XContentFactory.jsonBuilder()
											.startObject()
												.field("type", agentOrderRecordVO.getType())
											.endObject())
										.get();
		System.out.println(response.getResult());  
	}
	
	@Override
	public void deleteDocument(TransportClient client, AgentOrderRecordVO agentOrderRecordVO) throws IOException{
		UpdateResponse response = client.prepareUpdate("record", "agentOrder", agentOrderRecordVO.getOrderId())
				.setDoc(XContentFactory.jsonBuilder()
					.startObject()
						.field("isdelete", "1")
					.endObject())
				.get();
		System.out.println(response.getResult());  
	}
	
	@Override
	public void getDocument(TransportClient client, AgentOrderRecordVO agentOrderRecordVO) throws IOException{
		GetResponse response = client.prepareGet("record", "agentOrder", agentOrderRecordVO.getOrderId()).get();
		System.out.println(response.getSourceAsString());
	}
	
	public void realDeleteDocument(TransportClient client, AgentOrderRecordVO agentOrderRecordVO) throws IOException{
		DeleteResponse response = client.prepareDelete("record", "agentOrder", agentOrderRecordVO.getOrderId()).get();
		System.out.println(response.getResult());
	}
	
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
//				.setQuery(QueryBuilders.matchAllQuery()) //查询所有
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
