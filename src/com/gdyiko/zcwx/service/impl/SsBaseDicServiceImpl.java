package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.SsBaseDicDao;
import com.gdyiko.zcwx.dao.SsUserInfoDao;
import com.gdyiko.zcwx.service.SsBaseDicService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.po.SsBaseDic;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("ssBaseDicService")
public class SsBaseDicServiceImpl extends
		GenericServiceImpl<SsBaseDic, String> implements
		SsBaseDicService {
	@Resource(name = "ssBaseDicDao")
	SsBaseDicDao ssBaseDicDao;

	@Resource(name = "ssBaseDicDao")
	@Override
	public void setGenericDao(GenericDao<SsBaseDic, String> genericDao) {
		super.setGenericDao(genericDao);
	}

}
