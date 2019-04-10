package com.gdyiko.zcwx.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gdyiko.zcwx.po.OnlineApply;
import com.gdyiko.zcwx.weixinUtils.CustomMessageAPI;
import com.gdyiko.zcwx.weixinUtils.WxTemplate;

import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.sql.Template;
import org.springside.modules.web.struts2.Struts2Utils;

@Namespace("/")
@Action(value = "sendTemplate", results = {})
public class SendTemplateAction {

    HttpSession session = null;
    HttpServletRequest request = null;
    String templateData;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式

    public String getTemplateData() {
        return templateData;
    }

    public void setTemplateData(String templateData) {
        this.templateData = templateData;
    }

    public SendTemplateAction() {

        request = ServletActionContext.getRequest();
        session = ServletActionContext.getRequest().getSession();


    }

    public String sendTemplateMessage() {

        System.out.println("【推送消息进来了】");
        System.out.println("------------------templateData1:"+getTemplateData());
        JSONObject jsonObject = JSONObject.fromObject(getTemplateData());
        System.out.println("------------------templateData2:");
        WxTemplate template = (WxTemplate) JSONObject.toBean(jsonObject, WxTemplate.class);

        CustomMessageAPI api = new CustomMessageAPI();
        String result = api.sendTemplateMessage(template);
        JSONObject resData = JSONObject.fromObject(result);
        if (null != result && !result.equals("") && resData.getString("errmsg").equals("ok")) {
            Struts2Utils.renderText("{\"success\":\"true\",\"Msg\":\"发送成功\"}");
        }
        return null;
    }
}
