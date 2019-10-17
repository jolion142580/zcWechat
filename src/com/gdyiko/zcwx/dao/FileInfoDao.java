package com.gdyiko.zcwx.dao;


import com.gdyiko.zcwx.po.FileInfo;
import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsAffairs;
import com.gdyiko.tool.dao.GenericDao;

import java.util.List;
import java.util.Map;

public interface FileInfoDao extends GenericDao<FileInfo, String> {

    List<Map<String,String>> informationToList(String openid);

}
