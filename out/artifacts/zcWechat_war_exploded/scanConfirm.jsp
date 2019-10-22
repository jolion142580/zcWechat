<<<<<<< HEAD
﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.WxJSSignUtil" %>
<%@ page import="java.util.Map" %>
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
%>

<%


	String code = request.getParameter("code");
	String uuid=request.getParameter("uuid");
	
	System.out.println("code:::::" + code );
	String openid=(String)session.getAttribute("openid");
	
	if(code!=null && openid == null){
		OAuth oauth = new OAuth();
		openid=oauth.getOppenid(code);	
	} 
	//String openid="111";
	//String uuid="2222";
	 System.out.println("----网页扫一扫获取openid---"+openid);
	System.out.println("--=-=-网页uuid===="+uuid);

	//todo  增加了	application.setAttribute(uuid, openid);
    //    application.setAttribute(uuid, openid);

	

%>

<!DOCTYPE html>
<html>
<head>
    <meta name="generator"
          content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎街道行政服务中心</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <%--<link rel="shortcut icon" href="/favicon.ico"/>--%>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/sm.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/guide-style.css" type="text/css"/>

    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="js/jquery-weui.js"></script>
    <script type="text/javascript" src="lib/zepto.js"></script>
    <script type="text/javascript" src="js/config.js"></script>


</head>


<body >


</body>

</html>
<script type="text/javascript">
    
$.modal({
	  title: "微信公众号授权",
	  text: "获取你在\"张槎街道行政服务中心\"公众号认证绑定信息，如你填写表单信息后，可以在办事记录中查看！",
	  buttons: [
	    { text: "允许", onClick: function(){ 
	    	
	    	$.post('QRCode!allowScan', { uuid:'<%=uuid%>',openid:'<%=openid%>'}, function(data) {
	    		var data1 = eval("(" + data + ")");
	    		if(data1.state=="success"){
	    			$.toast("操作成功", function() {
		    			wx.closeWindow();
		    		});	
	    		}
	    		if(data1.state=="fail"){
	    		    location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"relation.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	    			/*$.alert("请关注\"张槎街道行政服务中心\"微信公众号并进行认证绑定！", function() {

	    				wx.closeWindow();
	    			});*/
	    		}
	    		    		
	    	});
	
	    	} 
	    },
	    { text: "拒绝", className: "default", onClick: function(){ 
	    		wx.closeWindow();
	    	} 
	    },
	  ]
});



</script>

=======
﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.WxJSSignUtil" %>
<%@ page import="java.util.Map" %>
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta name="generator"
          content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎街道行政服务中心</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <%--<link rel="shortcut icon" href="/favicon.ico"/>--%>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/sm.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/guide-style.css" type="text/css"/>

    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="js/jquery-weui.js"></script>
    <script type="text/javascript" src="lib/zepto.js"></script>
    <script type="text/javascript" src="js/config.js"></script>


</head>


<body >


</body>

</html>
<script type="text/javascript">
    
$.modal({
	  title: "微信公众号授权",
	  text: "获取你在\"张槎街道行政服务中心\"公众号认证绑定信息，如你填写表单信息后，可以在办事记录中查看！",
	  buttons: [
	    { text: "允许", onClick: function(){ 
	    	
	    	$.post('QRCode!allowScan', { uuid:'${uuid}',sid:'${sid}'}, function(data) {
	    		var data1 = eval("(" + data + ")");
	    		if(data1.state=="success"){
	    			$.toast("操作成功", function() {
		    			wx.closeWindow();
		    		});	
	    		}
	    		if(data1.state=="fail"){
	    		   // alert(1);
	    		 //   location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"relation.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	    			/*$.alert("请关注\"张槎街道行政服务中心\"微信公众号并进行认证绑定！", function() {

	    				wx.closeWindow();
	    			});*/
	    		}
	    		    		
	    	});
	
	    	} 
	    },
	    { text: "拒绝", className: "default", onClick: function(){ 
	    		wx.closeWindow();
	    	} 
	    },
	  ]
});



</script>

>>>>>>> withoutWechatInterface
