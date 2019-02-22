package com.gdyiko.zcwx.action;

import java.util.List;

import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.FAQ;
import com.gdyiko.zcwx.service.FAQService;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;
import com.opensymphony.xwork2.ActionContext;

@Namespace("/")
@Action(value = "FAQ", results = {
		@Result(name = "success", location = "/faq.jsp"),

		// 失败
		@Result(name = "fail", location = "/")
})
public class FAQAction extends BaseAction<FAQ, String> {
	
	private List<FAQ> faqList;
	public static String openid;
	
	private String problems;
	
//	private String problems;
	@Resource(name = "FAQService")
	FAQService faqService;
	
	@Override
	@Resource(name = "FAQService")
	public void setGenericService(GenericService<FAQ, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}

/*	public FAQAction(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get("request");
		String code = request.getParameter("code");
		OAuth oauth=new OAuth();
		 openid=oauth.getOppenid(code);
		 System.out.println("--openid--"+openid);
		this.setOpenid(openid);
	}*/



	public String getAnswer(){
	//	System.out.println(this.model.getProblem());
		String result = faqService.findAnswer(this.problems);

		
		//System.out.println("result---->"+result);
		//Struts2Utils.renderText(result,new String[0]);
		return result;
	}
	
	public String findAll(){
		faqList = faqService.findAll();
		System.out.println(faqList.size());
		//ServletActionContext.getRequest().setAttribute("list", ssBaseDicsList);
		 ActionContext.getContext().put("problemsItem", faqList);
		return "success";
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getProblems() {
		return problems;
	}

	public void setProblems(String problems) {
		this.problems = problems;
	}
	
	
	
/*public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	FAQAction faqaction= new FAQAction();
	
	//FAQAction faqaction = new FAQAction();
	faqaction.setProblems("入学查询");
	String res =faqaction.getAnswer();
	System.out.println(res);
}*/
}
