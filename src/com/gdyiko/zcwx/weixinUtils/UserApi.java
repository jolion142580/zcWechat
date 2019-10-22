<<<<<<< HEAD
package com.gdyiko.zcwx.weixinUtils;

import net.sf.json.JSONObject;

/**
 * @ClassName :UserApi
 * @Description :TODO
 * @Author :jolion
 * @Date :
 * @Version 1.0
 **/
public class UserApi {

    public static JSONObject getUserList(String accessToken){
        String userListUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
        String result = "";
        String url = userListUrl.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID","");
        JSONObject resultJson = null;
        // 调用接口创建菜单
        HttpContent httpcontent = new HttpContent();
        try{
            result = httpcontent.getHttpContent(url,"","","GET");
            resultJson = JSONObject.fromObject(result);
        }catch (Exception e){
            e.printStackTrace();
         }
        System.out.println(resultJson);
         return resultJson;
    }

}
=======
package com.gdyiko.zcwx.weixinUtils;

import com.gdyiko.zcwx.po.SsUserInfo;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * @ClassName :UserApi
 * @Description :TODO
 * @Author :jolion
 * @Date :
 * @Version 1.0
 **/
public class UserApi {

    public static SsUserInfo getUserInfo(){
        HttpSession session  = ServletActionContext.getRequest().getSession();
        //session获取用户信息
        SsUserInfo userInfo = (SsUserInfo) session.getAttribute("user");
    if (userInfo == null) {
      System.out.println("user null");
        /*由于session断开导致用户不能持久化，因此通过拦截器获取cookie信息，
         *并且保持session状态，由于拦截器属于后置，
         * 因此先通过cookie获取用户信息，再通过拦截器保持session*/
        try {
            String userjson = CookieUtil.getCookie("user");
            if (userjson!=null&&!userjson.equals("")){
                JSONObject j = JSONObject.fromObject(userjson);
            System.out.println("--j-"+j.toString());
                userInfo = (SsUserInfo) JSONObject.toBean(j, SsUserInfo.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    System.out.println("----uu----"+JSONObject.fromObject(userInfo).toString());
        return  userInfo;
    }


    public static JSONObject getUserList(String accessToken){
        String userListUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
        String result = "";
        String url = userListUrl.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID","");
        JSONObject resultJson = null;
        // 调用接口创建菜单
        HttpContent httpcontent = new HttpContent();
        try{
            result = httpcontent.getHttpContent(url,"","","GET");
            resultJson = JSONObject.fromObject(result);
        }catch (Exception e){
            e.printStackTrace();
         }
        System.out.println(resultJson);
         return resultJson;
    }



}
>>>>>>> withoutWechatInterface
