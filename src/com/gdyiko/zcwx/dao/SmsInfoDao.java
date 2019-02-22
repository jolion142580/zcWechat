package com.gdyiko.zcwx.dao;

import java.util.List;

import com.gdyiko.zcwx.po.SmsInfo;
import com.gdyiko.tool.dao.GenericDao;

public interface SmsInfoDao extends GenericDao<SmsInfo, String>{
	
	public List<SmsInfo> findbyMobileTime(String time,String moblie);
	
	public List<SmsInfo> findbyIPTime(String time,String ip);
}
