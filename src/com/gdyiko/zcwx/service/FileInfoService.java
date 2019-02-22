package com.gdyiko.zcwx.service;

import java.util.List;

import com.gdyiko.zcwx.po.FileInfo;
import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsBaseDic;
import com.gdyiko.tool.service.GenericService;

public interface FileInfoService extends GenericService<FileInfo, String> {


    //上传接口
    public boolean uploadByIdCardOrMaterials(FileInfo fileInfo);

}
