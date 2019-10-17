package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.StreetDao;
import com.gdyiko.zcwx.po.Street;
import com.gdyiko.zcwx.service.StreetService;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("streetService")
public class StreetServiceImpl extends GenericServiceImpl<Street, String> implements StreetService {
	@Resource(name = "streetDao")
	StreetDao streetDao;

	@Resource(name = "streetDao")
	@Override
	public void setGenericDao(GenericDao<Street, String> genericDao) {
		super.setGenericDao(genericDao);
	}

}
