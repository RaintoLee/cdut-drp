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
import org.springframework.beans.factory.annotation.Autowired;

import com.drp.models.BrowsingRecordVO;
import com.drp.models.Data;
import com.drp.models.LoginRecordVO;

public class BrowsingRecordCRUD4es extends RecordServiceImpl4es<BrowsingRecordVO> {

	@Autowired
	
	/**
	 * 创建浏览记录
	 * ES中的id由后台生成然后注入到index中。
	 */
	@Override
	public void createDocument(TransportClient client,BrowsingRecordVO browsingRecordVO) throws IOException {
//		BrowsingRecordVO browsingRecordVO = (BrowsingRecordVO) t;
		IndexResponse response = client.prepareIndex("record","browsing", browsingRecordVO.getBrowse_id())
									.setSource(XContentFactory.jsonBuilder()
											.startObject()
												.field("worksId", browsingRecordVO.getWorks_id())
												.field("userId", browsingRecordVO.getUsr_id())
												.field("worksName", browsingRecordVO.getWorksUserVO().getWorks_name())
												.field("browsingTime", browsingRecordVO.getBrowsing_time())
												.field("worksSrc", browsingRecordVO.getWorksUserVO().getWorks_store_src())
												.field("isdelete", "0")
											.endObject())
									.get();
		System.out.println("创建document完成");
		System.out.println(response.getResult()); 
	}

	/**
	 * 根据browsing_id逻辑删除浏览记录
	 * isdelete=0  未删除
	 * isdelete=1 删除
	 */
	@Override
	public void deleteDocument(TransportClient client, BrowsingRecordVO browsingRecordVO) throws IOException {
		UpdateResponse response = client.prepareUpdate("record", "browsing", browsingRecordVO.getBrowse_id())
											.setDoc(XContentFactory.jsonBuilder()
												.startObject()
													.field("isdelete", "1")
												.endObject())
											.get();
		System.out.println(response.getResult());
	}

	/**
	 * 根据browsing_id更新浏览记录（全替换模式）
	 */
	@Override
	public void updateDocument(TransportClient client, BrowsingRecordVO browsingRecordVO) throws IOException {
		UpdateResponse response = client.prepareUpdate("record", "browsing", browsingRecordVO.getBrowse_id())
									.setDoc(XContentFactory.jsonBuilder()
										.startObject()
											.field("browsingTime", browsingRecordVO.getBrowsing_time())
											.field("worksId", browsingRecordVO.getWorks_id())
											.field("userId", browsingRecordVO.getUsr_id())
											.field("timeOut", browsingRecordVO.getTimeOut())
											.field("worksSrc", browsingRecordVO.getWorksUserVO().getWorks_store_src())
											.field("worksName", browsingRecordVO.getWorksUserVO().getWorks_name())
										.endObject())
									.get();
		System.out.println(response.getResult());
		
	}

	/**
	 * 获取指定browsing_id的浏览记录
	 */
	@Override
	public void getDocument(TransportClient client, BrowsingRecordVO browsingRecordVO) {
		GetResponse response = client.prepareGet("record", "browsing", browsingRecordVO.getBrowse_id()).get();
		System.out.println(response.getSourceAsString());
		
	}

	/**
	 * 物理删除浏览记录
	 * @param client
	 * @param browsingRecordVO
	 * @throws IOException
	 */
	public void RealDeleteDocument(TransportClient client, BrowsingRecordVO browsingRecordVO) throws IOException {
		DeleteResponse response = client.prepareDelete("record", "browsing", browsingRecordVO.getBrowse_id()).get();
		System.out.println(response.getResult());
	}

	/*
	 * 搜索
	 */
	@Override
	public Data executeSearch(TransportClient client, BrowsingRecordVO browsingRecordVO) throws IOException{
		Data data = new Data();
			AggregationBuilder aggregation = AggregationBuilders
					.terms("count_by_isdelete")
					.field("isdelete");
			SearchResponse response1 = client.prepareSearch("record")
					.setTypes("browsing")
					.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
					.addSort("browsingTime", SortOrder.DESC)
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
				.setTypes("browsing")
//				.setQuery(QueryBuilders.matchAllQuery()) //查询所有
				.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
        		.setFrom(browsingRecordVO.getPage()*10).setSize(10)
        		.addSort("browsingTime", SortOrder.DESC)
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
	/**
	 * 搜索模板
	 */
//	@Override
//	public ArrayList<BrowsingRecordVO> executeSearch(TransportClient client, BrowsingRecordVO browsingRecordVO) throws Exception {
//		SearchResponse response = client.prepareSearch("record")
//				.setTypes("browsing")
//				.setQuery(QueryBuilders.matchQuery("worksName", "txt"))
////				.setPostFilter(QueryBuilders.rangeQuery("age").from(30).to(40))
//				.setFrom(1).setSize(10)
//				.get();
//		
//		SearchHit[] searchHits = response.getHits().getHits();
//		for(int i = 0; i < searchHits.length; i++) {
//			System.out.println(searchHits[i].getSourceAsString()); 
//		}
//		return null;
//		}


}
