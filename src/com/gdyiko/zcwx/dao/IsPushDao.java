package com.gdyiko.zcwx.dao;

import java.util.List;

import com.gdyiko.zcwx.po.IsPush;
import com.gdyiko.tool.dao.GenericDao;

public interface IsPushDao extends GenericDao<IsPush, String>
{
	/**
	 * 获取指定预约号的记录集合
	 * 
	 * @param yyno	预约号
	 * @return
	 */
	public List<IsPush> findByYyno(String yyno);
}
