package com.gdyiko.zcwx.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.gdyiko.zcwx.po.YuYuesHist;
import com.gdyiko.zcwx.service.YuYuesHistService;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;

@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "YuYuesHist", results = {
//成功
		@Result(name = "success", location = "/"),

		// 失败
		@Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class YuYuesHistAction  extends BaseAction<YuYuesHist, String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6530708428528956129L;

	public YuYuesHistAction() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get("request");
//		System.out.println(":::::::::"+request.getParameter("code"));
	}

	@Resource(name = "yuYuesHistService")
	YuYuesHistService yuYuesHistService;

	@Resource(name = "yuYuesHistService")
	@Override
	public void setGenericService(GenericService<YuYuesHist, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}

}
