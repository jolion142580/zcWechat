package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.OnlineApplyDao;
import com.gdyiko.zcwx.dao.SsUserInfoDao;
import com.gdyiko.zcwx.dao.WxTemplateLogDao;
import com.gdyiko.zcwx.service.OnlineApplyService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.service.WxTemplateLogService;
import com.gdyiko.zcwx.po.OnlineApply;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.po.WxTemplateLog;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("wxTemplateLogService")
public class WxTemplateLogServiceImpl extends
		GenericServiceImpl<WxTemplateLog, String> implements
		WxTemplateLogService {
	@Resource(name = "wxTemplateLogDao")
	WxTemplateLogDao wxTemplateLogDao;

	@Resource(name = "wxTemplateLogDao")
	@Override
	public void setGenericDao(GenericDao<WxTemplateLog, String> genericDao) {
		super.setGenericDao(genericDao);
	}

}
