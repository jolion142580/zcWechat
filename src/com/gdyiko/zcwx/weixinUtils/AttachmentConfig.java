package com.gdyiko.zcwx.weixinUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class AttachmentConfig {

	//读取properties的全部信息
    public  List<String> readProperties(String filePath) {
        Properties props = new Properties();
        List<String> list = new ArrayList<String>();
        try {
            InputStream in = getClass().getResourceAsStream(filePath);
            props.load(in);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String Property = props.getProperty (key);
                list.add(Property);
                //System.out.println(key+Property);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public String getProperty(String filePath,String key){
    	Properties props = new Properties();
    	String value="";
    	try {
    		InputStream in = getClass().getResourceAsStream(filePath);
            props.load(in);
            //value = props.getProperty(key);
            value = new String(props.getProperty(key).getBytes("ISO8859_1"),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return value;
    }
    
  //写入properties信息
    public void writeProperties(String filePath,String parameterName,String parameterValue) {
        Properties prop = new Properties();

        try {
            InputStream fis = getClass().getResourceAsStream(filePath);
            //从输入流中读取属性列表（键和元素对）
            prop.load(fis);
            
            OutputStream fos = new FileOutputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+filePath);
            prop.setProperty(parameterName, parameterValue);
            //以适合使用 load 方法加载到 Properties 表中的格式，将此 Properties 表中的属性列表（键和元素对）写入输出流
            prop.store(fos, "Update '" + parameterName + "' value");
        } catch (IOException e) {
        	e.printStackTrace();
            System.err.println("Visit "+filePath+" for updating "+parameterName+" value error");
        }
    }
    
}
