package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.OnlineApplyDao;
import com.gdyiko.zcwx.dao.SsUserInfoDao;
import com.gdyiko.zcwx.service.OnlineApplyService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.po.OnlineApply;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("onlineApplyService")
public class OnlineApplyServiceImpl extends
		GenericServiceImpl<OnlineApply, String> implements
		OnlineApplyService {
	@Resource(name = "onlineApplyDao")
	OnlineApplyDao onlineApplyDao;

	@Resource(name = "onlineApplyDao")
	@Override
	public void setGenericDao(GenericDao<OnlineApply, String> genericDao) {
		super.setGenericDao(genericDao);
	}

}
