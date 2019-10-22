package com.gdyiko.zcwx.action;

import com.gdyiko.tool.AesEncryptUtils;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.PrimaryProduce;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;
import com.gdyiko.zcwx.po.FileInfo;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.FileInfoService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.CookieUtil;
import com.gdyiko.zcwx.weixinUtils.UserApi;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSON;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import org.springside.modules.web.struts2.Struts2Utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Namespace("/")
@Action(value = "ssUserInfoNoLogin", results = {
//成功
       // @Result(name = "success", location = "/userList.jsp"),
        @Result(name = "success", location = "/userInfo.jsp"),
        @Result(name="scan",location = "/scanConfirm.jsp"),
        // 失败
        @Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class SsUserInfoNoLoginAction extends BaseAction<SsUserInfo, String> {


    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    HttpSession session = null;
    HttpServletRequest request = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式

    //	private String code;
    private String random_num;
    private String random_sms;

    private String uuid;
    private String sid;

    private SsUserInfo userInfo;

    private List<SsUserInfo> ssUserList;


    public SsUserInfoNoLoginAction() {
        request = ServletActionContext.getRequest();
        session = ServletActionContext.getRequest().getSession();
        userInfo= UserApi.getUserInfo();
    }

    private static final long serialVersionUID = -7209385373170290085L;
    @Resource(name = "ssUserInfoService")
    SsUserInfoService ssUserInfoService;

    @Resource(name = "fileInfoService")
    FileInfoService fileInfoService;

    @Resource(name = "ssUserInfoService")
    @Override
    public void setGenericService(GenericService<SsUserInfo, String> genericService) {
        // TODO Auto-generated method stub
        super.setGenericService(genericService);
    }

    //查询用户
    public void getUser(){
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(userInfo);
        Struts2Utils.renderText(jsonObject.toString());
    }

    public String save() {

        try {
            String rand = (String) ActionContext.getContext().getSession().get("numRandom");//对照验证码
            if (random_num == null || !random_num.equals(rand)) {
                String result = "{\"flag\":\"fail\",\"message\":\"验证码错误！\"}";
                JSONObject jo = new JSONObject(result);
                Struts2Utils.renderText(jo.toString());
                return null;
            }
            String smscode = (String) ActionContext.getContext().getSession().get("smscode");//对照验证码
//			System.out.println(smscode+";:::::::"+random_sms);
           /* if (random_sms == null || smscode == null || !smscode.equals(random_sms)) {
                String result = "{\"flag\":\"fail\",\"message\":\"短信验证码错误！\"}";
                JSONObject jo = new JSONObject(result);
                Struts2Utils.renderText(jo.toString());
                return "";
            }*/

            System.out.println("---openid----" + this.model.getId());
            System.out.println("===============idCard===============" + this.model.getIdCard());
            this.model.setIdCard(AesEncryptUtils.decrypt(this.model.getIdCard(), AesEncryptUtils.KEY));
            System.out.println("===============idCard===============" + this.model.getIdCard());
            this.model.setPhone(AesEncryptUtils.decrypt(this.model.getPhone(), AesEncryptUtils.KEY));
            List<SsUserInfo> list = null;
            SsUserInfo checkuser = new SsUserInfo();
            checkuser.setName(this.model.getName());
            checkuser.setIdCard(this.model.getIdCard());

            list = ssUserInfoService.findLikeByEntity(checkuser, BeanUtilEx.getNotNullPropertyNames(checkuser));
            net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

            if (list!=null&&list.size()>0){
                jsonObject.put("flag",FLAG_FAIL);
                jsonObject.put("message","用户已存在！");
                Struts2Utils.renderText(jsonObject.toString());
                return null;
            }
            checkuser.setPhone(this.model.getPhone());
            list = ssUserInfoService.findLikeByEntity(checkuser, BeanUtilEx.getNotNullPropertyNames(checkuser));
            if (list!=null&&list.size()>0){
                jsonObject.put("flag",FLAG_FAIL);
                jsonObject.put("message","用户已存在！");
                Struts2Utils.renderText(jsonObject.toString());
                return null;
            }


            this.model.setCreattime(df.format(new java.util.Date()));

            if (this.model.getId() == null || "".equals(this.model.getId())) {
                this.model.setId(PrimaryProduce.produce());
            }
                ssUserInfoService.save(model);
                ActionContext.getContext().getSession().put("user", model);
                CookieUtil.addCookie("user", net.sf.json.JSONObject.fromObject(model).toString());
             //   System.out.println(getResultJson(FLAG_SUCCESS));

                //绑定文件拥有者
                String[] files = this.model.getImgfiles().split(",");
                for (String fileId : files) {
                    if (!fileId.equals("")){
                        FileInfo fileInfo = fileInfoService.findById(fileId);
                        fileInfo.setOpenid(this.model.getId());
                        fileInfoService.save(fileInfo);
                    }
                }

                Struts2Utils.renderText(getResultJson(FLAG_SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
            Struts2Utils.renderText(getResultJson(FLAG_FAIL));
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

    public SsUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(SsUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
