package com.gdyiko.zcwx.action;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.HttpContent;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springside.modules.web.struts2.Struts2Utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;

//@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "affairProgress", results = {
//成功
        @Result(name = "success", location = "/affairProgressResult.jsp"),

        // 失败
        @Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class AffairProgressAction extends ActionSupport {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    HttpSession session = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式

    @Resource(name = "propertieService")
    PropertieService propertieService;

    @Resource(name = "ssUserInfoService")
    SsUserInfoService ssUserInfoService;


    public String currCode;
    public String url;
    public String openid;
    public String userName;

    public AffairProgressAction() {
        session = ServletActionContext.getRequest().getSession();
    }

    private static final long serialVersionUID = -7209385373170290085L;

	/*public String findByAffairCode(){
        String result="";
		try {
			System.out.println("::::currCode:::::"+this.model.getCurrCode());
			String affairProgressgURL = propertieService.getPropertie("affairsURL")+"/Interface/GetAffairProgress?CurrAffairCode="+this.model.getCurrCode();
			System.out.println("==affairProgressgURL=="+affairProgressgURL);
			HttpContent httpContent = new HttpContent();
			result = httpContent.getHttpContent(affairProgressgURL, "", "", "GET");
			System.out.println(result);
			Struts2Utils.renderText(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}*/

    public String findUserName() {
        String openid = (String) session.getAttribute("openid");
        SsUserInfo ssUserInfo = ssUserInfoService.findById(openid);
        HashMap<String, String> userInfo = new HashMap<String, String>();
        userInfo.put("userName", ssUserInfo.getName());
        String result = JSONObject.fromObject(userInfo).toString();
        Struts2Utils.renderText(result);
        return null;
    }

    public String findByAffairCode() throws JSONException, IOException {

        propertieService = new PropertieService();
        String affairProgressgURL = propertieService.getPropertie("affairsURL") + "queryYmsAffairInfo&username=" + this.getUserName() + "&id=" + this.getCurrCode();
        System.out.println(affairProgressgURL);
        HttpContent httpContent = new HttpContent();
        String content = httpContent.getHttpContent(affairProgressgURL, "", "", "GET");
    /*    String content = "{\n" +
                "    \"status\":1,\n" +
                "    \"data\":{\n" +
                "        \"result\":\"办结\",\n" +
                "        \"user_name\":\"测试\",\n" +
                "        \"auditrecords\":[\n" +
                "            {\n" +
                "                \"flow_result\":\"予以受理\",\n" +
                "                \"operatortime\":\"2014-08-31 15:52:56\",\n" +
                "                \"flow_name\":\"受理\",\n" +
                "                \"depart_name\":null\n" +
                "            },\n" +
                "            {\n" +
                "                \"flow_result\":\"已签收\",\n" +
                "                \"operatortime\":\"2014-08-31 19:57:14\",\n" +
                "                \"flow_name\":\"签收\",\n" +
                "                \"depart_name\":null\n" +
                "            },\n" +
                "            {\n" +
                "                \"flow_result\":\"办结通过\",\n" +
                "                \"operatortime\":\"2014-08-31 19:56:54\",\n" +
                "                \"flow_name\":\"办结\",\n" +
                "                \"depart_name\":null\n" +
                "            },\n" +
                "            {\n" +
                "                \"flow_result\":\"已回复\",\n" +
                "                \"operatortime\":\"2014-12-04 11:16:06\",\n" +
                "                \"flow_name\":\"回复\",\n" +
                "                \"depart_name\":null\n" +
                "            }\n" +
                "        ],\n" +
                "        \"affair_short_name\":\"公共场所卫生许可证新办\",\n" +
                "        \"affair_name\":\"公共场所卫生许可证新办\",\n" +
                "        \"cur_affaircode\":\"12342341234123412341234\"\n" +
                "    }\n" +
                "}\n";*/

        Struts2Utils.renderText(content);

        System.out.println("==============content=======================" + content);

        return null;
    }

/*	public String getSignture(){
       String result="";
       try {
    	   WeixinHttpConnect httpconnect = new WeixinHttpConnect();
    	   Map map = WxJSSignUtil.sign(,"http://www.fsyiko.com/ssWechat/affairProgress.jsp");
    	   JSONObject jsonObject = new JSONObject(map);
    	   result=jsonObject.toString();
    	   System.out.println(result);
    	   Struts2Utils.renderText(result);
    	   
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        return null;
    }*/

    public String isEqualsNull(String content) {
        return content.equals("null") ? "" : content;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /*	public static void main(String[] args) throws JSONException, IOException {
        AffairProgressAction affarip = new AffairProgressAction();
	affarip.setCurrCode("0500SBQ0011612201743285986");
	affarip.findByAffairCode();
	}*/

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
