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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.FileInfo;
import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsAffairs;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.FileInfoService;
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
@Action(value = "fileInfo", results = {
//成功
		@Result(name = "affairGuide", location = "/"),

		@Result(name = "affair", location = "/"),
		// 失败
		@Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class FileInfoAction  extends BaseAction<FileInfo, String> {
	
	/**
	 * 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	HttpSession session = null;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
	
	

	
	@Resource(name = "fileInfoService")
	FileInfoService fileInfoService;

	@Resource(name = "fileInfoService")
	@Override
	public void setGenericService(GenericService<FileInfo, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}

	public FileInfoAction()  {
		
	}

	



}
