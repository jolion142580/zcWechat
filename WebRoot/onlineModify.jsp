<%@page import="com.gdyiko.zcwx.po.OnlineApply"%>
<%@page import="com.gdyiko.zcwx.service.OnlineApplyService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.gdyiko.zcwx.weixinUtils.OAuth"%>
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

<style type="text/css">
.main_color {
	color: #FFFFFF;
}

.weui-cells {
	margin-top: 0;
}

.weui-search-bar {
	background-color: #0490E4;
}

.weui-search-bar__form {
	background-color: #0490E4;
}

.weui-search-bar__cancel-btn {
	color: #FFFFFF;
}
</style>
</head>

<%

String onlineApplyId=request.getParameter("onlineApplyId");
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
OnlineApplyService onlineApplyService = (OnlineApplyService) ctx.getBean("onlineApplyService");  
OnlineApply onlineApply = onlineApplyService.findById(onlineApplyId);
//System.out.println("11111111111111111111111111111111onlineModify:::"+onlineApply.getOnlineData());
String affairid=onlineApply.getAffairid();
String openid = onlineApply.getOpenid();
String objindex = onlineApply.getObjindex();

%>

<body style="background-color:transparent">
	<div class="page-group">
		<div class="page page-current">
			<%-- <header class="bar bar-nav"
	        style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
	        <a class="button button-link button-nav pull-left" href="javascript:;" onclick="javascript:history.back(-1);"><span class="icon icon-left main_color"></span></a>
	        <h1 class="title main_color">网上办事</h1>
	        </header> --%>

			<div class="content">

				  <div class="weui-tab">
					  <div class="weui-tab__bd" style="padding-bottom:85px;">
					    <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
					      <iframe src="dist/#/preview?affairid=<%=affairid%>&onlineApplyId=<%=onlineApplyId %>&objindex=<%=objindex %>" width="100%" height="100%" align="center" scrolling="auto" frameborder="0" allowTransparency="true" ></iframe>
					    </div>
					    <!-- <div id="tab2" class="weui-tab__bd-item">
					      <iframe src="" width="100%" height="100%" align="center" scrolling="auto" frameborder="0" allowTransparency="true" ></iframe>
					     
					    </div> -->

					  </div>
					
					  <div class="weui-tabbar">
					    <a href="#tab1" class="weui-tabbar__item">
					      <div class="weui-tabbar__icon">
					        <img src="./images/icon_nav_article.png" alt="">
					      </div>
					      <p class="weui-tabbar__label">信息修改</p>
					    </a>
					    <a id="a_href_SET" >
					    	<div class="weui-tabbar__icon">
					        	<img src="./images/icon_nav_msg.png" alt="">
					      	</div>
					      	<p class="weui-tabbar__label">附件上传</p>
					    </a>
					  </div>
					</div>

		</div>
	</div>

</body>
</html>
<script type="text/javascript" src="js/config.js"></script>

<script>
	$(function () {
		var HREF = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.WeChatDNSURL+'onlineApply!modifyAffairMaterials?id=<%=onlineApplyId %>&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect" class="weui-tabbar__item';
		$("#a_href_SET").attr("href",HREF);
	})
function onlineDataFun(inputData){
//alert(inputData);
	$.post("onlineApply!onlineApplyDataModify",{myid:'<%=onlineApplyId%>',onlineData:inputData},function(res){
      	
      	alert("提交成功");
  
     })

}
</script>
