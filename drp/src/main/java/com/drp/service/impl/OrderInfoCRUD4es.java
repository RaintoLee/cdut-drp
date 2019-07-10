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

import com.drp.models.Data;
import com.drp.models.ProtocolVO;

public class OrderInfoCRUD4es extends RecordServiceImpl4es<ProtocolVO>{

	@Override
	public void createDocument(TransportClient client, ProtocolVO protocolVO) throws IOException{
		IndexResponse response = client.prepareIndex("info", "order", protocolVO.getAgmt_id())
										.setSource(XContentFactory.jsonBuilder()
												.startObject()
													.field("agent_num", protocolVO.getAgent_num())
													.field("auth_fee", protocolVO.getAuth_fee())
													.field("Deskey", protocolVO.getDeskey())
													.field("info_isvalid_flg", protocolVO.getInfo_isvalid_flg())
													.field("parta_id", protocolVO.getParta_id())
													.field("parta_patio",protocolVO.getParta_patio())
													.field("partb_id",protocolVO.getPartb_id())
													.field("partb_patio",protocolVO.getPartb_patio())
													.field("protocol_deadline",protocolVO.getProtocol_deadline())
													.field("protocol_type",protocolVO.getProtocol_type())
													.field("receive_flg",protocolVO.getReceive_flg())
													.field("role_id",protocolVO.getRole_id())
													.field("usr_id",protocolVO.getUsr_id())
													.field("works_id",protocolVO.getWorks_id())
													.field("isdelete", "0")
											.endObject())
										.get();
		System.out.println("创建document完成");
		System.out.println(response.getResult()); 
	}
	
	@Override
	public void updateDocument(TransportClient client, ProtocolVO protocolVO) throws IOException{
		UpdateResponse response = client.prepareUpdate("info", "order", protocolVO.getAgmt_id())
										.setDoc(XContentFactory.jsonBuilder()
											.startObject()
											.field("info_isvalid_flg", protocolVO.getInfo_isvalid_flg())
											.field("receive_flg",protocolVO.getReceive_flg())
											.endObject())
										.get();
		System.out.println(response.getResult());  
	}
	
	@Override
	public void deleteDocument(TransportClient client, ProtocolVO protocolVO) throws IOException{
		UpdateResponse response = client.prepareUpdate("info", "order", protocolVO.getAgmt_id())
				.setDoc(XContentFactory.jsonBuilder()
					.startObject()
						.field("isdelete", "1")
					.endObject())
				.get();
		System.out.println(response.getResult());  
	}
	
	@Override
	public void getDocument(TransportClient client, ProtocolVO protocolVO) throws IOException{
		GetResponse response = client.prepareGet("info", "order", protocolVO.getAgmt_id()).get();
		System.out.println(response.getSourceAsString());
	}
	
	public void realDeleteDocument(TransportClient client, ProtocolVO protocolVO) throws IOException{
		DeleteResponse response = client.prepareDelete("info", "order", protocolVO.getAgmt_id()).get();
		System.out.println(response.getResult());
	}
	
	/*
	 * 搜索
	 */
	@Override
	public Data executeSearch(TransportClient client, ProtocolVO protocolVO) throws IOException{
		Data data = new Data();
		AggregationBuilder aggregation = AggregationBuilders
				.terms("count_by_isdelete")
				.field("isdelete");
		SearchResponse response1 = client.prepareSearch("info")
				.setTypes("order")
				.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
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
				.setTypes("order")
                .setQuery(QueryBuilders.matchQuery("isdelete", "0"))
        		.setFrom(protocolVO.getPage()).setSize(10)
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
