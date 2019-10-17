package com.gdyiko.zcwx.weixinUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.json.JSONException;

public class OldHoliday {

	public Date addWorkDay(Date date, int num) throws ParseException {
		//2015年法定节假日
		String isHoliday="1-1,1-2,1-3,2-18,2-19,2-20,2-21,2-22,2-23,2-24,4-4,4-5,4-6,5-1," +
				"5-2,5-3,5-19,5-20,5-21,9-26,9-27,9-28,10-1,10-2,10-3,10-4,10-5,10-6,10-7";
		//2015节假前后加班日起
		String overDay="1-4,2-15,5-4,10-10,10-11";
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, num);
		
		// 当前月 
		int month = (cal.get(Calendar.MONTH)) + 1;
		 // 当前月的第几天：即当前日  
	    int day_of_month = cal.get(Calendar.DAY_OF_MONTH);

	    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
	    String holiday = sdf.format(sdf.parse(month+"-"+day_of_month)) ;	    
		
		return cal.getTime();
  }
	
	/**
	 * 获得收益时间(获取当前天+1天，周末不算).
	 *
	 * @param date
	 *            任意日期
	 * @return the income date
	 * @throws NullPointerException
	 *             if null == date
	 * @throws IOException 
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public Date getIncomeDate(Date date,Boolean b) throws NullPointerException, IOException, JSONException, ParseException{
		//2015年法定节假日
		//String isHoliday="1-1,1-2,1-3,2-18,2-19,2-20,2-21,2-22,2-23,2-24,4-4,4-5,4-6,5-1," +
		//		"5-2,5-3,5-19,5-20,5-21,9-26,9-27,9-28,10-1,10-2,10-3,10-4,10-5,10-6,10-7,10-31";
		String FILE_PATH_NAME = "/holiday.properties";
		InputStream in = getClass().getResourceAsStream(FILE_PATH_NAME);
	    Properties props = new Properties();
	    props.load(in);
	    in.close();
		boolean flag=false;//判断除了节假前后加班日的其他日期是否是周末
	    //String isHoliday =props.getProperty("isHoliday");
	    HttpContent httpContent = new HttpContent();
	    String isHoliday = httpContent.getHttpContent("http://19.127.14.204:8080/pjcms/days!tll.action?namelr=isHoliday", "", "", "POST");
		//System.out.println("isHoliday:::"+isHoliday);
		
		//2015节假前后加班日
		//String overDay="1-4,2-15,5-4,10-10,10-11";
		//String overDay =props.getProperty("overDay");
		String overDay = httpContent.getHttpContent("http://19.127.14.204:8080/pjcms/days!tll.action?namelr=overDay", "", "", "POST");
		//System.out.println("overDay:::"+overDay);
		
		Integer time =0;
	 
	    //对日期的操作,我们需要使用 Calendar 对象
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(date);
	    if(b==true){
	    	time=1;
	    }
	    
	    //+1天
	    calendar.add(Calendar.DAY_OF_MONTH, +time);
	 
	    Date incomeDate = calendar.getTime();
	    
	    // 当前月 
		int month = (calendar.get(Calendar.MONTH)) + 1;
		 // 当前月的第几天：即当前日  
	    int day_of_month = calendar.get(Calendar.DAY_OF_MONTH);

	    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
	    String holiday = sdf.format(sdf.parse(month+"-"+day_of_month)) ;
	 
//	    if (isWeekend(calendar) || isHoliday.contains(holiday)){
//	        //递归
//	        return getIncomeDate(incomeDate);
//	    }
	    String[] isOverDays=overDay.split(",");
	    for (int i = 0; i < isOverDays.length; i++) {
			if(holiday.equals(isOverDays[i])){
				flag=true;
				break;
			}
		}
	    String[] isHolidays=isHoliday.split(",");
	    for (int i = 0; i < isHolidays.length; i++) {
			if(holiday.equals(isHolidays[i])){
				//递归
		        return getIncomeDate(incomeDate,true);
		        
			}
		}
	    //判断除了节假前后加班日之外的日期是否是周末
	    if(flag==false){
	    	int week = calendar.get(Calendar.DAY_OF_WEEK);
		    //System.out.println("当前星期：："+week);
		    if(isWeekend(calendar,week)){
		    	//递归
		        return getIncomeDate(incomeDate,true);
		    }
	    }
	    return incomeDate;
	}
	 

	//星期六预约必须周五0时前申请
	private boolean isWeekend(Calendar calendar,int week){
		//System.out.println("week:::::"+week);
	    //判断是星期几
	    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	    //判断一个日历是不是周末.
	    //if (dayOfWeek == 1 || dayOfWeek == 7){
	    
	    Calendar calendar_now = new GregorianCalendar();
	    calendar_now.setTime(new Date());
	    //realityWeek1 当前日期的星期几
	    int realityWeek1 = calendar_now.get(Calendar.DAY_OF_WEEK);
	    int week_temp=week-1;//week  减少一天的星期几

	    if(realityWeek1==week_temp){//如果当前星期几等于week减少一天
	    	if(week_temp==6){//等于星期五
		    	return true;
		    }
	    }


	    if (dayOfWeek == 1 || dayOfWeek == 7){	    
		   return true;
		}

	    
	    return false;
	}
	
	
	 
	
}
