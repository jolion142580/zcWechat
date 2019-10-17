package com.gdyiko.zcwx.service;

import java.text.ParseException;

import com.gdyiko.zcwx.po.IsPush;
import com.gdyiko.tool.service.GenericService;

public interface IsPushService extends GenericService<IsPush, String>
{
	/**
	 * 判断指定的预约号是否已经推送
	 * 
	 * @param yyno	预约号码
	 * 
	 * @return	已经推送,返回true;否则返回false;
	 * 
	 * @throws ParseException
	 */
	public boolean yynoIsPush(String yyno) throws ParseException;
}
