package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.OnlineApplyDao;
import com.gdyiko.zcwx.service.OnlineApplyService;
import com.gdyiko.zcwx.po.OnlineApply;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

import java.util.*;

@Service("onlineApplyService")
public class OnlineApplyServiceImpl extends
        GenericServiceImpl<OnlineApply, String> implements
        OnlineApplyService {
    @Resource(name = "onlineApplyDao")
    OnlineApplyDao onlineApplyDao;

    @Resource(name = "onlineApplyDao")
    @Override
    public void setGenericDao(GenericDao<OnlineApply, String> genericDao) {
        super.setGenericDao(genericDao);
    }


    /**
     * 按时间分组 yyyy-MM-dd : 事务1、事务2
     *
     * @param openid
     * @return
     */
    public List<Map<String, List<Map<String, String>>>> listByOpenIdToList(String openid) {
        List<Map<String, List<Map<String, String>>>> resultList = new LinkedList<Map<String, List<Map<String, String>>>>();

        List<Map<String, String>> list = onlineApplyDao.listByOpenId(openid);
        Set<String> timeSet = new LinkedHashSet<String>();
        for (Map<String, String> map : list) {
            String time = map.get("creattime").substring(0, 10);
            timeSet.add(time);
        }
        for (String time : timeSet) {
            List<Map<String, String>> temp = new LinkedList<Map<String, String>>();
            Map<String, List<Map<String, String>>> resultMap = new HashMap<String, List<Map<String, String>>>();
            for (Map<String, String> map : list) {
                String creattime = map.get("creattime").substring(0, 10);
                if (creattime.equals(time)) {
                    temp.add(map);
                }
            }
            resultMap.put(time, temp);
            resultList.add(resultMap);
        }

        return resultList;
    }

    public List<Map<String, String>> listByOpenId(String openid) {
        List<Map<String, String>> result = new LinkedList<Map<String, String>>();
        List<Map<String, String>> list = onlineApplyDao.listByOpenId(openid);
        for (Map<String, String> map : list) {
            String affairName = map.get("affairName");
            String creattime = map.get("creattime");
            String id = map.get("id");
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("id", id);
            resultMap.put("name", affairName + "<br/>(申请日期：" + creattime + ")");
            result.add(resultMap);
        }
        return result;
    }
}
