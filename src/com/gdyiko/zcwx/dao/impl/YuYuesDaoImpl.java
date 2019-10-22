package com.gdyiko.zcwx.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.YuYuesDao;
import com.gdyiko.zcwx.po.YuYues;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;

import java.util.List;


@Repository("yuYuesDao")
public class YuYuesDaoImpl extends GenericDaoImpl<YuYues, String> implements YuYuesDao {

    @Override
    public Class<YuYues> getEntityClass() {
        // TODO Auto-generated method stub
        return YuYues.class;
    }

    public List<YuYues> signNot(String idCard, String date, String year) {
        System.out.printf("com.gdyiko.zcwx.dao.impl.YuYuesDaoImpl line[24] output: -=> idCard=%s, date=%s, year=%s\n", idCard, date, year);
        List<YuYues> list = null;
        year += "-01-01";
        try {
            String sql = "from YuYues as y where idcard = ? and ydate <= ? and ydate >= ? and (y.state is null or y.state = '0' or y.state = '4')";
//            String sql = "from YuYues as y where idcard = ? and ydate <= ? and ydate >= ? and (y.state is null or y.state = '4')";
            list = this.getHibernateTemplate().find(sql, idCard, date, year);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<YuYues> listBefore(String openid, String date) {
        List<YuYues> list = null;

        try {
            //查询今天之前 | 预约时间没到已经取消
            String sql = "from YuYues as y where y.openid = ? and ( y.ydate < ? or state = '2' ) order by ydate desc ";
            list = this.getHibernateTemplate().find(sql, openid, date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public List<YuYues> listLater(String openid, String date) {
        List<YuYues> list = null;

        try {
            // 查询今天开始并未签到的预约
            String sql = "from YuYues as y where y.openid = ? and y.ydate >= ? AND state <> '2' order by ydate asc ";
            list = this.getHibernateTemplate().find(sql, openid, date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /*	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		YuYuesDao yuYuesDao = (YuYuesDao)context.getBean("yuYuesDao");
		YuYues yuYues=yuYuesDao.findById("10002075");
		//List<JmCydepartmentWorkerInfo> list = jmCydepartmentWorkerInfoService.getListByCydep("144108824432446765292",  0,  20);
		System.out.println(yuYues.getName());
	}*/

    public YuYues selectByNoAndIdCard(String no, String idcard) {
        YuYues yuYues = null;
        List<YuYues> list = null;

        try {
            String sql = "from YuYues as y where y.no = ? and y.idcard >= ?  ";
            list = this.getHibernateTemplate().find(sql, no, idcard);
            yuYues = list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return yuYues;
    }
}
