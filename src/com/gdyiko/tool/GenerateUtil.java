package com.gdyiko.tool;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;
import java.util.regex.*;

import org.springside.modules.test.utils.DataUtils;

public class GenerateUtil {

	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String numberChar = "0123456789";
	protected static int count = 0;   
  
    public static synchronized String getUUID(int n) {
        count++;   
        long time = System.currentTimeMillis();   
  
        String timePattern = Long.toHexString(time);
        int leftBit = 14 - timePattern.length();   
        if (leftBit > 0) {   
            timePattern = "0000000000".substring(0, leftBit) + timePattern;   
        }   
  
        String uuid = timePattern   
                + Long.toHexString(Double.doubleToLongBits(Math.random()))   
                + Long.toHexString(Double.doubleToLongBits(Math.random()))   
                + "000000000000000000";   
  
        uuid = uuid.substring(30-n, 30);//.toUpperCase();   
  
        return uuid;   
    } 
    
    public static  int getIntID() {   
        long time = System.currentTimeMillis();
        String numstr = String.valueOf(time);
        return Integer.parseInt(numstr.substring(4, numstr.length()));   
    }
    
	/**
	* 返回一个定长的随机字符串(只包含大小写字母、数字)
	*
	* @param length 随机字符串长度
	* @return 随机字符串
	*/
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}
	
	/**
	* 生成n个small-big的不重复随机数字
	*
	* @param n 随机数字个数
	* @param n 随机数字个数
	* @return 随机数字数组
	*/
	public static int[] generateDifNums(int n ,int small,int big) {           
		
		int length = big - small +1;
		int [] seed = new int[length];
		for(int i=0;i <length;i++){
			seed[i] = small+i; 
		}
        int [] ranArr = new int [n];   
        Random ran = new Random();   
        for(int i = 0 ; i<n ; i++){   
        	int j = ran.nextInt(length-i);   
        	ranArr [i] = seed [j];   
        	seed [j] = seed [length-1-i];   
              
        } 
        return ranArr;
    }
	
	
	/**
	 * 生成无乱码的剪切字符串
	 * @param str 源字符串
	 * @param tobytesize 剪切为字节数
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static  String cutStringSize(String str,int tobytesize) throws UnsupportedEncodingException {   
		byte[]strb=str.getBytes();
		int fitsize = tobytesize;
		if(strb.length>tobytesize){
			
			Pattern pattern =  Pattern.compile("^[\u4e00-\u9fa5]+$");
			Matcher matcher ;
			byte midb = strb[tobytesize-1];
			byte leftb = strb[tobytesize-2];
			byte rightb = strb[tobytesize];
			byte [] uletter = {leftb,midb};
			byte [] dletter = {midb,rightb};
			matcher = pattern.matcher(new String(uletter));
			if(matcher.find()){
				fitsize = tobytesize;
			}
			matcher = pattern.matcher(new String(dletter));
			if(matcher.find()){
				fitsize = tobytesize+1;
			}
			/*byte[] ssb = new byte[fitsize];
			for(int i=0;i<fitsize;i++){
				ssb[i] = strb[i];
			}*/
			return new String(strb,0, fitsize,"GBK");
		}
		return str;		
    }
	
	//-- 私有函数 --//
	public static String getRadomColor(){
		StringBuffer sb = new StringBuffer();		
		for (int i = 0; i < 6; i++) {
			Double db = Math.floor(Math.random()*16);
			sb.append(Integer.toHexString(db.intValue()));
		}
		return sb.toString();	
	}
    
    public static void main(String[] args){
    	/*System.out.println(System.currentTimeMillis());
    	StringBuffer sb = new StringBuffer();
    	String lineEnd = System.getProperty("line.separator");
    	for(int i=0;i<100;i++){
    		sb.append(getUUID(4)).append(lineEnd); 		
    	}
    	FileUtil.saveUTF8TextFile("d://serial.txt", sb.toString());*/
    	long time = System.currentTimeMillis();
    	String timePattern = Long.toHexString(time);   
        int leftBit = 14 - timePattern.length(); 
        System.out.println(time);
        System.out.println(Long.toHexString(time));
        System.out.println(leftBit);
    	//URL = new URL("http://static14.photo.sina.com.cn/bmiddle/61fcdce4x72eb9c4447dd&#38;690");
        System.out.println(UUID.randomUUID());
    }

}
