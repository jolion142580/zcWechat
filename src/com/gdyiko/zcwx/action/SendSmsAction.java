package com.gdyiko.zcwx.action;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.zcwx.weixinUtils.HttpClientUtil;
import com.gdyiko.zcwx.weixinUtils.SendMassageUtil;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.weixinUtils.HttpContent;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Namespace("/")
@Action(value = "sms", results = {})
public class SendSmsAction extends ActionSupport {
    @Autowired
    SendMassageUtil sendMassageUtil;

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //	http://localhost/SMS/SMSServlet?moblie=13425889665&moblieContent=testnihao&validation=ddd60800269641d7b036a789fa113a51
//    private static final String SEND = "http://127.0.0.1:8081/SMS/SMSServlet?moblie=phone&moblieContent=txt&validation=vale";
    /*	private static final String REPORT = "https://api.ums86.com:9600/sms/Api/report.do";
        private static final String SEARCHNUMBER = "https://api.ums86.com:9600/sms/Api/SearchNumber.do";
        private static final String REPLY = "https://api.ums86.com:9600/sms/Api/reply.do";
        private static final String REPLYCONFIRM = "https://api.ums86.com:9600/sms/Api/replyConfirm.do";*/
//认证绑定短信接口
    private String code = null;
    private String phone = null;
    private String txt = null;

    public SendSmsAction() {

    }

    // 4.2.2.发送短信
    public String sendSmsmessage() {
        //
        //String smscode = (String) ActionContext.getContext().getSession().get("smscode");

        try {
            if (("".equals(this.getTxt()) || this.getTxt() == null) ) {

                int codeLength = 6;
                String smscode = "";
                for (int i = 0; i < codeLength; i++) {
                    smscode += (int) (Math.random() * 9);
                }
                HttpServletRequest request=ServletActionContext.getRequest();
                HttpSession session=request.getSession();
                session.setMaxInactiveInterval(10*60);
                session.removeAttribute("smscode");
                session.setAttribute("smscode",smscode);
                String str = "欢迎使用张槎街道行政服务中心微信平台!帐号认证验证码：" + smscode + ",10分钟内有效。";
                this.setTxt(str);
            }
            JSONObject resjson =sendMassageUtil.sendSms(this.getTxt(),this.getPhone());
            Struts2Utils.renderText(resjson.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Struts2Utils.renderText("{\"flag\":\"0\",\"Msg\":\"发送失败\"}");
        }
        return null;
    }

    // 回执接口
    public String getReport() {
        return null;
    }

    // 剩余短信条数查询接口
    public String getnumber() {
        return null;
    }

    // 上行回复内容查询接口
    public String reply() {
        return null;
    }

    // 4.2.6.上行回复内容确认接口
    public String replyConfirm() {
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

}
