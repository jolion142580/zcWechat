package com.gdyiko.zcwx.weixinUtils;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtil {

    public static void addCookie(String name,String value){
        //后期将cookie信息进行加密处理避免信息外漏
        //创建Cookie
        Cookie cookie = new Cookie(name, URLEncoder.encode(value));
        //设置Cookie的生命周期
        cookie.setMaxAge(60*60*24*365);
        ServletActionContext.getResponse().addCookie(cookie);
    }


    public static void removeCookie(String name){
        addCookie(name,"");//“TEST”可以是任意非空字段
    }

    public static String getCookie(String name){

        Cookie[]  cookies = ServletActionContext.getRequest().getCookies();
        if (cookies!=null){
            for (int i = 0 ; i<cookies.length;i++){
                Cookie cookie =  cookies[i];
                System.out.println("cookie:"+cookie.getName()+"-|-"+cookie.getValue());
                if (cookie.getName().equals(name)){
                    String value =cookie.getValue();
                    if(value!=null)
                        return URLDecoder.decode(value);
                }
            }
        }
        return null;
    }


}
