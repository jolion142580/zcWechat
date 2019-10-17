package com.gdyiko.zcwx.action;

import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;
import com.gdyiko.zcwx.po.SsBaseDic;
import com.gdyiko.zcwx.service.SsBaseDicService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Namespace("/")
@Action(value = "ssBaseDicInfo", results = {
//成功
        @Result(name = "success", location = "/department.jsp"),
        @Result(name = "web", location = "/web/banshi.jsp"),

        // 失败
        @Result(name = "fail", location = "/")
})

public class SsBaseDicAction extends BaseAction<SsBaseDic, String> {


    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    HttpSession session = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式

    private List<SsBaseDic> ssBaseDicsList;

    @Resource(name = "ssBaseDicService")
    SsBaseDicService ssBaseDicService;

    @Resource(name = "ssBaseDicService")
    @Override
    public void setGenericService(GenericService<SsBaseDic, String> genericService) {
        // TODO Auto-generated method stub
        super.setGenericService(genericService);
    }


    public SsBaseDicAction() {


    }

    private static final long serialVersionUID = -7209385373170290085L;

    public String findAllByBaseDicType() {
        //valid 1为有效，0为无效
        this.model.setBaseDicType("depart");
        this.model.setValid("1");
        ssBaseDicsList = ssBaseDicService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
        return "success";
    }

    public String test() {
        //valid 1为有效，0为无效
        int i = 0;
        System.out.println("fjsdlkjfsljdk");
        i = 3 / i;
//        throw new NullPointerException("fdsfds");
        return "success";
    }


    public String findAllByBaseDicTypeToWeb() {
        //valid 1为有效，0为无效
        this.model.setBaseDicType("depart");
        this.model.setValid("1");
        ssBaseDicsList = ssBaseDicService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
        return "web";
    }

    public String findAllByLeftItemToWeb() {
        //valid 1为有效，0为无效
        this.model.setBaseDicType("life");
        this.model.setValid("1");
        ssBaseDicsList = ssBaseDicService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
        return "web";
    }
    public String findAllByLeftItem() {
        //valid 1为有效，0为无效
        this.model.setBaseDicType("life");
        this.model.setValid("1");
        List<SsBaseDic> ssBaseDics = ssBaseDicService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
        //System.out.println("=========="+ssBaseDics.size());

        JSONArray ja = JSONArray.fromObject(ssBaseDics);
        Struts2Utils.renderText(ja.toString());
        return null;
    }


    public List<SsBaseDic> getSsBaseDicsList() {
        return ssBaseDicsList;
    }

    //@TypeConversion(rule = ConversionRule.COLLECTION, converter = "com.gdyiko.zcwx.po.SsBaseDic")
    public void setSsBaseDicsList(List<SsBaseDic> ssBaseDicsList) {
        this.ssBaseDicsList = ssBaseDicsList;
    }


}
