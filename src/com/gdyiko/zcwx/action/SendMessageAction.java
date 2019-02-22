package com.gdyiko.zcwx.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.resp.Article;
import com.gdyiko.zcwx.weixinUtils.CustomMessageAPI;
import com.opensymphony.xwork2.ActionSupport;

@Controller
//@Scope("prototype")
//@ParentPackage(value = "custom-default")
@Namespace("/")
@Action(value = "sendMessage", results = {})
public class SendMessageAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Msg = "";
	private String openid = "";
	private String MsgUrl = "";
	private String Title = "";
	private String PicUrl ="";
	HttpSession session = null;
	String memId;
	String username;

	
	public SendMessageAction(){
	}

	public String send() throws IOException {

		ArrayList<Article> articles = new ArrayList<Article>();
		Article a = new Article();
		a.setTitle(this.getTitle());
		a.setUrl(this.getMsgUrl());
		a.setDescription(this.getMsg());
		a.setPicUrl(this.getPicUrl());
		articles.add(a);
		//调用接口，并获取返回值
		CustomMessageAPI api = new CustomMessageAPI();
		String result = api.sendNewsToUser(this.getOpenid(), articles);
//		System.out.println("......"+result);
		Struts2Utils.renderText(result, new String[0]);

		return null;
	}
	
	public String textmsg(){
		//调用接口，并获取返回值
		CustomMessageAPI api = new CustomMessageAPI();
		String result = api.sendTextMessageToUser(this.getOpenid(), this.getMsg());
//		System.out.println("......"+result);
		Struts2Utils.renderText(result, new String[0]);
		return null;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getMsgUrl() {
		return MsgUrl;
	}

	public void setMsgUrl(String msgUrl) {
		MsgUrl = msgUrl;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}
	
	
	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


/*	public static void main(String[] args) throws IOException {
		SendMessageAction sendmsg = new SendMessageAction();
		sendmsg.setTitle("预约成功提醒：");
		String str="预约业务：公共租赁房申请\n"; 
		str+="订单号:"+"1234"+"\n";
		str+="预约时间："+"2016-12-29 08:30-09:30"+"\n";
		str+="预约受理点："+"石湾镇街道办事处"+"\n";
		str+="您的预约已成功办理，请在预约事件内携带相关证件到现场取号处，凭身份证或订单号（预约号）取号。（若要取消预约请在《一门服务》-《办事预约》-《查询我的预约》中进行取消）";
		sendmsg.setMsg(str);
		sendmsg.setMsgUrl("http://www.baidu.com");
		sendmsg.setOpenid("oxGFmwY3Dnhq3NzJXGGYPohSAf4I");
		sendmsg.send();
	}
*/
}
