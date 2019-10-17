package com.gdyiko.zcwx.service;

import java.util.List;

import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsBaseDic;
import com.gdyiko.tool.service.GenericService;

public interface SsAffairsGuideService extends GenericService<SsAffairGuide, String> {
	public List<SsAffairGuide> findBybasedicId(String basedicid);
}
