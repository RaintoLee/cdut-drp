package com.drp.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.commons.io.IOUtils;
import org.csource.common.IniFileReader;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDfs
{
	///drp/src/main/resources/fdfs_client.conf
  public static String conf_filename = "E:/DRP/DoMain/drp20190304/drp/src/main/resources/fdfs_client.conf";
//  public static String conf_filename = "/var/lib/tomcat8/webapps/drp/WEB-INF/classes/fdfs_client.conf";
  public String local_filename = "/usr/local/encrypt.zip";
  public String vip = "114.115.168.82:8888/";
  
  public static void main(String[] args)
    throws Exception
  {
    IniFileReader iniReader = new IniFileReader(conf_filename);
    String[] szTrackerServers = iniReader.getValues("tracker_server");
    if (szTrackerServers == null) {
      System.out.println("is null");
    } else {
      for (String item : szTrackerServers) {
        System.out.println(item);
      }
    }
  }
  
  public String uploadFile(byte[] fileContent, String type)
    throws Exception
  {
    System.out.println("进来啦");
    ClientGlobal.init(conf_filename);
    
    IniFileReader iniReader = new IniFileReader(conf_filename);
    String[] szTrackerServers = iniReader.getValues("tracker_server");
    
    TrackerClient tracker = new TrackerClient();
    TrackerServer trackerServer = tracker.getConnection();
    StorageServer storageServer = null;
    StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
    
    String result = storageClient.upload_file1(fileContent, type, null);
    System.out.println("上传操作完成");
    System.out.println(this.vip + result);
    return this.vip + result;
  }
  
  public String upload(String fileAdd)
  {
    String allpath = "";
    try
    {
      ClientGlobal.init(conf_filename);
      TrackerClient tracker = new TrackerClient();
      TrackerServer trackerServer = tracker.getConnection();
      StorageServer storageServer = null;
      StorageClient storageClient = new StorageClient(trackerServer, storageServer);
      


      String[] fileIds = storageClient.upload_file(fileAdd, null, null);
      
      String group = fileIds[0];
      String path = fileIds[1];
      allpath = this.vip + group + "/" + path;
      
      System.out.println("组名：" + group);
      System.out.println("路径: " + path);
      System.out.println("全路径: " + allpath);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (MyException e)
    {
      e.printStackTrace();
    }
    return allpath;
  }
  
  public void download()
  {
    try
    {
      ClientGlobal.init(conf_filename);
      
      TrackerClient tracker = new TrackerClient();
      TrackerServer trackerServer = tracker.getConnection();
      StorageServer storageServer = null;
      
      StorageClient storageClient = new StorageClient(trackerServer, storageServer);
      byte[] b = storageClient.download_file("group1", "M00/02/00/wKicaFuwMIyAAMW8AABgiEpSGUU667.jpg");
      System.out.println(b);
      IOUtils.write(b, new FileOutputStream("G:/567下载2.jpg"));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void getFileInfo()
  {
    try
    {
      ClientGlobal.init(conf_filename);
      
      TrackerClient tracker = new TrackerClient();
      TrackerServer trackerServer = tracker.getConnection();
      StorageServer storageServer = null;
      
      StorageClient storageClient = new StorageClient(trackerServer, storageServer);
      FileInfo fi = storageClient.get_file_info("group1", "M00/02/00/wKicaFuwMIyAAMW8AABgiEpSGUU667.jpg");
      System.out.println(fi.getSourceIpAddr());
      System.out.println(fi.getFileSize());
      System.out.println(fi.getCreateTimestamp());
      System.out.println(fi.getCrc32());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void getFileMate()
  {
    try
    {
      ClientGlobal.init(conf_filename);
      
      TrackerClient tracker = new TrackerClient();
      TrackerServer trackerServer = tracker.getConnection();
      StorageServer storageServer = null;
      
      StorageClient storageClient = new StorageClient(trackerServer, 
        storageServer);
      NameValuePair[] nvps = storageClient.get_metadata("group1", "M00/00/00/wKgRcFV_08OAK_KCAAAA5fm_sy874.conf");
      for (NameValuePair nvp : nvps) {
        System.out.println(nvp.getName() + ":" + nvp.getValue());
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void delete()
  {
    try
    {
      ClientGlobal.init(conf_filename);
      
      TrackerClient tracker = new TrackerClient();
      TrackerServer trackerServer = tracker.getConnection();
      StorageServer storageServer = null;
      
      StorageClient storageClient = new StorageClient(trackerServer, 
        storageServer);
      int i = storageClient.delete_file("group1", "M00/00/00/wKgRcFV_08OAK_KCAAAA5fm_sy874.conf");
      System.out.println("删除失败:" + i);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
