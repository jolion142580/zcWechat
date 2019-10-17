package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.SmsInfoDao;
import com.gdyiko.zcwx.po.SmsInfo;
import com.gdyiko.zcwx.service.SmsInfoService;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("SmsInfoService")
public class SmsInfoServiceImpl extends GenericServiceImpl<SmsInfo, String> implements SmsInfoService {

	@Resource(name = "SmsInfoDao")
	SmsInfoDao smsInfoDao;

	@Resource(name = "SmsInfoDao")
	@Override
	public void setGenericDao(GenericDao<SmsInfo, String> genericDao) {
		super.setGenericDao(genericDao);
	}
	
	

	public String getmoblie(String time,String smsmobile) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		int count = smsInfoDao.findbyMobileTime(time, smsmobile).size();
		try {
			data.put("smsmobile", smsmobile);
			data.put("count",count);
			return data.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}



	public String getIp(String time, String ip) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		int count = smsInfoDao.findbyIPTime(time, ip).size();
		try {
			data.put("ip", ip);
			data.put("count",count);
			return data.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
/*	public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	SmsInfoService SmsInfo=(SmsInfoService)context.getBean("SmsInfoService");
	//System.out.println(System.currentTimeMillis());
	SmsInfo smsInfo = new SmsInfo();
	smsInfo.setCode("se34");
	smsInfo.setIp("192.168.1.2");
	smsInfo.setCreattime("2017-02-15 15:29:01.663");
	smsInfo.setSmsmobile("123456789");
	String rs = SmsInfo.save(smsInfo);
	//System.out.println(System.currentTimeMillis());
	System.out.println(rs);
	}*/
}
