package com.gdyiko.zcwx.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.SmsInfo;
import com.gdyiko.zcwx.service.SmsInfoService;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;
import com.opensymphony.xwork2.ActionContext;

@Namespace("/")
@Action(value = "smsinfo", results = {})
public class SmsInfoAction  extends BaseAction<SmsInfo, String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int phonecount;
	private int ipcount;
	private String random_num;

	@Resource(name = "SmsInfoService")
	SmsInfoService smsInfoService;
	
	@Resource(name = "SmsInfoService")
	
	@Override
	public void setGenericService(GenericService<SmsInfo, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}
	
	public String saveSmsinfo() {
		JSONObject rs;
		
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.model.setCreattime(ft.format(date));
		
		JSONObject phone_count = JSONObject.fromObject(getMobilecount());
//		System.out.println("----phone=--"+phone_count.get("count"));
		
		JSONObject ip_count = JSONObject.fromObject(getIpcount());
//		System.out.println("----ip=--"+ip_count.get("count"));
		
		int phoneforcount=Integer.parseInt(phone_count.get("count").toString());
		int ipforcount = Integer.parseInt(ip_count.get("count").toString());
//		String smscode = (String) ActionContext.getContext().getSession().get("smscode");
		int codeLength = 6;
		String smscode = "";
		for (int i = 0; i < codeLength; i++) {
			smscode += (int) (Math.random() * 9);
		}

		this.model.setCode(smscode);
		String rand=(String) ActionContext.getContext().getSession().get("numRandom");//对照验证码
		System.out.println(rand+";:::::::"+random_num);
		if(rand==null){
			rs = new JSONObject();
			rs.put("flag", "0");
			rs.put("errorMsg", "验证码过期请重新获取");
			System.out.println(rs.toString());
			Struts2Utils.renderText(rs.toString());
			return null;
		}else if(!rand.equals(random_num)){
			rs = new JSONObject();
			rs.put("flag", "0");
			rs.put("errorMsg", "验证码错误");
			System.out.println(rs.toString());
			
			Struts2Utils.renderText(rs.toString());
			return null;
		} else if(phoneforcount>phonecount){
			rs = new JSONObject();
			rs.put("flag", "0");
			rs.put("errorMsg", "当前号码："+this.model.getSmsmobile()+" 已经超出了当天发送次数（"+phonecount+"次）");
			System.out.println(rs.toString());
			Struts2Utils.renderText(rs.toString());
			return null;
		}else if(ipforcount>ipcount){
			rs = new JSONObject();
			rs.put("flag", "0");
			rs.put("errorMsg", "当前IP地址："+this.model.getIp()+" 已经超出了当天发送次数（"+ipcount+"次）");
			System.out.println(rs.toString());
			Struts2Utils.renderText(rs.toString());
			return null;
		}else{
			
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession session=request.getSession();
			session.setMaxInactiveInterval(10*60);
			session.removeAttribute("smscode");
			session.setAttribute("smscode",smscode);
			
			return  super.save();
		}
		
	}
	
	public String getMobilecount() {
		// TODO Auto-generated method stub
		String result = smsInfoService.getmoblie(this.model.getCreattime(), this.model.getSmsmobile());
//		System.out.println("result-1--->"+result);
		//Struts2Utils.renderText(result);
		return result;
	}
	
	public String getIpcount(){
		String result = smsInfoService.getIp(this.model.getCreattime(), this.model.getIp());
//		System.out.println("result-2--->"+result);
		//Struts2Utils.renderText(result);
		return result;
	}

	public int getPhonecount() {
		return phonecount;
	}

	public void setPhonecount(int phonecount) {
		this.phonecount = phonecount;
	}

	public void setIpcount(int ipcount) {
		this.ipcount = ipcount;
	}

	public String getRandom_num() {
		return random_num;
	}

	public void setRandom_num(String random_num) {
		this.random_num = random_num;
	}


	
}
