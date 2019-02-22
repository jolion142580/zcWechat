package com.gdyiko.zcwx.service;

import com.gdyiko.zcwx.po.YuYuesFull;
import com.gdyiko.tool.service.GenericService;

public interface YuYuesFullService extends GenericService<YuYuesFull, String> {
	//根据openid获得用户的预约列表
		public String yuYueFullList(String openid);
}
