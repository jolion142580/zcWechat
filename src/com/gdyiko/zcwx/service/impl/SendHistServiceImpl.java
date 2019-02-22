package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.SendHistDao;
import com.gdyiko.zcwx.po.SendHist;
import com.gdyiko.zcwx.service.SendHistService;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("sendHistService")
public class SendHistServiceImpl  extends GenericServiceImpl<SendHist, String> implements SendHistService {

	@Resource(name = "sendHistDao")
	SendHistDao sendHistDao;

	@Resource(name = "sendHistDao")
	@Override
	public void setGenericDao(GenericDao<SendHist, String> genericDao) {
		super.setGenericDao(genericDao);
	}
}
