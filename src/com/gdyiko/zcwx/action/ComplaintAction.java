package com.gdyiko.zcwx.action;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.UserApi;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.Complaint;
import com.gdyiko.zcwx.service.ComplaintService;

import net.sf.json.JSONArray;

import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;

@Namespace("/")
@Action(value = "complaint", results = {@Result(name = "success", location = "/"),
        @Result(name = "complaintpage", location = "/complaint.jsp"), // 获取投诉页面
        @Result(name = "submit", location = "/submitcomplaint.jsp"), // 获取留言页面
        // 失败
        @Result(name = "fail", location = "/")})

public class ComplaintAction extends BaseAction<Complaint, String> {
    @Resource(name = "complaintService")
    ComplaintService complaintService;
    @Resource(name = "ssUserInfoService")
    SsUserInfoService ssUserInfoService;

    private SsUserInfo ssUserInfo;

    //留言时间
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final long serialVersionUID = -8210055429850148097L;
    HttpSession session = null;
    HttpServletRequest request = null;


    public ComplaintAction() {
        request = ServletActionContext.getRequest();
        session = ServletActionContext.getRequest().getSession();
    }

    @Resource(name = "complaintService")
    public void setGenericService(GenericService<Complaint, String> genericService) {
        // TODO Auto-generated method stub
        super.setGenericService(genericService);
    }


    public String getComplaintPage(){
        ssUserInfo = UserApi.getUserInfo();
        return "complaintpage";
    }

    public String getsubmitPage(){
        ssUserInfo = UserApi.getUserInfo();
        return "submit";
    }

    public String findLikeByEntitypy()
            throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        this.model.setComplaint_Show("是");
        ssUserInfo = UserApi.getUserInfo();
        String openid =ssUserInfo.getId();
//            openid取消使用查询全部留言
        try {
            List<Complaint> list = complaintService.findByConditionShow(openid, "是");
            this.model.setComplaintReplyTime("orderby_desc_");
            JSONObject jo = new JSONObject(list);
            String json = JSONArray.fromObject(list).toString();
            Struts2Utils.renderText(json.toString());
        } catch (RuntimeException e) {
            throw new RuntimeException("ComplaintAction->findLikeByEntitypy openid 不存在");
        }
        return null;
    }

    public String save() {
        ssUserInfo = UserApi.getUserInfo();
        String openid =ssUserInfo.getId();
        if (openid != null) {
            SsUserInfo ssUserInfo = ssUserInfoService.findById(openid);
            String phone = ssUserInfo.getPhone();
            complaintService.saveByElement(this.model.getComplaint_Content(), phone,
                    format.format(new Date()), this.model.getComplaint_Num(), this.model.getComplaint_Title(),
                    this.model.getComplaint_Status(), this.model.getComplaint_Show(), openid);
            System.out.println(this.model.getComplaint_Show());
            System.out.println(this.model.getComplaint_Content());
        } else {
            throw new RuntimeException("compliaintAction->save openid不存在");
        }
      /*
        this.model.setComplaint_Content(this.model.getComplaint_Content());
        complaintService.save(this.model);*/
        return null;
    }

    public SsUserInfo getSsUserInfo() {
        return ssUserInfo;
    }

    public void setSsUserInfo(SsUserInfo ssUserInfo) {
        this.ssUserInfo = ssUserInfo;
    }
}
