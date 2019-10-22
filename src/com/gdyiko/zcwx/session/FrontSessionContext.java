package com.gdyiko.zcwx.session;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

//用于获取前端Session
public class FrontSessionContext {

    private static FrontSessionContext instance;
    private HashMap<String, HttpSession> sessionMap;

    public FrontSessionContext() {
        sessionMap = new HashMap<String,HttpSession>();
    }

    public static FrontSessionContext getInstance() {
        if (instance == null) {
            instance = new FrontSessionContext();
        }
        return instance;
    }
    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }
}
