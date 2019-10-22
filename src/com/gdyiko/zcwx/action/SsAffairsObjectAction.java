<<<<<<< HEAD
package com.gdyiko.zcwx.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsAffairObject;
import com.gdyiko.zcwx.po.SsAffairs;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.SsAffairsGuideService;
import com.gdyiko.zcwx.service.SsAffairsObjectService;
import com.gdyiko.zcwx.service.SsAffairsService;
import com.gdyiko.zcwx.service.SsBaseDicService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.OAuth;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.service.GenericService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.conversion.annotations.ConversionRule;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

//@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "ssAffairsObjectInfo", results = {
//成功
        @Result(name = "affairObject", location = "/affairObject.jsp"),
        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/relation.jsp?type=yuyue&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect
        @Result(name = "affairObjectToWeb", location = "/web/affairObject.jsp"),
        @Result(name = "webIndex", type = "redirect", location = "/web/QRCodeImg.jsp"),
        @Result(name = "affair", location = "/affairs.jsp"),
        // 失败
        @Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class SsAffairsObjectAction extends BaseAction<SsAffairObject, String> {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    HttpSession session = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式

    private List<SsAffairObject> ssAffairObjectList;

    @Resource(name = "ssAffairsObjectService")
    SsAffairsObjectService ssAffairsObjectService;

    @Resource(name = "ssAffairsService")
    SsAffairsService ssAffairsService;

    @Resource(name = "ssAffairsObjectService")
    @Override
    public void setGenericService(GenericService<SsAffairObject, String> genericService) {
        // TODO Auto-generated method stub
        super.setGenericService(genericService);
    }

    public SsAffairsObjectAction() {

        session = ServletActionContext.getRequest().getSession();

    }

    private static final long serialVersionUID = -7209385373170290085L;

    public String findByAffairId() {
        //System.out.println("--------"+this.model.getAffairid());
        this.model.setIsshow("1");
        ssAffairObjectList = ssAffairsObjectService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
        return "affairObject";
    }

    public String findByAffairIdToWeb() {

        String uuid = (String) session.getAttribute("uuid");
        if(uuid == null){
            return "webIndex";
        }
        String openid = (String) ActionContext.getContext().getApplication().get(uuid);
        if (openid == null) {
            return "webIndex";
        }
        ssAffairObjectList = ssAffairsObjectService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));

        return "affairObjectToWeb";
    }


    public List<SsAffairObject> getSsAffairObjectList() {
        return ssAffairObjectList;
    }

    public void setSsAffairObjectList(List<SsAffairObject> ssAffairObjectList) {
        this.ssAffairObjectList = ssAffairObjectList;
    }


}
=======
package com.gdyiko.zcwx.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsAffairObject;
import com.gdyiko.zcwx.po.SsAffairs;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.SsAffairsGuideService;
import com.gdyiko.zcwx.service.SsAffairsObjectService;
import com.gdyiko.zcwx.service.SsAffairsService;
import com.gdyiko.zcwx.service.SsBaseDicService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.OAuth;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.service.GenericService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.conversion.annotations.ConversionRule;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "ssAffairsObjectInfo", results = {
//成功
        @Result(name = "affairObject", location = "/affairObject.jsp"),
        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/relation.jsp?type=yuyue&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect
        @Result(name = "affairObjectToWeb", location = "/web/affairObject.jsp"),
        @Result(name = "webIndex", type = "redirect", location = "/web/QRCodeImg.jsp"),
        @Result(name = "affair", location = "/affairs.jsp"),
        // 失败
        @Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class SsAffairsObjectAction extends BaseAction<SsAffairObject, String> {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    HttpSession session = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式

    private List<SsAffairObject> ssAffairObjectList;

    @Resource(name = "ssAffairsObjectService")
    SsAffairsObjectService ssAffairsObjectService;

    @Resource(name = "ssAffairsService")
    SsAffairsService ssAffairsService;

    @Resource(name = "ssAffairsObjectService")
    @Override
    public void setGenericService(GenericService<SsAffairObject, String> genericService) {
        // TODO Auto-generated method stub
        super.setGenericService(genericService);
    }

    public SsAffairsObjectAction() {

        session = ServletActionContext.getRequest().getSession();

    }

    private static final long serialVersionUID = -7209385373170290085L;

    public String findByAffairId() {
        //System.out.println("--------"+this.model.getAffairid());
        this.model.setIsshow("1");
        ssAffairObjectList = ssAffairsObjectService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
        return "affairObject";
    }

    public String findByAffairIdToWeb() {

        String uuid = (String) session.getAttribute("uuid");
        if(uuid == null){
            return "webIndex";
        }
        String openid = (String) ActionContext.getContext().getApplication().get(uuid);
        if (openid == null) {
            return "webIndex";
        }
        ssAffairObjectList = ssAffairsObjectService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));

        return "affairObjectToWeb";
    }


    public List<SsAffairObject> getSsAffairObjectList() {
        return ssAffairObjectList;
    }

    public void setSsAffairObjectList(List<SsAffairObject> ssAffairObjectList) {
        this.ssAffairObjectList = ssAffairObjectList;
    }


}
>>>>>>> withoutWechatInterface
