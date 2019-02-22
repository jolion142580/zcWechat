package com.gdyiko.tool;
import org.hibernate.*;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

public class OurOpenSessionInViewFilter extends OpenSessionInViewFilter {
	
	public OurOpenSessionInViewFilter() {
        super.setFlushMode(FlushMode.AUTO);
    }

    protected void closeSession(Session session, SessionFactory sessionFactory) {
        session.flush();

        try {
            session.getTransaction().commit();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block//e.printStackTrace();
        }

        super.closeSession(session, sessionFactory);
    }

}
