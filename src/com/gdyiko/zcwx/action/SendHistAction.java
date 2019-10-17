package com.gdyiko.zcwx.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.SendHist;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.SendHistService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.OAuth;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;

@Namespace("/")
@Action(value = "sendHist", results = {})
public class SendHistAction extends BaseAction<SendHist, String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "sendHistService")
	SendHistService sendHistService;
	
	@Resource(name = "ssUserInfoService")
	SsUserInfoService ssUserInfoService;
	
	@Resource(name = "sendHistService")
	
	@Override
	public void setGenericService(GenericService<SendHist, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}
	
	private String code;
	private String tItemIds;
	
	public String insertSendInfo(){
		
		OAuth oauth=new OAuth();
		String openid=oauth.getOppenid(this.getCode());
		System.out.println("openid---"+openid);
		SsUserInfo ssUserInfo = ssUserInfoService.findById(openid);
		System.out.println("------"+ssUserInfo.getName());
		this.model.setUserName(ssUserInfo.getName());
		this.model.setIdCardNum(ssUserInfo.getIdCard());
		this.model.setPhone(ssUserInfo.getPhone());
		//this.model.setNumber(this.getNumber());
/*		System.out.println("----itemId:"+this.model.getItemId());
		System.out.println("----tItemIds:"+this.gettItemIds());
		System.out.println("------number:"+this.model.getNumber());*/
		
		/*String rs =sendHistService.save(this.model).toString();
		System.out.println("------"+rs);
		Struts2Utils.renderText(rs);*/
		return super.save();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String gettItemIds() {
		return tItemIds;
	}

	public void settItemIds(String tItemIds) {
		this.tItemIds = tItemIds;
	}
	

/*	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	*/
}
