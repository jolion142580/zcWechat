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
