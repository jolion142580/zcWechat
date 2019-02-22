package com.gdyiko.zcwx.dao.impl;

import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.YuYuesFullDao;
import com.gdyiko.zcwx.po.YuYuesFull;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;

@Repository("yuYuesFullDao")
public class YuYuesFullDaoImpl  extends GenericDaoImpl<YuYuesFull, String> implements YuYuesFullDao {

	@Override
	public Class<YuYuesFull> getEntityClass() {
		// TODO Auto-generated method stub
		return YuYuesFull.class;
	}
	
}
