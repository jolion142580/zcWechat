package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.po.Long2Short;
import com.gdyiko.zcwx.service.Long2ShortService;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;
@Service("Long2ShortService")
public class Long2ShortServiceImpl  extends GenericServiceImpl<Long2Short, String> implements Long2ShortService {
	
	@Resource(name = "Long2ShortDao")
	@Override
	public void setGenericDao(GenericDao<Long2Short, String> genericDao) {
		super.setGenericDao(genericDao);
	}
}
