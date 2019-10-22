package com.gdyiko.tool;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class onlineListener implements HttpSessionListener,
        HttpSessionAttributeListener {

    ArrayList list = new ArrayList();

    // 新建一个session时触发此操作
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("in sessionCreated() --> " + DateUtil.getDateStr(new Date(), DateUtil.DATE_PATTERN_YYYY_MM_DDHHMMSS));
		/*ActionContext context = ActionContext.getContext();
		Map application = context.getApplication();
		Map<String, String> smap = new HashMap<String, String>();
		if (application.get("map") == null) {
			smap.put(String.valueOf(temp.getId()), temp.getUserName());
		} else {
			smap = (Map<String, String>) application.get("map");
			smap.put(String.valueOf(temp.getId()), temp.getUserName());
		}
		application.put("map", smap);
		smap = (Map<String, String>) application.get("map");
		System.out.println(smap.size());*/
    }

    // 销毁一个session时触发此操作
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("in sessionDestroyed");
        HttpSession session = se.getSession();
        ///OaUsers temp = (OaUsers)session.getAttribute("cyuser");
        ///System.out.println(temp.getId());
		/*HttpSession session = se.getSession();
		ServletContext application = session.getServletContext();
		Map<String, String> smap = (Map<String, String>) application
				.getAttribute("map");
		System.out.println(smap.size());
		smap.remove(String.valueOf(se.getSession().getAttribute("userId")));
		application.setAttribute("map", smap);
		smap = (Map<String, String>) application.getAttribute("map");
		System.out.println(smap.size());
		se.getSession().removeAttribute("username");
		se.getSession().removeAttribute("userCnName");
		se.getSession().removeAttribute("userId");
		se.getSession().removeAttribute("user");
		se.getSession().removeAttribute("cyuser");*/
    }

    // 在session中添加对象时触发此操作，在list中添加一个对象
    public void attributeAdded(HttpSessionBindingEvent sbe) {

    }

    // 修改、删除session中添加对象时触发此操作
    public void attributeRemoved(HttpSessionBindingEvent arg0) {

    }

    public void attributeReplaced(HttpSessionBindingEvent arg0) {

    }
}
