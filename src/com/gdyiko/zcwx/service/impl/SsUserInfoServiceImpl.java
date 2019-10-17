package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.SsUserInfoDao;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("ssUserInfoService")
public class SsUserInfoServiceImpl extends
		GenericServiceImpl<SsUserInfo, String> implements
		SsUserInfoService {
	@Resource(name = "ssUserInfoDao")
	SsUserInfoDao ssUserInfoDao;

	@Resource(name = "ssUserInfoDao")
	@Override
	public void setGenericDao(GenericDao<SsUserInfo, String> genericDao) {
		super.setGenericDao(genericDao);
	}

}
