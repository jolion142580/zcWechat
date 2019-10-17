package com.gdyiko.base.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.gdyiko.base.po.MemGeninf;
import com.gdyiko.base.service.LoginService;
import com.gdyiko.base.service.MemGeninfService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.MD5Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



	 @Namespace("/")
	 @Action(value = "login", results = {
	 // 成功
	 @Result(name = "success", location = "/logined.jsp"),
	 // 失败
	 @Result(name = "input", location = "/login.jsp"),

	 @Result(name = "logout", location = "/login.jsp",type="redirect")

	 })
	 public class LoginAction extends ActionSupport {

	 /**
	 * 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private String userName;
	private String password;
	private String md5pw;
	
	private LoginService loginService;
	
	private static final long serialVersionUID = 7257397477502147052L;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String logout(){
		
		System.out.println("-----------------------------登出测试！！");
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("loginid");
		session.removeAttribute("memid");
		session.removeAttribute("username");
		session.removeAttribute("user");
		return "logout";
	}

	/*
	@Override
	public void validate() { // 验证

		if (null == this.getUserName() || "".equals(this.getUserName().trim())) {
			this.addFieldError("username", "username request");
		}
		if (null == this.getPassword() || "".equals(this.getPassword().trim())) {
			this.addFieldError("password", "password request");
		}
	}
	*/
	
	public String execute(){  
		        System.out.println(userName);  
		        System.out.println(password);  
		        //若session已经包含登陆信息则直接放行
		       MemGeninf user =  (MemGeninf) ActionContext.getContext().getSession().get("user");
		       System.out.println("若session已经包含登陆信息则直接放行---------------->"+user);
		       if(user!=null){
		    	   return "success";
		       }
		          
		         if (isInvalid(userName))     
		                return INPUT;     
		        
		         if (isInvalid(password))     
		               return INPUT;
		                    
		        //加密后字符串
		        MemGeninf  memGeninf  = loginService.loginVerify(userName, password);
		       if(memGeninf!=null){
		    	   ActionContext.getContext().getSession().put("loginid",userName);
		    	   ActionContext.getContext().getSession().put("memid", memGeninf.getMemid());
		    	   ActionContext.getContext().getSession().put("username", memGeninf.getUsername());
		    	   ActionContext.getContext().getSession().put("user", memGeninf);
		    	   return "success";
		       }

		    return "input";  
		}  

	
	public String getMd5pw() {
		return md5pw;
	}

	public void setMd5pw(String md5pw) {
		this.md5pw = md5pw;
	}

	private boolean isInvalid(String value) {     
		    return (value == null || value.length() == 0);     
	}



	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}  


	
}
