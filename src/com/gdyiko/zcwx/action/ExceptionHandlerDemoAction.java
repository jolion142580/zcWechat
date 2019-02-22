package com.gdyiko.zcwx.action;

import com.opensymphony.xwork2.ActionSupport;


public class ExceptionHandlerDemoAction /*extends ActionSupport*/ {
 
	public String execute(){
        System.out.println("开始执行execute（）方法");
		System.out.println("异常语句开始");
		int x=1/0;//异常语句
		return "index";
	}
}

