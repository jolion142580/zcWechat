package com.gdyiko.zcwx.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.gdyiko.base.service.WeChatPropertieService;
import com.gdyiko.tool.StringUtils;
import com.gdyiko.zcwx.po.SysUser;
import com.gdyiko.zcwx.service.SysUserService;
import com.gdyiko.zcwx.weixinUtils.*;
import net.sf.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.YuYuesDao;
import com.gdyiko.zcwx.po.YuYues;
import com.gdyiko.zcwx.service.InterfaceService;
import com.gdyiko.zcwx.service.YuYuesService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("yuYuesService")
public class YuYuesServiceImpl extends GenericServiceImpl<YuYues, String>
        implements YuYuesService {
    @Resource(name = "yuYuesDao")
    YuYuesDao yuYuesDao;

    @Resource(name = "weChatPropertieService")
    WeChatPropertieService weChatPropertieService;

    @Autowired
    SendMassageUtil sendMassageUtil;

    @Resource(name = "yuYuesDao")
    @Override
    public void setGenericDao(GenericDao<YuYues, String> genericDao) {
        super.setGenericDao(genericDao);
    }

    @Resource(name = "interfaceService")
    InterfaceService ifs;

    // 根据日期和street，获得8:30到17:30的剩余号数
//	public String getCount(String street, String ydate,String businessType,Integer weight) {
    public String getCount(String street, String ydate) {
        JSONObject data = new JSONObject();
        try {
            /*
             * String count = ifs.onlineQuery(street,ydate,"08:30","09:30");
             * JSONObject jsonObject = new JSONObject(count); JSONObject jo =
             * (JSONObject) jsonObject.get("data");
             * System.out.println(jo.get("count"));
             */
//            data.put("time0", handleJson(street, ydate, "08:30", "09:30"));
            data.put("time0", handleJson(street, ydate, "08:30", "09:30"));
            data.put("time1", handleJson(street, ydate, "09:30", "10:30"));
            data.put("time2", handleJson(street, ydate, "10:30", "11:30"));
            data.put("time3", handleJson(street, ydate, "14:30", "15:30"));
            data.put("time4", handleJson(street, ydate, "15:30", "16:30"));
//			data.put("time4", handleJson(street, ydate, "15:30", "16:30",businessType,weight));
//			data.put("time5", handleJson(street, ydate, "16:30", "17:30"));
            return data.toString();
            // return null;
        } catch (Exception e) {
            return "";
        }
    }

    // onlineQuery在onlineQuery返回的json中提取count
  /*  private String handleJson(String street, String ydate, String s_time,
                              String e_time, String businessType, int weight) throws ParseException { */
    private String handleJson(String street, String ydate, String s_time,
                              String e_time) throws ParseException {
        String result = "";
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date2 = format.parse(ydate + " " + s_time);
        long l = date.getTime() - date2.getTime();
        //  System.out.println("--时间差--"+l);
        JSONObject jsonObject = null;
        JSONObject jo = null;
        String count = "";
        try {
            if (l > 0) {
                result = "{\"data\":{\"count\":\"0\"}}";
            } else {
                // 返回报文
//					result = ifs.onlineQuery(street, ydate, s_time, e_time,businessType,weight);
                result = ifs.onlineQuery(street, ydate, s_time, e_time);
//                result = "{\"data\":{\"count\":\"1\"}}";
            }
            jsonObject = new JSONObject(result);
            jo = (JSONObject) jsonObject.get("data");
            count = jo.get("count").toString();
        } catch (Exception e) {

        }
        return count;
    }

    // 保存预约(需要做校验)
    public String saveYuYues(YuYues model) {
        String street = model.getStreet();
        String serviceType = model.getStype();
        String name = model.getName();
        String idCard = model.getIdcard();
        String phone = model.getPhone();
        String date = model.getYdate();
        String s_time = model.getYstime();
        String e_time = model.getYetime();
        String openid = model.getOpenid();
//		String businessName = model.getBusinessName();
//		int weight = model.getWeight();
        // 先校验
        String validate = null;
        try {
            validate = handleJson(street, date, s_time, e_time);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (validate == "" || Integer.valueOf(validate) < 1) {
            return "{\"msg\":\"该时段已经没有预约号了！\"}";
        }
        // 验证成功再预约
        String message = "";
        String result = "";
        String header = "";
        message = ifs.yuYues(street, serviceType, name, idCard, phone, date,
                s_time, e_time, openid, null, 0);


        // 返回data的值
        result = getJsonData(message);
        // 当天该用户已有预约 | 预约时段没号
        if (0 == net.sf.json.JSONObject.fromObject(result).getInt("code")) {
            System.out.println("time[" + new SimpleDateFormat("MM-dd HH:mm:ss").format(new Date()) + "] " +
                    "com.gdyiko.zcwx.service.impl.YuYuesServiceImpl " +
                    "line[147] output: -=> 【" + name + "】 当天已有预约！");
            return result;
        }

        //使用短信通知预约
        String bookNo = net.sf.json.JSONObject.fromObject(result).get("booking_no").toString();
        String msg = "地点：张槎街道行政服务中心,预约号：" + bookNo + ",预约时间：" + model.getYdate() + " " + model.getYstime() + "-" + model.getYetime() + "," +
                "您的预约已成功办理，请在预约时间内携带相关证件到现场取号处，\n" +
                "凭身份证或订单号（预约号）取号。如有问题可以咨询现场工作人员。\n" +
                "（若要取消预约请在预约记录中取消）。预约后不前来办事，累积达3次将会列入黑名单，当年不能再使用预约功能。";

        net.sf.json.JSONObject json = sendMassageUtil.sendSms(msg, phone);
        if (json.getString("flag").equals("1")) {
            System.out.println("网上预约信息模板推送成功");
        } else {
            throw new RuntimeException("网上预约信息模板推送失败");
        }

        model.setState("0");
        model.setNo(bookNo);
        model.setTerminal("1002"); //1002为手机智慧禅城APP终端
        yuYuesDao.save(model);

        return result;
    }

    // 获得根据预约号和证件号获得单个预约的详细信息
    @Override
    public String singleYuYue(YuYues model) {
        String booking_no = model.getNo();
        String id_card = model.getIdcard();
        // 如果证件没有值则跳出方法
        if (booking_no == null || id_card == null || "".equals(booking_no)
                || "".equals(id_card)) {
            return "";
        }

        String message = "";
        String result = "";
        // 传入booking_no和id_card

       /*
       YuYues yuYues = yuYuesDao.selectByNoAndIdCard(model.getNo(), model.getIdcard());
       message = "{\n" +
                "  \"header\": {\n" +
                "    \"handler_id\": 2001,\n" +
                "    \"command_id\": 2007,\n" +
                "    \"terminal\": 1002,\n" +
                "    \"version\": 0,\n" +
                "    \"reserved\": 0\n" +
                "  },\n" +
                "  \"data\": {\n" +
                "    \"id_card\": \"" + yuYues.getIdcard() + "\",\n" +
                "    \"booking_no\": \"" + yuYues.getNo() + "\",\n" +
                "    \"yuyue\": {\n" +
                "      \"name\": \"" + yuYues.getName() + "\",\n" +
                "      \"no\": \"" + yuYues.getNo() + "\",\n" +
                "      \"street\": \"3\",\n" +
                "      \"service_type\": \"ZH001\",\n" +
                "      \"service_name\": \"综合一门式业务\",\n" +
                "      \"id_card\": \"" + yuYues.getIdcard() + "\",\n" +
                "      \"phone\": \"" + yuYues.getPhone() + "\",\n" +
                "      \"y_date\": \"" + yuYues.getYdate() + "\",\n" +
                "      \"s_time\":  \"" + yuYues.getYstime() + "\",\n" +
                "      \"e_time\":\"" + yuYues.getYetime() + "\",\n" +
                "      \"state\": \"" + yuYues.getState() + "\",\n" +
                "    },\n" +
                "    \"code\": 1,\n" +
                "    \"msg\": \"查询成功\"\n" +
                "  }\n" +
                "}\n";*/
        /*预约单错误或者取消预约后!*/
       /* message = "{\n" +
                "  \"header\": {\n" +
                "    \"handler_id\": 2001,\n" +
                "    \"command_id\": 2007,\n" +
                "    \"terminal\": 1001,\n" +
                "    \"version\": 0,\n" +
                "    \"reserved\": 0\n" +
                "  },\n" +
                "  \"data\": {\n" +
                "    \"id_card\": \"012345678910\",\n" +
                "    \"booking_no\": \"10025253\",\n" +
                "    \"code\": 0,\n" +
                "    \"msg\": \"亲!我们已经很努力了,结果系统没有查询到你的预约记录信息...\"\n" +
                "  }\n" +
                "}";*/
        message = ifs.yuYuesState(booking_no, id_card);
        result = getJsonData(message);
        try {
            YuYues yuYues = yuYuesDao.selectByNoAndIdCard(model.getNo(), model.getIdcard());
            JSONObject json = new JSONObject(result);
            String code = json.get("code").toString();
            if ("1".equals(code)) {
                JSONObject data = new JSONObject(json);
                JSONObject yuyue = new JSONObject(json.get("yuyue").toString());
                if (yuYues != null) {
                    //查询事项 更新
                    if (!yuYues.getState().equals(yuyue.get("state").toString())) {
                        yuYues.setState(yuyue.get("state").toString());
                        yuYuesDao.modify(yuYues);
                    }
                    yuyue.put("businessName", yuYues.getBusinessName());
                    data.put("yuyue", yuyue);
                }
                result = data.toString();
            } else if ("0".equals(code)) {//预约事项已取消！更新  或者 预约时间过了很久
                if (yuYues != null && !"2".equals(yuYues.getState())) {
                    yuYues.setState("2");
                    yuYuesDao.modify(yuYues);
                }
                /*预约取消查询本地数据返回*/
                if (yuYues != null) {
                    JSONObject data = new JSONObject();
                    JSONObject yuyue = new JSONObject();
                    data.put("code", "1");
                    data.put("msg", "查询成功");
                    data.put("id_card", id_card);
                    data.put("booking_no", booking_no);
                    yuyue.put("name", yuYues.getName());
                    yuyue.put("no", yuYues.getNo());
                    yuyue.put("street", yuYues.getStreet());
                    yuyue.put("service_type", yuYues.getStype());
                    yuyue.put("service_name", "综合一门式业务");
                    yuyue.put("id_card", yuYues.getIdcard());
                    yuyue.put("phone", yuYues.getPhone());
                    yuyue.put("y_date", yuYues.getYdate());
                    yuyue.put("s_time", yuYues.getYstime());
                    yuyue.put("e_time", yuYues.getYetime());
                    yuyue.put("state", "2");
                    yuyue.put("businessName", yuYues.getBusinessName());
                    data.put("yuyue", yuyue);
                    result = data.toString();
                } else {
                    JSONObject data = new JSONObject();
                    data.put("code", "0");
                    data.put("msg", "亲!系统查找不到你预约信息...");
                    result = data.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    // 取消预约
    @Override
    public String cancelYuYue(YuYues model) {
        String booking_no = model.getNo();
        String id_card = model.getIdcard();
        // 如果证件没有值则跳出方法
        if (booking_no == null || id_card == null || "".equals(booking_no)
                || "".equals(id_card)) {
            return "";
        }

        String message = "";
        String result = "";


      /*  message = "{\n" +
                "  \"header\": {\n" +
                "    \"handler_id\": 2001,\n" +
                "    \"command_id\": 2002,\n" +
                "    \"terminal\": 1001,\n" +
                "    \"version\": 0,\n" +
                "    \"reserved\": 0\n" +
                "  },\n" +
                "  \"data\": {\n" +
                "    \"id_card\": \"" + id_card + "\",\n" +
                "    \"booking_no\": \"" + booking_no + "\",\n" +
                "    \"code\": 1,\n" +
                "    \"msg\": \"亲!系统已经处理了你的预约取消请求...\"\n" +
                "  }\n" +
                "}";*/
        // 传入booking_no和id_card
        message = ifs.cancelYuYues(booking_no, id_card);
        result = getJsonData(message);

        try {
            JSONObject json = new JSONObject(result);
            String code = json.get("code").toString();
            if ("1".equals(code)) {
                YuYues yuYues = yuYuesDao.selectByNoAndIdCard(booking_no, id_card);
                if (yuYues != null) {
                    yuYues.setState("2");
                    yuYuesDao.modify(yuYues);
                }

                String msg = "地点：张槎街道行政服务中心,预约号：" + yuYues.getNo() + ",预约时间：" + yuYues.getYdate() + " " + yuYues.getYstime() + "-" + yuYues.getYetime() + "," +
                        "您的预约已成功取消！\n" +
                        "感谢您的使用。";

                net.sf.json.JSONObject json1 = sendMassageUtil.sendSms(msg, yuYues.getPhone());
                if (json1.getString("flag").equals("1")) {
                    System.out.println("预约取消模板发送成功");
                } else {
                    throw new RuntimeException("预约取消模板发送失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 获得json的中data
    private String getJsonData(String json) {
        JSONObject jsonObject = null;
        String jsonData = "";
        try {
            jsonObject = new JSONObject(json);
            jsonData = jsonObject.get("data").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    // 根据openid获得用户的预约列表
	/*
	public String yuYueList(String openid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String today = sdf.format(new Date());
		Calendar cale = null;
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		String firstday;
		firstday = sdf.format(cale.getTime());
//		System.out.println("firstday=="+firstday);
		
		// 先获得月初日期之前的数据
		YuYues yy = new YuYues();
		yy.setOpenid("equals%3A" + openid);
		yy.setId("orderby1_desc_");
		yy.setYdate("lt<" + firstday);
		List<YuYues> list = super.findLikeByEntity(yy,
				BeanUtilEx.getNotNullEscapePropertyNames(yy));
		// 再获得月初日期之后的数据(包括当前日期)

		YuYues yy2 = new YuYues();
		yy2.setOpenid("equals%3A" + openid);
		yy2.setId("orderby1_desc_");
		yy2.setYdate("gt>=" + firstday);
		List<YuYues> list2 = super.findLikeByEntity(yy2,
				BeanUtilEx.getNotNullEscapePropertyNames(yy2));
		// 组合形成json
		// 组合参数
		JSONObject obj = new JSONObject();
		String result = "";
		try {
			JSONArray jaOld = JSONArray.fromObject(list);
			JSONArray jaNew = JSONArray.fromObject(list2);
			obj.put("old", jaOld);
			obj.put("future", jaNew);
			result = obj.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}

		return result;
	}*/
    public String yuYueList(String openid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstday;
        firstday = sdf.format(new Date());

        List<YuYues> list = yuYuesDao.listBefore(openid, firstday);

        List<YuYues> list2 = yuYuesDao.listLater(openid, firstday);
        /*YuYues yy2 = new YuYues();
        yy2.setOpenid("equals%3A" + openid);
        yy2.setId("orderby1_desc_");
        yy2.setYdate("gt>=" + firstday);
        List<YuYues> list2 = super.findLikeByEntity(yy2,
                BeanUtilEx.getNotNullEscapePropertyNames(yy2));*/
        // 组合形成json
        // 组合参数
        JSONObject obj = new JSONObject();
        String result = "";
        try {
            JSONArray jaOld = JSONArray.fromObject(list);
            JSONArray jaNew = JSONArray.fromObject(list2);
            obj.put("old", jaOld);
            obj.put("future", jaNew);
            result = obj.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }

        return result;
    }

    @Override
    public List<YuYues> getYuyuesBytime(Long currtime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date(System.currentTimeMillis() - currtime);
        String date = format.format(d);
        YuYues yuYues = new YuYues();
        yuYues.setYdate(date);
        List<YuYues> list = yuYuesDao.findEqualByEntity(yuYues, BeanUtilEx.getNotNullPropertyNames(yuYues));
        return list;
    }

    @Override
    public List<YuYues> signNot(String idCard, String date, String year) {
        List<YuYues> list = yuYuesDao.signNot(idCard, date, year);
        return list == null ? new ArrayList<YuYues>() : list;
    }

    public static void main(String[] args) {
//        String txt = String.format("张槎街道行政服务中心收到新的预约，预约事项为：%s，预约时间为：%s %s-%s，请相关人员注意","再生育审批","2019-06-29","14:30","15:30" );
//        System.out.println(txt);
//        Map map = new HashMap();
//        map.put("method", "sendSMS");
//        map.put("content", txt);
//        System.out.println(map.toString());
    }
}
