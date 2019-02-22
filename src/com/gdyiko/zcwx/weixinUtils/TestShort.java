package com.gdyiko.zcwx.weixinUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class TestShort {
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String httpUrl = "http://apis.baidu.com/3023/shorturl/shorten";
		String httpArg = "url_long=http%3A%2F%2Fapistore.baidu.com%2Fastore%2Fshopready%2F1973.html";
		String jsonResult = request(httpUrl, httpArg);
		System.out.println(jsonResult);
	}*/
	
	/**
	* @param urlAll
	*            :请求接口
	* @param httpArg
	*            :参数
	* @return 返回结果
	*/
	public static String request(String httpUrl, String httpArg) {
	   BufferedReader reader = null;
	   String result = null;
	   StringBuffer sbf = new StringBuffer();
	   httpUrl = httpUrl + "?" + httpArg;
	   try {
	       URL url = new URL(httpUrl);
	       HttpURLConnection connection = (HttpURLConnection) url
	               .openConnection();
	       connection.setRequestMethod("GET");
	       // 填入apikey到HTTP header
	       connection.setRequestProperty("apikey",  "fa0H8rnA6YZbL3tS9XjlgPwWLlvIWi4I");
	       connection.connect();
	       InputStream is = connection.getInputStream();
	       reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	       String strRead = null;
	       while ((strRead = reader.readLine()) != null) {
	           sbf.append(strRead);
	           sbf.append("\r\n");
	       }
	       reader.close();
	       result = sbf.toString();
	   } catch (Exception e) {
	       e.printStackTrace();
	   }
	   return result;
	}
}