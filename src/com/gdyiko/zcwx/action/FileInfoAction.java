package com.gdyiko.zcwx.action;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.FileInfo;
import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsAffairs;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.zcwx.service.FileInfoService;
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
@Action(value = "fileInfo", results = {
//成功
        @Result(name = "affairGuide", location = "/"),

        @Result(name = "affair", location = "/"),
        // 失败
        @Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class FileInfoAction extends BaseAction<FileInfo, String> {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    HttpSession session = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式


    @Resource(name = "fileInfoService")
    FileInfoService fileInfoService;

    @Resource(name = "fileInfoService")
    @Override
    public void setGenericService(GenericService<FileInfo, String> genericService) {
        // TODO Auto-generated method stub
        super.setGenericService(genericService);
    }

    public FileInfoAction() {

    }


    public void showImgByWeb() {
        String id = this.model.getId();
//		System.out.println("pc端显示图片id--:"+id);
        HttpServletResponse response = ServletActionContext.getResponse();//struts2获取response
        if (id != null && !"".equals(id)) {
            FileInfo fileInfo = fileInfoService.findById(id);
            String localpath = fileInfo.getLocalpath();
            FileInputStream is;
            try {
                is = new FileInputStream(localpath);
                int i = is.available(); // 得到文件大小
                byte data[] = new byte[i];
                is.read(data); // 读数据
                is.close();
                response.setContentType("image/*"); // 设置返回的文件类型
                OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
                toClient.write(data); // 输出数据
                toClient.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
