package com.gdyiko.zcwx.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.gdyiko.zcwx.po.FileInfo;
import com.gdyiko.tool.service.GenericService;
import net.sf.json.JSONObject;

public interface FileInfoService extends GenericService<FileInfo, String> {


    //上传接口
    JSONObject uploadByIdCardOrMaterials(File file, FileInfo fileInfo);

    List< Map<String,List<Map<String, List<Map<String, String>>>>>> informationToList(String openid);

    List<Map<String,List<FileInfo>>> listByOnlineApply(String onlineApplyId);

    String saveImg(File file,FileInfo fileInfo);
}
