package com.drp.service;

import java.io.IOException;
import java.util.ArrayList;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.stereotype.Service;

import com.drp.models.Data;
import com.drp.models.Employee;
import com.drp.models.WorksUserVO;

@Service
public interface RecordService4es<E , T> {

	/**
	 * 创建一个document
	 * @param <T>
	 * @param client
	 * @throws IOException 
	 */
	@SuppressWarnings("hiding")
	public void createDocument(TransportClient client, T t) throws IOException;

	
	/**
	 * 删除document
	 * @param client
	 * @param t
	 * @throws IOException 
	 */
	public void deleteDocument(TransportClient client , T t) throws IOException ;
	
	
	/**
	 * 更新document
	 * @param client
	 * @param t
	 * @throws IOException 
	 */
	public void updateDocument(TransportClient client , T t) throws IOException ;
	
	
	/**
	 * 获取document
	 * @param client
	 * @param t
	 * @throws IOException 
	 */
	public void getDocument(TransportClient client , T t) throws IOException ;

	public Data executeSearch(TransportClient client , T t) throws Exception;


	public void updatePageViews(TransportClient client, WorksUserVO worksUserVO) throws IOException;

}
