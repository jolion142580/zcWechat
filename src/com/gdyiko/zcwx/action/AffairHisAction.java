package com.gdyiko.zcwx.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.AffairHis;
import com.gdyiko.zcwx.service.AffairHisService;
import com.gdyiko.zcwx.weixinUtils.OAuth;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;

@Namespace("/")
@Action(value = "affairhis", results = {
		@Result(name = "success", location = "/affairshis.jsp"),

		// 失败
		@Result(name = "fail", location = "/")
})
public class AffairHisAction extends BaseAction<AffairHis, String> {
	@Resource(name = "AffairHisService")
	AffairHisService affairHisService;
	
	private List<AffairHis> list;
	
	private String keyword;
	private String code;
	private String openid1;
	
	@Override
	@Resource(name = "AffairHisService")
	public void setGenericService(GenericService<AffairHis, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}
	
	public AffairHisAction(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		OAuth oauth=new OAuth();
		openid1 = oauth.getOppenid(code);
		
	//	System.out.println("openid---------"+openid1);
		this.model.setOpenid(openid1);
	}
	
	public String save(){
		OAuth oauth=new OAuth();
		String openid = oauth.getOppenid(code);
//		System.out.println("openid---------"+openid);
		if(openid1==null||"".equals(openid1)){
			
			Struts2Utils.renderText("{\"msg\":\"获取信息失败\"}");
			return null;
		}
		System.out.println("openid---------"+openid1);
		
		AffairHis af = new AffairHis();
		af.setOpenid(openid1);
		af.setAffairid(this.model.getAffairid());
		List<AffairHis> list1 = this.affairHisService.findEqualByEntity(af, BeanUtilEx.getNotNullEscapePropertyNames(af));
		System.out.println(list1);
		if(!"[]".equals(list1.toString())){
//			System.out.println("------------");
			this.model.setId(list1.get(0).getId());
		}
		
		
		this.model.setOpenid(openid1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.model.setCreattime(sdf.format(new Date()));
		
		return super.save();
	}
	
	public String findbyopenid(){
		
		if(openid1==null||"".equals(openid1)){
			
			Struts2Utils.renderText("{\"msg\":\"获取信息失败\"}");
			return null;
		}
		//System.out.println("openid---------"+openid1);
		this.model.setOpenid(openid1);
		this.model.setCreattime("orderby1_desc_");
		list = affairHisService.findLikeByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
		
		return "success";
	}
	
	public String findByAffairName() throws UnsupportedEncodingException{
		
		//keyword=new String(this.model.getAffairname().getBytes("ISO-8859-1"),"UTF-8");
		this.model.setCreattime("orderby1_desc_");
		list = affairHisService.findLikeByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
		JSONArray ja = JSONArray.fromObject(list);
		Struts2Utils.renderText(ja.toString());
		
		return null;
	}

	public List<AffairHis> getList() {
		return list;
	}

	public void setList(List<AffairHis> list) {
		this.list = list;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
