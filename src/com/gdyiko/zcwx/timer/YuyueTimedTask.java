/*
package com.gdyiko.zcwx.timer;


import com.gdyiko.zcwx.po.YuYues;
import com.gdyiko.zcwx.service.InterfaceService;
import com.gdyiko.zcwx.service.YuYuesService;
import com.gdyiko.zcwx.weixinUtils.CustomMessageAPI;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;


@Component
public class YuyueTimedTask {

    @Resource(name = "yuYuesService")
    private YuYuesService yuYuesService;

    @Resource(name = "interfaceService")
    InterfaceService ifs;


    private final SimpleDateFormat dayformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    */
/*
        检测昨天预约情况
     *//*

    public void checYesterdayYuyue() throws Exception {
        List<YuYues> list = yuYuesService.getYuyuesBytime(24 * 60 * 60 * 1000L);
        if (list == null || list.size() == 0) {
            System.out.println(dateFormat.format(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000L)) + " 没有预约！");
            return;
        }
        for (YuYues yuYues : list) {
            String booking_no = yuYues.getNo();
            String id_card = yuYues.getIdcard();
            if (booking_no == null || id_card == null || "".equals(booking_no)
                    || "".equals(id_card)) {
                return;
            }
            String message = ifs.yuYuesState(booking_no, id_card);
            JSONObject result = getJsonData(message);
            String code = result.get("code").toString();
            if ("0".equals(code)) { //找不到预约记录 用户取消预约
                yuYues.setState("2");
                yuYuesService.modify(yuYues);
            } else if ("1".equals(code)) {
                JSONObject yuyue = (JSONObject) result.get("yuyue");
                String state = yuyue.get("state").toString();
//                state = 0 | 1 ; 0没有签到 | 1已签到
                if (!state.equals(yuYues.getState())) { //yuYues.getState() 默认为0
                    yuYues.setState(state);
                    yuYuesService.modify(yuYues);
                }
            }
        }

    }

    //    @PostConstruct
//    @Scheduled(cron = "0 0 8 * * ?")
//    @Scheduled(cron="0/10 * *  * * ? ")//demo 10秒执行一次
    public void sendYuyueNotice() throws Exception {
        try {
            CustomMessageAPI api = new CustomMessageAPI();
            List<YuYues> list = yuYuesService.getYuyuesBytime(0L);
            if (list.size() > 0) {
                for (YuYues yuYues : list) {
                    String message = "您今天（" + yuYues.getYstime() + "-" + yuYues.getYetime() + "）预约了一项业务，" +
                            "请在预约时间段内前往办事。若在预约时间段内不能前来办事，累计3次将列入黑名单，不能再使用预约功能。";
                    api.sendTextMessageToUser(message, yuYues.getOpenid());
//                    System.out.println(yuYues.getName() + ":预约时间：   " + yuYues.getYdate() + " " + yuYues.getYstime() + "-" + yuYues.getYetime() + "\t\t系统输出时间:" + dayformat.format(new Date()));
                }
            }
            List<YuYues> list2 = yuYuesService.getYuyuesBytime(24 * 60 * 60 * 1000L);
            if (list2.size() > 0) {
                for (YuYues yuyues : list2) {
                    if ("0".equals(yuyues.getState()) || "4".equals(yuyues.getState())) { //失约
                        if ("4".equals(yuyues.getState())) {
                            yuyues.setState("0");
                            yuYuesService.modify(yuyues);
                        }
                        int count = yuYuesService.signNot(yuyues.getIdcard(), yuyues.getYdate(), yuyues.getYdate().substring(0, 4)).size();
                        String message = "您昨天（" + yuyues.getYdate() + " " + yuyues.getYstime() + "-" + yuyues.getYetime() + "）未能前来办理预约事项，当前失约" + count + "次，超过3次失约，将被限制使用预约功能。如有疑问请联系工作人员。";
                        api.sendTextMessageToUser(message, yuyues.getOpenid());
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("预约通知发送失败！");
        }
    }


    public void showTimer() {
        Runnable runable = new Runnable() {
            public void run() {
                try {
                    checYesterdayYuyue();
                    sendYuyueNotice();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        long oneDay = 24 * 60 * 60 * 1000;
        long initDelay = getTimeMillis("08:00:00") - System.currentTimeMillis();
//        long initDelay = getTimeMillis("11:54:00") - System.currentTimeMillis(); //demo 就近时间
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;
        executor.scheduleAtFixedRate(runable, initDelay, oneDay, TimeUnit.MILLISECONDS); //每天8:00发送
//        executor.scheduleAtFixedRate(runable,initDelay,1000,TimeUnit.MILLISECONDS); //demo

     */
/*
       TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    sendYuyueNotice();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
//        定制每天的8:00:00执行，
        calendar.set(year, month, day, 8, 0, 00);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        System.out.println("每天执行时间：" + dayformat.format(date));

        int period = 10 * 1000;
//        每天的date时刻执行task，每隔10秒重复执行
        timer.schedule(task, date, period);
//        每天的date时刻执行task, 仅执行一次
        timer.schedule(task, date);
        *//*

    }

    private long getTimeMillis(String time) {
        try {
            Date currentDate = dayformat.parse(dateFormat.format(new Date()) + " " + time);
            return currentDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 获得json的中data
    private JSONObject getJsonData(String json) {
        JSONObject jsonData = null;
        try {
            jsonData = new JSONObject(new JSONObject(json).get("data").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public static void main(String[] args) {
        long oneDay = 24 * 60 * 60 * 1000;
        long initDelay = new YuyueTimedTask().getTimeMillis("08:00:00") - System.currentTimeMillis();
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;
        System.out.println(initDelay);
    }

}
*/
