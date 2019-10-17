package com.gdyiko.base.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service("weChatPropertieService")
public class WeChatPropertieService {
    Properties p = new Properties();
    static Properties pp = new Properties();
    private static String fileName="/wechat.properties";
    static {
        InputStream in = WeChatPropertieService.class.getResourceAsStream(fileName);
        try {
            pp.load(in);
            in.close();
        } catch (IOException e) {
            System.out.println("weChat配置文件读取失败");
        }
    }

    public WeChatPropertieService()throws IOException {
        InputStream in = WeChatPropertieService.class.getResourceAsStream(fileName);
        p = new Properties();
        p.load(in);
        in.close();
    }

    public String getPropertie(String name){
        return p.getProperty(name);
    }

    public static String getPropertieByStatic(String name){
        return pp.getProperty(name);
    }
}
