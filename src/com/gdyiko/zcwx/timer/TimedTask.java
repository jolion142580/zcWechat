/*
package com.gdyiko.zcwx.timer;

import com.gdyiko.tool.DateUtil;
import com.gdyiko.zcwx.weixinUtils.HttpContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimedTask {
    @Autowired
    TimedTaskData timedTaskData;
    private static final String url = "http://172.16.120.111/main?xwl=241U0FRWQ22L&dept=zc";

    //    @Scheduled(cron = "0/5 * * * * ? ")
    @Scheduled(cron = "0 0/1 * * * ?")
    public void queueInfo() {
        HttpContent httpContent = new HttpContent();
        try {
            if (containCalendar()) {
                String content = httpContent.getHttpContent(url, "", "", "post");
                timedTaskData.setContent(content);
            } else {
                timedTaskData.setContent("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(new TimedTask().containCalendar());
    }




    //在这个时间段内才调用
    public boolean containCalendar() {
        Date nowDate = new Date();
        String startStr = DateUtil.getDateStr(nowDate, DateUtil.DATE_PATTERN_YYYY_MM_DD);
        String startDate = startStr + " 08:30";
        String endDate = startStr + " 17:55";
        Date finalBiginDate = DateUtil.strToDate(startDate, DateUtil.DATE_PATTERN_YYYY_MM_DDHHMM);
        Date finalEndDate = DateUtil.strToDate(endDate, DateUtil.DATE_PATTERN_YYYY_MM_DDHHMM);
        return DateUtil.containCalendar(nowDate, finalBiginDate, finalEndDate);
    }


}
*/
