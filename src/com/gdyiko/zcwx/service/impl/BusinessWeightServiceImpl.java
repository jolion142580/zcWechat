package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.BusinessWeightDao;
import com.gdyiko.zcwx.dao.StreetDao;
import com.gdyiko.zcwx.po.BusinessWeight;
import com.gdyiko.zcwx.service.BusinessWeightService;
import com.gdyiko.zcwx.service.StreetService;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("businessWeightService")
public class BusinessWeightServiceImpl extends GenericServiceImpl<BusinessWeight, String> implements BusinessWeightService {
	@Resource(name = "businessWeightDao")
	BusinessWeightDao businessWeightDao;

	@Resource(name = "businessWeightDao")
	@Override
	public void setGenericDao(GenericDao<BusinessWeight, String> genericDao) {
		super.setGenericDao(genericDao);
	}

}
