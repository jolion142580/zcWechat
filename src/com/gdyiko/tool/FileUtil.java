package com.gdyiko.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

public class FileUtil {
	
	private static final int BUFFER_SIZE=16*1024;
	
	public static String appPath;
	
	public static String separator = System.getProperty("file.separator");
	
	public static String basexmlPath;
	
	public static boolean  copy(File src, File dst) {   
        boolean result=false;   
        InputStream in = null;   
        OutputStream out = null;   
        try {   
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);   
            out = new BufferedOutputStream(new FileOutputStream(dst),   
                    BUFFER_SIZE);   
            byte[] buffer = new byte[BUFFER_SIZE];   
            int len = 0;   
            while ((len = in.read(buffer)) > 0) {   
                out.write(buffer, 0, len);   
            }   
            result=true;   
        } catch (Exception e) {   
            e.printStackTrace();   
            result=false;   
        } finally {   
            if (null != in) {   
                try {   
                    in.close();   
                } catch (IOException e) {   
                    e.printStackTrace();   
                }   
            }   
            if (null != out) {   
                try {   
                    out.close();   
                } catch (IOException e) {   
                    e.printStackTrace();   
                }   
            }   
        }   
        return result;   
    } 
	
	
	/**
	 * 保存字符文件
	 * 
	 * @param filePath
	 * @param fileContent
	 * @return
	 */
	public static File saveUTF8TextFile(String filePath , String fileContent) {
		File myFile = null;
		try {
			File myFilePath = new File(filePath);
			myFile = myFilePath;
			if (!myFilePath.exists()) {
				myFilePath.getParentFile().mkdirs();
				myFilePath.createNewFile();
			}
			String content = new String(fileContent.getBytes("UTF-8"), "UTF-8");
			//byte[] data = content.getBytes();
			File file = new File(filePath);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF8"));
			out.write(content);
			out.close();
			return file;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(" ----"+myFile.getParentFile().getPath());
		}
		return null;
	}
	
	/**
	 * 读取字符文件
	 * @param path String 原文件路径 如：c:/fqf.txt
	 * @return String 文本内容
	 */
	public static String readUTF8TextFile(String strFileName) {
		StringBuffer buf=new StringBuffer();//the   intermediary,   mutable   buffer   
        BufferedReader breader = null;//reader   for   the   template   files   
        try{   
          
          breader = new BufferedReader(new InputStreamReader(new FileInputStream((strFileName)),Charset.forName("utf-8")));               
          while(breader.ready())                               	  
        	  buf.append((char)breader.read());             
          breader.close();   
        }catch(Exception e){         
        	e.printStackTrace();
        } 
        return buf.toString();   
	}
}
