package com.gdyiko.zcwx.dao;

import com.gdyiko.zcwx.po.YuYues;
import com.gdyiko.tool.dao.GenericDao;

import java.util.List;

public interface YuYuesDao extends GenericDao<YuYues, String> {

    List<YuYues> signNot(String idCard, String date, String year);

    List<YuYues> listBefore(String openid, String date);

    List<YuYues> listLater(String openid, String date);


    YuYues selectByNoAndIdCard(String no, String idcard);
}
