package com.gdyiko.zcwx.timer;

import org.springframework.stereotype.Component;

@Component
public class TimedTaskData {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
