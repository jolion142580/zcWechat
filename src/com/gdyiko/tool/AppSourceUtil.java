package com.gdyiko.tool;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppSourceUtil {
	
	
	public static String CHARGESTA_RUNATTIME;
	
	public static String LOGINSTA_RUNATTIME;
	
	static{
		Properties p = getClassPathProperties("setting/gamemag.properties");
		CHARGESTA_RUNATTIME = p.getProperty("timer.chargeSta.runAtTime");
		LOGINSTA_RUNATTIME = p.getProperty("timer.loginSta.runAtTime");
	}
		
	
	public static Properties getClassPathProperties(String fname) {
		InputStream in = AppSourceUtil.class.getClassLoader().getResourceAsStream(fname);
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;		
	}	

}
