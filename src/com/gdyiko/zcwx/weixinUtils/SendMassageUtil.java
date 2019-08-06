package com.gdyiko.zcwx.weixinUtils;

import com.gdyiko.base.service.PropertieService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springside.modules.web.struts2.Struts2Utils;

import java.util.HashMap;
import java.util.Map;

//公用发送短信
@Component
public class SendMassageUtil {

    @Autowired
    PropertieService propertieService;

    public  JSONObject sendSms(String msg,String phone){
        JSONObject rejson = new JSONObject();
        try {
            String url = propertieService.getPropertie("onlineSuccessMsg");
            Map map = new HashMap();
            map.put("method", "sendSMS");
            map.put("content", msg);
            map.put("receiver", phone);
            map.put("token", "0menshi789");

            String content = HttpClientUtil.doPost(url, map);
            JSONObject result = JSONObject.fromObject(content);
            String r = result.getString("result");
            if (r == "true") {
                rejson.put("flag","1");
                rejson.put("Msg","发送成功");
            } else {
                rejson.put("flag","0");
                rejson.put("Msg","发送失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rejson.put("flag","0");
            rejson.put("Msg","发送失败");
        }
        return rejson;
    }

}
