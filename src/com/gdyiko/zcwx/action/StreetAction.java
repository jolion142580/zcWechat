package com.gdyiko.zcwx.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.Street;
import com.gdyiko.zcwx.service.StreetService;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;

//@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "Street", results = {
//成功
		@Result(name = "success", location = "/"),

		// 失败
		@Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class StreetAction  extends BaseAction<Street, String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8321018464736012550L;

	public StreetAction() {
		
	}

	@Resource(name = "streetService")
	StreetService streetService;

	@Resource(name = "streetService")
	@Override
	public void setGenericService(GenericService<Street, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}
	 
	public String findAll(){
		
		List<Street> streetLists = streetService.findAll();
		JSONArray ja = JSONArray.fromObject(streetLists);
		Struts2Utils.renderText(ja.toString());
		return null;
		
	}
	
	public String findJson(){
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("----参数-----"+request.getParameter("openid"));
		//String json="{'title':'居住证申办','inline': false, 'labelPosition': 'right', 'labelWidth': '80px', 'size': 'small', 'statusIcon': true, 'formItemList': [{'type': 'input', 'label': '文本', 'disable': false, 'readonly': false, 'value': '', 'placeholder': '请输入一些文本', 'rules': [{'required': true, 'message': '不能为空', 'trigger': 'blur'}, {'min': 1, 'max': 8, 'type': 'string', 'message': '长度在1~8个字符', 'trigger': 'blur'}], 'key': 'name', 'subtype': 'text'}, {'type': 'number', 'label': '数字', 'value': 16, 'decimal1': 2, 'min': 0, 'max': 99998, 'prepend': '￥', 'append': '元', 'key': 'price'}]}";
		String json="{'title':'居住证办理','inline':false,'labelPosition':'right','labelWidth':'80px','size':'small','statusIcon':true,'formItemList':[{'type':'input','label':'文本','disable':false,'readonly':false,'value':'','placeholder':'请输入一些文本','rules':[{'required':true,'message':'不能为空','trigger':'blur'},{'min':1,'max':8,'type':'string','message':'长度在1~8个字符','trigger':'blur'},{'pattern':'^[\\w\\u4e00-\\u9fa5-_]+$','message':'正则验证失败:^[\\w\\u4e00-\\u9fa5-_]+$','trigger':'blur'},{'sql':'SELECT {key} FROM balabala','message':'SQL验证失败','trigger':'blur'}],'key':'205a4be0-4b0d-4ac7-8079-52ee54039eae','subtype':'text'},{'type':'input','label':'文本','disable':false,'readonly':false,'value':'','placeholder':'请输入一些文本','rules':[{'required':true,'message':'不能为空','trigger':'blur'},{'min':1,'max':8,'message':'长度在 1 到 8 个字符','trigger':'blur'},{'pattern':'^[\\w\\u4e00-\\u9fa5-_]+$','message':'正则验证失败:^[\\w\\u4e00-\\u9fa5-_]+$','trigger':'blur'},{'sql':'SELECT {key} FROM balabala','message':'SQL验证失败','trigger':'blur'}],'key':'6c08b245-c51c-460b-8bb9-82878b589f41','subtype':'text'}]}";
		JSONObject jsonObj = JSONObject.fromObject(json);
		Struts2Utils.renderText(jsonObj.toString());
		return null;
		
	}

}
