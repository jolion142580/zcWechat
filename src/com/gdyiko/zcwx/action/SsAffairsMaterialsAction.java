package com.gdyiko.zcwx.action;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.SsAffairMaterials;
import com.gdyiko.zcwx.po.SsAffairObject;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.SsAffairsMaterialsService;
import com.gdyiko.zcwx.service.SsAffairsService;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.weixinUtils.OAuth;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;


//@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "ssAffairsMaterialsInfo", results = {
//成功
        @Result(name = "success", location = "/affairMaterials.jsp"),

        @Result(name = "notPermitted", type = "redirect", location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${APPID}&redirect_uri=${weChatDNSURL}relation.jsp?type=affairMaterials&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect"),

        @Result(name = "affair", location = "/affairs.jsp"),
        // 失败
        @Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class SsAffairsMaterialsAction extends BaseAction<SsAffairMaterials, String> {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    HttpSession session = null;
    HttpServletRequest request = null;
    HttpServletResponse response = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式

    private List<SsAffairMaterials> ssAffairMaterialList;

    private String APPID;
    private String weChatDNSURL;

    @Resource(name = "ssUserInfoService")
    SsUserInfoService ssUserInfoService;

    @Resource(name = "ssAffairsService")
    SsAffairsService ssAffairsService;

    @Resource(name = "ssAffairsMaterialsService")
    SsAffairsMaterialsService ssAffairsMaterialsService;

    @Resource(name = "ssAffairsMaterialsService")
    @Override
    public void setGenericService(GenericService<SsAffairMaterials, String> genericService) {
        // TODO Auto-generated method stub
        super.setGenericService(genericService);
    }

    public SsAffairsMaterialsAction() {
        request = ServletActionContext.getRequest();
        session = ServletActionContext.getRequest().getSession();
        response = ServletActionContext.getResponse();
    }

    private static final long serialVersionUID = -7209385373170290085L;


    public String findMaterialsByAffairid() {
        this.model.setMatindex("likeand%3A" + this.model.getMatindex());
        this.model.setAffairid("equals%3A" + this.model.getAffairid());
        this.model.setValid("1");
        //System.out.println("======="+this.model.getMatindex());
        ssAffairMaterialList = ssAffairsMaterialsService.findLikeByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));

        //ssAffairMaterialList = ssAffairsMaterialsService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));

        JSONArray ja = JSONArray.fromObject(ssAffairMaterialList);
        Struts2Utils.renderText(ja.toString());
        return null;
    }

    public void download() throws IOException {
        String id = request.getParameter("id");
        SsAffairMaterials ssAffairMaterials = ssAffairsMaterialsService.findById(id);
        if (ssAffairMaterials != null) {
            if (ssAffairMaterials.getLocalpath() != null && !ssAffairMaterials.getLocalpath().equals("")) {
                //1.获取要下载的文件的绝对路径
                String realPath = ssAffairMaterials.getLocalpath();
                File file = new File(realPath);
                if (!file.exists()) {
                    return;
                }

                System.out.println("===============realPath=============" + realPath);
                //2.获取要下载的文件名
                String fileName = realPath.substring(realPath.lastIndexOf("/") + 1);

                System.out.println("===============fileName=============" + fileName);


                response.reset();
                response.setContentType("application/octet-stream");
                //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                InputStream in = new FileInputStream(realPath);//获取文件输入流
                int len = 0;
                byte[] buffer = new byte[1024];
                OutputStream out = response.getOutputStream();
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);//将缓冲区的数据输出到客户端浏览器
                }
                in.close();
            }
        }
    }


    public List<SsAffairMaterials> getSsAffairMaterialList() {
        return ssAffairMaterialList;
    }

    public void setSsAffairMaterialList(List<SsAffairMaterials> ssAffairMaterialList) {
        this.ssAffairMaterialList = ssAffairMaterialList;
    }

    public String getAPPID(){return APPID;}

    public void setAPPID(String APPID){this.APPID = APPID;}

    public String getWeChatDNSURL() {
        return weChatDNSURL;
    }

    public void setWeChatDNSURL(String weChatDNSURL) {
        this.weChatDNSURL = weChatDNSURL;
    }
}
