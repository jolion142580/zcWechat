package com.gdyiko.zcwx.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdyiko.zcwx.service.BlackWhiteListService;
import com.gdyiko.zcwx.weixinUtils.Holiday;
import com.gdyiko.zcwx.weixinUtils.UserApi;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.po.YuYues;
import com.gdyiko.zcwx.service.InterfaceService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.service.YuYuesService;
import com.gdyiko.zcwx.weixinUtils.HttpContent;
import com.gdyiko.zcwx.weixinUtils.OAuth;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;
import com.opensymphony.xwork2.ActionContext;

@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "YuYues", results = {
        //成功
        @Result(name = "success", location = "/"),
        //已绑定，允许预约
        @Result(name = "permitted", location = "/newyuyue.jsp"),
        //未绑定，不允许预约
        //@Result(name = "notPermitted", type="redirect",location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx481172387f6fb7c5&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/ssUserInfo!userlist?type=yuyue&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect"),
//	@Result(name = "notPermitted", type="redirect",location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/relation.jsp?type=yuyue&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect"),
    //    @Result(name = "notPermitted", type = "redirect", location = "/relation.jsp?type=yuyue"),
        //已绑定，允许预约
        @Result(name = "userYuYues", location = "/newwdyy_2.jsp"),
        //已绑定，允许预约
        @Result(name = "singleYuYue", location = "/wdyy_1.jsp"),
        // 失败
        @Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class YuYuesAction extends BaseAction<YuYues, String> {

    private static final long serialVersionUID = 8321018464736012550L;

    private String openid;
    //业务类型（综合业务、公安业务）
    private String businessType;
    private String twoLevelAffairId;
    private String wordBookId;


    //大厅编码
    private String officeId;
    //预约办事类型编码
    private String appointmentType;
    //预约日期 2016-07-29
    private String appointDate;
    //预约类型 G-工作日预约，W-周末预约
    private String appointType;


    private String appointMsg;

    private net.sf.json.JSONObject dayJson;


    //private String paramVal;

    private List<YuYues> yuYuesList;

    public YuYuesAction() {
        //HttpServletRequest request = ServletActionContext.getRequest();
        //HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get("request");
        //System.out.println(":::::::::"+request.getParameter("code"));
    }

    @Resource(name = "yuYuesService")
    YuYuesService yuYuesService;

    @Resource(name = "ssUserInfoService")
    SsUserInfoService ssUserInfoService;

    @Resource(name = "interfaceService")
    InterfaceService ifs;

    @Resource(name = "blackWhiteListService")
    BlackWhiteListService blackWhiteListService;

    @Autowired
    Holiday holiday;


    @Resource(name = "yuYuesService")
    @Override
    public void setGenericService(GenericService<YuYues, String> genericService) {
        // TODO Auto-generated method stub
        super.setGenericService(genericService);
    }
    /*
     *
     * 传入code，再获得openid，判断这个openid是否已绑定
     * @return
     * */

    //    预约确认
    public String yuYues() {


        HttpServletRequest request = ServletActionContext.getRequest();
        String affairname = request.getParameter("affairname");
       /* if(affairname == null || "null".equals(affairname)){
            ActionContext.getContext().getSession().remove("businessName");
        }else{
            ActionContext.getContext().getSession().put("businessName",affairname);
        }*/
//        String paramVal = request.getParameter("paramVal"); //选择办理业务 -->统一综合业务不需要限制
//		System.out.println("===paramVal=="+paramVal);

//        ActionContext.getContext().getSession().put("paramVal", paramVal);


        //校验是否已绑定
        //SsUserInfo sui=new SsUserInfo();
        //sui.setId(this.getOpenid());
        //List<SsUserInfo> list = ssUserInfoService.findLikeByEntity(sui, BeanUtilEx.getNotNullEscapePropertyNames(sui));

        //HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get("request");
        String code = request.getParameter("code");

		
		/*OAuth oauth=new OAuth();
         openid=oauth.getOppenid(code);
		 if(openid==null||openid==""){
			 openid="openid";
		 }
		this.model.setOpenid("2222");
		//this.model.setOpenid("14864358837182847135");
//		System.out.println("---openid---"+openid);
		SsUserInfo ssUserInfo = new SsUserInfo();

		//setOpenid
//		ssUserInfo.setOpenid(this.model.getOpenid());
		List<SsUserInfo> ssUserList = ssUserInfoService.findEqualByEntity(ssUserInfo, BeanUtilEx.getNotNullEscapePropertyNames(ssUserInfo));
//		SsUserInfo ssUserInfo = ssUserInfoService.findById(this.model.getOpenid());
//		System.out.println("----ssUserList---"+ssUserList.size());
		if(ssUserList.size()<=0){
			System.out.println("-=-=-==-=ssUserList----");
			return "notPermitted";
		}
		yuYuesList = new ArrayList<YuYues>();
		
		for(SsUserInfo ssUser : ssUserList){
			System.out.println("--name---"+ssUser.getName());
			YuYues yuyue = new YuYues();
			yuyue.setName(ssUser.getName());
			yuyue.setIdcard(ssUser.getIdCard());
			yuyue.setPhone(ssUser.getPhone());
			yuYuesList.add(yuyue);
		}
		
		ActionContext.getContext().put("yuYuesList", yuYuesList);

		
	    this.model.setName(ssUserInfo.getName());
		this.model.setIdcard(ssUserInfo.getIdCard());
		this.model.setPhone(ssUserInfo.getPhone());

		
		return "permitted";*/


       /* OAuth oauth = new OAuth();
        openid = oauth.getOppenid(code);
        this.model.setOpenid(openid);*/
        //todo openid 要换
//        String openid = "2222";
        //System.out.println("---openid---"+openid);
        //bymao
        SsUserInfo ssUserInfo = UserApi.getUserInfo();//ssUserInfoService.findById(this.model.getOpenid());
//        SsUserInfo ssUserInfo = ssUserInfoService.findById(openid);
        //System.out.println("----ssUserInfo---"+ssUserInfo.getName());
   /*     if (ssUserInfo == null) {
            return "notPermitted";
        }*/

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date addDate = holiday.getIncomeDate(new Date());
            String date = format.format(addDate);
            String weekDay = holiday.convert(addDate.getDay());
            //System.out.println(":::::::::::::"+addDate);

            Date addDate2 = holiday.getIncomeDate(addDate);
            String date2 = format.format(addDate2);
            String weekDay2 = holiday.convert(addDate2.getDay());
            //System.out.println(":::::::::::::"+addDate2);

            Date addDate3 = holiday.getIncomeDate(addDate2);
            String date3 = format.format(addDate3);
            String weekDay3 = holiday.convert(addDate3.getDay());

            Date addDate4 = holiday.getIncomeDate(addDate3);
            String date4 = format.format(addDate4);
            String weekDay4 = holiday.convert(addDate4.getDay());

            Date addDate5 = holiday.getIncomeDate(addDate4);
            String date5 = format.format(addDate5);
            String weekDay5 = holiday.convert(addDate5.getDay());
            dayJson = new net.sf.json.JSONObject();
            dayJson.put(date, weekDay);
            dayJson.put(date2, weekDay2);
            dayJson.put(date3, weekDay3);
            dayJson.put(date4, weekDay4);
            dayJson.put(date5, weekDay5);



        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        this.model.setName(ssUserInfo.getName());
        this.model.setIdcard(ssUserInfo.getIdCard());
        this.model.setPhone(ssUserInfo.getPhone());
        this.model.setBusinessName("null".equals(affairname) ? "" : affairname);

        return "permitted";
    }

    //返回用户预约列表页面
    public String userYuYues() {

        SsUserInfo ssUserInfo = UserApi.getUserInfo();
        //openid=ssUserInfo.getId();

        this.model.setOpenid(ssUserInfo.getId());

        return "userYuYues";
    }

    //返回单个预约详细信息的页面
    public String singleYuYue() {

        return "singleYuYue";
    }

    //保存预约：1、要重新检测是否有号
    //检查该时段是否有号
    public String saveYuYues() {
        //System.out.println("---svaeOpenid---"+this.model.getOpenid());
//		System.out.println("--stype--"+this.model.getStype()+"-=-idcard==="+this.model.getIdcard()+"-=-phone=-"+this.model.getPhone());

        try {
            String result = "";
            //查看黑白名单
            JSONObject json = blackWhiteListService.check(model);
            String flag = json.get("flag").toString();
//            String forever = json.getString("forever");
            String forever = json.get("forever").toString();
            if ("1".equals(flag)) {
                result = yuYuesService.saveYuYues(this.model);
            } else if ("1".equals(forever) && "0".equals(flag)) {
                //永久黑名单
                result = "{\"msg\":\"您在黑名单中，不能使用预约功能！\"}";
            } else {
                //今年进入黑名单
                result = "{\"msg\":\"您在一年内超过3次失约，被限制使用预约功能！\"}";
            }

//            String idCard = this.model.getIdcard();
//            HttpContent httpContent = new HttpContent();
            //黑名单：
            //是,返回1；
            //条件错误,返回2；
            //不是,返回3;
            //报错,返回错误信息;
//            String blackContent = httpContent.getHttpContent("http://19.127.14.204:8080/pjcms/BlackList!hasBlackListNow.action?idCard=" + idCard, "", "", "POST");
            //String blackContent = httpContent.getHttpContent("http://192.168.1.35:8088/pjcms/BlackList!hasBlackListNow.action?idCard="+idCard, "", "", "POST");
            /*
             * 白名单
             * 是,返回1;
             * idCard为空,返回2;
             * 不是,返回3;
             * 报错,返回错误信息;
             */

//            String whiteContent = httpContent.getHttpContent("http://19.127.14.204:8080/pjcms/WhiteList!hasWhiteList.action?idCard=" + idCard, "", "", "POST");
            //String whiteContent = httpContent.getHttpContent("http://192.168.1.35:8088/pjcms/WhiteList!hasWhiteList.action?idCard="+idCard, "", "", "POST");
//			System.out.println(BlackContent);
//            JSONObject joBlack = new JSONObject(blackContent);
//            JSONObject joWhite = new JSONObject(whiteContent);
//            if ("1".equals(joWhite.getString("flag"))) {
//                result = yuYuesService.saveYuYues(this.model);
//            }
//            if ("1".equals(joBlack.getString("flag")) && "3".equals(joWhite.getString("flag"))) {
            //System.out.println(idCard+"是黑名单");
//                result = "{\"msg\":\"您在一年内超过3次失约，被限制使用预约功能！\"}";
//            } else {
//                result = yuYuesService.saveYuYues(this.model);
//            }

//			System.out.println("result---->"+result);
            Struts2Utils.renderText(result);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;
    }

    //通过日期获得剩余号数
    public String getCount() {
        //System.out.println("-=-==weight-="+this.model.getWeight());
//        String result = yuYuesService.getCount(this.model.getStreet(), this.model.getYdate(), businessType, this.model.getWeight());
        String result = yuYuesService.getCount(this.model.getStreet(), this.model.getYdate());
//		System.out.println("result---->"+result);
        Struts2Utils.renderText(result);
        return null;

    }

    //取消预约
    public String cancelYuYue() {
        String result = yuYuesService.cancelYuYue(model);
        Struts2Utils.renderText(result);
        /*String result = "";
        String s_time = this.model.getYdate() + " " + this.model.getYstime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d = null;
        try {
            d = sdf.parse(s_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long s_timestamp = d.getTime();
        long now_timestamp = new Date().getTime();

        if ("1".equals(this.model.getState())) {

            result = "{\"msg\":\"该预约已经签到，无须取消！\"}";
        } else if (s_timestamp < now_timestamp) {
            result = "{\"msg\":\"请在选取的预约时间段前取消！\"}";
        } else {
            result = yuYuesService.cancelYuYue(this.model);
        }


        System.out.println("result---->" + result);
        Struts2Utils.renderText(result);
        return null;*/
      /*  String appointId=this.model.getNo();
        String url = "http://192.168.1.207:8089/cancelAppointment?appointId="+appointId;
        HttpContent httpContent = new HttpContent();
        try {
            String content = httpContent.getHttpContent(url, "", "", "post");
            Struts2Utils.renderText(content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/


        return null;

    }

    //获得用户的预约信息列表
    //预约记录
    public String userYuYuesJson() {
        //System.out.println("---yuyueListOpenid---"+this.model.getOpenid());
        //this.model.setOpenid("ovh5dxGh-9EXBe-fFYD5IU1fSW4k");
  /*      if (this.model.getOpenid().equals("")) {
            //this.model.setOpenid("oxFMm1hm4FmIDGvwIeRJk7iA-D3A");
        }*/
        String result = yuYuesService.yuYueList(this.model.getOpenid());
        System.out.println("result---->" + result);
        Struts2Utils.renderText(result);

        //todo openid要换
//        String openid = "2222";
/*        String openid =this.model.getOpenid();
        SsUserInfo ssUserInfo = ssUserInfoService.findById(openid);
        if (ssUserInfo != null) {
            String idCard = ssUserInfo.getIdCard();
            String url = "http://192.168.1.207:8089/queryAppointmentListByIdNum?idNum=" + idCard;
            HttpContent httpContent = new HttpContent();
            try {
                String content = httpContent.getHttpContent(url, "", "", "post");
                Struts2Utils.renderText(content);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/


        return null;
    }

    //获得单个预约的详细信息(返回json字符串)
    public String singleYuYueJson() {
//        String no =this.model.getNo();
//        String url = "http://192.168.1.207:8089/queryAppointmentByappointmentId?APPOINTMENT_ID="+no;
        String result = yuYuesService.singleYuYue(this.model);
//        System.out.println("result---->" + result);
        Struts2Utils.renderText(result);
//        HttpContent httpContent = new HttpContent();
        try {
//            String content = httpContent.getHttpContent(url, "", "", "post");
//            Struts2Utils.renderText(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTodayYuyues() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        this.model.setYdate(sdf.format(new Date()));
        this.model.setState("0");
        List<YuYues> yuyuesList = yuYuesService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));

        JSONArray ja = JSONArray.fromObject(yuyuesList);
        Struts2Utils.renderText(ja.toString());
        return null;

    }

    //查询预约时间段剩余 停用
    public String queryAppointmentQueue() {
//        String url = String.format("http://192.168.1.207:8089/queryAppointmentQueue?officeId=%d &appointment_type=%d &appointDate=%d &appointType=%d", officeId, appointment_type, appointDate, appointType);
        String url = "http://192.168.1.207:8089/queryAppointmentQueue?officeId=" + officeId + "&appointment_type=" + appointmentType + "&appointDate=" + appointDate + "&appointType=" + appointType;
//       http://192.168.1.207:8089/queryAppointmentQueue?officeId=CC00Z03C0001&appointment_type=ZH001&appointDate=2019-06-24&appointType=G
        HttpContent httpContent = new HttpContent();
        try {
            String content = httpContent.getHttpContent(url, "", "", "post");
            Struts2Utils.renderText(content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sendAppointment() {
        try {
            String sendData = URLEncoder.encode(appointMsg, "utf-8");
            sendData = URLEncoder.encode(sendData, "utf-8");
            String url = "http://192.168.1.207:8089/sendAppointment?appointMsg=" + sendData;
            HttpContent httpContent = new HttpContent();
            String content = httpContent.getHttpContent(url, "", "", "post");
            Struts2Utils.renderText(content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAppointMsg() {
        return appointMsg;
    }

    public void setAppointMsg(String appointMsg) {
        this.appointMsg = appointMsg;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getTwoLevelAffairId() {
        return twoLevelAffairId;
    }

    public void setTwoLevelAffairId(String twoLevelAffairId) {
        this.twoLevelAffairId = twoLevelAffairId;
    }

    public String getWordBookId() {
        return wordBookId;
    }

    public void setWordBookId(String wordBookId) {
        this.wordBookId = wordBookId;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(String appointDate) {
        this.appointDate = appointDate;
    }

    public String getAppointType() {
        return appointType;
    }

    public void setAppointType(String appointType) {
        this.appointType = appointType;
    }

	/*
public String getParamVal() {
		return paramVal;
	}

	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}
*/

    public net.sf.json.JSONObject getDayJson() {
        return dayJson;
    }

    public void setDayJson(net.sf.json.JSONObject dayJson) {
        this.dayJson = dayJson;
    }
}
