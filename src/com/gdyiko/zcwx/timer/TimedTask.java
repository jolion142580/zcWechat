package com.gdyiko.zcwx.timer;

import com.gdyiko.tool.DateUtil;
import com.gdyiko.zcwx.weixinUtils.HttpContent;
import java.io.PrintStream;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimedTask
{

    @Autowired
    TimedTaskData timedTaskData;
    private static final String url = "http://172.16.120.111/main?xwl=241U0FRWQ22L&dept=zc";

    @Scheduled(cron="0 0/1 * * * ?")
    public void queueInfo()
    {
        HttpContent httpContent = new HttpContent();
        try {
            if (containCalendar()) {
                String content = httpContent.getHttpContent("http://172.16.120.111/main?xwl=241U0FRWQ22L&dept=zc", "", "", "post");
                this.timedTaskData.setContent(content);
            } else {
                this.timedTaskData.setContent("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(new TimedTask().containCalendar());
    }

    public boolean containCalendar()
    {
        Date nowDate = new Date();
        String startStr = DateUtil.getDateStr(nowDate, "yyyy-MM-dd");
        String startDate = startStr + " 08:30";
        String endDate = startStr + " 17:55";
        Date finalBiginDate = DateUtil.strToDate(startDate, "yyyy-MM-dd HH:mm");
        Date finalEndDate = DateUtil.strToDate(endDate, "yyyy-MM-dd HH:mm");
        return DateUtil.containCalendar(nowDate, finalBiginDate, finalEndDate);
    }
}