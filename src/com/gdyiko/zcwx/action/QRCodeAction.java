package com.gdyiko.zcwx.action;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.base.service.WeChatPropertieService;
import com.gdyiko.tool.QRCodeUtil;
import com.gdyiko.zcwx.po.SsAffairObject;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.po.resp.Token;
import com.gdyiko.zcwx.service.SsAffairsObjectService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.TokenHepl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

//@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "QRCode", results = {
//成功
        @Result(name = "success", location = "/"),
        // 失败
        @Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class QRCodeAction extends ActionSupport {

    public static final long cacheTime = 1000 * 60 * 30; //30分钟
    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @Fields serialVersionUID :
     */
    HttpSession session = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
    HttpServletRequest request = null;
    HttpServletResponse response = null;

    private List<SsAffairObject> ssAffairObjectList;

    public QRCodeAction() {
        request = ServletActionContext.getRequest();
        session = ServletActionContext.getRequest().getSession();
        response = ServletActionContext.getResponse();

    }

    private static final long serialVersionUID = -7209385373170290085L;

    @Resource(name = "ssUserInfoService")
    SsUserInfoService ssUserInfoService;

    @Resource(name = "ssAffairsObjectService")
    SsAffairsObjectService ssAffairsObjectService;

    @Autowired
    PropertieService propertieService;

    @Autowired
    WeChatPropertieService weChatPropertieService;

    public void createUUID() {
        Token token = (Token) session.getAttribute("token");
        if (token != null) {
            long curTime = System.currentTimeMillis();
            System.out.println("当前时间----：" + format.format(curTime));
            System.out.println("Token过期时间----：" + format.format(token.getBegin_time() + cacheTime));
            if (curTime - token.getBegin_time() <= cacheTime) {
                String uuid = (String) session.getAttribute("uuid");
                System.out.println("继续监听UUID：" + uuid);
                return;
            }
        }
        String uuid = UUID.randomUUID().toString();
//        System.out.println("---createQRCode----"+uuid);

        System.out.println("---生成UUID----" + uuid);
        session.removeAttribute("uuid");
        session.setAttribute("uuid", uuid);

    }


    public void createQRCode() throws JSONException, IOException {

		/*
        String uuid=UUID.randomUUID().toString();
		System.out.println("---createQRCode----"+uuid);
		session.removeAttribute("uuid");
		session.setAttribute("uuid", uuid);*/
        String uuid = (String) session.getAttribute("uuid");
        //System.out.println("---createQRCode----"+uuid);
//        System.out.println("---后台sesssion获取UUID----"+uuid);

        String APPID = weChatPropertieService.getPropertie("APPID");
        String weChatDNSURL = weChatPropertieService.getPropertie("WeChatDNSURL");
        String content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID + "&redirect_uri=" + weChatDNSURL + "scanConfirm.jsp?uuid=" + uuid + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

        response.reset();
        //设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //String logo =basePath+"images/logo.png";
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        BufferedImage qRCode = null;
        try {
            //qRCode = QRCodeUtil.createImage("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/ssAffairsGuideInfo!findByAffairId?affairid="+affairid+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect", logo,true);
            qRCode = QRCodeUtil.createImage(content, "C:\\ewmLogo.jpg", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 输出图象到页面
        ImageIO.write(qRCode, "JPEG", response.getOutputStream());
        //清除
        /*out.clear();
        out = pageContext.pushBody();*/

    }

    /*检测扫码——未使用*/
    public String scanQRCode() {

        String affairid = request.getParameter("affairid");
        String uuid = (String) session.getAttribute("uuid");
//        System.out.println("--------" + uuid);
        System.out.println("----后台获取页面----" + uuid);
        String sacnResultOpenid = (String) ActionContext.getContext().getApplication().get(uuid);
//        System.out.println("---扫描二维码结果----" + sacnResultOpenid);
        System.out.println("---扫描二维码结果--openId--" + sacnResultOpenid);
        if (sacnResultOpenid != null) {
            HashMap<String, String> hp = new HashMap<String, String>();
            hp.put("state", "success");
            Struts2Utils.renderText(net.sf.json.JSONObject.fromObject(hp).toString());
        }
        return null;

    }

	/*public String scanAffairObject(){
        String affairid=request.getParameter("affairid");
		SsAffairObject ssAffairObject = new SsAffairObject();
		ssAffairObject.setAffairid(affairid);
		
		ssAffairObjectList = ssAffairsObjectService.findEqualByEntity(ssAffairObject, BeanUtilEx.getNotNullEscapePropertyNames(ssAffairObject));
		System.out.println("=-=-==-"+ssAffairObjectList.size());
		return "affairObject";
	}*/

    public String allowScan() {
        HashMap<String, String> hp = new HashMap<String, String>();
        String uuid = request.getParameter("uuid");
        String openid = request.getParameter("openid");

        SsUserInfo ssUserInfo = ssUserInfoService.findById(openid);

        if (ssUserInfo == null) {
            hp.put("state", "fail");
        } else {
            ActionContext.getContext().getApplication().put(uuid, openid);
            hp.put("state", "success");
        }

        Struts2Utils.renderText(net.sf.json.JSONObject.fromObject(hp).toString());
        return null;

    }

    public List<SsAffairObject> getSsAffairObjectList() {
        return ssAffairObjectList;
    }

    public void setSsAffairObjectList(List<SsAffairObject> ssAffairObjectList) {
        this.ssAffairObjectList = ssAffairObjectList;
    }

    public void listenUUID() {
        Boolean result = false;
        String uuid = (String) session.getAttribute("uuid");
        String openId = (String) ActionContext.getContext().getApplication().get(uuid);

        System.out.println("监听uuid--:" + uuid);
        if (openId != null && !openId.equals("")) {
            System.out.println("监听openId--:" + openId);
            result = true;
            Token token = TokenHepl.getaccessToken();
            token.setBegin_time(System.currentTimeMillis());
            session.setAttribute("token", token);
            session.setAttribute("openid", openId);
        }
        Struts2Utils.renderText(result.toString());
    }

}
