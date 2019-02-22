package com.gdyiko.zcwx.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.json.JSONObject;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.BusinessWeight;
import com.gdyiko.zcwx.po.OnlineApply;
import com.gdyiko.zcwx.po.ServerType;
import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsAffairs;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.BusinessWeightService;
import com.gdyiko.zcwx.service.OnlineApplyService;
import com.gdyiko.zcwx.service.ServerTypeService;
import com.gdyiko.zcwx.service.SsAffairsGuideService;
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
@Action(value = "ssAffairsInfo", results = {
//成功
        @Result(name = "success", location = "/affairs.jsp"),
        @Result(name = "onlineList", location = "/onlineList.jsp"),
        // 失败
        @Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class SsAffairsAction extends BaseAction<SsAffairs, String> {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    HttpSession session = null;
    HttpServletRequest request = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式

    private List<SsAffairs> ssAffairsList;
    private String keyword;

    private String deptInfoId;

    @Resource(name = "ssAffairsGuideService")
    SsAffairsGuideService ssAffairsGuideService;

    @Resource(name = "onlineApplyService")
    OnlineApplyService onlineApplyService;

    @Resource(name = "ssUserInfoService")
    SsUserInfoService ssUserInfoService;

    @Resource(name = "ssAffairsService")
    SsAffairsService ssAffairsService;

    @Resource(name = "ssAffairsService")
    @Override
    public void setGenericService(GenericService<SsAffairs, String> genericService) {
        // TODO Auto-generated method stub
        super.setGenericService(genericService);
    }


    public SsAffairsAction() {
        request = ServletActionContext.getRequest();
        session = ServletActionContext.getRequest().getSession();

    }

    private static final long serialVersionUID = -7209385373170290085L;

    public String findByDepartId() throws UnsupportedEncodingException {
        System.out.println("::::baseDicId:::::" + this.model.getDepartid());
        this.model.setLevel("10");
        ssAffairsList = ssAffairsService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));

        return "success";
    }

    public String findByBaseDicId() throws UnsupportedEncodingException {
        //System.out.println("::::baseDicId:::::"+this.model.getBaseDicId());
        String pa = new String(this.model.getDepartid().getBytes("ISO-8859-1"), "UTF-8");
        this.model.setDepartid(pa);
        //this.model.setBaseDicId(baseDicId);
        ssAffairsList = ssAffairsService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
        if (pa.equals("all")) {
            this.model.setDepartid("");
            this.model.setLevel("10");
            ssAffairsList = ssAffairsService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
        }
        ServletActionContext.getRequest().setAttribute("isOnline","false");
        ServletActionContext.getRequest().setAttribute("ssAffairsList", ssAffairsList);
        return "success";
    }

    public String findByItem() throws UnsupportedEncodingException {

        ssAffairsList = ssAffairsService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
        request.setAttribute("departonline", "online");
        return "success";
    }

    public String findByAffairName() throws UnsupportedEncodingException {
        System.out.println("---------" + this.model.getAffairname());

        keyword = new String(this.model.getAffairname().getBytes("ISO-8859-1"), "UTF-8");
        this.model.setLevel("10");
        ssAffairsList = ssAffairsService.findLikeByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
        JSONArray ja = JSONArray.fromObject(ssAffairsList);
        Struts2Utils.renderText(ja.toString());
        return null;
    }

    public String findConfigByAffairId() {
        try {
            System.out.println("-=-=-=-" + request.getParameter("onlineApplyId"));
            System.out.println("-=-affairid=-=-" + this.model.getAffairid());
            String onlineApplyId = request.getParameter("onlineApplyId");
            String objindex = request.getParameter("objindex");
            //String openid = "oEyt00yz55O7DYPXt6fVGQIjYZmo";
            String openid = (String)session.getAttribute("openid");
            if(openid==null){//电脑端扫码登录获取表单信息
            	String uuid = (String)session.getAttribute("uuid");
            	openid = (String)ActionContext.getContext().getApplication().get(uuid);
            }

            JSONObject jo = ssAffairsService.findConfigByAffairId(onlineApplyId, this.model.getAffairid(), objindex,openid);
            Struts2Utils.renderText(jo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public String findAffairByIsonline() throws JSONException {

        ssAffairsList = ssAffairsService.findAffairByIsonline();

        return "onlineList";

    }


    public List<SsAffairs> getSsAffairsList() {
        return ssAffairsList;
    }


    public void setSsAffairsList(List<SsAffairs> ssAffairsList) {
        this.ssAffairsList = ssAffairsList;
    }


    public String getKeyword() {
        return keyword;
    }


    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public String getDeptInfoId() {
        return deptInfoId;
    }


    public void setDeptInfoId(String deptInfoId) {
        this.deptInfoId = deptInfoId;
    }


}
