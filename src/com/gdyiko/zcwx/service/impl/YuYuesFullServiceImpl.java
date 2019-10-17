package com.gdyiko.zcwx.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.YuYuesFullDao;
import com.gdyiko.zcwx.po.YuYuesFull;
import com.gdyiko.zcwx.service.YuYuesFullService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;


@Service("yuYuesFullService")
public class YuYuesFullServiceImpl  extends GenericServiceImpl<YuYuesFull, String> 
		implements YuYuesFullService {
	@Resource(name = "yuYuesFullDao")
	YuYuesFullDao yuYuesFullDao;

	@Resource(name = "yuYuesFullDao")
	@Override
	public void setGenericDao(GenericDao<YuYuesFull, String> genericDao) {
		super.setGenericDao(genericDao);
	}

	// 根据openid获得用户的预约列表
		public String yuYueFullList(String openid) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// String today = sdf.format(new Date());
			Calendar cale = null;
			cale = Calendar.getInstance();
			cale.add(Calendar.MONTH, 0);
			cale.set(Calendar.DAY_OF_MONTH, 1);
			String firstday;
			firstday = sdf.format(cale.getTime());
//			System.out.println("firstday=="+firstday);
			
			// 先获得月初日期之前的数据
			YuYuesFull yy = new YuYuesFull();
			yy.setOpenid("equals%3A" + openid);
			yy.setId("orderby1_desc_");
			yy.setYdate("lt<" + firstday);
			List<YuYuesFull> list = super.findLikeByEntity(yy,
					BeanUtilEx.getNotNullEscapePropertyNames(yy));
			// 再获得月初日期之后的数据(包括当前日期)

			YuYuesFull yy2 = new YuYuesFull();
			yy2.setOpenid("equals%3A" + openid);
			yy2.setId("orderby1_desc_");
			yy2.setYdate("gt>=" + firstday);
			List<YuYuesFull> list2 = super.findLikeByEntity(yy2,
					BeanUtilEx.getNotNullEscapePropertyNames(yy2));
			// 组合形成json
			// 组合参数
			JSONObject obj = new JSONObject();
			String result = "";
			try {
				JSONArray jaOld = JSONArray.fromObject(list);
				JSONArray jaNew = JSONArray.fromObject(list2);
				obj.put("old", jaOld);
				obj.put("future", jaNew);
				result = obj.toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block

			}

			return result;
		}
}
