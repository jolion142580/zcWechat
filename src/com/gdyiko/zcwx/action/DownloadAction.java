package com.gdyiko.zcwx.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.zcwx.weixinUtils.HttpContent;
import com.opensymphony.xwork2.ActionSupport;

import example.server.FileServer;

//@ParentPackage("custom-default")
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

	private String queryName;
	private String queryVal;

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

	public InputStream getInStream() throws IOException, JSONException {

		
		/*String url = propertieService.getPropertie("configurePath")+"/File!getFileInfo?id="+queryVal;
		 System.out.println(url);
		 HttpContent httpContent = new HttpContent();
		 String content  = httpContent.getHttpContent(url, "", "", "GET");
		 JSONObject jo = new JSONObject(content);
		 System.out.println("-=-="+content);*/
		
		propertieService=new PropertieService();
		String subjectURL = propertieService.getPropertie("configurePath")+"/download?"+queryName+"="+queryVal;
		 URL httpurl=new URL(subjectURL);
	     HttpURLConnection httpConn=(HttpURLConnection)httpurl.openConnection();
	        httpConn.setDoOutput(true);// 使用 URL 连接进行输出
	        httpConn.setDoInput(true);// 使用 URL 连接进行输入
	        httpConn.setUseCaches(false);// 忽略缓存
	        httpConn.setRequestMethod("GET");// 设置URL请求方法
	        //可设置请求头
	        httpConn.setRequestProperty("Content-Type", "application/octet-stream");
	        httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
	        httpConn.setRequestProperty("Charset", "UTF-8");
	        //可设置请求头

	       return httpConn.getInputStream();
		
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

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}


	

	
}
