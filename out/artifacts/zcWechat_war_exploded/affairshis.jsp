<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String code = request.getParameter("code");
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
<title>历史记录</title>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="yms/window/weiXinAppointment/css/common.css"
	type="text/css"></link>
<script type="text/javascript" src="yms/common/js/ajaxForm.js"></script>
<script type="text/javascript"
	src="yms/window/weiXinAppointment/js/common.js"></script>

</head>

<script type="text/javascript">
function affairsSerach(){

	$.post("Affairhis!findByAffairName",{ affairname: $("#affairname").val(),openid:"${list[0].openid}"} ,function(data) {
			  //alert(data);
			  $("#personalList").empty();
			 
			  var content="";
			  if(data.length<=0)content='<li><a href="javascript:void(0);"><h4><div class="item-title">暂无数据</div><p>暂无数据[暂无数据]</p></h4></a></li>';
        	   $.each(data, function(k, v) {
                  //$.each(v, function(kk, vv) {
                	  //alert(v.affairname);
                	  
                	  var subAffairName="";
                	  //if(v.affairname.length>18){
                	  //	subAffairName=v.affairname.substring(0,18)+"...";
                	  //}else{
                	  	subAffairName=v.affairname;
                	  //}
                	  content+="<li><a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/ssAffairsGuideInfo!findByAffairId?affairid="+v.affairid+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect\">"+subAffairName+" <h4> </h4> </a> </li>";
                    //   content+="<li><a href=\"ssAffairsGuideInfo!findByAffairId?affairid="+v.affairid+"\">"+subAffairName+" <h4> </h4> </a> </li>";
                      
                      
                  //});
              });
              $("#personalList").append(content);
        }, "json");

}
</script>
<body>
	<div class="header" id="header_">
		<div class="search-bar" id="search_bar">
			 <input type="search" class="input-txt" placeholder="请输入事项关键字查询" onkeyup="affairsSerach();"  oninput="affairsSerach();"
				id="affairname" value=""> <input type="hidden" id="old_keyword">

		</div>
	</div>
<!-- 个人事项 -->
	<div class="content-1 mark_div" id="personal_list_div" style="display: block;">
		<div class="lobby" id='affairsItem'>
			<h3>您查询过的历史事项</h3>
			<div class="lobby-main" id="personalList">
				<s:iterator value="list">
					<li><a
						href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/ssAffairsGuideInfo!findByAffairId?affairid=<s:property value="affairid" />&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect"><s:property value="affairname" />

					</a>
					</li>
				</s:iterator>
				
			</div>
			<div id="loadingdiv_personal" class="loading" style="display: none;"><img src="yms/window/weiXinAppointment/images/loading2.gif" alt=""></div>
		</div>
	</div>
</body>
</html>