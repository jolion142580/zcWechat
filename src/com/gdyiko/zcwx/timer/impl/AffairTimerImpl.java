package com.gdyiko.zcwx.timer.impl;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.PrimaryProduce;
import com.gdyiko.zcwx.po.*;
import com.gdyiko.zcwx.service.*;
import com.gdyiko.zcwx.timer.AffairTimer;
import com.gdyiko.zcwx.weixinUtils.HttpContent;
import net.sf.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


//@Component
//@Lazy(value=false)
@Service
public class AffairTimerImpl implements AffairTimer {

    @Resource(name = "propertieService")
    PropertieService propertieService;

    @Resource(name = "ssBaseDicService")
    SsBaseDicService ssBaseDicService;

    @Resource(name = "ssAffairsService")
    SsAffairsService ssAffairsService;

    @Resource(name = "ssAffairsGuideService")
    SsAffairsGuideService ssAffairsGuideService;

    @Resource(name = "ssAffairsObjectService")
    SsAffairsObjectService ssAffairsObjectService;

    @Resource(name = "ssAffairsMaterialsService")
    SsAffairsMaterialsService ssAffairsMaterialsService;


    /*
    1 Seconds (0-59) 
    2 Minutes (0-59) 
    3 Hours (0-23) 
    4 Day of month (1-31) 
    5 Month (1-12 or JAN-DEC) 
    6 Day of week (1-7 or SUN-SAT) 
    7 Year (1970-2099) 
    取值：可以是单个值，如6； 
        也可以是个范围，如9-12； 
        也可以是个列表，如9,11,13 
        也可以是任意取值，使用* 
*/
//0 * * * * * 代表每分钟执行一次  
/* 
    2011-09-07 09:23:00 
    2011-09-07 09:24:00 
    2011-09-07 09:25:00 
    2011-09-07 09:26:00 
 */
//	@Scheduled(cron = "0/59 * *  * * ? ")
//    @Scheduled(cron = "0 0/1 * * * ?")
    //@Scheduled(cron = "0 0 2 * * ? ")//每天凌晨2点
    public void timerJob() {
        try {

            String affairsURL = propertieService.getPropertie("affairsURL");

//			 System.out.println("当前时间:"+new Date().toString());
            //获取部门;
            //updateBaseDic(affairsURL+"getDeparts");
            //获取事项主题分类
            //updateBaseDic(affairsURL+"getAffairSorts");

            //获取事项
            // updateAffairs(affairsURL + "getAffairs");

            //获取办事指南
//            updateAffairGuide(affairsURL+"getAffairGuide&affair_id=");

            //获取事项的对象
            //updateAffairObject(affairsURL+"getObjsByAffair&affair_id=");

            //根据事项对象获取材料
//            updateMaterialsByAffair(affairsURL + "getMaterialsByAffair&affair_id=");
//

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void updateBaseDic(String url) throws JSONException {
        System.out.println("==============================================开始执行");
        HttpContent httpContent = new HttpContent();
        String result = httpContent.getHttpContent(url, "", "", "GET");

        JSONObject jo = new JSONObject(result);


        //System.out.println("------");
        //if(jo.get("Result").equals("1")){
        //System.out.println("1111111:::"+jo.get("Data"));

        //JSONObject subjo =new JSONObject(jo.get("data").toString());
        //System.out.println("22222:::"+subjo.get("D001"));
        JSONArray subJo = JSONArray.fromObject(jo.get("data").toString());
        for (int i = 0; i < subJo.size(); i++) {
            net.sf.json.JSONObject ob = (net.sf.json.JSONObject) subJo.get(i);
            //JSONObject ob =  (JSONObject) subJo.get(i);
            //System.out.println("33333:::::"+ob.get("depart_name"));
            SsBaseDic ssBaseDic = new SsBaseDic();
            ssBaseDic.setId(PrimaryProduce.produce());
            ssBaseDic.setBaseDicId(ob.get("id").toString().equals("null") ? "" : ob.get("id").toString());
            if (url.indexOf("getDeparts") != -1) {
                ssBaseDic.setBaseDicType("depart");
                ssBaseDic.setCname(ob.get("depart_name").toString().equals("null") ? "" : ob.get("depart_name").toString());
            }
            if (url.indexOf("getAffairSorts") != -1) {
                ssBaseDic.setBaseDicType("life");
                ssBaseDic.setCname(ob.get("sort_name").toString().equals("null") ? "" : ob.get("sort_name").toString());
            }


            SsBaseDic ssBaseDic2 = ssBaseDicService.findById(ob.get("id").toString().equals("null") ? "" : ob.get("id").toString());
            if (ssBaseDic2 != null) {
                ssBaseDic.setIconPath(ssBaseDic2.getIconPath());
            }

            ssBaseDicService.save(ssBaseDic);

        }
        System.out.println("====================================================执行结束");
    }

    public void updateAffairs(String url) throws JSONException {
        String flag = "";
        HttpContent httpContent = new HttpContent();
        String result = httpContent.getHttpContent(url, "", "", "get");
        //System.out.println("--------"+result);
        //String result="{"id":"101","departid":"6","level":10,"timelimit_type":1,"timelimit":9,"sortid":"14","result_form":null,"isnet":null,"code":"HB5065","affairname":"防治污染设施拆除或者闲置的审批","istodo":2}";
        JSONObject jo = new JSONObject(result);

        JSONArray subJo = JSONArray.fromObject(jo.get("data").toString());

        try {


            //System.out.println("-=-=-=-"+subJo.size());
            for (int i = 0; i < subJo.size(); i++) {
                net.sf.json.JSONObject ob = (net.sf.json.JSONObject) subJo.get(i);
                //JSONObject ob =  (JSONObject) subJo.get(i);
                System.out.println("33333:::::" + ob.get("id") + "------" + ob.get("affairname"));
                String affairid = ob.get("id").toString().equals("null") ? "" : ob.get("id").toString();
                String departid = ob.get("departid").toString().equals("null") ? "" : ob.get("departid").toString();
                String level = ob.get("level").toString().equals("null") ? "" : ob.get("level").toString();
                String timelimit_type = ob.get("timelimit_type").toString().equals("null") ? "" : ob.get("timelimit_type").toString();
                String timelimit = ob.get("timelimit").toString().equals("null") ? "" : ob.get("timelimit").toString();
                String sortid = ob.get("sortid").toString().equals("null") ? "" : ob.get("sortid").toString();
                String result_form = ob.get("result_form").toString().equals("null") ? "" : ob.get("result_form").toString();
                String isnet = ob.get("isnet").toString().equals("null") ? "" : ob.get("isnet").toString();
                String code = ob.get("code").toString().equals("null") ? "" : ob.get("code").toString();
                String affairname = ob.get("affairname").toString().equals("null") ? "" : ob.get("affairname").toString();
                String istodo = ob.get("istodo").toString().equals("null") ? "" : ob.get("istodo").toString();


                SsAffairs ssAffairs = ssAffairsService.findById(ob.get("id").toString().equals("null") ? "" : ob.get("id").toString());
                //System.out.println("-=-=="+ssAffairs.getAffairname());
                if (ssAffairs != null) {
                    //System.out.println("===-------=======");
                                  /*twolevelaffair=ssAffairs.getTwolevelaffair();
								  weight=ssAffairs.getWeight();
								  isdisplay=ssAffairs.getIsdisplay();
								  isnormal=ssAffairs.getIsnormal();
								  iscommon=ssAffairs.getIscommon();*/
                }

                SsAffairs addssAffairs = new SsAffairs();
                addssAffairs.setAffairid(affairid);
                addssAffairs.setDepartid(departid);
                addssAffairs.setLevel(level);
                addssAffairs.setTimelimitType(timelimit_type);
                addssAffairs.setTimelimit(timelimit);
                addssAffairs.setSortid(sortid);
                addssAffairs.setResultForm(result_form);
                addssAffairs.setIsnet(isnet);
                addssAffairs.setCode(code);
                addssAffairs.setAffairname(affairname);
                addssAffairs.setIstodo(istodo);

                ssAffairsService.save(addssAffairs);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        List<SsAffairs> affairsList = ssAffairsService.findAll();

        for (SsAffairs ssAffairs : affairsList) {
            flag = "false";
            for (int i = 0; i < subJo.size(); i++) {
                net.sf.json.JSONObject ob = (net.sf.json.JSONObject) subJo.get(i);
                if (ssAffairs.getAffairid().equals(ob.get("id").toString())) {
                    flag = "true";
                    break;
                }
            }
            if (flag.equals("false")) {
                ssAffairsService.remove(ssAffairs);
            }

        }


    }


    public void updateAffairGuide(String url) throws JSONException {
        System.out.println("===================================开始执行");
        HttpContent httpContent = new HttpContent();
        List<SsAffairs> list = ssAffairsService.findAll();

        for (SsAffairs ssAffairs : list) {
            String urls = url + ssAffairs.getAffairid();
            //System.out.println("-=-=-=-="+urls);
            String result = httpContent.getHttpContent(urls, "", "", "GET");


            if (result.length() > 0 && result.substring(0, 1).equals("{")) {
                JSONObject jo = new JSONObject(result);
                if (jo.has("data")) {
                    //System.out.println("1111111:::"+jo.get("Data"));
                    JSONObject subjo = new JSONObject(jo.get("data").toString());
                    //System.out.println("22222:::"+subjo.get("ACCORDING"));
                    String guideid = subjo.get("GUIDEID").toString().equals("null") ? "" : replace(subjo.get("GUIDEID").toString());
                    String affairid = subjo.get("AFFAIRID").toString().equals("null") ? "" : replace(subjo.get("AFFAIRID").toString());
                    String according = subjo.get("ACCORDING").toString().equals("null") ? "" : replace(subjo.get("ACCORDING").toString());
                    String procedures = subjo.get("PROCEDURES").toString().equals("null") ? "" : replace(subjo.get("PROCEDURES").toString());
                    String material = subjo.get("MATERIAL").toString().equals("null") ? "" : replace(subjo.get("MATERIAL").toString());
                    String institution = subjo.get("INSTITUTION").toString().equals("null") ? "" : replace(subjo.get("INSTITUTION").toString());
                    String accessorypath = subjo.get("ACCESSORYPATH").toString().equals("null") ? "" : replace(subjo.get("ACCESSORYPATH").toString());
                    String condition = subjo.get("CONDITION").toString().equals("null") ? "" : replace(subjo.get("CONDITION").toString());
                    String xrndomu = subjo.get("XRNDOMU").toString().equals("null") ? "" : replace(subjo.get("XRNDOMU").toString());
                    String site = subjo.get("SITE").toString().equals("null") ? "" : replace(subjo.get("SITE").toString());
                    String time = subjo.get("TIME").toString().equals("null") ? "" : replace(subjo.get("TIME").toString());
                    String inquire = subjo.get("INQUIRE").toString().equals("null") ? "" : replace(subjo.get("INQUIRE").toString());
                    String charge = subjo.get("CHARGE").toString().equals("null") ? "" : replace(subjo.get("CHARGE").toString());
                    String chargegist = subjo.get("CHARGEGIST").toString().equals("null") ? "" : replace(subjo.get("CHARGEGIST").toString());
                    String sepcialversion = subjo.get("SEPCIALVERSION").toString().equals("null") ? "" : replace(subjo.get("SEPCIALVERSION").toString());

                    SsAffairGuide ssAffairGuide = new SsAffairGuide(guideid, affairid, according, procedures, material, institution, accessorypath, condition, xrndomu, site, time, inquire, charge, chargegist, sepcialversion);

                    ssAffairsGuideService.save(ssAffairGuide);
                }
            }

        }

        /*List<SsAffairGuide> affairGuidesList = ssAffairsGuideService.findAll();
        for (SsAffairGuide ssAffairGuide2 : affairGuidesList) {
            String flag = "false";

            List<SsAffairs> ssAffairsList = ssAffairsService.findAll();

            for (SsAffairs ssAffairs : ssAffairsList) {
                String urls = url + ssAffairs.getAffairid();

                String result = httpContent.getHttpContent(urls, "", "", "GET");

                //System.out.println("--------"+result);

                JSONObject jo = new JSONObject(result);

                //System.out.println("------");

                JSONObject subjo = new JSONObject(jo.get("data").toString());
                if (ssAffairGuide2.getGuideid().equals(subjo.get("GUIDEID").toString())) {
                    flag = "true";
                    break;
                }

            }

            if (flag.equals("false")) {
                ssAffairsGuideService.remove(ssAffairGuide2);
            }
        }*/
        System.out.println("====================================================执行结束");
    }

    public void updateAffairObject(String url) throws JSONException {
        System.out.println("=================================开始执行");
        HttpContent httpContent = new HttpContent();
        List<SsAffairs> list = ssAffairsService.findAll();

        for (SsAffairs ssAffairs : list) {
            String urls = url + ssAffairs.getAffairid();
            //System.out.println("-=-=-=-="+urls);
            String result = httpContent.getHttpContent(urls, "", "", "GET");


            if (result.length() > 0 && result.substring(0, 1).equals("{")) {
                JSONObject jo = new JSONObject(result);
                if (jo.has("data")) {
                    //System.out.println("1111111:::"+jo.get("Data"));
                    JSONArray subJo = JSONArray.fromObject(jo.get("data").toString());
                    for (int i = 0; i < subJo.size(); i++) {
                        net.sf.json.JSONObject ob = (net.sf.json.JSONObject) subJo.get(i);

                        String objid = ob.get("OBJID").toString().equals("null") ? "" : replace(ob.get("OBJID").toString());
                        String objindex = ob.get("OBJINDEX").toString().equals("null") ? "" : replace(ob.get("OBJINDEX").toString());
                        String objname = ob.get("OBJNAME").toString().equals("null") ? "" : replace(ob.get("OBJNAME").toString());
                        String affairid = ssAffairs.getAffairid();

                        SsAffairObject ssAffairObject = new SsAffairObject(objid, objindex, objname, affairid);

                        ssAffairsObjectService.save(ssAffairObject);
                    }
                }
            }

        }


        List<SsAffairs> ssAffairsList = ssAffairsService.findAll();

        for (SsAffairs ssAffairs : ssAffairsList) {
            SsAffairObject ssAffairObject = new SsAffairObject();
            ssAffairObject.setAffairid(ssAffairs.getAffairid());
            List<SsAffairObject> affairObjectList = ssAffairsObjectService.findEqualByEntity(ssAffairObject, BeanUtilEx.getNotNullEscapePropertyNames(ssAffairObject));

            for (SsAffairObject ssAffairObject2 : affairObjectList) {
                String flag = "false";
                String urls = url + ssAffairs.getAffairid();
                //System.out.println("======"+urls);
                String result = httpContent.getHttpContent(urls, "", "", "GET");
                //System.out.println("------"+result);
                if (result.length() > 0 && result.substring(0, 1).equals("{")) {
                    JSONObject jo = new JSONObject(result);

                    JSONArray subJo = JSONArray.fromObject(jo.get("data").toString());
                    for (int i = 0; i < subJo.size(); i++) {
                        net.sf.json.JSONObject ob = (net.sf.json.JSONObject) subJo.get(i);

                        if (ssAffairObject2.getObjid().equals(ob.get("OBJID").toString())) {
                            flag = "true";
                            break;
                        }
                    }
                    if (flag.equals("false")) {
                        ssAffairsObjectService.remove(ssAffairObject2);
                    }
                }
            }

        }
        System.out.println("===========================================执行结束");

    }

    public void updateMaterialsByAffair(String url) throws JSONException {
        System.out.println("===========================================开始执行");

        HttpContent httpContent = new HttpContent();
        List<SsAffairObject> list = ssAffairsObjectService.findAll();

        for (SsAffairObject ssAffairObject : list) {
            String urls = url + ssAffairObject.getAffairid() + "&obj_index=" + ssAffairObject.getObjindex();
            System.out.println("-=-=-=-=" + urls);
            String result = httpContent.getHttpContent(urls, "", "", "GET");


            if (result.length() > 0 && result.substring(0, 1).equals("{")) {
                JSONObject jo = new JSONObject(result);
                if (jo.has("data")) {
                    //System.out.println("1111111:::"+jo.get("data"));
                    JSONArray subJo = JSONArray.fromObject(jo.get("data").toString());
                    for (int i = 0; i < subJo.size(); i++) {
                        net.sf.json.JSONObject ob = (net.sf.json.JSONObject) subJo.get(i);

                        String id = ob.get("ID").toString().equals("null") ? "" : replace(ob.get("ID").toString());
                        String affairid = ob.get("AFFAIRID").toString().equals("null") ? "" : replace(ob.get("AFFAIRID").toString());
                        String tableid = ob.get("TABLEID").toString().equals("null") ? "" : replace(ob.get("TABLEID").toString());
                        String examplepath = ob.get("EXAMPLEPATH").toString().equals("null") ? "" : replace(ob.get("EXAMPLEPATH").toString());
                        String istop = ob.get("ISTOP").toString().equals("null") ? "" : replace(ob.get("ISTOP").toString());
                        String imageNum = ob.get("IMAGE_NUM").toString().equals("null") ? "" : replace(ob.get("IMAGE_NUM").toString());
                        String matname = ob.get("MATNAME").toString().equals("null") ? "" : replace(ob.get("MATNAME").toString());
                        String remarks = ob.get("REMARKS").toString().equals("null") ? "" : replace(ob.get("REMARKS").toString());
                        String mattype = ob.get("MATTYPE").toString().equals("null") ? "" : replace(ob.get("MATTYPE").toString());
                        String imageInfo = ob.get("IMAGE_INFO").toString().equals("null") ? "" : replace(ob.get("IMAGE_INFO").toString());
                        String emptytablepath = ob.get("EMPTYTABLEPATH").toString().equals("null") ? "" : replace(ob.get("EMPTYTABLEPATH").toString());
                        String matgroup = ob.get("MATGROUP").toString().equals("null") ? "" : replace(ob.get("MATGROUP").toString());
                        String valid = ob.get("VALID").toString().equals("null") ? "" : replace(ob.get("VALID").toString());
                        String materialcode = ob.get("MATERIALCODE").toString().equals("null") ? "" : replace(ob.get("MATERIALCODE").toString());
                        String required = ob.get("REQUIRED").toString().equals("null") ? "" : replace(ob.get("REQUIRED").toString());
                        String matnumber = ob.get("MATNUMBER").toString().equals("null") ? "" : replace(ob.get("MATNUMBER").toString());
                        String ismust = ob.get("ISMUST").toString().equals("null") ? "" : replace(ob.get("ISMUST").toString());
                        String reusetypeid = ob.get("ReuseTypeID").toString().equals("null") ? "" : replace(ob.get("ReuseTypeID").toString());
                        String reusedetail = ob.get("ReuseDetail").toString().equals("null") ? "" : replace(ob.get("ReuseDetail").toString());
                        String matindex = ob.get("MATINDEX").toString().equals("null") ? "" : replace(ob.get("MATINDEX").toString());
                        String validdate = ob.get("VALIDDATE").toString().equals("null") ? "" : replace(ob.get("VALIDDATE").toString());

                        SsAffairMaterials ssAffairMaterials = new SsAffairMaterials(id, affairid, tableid, examplepath, istop, imageNum, matname, remarks, mattype, imageInfo, emptytablepath, matgroup, valid, materialcode, required, matnumber, ismust, reusetypeid, reusedetail, matindex, validdate);

                        ssAffairsMaterialsService.save(ssAffairMaterials);

                    }
                }
            }

        }
        System.out.println("================================================执行结束");

    }

    public String replace(String text) {
        return text.replace("\r\n", "<br>").replace("\"", "");
    }


}
