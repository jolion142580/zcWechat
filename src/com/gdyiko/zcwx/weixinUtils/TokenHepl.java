package com.gdyiko.zcwx.weixinUtils;

import com.gdyiko.zcwx.po.resp.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import net.sf.json.JSONObject;

/**
 * @ClassName :TokenHepl
 * @Description :TODO
 * @Author :jolion
 * @Date :
 * @Version 1.0
 **/
public class TokenHepl {

    private static Logger log = LoggerFactory.getLogger(TokenHepl.class);

    public static Token accessToken = null;
    public static String jsapi_ticket=null;
    public static final long cacheTime = 1000 * 60 * 30 * 2; //1小时

    public static Token getaccessToken(){
        long curTime = System.currentTimeMillis();
        try {
            System.out.println("----查询Token--"+accessToken);
            log.error("----查询Token--"+accessToken);
            WeixinHttpConnect httpconnect= new WeixinHttpConnect();

            if (accessToken == null ){
                System.out.println("--accessToken为空重新获取---");
                log.error("----accessToken为空重新获取--");
                accessToken = httpconnect.getAccess_token();
                jsapi_ticket = httpconnect.getTicket(accessToken.getAccessToken());
                accessToken.setBegin_time(curTime);
                log.error("----新accessToken为--"+accessToken.toString());
                return accessToken;
            }
            if(accessToken != null&& curTime - accessToken.getBegin_time() >= cacheTime){
                System.out.println("--accessToken自然超时--");
                log.error("----accessToken自然超时--");
                accessToken = httpconnect.getAccess_token();
                jsapi_ticket = httpconnect.getTicket(accessToken.getAccessToken());
                accessToken.setBegin_time(curTime);
                log.error("----新accessToken为--"+accessToken.toString());
                return accessToken;
            }
            JSONObject userJson = UserApi.getUserList(accessToken.getAccessToken());
            if (userJson==null){
                System.out.println("---无法验证accessToken有效--");
                log.error("----无法验证accessToken有效--");
                accessToken = httpconnect.getAccess_token();
                jsapi_ticket = httpconnect.getTicket(accessToken.getAccessToken());
                accessToken.setBegin_time(curTime);
                log.error("----新accessToken为--"+accessToken.toString());
                return accessToken;
            }
            if (userJson.get("errcode")!=null){
                System.out.println("---验证accessToken无效重新获取--");
                log.error("----验证accessToken无效重新获取--");
                accessToken = httpconnect.getAccess_token();
                jsapi_ticket = httpconnect.getTicket(accessToken.getAccessToken());
                accessToken.setBegin_time(curTime);
                log.error("----新accessToken为--"+accessToken.toString());
                return accessToken;
            }
            log.error("accessToken:"+accessToken.toString());
        } catch (Exception e) {
            System.out.println("----查询Token失败--");
            log.error("----查询Token失败--");
            e.printStackTrace();
            log.error(e.getMessage());

        }
        System.out.println(accessToken.toString());

        return accessToken;
    }


}
