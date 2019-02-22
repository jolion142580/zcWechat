package com.gdyiko.base.action;



import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.base.po.MemGeninf;
import com.gdyiko.base.po.MenuInfo;
import com.gdyiko.base.po.RolGeninf;
import com.gdyiko.base.po.RoleInfo;
import com.gdyiko.base.service.MemGeninfService;
import com.gdyiko.base.service.RolGeninfService;
import com.gdyiko.tool.MD5Util;
import com.gdyiko.tool.action.MyBaseAction;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.easyui.DataGridJson;
import com.gdyiko.tool.service.MyBaseGenericService;

@ParentPackage("custom-default") 
@Namespace("/")
@Action(value="MemGeninf",results={
	    //成功
		@Result(name="success" ,location = "/projectweb/mem_manager.jsp"),
		//失败
		@Result(name="fail" ,location = "/")
		
	})
public class MemGeninfAction   extends MyBaseAction<MemGeninf, String> {
	
	//String loginid ="";
	String rolid =null;
	String mymemid = null;
	String memidList = null;
	
	String roleinfoids = null;
	
	RolGeninf rolGeninf ;
	
	MemGeninf memGeninf ;
	
	
	
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -1478973090188121979L;
	
	@Resource(name="rolGeninfService")
	RolGeninfService  rolGeninfService ;
	
	
	@Resource(name="memGeninfService")
	MemGeninfService  memGeninfService ;
	
	
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	
	public MemGeninfAction() {
		// TODO Auto-generated constructor stub
		setModelTitle("职务管理");

		   //设置表格列
		   List<DataGridColumn> columnList = new ArrayList<DataGridColumn>();
		   columnList.add(new DataGridColumn("用户名","username","250"));
		   columnList.add(new DataGridColumn("登陆账号","loginid","150"));
		   columnList.add(new DataGridColumn("电话","phone","100"));
		   columnList.add(new DataGridColumn("邮箱","email","80"));
		   columnList.add(new DataGridColumn("描述","synopsis","200"));
		   columnList.add(new DataGridColumn("id", "id", "20", true));
		   setColumnJson(columnList);
		   
		   super.setModelListUrl("MemGeninf!showJsonDatagridListByPage.action");
		   super.setModelSaveUrl("MemGeninf!save.action");
		   super.setModelModifyUrl("MemGeninf!modify.action?myid=");
		   super.setModelDelUrl("MemGeninf!remove.action");
		   super.setLazy(true);
	}
	@Resource(name="memGeninfService")
	@Override
	public void setGenericService(MyBaseGenericService<MemGeninf, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}

	public RolGeninfService getRolGeninfService() {
		return rolGeninfService;
	}

	public void setRolGeninfService(RolGeninfService rolGeninfService) {
		this.rolGeninfService = rolGeninfService;
	}
	/*返回列表*/
	@Override
	public String showJsonDatagridListByPage() throws RuntimeException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		if(rolid==null){
	    	System.out.println("info:查全部");
	    	return super.showJsonDatagridListByPage();
	    }
	    RolGeninf rolGeninf = rolGeninfService.findById(rolid);
	    Set<RolGeninf> rolGeninfs  = new HashSet<RolGeninf>();
	    if(rolGeninf!=null){
	    	System.out.println("info:正常查询");
		    rolGeninfs.add(rolGeninf);
	    }else{
	    	//错误的时候
	    	System.out.println("info:没有职务信息时候");
	    	RolGeninf rol = new RolGeninf();
	    	rol.setRolid("0");
	    	 rolGeninfs.add(rol);
	    	
	    }
	    this.model.setRolGenInfs(rolGeninfs);
		return super.showJsonDatagridListByPage();
	}

	
	
	public String showJsonDatagridNotRoleMem() throws RuntimeException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		int page = this.getPage();
		int rows = this.getRows();
		System.out.println("查询数据量：page-->" + page + "rows-->" + rows);
		memGeninfService.findByPage(page, rows);
		List<MemGeninf> list =memGeninfService.findByPageNotRoleMem(page, rows,rolid);
		int total = memGeninfService.getTotalRows();
		System.out.println("查询数据量：" + list.size());
		DataGridJson dgjo = new DataGridJson(list, total);
		JsonConfig jsonConfig = getJsonConfig(model);
		JSONObject jsonObject = JSONObject.fromObject(dgjo, jsonConfig);
		Struts2Utils.renderText(jsonObject.toString());
		return null;
	}
	
	public String getRoleInfosById(){
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%5---->"+mymemid);
		MemGeninf memGeninf = (MemGeninf) memGeninfService.findById(mymemid);
		
		Set<RoleInfo> roleinfos =memGeninf.getRoleinfos();
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%5---->"+roleinfos.size());
		Iterator<RoleInfo> iterator = roleinfos.iterator();
		String ids= "";
		while (iterator.hasNext()) {
			RoleInfo roleInfo  = iterator.next();
			String tempid = roleInfo.getId();
			ids = ids+tempid+";";
		}
		Struts2Utils.renderText(ids);
		return null;
	
	}
	
	public String setRolMem(){
		System.out.println("memidList------------->"+memidList);
		System.out.println("rolid------------->"+rolid);
		rolGeninf = rolGeninfService.findById(rolid);
		String[] memid = memidList.split(";");
		for(int i=0;i<memid.length;i++){
			//加入人员 
			System.out.println("memid------>"+memid[i]);
			memGeninf = memGeninfService.findById(memid[i]);
			if(memGeninf!=null){
				rolGeninf.getMemGeninfs().add(memGeninf);
			}
		}
		rolGeninfService.save(rolGeninf);
		System.out.println(getResultJson(FLAG_SUCCESS));
		Struts2Utils.renderText(getResultJson(FLAG_SUCCESS));
		return null;
	}
	//新增
	@Override
	public String save() {
		// TODO Auto-generated method stub
		String md5pw = MD5Util.MD5( this.model.getPassword().trim());
		this.model.setPassword(md5pw);
		return super.save();
	}
	 //修改
	@Override
	public String modify() {
		
		// TODO Auto-generated method stub
		//判断是否加密后
		String password  = this.model.getPassword().trim();
		MemGeninf memGeninf = memGeninfService.findById(this.myid);
		//如果不同就重新加密
		if(password.equals(memGeninf.getPassword())==false){
			this.model.setPassword( MD5Util.MD5(password));
		}
		
		return super.modify();
	}
	public String getRolid() {
		return rolid;
	}
	

	public void setRolid(String rolid) {
		this.rolid = rolid;
	}
	public String getMemidList() {
		return memidList;
	}
	public void setMemidList(String memidList) {
		this.memidList = memidList;
	}
	public MemGeninfService getMemGeninfService() {
		return memGeninfService;
	}
	public void setMemGeninfService(MemGeninfService memGeninfService) {
		this.memGeninfService = memGeninfService;
	}
	public String getMymemid() {
		return mymemid;
	}
	public void setMymemid(String mymemid) {
		this.mymemid = mymemid;
	}

	public String saveRoleInfos(){
		memGeninfService.saveRoleInfos(this.model.getMemid(), roleinfoids);
		return null;
	}
	public String getRoleinfoids() {
		return roleinfoids;
	}
	public void setRoleinfoids(String roleinfoids) {
		this.roleinfoids = roleinfoids;
	}
	public RolGeninf getRolGeninf() {
		return rolGeninf;
	}
	public void setRolGeninf(RolGeninf rolGeninf) {
		this.rolGeninf = rolGeninf;
	}
	public MemGeninf getMemGeninf() {
		return memGeninf;
	}
	public void setMemGeninf(MemGeninf memGeninf) {
		this.memGeninf = memGeninf;
	}
	
}
