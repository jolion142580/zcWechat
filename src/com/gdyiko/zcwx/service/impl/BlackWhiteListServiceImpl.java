package com.gdyiko.zcwx.service.impl;

import com.gdyiko.tool.PrimaryProduce;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;
import com.gdyiko.zcwx.dao.BlackWhiteListDao;
import com.gdyiko.zcwx.po.BlackWhiteList;
import com.gdyiko.zcwx.po.YuYues;
import com.gdyiko.zcwx.service.BlackWhiteListService;
import com.gdyiko.zcwx.service.YuYuesService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("blackWhiteListService")
public class BlackWhiteListServiceImpl extends GenericServiceImpl<BlackWhiteList, String>
        implements BlackWhiteListService {
    @Resource(name = "yuYuesService")
    YuYuesService yuYuesService;

    @Resource(name = "blackWhiteListDao")
    BlackWhiteListDao blackWhiteListDao;

    @Resource(name = "blackWhiteListDao")
    @Override
    public void setGenericDao(GenericDao<BlackWhiteList, String> genericDao) {
        super.setGenericDao(genericDao);
    }

    public JSONObject check(YuYues yuYues) {
//        System.out.println("com.gdyiko.zcwx.service.impl.BlackWhiteListServiceImpl line[37] output: -=>111" );
        JSONObject result = new JSONObject();
        try {
//            System.out.println("com.gdyiko.zcwx.service.impl.BlackWhiteListServiceImpl line[37] output: -=>111.1" );
            BlackWhiteList blackWhiteList;
            Calendar cal = Calendar.getInstance();
            String year = String.valueOf(cal.get(Calendar.YEAR));
            String idCard = yuYues.getIdcard();
            blackWhiteList = getForever(idCard);
//            System.out.println("com.gdyiko.zcwx.service.impl.BlackWhiteListServiceImpl line[46] output: -=>111.2" );
            if (blackWhiteList != null) { //永久黑白名单
                result.put("flag", blackWhiteList.getFlag());
//                result.put("forever", "".equals(blackWhiteList.getForever()) ? "0" : blackWhiteList.getForever());
                result.put("forever", null == blackWhiteList.getForever() ? "0" : blackWhiteList.getForever());
                return result;
            }
            blackWhiteList = getByYear(idCard, year);
//            System.out.println("com.gdyiko.zcwx.service.impl.BlackWhiteListServiceImpl line[54] output: -=>111.3" );
            if (blackWhiteList != null) { // 今年进入黑白名单
                result.put("flag", blackWhiteList.getFlag());
                result.put("forever", null == blackWhiteList.getForever() ? "0" : blackWhiteList.getForever());
                return result;
            }
            //查询今年是否超过3次失约，超过记录黑名单
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(System.currentTimeMillis() - (1000 * 60 * 60 * 24));
            List<YuYues> yuYuesList = yuYuesService.signNot(idCard, date, year);
//            System.out.println("com.gdyiko.zcwx.service.impl.BlackWhiteListServiceImpl line[64] output: -=>111.4" );
            /* 今年失约超过3次*/
            if (yuYuesList.size() >= 3) {
                System.out.println(yuYues.getName() + " 今年失约-->" + yuYuesList.size());
                blackWhiteList = new BlackWhiteList();
                blackWhiteList.setId(PrimaryProduce.produce().substring(0, 10));
                blackWhiteList.setIdCard(yuYues.getIdcard());
                blackWhiteList.setTime(year);
                blackWhiteList.setCreator("Administrator");
                blackWhiteList.setCreattime(format.format(new Date()));
                blackWhiteList.setFlag("0");//黑名单
                blackWhiteList.setOpenid(yuYues.getOpenid());
                blackWhiteList.setName(yuYues.getName());
                blackWhiteList.setPhone(yuYues.getPhone());
                blackWhiteListDao.save(blackWhiteList);
                result.put("flag", "0");
                result.put("forever", "0");
                return result;
            }
//            默认返回
            result.put("flag", "1");
            result.put("forever", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        System.out.println("com.gdyiko.zcwx.service.impl.BlackWhiteListServiceImpl line[89] output: -=>222" );
        return result;
    }

    private BlackWhiteList getForever(String idCard) {
        BlackWhiteList model = null;
        List<BlackWhiteList> list = blackWhiteListDao.selectByForever(idCard);
        if (list.size() > 0) {
            model = list.get(0);
        }
        return model;
    }

    private BlackWhiteList getByYear(String idCard, String year) {
        BlackWhiteList model = null;
        List<BlackWhiteList> list = blackWhiteListDao.selectByYear(idCard, year);
        if (list.size() > 0) {
            model = list.get(0);
        }
        return model;
    }

}
