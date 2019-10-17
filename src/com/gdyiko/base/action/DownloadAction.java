package com.gdyiko.base.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.util.ServletContextAware;

import com.gdyiko.base.po.JmFileBsnLink;
import com.gdyiko.base.po.JmFileInfo;
import com.gdyiko.base.service.JmFileInfoService;
import com.gdyiko.base.service.PropertieService;
import com.opensymphony.xwork2.ActionSupport;

///@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "download", results = {
// 成功
@Result(name = "success", type = "stream", params = { "contentType",
		"application/octet-stream;charset=ISO8859-1", "inputName", "inStream",
		"contentDisposition", "attachment;filename=\"${displayName}\"",
		"bufferSize", "4096" })
,@Result(name = "doc", location = "/oa/openword.jsp")
})
public class DownloadAction extends ActionSupport implements
		ServletContextAware {
	private static final long serialVersionUID = 1L;

	private ServletContext context;

	private String filename;

	private String mimeType;

	private InputStream inStream;

	private String fileid;
	
	private String displayName;

	private String localpath;
	@Resource(name="jmFileInfoService")
	JmFileInfoService jmFileInfoService;

	@Resource(name="propertieService")
	PropertieService propertieService;
	
	@Override
	public String execute() throws Exception {
		// /JmFileInfo jmFileInfo= jmFileInfoService.findById(fileid);
		// /this.setLocalpath(jmFileInfo.getLocalpath());
		// /this.setFilename(jmFileInfo.getName());
		// System.out.println(localpath + filename);
		// /mimeType = context.getMimeType(jmFileInfo.getName());
		return SUCCESS;
	}

	public InputStream getInStream() throws FileNotFoundException {
		JmFileInfo jmFileInfo = jmFileInfoService.findById(fileid);
		this.setLocalpath(jmFileInfo.getLocalpath());
		this.setFilename(jmFileInfo.getName());
		
		if(jmFileInfo.getJmFileBsnLinks().isEmpty()){
			this.setDisplayName(jmFileInfo.getName());
		}else{
			JmFileBsnLink jmFileBsnLink= jmFileInfo.getJmFileBsnLinks().iterator().next();
			this.setDisplayName(jmFileBsnLink.getDisplayname());
		}
		
		System.out.println(jmFileInfo.getLocalpath() + jmFileInfo.getName());
		System.out.println("-------------------->"+ propertieService.getPropertie("filepath")+jmFileInfo.getLocalpath() + jmFileInfo.getName());
		mimeType = context.getMimeType(jmFileInfo.getName());
		System.out.println(mimeType);
		// //File file = new File("D:\\jmcytz\\file\\" + filename);
		System.out.println("-------------------->"+ propertieService.getPropertie("filepath")+jmFileInfo.getLocalpath() + jmFileInfo.getName());
		File file = new File( propertieService.getPropertie("filepath")+jmFileInfo.getLocalpath() + jmFileInfo.getName());
		System.out.println(file);
		inStream = new FileInputStream(file);
		System.out.println(inStream);
		// /inStream = context.getResourceAsStream("D:\\jmcytz\\file\\" +
		// filename);

		if (inStream == null) {
			inStream = new ByteArrayInputStream(
					"Sorry,File not found !".getBytes());
		}
		return inStream;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setFilename(String filename) {
		/*
		 * try { this.filename = new
		 * String(filename.getBytes("ISO8859-1"),"UTF-8"); } catch
		 * (UnsupportedEncodingException e) { }
		 */
		this.filename = filename;
	}

	public String getFilename() {

		try {
			return new String(filename.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			return this.filename;
		}
		// //return this.filename;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public JmFileInfoService getJmFileInfoService() {
		return jmFileInfoService;
	}

	public void setJmFileInfoService(JmFileInfoService jmFileInfoService) {
		this.jmFileInfoService = jmFileInfoService;
	}

	public String getLocalpath() {
		return localpath;
	}
	public String doc(){
		
		return "doc";
	}
	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}

	public PropertieService getPropertieService() {
		return propertieService;
	}

	public void setPropertieService(PropertieService propertieService) {
		this.propertieService = propertieService;
	}

	public String getDisplayName() {
		///return displayName;
		try {
			return new String(displayName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			return this.displayName;
		}
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	
}
