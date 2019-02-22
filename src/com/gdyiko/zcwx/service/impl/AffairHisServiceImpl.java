package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.po.AffairHis;
import com.gdyiko.zcwx.service.AffairHisService;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("AffairHisService")
public class AffairHisServiceImpl extends GenericServiceImpl<AffairHis, String> implements AffairHisService {
	
	@Resource(name = "AffairHisDao")
	@Override
	public void setGenericDao(GenericDao<AffairHis, String> genericDao) {
		super.setGenericDao(genericDao);
	}

}
