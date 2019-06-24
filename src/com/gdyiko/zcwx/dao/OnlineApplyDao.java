package com.gdyiko.zcwx.dao;

import com.gdyiko.zcwx.po.OnlineApply;
import com.gdyiko.tool.dao.GenericDao;

import java.util.List;
import java.util.Map;

public interface OnlineApplyDao extends GenericDao<OnlineApply, String> {

    List<Map<String,String>> listByOpenId(String openid);
}
