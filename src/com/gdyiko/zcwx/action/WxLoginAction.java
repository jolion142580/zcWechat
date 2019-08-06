package com.gdyiko.zcwx.action;

import com.gdyiko.tool.StringUtils;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.CookieUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Namespace("/")
@Action(
    value = "wxlogin",
    results = {
      // 成功
      @Result(name = "success", location = "/index.jsp"),
      @Result(name = "jump" , type = "redirect", location = "${openUrl}"),
      // 失败
      @Result(name = "input", location = "/login.jsp"),
      @Result(name = "logout", location = "/login.jsp", type = "redirect")
    })
public class WxLoginAction extends ActionSupport {

  private String phone;
  private String SmsCode;

  private String openUrl = "";

  @Resource(name = "ssUserInfoService")
  SsUserInfoService ssUserInfoService;

  public String execute() {

    // 若session已经包含登陆信息则直接放行
    Map<String, Object> session =ActionContext.getContext().getSession();
    SsUserInfo user = (SsUserInfo) session.get("user");

    System.out.println("若session已经包含登陆信息则直接放行---------------->" + user);
    if (user != null) {

      return "success";
    }

    String url = (String) session.get("JumpUrl");

    // 从cookies中获取用户信息，并自动登陆
    String userjson = CookieUtil.getCookie("user");
    //判断获取的值是否为空
    if (userjson!=null&&!userjson.equals("")){
      JSONObject j = JSONObject.fromObject(userjson);
      user = (SsUserInfo) JSONObject.toBean(j, SsUserInfo.class);
      //验证对象是否一致
      if (user != null && !user.getName().equals("")) {
        SsUserInfo ckuser = ssUserInfoService.findById(user.getId());
        if (user.equals(ckuser)){
          ActionContext.getContext().getSession().put("user", user);
          CookieUtil.addCookie("user", JSONObject.fromObject(user).toString());
          if (StringUtils.isNotEmpty(url)) {
            setOpenUrl(url);
            return "jump";
          }
          return "success";
        }
      }
    }


/*    if (isInvalid(phone)) return INPUT;

    if (isInvalid(SmsCode)) return INPUT;*/
    // 从库中查找是否存在该数据
    user = ssUserInfoService.findByPhone(getPhone());
    String smscode = (String) ActionContext.getContext().getSession().get("smscode"); // 对照验证码

    if (user != null && !user.getName().equals("") &&SmsCode==smscode) {
      ActionContext.getContext().getSession().put("user", user);
      CookieUtil.addCookie("user", JSONObject.fromObject(user).toString());
      if (StringUtils.isNotEmpty(url)) {
        setOpenUrl(url);
        return "jump";
      }
      return "success";
    }
    return "input";
  }


  public String logout(){

    HttpSession session = ServletActionContext.getRequest().getSession();
    session.removeAttribute("user");
    CookieUtil.removeCookie("user");
    return "logout";
  }




  private boolean isInvalid(String value) {
    return (value == null || value.length() == 0);
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getSmsCode() {
    return SmsCode;
  }

  public void setSmsCode(String smsCode) {
    SmsCode = smsCode;
  }

  public String getOpenUrl() {
    return openUrl;
  }

  public void setOpenUrl(String openUrl) {
    this.openUrl = openUrl;
  }
}
