package com.gdyiko.zcwx.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gdyiko.zcwx.dao.SsAffairsMaterialsDao;
import com.gdyiko.zcwx.service.SsAffairsMaterialsService;
import com.gdyiko.zcwx.po.SsAffairMaterials;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("ssAffairsMaterialsService")
public class SsAffairsMaterialsServiceImpl extends
		GenericServiceImpl<SsAffairMaterials, String> implements
		SsAffairsMaterialsService {
	@Resource(name = "ssAffairsMaterialsDao")
	SsAffairsMaterialsDao ssAffairsMaterialsDao;

	@Resource(name = "ssAffairsMaterialsDao")
	@Override
	public void setGenericDao(GenericDao<SsAffairMaterials, String> genericDao) {
		super.setGenericDao(genericDao);
	}


	
}
