package com.gdyiko.zcwx.dao.impl;

import com.gdyiko.tool.dao.impl.GenericDaoImpl;
import com.gdyiko.zcwx.dao.BlackWhiteListDao;
import com.gdyiko.zcwx.po.BlackWhiteList;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("blackWhiteListDao")
public class BlackWhiteListDaoImpl extends GenericDaoImpl<BlackWhiteList, String> implements BlackWhiteListDao {

    @Override
    public Class<BlackWhiteList> getEntityClass() {
        return BlackWhiteList.class;
    }

    public List<BlackWhiteList> selectByForever(String idCard) {
        List<BlackWhiteList> list = null;
        try {
            String sql = "from BlackWhiteList  as b  where b.idCard = ? and b.forever ='1' order by b.creattime desc ";
            list = this.getHibernateTemplate().find(sql, idCard);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<BlackWhiteList> selectByYear(String idCard, String year) {
        List<BlackWhiteList> list = null;
        try {
            String sql = "from BlackWhiteList  as b  where b.idCard = ? and b.time = ? order by b.creattime desc ";
            list = this.getHibernateTemplate().find(sql, idCard, year);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
}
