<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String type = request.getParameter("type");
%>

<html>
<head>
<!-- Mobile Devices Support @begin -->
<meta content="application/xhtml+xml;charset=utf-8"
	http-equiv="Content-Type">
<meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
<meta content="no-cache" http-equiv="pragma">
<meta content="0" http-equiv="expires">
<meta content="telephone=no, address=no" name="format-detection">
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- apple devices fullscreen -->
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent">
<meta
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<!-- Mobile Devices Support @end -->
<title>认证绑定</title>
<script type="text/javascript"
	src="yms/window/weiXinAppointment/js/jquery-1.10.1.min.js"></script>
<link rel="stylesheet" href="lib/weui.min.css" type="text/css" />
<link rel="stylesheet" href="yms/window/weiXinAppointment/css/common.css" type="text/css"/>

<script type="text/javascript" src="yms/common/js/ajaxForm.js"></script>
<script type="text/javascript"
	src="yms/window/weiXinAppointment/js/common.js"></script>
<style type="text/css">
.tabbar {
	display: -webkit-box;
	display: -webkit-flex;
	display: flex;
	position: absolute;
	z-index: 500;
	bottom: 0;
	width: 100%;
	background-color: #f7f7fa
}
</style>
</head>

<body>

<!-- 个人事项 -->
	<div class="mark_div" id="personal_list_div" style="display: block;">
		<div class="lobby" id='affairsItem'>
			
			<div class="lobby-main" id="personalList">
				<s:iterator value="ssUserList">
					<li>
						<a 
						href='relation.jsp?id=<s:property value="id" />' ><s:property value="name" />
							<h4>
								<p ><h style="text-align: left;"><s:property value="idCard" /></h>
								
									<h style="text-align: right;"><s:set name="isOrther" value="isOrther" />
									<s:if test="#isOrther=='false'">本人</s:if><s:else>他人</s:else></h>
								</p>
							</h4>
						</a>
						
					</li>
				</s:iterator>
				
			</div>
			<!-- <div id="loadingdiv_personal" class="loading" style="display: block;"><img src="yms/window/weiXinAppointment/images/loading2.gif" alt=""></div> -->
			
		</div>
		
	</div>
	<div class="weui-tabbar">
				<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/relation.jsp?type=<%=type %>&paramVal=&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect" class="weui-tabbar__item">
					<div class="weui-tabbar__icon">
						<img src="icon/ADD.png" alt="" />
					</div>
					<p class="weui-tabbar__label main_color" style="margin:0px">添加绑定</p>
				</a>
	</div>
</body>
</html>