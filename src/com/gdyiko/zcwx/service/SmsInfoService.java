package com.gdyiko.zcwx.service;

import com.gdyiko.zcwx.po.SmsInfo;
import com.gdyiko.tool.service.GenericService;

public interface SmsInfoService extends GenericService<SmsInfo, String>{
	
	public String getmoblie(String time,String smsmobile);
	
	public String getIp(String time,String ip);
}
