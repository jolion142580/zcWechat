package com.gdyiko.zcwx.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.gdyiko.zcwx.po.ServerType;
import com.gdyiko.zcwx.service.ServerTypeService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;

//@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "ServerType", results = {
//成功
		@Result(name = "success", location = "/choiceDep.jsp"),

		// 失败
		@Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class ServerTypeAction  extends BaseAction<ServerType, String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8321018464736012550L;

	public ServerTypeAction() {
		
	}
	
	private List<ServerType> serverTypeList;

	@Resource(name = "serverTypeService")
	ServerTypeService serverTypeService;

	@Resource(name = "serverTypeService")
	@Override
	public void setGenericService(GenericService<ServerType, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}
	
	public String findAll(){
		
		serverTypeList = serverTypeService.findAll();
		//JSONArray ja = JSONArray.fromObject(wordBookList);
		//Struts2Utils.renderText(ja.toString());
		return "success";
		
	}
	
	public String findListByIsShow(){
		this.model.setIsShow("1");
		serverTypeList = serverTypeService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
		//JSONArray ja = JSONArray.fromObject(wordBookList);
		//Struts2Utils.renderText(ja.toString());
		return "success";
		
	}

	public List<ServerType> getServerTypeList() {
		return serverTypeList;
	}

	public void setServerTypeList(List<ServerType> serverTypeList) {
		this.serverTypeList = serverTypeList;
	}
	
	

}
