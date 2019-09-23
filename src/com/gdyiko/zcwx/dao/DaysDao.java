package com.gdyiko.zcwx.dao;


import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.zcwx.po.Days;

public interface DaysDao extends GenericDao<Days, String> {
	
	//查找主键，主键为name和year，year锁定当前年份
	public Days findPkey(String name);

}
