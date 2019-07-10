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
import com.drp.models.WorksUserVO;

public class WorksInfoCRUD4es extends RecordServiceImpl4es<WorksUserVO>{

	@Override
	public void createDocument(TransportClient client, WorksUserVO worksUserVO) throws IOException{
		IndexResponse response = client.prepareIndex("info", "works", worksUserVO.getWorks_id())
										.setSource(XContentFactory.jsonBuilder()
												.startObject()
												.field("works_id", worksUserVO.getWorks_id())
												.field("discount", worksUserVO.getDiscount())
												.field("keywords", worksUserVO.getKeywords())
												.field("type", worksUserVO.getType())
												.field("usr_nm", worksUserVO.getUsr_name())
												.field("usr_id", worksUserVO.getUsr_id())
												.field("works_agent_qty", worksUserVO.getWorks_agent_qty())
												.field("works_auth_qty", worksUserVO.getWorks_auth_qty())
												.field("works_cate", worksUserVO.getWorks_cate())
												.field("works_ciphertext", worksUserVO.getWorks_ciphertext())
												.field("works_format", worksUserVO.getWorks_format())
												.field("works_hdgt", worksUserVO.getWorks_hdgt())
												.field("works_ischrg", worksUserVO.getWorks_ischrg())
												.field("works_issu_dt", worksUserVO.getWorks_issu_dt())
												.field("works_issu_tm", worksUserVO.getWorks_issu_tm())
												.field("works_isvalid_flg", worksUserVO.getWorks_isvalid_flg())
												.field("works_memo", worksUserVO.getWorks_memo())
												.field("works_name", worksUserVO.getWorks_name())
												.field("works_pageviews", worksUserVO.getWorks_pageviews())
												.field("works_recinfo_src", worksUserVO.getWorks_recinfo_src())
												.field("works_rgst_dt", worksUserVO.getWorks_rgst_dt())
												.field("works_rgst_tm", worksUserVO.getWorks_rgst_tm())
												.field("works_secr_lvl", worksUserVO.getWorks_secr_lvl())
												.field("works_spec", worksUserVO.getWorks_spec())
												.field("works_src", worksUserVO.getWorks_src())
												.field("works_store_src", worksUserVO.getWorks_store_src())
												.field("works_sym_key", worksUserVO.getWorks_sym_key())
												.field("works_theme", worksUserVO.getWorks_theme())
												.field("isdelete", "0")
											.endObject())
										.get();
		System.out.println("创建document完成");
		System.out.println(response.getResult()); 
	}
	
	@Override
	public void updateDocument(TransportClient client, WorksUserVO worksUserVO) throws IOException{
		UpdateResponse response = client.prepareUpdate("info", "works", worksUserVO.getWorks_id())
										.setDoc(XContentFactory.jsonBuilder()
											.startObject()
											.field("works_hdgt", worksUserVO.getWorks_hdgt())
											.field("works_ischrg", worksUserVO.getWorks_ischrg())
											.field("works_memo", worksUserVO.getWorks_memo())
											.field("works_name", worksUserVO.getWorks_name())
											.field("works_secr_lvl", worksUserVO.getWorks_secr_lvl())
											.endObject())
										.get();
		System.out.println(response.getResult());  
	}
	
	@Override
	public void deleteDocument(TransportClient client, WorksUserVO worksUserVO) throws IOException{
		UpdateResponse response = client.prepareUpdate("info", "works", worksUserVO.getWorks_id())
				.setDoc(XContentFactory.jsonBuilder()
					.startObject()
						.field("isdelete", "1")
					.endObject())
				.get();
		System.out.println(response.getResult());  
	}
	
	@Override
	public void getDocument(TransportClient client, WorksUserVO worksUserVO) throws IOException{
		GetResponse response = client.prepareGet("info", "works", worksUserVO.getWorks_id()).get();
		System.out.println(response.getSourceAsString());
	}
	
	public void realDeleteDocument(TransportClient client, WorksUserVO worksUserVO) throws IOException{
		DeleteResponse response = client.prepareDelete("info", "works", worksUserVO.getWorks_id()).get();
		System.out.println(response.getResult());
	}
	
	/*
	 * 搜索
	 */
	@Override
	public Data executeSearch(TransportClient client, WorksUserVO worksUserVO) throws IOException{
		if("".equals(worksUserVO.getPage()) || worksUserVO.getPage() == null){
			worksUserVO.setPage(0);
		}
		if(!"".equals(worksUserVO.getMsg()) && null != worksUserVO.getMsg()){
			SearchResponse response = client.prepareSearch("info")
					.setTypes("works")
//					.setQuery(QueryBuilders.matchAllQuery()) //查询所有
	                .setQuery(QueryBuilders.matchQuery("works_name", worksUserVO.getMsg()))
//	                .setQuery(QueryBuilders.matchQuery("user_name", worksUserVO.getMsg()))
//	                .setQuery(QueryBuilders.boolQuery()
//	                		.must(QueryBuilders.matchQuery("isdelete", "0")))
	        		.setFrom(worksUserVO.getPage()).setSize(10)
//					.setPostFilter(QueryBuilders.rangeQuery("age").from(30).to(40))
					.get();
			
//			SearchResponse searchResponse = client.prepareSearch(index) 
//	                .setTypes(type) 
//	                .setQuery(QueryBuilders.matchAllQuery()) //查询所有
//	                .setQuery(QueryBuilders.boolQuery()
//	                .must(QueryBuilders.matchQuery("uquestion", uquestion))//查询uquestion为139的
//	                .must(QueryBuilders.matchQuery("province", province)))//查询省份为江苏的
//	                //.setQuery(QueryBuilders.matchQuery("uquestion", "12599").operator(Operator.AND)) //根据tom分词查询name,默认or
//	                //.setQuery(QueryBuilders.matchQuery("province", "江苏").operator(Operator.AND)) //根据tom分词查询name,默认or
//	                //.setQuery(QueryBuilders.multiMatchQuery("tom", "name", "age")) //指定查询的字段 
//	                //.setQuery(QueryBuilders.queryString("name:to* AND age:[0 TO 19]")) //根据条件查询,支持通配符大于等于0小于等于19 
//	                //.setQuery(QueryBuilders.termQuery("name", "tom"))//查询时不分词 
//	                .setSearchType(SearchType.QUERY_THEN_FETCH) 
//	                .setFrom(0).setSize(10)//分页 
//	                //.addSort("age", SortOrder.DESC)//排序 
//	                .get(); 
			
			SearchHit[] searchHits = response.getHits().getHits();
			List<Object> searchHit = new ArrayList<Object>();
			Data data = new Data();
			for(int i = 0; i < searchHits.length; i++) {
				System.out.println(searchHits[i].getSourceAsString()); 
				searchHit.add(searchHits[i].getSource());
			}
			data.setDataOne(searchHit);
			return data;
		}else if(!"".equals(worksUserVO.getUsr_name()) && null != worksUserVO.getUsr_name()){
			SearchResponse response = client.prepareSearch("info")
					.setTypes("works")
//					.setQuery(QueryBuilders.matchAllQuery()) //查询所有
	                .setQuery(QueryBuilders.matchQuery("usr_name", worksUserVO.getUsr_name()))
	                .setQuery(QueryBuilders.matchQuery("isdelete", "0"))
//	                .setQuery(QueryBuilders.matchQuery("user_name", worksUserVO.getMsg()))
//	                .setQuery(QueryBuilders.boolQuery()
//	                		.must(QueryBuilders.matchQuery("isdelete", "0")))
	        		.setFrom(worksUserVO.getPage()).setSize(10)
//					.setPostFilter(QueryBuilders.rangeQuery("age").from(30).to(40))
					.get();
			SearchHit[] searchHits = response.getHits().getHits();
			List<Object> searchHit = new ArrayList<Object>();
			Data data = new Data();
			for(int i = 0; i < searchHits.length; i++) {
				System.out.println(searchHits[i].getSourceAsString()); 
				searchHit.add(searchHits[i].getSource());
			}
			data.setDatatwo(searchHit);
			return data;
		}else{
			Data data = new Data();
			AggregationBuilder aggregation = AggregationBuilders
					.terms("count_by_isdelete")
					.field("isdelete");
			SearchResponse response1 = client.prepareSearch("info")
					.setTypes("works")
					.setQuery(QueryBuilders.matchQuery("isdelete", "0"))
					.addSort("works_rgst_dt", SortOrder.DESC)
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
					.addSort("works_rgst_dt", SortOrder.DESC)
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
