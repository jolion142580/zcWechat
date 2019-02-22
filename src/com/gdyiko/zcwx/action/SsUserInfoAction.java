package com.gdyiko.zcwx.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gdyiko.tool.AesEncryptUtils;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.OAuth;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;
import com.opensymphony.xwork2.ActionContext;

//@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "ssUserInfo", results = {
//成功
        @Result(name = "success", location = "/userList.jsp"),

        // 失败
        @Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class SsUserInfoAction extends BaseAction<SsUserInfo, String> {


    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    HttpSession session = null;
    HttpServletRequest request = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式

    //	private String code;
    private String random_num;
    private String random_sms;




    private List<SsUserInfo> ssUserList;


    public SsUserInfoAction() {
        request = ServletActionContext.getRequest();
        session = ServletActionContext.getRequest().getSession();

    }

    private static final long serialVersionUID = -7209385373170290085L;
    @Resource(name = "ssUserInfoService")
    SsUserInfoService ssUserInfoService;

    @Resource(name = "ssUserInfoService")
    @Override
    public void setGenericService(GenericService<SsUserInfo, String> genericService) {
        // TODO Auto-generated method stub
        super.setGenericService(genericService);
    }

    public String save() {

        try {
            String rand = (String) ActionContext.getContext().getSession().get("numRandom");//对照验证码
            if (random_num == null || !random_num.equals(rand)) {
                String result = "{\"flag\":\"fail\",\"message\":\"验证码错误！\"}";
                JSONObject jo = new JSONObject(result);
                Struts2Utils.renderText(jo.toString());
                return "";
            }
            String smscode = (String) ActionContext.getContext().getSession().get("smscode");//对照验证码
//			System.out.println(smscode+";:::::::"+random_sms);
            if (random_sms == null || smscode == null || !smscode.equals(random_sms)) {
                String result = "{\"flag\":\"fail\",\"message\":\"短信验证码错误！\"}";
                JSONObject jo = new JSONObject(result);
                Struts2Utils.renderText(jo.toString());
                return "";
            }

            System.out.println("---openid----" + this.model.getId());
//			this.model.setId(this.model.getOpenid());
            System.out.println("===============idCard===============" + this.model.getIdCard());
            this.model.setIdCard(AesEncryptUtils.decrypt(this.model.getIdCard(), AesEncryptUtils.KEY));
            System.out.println("===============idCard===============" + this.model.getIdCard());
            this.model.setPhone(AesEncryptUtils.decrypt(this.model.getPhone(), AesEncryptUtils.KEY));
            this.model.setCreattime(df.format(new java.util.Date()));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.save();

    }

	/*public String modify(){

		try {
			String rand=(String) ActionContext.getContext().getSession().get("numRandom");//对照验证码
//			System.out.println(random_num+";:::::::"+rand);
//			System.out.println("myid..123.."+this.myid);
			if(!random_num.equals(rand)||random_num==null){
				String result="{\"flag\":\"fail\",\"message\":\"验证码错误！\"}";
				JSONObject jo = new JSONObject(result);
				Struts2Utils.renderText(jo.toString());
				return "";
			}
			String smscode=(String) ActionContext.getContext().getSession().get("smscode");//对照验证码
//			System.out.println(smscode+";:::::::"+random_sms);
			if(!smscode.equals(random_sms)||random_sms==null){
				String result="{\"flag\":\"fail\",\"message\":\"短信验证码错误！\"}";
				JSONObject jo = new JSONObject(result);
				Struts2Utils.renderText(jo.toString());
				return "";
			}
			System.out.println("---openid----"+this.model.getId());
//			this.model.setId(this.model.getOpenid());
			this.model.setCreattime(df.format(new java.util.Date()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return super.modify();
	}*/

    public String findByOpenId() {

        try {
            System.out.println("【fdsafdsfds】");
            System.out.println("----【查找人员，id为】---"+this.model.getId());

            SsUserInfo ssUserInfo = ssUserInfoService.findById(this.model.getId());
            //System.out.println("----"+ssUserInfo);
            if (ssUserInfo != null) {
                JSONObject jo = new JSONObject(ssUserInfo);
                Struts2Utils.renderText(jo.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findByuserOpenId() {

        try {
            System.out.println("----【查找人员，id为】---"+this.model.getId());

            SsUserInfo ssUserInfo = ssUserInfoService.findById(this.model.getId());
            //System.out.println("----"+ssUserInfo);
            if (ssUserInfo != null) {
                JSONObject jo = new JSONObject(ssUserInfo);
                Struts2Utils.renderText(jo.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findById() {
        String openid = (String) this.session.getAttribute("openid");
        System.out.println(openid);
        SsUserInfo ssUserInfo = ssUserInfoService.findById(openid);

        if (ssUserInfo != null) {
            String res = "{\"success\":\"true\"}";
            Struts2Utils.renderJson(res);
        }
        return null;

    }

	/*public String userlist(){
        //System.out.println("--openid--"+this.model.getOpenid());
		ssUserList= ssUserInfoService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
		 ActionContext.getContext().put("ssUserList", ssUserList);
		return "success";
	}*/

    public String getRandom_num() {
        return random_num;
    }

    public void setRandom_num(String random_num) {
        this.random_num = random_num;
    }




    public String getRandom_sms() {
        return random_sms;
    }

    public void setRandom_sms(String random_sms) {
        this.random_sms = random_sms;
    }


}
