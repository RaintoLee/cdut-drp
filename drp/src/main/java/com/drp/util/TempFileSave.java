package com.drp.util;

import java.io.FileOutputStream;
import java.io.FileWriter;


public class TempFileSave {
//		static String tempFolder="//drp//src//main//resources//temp//";
	    static String tempFolder="G:\\drp\\drp\\src\\main\\resources\\temp\\";
		public static void main(String args[]){
			save("123.txt","hello,world");
		}				
		public static String save(String fileName,String text){
            FileOutputStream fos;
            String path=tempFolder+fileName;
			try {
				
		        FileWriter  fwriter = new FileWriter(path);
		        System.out.println("文件存放地址为： "+path);
		        fwriter.write(text);
		        fwriter.close();
	 
			} catch (Exception e) {
				e.printStackTrace();
			}  
			 return path;
		}
}
