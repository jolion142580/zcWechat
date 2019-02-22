package com.gdyiko.zcwx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.SsAffairsGuideDao;
import com.gdyiko.zcwx.dao.SsAffairsObjectDao;
import com.gdyiko.zcwx.service.SsAffairsGuideService;
import com.gdyiko.zcwx.service.SsAffairsObjectService;
import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsAffairObject;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("ssAffairsObjectService")
public class SsAffairsObjectServiceImpl extends
		GenericServiceImpl<SsAffairObject, String> implements
		SsAffairsObjectService {
	@Resource(name = "ssAffairsObjectDao")
	SsAffairsObjectDao ssAffairsObjectDao;

	@Resource(name = "ssAffairsObjectDao")
	@Override
	public void setGenericDao(GenericDao<SsAffairObject, String> genericDao) {
		super.setGenericDao(genericDao);
	}


	
}
