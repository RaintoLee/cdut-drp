package com.drp.service.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.drp.models.BrowsingRecordVO;
import com.drp.models.Data;
import com.drp.models.Employee;
import com.drp.models.WorksUserVO;
import com.drp.service.RecordService4es;

public class RecordServiceImpl4es<T> implements RecordService4es<TransportClient, T> {

	public RecordServiceImpl4es(){}
	
	@Override
	public void createDocument(TransportClient client, T t) throws IOException {}

	@Override
	public void deleteDocument(TransportClient client, T t) throws IOException {}

	@Override
	public void updateDocument(TransportClient client, T t) throws IOException {}

	@Override
	public void getDocument(TransportClient client, T t) throws IOException {}
	
	public Integer CRUDServiceImpl(T t) {
		Type type = t.getClass();
		if("com.drp.models.BrowsingRecordVO".equals(type.getTypeName()) || "com.drp.models.BrowsingRecordVO" == type.getTypeName()){
			return 1;
		}
		else if("com.drp.models.Employee".equals(type.getTypeName()) || "com.drp.models.Employee" == type.getTypeName()){
			return 2;
		}
		else if("com.drp.models.LoginRecordVO".equals(type.getTypeName()) || "com.drp.models.LoginRecordVO" == type.getTypeName()){
			return 3;
		}
		else if("com.drp.models.AgentOrderRecordVO".equals(type.getTypeName()) || "com.drp.models.AgentOrderRecordVO" == type.getTypeName()){
			return 4;
		}
		else if("com.drp.models.BuyOrderRecordVO".equals(type.getTypeName()) || "com.drp.models.BuyOrderRecordVO" == type.getTypeName()){
			return 5;
		}
		else if("com.drp.models.LoadRecordVO".equals(type.getTypeName()) || "com.drp.models.LoadRecordVO" == type.getTypeName()){
			return 6;
		}
		else if("com.drp.models.ChangeUserInfoVO".equals(type.getTypeName()) || "com.drp.models.ChangeUserInfoVO" == type.getTypeName()){
			return 7;
		}
		else if("com.drp.models.ChangeWorksInfoVO".equals(type.getTypeName()) || "com.drp.models.ChangeWorksInfoVO" == type.getTypeName()){
			return 8;
		}
		else if("com.drp.models.UserVO".equals(type.getTypeName()) || "com.drp.models.UserVO" == type.getTypeName()){
			return 9;
		}
		else if("com.drp.models.ProtocolVO".equals(type.getTypeName()) || "com.drp.models.ProtocolVO" == type.getTypeName()){
			return 10;
		}
		else if("com.drp.models.WorksUserVO".equals(type.getTypeName()) || "com.drp.models.WorksUserVO" == type.getTypeName()){
			return 11;
		}else{
			return 0;
		}
	}

	@Override
	public Data executeSearch(TransportClient client, T t) throws Exception {
		return null;
		}

	@Override
	public void updatePageViews(TransportClient client, WorksUserVO worksUserVO) throws IOException {
		UpdateResponse response = client.prepareUpdate("info", "works", worksUserVO.getWorks_id())
				.setDoc(XContentFactory.jsonBuilder()
					.startObject()
						.field("works_pageviews", worksUserVO.getWorks_pageviews())
					.endObject())
				.get();
		System.out.println(response.getResult());  
		
	}
}
