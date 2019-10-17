package com.gdyiko.zcwx.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.YuYuesFull;
import com.gdyiko.zcwx.service.YuYuesFullService;
import com.gdyiko.tool.action.BaseAction;

@Namespace("/")
@Action(value = "yuYuesFull", results = {
	//成功
	@Result(name = "success", location = "/")
})
public class YuYuesFullAction extends BaseAction<YuYuesFull, String> {
	
	
	@Resource(name = "yuYuesFullService")
	YuYuesFullService yuYuesFullService;
	
	//获得用户的预约信息列表
	public String userYuYuesFullJson(){

		String result = yuYuesFullService.yuYueFullList(this.model.getOpenid());
//		System.out.println("result---->"+result);
		Struts2Utils.renderText(result);
		return null;
	}
	

}
