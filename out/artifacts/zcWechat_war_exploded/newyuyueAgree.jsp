<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String affairname = request.getParameter("affairname");
//	System.out.println("预约业务："+affairname);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,height=device-height, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>张槎街道行政服务中心</title>
<link rel="stylesheet" href="css/sm-extend.min.css" type="text/css" />
<link rel="stylesheet" href="css/sm.min.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css" />
<link rel="stylesheet" href="lib/weui.min.css" type="text/css" />
<link rel="stylesheet" href="css/guide-style.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="js/config.js"></script>

<style type="text/css">
.main_color {
	color: #ffffff;
}

.weui-cell__bd .number {
	color: #c40e24;
}
</style>
</head>

<body>
	<div class="page-group">
		<div class="page page-current">
			<%--<header class="bar bar-nav"
				style="background-image:url(images/bar_nav_bg_purple.png);background-size: contain;background-repeat: repeat-x;background-color:#FFF ">
			<img src="images/bar_nav_left_purple.png"
				style="position: absolute; left: -0.5rem;height: 2.2rem;" /> <a
				class="button button-link button-nav pull-left" href="javascript:;"
				onclick="javascript:history.back(-1);"> <span
				class="icon icon-left" style=" top:10px;color:#911edb "></span> </a>
			<h1 class="title" style="color:#911edb">预约须知</h1>
			</header>
			<div class="header-bar" style="background-color: #911edb">
				<div>PERSONAL INFORMATION REGISTRATION</div>
			</div>--%>
				<header class="bar bar-nav"
						style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
					<a class="button button-link button-nav pull-left" href="javascript:;"
					   onclick="javascript:WeixinJSBridge.call('closeWindow');">
						<span class="icon icon-left main_color" style="margin-top: 11px;"></span></a>
					<h1 class="title main_color">预约须知</h1>
				</header>
			<div class="content">

				<form id="accountform" action="" method="post"
					onsubmit="return checkYanzhengma()"
					style="padding:15px 15px 30px 15px; margin:15px; border-radius:10px; background:#FFF;"
					class="fonts">
					<fieldset>
						<legend>
							<span>注意事项</span>
						</legend>

						<div class="form-group">
							<p>1. 需要提前一天进行预约，并选取预约的时间段；</p>
							<p>2. 预约实名制，预约人须如实提供个人信息，且只能办理与本人相关的业务，否则预约失效；</p>
							<p>3. 在预约时间段内到办事大厅前台凭身份证或预约编号取号（可提前15分钟取号），预约号会优先安排办事；</p>
							<p>4. 预约群众在一年内超过3次失约，将被限制使用预约功能；</p>
							<p>5. 每个用户一天只能使用1次预约功能。</p>
						</div>
						<div class="weui-cells">

							<div class="weui-cell">
								<div class="weui-cell__bd">
									<button name="Submit" class="weui-btn weui-btn_primary"
										type="button" id="button1id" onclick="yuyueSure()">确认</button>
								</div>
								&nbsp;&nbsp;&nbsp;
								<div class=" weui-cell__bd">
									<button class="weui-btn weui-btn_warn" id="button2id"
										type="button" onClick="WeixinJSBridge.call('closeWindow');">取消</button>
								</div>
							</div>
							<!-- <div class="weui-cell" style="padding:10px">
						<button name="Submit" class="weui-btn weui-btn_primary"  type="button" id="button1id" onclick="yuyueSure()">确认</button>
						&nbsp;&nbsp;&nbsp;
						<button  class="weui-btn weui-btn_warn"  id="button2id" type="button" onClick="WeixinJSBridge.call('closeWindow');">取消</button>
					</div> -->
						</div>
					</fieldset>
				</form>
				<input type="hidden" id="codeResult"></input>
				<div class=" foot fixPosition width100 bottomPosition iff">
					<div align="center" style="line-height:25px;">
						<span style='font-size:12px;'>张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
					</div>
				</div>
			</div>

		</div>
	</div>
	
</body>

<script type="text/javascript">
	function yuyueSure() {
	    //添加 检测该用户是否有失约

//		location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaec78dd064e22ada&redirect_uri=http://zhengqiao.ss.gov.cn/ssWechat/YuYues!yuYues&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
// 		location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"YuYues!yuYues&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"YuYues!yuYues?affairname=<%=affairname%>&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	}
</script>

</html>