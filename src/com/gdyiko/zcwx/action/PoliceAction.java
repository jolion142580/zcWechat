package com.gdyiko.zcwx.action;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.json.JSONObject;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.zcwx.po.AffairProgress;
import com.gdyiko.zcwx.po.SsAffairs;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.SsAffairsService;
import com.gdyiko.zcwx.service.SsBaseDicService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.HttpContent;
import com.gdyiko.zcwx.weixinUtils.OAuth;
import com.gdyiko.zcwx.weixinUtils.WeixinHttpConnect;
import com.gdyiko.zcwx.weixinUtils.WxJSSignUtil;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.service.GenericService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.ConversionRule;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

//@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "police", results = {
//成功
		@Result(name = "success", location = "/"),

		// 失败
		@Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class PoliceAction  extends ActionSupport {
	
	/**
	 * 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	HttpSession session = null;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式

//	@Resource(name = "propertieService")
	PropertieService propertieService;
	
	private String queryName;
	private String queryVal;
		

	public PoliceAction() {
		
		
	}

	private static final long serialVersionUID = -7209385373170290085L;
	
	
	public String findByRootNode() throws JSONException, IOException{

		propertieService=new PropertieService();
		String subjectURL = propertieService.getPropertie("configurePath")+"/departInf!rootNode";
		 //System.out.println(subjectURL);
		 HttpContent httpContent = new HttpContent();
		 String content  = httpContent.getHttpContent(subjectURL, "", "", "GET");
		 System.out.println(content);
			//System.out.println( jsonObject.toString() );
		Struts2Utils.renderText(content);
		return null;
	}
	
	public String findBySubNode() throws JSONException, IOException{

		propertieService=new PropertieService();
		String subjectURL = propertieService.getPropertie("configurePath")+"/departInf!subNode?"+queryName+"="+queryVal;
		 System.out.println(subjectURL);
		 HttpContent httpContent = new HttpContent();
		 String content  = httpContent.getHttpContent(subjectURL, "", "", "GET");
		 System.out.println(content);
			//System.out.println( jsonObject.toString() );
		Struts2Utils.renderText(content);
		return null;
	}
	
	
	public String findByFirstQuestion() throws JSONException, IOException{

		propertieService=new PropertieService();
		String subjectURL = propertieService.getPropertie("configurePath")+"/QuestionListsInf!getFirstQuestion?"+queryName+"="+queryVal;
		 System.out.println(subjectURL);
		 HttpContent httpContent = new HttpContent();
		 String content  = httpContent.getHttpContent(subjectURL, "", "", "GET");
		 System.out.println(content);
			//System.out.println( jsonObject.toString() );
		Struts2Utils.renderText(content);
		return null;
	}
	
	public String findByNextQuestion() throws JSONException, IOException{

		propertieService=new PropertieService();
		String subjectURL = propertieService.getPropertie("configurePath")+"/QuestionListsInf!getNextQuestion?"+queryName+"="+queryVal;
		 System.out.println(subjectURL);
		 HttpContent httpContent = new HttpContent();
		 String content  = httpContent.getHttpContent(subjectURL, "", "", "GET");
		 System.out.println(content);
			//System.out.println( jsonObject.toString() );
		Struts2Utils.renderText(content);
		return null;
	}
	
	public String findByBackQuestion() throws JSONException, IOException{

		propertieService=new PropertieService();
		String subjectURL = propertieService.getPropertie("configurePath")+"/QuestionListsInf!getBackQuestion?"+queryName+"="+queryVal;
		 System.out.println(subjectURL);
		 HttpContent httpContent = new HttpContent();
		 String content  = httpContent.getHttpContent(subjectURL, "", "", "GET");
		 System.out.println(content);
			//System.out.println( jsonObject.toString() );
		Struts2Utils.renderText(content);
		return null;
	}
	
	public String findByFile() throws JSONException, IOException{

		propertieService=new PropertieService();
		String subjectURL = propertieService.getPropertie("configurePath")+"/VAnswerFileInf!getFile?"+queryName+"="+queryVal;
		 System.out.println(subjectURL);
		 HttpContent httpContent = new HttpContent();
		 String content  = httpContent.getHttpContent(subjectURL, "", "", "GET");
		 System.out.println(content);
			//System.out.println( jsonObject.toString() );
		Struts2Utils.renderText(content);
		return null;
	}
	
	public InputStream getFileStream() throws IOException, JSONException{
		propertieService=new PropertieService();
		String subjectURL = propertieService.getPropertie("configurePath")+"/download?"+queryName+"="+queryVal;
		 System.out.println(subjectURL);
		 HttpContent httpContent = new HttpContent();
		 String content  = httpContent.getHttpContent(subjectURL, "", "", "GET");
		 
		 Struts2Utils.renderText(content);
		return null;
	}
	
	
	
	
	public String findBySubId() throws JSONException, IOException{
		System.out.println("-----queryName-------"+queryName+"----queryVal---"+queryVal);
		propertieService=new PropertieService();
		String subjectURL = propertieService.getPropertie("configurePath")+"/querySet!findbyid?"+queryName+"="+queryVal;
		 System.out.println(subjectURL);
		 HttpContent httpContent = new HttpContent();
		 String content  = httpContent.getHttpContent(subjectURL, "", "", "GET");
		 System.out.println(content);
		//System.out.println( jsonObject.toString() );
		Struts2Utils.renderText(content);
		return null;
	}
	
	
	public String findByAnswersId() throws JSONException, IOException{
		
		propertieService=new PropertieService();
		String subjectURL = propertieService.getPropertie("configurePath")+"/answers!findbyid?"+queryName+"="+queryVal;
		 System.out.println(subjectURL);
		 HttpContent httpContent = new HttpContent();
		 String content  = httpContent.getHttpContent(subjectURL, "", "", "GET");
		 System.out.println(content);
		//System.out.println( jsonObject.toString() );
		Struts2Utils.renderText(content);
		return null;
	}

	
	
	
	public String isEqualsNull(String content){
		return content.equals("null")?"":content;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryVal() {
		return queryVal;
	}

	public void setQueryVal(String queryVal) {
		this.queryVal = queryVal;
	}

	

	
/*	public static void main(String[] args) throws JSONException, IOException {
		AffairProgressAction affarip = new AffairProgressAction();
	affarip.setCurrCode("0500SBQ0011612201743285986");
	affarip.findByAffairCode();
	}*/
}
