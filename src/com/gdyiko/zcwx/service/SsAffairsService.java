package com.gdyiko.zcwx.service;

import java.util.List;

import org.json.JSONObject;

import com.gdyiko.zcwx.po.SsAffairs;
import com.gdyiko.zcwx.po.SsBaseDic;
import com.gdyiko.tool.service.GenericService;

public interface SsAffairsService extends GenericService<SsAffairs, String> {
	

	public JSONObject findConfigByAffairId(String onlineApplyId,String affairid,String objindex,String openid);
	
	public List<SsAffairs> findAffairByIsonline();

}
