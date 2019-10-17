package com.gdyiko.zcwx.service;

import com.gdyiko.zcwx.po.OnlineApply;
import com.gdyiko.tool.service.GenericService;

import java.util.List;
import java.util.Map;

public interface OnlineApplyService extends GenericService<OnlineApply, String> {


    List<Map<String,List<Map<String,String>>>> listByOpenIdToList(String openid);

    List<Map<String, String>>  listByOpenId(String openid);
}
