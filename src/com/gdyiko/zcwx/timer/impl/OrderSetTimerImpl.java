package com.gdyiko.zcwx.timer.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gdyiko.zcwx.dao.DaysDao;
import com.gdyiko.zcwx.po.Days;
import com.gdyiko.zcwx.timer.OrderSetTimer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.directwebremoting.util.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.zcwx.weixinUtils.HttpContent;
import com.gdyiko.tool.PrimaryProduce;

import flex.messaging.io.ArrayList;
@Service
public class OrderSetTimerImpl implements OrderSetTimer {

	private static boolean isRunning = false;

	
	private final Logger logger = Logger.getLogger(OrderSetTimerImpl.class);
	

	
	@Resource(name="daysDao")
	DaysDao daysDao;

	@Transactional
	@Scheduled(cron = "0 0 1 1 * ?" )//每月1点
	public void runup()   {


		if (!isRunning) {
			isRunning = true;
			try {
				
				//同步节假日
				System.out.println("开始同步------------------------");
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				Date d = new Date();
				String y = sdf.format(d);
				String url = "http://timor.tech/api/holiday/year/"+y;
				
				System.out.println(url);
				logger.error("url:"+url);
				 HttpContent httpContent = new HttpContent();
				String day = httpContent.getHttpContent(url, "", "", "GET");
				
				System.out.println("dayurl:"+day);
				
				logger.error("dayurl:"+day);
				JSONObject dayjson = JSONObject.fromObject(day);
				JSONArray daysJA = JSONArray.fromObject(dayjson.get("holiday"));
				String isHoliday = "";
				String overDay = "";
				for (Object object : daysJA) {
					JSONObject jo = (JSONObject) object;
					for (Object obEnty : jo.entrySet()) {
						Map.Entry<String, Object> entry = (Map.Entry<String, Object>) obEnty;
						// System.out.println(entry.getKey());
						JSONObject d1 = (JSONObject) jo.get(entry.getKey().toString());
						if (d1.getBoolean("holiday")) {
							isHoliday += sdf.format(d)+"-"+entry.getKey() + ",";
						} else {
							overDay += sdf.format(d)+"-"+entry.getKey() + ",";
						}
					}
				}
				
				Days holiday = new Days();
				holiday.setYear(y);
				holiday.setName("isHoliday");
				List<Days> dls =  daysDao.findEqualByEntity(holiday, BeanUtilEx.getNotNullEscapePropertyNames(holiday));
				System.out.println("dd "+dls.size()+"  "+dls.get(0).getDays()+"---");
				logger.error("dd "+dls.size()+"  "+dls.get(0).getDays()+"---");
				if(dls!=null&&dls.size()!=0){
					holiday = dls.get(0);
				}
				
				holiday.setDays(isHoliday.substring(0,isHoliday.length()-1));
				
				if(holiday.getId()==null){
					holiday.setId(PrimaryProduce.produce());
					daysDao.save(holiday);
				}else{
					daysDao.modify(holiday);
				}
				System.out.println("dd  "+holiday.getDays());
				logger.error("dd  "+holiday.getDays());
				
				Days overday = new Days();
				overday.setYear(y);
				overday.setName("overDay");
				dls =  daysDao.findEqualByEntity(overday, BeanUtilEx.getNotNullEscapePropertyNames(overday));
				System.out.println("dd222 "+dls.size()+"  "+dls.get(0).getDays()+"---");
				logger.error("dd222 "+dls.size()+"  "+dls.get(0).getDays()+"---");
				if(dls!=null&&dls.size()!=0){
					overday = dls.get(0);
				}
				overday.setDays(overDay.substring(0,overDay.length()-1));
				
				if(overday.getId()==null){
					overday.setId(PrimaryProduce.produce());
					daysDao.save(overday);
				}else{
					daysDao.modify(overday);
				}
				System.out.println("dd2  "+holiday.getDays());
				logger.error("dd2  "+holiday.getDays());
				System.out.println("结束同步------------------------");
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
			isRunning = false;
		}else {
			logger.error("上一次任务执行还未结束");
		}
	}

}
