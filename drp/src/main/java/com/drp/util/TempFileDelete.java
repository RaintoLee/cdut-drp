package com.drp.util;

import java.io.File;

public class TempFileDelete {
//	 static String tempFolder="G:\\drp\\drp\\src\\main\\resources\\temp\\";
	 
	 public static void main(String args[]){
		 deleteFile("123.txt");
	 }
		 
	 
	//删除单个文档
		public static boolean deleteFile(String filepath) {
//			String path =tempFolder+fileName;
			
	        File file = new File(filepath);
	        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
	        if (file.exists() && file.isFile()) {
	            if (file.delete()) {
	                System.out.println("删除单个文件" + filepath + "成功！");
	                return true;
	            } else {
	                System.out.println("删除单个文件" + filepath + "失败！");
	                return false;
	            }
	        } else {
	            System.out.println("删除单个文件失败：" + filepath + "不存在！");
	            return false;
	        }
	    }

}
