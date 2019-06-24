package com.gdyiko.zcwx.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.FileInfoDao;
import com.gdyiko.zcwx.dao.ServerTypeDao;
import com.gdyiko.zcwx.dao.SsAffairsDao;
import com.gdyiko.zcwx.dao.SsAffairsGuideDao;
import com.gdyiko.zcwx.dao.SsBaseDicDao;
import com.gdyiko.zcwx.po.FileInfo;
import com.gdyiko.zcwx.po.ServerType;
import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsAffairs;
import com.gdyiko.zcwx.po.SsBaseDic;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;

import java.util.List;
import java.util.Map;


@Repository("fileInfoDao")
public class FileInfoDaoImpl extends GenericDaoImpl<FileInfo, String> implements FileInfoDao {

    @Override
    public Class<FileInfo> getEntityClass() {
        // TODO Auto-generated method stub
        return FileInfo.class;
    }

    public List<Map<String, String>> informationToList(String openid) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select c.affairname as affairname,")
                .append(" a.id as id,")
                .append(" a.onlineApplyId as onlineApplyId,")
                .append(" a.localpath as localpath ,")
                .append(" a.remark as remark,")
                .append(" b.creattime as creattime")
                .append(" from file_info as a")
                .append(" left join onlineApply as b on b.id=a.onlineApplyId")
                .append(" left join ss_affairs as c on c.affairid = b.affairid")
                .append(" WHERE a.filename not LIKE '%.pdf'")
                .append(" and a.openid ='").append(openid).append("'")
                .append(" and affairname is not null")
                .append(" and onlineApplyId is not null")
                .append(" ORDER BY b.creattime desc");
        System.out.println("PC端查询上传文件记录剔除身份证正反面-----：" + sql.toString());
        List<Map<String, String>> list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createSQLQuery(sql.toString())
                .addScalar("affairname", Hibernate.STRING)
                .addScalar("id", Hibernate.STRING)
                .addScalar("onlineApplyId", Hibernate.STRING)
                .addScalar("localpath", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("creattime", Hibernate.STRING)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }
	/*	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ServerTypeDao serverTypeDao = (ServerTypeDao)context.getBean("serverTypeDao");
		ServerType serverType=serverTypeDao.findById("1");
		//List<JmCydepartmentWorkerInfo> list = jmCydepartmentWorkerInfoService.getListByCydep("144108824432446765292",  0,  20);
		System.out.println(serverType.getName());
	}*/

}
