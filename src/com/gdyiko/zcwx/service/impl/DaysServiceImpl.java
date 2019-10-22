package com.gdyiko.zcwx.service.impl;



import java.text.ParseException;
import java.util.Calendar;

import javax.annotation.Resource;

import com.gdyiko.zcwx.dao.DaysDao;
import com.gdyiko.zcwx.po.Days;
import com.gdyiko.zcwx.service.DaysService;
import org.springframework.stereotype.Service;

import com.gdyiko.tool.PrimaryProduce;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("daysService")
public class DaysServiceImpl extends GenericServiceImpl<Days, String> implements DaysService {

	@Resource(name="daysDao")
	DaysDao daysDao;
	
	@Resource(name="daysDao")
	@Override
	public void setGenericDao(GenericDao<Days, String> genericDao) {
		// TODO Auto-generated method stub
		super.setGenericDao(genericDao);
	}

	public String saveInfo(String Jban, String Jjia) {
		String bool = "0";
		
		String jbans[] = Jban.split(",");
		System.out.println(jbans[0]);
		String jjias[] = Jjia.split(",");
		System.out.println(jjias[0]);
		int i=0;
		String banstr = "";
		String jiastr = "";
	 Calendar a=Calendar.getInstance();
		while(!"".equals(jbans[0])){	
			
			System.out.println(jbans.length);	
		if(jbans[i].trim().substring(0,4).equals(String.valueOf(a.get(Calendar.YEAR))))
		banstr += jbans[i].trim();
		i++;
		if(i>=jbans.length)
			break;
		
			
			banstr += ",";
		}
		i=0;
		while(!"".equals(jjias[0])){
			
			if(jjias[i].trim().substring(0,4).equals(String.valueOf(a.get(Calendar.YEAR))))
			jiastr += jjias[i].trim();
			i++;
			if(i>=jjias.length)
				break;
			jiastr += ",";
			
		}
		
		try {
			Days days = daysDao.findPkey("overDay");
			if(days!=null)
				remove(days);
			Days model = new Days();
			model.setId(PrimaryProduce.produce());
			model.setName("overDay");
			model.setYear(String.valueOf(a.get(Calendar.YEAR)));
			model.setDays(banstr);
			save(model);
			bool="1";
		} catch (Exception e) {
			e.printStackTrace();
			bool = "0";
		}
		try {
			Days days2 = daysDao.findPkey("isHoliday");
			if(days2!=null)
				remove(days2);
			Days model = new Days();
			model.setId(PrimaryProduce.produce());
			model.setName("isHoliday");
			model.setYear(String.valueOf(a.get(Calendar.YEAR)));
			model.setDays(jiastr);
			save(model);
			bool = "1";
		} catch (Exception e) {
			e.printStackTrace();
			bool = "0";
		}
		return bool;
	}

	/*public String findDays(String name) {
		Days days = daysDao.findPkey(name);
		Days days2 = daysDao.findPkey("week");
		if(days2==null)
		{
			Calendar a=Calendar.getInstance();
			days2 = new Days();
			days2.setId(PrimaryProduce.produce());
			days2.setName("week");
			days2.setYear(String.valueOf(a.get(Calendar.YEAR)));
			try {
				days2.setDays(ErgodicDays.Weekdays(String.valueOf(a.get(Calendar.YEAR))));
				save(days2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
		}
	*//*	if(days!=null&&"overDay".equals(name))
			return days.getDays();
		if(days!=null&&"isHoliday".equals(name))
			return days2.getDays()+","+days.getDays();*//*
		if(days!=null)
			return days.getDays();
		else
		return null;
	}*/

	public String showdays(String name) {
		Days days = daysDao.findPkey(name);
		String daysarray[] = days.getDays().split(",");
		String daysstr = "";
		for(int i=0;i<daysarray.length;i++){
			String str = daysarray[i];
			
			daysstr += str.substring(str.indexOf("-")+1);
			if(i+1<daysarray.length)
				daysstr += ",";
			
		}
		
		return daysstr;
	}
	
}
