package com.gdyiko.base.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.gdyiko.base.po.JmFileBsnLink;
import com.gdyiko.base.po.JmFileInfo;
import com.gdyiko.base.service.JmFileInfoService;
import com.gdyiko.base.service.PropertieService;
import com.gdyiko.base.service.impl.UploadService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

///@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "Plupload", results = {
// 成功
@Result(name = "success", location = "/projectweb/upload.jsp"),
@Result(name = "input", location = "/projectweb/input.jsp"),
@Result(name = "error", location = "/projectweb/error.jsp")

// 失败
})
public class PluploadAction extends ActionSupport {
	
	@Resource(name="uploadService")
	UploadService uploadService;
	
	
	@Resource(name="jmFileInfoService")
	JmFileInfoService jmFileInfoService;
	
	@Resource(name="propertieService")
	PropertieService propertieService;


	///JmCydepartmentInfo cyuser = null;
	//业务分类
	private String bsn_type;
	//业务id
	private String bsn_id;
	
	HttpSession session = null;

	private static final int BUFFER_SIZE = 2 * 1024;
	private Logger logger = Logger.getLogger(PluploadAction.class);
	public  File file;
	private String name=""; // plupload上传文件的临时文件名 uuid.文件后缀
	private String uploadFileName;
	private String uploadContentType;
	private int chunk=0;
	private int chunks=1;
	private String creator;
	private String fileid="0";
	File finalFile;
	
	public PluploadAction() {
		// TODO Auto-generated constructor stub

				session = ServletActionContext.getRequest().getSession();
				// /session.getAttribute("user");
				System.out.println("初始化当前录入人员");
				String userCnName = (String)session.getAttribute("userCnName");
				System.out.println("userCnName---------------->"+userCnName);
				if(userCnName!=null){
					//设置创建者名称
					this.setCreator(userCnName);
				}
	}

	public JmFileInfoService getJmFileInfoService() {
		return jmFileInfoService;
	}


	public void setJmFileInfoService(JmFileInfoService jmFileInfoService) {
		System.out.println("setJmFileInfoService");
		this.jmFileInfoService = jmFileInfoService;
	}

	public UploadService getUploadService() {
		return uploadService;
	}

	public void setUploadService(UploadService uploadService) {
		System.out.println("setUploadService");
		this.uploadService = uploadService;
	}

	public PropertieService getPropertieService() {
		return propertieService;
	}

	public void setPropertieService(PropertieService propertieService) {
		System.out.println("setPropertieService");
		this.propertieService = propertieService;
	}

	private void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			if (dst.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(dst, true), BUFFER_SIZE); // plupload
																								// 配置了chunk的时候新上传的文件appand到文件末尾
			} else {
				out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			}
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);

			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//上传weboffice组件
	public String uploadWebOffice() {
		System.out.println("check1--------------------------------------------------------------");
		JmFileInfo jmFileInfo = jmFileInfoService.findById(fileid);
		String localpath =jmFileInfo.getLocalpath();
		//ServletActionContext.getServletContext().getRealPath("\\tmp") 
		String dstPath = propertieService.getPropertie("filepath")+localpath; // 保存目录可以自己配置
		System.out.println(dstPath);
		String dstFullPath ="";
		File dir = new File(dstPath);
		//创建文件夹
		
		if (!dir.exists()) dir.mkdirs();
		dstFullPath = dstPath+this.getUploadFileName()+"chunks_"+chunks+"_"+chunk;
		File dstFile = new File(dstFullPath);																				// 或者定义变量自行配置
		System.out.println(this.file);
		System.out.println(dstPath);
		System.out.println(dstFullPath);
		copy(this.file, dstFile);
		return SUCCESS;
	}
	
	public String aaaa(){
		System.out.println("check!!!!1111111111111111111111111111111111111111111111111111");
		return SUCCESS;
	}
	
	/*
	
	///System.out.println(uploadFileName + " " + uploadContentType + " "+
	////chunk + " " + chunks);
	 
		if (chunk ==chunks   - 1) {
			// 一个完整的文件上传完成
			System.out.println("合成数据！！");
			for(int i =0 ;i<chunks ;i++){
				System.out.println(dstPath+this.getName()+"chunks_"+chunks+"_"+i);
				File tempFile = new File(dstPath+this.getName()+"chunks_"+chunks+"_"+i);
				 InputStream in = new FileInputStream(tempFile);
				  finalFile = new File(dstPath+this.getName());
				 appendFile( in,finalFile);
				 System.out.println("合成:"+dstPath+this.getName()+"chunks_"+chunks+"_"+i);
				 tempFile.delete();
			}
			
		}*/
	
	
	public String upload() throws Exception {
		//ServletActionContext.getServletContext().getRealPath("\\tmp") 
		String  localpath = "/"+getBsn_type()+"/"+this.getBsn_id()+"/";  
		String dstPath = propertieService.getPropertie("filepath")+localpath; // 保存目录可以自己配置
		String dstFullPath ="";
		File dir = new File(dstPath);
		//创建文件夹
		if (!dir.exists()) dir.mkdirs();
		dstFullPath = dstPath+this.getName()+"chunks_"+chunks+"_"+chunk;
		File dstFile = new File(dstFullPath);																				// 或者定义变量自行配置
		System.out.println(this.file);
		System.out.println(dstPath);
		System.out.println(dstFullPath);
		copy(this.file, dstFile);
		 System.out.println(uploadFileName + " " + uploadContentType + " "+
		 chunk + " " + chunks);
		
		if (chunk ==chunks   - 1) {
			// 一个完整的文件上传完成
			System.out.println("合成数据！！");
			for(int i =0 ;i<chunks ;i++){
				System.out.println(dstPath+this.getName()+"chunks_"+chunks+"_"+i);
				File tempFile = new File(dstPath+this.getName()+"chunks_"+chunks+"_"+i);
				 InputStream in = new FileInputStream(tempFile);
				  finalFile = new File(dstPath+this.getName());
				 appendFile( in,finalFile);
				 System.out.println("合成:"+dstPath+this.getName()+"chunks_"+chunks+"_"+i);
				 tempFile.delete();
			}
			////appendFile(InputStream in, File destFile);
			JmFileInfo jmFileInfo = new JmFileInfo();
			jmFileInfo.setFilesize(finalFile.length());
			jmFileInfo.setLocalpath(localpath);
			jmFileInfo.setName(this.getName());
			jmFileInfo.setCreator(this.getCreator());
			
			JmFileBsnLink jmFileBsnLink =new JmFileBsnLink();
			jmFileBsnLink.setBsn_id(this.getBsn_id());
			jmFileBsnLink.setBsn_type(this.getBsn_type());
			jmFileBsnLink.setCreator(this.getCreator());
			jmFileBsnLink.setDisplayname(this.getName());
			jmFileBsnLink.setRemark("批量上传");
			
			uploadService.save(jmFileInfo, jmFileBsnLink);
		}
		
		
		return SUCCESS;
	}
	public String uploadb() throws IOException {
		
		 System.out.println("上传测试！！1" );
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		 System.out.println("上传测试！！2" );
		 String fileName = "";
         Integer chunk = 0, chunks = 0;
          
         //检查文件目录，不存在则创建
         String relativePath = "file/";
         String realPath = "d:/jmcytz/";
         File folder = new File(realPath + relativePath);
         if (!folder.exists()) {
             folder.mkdirs();
         }
         System.out.println("上传测试！！3" );
         DiskFileItemFactory diskFactory = new DiskFileItemFactory();
         // threshold 极限、临界值，即硬盘缓存 1M  
         diskFactory.setSizeThreshold(4 * 1024);
         System.out.println("上传测试！！4" );
         ServletFileUpload upload = new ServletFileUpload(diskFactory);
         System.out.println("上传测试！！5" );
         // 设置允许上传的最大文件大小（单位MB）
         upload.setSizeMax(1024 * 1048576);
         upload.setHeaderEncoding("UTF-8");
         System.out.println("上传测试！！" );
         try {
             List<FileItem> fileList = upload.parseRequest(request);
             Iterator<FileItem> it = fileList.iterator();
             while (it.hasNext()) {
                 FileItem item = it.next();
                 String name = item.getFieldName();
                 InputStream input = item.getInputStream();
                 if("name".equals(name)){
                     fileName = Streams.asString(input);  
                     continue;
                 }
                 if("chunk".equals(name)){
                     chunk = Integer.valueOf(Streams.asString(input));  
                     continue;  
                 }
                 if("chunks".equals(name)){
                     chunks = Integer.valueOf(Streams.asString(input));  
                     continue;  
                 }
                 // 处理上传文件内容
                 if (!item.isFormField()) {
                     //目标文件
                     File destFile = new File(folder, fileName);
                     //文件已存在删除旧文件（上传了同名的文件） 
                     if (chunk == 0 && destFile.exists()) {  
                         destFile.delete();  
                         destFile = new File(folder, fileName);
                     }
                     System.out.println("测试！");
                     //合成文件
                     appendFile(input, destFile);  
                     if (chunk == chunks - 1) {  
                         logger.info("上传完成");
                         System.out.println("上传完成");
                     }else {
                         logger.info("还剩["+(chunks-1-chunk)+"]个块文件");
                         System.out.println("还剩["+(chunks-1-chunk)+"]个块文件");
                     }
                 }
             }
         } catch (FileUploadException ex) {
            logger.warn("上传文件失败：" + ex.getMessage());
            System.out.println("上传文件失败：" + ex.getMessage());
            /// return;
         }
		
		
		return SUCCESS;
	}
	
	public String uploadFinish() {
		String dstPath = ServletActionContext.getServletContext().getRealPath("\\tmp");

		HttpServletRequest request = ServletActionContext.getRequest();

		int count = Integer.parseInt(request.getParameter("uploader_count"));
		for (int i = 0; i < count; i++) {
			uploadFileName = request.getParameter("uploader_" + i + "_name");
			name = request.getParameter("uploader_" + i + "_tmpname");
			System.out.println(uploadFileName + " " + name);
			try {

				// 对已经上传成功的临时文件进行操作
			} catch (Exception e) {

			}
		}
		return SUCCESS;
	}


	 private void appendFile(InputStream in, File destFile) {
	        OutputStream out = null;
	        try {
	            // plupload 配置了chunk的时候新上传的文件append到文件末尾
	            if (destFile.exists()) {
	                out = new BufferedOutputStream(new FileOutputStream(destFile, true), BUFFER_SIZE); 
	            } else {
	                out = new BufferedOutputStream(new FileOutputStream(destFile),BUFFER_SIZE);
	            }
	           ////out.
	            in = new BufferedInputStream(in, BUFFER_SIZE);
	             
	            int len = 0;
	            byte[] buffer = new byte[BUFFER_SIZE];          
	            while ((len = in.read(buffer)) > 0) {
	                out.write(buffer, 0, len);
	            }
	        } catch (Exception e) {
	            logger.error(e.getMessage());
	        } finally {     
	            try {
	                if (null != in) {
	                    in.close();
	                }
	                if(null != out){
	                    out.close();
	                }
	            } catch (IOException e) {
	                logger.error(e.getMessage());
	            }
	        }
	    }


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		System.out.println("setFile");
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("setName");
		this.name = name;
	}



	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		System.out.println("setFileid");
		this.fileid = fileid;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		System.out.println("setUploadFileName");
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		System.out.println("setUploadContentType");
		this.uploadContentType = uploadContentType;
	}
	
	public int getChunk() {
		return chunk;
	}

	public void setChunk(int chunk) {
		System.out.println("setChunk");
		this.chunk = chunk;
	}

	public int getChunks() {
		return chunks;
	}

	public void setChunks(int chunks) {
		System.out.println("setChunks");
		this.chunks = chunks;
	}
	

	public String getBsn_type() {
		System.out.println("getBsn_type");
		return bsn_type;
	}

	public void setBsn_type(String bsn_type) {
		System.out.println("setBsn_type");
		this.bsn_type = bsn_type;
	}

	public String getBsn_id() {
		return bsn_id;
	}

	public void setBsn_id(String bsn_id) {
		System.out.println("setBsn_id");
		this.bsn_id = bsn_id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		System.out.println("setCreator");
		this.creator = creator;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}
	

}