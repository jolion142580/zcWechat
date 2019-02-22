package com.gdyiko.zcwx.dao;



import com.gdyiko.zcwx.po.Complaint;

import java.util.List;

import com.gdyiko.tool.dao.GenericDao;

public interface ComplaintDao extends GenericDao<Complaint, String> {
  List<Complaint> findByConditionShow(String openId,String complaintShow);
}
