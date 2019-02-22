package com.gdyiko.zcwx.weixinUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.json.JSONException;
import org.json.JSONObject;


public class HttpContent {
	
	public  String getHttpContent(String url,String jsonData,String sessionId,String reqMenthod) throws JSONException {

		HttpURLConnection connection = null;
        String content = "";
        try {     	
        	
            URL address_url = new URL(url);
            connection = (HttpURLConnection) address_url.openConnection();
                        
            //在http头中的Cookie信息中，带上sessionID.一般是登录一个远程服务器之后，拿到sessionid以后请求都带上，服务器会认为是已登录的用户
            if(!sessionId.equals("")){
            	connection.setRequestProperty("Cookie", sessionId);
            }
//            connection.setUseCaches(false);
//            connection.setRequestMethod("GET");
            //设置访问超时时间及读取网页流的超市时间,毫秒值
            //超时设置，防止 网络异常的情况下，可能会导致程序僵死而不继续往下执行
            System.setProperty("sun.net.client.defaultConnectTimeout","30000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");
            
            if(reqMenthod.equalsIgnoreCase("post")){
            	connection.setDoOutput(true);// 允许连接提交信息
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//              String obj="{'data':{'phone':'1234567890','e_time':'10:00','name':'张三','service_type':'MZQ001','street':'1','s_time':'09:00','id_card':'1234567890','date':'2016-01-18'},'header':{'terminal':1001,'command_id':2001,'reserved':0,'handler_id':2001,'version':0}}";
	              if(!jsonData.equals("")){
	  	            JSONObject js = new JSONObject(jsonData);
//	  	            System.out.println("--jsonData---"+js.toString());
	  	            //out.writeBytes(js.toString());
	  	            out.append(js.toString());
	  	            out.flush();
	  	            out.close();
	              }
            }else{
            	connection.setRequestMethod("GET"); 
            }
            //DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            //after JDK 1.5
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            //得到访问页面的返回值
            int response_code = connection.getResponseCode();
            //System.out.println("-------"+response_code);
            if (response_code == HttpURLConnection.HTTP_OK) {
            	
            	String encoding = connection.getContentEncoding();  
            	InputStream  in = connection.getInputStream();  
                if (encoding != null && encoding.contains("gzip")) {//首先判断服务器返回的数据是否支持gzip压缩，  
                    //如果支持则应该使用GZIPInputStream解压，否则会出现乱码无效数据  
                    in = new GZIPInputStream(connection.getInputStream());  
                }  
             
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    content+=line;
                    
                }
                return content;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection !=null){
                connection.disconnect();
            }
        }
        return "";
    }
	
	/**
	 * 发送短信接口
	 * @param url
	 * @param txt 发送内容
	 * @param phone 发送的手机号码
	 * @return
	 * @throws JSONException
	 */
	public  String getHttpContentSms(String url,String txt,String phone) throws JSONException {
        HttpURLConnection connection = null;
        String content = "";
        try {     	
        	
            URL address_url = new URL(url);
            connection = (HttpURLConnection) address_url.openConnection();
            connection.setDoOutput(true);// 允许连接提交信息
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            //在http头中的Cookie信息中，带上sessionID.一般是登录一个远程服务器之后，拿到sessionid以后请求都带上，服务器会认为是已登录的用户
            /*if(!sessionId.equals("")){
            	connection.setRequestProperty("Cookie", sessionId);
            }*/
//            connection.setUseCaches(false);
//            connection.setRequestMethod("GET");
            //设置访问超时时间及读取网页流的超市时间,毫秒值
            //超时设置，防止 网络异常的情况下，可能会导致程序僵死而不继续往下执行
            System.setProperty("sun.net.client.defaultConnectTimeout","30000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");
            
            //DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "GBK");
//            String obj="{'data':{'phone':'1234567890','e_time':'10:00','name':'张三','service_type':'MZQ001','street':'1','s_time':'09:00','id_card':'1234567890','date':'2016-01-18'},'header':{'terminal':1001,'command_id':2001,'reserved':0,'handler_id':2001,'version':0}}";
            //JSONObject js = new JSONObject(jsonData);
            //js.put("hospitalId", 1);
            
            //System.out.println("--jsonData---"+js.toString());
            //out.writeBytes(js.toString());
            //String time = Long.toString(System.currentTimeMillis());
     
            String sendContent="SpCode=210808&LoginName=admin&Password=ss888888&MessageContent="+txt+"&UserNumber="+phone+"&SerialNumber="+System.currentTimeMillis()+"&ScheduleTime=&f=1";
            out.append(sendContent);
            out.flush();
            out.close();

            //after JDK 1.5
//            connection.setConnectTimeout(10000);
//            connection.setReadTimeout(10000);
            //得到访问页面的返回值
            int response_code = connection.getResponseCode();
            //System.out.println("-------"+response_code);
            if (response_code == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
//                InputStreamReader reader = new InputStreamReader(in,charSet);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    content+=line;
                    
                }
                return content;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection !=null){
                connection.disconnect();
            }
        }
        return "";
    }
    
    public String getSessionId(String url){
    	String[] sessionId=null;
    	try {
    		URL url1 = new URL(url);            
	    	HttpURLConnection connection = (HttpURLConnection) url1.openConnection();             
			String session_value= connection.getHeaderField("Set-Cookie");
			sessionId = session_value.split(";");
			connection.disconnect(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionId[0];
    	
    }
  

    
    public String getHttpContentByByte(String caseid,String fileName){
    	String result="";
    	/*try {
    		BASE64Encoder base64Encoder = new BASE64Encoder();  
        	StringBuilder sb = new StringBuilder();  
        	sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");  
        	sb.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");  
        	sb.append("  <soap:Body>");  
        	sb.append("    <Append xmlns=\"http://www.fsmap.com.cn/\">");  
        	sb.append("     <caseid>");  
        	sb.append(caseid);  
        	sb.append("     </caseid>");  
        	sb.append("     <fileName>");  
        	sb.append(fileName);  
        	sb.append("     </fileName>");
        	sb.append("     <buffer>");  
        	byte[] bs=getByteArray("");//取得某个文本的byte数组，具体不写  
        	sb.append(base64Encoder.encode(bs));  
        	sb.append("</buffer>");  
        	sb.append("    </Append>");  
        	sb.append("  </soap:Body>");  
        	sb.append("</soap:Envelope>");  
        	URL u = new URL("http://19.129.96.57/FsugicWebServiceV2/Service/ZFService.asmx");  
        	HttpURLConnection conn = (HttpURLConnection) u.openConnection();  
        	conn.setConnectTimeout(60000);  
        	conn.setReadTimeout(60000);  
        	conn.setDoInput(true);  
        	conn.setDoOutput(true);  
        	conn.setUseCaches(false);  
        	conn.setDefaultUseCaches(false);  
        	conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");  
        	conn.setRequestProperty("Content-Length", String.valueOf(sb.toString().length()));  
        	conn.setRequestProperty("SOAPAction", "http://www.fsmap.com.cn/Append");  
        	conn.setRequestMethod("POST");  
        	  
        	OutputStream output = conn.getOutputStream();  
        	if (null != sb) {  
        	    byte[] b = sb.toString().getBytes("utf-8");  
        	    output.write(b, 0, b.length);  
        	}  
        	output.flush();  
        	output.close();  
        	  
        	InputStream input = conn.getInputStream();  
        	int c = -1;  
        	while (-1 != (c = input.read())) {  
        	    sb.append((char) c);  
        	}  
        	input.close();  
        	result = sb.toString().replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll(  
        	        "&quot;", "\"");  
//        	System.out.println(result); 
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    	
    	return result;
    }
    
    public  String getHttpContentforsms(String url,String jsonData,String sessionId,String reqMenthod) throws JSONException {
		HttpURLConnection connection = null;
        String content = "";
        try {     	
        	
            URL address_url = new URL(url);
            connection = (HttpURLConnection) address_url.openConnection();
                        
            //在http头中的Cookie信息中，带上sessionID.一般是登录一个远程服务器之后，拿到sessionid以后请求都带上，服务器会认为是已登录的用户
            if(!sessionId.equals("")){
            	connection.setRequestProperty("Cookie", sessionId);
            }
//            connection.setUseCaches(false);
//            connection.setRequestMethod("GET");
            //设置访问超时时间及读取网页流的超市时间,毫秒值
            //超时设置，防止 网络异常的情况下，可能会导致程序僵死而不继续往下执行
            System.setProperty("sun.net.client.defaultConnectTimeout","30000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");

            if(reqMenthod.equalsIgnoreCase("post")){
            	connection.setDoOutput(true);// 允许连接提交信息
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
                connection.setRequestProperty("Accept-Charset", "UTF-8");

                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//              String obj="{'data':{'phone':'1234567890','e_time':'10:00','name':'张三','service_type':'MZQ001','street':'1','s_time':'09:00','id_card':'1234567890','date':'2016-01-18'},'header':{'terminal':1001,'command_id':2001,'reserved':0,'handler_id':2001,'version':0}}";
                System.out.println("----"+jsonData);
	              if(!jsonData.equals("")){
	  	            JSONObject js = new JSONObject(jsonData);
//	  	            System.out.println("--jsonData---"+js.toString());
	  	            //out.writeBytes(js.toString());
	  	            out.append(js.toString());
	  	            out.flush();
	  	            out.close();
	              }
            }else{
            	connection.setRequestMethod("GET");
            	connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            	connection.setRequestProperty("Accept-Charset", "UTF-8");
            	connection.setRequestProperty("Accept-Encoding", "UTF-8");
            }
            //DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            //after JDK 1.5
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            //得到访问页面的返回值
            int response_code = connection.getResponseCode();
            //System.out.println("-------"+response_code);
            if (response_code == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
//                InputStreamReader reader = new InputStreamReader(in,charSet);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    content+=line;
                    
                }
                return content;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection !=null){
                connection.disconnect();
            }
        }
        return "";
    }
    
    
    public byte[] getByteArray(String filePath) throws IOException {  
        File file = new File(filePath);  
        long fileSize = file.length();  
        if (fileSize > Integer.MAX_VALUE) {  
            System.out.println("file too big...");  
            return null;  
        }  
        FileInputStream fi = new FileInputStream(file);  
        byte[] buffer = new byte[(int) fileSize];  
        int offset = 0;  
        int numRead = 0;  
        while (offset < buffer.length  
        && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {  
            offset += numRead;  
        }  
        // 确保所有数据均被读取  
        if (offset != buffer.length) {  
        throw new IOException("Could not completely read file "  
                    + file.getName());  
        }  
        fi.close();  
        return buffer;  
    }


    public  String getHttpContentforQueue(String url,String jsonData,String sessionId,String reqMenthod) throws JSONException {
        HttpURLConnection connection = null;
        String content = "";
        try {

            URL address_url = new URL(url);
            connection = (HttpURLConnection) address_url.openConnection();

            //在http头中的Cookie信息中，带上sessionID.一般是登录一个远程服务器之后，拿到sessionid以后请求都带上，服务器会认为是已登录的用户
            if(!sessionId.equals("")){
                connection.setRequestProperty("Cookie", sessionId);
            }
//            connection.setUseCaches(false);
//            connection.setRequestMethod("GET");
            //设置访问超时时间及读取网页流的超市时间,毫秒值
            //超时设置，防止 网络异常的情况下，可能会导致程序僵死而不继续往下执行
            System.setProperty("sun.net.client.defaultConnectTimeout","30000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");

            judge(jsonData,reqMenthod,connection);
            //DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            //after JDK 1.5
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            //得到访问页面的返回值
            int response_code = connection.getResponseCode();
            //System.out.println("-------"+response_code);
            if (response_code == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
//                InputStreamReader reader = new InputStreamReader(in,charSet);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    content+=line;

                }
                return content;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection !=null){
                connection.disconnect();
            }
        }
        return "";
    }
    //请求中判断get还是post的封装method
    private void judge(String jsonData, String reqMenthod, HttpURLConnection connection) throws IOException, JSONException {
        if(reqMenthod.equalsIgnoreCase("post")){
            connection.setDoOutput(true);// 允许连接提交信息
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            connection.setRequestProperty("Accept-Charset", "UTF-8");

            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//              String obj="{'data':{'phone':'1234567890','e_time':'10:00','name':'张三','service_type':'MZQ001','street':'1','s_time':'09:00','id_card':'1234567890','date':'2016-01-18'},'header':{'terminal':1001,'command_id':2001,'reserved':0,'handler_id':2001,'version':0}}";
            System.out.println("----"+jsonData);
            if(!jsonData.equals("")){
                JSONObject js = new JSONObject(jsonData);
//	  	            System.out.println("--jsonData---"+js.toString());
                //out.writeBytes(js.toString());
                out.append(js.toString());
                out.flush();
                out.close();
            }
        }else{
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Accept-Encoding", "UTF-8");
        }
    }

}
