package com.drp.util;


import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;


/**
 * FastDFS Java客户端工具类，负责文件上传、下载等功能
 * 
 * @author curry
 *
 */

public class FastDFSUtil {

    static {

        //通过环境变量初始化fastdfs

        Map<String, String> envs = System.getenv();
        String fastdfs_tracker = envs.get("DRP_FASTDFS_TRACKER").toString();


        Properties p = new Properties();
        p.put("fastdfs.tracker_servers",fastdfs_tracker);
        try {
            ClientGlobal.initByProperties(p);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient1 storageClient;

    public FastDFSUtil() throws Exception {
    	trackerClient = new TrackerClient();
    	trackerServer = trackerClient.getConnection();
    	storageServer = null;
    	storageClient = new StorageClient1(trackerServer, storageServer);
    }
    
    /**
     * 文件上传方法
     * @param fileName 文件全路径
     * @param extName 文件拓展名
     * @param metas 文件拓展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
		String result = storageClient.upload_file1(fileName, extName, metas);
    	return result;
    }
    
    public String uploadFile(String fileName) throws Exception {
    	return uploadFile(fileName, null, null);
    }
    
    public String uploadFile(String fileName, String extName) throws Exception {
    	return uploadFile(fileName, extName, null);
    }
    
    /**
     * 文件上传方法
     * @param fileContent 文件内容，字节数组
     * @param extName 文件拓展名
     * @param metas 文件拓展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
		String result = storageClient.upload_file1(fileContent, extName, metas);
    	return result;
    }
    
    public String uploadFile(byte[] fileContent) throws Exception {
    	return uploadFile(fileContent, null, null);
    }
    
    public String uploadFile(byte[] fileContent, String extName) throws Exception {
    	return uploadFile(fileContent, extName, null);
    }
    
    /** 
     * 文件下载到磁盘 
     * @param path 图片路径 
     * @param output 输出流 中包含要输出到磁盘的路径 
     * @return -1失败,0成功 
     */  
    public int download_file(String path,BufferedOutputStream output) {
        //byte[] b = storageClient.download_file(group, path);
        int result=-1;
        try {
            byte[] b = storageClient.download_file1(path);
                try{
                    if(b != null){
                        output.write(b);
                        result=0;
                    }
                }catch (Exception e){} //用户可能取消了下载
                finally {
                    if (output != null)
                        try {
                            output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }  
    /** 
     * 
     * @param group storage组名
     * @param remoteFileName storage上存放的文件的名字（名字是一串字符串，如：M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg ）
     * @param localFileName 存放在mysql上的文件名（文件名和文件名后缀）
     * @return
     */
    public ResponseEntity<byte[]> download_bytes(String group, String remoteFileName, String localFileName) {
        byte[] b=null;
        HttpHeaders headers = new HttpHeaders();
        try {
            b = storageClient.download_file(group, remoteFileName);
            headers.setContentDispositionFormData("attachment", new String(localFileName.getBytes("UTF-8"),"iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (MyException e) {  
            e.printStackTrace();  
        }  
        return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);  
    }  
      
    /** 
     * 删除文件 
     * @param group 组名 如：group1 
     * @param storagePath 不带组名的路径名称 如：M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg 
     * @return -1失败,0成功 
     */  
    public Integer delete_file(String group ,String storagePath){  
        int result=-1;  
        try {  
            result = storageClient.delete_file(group, storagePath);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (MyException e) {  
            e.printStackTrace();  
        }  
         return  result;  
    }  
    /** 
     *  
     * @param storagePath  文件的全部路径 如：group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg 
     * @return -1失败,0成功 
     * @throws IOException 
     * @throws Exception 
     */  
    public Integer delete_file(String storagePath){  
        int result=-1;  
        try {  
            result = storageClient.delete_file1(storagePath);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (MyException e) {  
            e.printStackTrace();  
        }  
        return  result;  
    }  
}
