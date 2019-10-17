package com.gdyiko.zcwx.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.Long2Short;
import com.gdyiko.zcwx.service.Long2ShortService;
import com.gdyiko.tool.ShortUrlUtil;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;

@Namespace("/")
@Action(value = "long2short", results = {
		

})
public class Long2ShortAction extends BaseAction<Long2Short, String> {
	
	@Resource(name = "Long2ShortService")
	Long2ShortService long2ShortService;
	
	@Resource(name = "Long2ShortService")
	public void setGenericService(GenericService<Long2Short, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}
	
	public String change(){
		System.out.println(this.model.getLongUrl());
		String[] url = ShortUrlUtil.shortUrl(this.model.getLongUrl());
		this.model.setShortUrl(url[0]);
		
		return super.save();
	}
	
	public String geturl(){
		System.out.println(this.model.getShortUrl());
		Long2Short long2 = long2ShortService.findById(this.model.getShortUrl());
		Struts2Utils.renderText(long2.getLongUrl());
		return null;
	}
}
