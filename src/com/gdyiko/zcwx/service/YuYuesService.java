package com.gdyiko.zcwx.service;



import com.gdyiko.zcwx.po.YuYues;
import com.gdyiko.tool.service.GenericService;

public interface YuYuesService extends GenericService<YuYues, String> {
	//根据street和ydate获得剩余号数
	public String getCount(String street,String ydate,String businessType,Integer weight);
	//保存预约
	public String saveYuYues(YuYues model);
	//获得根据预约号和证件号获得单个预约的详细信息
	public String singleYuYue(YuYues model);
	//取消预约
	public String cancelYuYue(YuYues model);
	//根据openid获得用户的预约列表
	public String yuYueList(String openid);
}
