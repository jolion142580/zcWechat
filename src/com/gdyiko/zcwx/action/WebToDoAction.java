package com.gdyiko.zcwx.action;

import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.zcwx.po.FileInfo;
import com.gdyiko.zcwx.po.OnlineApply;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.po.resp.Token;
import com.gdyiko.zcwx.service.FileInfoService;
import com.gdyiko.zcwx.service.OnlineApplyService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.CookieUtil;
import com.gdyiko.zcwx.weixinUtils.TokenHepl;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.ApplicationContext;
import org.springside.modules.web.struts2.Struts2Utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Namespace("/")
@Action(value = "web", results = {
        // 认证成功
        @Result(name = "success", location = "/web/mainPage.jsp"),
        //查看历史
        @Result(name = "history", location = "/web/history.jsp"),
        //查看历史
        @Result(name = "newHistory", location = "/web/newHistory.jsp"),
        //查看上传资料
        @Result(name = "information", location = "/web/information.jsp"),
        //登录信息
        @Result(name = "logInfo", location = "/web/logInfo.jsp"),
        //我要留言
        @Result(name = "complaint", location = "/web/complaint.jsp"),
        //办件跟踪
        @Result(name = "affairProgress", location = "/web/affairProgress.jsp"),
        //联系方式
        @Result(name = "map", location = "/web/map.jsp"),
        //查看上传资料
        @Result(name = "showInformation", location = "/web/showInformation.jsp"),
        // 主页认证失败
//        @Result(name = "fail", location = "/web/Index.jsp"),
        @Result(name = "fail", location = "/web/mainPage.jsp"),
        // 认证失败
        @Result(name = "skip", location = "/web/skip.jsp")

})
public class WebToDoAction extends BaseAction<SsUserInfo, String> {
    public static final long cacheTime = 1000 * 60 * 30; //30分钟
    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    HttpSession session = null;

    @Resource(name = "onlineApplyService")
    private OnlineApplyService onlineApplyService;
    @Resource(name = "fileInfoService")
    private FileInfoService fileInfoService;
    @Resource(name = "ssUserInfoService")
    private SsUserInfoService ssUserInfoService;

    /**
     * showInformation.jsp应用
     */
    private OnlineApply onlineApply;

    /**
     * showInformation.jsp应用 记录事项上传资料
     */
    private List<Map<String, List<FileInfo>>> fileList;
    /**
     * 记录登录人信息 __logInfo.jsp应用
     */
    private SsUserInfo ssUserInfo;
    /**
     * 记录上传身份证正反面__logInfo.jsp&information.jsp(遗弃)应用
     */
    private List<FileInfo> cardList;
    /**
     * 办理事项记录__newHistory.jsp应用
     */
    List<Map<String, String>> onlineApplyList;

    /**
     * 办理事项记录__history.jsp(遗弃)应用
     */
    private List<Map<String, List<Map<String, String>>>> historyToList;

    /**
     * 上传资料记录__information.jsp(遗弃)应用
     */
    private List<Map<String, List<Map<String, List<Map<String, String>>>>>> informationToList;

    public WebToDoAction() {
        session = ServletActionContext.getRequest().getSession();
    }

    /**
     * 测试用户登录
     */
    public void demoLogin() {
        String openid = "oEyt000uPffVya2HvQVqTUEX4_OQ";
        String uuid = (String) session.getAttribute("uuid");
        session.setAttribute("openid", openid);
        ActionContext.getContext().getApplication().put(uuid, openid);
        Token token = TokenHepl.getaccessToken();
        token.setBegin_time(System.currentTimeMillis());
        session.setAttribute("token", token);
//        System.out.println("sessionID_0:"+session.getId());
    }


    //pc登录
    public String login() {
        String result = vail();
        return result;
    }

    public String logout() {
        String result = vail();
        clearSession();
        try {
            CookieUtil.removeCookie("user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //办件跟踪
    public String affairProgress() {
        return "affairProgress";
    }

    //留言
    public String complaint() {
        return "complaint";
    }

    //联系方式
    public String map() {
        return "map";
    }

    //历史记录
    public String newHistory() {
        String result = vail();
        if (result.equals("fail")) {
            clearSession();
            return "skip";
        }
        String uuid = (String) session.getAttribute("uuid");
        String openid = (String) ActionContext.getContext().getApplication().get(uuid);
        OnlineApply onlineApply = new OnlineApply();
        onlineApply.setOpenid(openid);
        onlineApplyList = onlineApplyService.listByOpenId(openid);
        return "newHistory";
    }

    //历史记录 --> 事项记录
    public String showInformation() {
        String onlineApplyId = this.model.getId();
        onlineApply = onlineApplyService.findById(onlineApplyId);
        fileList = fileInfoService.listByOnlineApply(onlineApplyId);
        return "showInformation";
    }

    //个人信息
    public String logInfo() {
        String result = vail();
        if (result.equals("fail")) {
            clearSession();
            return "skip";
        }
        String uuid = (String) session.getAttribute("uuid");
        String openid = (String) ActionContext.getContext().getApplication().get(uuid);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOpenid(openid);
        fileInfo.setRemark("身份证正反面");
        cardList = fileInfoService.findLikeByEntity(fileInfo, BeanUtilEx.getNotNullPropertyNames(fileInfo));
        ssUserInfo = ssUserInfoService.findById(openid);
        return "logInfo";
    }

    //检测token
    public void vailToken() {
        Boolean result = false;
        String openid = null;
        Token token = (Token) session.getAttribute("token");
        if (token != null) {
            long curTime = System.currentTimeMillis();
            System.out.println("当前时间----：" + format.format(curTime));
            System.out.println("Token过期时间----：" + format.format(token.getBegin_time() + cacheTime));
            if (curTime - token.getBegin_time() <= cacheTime) {
                String uuid = (String) session.getAttribute("uuid");
                openid = (String) ActionContext.getContext().getApplication().get(uuid);
                if (openid != null && !openid.equals("")) {
                    result = true;//有效
                }
            }
        }
        Struts2Utils.renderText(getResultJson(result.toString()));
    }


    //检测事项提交
    public void vailOnlineApply() {
        OnlineApply onlineApply = onlineApplyService.findById(this.myid);
        if (onlineApply == null) {
            Struts2Utils.renderText("{\"flag\":\"0\",\"Msg\":\"验证失败\"}");
            return;
        }
        String state = onlineApply.getState();
//        if (state.equals("待资料上传") || state.equals("STATE")) {
        if (onlineApply.getApprovedOrNot() == null) { //未审核的 验证避免用户修改参数
            Struts2Utils.renderText(JSONObject.fromObject(onlineApply).toString());
            return;
        }
//        }
        Struts2Utils.renderText("{\"flag\":\"0\",\"Msg\":\"请刷新界面再试一次\"}");
        return;
    }

    //上传图片记录 //已关闭
    public String information() {
        String result = vail();
        if (result == "fail") {
            clearSession();
            return "skip";
        }
        String uuid = (String) session.getAttribute("uuid");
        String openid = (String) ActionContext.getContext().getApplication().get(uuid);
        informationToList = fileInfoService.informationToList(openid);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOpenid(openid);
        fileInfo.setRemark("身份证正反面");
        cardList = fileInfoService.findLikeByEntity(fileInfo, BeanUtilEx.getNotNullPropertyNames(fileInfo));
        return "information";
    }

    //办事记录 //已关闭
    public String history() {
        String result = vail();
        if (result.equals("fail")) {
            clearSession();
            return "skip";
        }
        String uuid = (String) session.getAttribute("uuid");
        String openid = (String) ActionContext.getContext().getApplication().get(uuid);
        historyToList = onlineApplyService.listByOpenIdToList(openid);
        return "history";
    }

    private void clearSession() {
        if (session == null) {
            return;
        }
        String uuid = (String) session.getAttribute("uuid");
        Enumeration em = session.getAttributeNames();
        while (em.hasMoreElements()) {
            session.removeAttribute(em.nextElement().toString());
        }
        if (uuid != null) {
            ActionContext.getContext().getApplication().remove(uuid);
        }
    }

    private String vail() {
//        System.out.println("sessionID_-1:"+session.getId());
        Token token = (Token) session.getAttribute("token");
        if (token != null) {
            Long curTime = System.currentTimeMillis();
            if (curTime - token.getBegin_time() >= cacheTime) {
                return "fail";
            }
        }
        String uuid = (String) session.getAttribute("uuid");
        if (uuid == null || uuid.equals("")) {
            return "fail";
        }
        String openid = (String) ActionContext.getContext().getApplication().get(uuid);
        if (openid == null || openid.equals("")) {
            return "fail";
        }
        return "success";
    }


    public List<Map<String, List<Map<String, String>>>> getHistoryToList() {
        return historyToList;
    }

    public List<FileInfo> getCardList() {
        return cardList;
    }

    public List<Map<String, List<Map<String, List<Map<String, String>>>>>> getInformationToList() {
        return informationToList;
    }

    public SsUserInfo getSsUserInfo() {
        return ssUserInfo;
    }

    public List<Map<String, String>> getOnlineApplyList() {
        return onlineApplyList;
    }

    public OnlineApply getOnlineApply() {
        return onlineApply;
    }

    public List<Map<String, List<FileInfo>>> getFileList() {
        return fileList;
    }
}
