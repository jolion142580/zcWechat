package com.gdyiko.base.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.gdyiko.base.po.DepGeninf;
import com.gdyiko.base.po.RolGeninf;
import com.gdyiko.base.service.DepGeninfService;
import com.gdyiko.base.service.RolGeninfService;
import com.gdyiko.tool.action.MyBaseAction;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.service.MyBaseGenericService;

@ParentPackage("custom-default")  
@Namespace("/")
@Action(value="RolGeninf",results={

		//失败
		@Result(name="fail" ,location = "/")
		
	})
public class RolGeninfAction extends MyBaseAction<RolGeninf, String> {
	
	///modelTitle ="";

	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -9067310222260941951L;
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	@Resource(name="depGeninfService")
	private DepGeninfService depGeninfService;
	
	@Resource(name="rolGeninfService")
	private RolGeninfService rolGeninfService;
	
	private String depid; 
	private String memids;
	
	
	public RolGeninfAction(){
		//super();
		//设置模块名称 
		setModelTitle("职务管理");

	   //设置表格列
	   List<DataGridColumn> columnList = new ArrayList<DataGridColumn>();
	   columnList.add(new DataGridColumn("职务","name","100"));
	   columnList.add(new DataGridColumn("描述","synopsis","200"));
	   columnList.add(new DataGridColumn("排序","siblingorder","80"));
	   columnList.add(new DataGridColumn("创建人","creator","80"));
	   columnList.add(new DataGridColumn("id", "id", "20", true));
	   setColumnJson(columnList);
	   
	   super.setModelListUrl("RolGeninf!showJsonDatagridListByPage.action");
	   super.setModelSaveUrl("RolGeninf!save.action");
	   super.setModelModifyUrl("RolGeninf!modify.action?myid=");
	   super.setModelDelUrl("RolGeninf!remove.action");
		super.setLazy(false);
	}
	
	@Override
	public void setModelTitle(String modelTitle) {
		// TODO Auto-generated method stub
		super.setModelTitle(modelTitle);
	}
	@Resource(name="rolGeninfService")
	@Override
	public void setGenericService(MyBaseGenericService<RolGeninf, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}
	//查询相关职务
	@Override
	public String showJsonDatagridListByPage() throws RuntimeException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		System.out.println(depid);
		DepGeninf depGeninf = depGeninfService.findById(depid);
		System.out.println(depGeninf);
		this.setLazy(true);
		if(depGeninf!=null){
			this.model.setDepGeninf(depGeninf);
		}
		return super.showJsonDatagridListByPage();
	}
	
	@Override
	public String save() {
		// TODO Auto-generated method stub
		DepGeninf depGeninf = depGeninfService.findById(depid);
		this.model.setDepGeninf(depGeninf);
		return super.save();
	}

	public DepGeninfService getDepGeninfService() {
		return depGeninfService;
	}

	public void setDepGeninfService(DepGeninfService depGeninfService) {
		this.depGeninfService = depGeninfService;
	}

	public String getDepid() {
		return depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
	}
	public String delMemgeninfsById() {
		rolGeninfService.delMemgeninfsById(this.model.getRolid(),memids);
		return null;
	}

	public String getMemids() {
		return memids;
	}

	public void setMemids(String memids) {
		this.memids = memids;
	}

	public RolGeninfService getRolGeninfService() {
		return rolGeninfService;
	}

	public void setRolGeninfService(RolGeninfService rolGeninfService) {
		this.rolGeninfService = rolGeninfService;
	}

	
}
