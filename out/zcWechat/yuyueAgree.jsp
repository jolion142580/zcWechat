<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,height=device-height, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>预约须知</title>
<link rel="stylesheet" href="css/sm-extend.min.css" type="text/css" />
<link rel="stylesheet" href="css/sm.min.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css" />
<link rel="stylesheet" href="lib/weui.min.css" type="text/css" />
<link rel="stylesheet" href="css/guide-style.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
<style type="text/css">
.main_color {
	color: #FFFFFF;
}

.weui-cell__bd .number {
	color: #c40e24;
}
</style>
</head>

<body>
	<div class="page-group">
		<div class="page page-current">

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
							<p>1. 网上预约办理需最少提前1天预约，可预约范围为未来5个工作日（从次日起算），暂不支持当天预约办理；</p>
							<p>2. 预约过程中需点选办事地点、预约时间段才能进行预约，预约成功后应在已预约成功的时间段内凭借办事人本人身份证号码或预约手机号码到预约地点取号办理，逾时预约号将失效（注：支持提前15分钟内取号）；</p>
							<p>3.为确保办事公平，每名用户1天只能够使用1次预约功能，每个自然月只能使用3次。预约成功后如在一个自然季度内失约3次（包括未有取号、逾时取号），将在本自然季度及下一个自然季度期间被限制使用预约功能。</p>
							
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
						<span style='font-size:12px;'>版权所有：蓬江一门式&nbsp;&nbsp;技术支持：南邮英科</span>
					</div>
				</div>
			</div>

		</div>
	</div>
	
</body>

<script type="text/javascript">
	function yuyueSure() {
		location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/ServerType!findListByIsShow&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	}
</script>

</html>