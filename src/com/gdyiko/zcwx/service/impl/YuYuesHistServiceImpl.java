package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.YuYuesHistDao;
import com.gdyiko.zcwx.po.YuYuesHist;
import com.gdyiko.zcwx.service.YuYuesHistService;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("yuYuesHistService")
public class YuYuesHistServiceImpl extends GenericServiceImpl<YuYuesHist, String> implements YuYuesHistService {
	@Resource(name = "yuYuesHistDao")
	YuYuesHistDao yuYuesHistDao;

	@Resource(name = "yuYuesHistDao")
	@Override
	public void setGenericDao(GenericDao<YuYuesHist, String> genericDao) {
		super.setGenericDao(genericDao);
	}

}
