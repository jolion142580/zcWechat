package com.gdyiko.base.interceptor;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gdyiko.zcwx.po.SsUserInfo;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class JumpBeforeInterceptor extends AbstractInterceptor implements ApplicationContextAware{

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        ActionContext actionContext = ai.getInvocationContext();
        Map<String, Object> session = actionContext.getSession();
        SsUserInfo currentUser = (SsUserInfo) session.get("user");
        if (null != currentUser
                && !currentUser.getName().equals("")) {
            return ai.invoke();//执行下一个拦截器，如果没有拦截器了，就执行action
        }
        else{
            HttpServletRequest request = (HttpServletRequest) actionContext
                    .get(StrutsStatics.HTTP_REQUEST);
            String queryString = request.getQueryString();
            // 预防空指针
            if (queryString == null) {
                queryString = "";
            }
            // 拼凑得到登陆之前的地址
            String realPath = request.getRequestURI() + "?" + queryString;
            System.out.println(realPath+"	realPath");
            session.put("JumpUrl", realPath);
            return ai.invoke();//放行
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        // TODO Auto-generated method stub

    }
}