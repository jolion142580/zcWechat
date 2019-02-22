package com.gdyiko.base.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.gdyiko.base.po.JmFileBsnLink;
import com.gdyiko.base.po.JmFileInfo;
import com.gdyiko.base.service.impl.UploadService;
import com.gdyiko.tool.action.UploadAction;

@ParentPackage("custom-default")  
@Namespace("/")
@Action(value = "Upload", results = {
		
// 成功
		@Result(name = "success", location = "/cylogined.jsp")
		// 失败
		///@Result(name = "input", location = "/cylogin.jsp",type="redirect"),
		
		///@Result(name = "logout", location = "/cylogin.jsp")

},
interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class FileUploadAction extends UploadAction {
	
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -9150641408121089046L;
	
	@Resource(name="uploadService")
	UploadService uploadService;
	@Resource(name="excelInputService")
	///ExcelInputService excelInputService;

	HttpSession session = null;
	
	
	//业务分类
	private String bsn_type;
	//业务id
	private String bsn_id;
	
	private String remark;
	/*
	//显示名称
	private String displayname;
	//备注
	
	//文件id
	private String file_id; 
	//文件名
	private String name;
	//存放位置
	private String localpath;
	//文件大小
	private String filesize;
	*/
	
	private String creator;
	
	
	public FileUploadAction() {
		// TODO Auto-generated constructor stub
		session = ServletActionContext.getRequest().getSession();
		// /session.getAttribute("user");
		System.out.println("初始化当前录入人员");
		///System.out.println(session.getAttribute("user"));
	
		String userCnName = (String)session.getAttribute("userCnName");
		System.out.println("userCnName---------------->"+userCnName);
		if(userCnName!=null){
			//设置创建者名称
			this.setCreator(userCnName);
		}

	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		super.setRealpath("e:/jmcytz/file/"+getBsn_type()+"/"+this.getBsn_id()+"/");
		String rs = super.execute();
	
		JmFileInfo jmFileInfo = new JmFileInfo();
		jmFileInfo.setFilesize(super.getFileSize());
		jmFileInfo.setLocalpath(super.getRealpath());
		jmFileInfo.setName(super.getFileName());
		jmFileInfo.setCreator(this.getCreator());
		
		JmFileBsnLink jmFileBsnLink =new JmFileBsnLink();
		jmFileBsnLink.setBsn_id(this.getBsn_id());
		jmFileBsnLink.setBsn_type(this.getBsn_type());
		jmFileBsnLink.setCreator(this.getCreator());
		jmFileBsnLink.setDisplayname(super.getFileName());
		jmFileBsnLink.setRemark(this.getRemark());
		
		uploadService.save(jmFileInfo, jmFileBsnLink);
		return rs;
	}

	public String getBsn_type() {
		return bsn_type;
	}

	public void setBsn_type(String bsn_type) {
		this.bsn_type = bsn_type;
	}

	public String getBsn_id() {
		return bsn_id;
	}

	public void setBsn_id(String bsn_id) {
		this.bsn_id = bsn_id;
	}
	

	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public UploadService getUploadService() {
		return uploadService;
	}
	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

	
}
