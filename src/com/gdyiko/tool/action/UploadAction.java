package com.gdyiko.tool.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport{
    
    /** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = 987383000618742153L;
	private File file; //上传的文件
    private String fileName; //文件名称
    private String fileContentType; //文件类型
    private long fileSize ;
    //存放文件夹路径
    private String realpath; 

    public String execute() throws Exception {
        ///String realpath = ServletActionContext.getServletContext().getRealPath("/images");
    	///String realpath = "d:/jmcytz/file/";
    	
        //D:\apache-tomcat-6.0.18\webapps\struts2_upload\images
        System.out.println("realpath: "+realpath);
        if (file != null) {
        	
        	System.out.println("上传文件名称:"+getFileName());
            File savefile = new File(new File(this.getRealpath()),getFileName());
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(file, savefile);
            this.setFileSize(file.length());
            
            ActionContext.getContext().put("message", "文件上传成功");
            
        }
        return null;
    }

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getRealpath() {
		return realpath;
	}

	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}


    
}