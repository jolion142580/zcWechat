package com.gdyiko.base.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.gdyiko.base.po.MemGeninf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		///AFControlUtil afc = new AFControlUtil();
		ActionContext context = invocation.getInvocationContext();
		String theme="default";
		// 通过ActionContext来获取httpRequest
		HttpServletRequest request = (HttpServletRequest) context.get(StrutsStatics.HTTP_REQUEST);
		
		//放行
		MemGeninf u=null; 
	
	
		if(request.getSession().getAttribute("user")!=null){
			u =(MemGeninf) request.getSession().getAttribute("user");
		}
		System.out.println("测试拦截器------------>"+ u);
		
		if (u != null) {
			//后台登陆
			invocation.invoke();
		}
	
		
		return "login";

	}

}
