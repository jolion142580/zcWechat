package com.gdyiko.zcwx.weixinUtils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static final String key = "zcweixinzcweixin";

    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

 /*   public static void main(String[] args) throws Exception {
        *//*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         *//*
*//*        String cKey = "zcweixinzcweixin";
        // 需要加密的字串
        String cSrc = "%7B%22address%22%3A%221236666%22%2C%22brithday%22%3A%221989-02-12%22%2C%22creator%22%3A%22%22%2C%22creattime%22%3A%222019-08-05+15%3A51%22%2C%22id%22%3A%22156499045974853821920%22%2C%22idCard%22%3A%22440682198902123233%22%2C%22imgfiles%22%3A%22156499045375450945360%2C156499045781546923788%2C156499045375450945360%2C156499045781546923788%2C156499045375450945360%2C156499045781546923788%2C156499045375450945360%2C156499045781546923788%22%2C%22name%22%3A%22%B3%CC%B1%F2%BB%AA%22%2C%22phone%22%3A%2213425889665%22%2C%22sex%22%3A%22%C4%D0%22%7D";
        System.out.println(cSrc);
        // 加密
        String enString = AES.Encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + enString);

        // 解密
        String DeString = AES.Decrypt(enString, cKey);
        System.out.println("解密后的字串是：" + DeString);*//*

        String url = "http://zcxzfwzx.chancheng.gov.cn/zdcWechat/zcWechat/index.jsp";

        url = url.replaceAll("zcWechat/zcWechat/","zcWechat/");

    System.out.println(url);
    }*/
}
