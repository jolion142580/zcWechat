package com.gdyiko.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdyiko.base.po.JmFileBsnLink;
import com.gdyiko.base.po.JmFileInfo;
import com.gdyiko.base.service.JmFileBsnLinkService;
import com.gdyiko.base.service.JmFileInfoService;
import com.gdyiko.tool.PrimaryProduce;

@Service("uploadService")
public class UploadService {
	@Autowired
	JmFileBsnLinkService jmFileBsnLinkService;
	
	@Autowired
	JmFileInfoService jmFileInfoService;

	public JmFileBsnLinkService getJmFileBsnLinkService() {
		return jmFileBsnLinkService;
	}

	public void setJmFileBsnLinkService(JmFileBsnLinkService jmFileBsnLinkService) {
		this.jmFileBsnLinkService = jmFileBsnLinkService;
	}

	public JmFileInfoService getJmFileInfoService() {
		return jmFileInfoService;
	}

	public void setJmFileInfoService(JmFileInfoService jmFileInfoService) {
		this.jmFileInfoService = jmFileInfoService;
	}
	@Transactional 
	public void save(JmFileInfo jmFileInfo,JmFileBsnLink jmFileBsnLink){
		//插入上传文件
		//
		
		if (jmFileInfo.getId() == null || "".equals(jmFileInfo.getId())) {
			jmFileInfo.setId(PrimaryProduce.produce());
		}
		String fileid= jmFileInfoService.save(jmFileInfo);
		System.out.println("fileid---------------->"+fileid);
		
		if (jmFileBsnLink.getId() == null || "".equals(jmFileBsnLink.getId())) {
			jmFileBsnLink.setId(PrimaryProduce.produce());
		}
		jmFileInfo.setId(fileid);
		jmFileBsnLink.setJmFileInfo(jmFileInfo);
		
		String linkid = jmFileBsnLinkService.save(jmFileBsnLink);
		System.out.println("linkid---------------->"+linkid);
	}
	//复制路径
	@Transactional 
	public void saveCopyLink(String file_id,JmFileBsnLink jmFileBsnLink){
		JmFileInfo jmFileInfo= jmFileInfoService.findById(file_id);
		jmFileBsnLink.setJmFileInfo(jmFileInfo);
		if (jmFileBsnLink.getId() == null || "".equals(jmFileBsnLink.getId())) {
			jmFileBsnLink.setId(PrimaryProduce.produce());
		}
		jmFileBsnLinkService.save(jmFileBsnLink);
	}
	//删除表关系
	@Transactional 
	public void delete(String linkid ){
		JmFileBsnLink jmFileBsnLink = jmFileBsnLinkService.findById(linkid);
		jmFileBsnLinkService.remove(jmFileBsnLink);
	}
	
}
