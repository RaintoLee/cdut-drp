package com.drp.service.impl;

import java.io.IOException;
import java.net.InetAddress;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.drp.service.UpdateService4es;

public class UpdateServiceImpl4es {

	public static void create4es(String[] create, Integer c) throws IOException {
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		String[] data = new String[50];
		for(Integer i = 0 ; i < c ; i++){
			data = create[i].split(",");
			if(data[2].equals("agentOrder")){
				bulkRequest.add(client.prepareIndex(data[1], data[2], data[3])
						.setSource(XContentFactory.jsonBuilder()
								.startObject()
								.field("userId", data[4])
								.field("agentId", data[5])
								.field("worksId", data[6])
								.field("type", data[7])
								.field("birthTime", data[8])
								.field("isdelete", data[9])
								.endObject()));
			}else if(data[2].equals("browsing")){
				bulkRequest.add(client.prepareIndex(data[1], data[2], data[3])
						.setSource(XContentFactory.jsonBuilder()
								.startObject()
								.field("worksId", data[4])
								.field("userId", data[5])
								.field("worksName", data[6])
								.field("browsingTime", data[7])
								.field("worksSrc", data[8])
								.field("isdelete", data[9])
								.endObject()));
			}else if(data[2].equals("buyOrder")){
				bulkRequest.add(client.prepareIndex(data[1], data[2], data[3])
						.setSource(XContentFactory.jsonBuilder()
								.startObject()
								.field("userId", data[4])
								.field("agentId", data[5])
								.field("worksId", data[6])
								.field("type", data[7])
								.field("birthTime", data[8])
								.field("isdelete", data[9])
								.endObject()));
			}else if(data[2].equals("changeUserInfo")){
				bulkRequest.add(client.prepareIndex(data[1], data[2], data[3])
						.setSource(XContentFactory.jsonBuilder()
								.startObject()
								.field("userId", data[4])
								.field("birthTime", data[5])
								.field("after", data[6])
								.field("before", data[7])
								.field("isdelete", data[8])
								.endObject()));
			}else if(data[2].equals("changeWorksInfo")){
				bulkRequest.add(client.prepareIndex(data[1], data[2], data[3])
						.setSource(XContentFactory.jsonBuilder()
								.startObject()
								.field("userId", data[4])
								.field("birthTime", data[5])
								.field("worksId", data[6])
								.field("after", data[7])
								.field("before", data[8])
								.field("isdelete", data[9])
								.endObject()));
			}else if(data[2].equals("load")){
				bulkRequest.add(client.prepareIndex(data[1], data[2], data[3])
						.setSource(XContentFactory.jsonBuilder()
								.startObject()
								.field("userId", data[4])
								.field("loadTime", data[5])
								.field("location", data[6])
								.field("type", data[7])
								.field("fileType", data[8])
								.field("worksId" , data[9])
								.field("isdelete", data[10])
								.endObject()));
			}else if(data[2].equals("login")){
				bulkRequest.add(client.prepareIndex(data[1], data[2], data[3])
						.setSource(XContentFactory.jsonBuilder()
								.startObject()
								.field("userId", data[4])
								.field("loginTime", data[5])
								.field("location", data[6])
								.field("isdelete", data[7])
								.endObject()));
			}else if(data[2].equals("order")){
				bulkRequest.add(client.prepareIndex(data[1], data[2], data[3])
						.setSource(XContentFactory.jsonBuilder()
								.startObject()
								.field("agent_num", data[4])
								.field("auth_fee", data[5])
								.field("Deskey", data[6])
								.field("info_isvalid_flg", data[7])
								.field("parta_id", data[8])
								.field("parta_patio",data[9])
								.field("partb_id", data[10])
								.field("partb_patio", data[11])
								.field("protocol_deadline", data[12])
								.field("protocol_type", data[13])
								.field("receive_flg", data[14])
								.field("role_id", data[15])
								.field("usr_id", data[16])
								.field("works_id", data[17])
								.field("isdelete", data[18])
								.endObject()));
			}else if(data[2].equals("user")){
				bulkRequest.add(client.prepareIndex(data[1], data[2], data[3])
						.setSource(XContentFactory.jsonBuilder()
								.startObject()
								.field("acct_bal", data[4])
								.field("pay_pwd", data[5])
								.field("usr_addr", data[6])
								.field("usr_ava_src", data[7])
								.field("usr_cert_cate", data[8])
								.field("usr_cert_num", data[9])
								.field("usr_certpic_src", data[10])
								.field("usr_email", data[11])
								.field("gender", data[12])
								.field("usr_isMembership", data[13])
								.field("usr_isvalid_flg", data[14])
								.field("usr_nicknm", data[15])
								.field("usr_nm", data[16])
								.field("usr_phone", data[17])
								.field("usr_pub_key", data[18])
								.field("usr_pwd", data[19])
								.field("usr_qlfy", data[20])
								.field("usr_qlfypic_src", data[21])
								.field("usr_rgst_dt", data[22])
								.field("usr_rgst_tm", data[23])
								.field("usr_stat", data[24])
								.field("isdelete", data[25])
								.endObject()));
			}else if(data[2].equals("works")){
				bulkRequest.add(client.prepareIndex(data[1], data[2], data[3])
						.setSource(XContentFactory.jsonBuilder()
								.startObject()
								.field("works_id", data[4])
								.field("discount", data[5])
								.field("keywords", data[6])
								.field("type", data[7])
								.field("usr_nm", data[8])
								.field("usr_id", data[9])
								.field("works_agent_qty", data[10])
								.field("works_auth_qty", data[11])
								.field("works_cate", data[12])
								.field("works_ciphertext", data[13])
								.field("works_format", data[14])
								.field("works_hdgt", data[15])
								.field("works_ischrg", data[16])
								.field("works_issu_dt", data[17])
								.field("works_issu_tm", data[18])
								.field("works_isvalid_flg", data[19])
								.field("works_memo", data[20])
								.field("works_name", data[21])
								.field("works_pageviews", data[22])
								.field("works_recinfo_src", data[23])
								.field("works_rgst_dt", data[24])
								.field("works_rgst_tm", data[25])
								.field("works_secr_lvl", data[26])
								.field("works_spec", data[27])
								.field("works_src", data[28])
								.field("works_store_src", data[29])
								.field("works_sym_key", data[30])
								.field("works_theme", data[31])
								.field("isdelete", data[32])
								.endObject()));
			}
		}
		BulkResponse bulkResponse = bulkRequest.get();
//		 判断是否存在失败操作
		if (bulkResponse.hasFailures()) {
			System.out.println("存在失败操作");
		}
//		遍历每个操作的执行结果
//		for (BulkItemResponse bulkItemResponse : bulkResponse.getItems()) {
//			System.out.println(bulkItemResponse.getResponse().toString());
//		}
	}

	public static void update4es(String[] update, Integer u) throws IOException{
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		String[] data = new String[50];
		for(Integer i = 0 ; i < u ; i++){
			data = update[i].split(",");
			if(data[2].equals("agentOrder")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("type", data[4])
								.endObject()));
			}else if(data[2].equals("browsing")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("browsingTime", data[4])
								.field("worksId", data[6])
								.field("userId", data[7])
								.field("timeOut", data[8])
								.field("worksSrc", data[9])
								.field("worksName", data[10])
								.endObject()));
			}else if(data[2].equals("buyOrder")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("type", data[4])
								.endObject()));
			}else if(data[2].equals("changeUserInfo")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("userId", data[4])
								.field("birthTime", data[5])
								.field("after", data[6])
								.field("before", data[7])
								.endObject()));
			}else if(data[2].equals("changeWorksInfo")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("userId", data[4])
								.field("worksId", data[5])
								.field("birthTime", data[6])
								.field("after", data[7])
								.field("before", data[8])
								.endObject()));
			}else if(data[2].equals("load")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("userId", data[4])
								.field("loadTime", data[5])
								.field("location", data[6])
								.field("type", data[7])
								.field("fileType", data[8])
								.field("worksId" , data[9])
								.endObject()));
			}else if(data[2].equals("login")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("userId", data[4])
								.field("loginTime", data[5])
								.field("location", data[6])
								.field("isdelete", data[7])
								.endObject()));
			}else if(data[2].equals("order")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("info_isvalid_flg", data[4])
								.field("receive_flg", data[5])
								.endObject()));
			}else if(data[2].equals("user")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("usr_addr", data[4])
								.field("usr_cert_cate", data[5])
								.field("usr_cert_num", data[6])
								.field("gender", data[7])
								.field("usr_nicknm", data[8])
								.field("usr_phone", data[9])
								.field("usr_qlfy", data[10])
								.endObject()));
			}else if(data[2].equals("works")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("works_hdgt", data[4])
								.field("works_ischrg", data[5])
								.field("works_memo", data[6])
								.field("works_name", data[7])
								.field("works_isvalid_flg", data[8])
								.field("works_secr_lvl", data[9])
								.endObject()));
			}
		}
		BulkResponse bulkResponse = bulkRequest.get();
		// 判断是否存在失败操作
		if (bulkResponse.hasFailures()) {
			System.out.println("存在失败操作");
		}
		//遍历每个操作的执行结果
//		for (BulkItemResponse bulkItemResponse : bulkResponse.getItems()) {
//			System.out.println(bulkItemResponse.getResponse().toString());
//		}
	}

	public static void delete4es(String[] delete, Integer d) throws IOException{
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("114.115.168.82"), 9300));
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		String[] data = new String[50];
		for(Integer i = 0 ; i < d ; i++){
			data = delete[i].split(",");
			if(data[2].equals("agentOrder")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("isdelete", "1")
								.endObject()));
			}else if(data[2].equals("browsing")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("isdelete", "1")
								.endObject()));
			}else if(data[2].equals("buyOrder")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("isdelete", "1")
								.endObject()));
			}else if(data[2].equals("changeUserInfo")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("isdelete", "1")
								.endObject()));
			}else if(data[2].equals("changeWorksInfo")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("isdelete", "1")
								.endObject()));
			}else if(data[2].equals("load")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("isdelete", "1")
								.endObject()));
			}else if(data[2].equals("login")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("isdelete", "1")
								.endObject()));
			}else if(data[2].equals("order")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("isdelete", "1")
								.endObject()));
			}else if(data[2].equals("user")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("isdelete", "1")
								.endObject()));
			}else if(data[2].equals("works")){
				bulkRequest.add(client.prepareUpdate(data[1], data[2], data[3])
						.setDoc(XContentFactory.jsonBuilder()
								.startObject()
								.field("isdelete", "1")
								.endObject()));
			}
		}
		BulkResponse bulkResponse = bulkRequest.get();
		// 判断是否存在失败操作
		if (bulkResponse.hasFailures()) {
			System.out.println("存在失败操作");
		}
		//遍历每个操作的执行结果
//		for (BulkItemResponse bulkItemResponse : bulkResponse.getItems()) {
//			System.out.println(bulkItemResponse.getResponse().toString());
//		}
	}

}
