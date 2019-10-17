<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="generator"
	content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>张槎街道行政服务中心</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1" />
<link rel="shortcut icon" href="/favicon.ico" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" href="css/sm-extend.min.css" type="text/css" />
<link rel="stylesheet" href="css/sm.min.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css" />

<link rel="stylesheet" href="lib/weui.min.css" type="text/css" />
<link rel="stylesheet" href="css/guide-style.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/jquery-weui.js"></script>
<script type="text/javascript" src="js/config.js"></script>
</head>

<%

String affairid=(String)session.getAttribute("affairid");
String openid=(String)session.getAttribute("openid");
String objindex=(String)session.getAttribute("objindex");
//System.out.println("--11111---"+affairid+"-=-=-="+openid);
 %>

<body style="background-color:transparent" >

	<iframe src="dist/#/preview?affairid=<%=affairid%>&objindex=<%=objindex %>" width="100%" height="100%" align="center" scrolling="auto" frameborder="0" allowTransparency="true" >
	</iframe>
	
</body>
</html>
<script>

	$(function () {
		$.modal({
			title: "温馨提示",
			text: "如果您所办理的业务已经填写<p/ >请直接上传表格",
			buttons: [
				{ text: "自助填表", onClick: function(){

					} },
				{ text: "上传表格", onClick: function(){
						location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"onlineApply!isrelation?affairid=<%=affairid%>_affairMaterials_<%=objindex%>&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
					} },

			]
		});
	});

function onlineDataFun(inputData){
//alert(inputData);
	$.post("onlineApply!onlineApplySave",{affairid:<%=affairid%>,objindex:<%=objindex%>,openid:'<%=openid%>',onlineData:inputData,iscommit:'true'},function(res){
      	
      	alert("提交成功");
      	location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"onlineApply!isrelation?affairid=<%=affairid%>_affairMaterialsByWrite_<%=objindex%>&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
         
     })

}
</script>

