package com.gdyiko.zcwx.action;

import com.gdyiko.zcwx.timer.TimedTaskData;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;


@Namespace("/")
@Action(value = "queueinfo", results = {})
public class QueueInfoAction extends ActionSupport {
    @Autowired
    TimedTaskData timedTaskData;
    private static final long serialVersionUID = 1L;

    public String send() {
        Struts2Utils.renderText(timedTaskData.getContent() == null ? "" : timedTaskData.getContent());
        return null;
    }


}
