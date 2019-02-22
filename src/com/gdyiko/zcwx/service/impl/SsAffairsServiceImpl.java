package com.gdyiko.zcwx.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.OnlineApplyDao;
import com.gdyiko.zcwx.dao.SsAffairsDao;
import com.gdyiko.zcwx.dao.SsAffairsGuideDao;
import com.gdyiko.zcwx.dao.SsAffairsObjectDao;
import com.gdyiko.zcwx.dao.SsUserInfoDao;
import com.gdyiko.zcwx.service.SsAffairsService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.po.OnlineApply;
import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsAffairObject;
import com.gdyiko.zcwx.po.SsAffairs;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("ssAffairsService")
public class SsAffairsServiceImpl extends
		GenericServiceImpl<SsAffairs, String> implements
		SsAffairsService {
	
	@Resource(name = "onlineApplyDao")
	OnlineApplyDao onlineApplyDao;
	
	@Resource(name = "ssAffairsGuideDao")
	SsAffairsGuideDao ssAffairsGuideDao;
	
	@Resource(name = "ssAffairsDao")
	SsAffairsDao ssAffairsDao;
	
	@Resource(name = "ssAffairsObjectDao")
	SsAffairsObjectDao ssAffairsObjectDao;
	
	@Resource(name = "ssUserInfoDao")
	SsUserInfoDao ssUserInfoDao;

	@Resource(name = "ssAffairsDao")
	@Override
	public void setGenericDao(GenericDao<SsAffairs, String> genericDao) {
		super.setGenericDao(genericDao);
	}

/*	public List<SsAffairs> findTwoLevelAffairByType(String type) {
		String hql="";
		if(type.equals("GA")){
			hql="select distinct twolevelaffair from SsAffairs where baseDicId=15 and twolevelaffair is not null";
		}else{
			hql="select distinct twolevelaffair from SsAffairs where baseDicId<>15 and twolevelaffair is not null";
		}
		return this.getGenericDao().findByHql(hql);
	}*/

	public JSONObject findConfigByAffairId(String onlineApplyId,String affairid,String objindex,String openid) {
		JSONObject jo = null;
		try {
			OnlineApply onlineApply = new OnlineApply();
			String onlineData=""; 
			
			if(onlineApplyId==null){
				SsUserInfo ssUserInfo = ssUserInfoDao.findById(openid);
				onlineData = new JSONObject(ssUserInfo).toString();
			}
			
			if(onlineApplyId!=null && !onlineApplyId.equals("")){
				onlineApply = onlineApplyDao.findById(onlineApplyId);
				onlineData = onlineApply.getOnlineData();
			}

			SsAffairs ssAffairs = ssAffairsDao.findById(affairid);
			
			SsAffairObject ssAffairObject = new SsAffairObject();
			ssAffairObject.setAffairid(affairid);
			ssAffairObject.setObjindex(objindex);
			List<SsAffairObject> ssAffairObjectList = ssAffairsObjectDao.findEqualByEntity(ssAffairObject, BeanUtilEx.getNotNullEscapePropertyNames(ssAffairObject));	
			jo = new JSONObject(ssAffairObjectList.get(0).getConfig());
			//System.out.println("''''1111'''''"+jo.getJSONArray("formItemList"));
			org.json.JSONArray ja = jo.getJSONArray("formItemList");
			for (int i = 0; i < ja.length(); i++) {
				JSONObject job = ja.getJSONObject(i);
				//System.out.println("-=-=-=-="+job.get("key"));
				
				if(!onlineData.equals("")){
					JSONObject onlineDataObject = new JSONObject(onlineData);
					Iterator<String> it = onlineDataObject.keys(); 
					while(it.hasNext()){
						// 获得key
						String key = it.next(); 
						String value = onlineDataObject.getString(key);    
						//System.out.println("key: "+key+",value:"+value);
						//System.out.println("---job key--"+job.get("key"));
						if(job.get("key").toString().equalsIgnoreCase(key)){
							job.put("value", value);
						}
					}
				}
				
			}
			
			
			jo.put("title", ssAffairs.getAffairname());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jo;
	}

	public List<SsAffairs> findAffairByIsonline() {
		
		SsAffairs ssAffairs1 = new SsAffairs();
		ssAffairs1.setIsonline("true");

		List<SsAffairs> ssAffairsList1 = ssAffairsDao.findEqualByEntity(ssAffairs1, BeanUtilEx.getNotNullEscapePropertyNames(ssAffairs1));
		List<SsAffairs> ssAffairsList = new ArrayList<SsAffairs>();
		
		for (SsAffairs ssAffairs : ssAffairsList1) {
			
			SsAffairGuide ssAffairGuide = new SsAffairGuide();
			ssAffairGuide.setAffairid(ssAffairs.getAffairid());
			
			List<SsAffairGuide> ssAffairGuides = ssAffairsGuideDao.findEqualByEntity(ssAffairGuide, BeanUtilEx.getNotNullEscapePropertyNames(ssAffairGuide));
			
			String condition="";
			if(ssAffairGuides.size()>0){
				condition=ssAffairGuides.get(0).getCondition();
			}
			ssAffairs.setCondition(condition);

			ssAffairsList.add(ssAffairs);
			
		}
		return ssAffairsList;
	}

}
