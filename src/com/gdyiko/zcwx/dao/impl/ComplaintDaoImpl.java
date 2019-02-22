package com.gdyiko.zcwx.dao.impl;

import java.util.List;

import com.gdyiko.zcwx.po.SsUserInfo;
import com.google.common.collect.Lists;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.ComplaintDao;
import com.gdyiko.zcwx.po.Complaint;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;


@Repository("complaintDao")
public class ComplaintDaoImpl extends GenericDaoImpl<Complaint, String> implements ComplaintDao {

    @Override
    public Class<Complaint> getEntityClass() {
        // TODO Auto-generated method stub
        return Complaint.class;
    }


    public List<Complaint> findByConditionShow(String openId, String complaintShow) {
        List<Complaint> list = null;
        try {
            /*String sql = "from Complaint c left join SsUserInfo s on c.open_Id = s.id where  c.complaint_Show = ?";*/
            String sql = "from Complaint c  where c.open_Id =? and c.complaint_Show = ? order by c.complaintTime desc";
            list = this.getHibernateTemplate().find(sql, openId, complaintShow);
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }


}
