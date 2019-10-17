package com.gdyiko.tool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StaticParam {
	
//	private static StaticParam param;
	private String token;
	/**
	 * 获取token
	 */
	public String gettoken(){
		try {
		      InputStream in = getClass().getResourceAsStream("/staticParam.properties");
		      Properties props = new Properties();

		      props.load(in);
		      in.close();
		      this.token = props.getProperty("token");

		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		return this.token;
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
/*	public static void main(String[] args) throws IOException {
		StaticParam param = new StaticParam();
		String params = null;
		params=param.gettoken();
		System.out.println(params);
	}*/

}
