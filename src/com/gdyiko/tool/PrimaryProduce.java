package com.gdyiko.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class PrimaryProduce {
	//produce num
	public static String produce(){
		long timemillis = System.currentTimeMillis();
		Random random= new Random();
		float num = random.nextFloat();
		int finalNum = (int) (num*100000000);
		String finalNumStr =new Integer(finalNum).toString();
		return timemillis+finalNumStr;
	}
	public static String getSystemYear(){
	        Calendar calendar=Calendar.getInstance();  
                calendar.setTime(new Date()); 
                String yyyy=String.valueOf(calendar.get(Calendar.YEAR));
                return yyyy;
	}
	public static String getSystemMonth(){
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	        Date currentTime = new Date();//得到当前系统时间  
	        String timeStr = formatter.format(currentTime); //将日期时间格式化 
                return timeStr.substring(0,2);
	}
	public static String getSystemData(){
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
                Date currentTime = new Date();//得到当前系统时间  
                String timeStr = formatter.format(currentTime); //将日期时间格式化 
                return timeStr.substring(3,5);
	}
	public static void main(String [] xxxx){
		java.lang.System.out.println(PrimaryProduce.getSystemYear());
		
	}
}
