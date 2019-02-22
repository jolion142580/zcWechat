package com.gdyiko.tool;

import java.util.Calendar;
import java.util.Date;

public class TimerUtil {
	
	public static final long DAY_MILLIS = 3600000 * 24;
	
	public static final long HOUR_MILLIS = 3600000;
	
	public static final long SECCEND = 1000;
	public static long initTodayAtTime(long time){//获取时分秒设定的当天时间戳
		Calendar old = Calendar.getInstance();
		old.setTimeInMillis(time);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, old.get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, old.get(Calendar.MINUTE));
        c.set(Calendar.SECOND, old.get(Calendar.SECOND));
        return c.getTimeInMillis();
	}

	
	public static long getFixedTime(long time) {
		int aday = 1000*3600*24;
		Calendar old = Calendar.getInstance();
		old.setTimeInMillis(time);
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();		
		c.set(Calendar.HOUR_OF_DAY, old.get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, old.get(Calendar.MINUTE));
        c.set(Calendar.SECOND, old.get(Calendar.SECOND));
		if(c.getTimeInMillis()<now.getTime()){
			System.out.println("aad"+time);
            c.setTimeInMillis(c.getTimeInMillis()+aday); 
        }
        return c.getTimeInMillis()-now.getTime();
		/*int aday = 1000*3600*24;
        Calendar c = Calendar.getInstance();
        Date now = c.getTime();
        if(now.getTime() - time>aday){
        	c.setTimeInMillis(getDayStartTime(now)+time%aday);
        	if(c.getTimeInMillis()<now.getTime()){
            	c.setTimeInMillis(c.getTimeInMillis()+aday);            
            }
        }else{
        	if(time<now.getTime()){
            	c.setTimeInMillis(c.getTimeInMillis()+aday);            
            }
        }
        System.out.println(c.getTimeInMillis()-now.getTime()/1000);
        return c.getTimeInMillis()-now.getTime();*/
    }
	
	public static long getFixedTime(int runAtTime) {
        Calendar c = Calendar.getInstance();
        Date now = c.getTime();
        c.set(Calendar.HOUR_OF_DAY, runAtTime);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        if(c.getTimeInMillis()<now.getTime()){         
             //c.add(Calendar.DAY_OF_MONTH, 1);
             c.setTimeInMillis(c.getTimeInMillis()+1000*3600*24*1); 
        }
        return c.getTimeInMillis()-now.getTime();
    }
	
	public static long getFixedTime(int runAtWeekday,int runAtTime) {
        Calendar c = Calendar.getInstance();
        Date now = c.getTime();
        c.set(Calendar.DAY_OF_WEEK, runAtWeekday);
        c.set(Calendar.HOUR_OF_DAY, runAtTime);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        if(c.getTimeInMillis()<now.getTime()){         
             c.setTimeInMillis(c.getTimeInMillis()+1000*3600*24*7);      	     	
        }
        return c.getTimeInMillis()-now.getTime();
    }
	
	public static long getMonthStartTime(Date dayTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(dayTime);
        c.set(Calendar.DAY_OF_MONTH,1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }
	
	public static long getMonthEndTime(Date dayTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(dayTime);
        c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTimeInMillis();
    }
	
	public static Date getMonthStartDate(Date dayTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(dayTime);
        c.set(Calendar.DAY_OF_MONTH,1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
	
	public static Date getMonthEndDate(Date dayTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(dayTime);
        c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }
	
	public static Date getWeekStartDate(Date dayTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(dayTime);
        c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
	
	public static long getDayStartTime(Date dayTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(dayTime);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }
	
	public static long getDayEndTime(Date dayTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(dayTime);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }
	
	public static void main (String args[]){
		System.out.println((double)getFixedTime(System.currentTimeMillis()+2*60*60*1000)/60/60/1000);
		System.out.println((double)getFixedTime(6)/60/60/1000);
		System.out.println((double)getFixedTime(5,14)/60/60/1000);
	}
}
