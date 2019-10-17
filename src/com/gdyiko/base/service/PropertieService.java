package com.gdyiko.base.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service("propertieService")
public class PropertieService {
	Properties p = new Properties();

	private static String fileName="/syspathconfig.properties";

	public PropertieService() throws IOException{
		InputStream in = PropertieService.class.getResourceAsStream(fileName);
		p = new Properties();
		p.load(in);
		in.close();
	}
	public String getPropertie(String name){

		return p.getProperty(name);
	}

	public static String getPropertieByStatic(String name){

		return null;
	}

}
