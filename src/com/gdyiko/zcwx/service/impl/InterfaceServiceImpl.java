package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.zcwx.service.InterfaceService;
import com.gdyiko.zcwx.weixinUtils.HttpContent;

@Service("interfaceService")
public class InterfaceServiceImpl implements InterfaceService {

	@Resource(name="propertieService")
	PropertieService propertieService;
	
	private String sp;
	
	public InterfaceServiceImpl(){
		//sp=propertieService.getPropertie("servicePath");
	}
	
	//预约签到接口
	public String signIn(String street, String id_card, String booking_no, String phone){
		try{
			JSONObject header = new JSONObject();
			//请求头
			header.put("handler_id", "2001");
			header.put("command_id", "2003");
			header.put("terminal", "1002");
			header.put("version", "0");
			header.put("reserved", "0");
			
			//参数
			JSONObject data = new JSONObject();
			data.put("street", street);
			data.put("id_card", id_card);
			data.put("booking_no", booking_no);
			data.put("phone", phone);
			
			//组合参数
			JSONObject obj = new JSONObject();
			obj.put("header",header);
			obj.put("data",data);
			
			String jsonData = obj.toString();
//			System.out.println("jsonData--->"+jsonData);
			HttpContent httpContent = new HttpContent();
			//String content = httpContent.getHttpContent("http://192.168.1.8:8383", jsonData, "", "POST");
			sp=propertieService.getPropertie("servicePath");
			String content = httpContent.getHttpContent(sp, jsonData, "", "POST");
//			System.out.println(content);
			return content;
		}catch(Exception e){
			return "读取数据失败";
		}
	}

	//预约接口
	public String yuYues(String street, String service_type, String name,
			String id_card, String phone, String date, String s_time, String e_time,String openid,String businessName,int weight) {
		try{
			JSONObject header = new JSONObject();
			//请求头
			header.put("handler_id", "2001");
			header.put("command_id", "2001");
			header.put("terminal", "1002");
			header.put("version", "0");
			header.put("reserved", "0");
			
			//参数
			JSONObject data = new JSONObject();
			data.put("street", street);
			data.put("service_type", service_type);
			data.put("name", name);
			data.put("id_card", id_card);
			data.put("phone", phone);
			data.put("date", date);
			data.put("s_time", s_time);
			data.put("e_time", e_time);
			data.put("openid", openid);
//			data.put("businessName", businessName);
//			data.put("weight", weight);
			
			//组合参数
			JSONObject obj = new JSONObject();
			obj.put("header",header);
			obj.put("data",data);
			
			String jsonData = obj.toString();
//			System.out.println("jsonData--->"+jsonData);
			HttpContent httpContent = new HttpContent();
			//String content = httpContent.getHttpContent("http://192.168.1.8:8383", jsonData, "", "POST");
			sp=propertieService.getPropertie("servicePath");
			String content = httpContent.getHttpContent(sp, jsonData, "", "POST");
//			System.out.println(content);
			return content;
		}catch(Exception e){
			return "读取数据失败";
		}
	}

	//业务事项清单查询接口
	public String businessList(String department) {
		try{
			JSONObject header = new JSONObject();
			//请求头
			header.put("handler_id", "3001");
			header.put("command_id", "3004");
			header.put("terminal", "1002");
			header.put("version", "0");
			header.put("reserved", "0");
			
			//参数
			JSONObject data = new JSONObject();
			data.put("department", department);
			
			//组合参数
			JSONObject obj = new JSONObject();
			obj.put("header",header);
			obj.put("data",data);
			
			String jsonData = obj.toString();
//			System.out.println("jsonData--->"+jsonData);
			HttpContent httpContent = new HttpContent();
			//String content = httpContent.getHttpContent("http://192.168.1.8:8383", jsonData, "", "POST");
			sp=propertieService.getPropertie("servicePath");
			String content = httpContent.getHttpContent(sp, jsonData, "", "POST");
//			System.out.println(content);
			return content;
		}catch(Exception e){
			return "读取数据失败";
		}
	}

	//排队情况查询
	public String queueQuery(String street) {
		try{
			JSONObject header = new JSONObject();
			//请求头
			header.put("handler_id", "1001");
			header.put("command_id", "1004");
			header.put("terminal", "1002");
			header.put("version", "0");
			header.put("reserved", "0");
			
			//参数
			JSONObject data = new JSONObject();
			data.put("street", street);
			
			//组合参数
			JSONObject obj = new JSONObject();
			obj.put("header",header);
			obj.put("data",data);
			
			String jsonData = obj.toString();
//			System.out.println("jsonData--->"+jsonData);
			HttpContent httpContent = new HttpContent();
			//String content = httpContent.getHttpContent("http://192.168.1.8:8383", jsonData, "", "POST");
			sp=propertieService.getPropertie("servicePath");
			String content = httpContent.getHttpContent(sp, jsonData, "", "POST");
//			System.out.println(content);
			return content;
		}catch(Exception e){
			return "读取数据失败";
		}
	}

	//上传街镇排队情况查询接口
	public String townQueueQuery(String street, String queues) {
		try{
			JSONObject header = new JSONObject();
			//请求头
			header.put("handler_id", "1001");
			header.put("command_id", "1001");
			header.put("terminal", "1002");
			header.put("version", "0");
			header.put("reserved", "0");
			
			//参数
			JSONObject data = new JSONObject();
			data.put("street", street);
			data.put("queues", queues);
			
			//组合参数
			JSONObject obj = new JSONObject();
			obj.put("header",header);
			obj.put("data",data);
			
			String jsonData = obj.toString();
//			System.out.println("jsonData--->"+jsonData);
			HttpContent httpContent = new HttpContent();
			//String content = httpContent.getHttpContent("http://192.168.1.8:8383", jsonData, "", "POST");
			sp=propertieService.getPropertie("servicePath");
			String content = httpContent.getHttpContent(sp, jsonData, "", "POST");
//			System.out.println(content);
			return content;
		}catch(Exception e){
			return "读取数据失败";
		}
	}

	//网点预约情况查询接口
//	public String onlineQuery(String street, String date, String s_time, String e_time,String businessType,int weight) {
	public String onlineQuery(String street, String date, String s_time, String e_time) {
		try{
			JSONObject header = new JSONObject();
			//请求头
			header.put("handler_id", "2001");
			header.put("command_id", "2004");
			header.put("terminal", "1002");
			header.put("version", "0");
			header.put("reserved", "0");
			
			//参数
			JSONObject data = new JSONObject();
			data.put("street", street);
			data.put("date", date);
			data.put("s_time", s_time);
			data.put("e_time", e_time);
//			data.put("businessType", businessType);
//			data.put("weight", weight);
			
			//组合参数
			JSONObject obj = new JSONObject();
			obj.put("header",header);
			obj.put("data",data);
			
			String jsonData = obj.toString();
//			System.out.println("jsonData--->"+jsonData);
			HttpContent httpContent = new HttpContent();
			//String content = httpContent.getHttpContent("http://192.168.1.8:8383", jsonData, "", "POST");
			sp=propertieService.getPropertie("servicePath");
			System.out.println("com.gdyiko.zcwx.service.impl.InterfaceServiceImpl line[236] output: -=>" +sp);
			System.out.println("com.gdyiko.zcwx.service.impl.InterfaceServiceImpl line[237] output: -=>" +jsonData);
			String content = httpContent.getHttpContent(sp, jsonData, "", "POST");
//			System.out.println(content);
			return content;
		}catch(Exception e){
			return "读取数据失败";
		}
	}

	//取消预约接口
	public String cancelYuYues(String booking_no, String id_card) {
		try{
			JSONObject header = new JSONObject();
			//请求头
			header.put("handler_id", "2001");
			header.put("command_id", "2002");
			header.put("terminal", "1002");
			header.put("version", "0");
			header.put("reserved", "0");
			
			//参数
			JSONObject data = new JSONObject();
			data.put("booking_no", booking_no);
			data.put("id_card", id_card);
			
			//组合参数
			JSONObject obj = new JSONObject();
			obj.put("header",header);
			obj.put("data",data);
			
			String jsonData = obj.toString();
//			System.out.println("jsonData--->"+jsonData);
			HttpContent httpContent = new HttpContent();
			//String content = httpContent.getHttpContent("http://192.168.1.8:8383", jsonData, "", "POST");
			sp=propertieService.getPropertie("servicePath");
			String content = httpContent.getHttpContent(sp, jsonData, "", "POST");
//			System.out.println(content);
			return content;
		}catch(Exception e){
			return "读取数据失败";
		}
	}

	//字典查询接口
	public String dictionary(String type) {
		try{
			JSONObject header = new JSONObject();
			//请求头
			header.put("handler_id", "4001");
			header.put("command_id", "4001");
			header.put("terminal", "1002");
			header.put("version", "0");
			header.put("reserved", "0");
			
			//参数
			JSONObject data = new JSONObject();
			data.put("type", type);
			
			//组合参数
			JSONObject obj = new JSONObject();
			obj.put("header",header);
			obj.put("data",data);
			
			String jsonData = obj.toString();
//			System.out.println("jsonData--->"+jsonData);
			HttpContent httpContent = new HttpContent();
			//String content = httpContent.getHttpContent("http://192.168.1.8:8383", jsonData, "", "POST");
			sp=propertieService.getPropertie("servicePath");
			String content = httpContent.getHttpContent(sp, jsonData, "", "POST");
//			System.out.println(content);
			return content;
		}catch(Exception e){
			return "读取数据失败";
		}
	}

	//查询预约状态信息
	public String yuYuesState(String booking_no, String id_card) {
		try{
			JSONObject header = new JSONObject();
			//请求头
			header.put("handler_id", "2001");
			header.put("command_id", "2007");
			header.put("terminal", "1002");
			header.put("version", "0");
			header.put("reserved", "0");
			
			//参数
			JSONObject data = new JSONObject();
			data.put("booking_no", booking_no);
			data.put("id_card", id_card);
			
			//组合参数
			JSONObject obj = new JSONObject();
			obj.put("header",header);
			obj.put("data",data);
			
			String jsonData = obj.toString();
//			System.out.println("jsonData--->"+jsonData);
			HttpContent httpContent = new HttpContent();
			//String content = httpContent.getHttpContent("http://192.168.1.8:8383", jsonData, "", "POST");
			sp=propertieService.getPropertie("servicePath");
			String content = httpContent.getHttpContent(sp, jsonData, "", "POST");
//			System.out.println(content);
			return content;
		}catch(Exception e){
			return "读取数据失败";
		}
	}
	
	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}
	
/*	public static void main(String[] args) throws JSONException {
		System.out.println(11111);
		InterfaceServiceImpl test = new InterfaceServiceImpl();
		//PropertieService ps=new PropertieService();
		
		//System.out.println("sign in--->"+test.signIn("12","1234567890","12341234","1234567890"));
		//System.out.println("onlineQuery--->"+test.onlineQuery("0","2016-12-28","09:30","10:30"));
		System.out.println("businessList--->"+test.businessList("HY"));
	}*/
	
}
