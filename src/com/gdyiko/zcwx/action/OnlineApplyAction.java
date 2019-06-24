package com.gdyiko.zcwx.action;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.base.service.WeChatPropertieService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.PrimaryProduce;
import com.gdyiko.tool.StringUtils;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;
import com.gdyiko.zcwx.po.*;
import com.gdyiko.zcwx.service.*;
import com.gdyiko.zcwx.weixinUtils.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "onlineApply", results = {
//成功
        @Result(name = "success", location = "/"),
        @Result(name = "permitted", location = "/onlineWrite.jsp"),
        @Result(name = "notPermitted", type = "redirect", location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${APPID}&redirect_uri=${WeChatDNSURL}relation.jsp?type=onlineApply&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect"),
        @Result(name = "afairMaterials", type = "redirect", location = "/affairMaterials.jsp"),
        @Result(name = "onlineHistory", location = "/onlineHistory.jsp"),
        @Result(name = "onlineDepart", location = "/onlineList.jsp"),
        @Result(name = "affairs", location = "/affairs.jsp"),
        // 失败
        @Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class OnlineApplyAction extends BaseAction<OnlineApply, String> {

    /**
     * @Fields serialVersionUID :
     */
    HttpSession session = null;
    HttpServletRequest request = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
    private List<OnlineApply> onlineApplyList;
    private List<SsBaseDic> ssBaseDicsList;

    private String APPID; //

    private String weChatDNSURL;

    List<SsAffairs> ssAffairsList;


    public OnlineApplyAction() {

        request = ServletActionContext.getRequest();
        session = ServletActionContext.getRequest().getSession();


    }

    private static final long serialVersionUID = -7209385373170290085L;

    @Resource(name = "ssUserInfoService")
    SsUserInfoService ssUserInfoService;

    @Resource(name = "ssAffairsService")
    SsAffairsService ssAffairsService;

    @Resource(name = "onlineApplyService")
    OnlineApplyService onlineApplyService;

    @Resource(name = "ssBaseDicService")
    SsBaseDicService ssBaseDicService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    PropertieService propertieService;

    @Autowired
    WeChatPropertieService weChatPropertieService;

  /*  @Autowired
    SysUserDao sysUserDao;*/


    @Override
    @Resource(name = "onlineApplyService")
    public void setGenericService(GenericService<OnlineApply, String> genericService) {
        //
        super.setGenericService(genericService);
    }

    public String isrelation() {
        String affairidAndType = request.getParameter("affairid");
        String[] affairidType = affairidAndType.split("_");
        String affairid = affairidType[0];
        String type = affairidType[1];
        String objindex = affairidType[2];
        //System.out.println("--------"+objindex);

        session.setAttribute("affairid", affairid);
        session.setAttribute("objindex", objindex);
        //System.out.println("-----"+affairid);
        String code = request.getParameter("code");

        OAuth oauth = new OAuth();

        //session.setAttribute("openid", "oEyt00yz55O7DYPXt6fVGQIjYZmo");//用于测试

        String openid = (String) session.getAttribute("openid");

        if (code != null && openid == null) {
            openid = oauth.getOppenid(code);
            System.out.println("【session中openid为空】" + openid);

        }
        //System.out.println("====----"+openid);
//        SsUserInfo ssUserInfo = new SsUserInfo();
//        ssUserInfo.setId(openid);

        SsUserInfo ssUserInfo1 = ssUserInfoService.findById(openid);

        if (ssUserInfo1 == null) {
            this.setAPPID(weChatPropertieService.getPropertie("APPID"));
            this.setWeChatDNSURL(weChatPropertieService.getPropertie("WeChatDNSURL"));
            return "notPermitted";
        }
        if (ssUserInfo1 != null && type.equals("onlineApply")) {
            System.out.println("【网上办事网上办事】");
            return "permitted";
        }
        //不需要填表，在onlineApply表插入一条数据，上传图片之后，把iscommit状态为true正式提交
        if (ssUserInfo1 != null && type.equals("affairMaterials")) {
            //if(session.getAttribute("onlineApplyId")==null){
            this.model.setId(PrimaryProduce.produce());
//            this.model.setIscommit("true");
            this.model.setAffairid(affairid);
            this.model.setObjindex(objindex);
            this.model.setOpenid(openid);
            this.model.setCreattime(df.format(new java.util.Date()));
            onlineApplyService.save(this.model);
            //System.out.println("=-=保存后-=="+this.model.getId());
            session.setAttribute("onlineApplyId", this.model.getId());
            //}

            return "afairMaterials";
        }
        //在线申请单填写完之后跳转到上传附件
        //System.out.println("-=-=-=111111-="+type+"==2222"+ssUserInfo1);
        if (ssUserInfo1 != null && type.equals("affairMaterialsByWrite")) {
            //System.out.println("上传附件");
            return "afairMaterials";
        }
        //PC填表微信扫一扫上传资料
        if (ssUserInfo1 != null && type.equals("affairMaterialsByPC2Wechart")) {
            String onlineApplyId = affairidType[3];
            //判断是否同一个账号上传资料
            OnlineApply onlineApply = onlineApplyService.findById(onlineApplyId);
            if (!openid.equals(onlineApply.getOpenid())) {
                return "permitted";
            }
            //判断该事项是否提交
            /*if (onlineApply.getIscommit() != null && onlineApply.getIscommit().equals("true")) {
                return "permitted";
            }*/

            session.setAttribute("onlineApplyId", onlineApplyId);
            return "afairMaterials";
        }
        return null;
    }


    public void onlineApplySave() throws JSONException {
        System.out.println("=======================onlinapplySave====================================");
        System.out.println("====" + this.model.getOnlineData() + "====" + this.model.getAffairid() + "===" + this.model.getOpenid());
        this.model.setState("预审中");
        this.model.setCreattime(df.format(new java.util.Date()));
        String onlineDataTemp = this.model.getOnlineData().replaceAll("null", "\"\"");
        this.model.setOnlineData(onlineDataTemp);

        //onlineApplyService.save(this.model);

        super.save();
        //获取填单id插入上传附件表
        session.setAttribute("onlineApplyId", this.model.getId());
    }

    public void onlineApplySaveToWeb() throws JSONException {

        this.model.setState("待资料上传");
        this.model.setCreattime(df.format(new java.util.Date()));
        String onlineDataTemp = this.model.getOnlineData();
        if (onlineDataTemp != null && !onlineDataTemp.equals("null")) {
            onlineDataTemp = this.model.getOnlineData().replaceAll("null", "\"\"");
            this.model.setOnlineData(onlineDataTemp);
        }
        if (this.model.getId() == null || "".equals(this.model.getId())) {
            this.model.setId(PrimaryProduce.produce());
        }
        try {
            onlineApplyService.save(model);
            session.setAttribute("onlineApplyId", this.model.getId());
            Struts2Utils.renderText("{\"onlineApplyId\":\"" + this.model.getId() + "\"}");
        } catch (Exception exception) {
            exception.printStackTrace();
            Struts2Utils.renderText(getResultJson(FLAG_FAIL));

        }
        //防止pc直接提交表格导致onlineApplyId为空

        //避免浏览器返回时还能显示到表单
//        session.removeAttribute("uuid");
    }

    public String getOnlineDepart() {
        SsBaseDic ssBaseDic = new SsBaseDic();
        ssBaseDic.setBaseDicType("depart");
        ssBaseDic.setValid("1");
        ssBaseDic.setSort("orderby_desc_");
        ssBaseDicsList = ssBaseDicService.findLikeByEntity(ssBaseDic, BeanUtilEx.getNotNullPropertyNames(ssBaseDic));
        return "onlineDepart";
    }

    public String getAllAffairs() {
        SsAffairs ssAffairs = new SsAffairs();
        ssAffairs.setIsonline("true");
        ssAffairs.setLevel("10");
        ssAffairsList = ssAffairsService.findEqualByEntity(ssAffairs, BeanUtilEx.getNotNullEscapePropertyNames(ssAffairs));
        request.setAttribute("departonline", "online");
        request.setAttribute("isOnline", "true");
        return "affairs";
    }

    public String getLevelAffairs() {
        String departId = request.getParameter("departid");
        SsAffairs ss = new SsAffairs();
        ss.setLevel("10");
        ss.setIsonline("true");
        ss.setDepartid(departId);
        ssAffairsList = ssAffairsService.findEqualByEntity(ss, BeanUtilEx.getNotNullPropertyNames(ss));
        request.setAttribute("departonline", "online");

        return "affairs";
    }

    public String onlineApplyHistory() {
        String code = request.getParameter("code");

        OAuth oauth = new OAuth();
        String openid = (String) session.getAttribute("openid");

        if (code != null && openid == null) {
            openid = oauth.getOppenid(code);
        }
        //if(openid==null) openid="openid";
        onlineApplyList = new ArrayList<OnlineApply>();

        this.model.setOpenid(openid);
        this.model.setIscommit("true");
        this.model.setCreattime("orderby_desc_");
        this.model.setState("");
        List<OnlineApply> onlineApplyList1 = onlineApplyService.findLikeByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
        for (OnlineApply onlineApply : onlineApplyList1) {
            SsAffairs ssAffairs = ssAffairsService.findById(onlineApply.getAffairid());
            //删除旧事项 不存在报错
            if (ssAffairs != null) {
                onlineApply.setAffairName(ssAffairs.getAffairname());
                onlineApplyList.add(onlineApply);
                System.out.println(String.format("==================state%s=====================", onlineApply.getState()));
                System.out.println(String.format("==================app%s=====================", onlineApply.getApprovedOrNot()));
            }

        }


        return "onlineHistory";

    }

    public String modify() {
        this.model.setIscommit("true");
        this.model.setState("预审中");
        this.model.setApprovedOrNot("");
        super.modify();

        String openid = (String) session.getAttribute("openid");
        SsUserInfo ssUserInfo = ssUserInfoService.findById(openid);
        OnlineApply onlineApply = onlineApplyService.findById(request.getParameter("myid"));
        SsAffairs ssAffairs = ssAffairsService.findById(onlineApply.getAffairid());

        System.out.println("==============================================" + onlineApply);

        Map<String, TemplateData> m = new HashMap<String, TemplateData>();

        TemplateData first = new TemplateData();
        first.setColor("#000000");
        //bymao " + affairsDO.getAffairName() + "
        first.setValue("您好，" + ssUserInfo.getName() + "  您所申请的" + ssAffairs.getAffairname() + "事项已提交成功，工作人员将于一个工作日回复预审结果，敬请耐心等待，如有疑问可拨打82590191咨询，谢谢。");

        m.put("first", first);
        TemplateData keyword1 = new TemplateData();
        keyword1.setColor("#328392");
        keyword1.setValue(ssUserInfo.getName());
        m.put("keyword1", keyword1);

        TemplateData keyword2 = new TemplateData();
        keyword2.setColor("#328392");
        keyword2.setValue(ssAffairs.getAffairname());
        m.put("keyword2", keyword2);

        TemplateData keyword3 = new TemplateData();
        keyword3.setColor("#328392");
        keyword3.setValue(onlineApply.getCreattime());
        m.put("keyword3", keyword3);

        TemplateData keyword4 = new TemplateData();
        keyword4.setColor("#328392");
        keyword4.setValue(this.model.getState());
        m.put("keyword4", keyword4);


        TemplateData remark = new TemplateData();
        remark.setColor("#929232");
        remark.setValue("请关注");
        m.put("remark", remark);

        WxTemplate template = new WxTemplate();
        String APPID = weChatPropertieService.getPropertie("APPID");
        String weChatDNSURL = weChatPropertieService.getPropertie("WeChatDNSURL");

        template.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID + "&redirect_uri=" + weChatDNSURL + "onlineApply!onlineApplyHistory&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
        template.setTouser(openid);
        template.setTopcolor("#000000");
        template.setTemplate_id("WLkSsDqQtqCeJyy33EbwFyC_CrOHi_FjS1WfaTtYhdM");
        template.setData(m);

        CustomMessageAPI api = new CustomMessageAPI();
        String result = api.sendTemplateMessage(template);
        JSONObject jsonObject = JSONObject.fromObject(result);
        if (jsonObject.getString("errmsg").equals("ok")) {
            System.out.println("发送成功");
            session.removeAttribute("onlineApplyId");

        } else {
            throw new RuntimeException("申请网上办事失败");
        }
        //申请在线填单事项后  再申请其他事项需要清空onlineApplyId

        //网上办事成功后通知部门人员
        String txt = String.format("张槎街道行政服务中心收到新的待办事项，事项名为：%s,请相关人员注意查收", ssAffairs.getAffairname());
        sendSuccessMessage(txt);
        return null;
    }

    public void sendSuccessMessage(String txt) {
        System.out.println("办事成功，发送短信，短信内容：" + txt);
        SysUser sysUser = new SysUser();
        sysUser.setDeptId(30L);
        List<SysUser> sysUserList = sysUserService.findEqualByEntity(sysUser, BeanUtilEx.getNotNullEscapePropertyNames(sysUser));
        Map map = new HashMap();
        String url = propertieService.getPropertie("onlineSuccessMsg");
        map.put("method", "sendSMS");
        map.put("content", txt);
        map.put("token", "0menshi789");
        for (SysUser user : sysUserList) {
            String name = user.getName();
            if (name.equals("蔡伟江") || name.equals("汤庆治") || name.equals("梁巧云")) {
                if (StringUtils.isNotBlank(user.getMobile())) {
                    System.out.println("==================开始发短信=====================");
                    map.put("receiver", user.getMobile());
                    String content = HttpClientUtil.doPost(url, map);
                    JSONObject result = JSONObject.fromObject(content);
                    String r = result.getString("result");
                    if (r != "true") {
                        throw new RuntimeException("短信发送失败");
                    } else {
                        System.out.println("========================发送成功================");
                    }
                }
            }
        }

    }

    public String modifyAffairMaterials() {

        OnlineApply onlineApply = onlineApplyService.findById(this.model.getId());
        session.setAttribute("affairid", onlineApply.getAffairid());
        session.setAttribute("openid", onlineApply.getOpenid());
        session.setAttribute("objindex", onlineApply.getObjindex());
        session.setAttribute("onlineApplyId", this.model.getId());

        return "afairMaterials";
    }


    public String onlineApplyDataModify() {
        this.model.setIscommit("true");
        this.model.setState("预审中");

        super.modify();

        return null;
    }


    public List<OnlineApply> getOnlineApplyList() {
        return onlineApplyList;
    }

    public void setOnlineApplyList(List<OnlineApply> onlineApplyList) {
        this.onlineApplyList = onlineApplyList;
    }

    public List<SsAffairs> getSsAffairsList() {
        return ssAffairsList;
    }

    public void setSsAffairsList(List<SsAffairs> ssAffairsList) {
        this.ssAffairsList = ssAffairsList;
    }

    public List<SsBaseDic> getSsBaseDicsList() {
        return ssBaseDicsList;
    }

    public void setSsBaseDicsList(List<SsBaseDic> ssBaseDicsList) {
        this.ssBaseDicsList = ssBaseDicsList;
    }

    public String getAPPID() {
        return APPID;
    }

    public void setAPPID(String APPID) {
        this.APPID = APPID;
    }


    public String getWeChatDNSURL() {
        return weChatDNSURL;
    }

    public void setWeChatDNSURL(String weChatDNSURL) {
        this.weChatDNSURL = weChatDNSURL;
    }
}
