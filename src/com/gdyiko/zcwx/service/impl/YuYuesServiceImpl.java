package com.gdyiko.zcwx.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.YuYuesDao;
import com.gdyiko.zcwx.po.YuYues;
import com.gdyiko.zcwx.service.InterfaceService;
import com.gdyiko.zcwx.service.YuYuesService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("yuYuesService")
public class YuYuesServiceImpl extends GenericServiceImpl<YuYues, String>
		implements YuYuesService {
	@Resource(name = "yuYuesDao")
	YuYuesDao yuYuesDao;

	@Resource(name = "yuYuesDao")
	@Override
	public void setGenericDao(GenericDao<YuYues, String> genericDao) {
		super.setGenericDao(genericDao);
	}

	@Resource(name = "interfaceService")
	InterfaceService ifs;

	// 根据日期和street，获得8:30到17:30的剩余号数
	public String getCount(String street, String ydate,String businessType,Integer weight) {
		JSONObject data = new JSONObject();
		try {
			/*
			 * String count = ifs.onlineQuery(street,ydate,"08:30","09:30");
			 * JSONObject jsonObject = new JSONObject(count); JSONObject jo =
			 * (JSONObject) jsonObject.get("data");
			 * System.out.println(jo.get("count"));
			 */
			//data.put("time0", handleJson(street, ydate, "08:30", "09:30"));
			data.put("time1", handleJson(street, ydate, "09:30", "10:30",businessType,weight));
			data.put("time2", handleJson(street, ydate, "10:30", "11:30",businessType,weight));
			data.put("time3", handleJson(street, ydate, "14:30", "15:30",businessType,weight));
			data.put("time4", handleJson(street, ydate, "15:30", "16:30",businessType,weight));
			//data.put("time5", handleJson(street, ydate, "16:30", "17:30"));
			return data.toString();
			// return null;
		} catch (Exception e) {
			return "";
		}
	}

	// onlineQuery在onlineQuery返回的json中提取count
	private String handleJson(String street, String ydate, String s_time,
			String e_time,String businessType,int weight) throws ParseException {
		
		String result="";
		Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		  
		  Date date2 = format.parse( ydate+" "+ s_time);
		  long l = date.getTime() - date2.getTime();
		//  System.out.println("--时间差--"+l);
		  

		JSONObject jsonObject = null;
		JSONObject jo = null;
		String count = "";
		try {
			if(l>0){
				  result="{\"data\":{\"count\":\"0\"}}";
			  }else{
				// 返回报文
					result = ifs.onlineQuery(street, ydate, s_time, e_time,businessType,weight);
			  }
			jsonObject = new JSONObject(result);
			jo = (JSONObject) jsonObject.get("data");
			count = jo.get("count").toString();
		} catch (Exception e) {

		}
		return count;
	}

	// 保存预约(需要做校验)
	public String saveYuYues(YuYues model)  {
		String street = model.getStreet();
		String service_type = model.getStype();
		String name = model.getName();
		String id_card = model.getIdcard();
		String phone = model.getPhone();
		String date = model.getYdate();
		String s_time = model.getYstime();
		String e_time = model.getYetime();
		String openid = model.getOpenid();
		String businessName = model.getBusinessName();
		int weight = model.getWeight();
		// 先校验
		String validate = null;
		try {
			validate = handleJson(street, date, s_time, e_time,service_type.substring(0, 2),weight);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (validate == "" || Integer.valueOf(validate) < 1) {
			return "该时段没有预约号";
		}
		// 验证成功再预约
		String message = "";
		String result = "";
		message = ifs.yuYues(street, service_type, name, id_card, phone, date,
				s_time, e_time,openid,businessName,weight);

		// 返回data的值
		result = getJsonData(message);

		/*
		 * JSONObject jsonObject = null; try { jsonObject = new
		 * JSONObject(message); result = jsonObject.get("data").toString(); }
		 * catch (JSONException e) {
		 * 
		 * }
		 */
		return result;
	}

	// 获得根据预约号和证件号获得单个预约的详细信息
	public String singleYuYue(YuYues model) {
		String booking_no = model.getNo();
		String id_card = model.getIdcard();
		// 如果证件没有值则跳出方法
		if (booking_no == null || id_card == null || "".equals(booking_no)
				|| "".equals(id_card)) {
			return "";
		}

		String message = "";
		String result = "";
		// 传入booking_no和id_card
		message = ifs.yuYuesState(booking_no, id_card);
		result = getJsonData(message);
		return result;
	}

	// 取消预约
	public String cancelYuYue(YuYues model) {
		String booking_no = model.getNo();
		String id_card = model.getIdcard();
		// 如果证件没有值则跳出方法
		if (booking_no == null || id_card == null || "".equals(booking_no)
				|| "".equals(id_card)) {
			return "";
		}

		String message = "";
		String result = "";
		// 传入booking_no和id_card
		message = ifs.cancelYuYues(booking_no, id_card);
		result = getJsonData(message);
		return result;
	}

	// 获得json的中data
	private String getJsonData(String json) {
		JSONObject jsonObject = null;
		String jsonData = "";
		try {
			jsonObject = new JSONObject(json);
			jsonData = jsonObject.get("data").toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonData;
	}

	// 根据openid获得用户的预约列表
	public String yuYueList(String openid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String today = sdf.format(new Date());
		Calendar cale = null;
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		String firstday;
		firstday = sdf.format(cale.getTime());
//		System.out.println("firstday=="+firstday);
		
		// 先获得月初日期之前的数据
		YuYues yy = new YuYues();
		yy.setOpenid("equals%3A" + openid);
		yy.setId("orderby1_desc_");
		yy.setYdate("lt<" + firstday);
		List<YuYues> list = super.findLikeByEntity(yy,
				BeanUtilEx.getNotNullEscapePropertyNames(yy));
		// 再获得月初日期之后的数据(包括当前日期)

		YuYues yy2 = new YuYues();
		yy2.setOpenid("equals%3A" + openid);
		yy2.setId("orderby1_desc_");
		yy2.setYdate("gt>=" + firstday);
		List<YuYues> list2 = super.findLikeByEntity(yy2,
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
