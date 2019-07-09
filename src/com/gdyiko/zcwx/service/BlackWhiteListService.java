package com.gdyiko.zcwx.service;


import com.gdyiko.tool.service.GenericService;
import com.gdyiko.zcwx.po.BlackWhiteList;
import com.gdyiko.zcwx.po.YuYues;
import org.json.JSONObject;


public interface BlackWhiteListService extends GenericService<BlackWhiteList, String> {

	/**
	 *
	 * @param yuYues
	 * @return flag: 0|1  0黑名单 1白名单
	 * 			forever 0/1  0时今年被限制 1时永久限制
	 * 		renturn 1:{flag:1 ,forever:1} 	永久白名单
	 * 		renturn 2:{flag:0 ,forever:0} 今年被限制预约
	 * 		renturn 3:{flag:0 ,forever:1}	永久黑名单
	 * 		renturn 4:{flag:1 ,forever:0}	默认返回
	 */
	JSONObject check(YuYues yuYues);
}
