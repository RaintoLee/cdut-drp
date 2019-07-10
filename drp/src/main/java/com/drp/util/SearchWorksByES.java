package com.drp.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram.Order;
import org.elasticsearch.search.sort.SortOrder;

import com.drp.models.Data;

public class SearchWorksByES {

	/*
	 * 根据内容搜索作品
	 * msg=搜索内容
	 */
	public static Data searchWorksByES(TransportClient client, String msg, Integer page){
		Data data = new Data();
		SearchResponse response = client.prepareSearch("info")
				.setTypes("works")
                .setQuery(QueryBuilders.matchQuery("works_name", msg))
                .setQuery(QueryBuilders.matchQuery("usr_nm", msg))
                .setQuery(QueryBuilders.matchQuery("isdelete", "0"))
        		.setFrom(page).setSize(10)
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
	/*
	 * 按作品发布时间排序
	 * type=1(降序)0(升序)
	 */
	public static Data searchWorksByES(TransportClient client, String msg, Integer page, Integer type){
		Data data = new Data();
		if(type == 0){
			SearchResponse response = client.prepareSearch("info")
					.setTypes("works")
	                .setQuery(QueryBuilders.matchQuery("works_name", msg))
	                .setQuery(QueryBuilders.matchQuery("usr_nm", msg))
	                .setQuery(QueryBuilders.matchQuery("isdelete", "0"))
	                .addSort("works_rgst_tm", SortOrder.ASC)
	        		.setFrom(page).setSize(10)
					.get();
			SearchHit[] searchHits = response.getHits().getHits();
			List<Object> searchHit = new ArrayList<Object>();
			for(int i = 0; i < searchHits.length; i++) {
				System.out.println(searchHits[i].getSourceAsString()); 
				searchHit.add(searchHits[i].getSource());
			}
			data.setDataOne(searchHit);
		}else if(type == 1){
			SearchResponse response = client.prepareSearch("info")
					.setTypes("works")
	                .setQuery(QueryBuilders.matchQuery("works_name", msg))
	                .setQuery(QueryBuilders.matchQuery("usr_nm", msg))
	                .setQuery(QueryBuilders.matchQuery("isdelete", "0"))
	                .addSort("works_rgst_tm", SortOrder.DESC)
	        		.setFrom(page).setSize(10)
					.get();
			SearchHit[] searchHits = response.getHits().getHits();
			List<Object> searchHit = new ArrayList<Object>();
			for(int i = 0; i < searchHits.length; i++) {
				System.out.println(searchHits[i].getSourceAsString()); 
				searchHit.add(searchHits[i].getSource());
			}
			data.setDataOne(searchHit);
		}
		return data;
	}
	/*
	 * 按作品价格排序
	 * works_hdgt
	 * type=1(降序)0(升序)
	 */
	public static Data searchWorksByPrice(TransportClient client, String msg, Integer page, Integer type){
		Data data = new Data();
		if(type == 0){
			SearchResponse response = client.prepareSearch("info")
					.setTypes("works")
	                .setQuery(QueryBuilders.matchQuery("works_name", msg))
	                .setQuery(QueryBuilders.matchQuery("usr_nm", msg))
	                .setQuery(QueryBuilders.matchQuery("isdelete", "0"))
	                .addSort("works_hdgt", SortOrder.ASC)
	        		.setFrom(page).setSize(10)
					.get();
			SearchHit[] searchHits = response.getHits().getHits();
			List<Object> searchHit = new ArrayList<Object>();
			for(int i = 0; i < searchHits.length; i++) {
				System.out.println(searchHits[i].getSourceAsString()); 
				searchHit.add(searchHits[i].getSource());
			}
			data.setDataOne(searchHit);
		}else if(type == 1){
			SearchResponse response = client.prepareSearch("info")
					.setTypes("works")
	                .setQuery(QueryBuilders.matchQuery("works_name", msg))
	                .setQuery(QueryBuilders.matchQuery("usr_nm", msg))
	                .setQuery(QueryBuilders.matchQuery("isdelete", "0"))
	                .addSort("works_hdgt", SortOrder.DESC)
	        		.setFrom(page).setSize(10)
					.get();
			SearchHit[] searchHits = response.getHits().getHits();
			List<Object> searchHit = new ArrayList<Object>();
			for(int i = 0; i < searchHits.length; i++) {
				System.out.println(searchHits[i].getSourceAsString()); 
				searchHit.add(searchHits[i].getSource());
			}
			data.setDataOne(searchHit);
		}
		return data;
	}
	/*
	 * 按照作品类型搜索
	 * works_cate
	 * 音频3，视频2，文档1，图片0
	 */
	public static Data searchWorksByCategory(TransportClient client, String msg, Integer page, Integer type){
		Data data = new Data();
			SearchResponse response = client.prepareSearch("info")
					.setTypes("works")
	                .setQuery(QueryBuilders.matchQuery("works_name", msg))
	                .setQuery(QueryBuilders.matchQuery("works_cate", type))
	                .setQuery(QueryBuilders.matchQuery("usr_nm", msg))
	                .setQuery(QueryBuilders.matchQuery("isdelete", "0"))
	        		.setFrom(page).setSize(10)
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
