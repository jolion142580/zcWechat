package com.gdyiko.zcwx.dao;

import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.zcwx.po.BlackWhiteList;

import java.util.List;

public interface BlackWhiteListDao extends GenericDao<BlackWhiteList, String> {

    List<BlackWhiteList> selectByForever(String idCard);

    List<BlackWhiteList> selectByYear(String idCard, String year);

}
