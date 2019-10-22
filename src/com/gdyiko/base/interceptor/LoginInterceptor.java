<<<<<<< HEAD
package com.gdyiko.base.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.gdyiko.zcwx.po.SsUserInfo;
import org.apache.struts2.StrutsStatics;

import com.gdyiko.base.po.MemGeninf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        // TODO Auto-generated method stub
        ///AFControlUtil afc = new AFControlUtil();
        ActionContext context = invocation.getInvocationContext();
        String theme = "default";
        // 通过ActionContext来获取httpRequest
        HttpServletRequest request = (HttpServletRequest) context.get(StrutsStatics.HTTP_REQUEST);

        //放行
        SsUserInfo u = null;


        if (request.getSession().getAttribute("user") != null) {
            u = (SsUserInfo) request.getSession().getAttribute("user");
        }
        System.out.println("测试拦截器------------>" + u);
        System.out.println("com.gdyiko.base.interceptor.LoginInterceptor line[30] output: -=>" + u != null);
        if (u != null) {
            //后台登陆
            System.out.println("com.gdyiko.base.interceptor.LoginInterceptor line[33] output: -=>" + u.getName());
            invocation.invoke();
        }


        return "login";

    }

}
=======
package com.gdyiko.base.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.weixinUtils.CookieUtil;
import net.sf.json.JSONObject;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {
  @Override
  protected String doIntercept(ActionInvocation invocation) throws Exception {
    // TODO Auto-generated method stub
    /// AFControlUtil afc = new AFControlUtil();
    ActionContext context = invocation.getInvocationContext();
    String theme = "default";
    // 通过ActionContext来获取httpRequest
    HttpServletRequest request = (HttpServletRequest) context.get(StrutsStatics.HTTP_REQUEST);
    // 放行
    SsUserInfo u = null;

    if (request.getSession().getAttribute("user") != null) {
      u = (SsUserInfo) request.getSession().getAttribute("user");
    }
    System.out.println("测试拦截器------------>" + u);

    //获取cookie信息，即使session断开通过cookie来重新连接session
    String userjson = CookieUtil.getCookie("user");
    if (userjson!=null&&!userjson.equals("")){
      JSONObject j = JSONObject.fromObject(userjson);
      u = (SsUserInfo) JSONObject.toBean(j, SsUserInfo.class);
      ActionContext.getContext().getSession().put("user", u);
      CookieUtil.addCookie("user", JSONObject.fromObject(u).toString());
    }



    if (u != null) {
      // 后台登陆
      invocation.invoke();
    }
    return "login";
  }
}
>>>>>>> withoutWechatInterface
