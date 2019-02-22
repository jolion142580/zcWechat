<%@ page contentType="text/html;charset=GBK"%> 

<% 
try{
	String sCurrentLine=""; 

	String sTotalString=""; 

	java.io.InputStream l_urlStream; 

	java.net.URL l_url = new java.net.URL("http://apistore.baidu.com/microservice/weather?cityid=101280803"); 

	java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url.openConnection();

	  /*  l_connection.setRequestMethod("POST");
		l_connection.setRequestProperty("Content-type", "text/html");
	   
	*/

	l_connection.connect(); 

	l_urlStream = l_connection.getInputStream(); 

	java.io.BufferedReader l_reader = new java.io.BufferedReader(new java.io.InputStreamReader(l_urlStream,"UTF-8")); 

	while ((sCurrentLine = l_reader.readLine()) != null) 

	{ 

	 sTotalString+=sCurrentLine; 

	} 

	out.println(sTotalString); 
}catch(Exception ex){
	out.println("{'errNum':0,'errMsg':'success','retData':{'city':'佛山','pinyin':'foshan','citycode':'101280802','date':'16-01-12','time':'11:00','postCode':'528100','longitude':112.909,'latitude':23.267,'altitude':'9','weather':'晴','temp':'17','l_tmp':'12','h_tmp':'17','sunrise':'07:11','sunset':'18:01'}} "); 
	
}

%> 

