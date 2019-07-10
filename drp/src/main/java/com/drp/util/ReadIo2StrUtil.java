package com.drp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/***
 * 将文件转String，将string转文件
 */
public class ReadIo2StrUtil {

    private static ReadIo2StrUtil instance;

    private ReadIo2StrUtil() {
    }

    public static ReadIo2StrUtil getInstance() {
        if (null == instance) {
            instance = new ReadIo2StrUtil();
        }
        return instance;
    }

    public static void main(String[] args) {
        String filePath = "G:\\test\\ppx.jpg.txt";
        String filePath2 = "G:\\test\\ppx.jpt.toString.txt";
        String str = ReadIo2StrUtil.getInstance().read2String(filePath);
        System.out.println(str);
        ReadIo2StrUtil.getInstance().read2Io(str, filePath2);
        System.out.println(str);
    }
/**
 * 将乱码转成String
 * @param luanma
 * @return
 */
    public String changeMessycode(String luanma){
    	byte[] in =luanma.getBytes();
    	
    	String ioStr = HQCodecUtil.hexEncode(in);
    	
    	System.out.println(ioStr);
    	
    	return ioStr;
    }
    
    
    
    
    /**
     * 将String转换成乱码
     */
    public String changeString(String str){
        byte[] in =HQCodecUtil.hexDecode(str);

        String ioStr = new String(in);

        System.out.println(ioStr);

        return ioStr;
    }
    /**
     * description:  将流转换成String
     * @param filePath
     * @return
     */
    public String read2String(String filePath) {
        String ioStr = "";
        FileInputStream is = null;
        try {
            is = new FileInputStream(filePath);
            byte[] in = new byte[is.available()];
            is.read(in);
            ioStr = HQCodecUtil.hexEncode(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ioStr;
    }
    
    

    /**
     * description:  将文件流写到某一个目录下
     * @param ioStr
     * @param filePath
     */
    public void read2Io(String ioStr, String filePath) {
        FileOutputStream fos = null;
        try {
            byte[] iobyte = HQCodecUtil.hexDecode(ioStr);
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            fos = new FileOutputStream(file);
            fos.write(iobyte, 0, iobyte.length);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
