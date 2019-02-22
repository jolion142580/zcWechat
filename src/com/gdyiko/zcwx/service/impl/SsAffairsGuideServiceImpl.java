package com.gdyiko.zcwx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.SsAffairsGuideDao;
import com.gdyiko.zcwx.service.SsAffairsGuideService;
import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("ssAffairsGuideService")
public class SsAffairsGuideServiceImpl extends
		GenericServiceImpl<SsAffairGuide, String> implements
		SsAffairsGuideService {
	@Resource(name = "ssAffairsGuideDao")
	SsAffairsGuideDao ssAffairsGuideDao;

	@Resource(name = "ssAffairsGuideDao")
	@Override
	public void setGenericDao(GenericDao<SsAffairGuide, String> genericDao) {
		super.setGenericDao(genericDao);
	}

	public List<SsAffairGuide> findBybasedicId(String basedicid) {
		SsAffairGuide ssaffairgide = new SsAffairGuide();
		//ssaffairgide.setBaseDicId(basedicid);
		List<SsAffairGuide> list = ssAffairsGuideDao.findLikeByEntity(ssaffairgide, BeanUtilEx.getNotNullEscapePropertyNames(ssaffairgide));

		return list;
	}

	
}
