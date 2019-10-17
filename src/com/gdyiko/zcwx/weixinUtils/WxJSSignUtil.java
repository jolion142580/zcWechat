package com.gdyiko.zcwx.weixinUtils;

import com.gdyiko.zcwx.po.resp.Token;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WxJSSignUtil {
    /*
     * public static void main(String[] args) {
     *
     * // 注意 URL 一定要动态获取，不能 hardcode String url =
     * "http://gwchsk.imwork.net/wechat/order/test.html"; Map<String, String>
     * ret = sign(url); for (Map.Entry entry : ret.entrySet()) {
     * System.out.println(entry.getKey() + "=" + entry.getValue()); } };
     */
    public static Token token = null;
    public static String time = null;
    public static String jsapi_ticket = null;

	/* */

    /**
     * @param appId     公账号appId
     * @param appSecret
     * @param url       当前网页的URL，不包含#及其后面部分
     * @return
     */
    /*
     * public static String getParam(String appId,String appSecret){
	 * WeixinHttpConnect httpconncet = new WeixinHttpConnect(); try{ if(token ==
	 * null){ token = httpconncet.getAccess_token(); jsapi_ticket =
	 * httpconncet.getTicket(token.getAccessToken()); time = getTime(); }else{
	 * if(!time.substring(0, 13).equals(getTime().substring(0, 13))){ //每小时刷新一次
	 * token = null; token = httpconncet.getAccess_token(); jsapi_ticket =
	 * httpconncet.getTicket(token.getAccessToken()); time = getTime(); } }
	 * 
	 * String url = getUrl();
	 * 
	 * Map<String, String> params = sign(jsapi_ticket, url); params.put("appid",
	 * appId);
	 * 
	 * JSONObject jsonObject = JSONObject.fromObject(params); String jsonStr =
	 * jsonObject.toString(); System.out.println(jsonStr); return jsonStr;
	 * 
	 * }catch (Exception e) { // TODO: handle exception return null; } }
	 */
    public static String getUrl() {
        HttpServletRequest request = ServletActionContext.getRequest();

		/*
         * StringBuffer requestUrl = request.getRequestURL();
		 * 
		 * String queryString = request.getQueryString(); String url =
		 * requestUrl +"?"+queryString;
		 */

        String param = request.getQueryString();
        String url = request.getScheme() + "://" + request.getServerName()
                + request.getRequestURI();
        if (param != null) {
            url = url + "?" + request.getQueryString();
        }

        return url;
    }

    public static Map<String, String> sign(String jsapi_ticket, String url)
            throws IOException {

        if (url.indexOf("localhost") != -1) {
            url = url.replace("localhost", "zcxzfwzx.chancheng.gov.cn");
        }
        Map<String, String> ret = new HashMap<String, String>();
        // 这里的jsapi_ticket是获取的jsapi_ticket。
        // WeixinHttpConnect httpconnect = new WeixinHttpConnect();
        // String jsapi_ticket = httpconnect.getTicket();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

       /* System.out.println("【jsapi_ticket】" + TokenThread.jsapi_ticket);
        System.out.println("【nonce_str】" + nonce_str);
        System.out.println("【timestamp】" + timestamp);
        System.out.println("【url】" + url);*/

        // 注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
                + "&timestamp=" + timestamp + "&url=" + url;
      //  System.out.println("【string1】" + string1);

        // System.out.println(string1);

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
      //      System.out.println("【signature】" + signature);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    // 获取当前系统时间 用来判断access_token是否过期
    public static String getTime() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }

	/*
     * public static void main(String[] args) { try {
	 * System.out.println(WxJSSignUtil.sign("112.74.216.15")); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } }
	 */

    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    /**
     * 生成随机字符串
     */
    public static String getNonceStr() {
        String currTime = getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = buildRandom(4) + "";
        return strTime + strRandom;
    }

    public static String getMediaId() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        String strAll = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-";
        for (int i = 0; i < 64; i++) {
            int f = (int) (Math.random() * 64);
            sb.append(strAll.charAt(f));

        }
        String str = sb.toString();
        if(str.startsWith("_") || str.startsWith("-")){
            str = str.substring(1);
        }
        if(str.endsWith("_")|| str.endsWith("-")){
            str = str.substring(0,str.length()-1);
        }
        return str;
    }
}
