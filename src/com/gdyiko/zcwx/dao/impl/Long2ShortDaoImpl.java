package com.gdyiko.zcwx.dao.impl;

import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.Long2ShortDao;
import com.gdyiko.zcwx.po.Long2Short;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;
@Repository("Long2ShortDao")
public class Long2ShortDaoImpl extends GenericDaoImpl<Long2Short, String> implements Long2ShortDao {

	@Override
	public Class<Long2Short> getEntityClass() {
		// TODO Auto-generated method stub
		return Long2Short.class;
	}


}
