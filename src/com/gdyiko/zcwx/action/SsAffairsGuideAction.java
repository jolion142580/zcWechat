package com.gdyiko.zcwx.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsAffairs;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.SsAffairsGuideService;
import com.gdyiko.zcwx.service.SsAffairsService;
import com.gdyiko.zcwx.service.SsBaseDicService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.OAuth;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.service.GenericService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.conversion.annotations.ConversionRule;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

//@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "ssAffairsGuideInfo", results = {
//成功
		@Result(name = "affairGuide", location = "/affairGuide.jsp"),

		@Result(name = "affair", location = "/affairs.jsp"),
		// 失败
		@Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class SsAffairsGuideAction  extends BaseAction<SsAffairGuide, String> {
	
	/**
	 * 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	HttpSession session = null;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
	
	SsAffairGuide ssAffairGuide;
	SsAffairs ssAffairs;
	String baseDicIds;
	String affairid;
	private String keyword;
	//ssAffairs表中的isDisplay值
	private String isDisplay;
	
	private List<SsAffairGuide> ssAffairsList;
	
	@Resource(name = "ssAffairsGuideService")
	SsAffairsGuideService ssAffairsGuideService ;
	
	@Resource(name = "ssAffairsService")
	SsAffairsService ssAffairsService;

	@Resource(name = "ssAffairsGuideService")
	@Override
	public void setGenericService(GenericService<SsAffairGuide, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}

	public SsAffairsGuideAction()  {
		
	}

	private static final long serialVersionUID = -7209385373170290085L;
	
	public String findByAffairId(){
//		System.out.println("::::affairId	:::::"+this.model.getAffairid());
		//this.model.setBaseDicId(baseDicId);

		SsAffairs ssAffairs = ssAffairsService.findById(this.model.getAffairid());
		//System.out.println("-=-=-="+ssAffairsList.size()+"-=-=-="+ssAffairsList.get(0).getAffairname());
		
		List<SsAffairGuide> ssAffairGuideLists = ssAffairsGuideService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
		ssAffairGuide = ssAffairGuideLists.get(0);
		
		ssAffairGuide.setAffairname(ssAffairs.getAffairname());
		ssAffairGuide.setIswrite(ssAffairs.getIswrite());
		ssAffairGuide.setIsonline(ssAffairs.getIsonline());
		//System.out.println("------"+ssAffairGuideLists.get(0).getCondition());
		
		 ActionContext.getContext().put("ssAffairGuide", ssAffairGuide);
		return "affairGuide";
	}
	
	public void findByAffairIdToWeb(){
//		System.out.println("::::affairId	:::::"+this.model.getAffairid());
		//this.model.setBaseDicId(baseDicId);

		SsAffairs ssAffairs = ssAffairsService.findById(this.model.getAffairid());
		//System.out.println("-=-=-="+ssAffairsList.size()+"-=-=-="+ssAffairsList.get(0).getAffairname());
		
		List<SsAffairGuide> ssAffairGuideLists = ssAffairsGuideService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
		ssAffairGuide = ssAffairGuideLists.get(0);
		
		ssAffairGuide.setAffairname(ssAffairs.getAffairname());
		ssAffairGuide.setIswrite(ssAffairs.getIswrite());
		ssAffairGuide.setIsonline(ssAffairs.getIsonline());
		//System.out.println("------"+ssAffairGuideLists.get(0).getCondition());
		
		JSONObject jo =new JSONObject(ssAffairGuide);
		Struts2Utils.renderText(jo.toString());
		 
	}

	
	public SsAffairGuide getSsAffairGuide() {
		return ssAffairGuide;
	}


	public void setSsAffairGuide(SsAffairGuide ssAffairGuide) {
		this.ssAffairGuide = ssAffairGuide;
	}


	public SsAffairs getSsAffairs() {
		return ssAffairs;
	}


	public void setSsAffairs(SsAffairs ssAffairs) {
		this.ssAffairs = ssAffairs;
	}

	
	
	public String getBaseDicIds() {
		return baseDicIds;
	}


	public void setBaseDicId(String baseDicIds) {
		this.baseDicIds = baseDicIds;
	}


	public String getAffairid() {
		return affairid;
	}


	public void setAffairid(String affairid) {
		this.affairid = affairid;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}



}
