<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.*" %>
<%@page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>


<%
	String code = request.getParameter("code");
	OAuth oauth=new OAuth();
    String openid=oauth.getOppenid(code);
    //String openid="oC8VwwhUqXU64FxAVyiVlPNhJ9Fc";
    //System.out.println("2222---"+openid);
    
 %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>我的预约</title>
<link href="bootstrap3/css/base.css" rel="stylesheet">
<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap3/css/bootstrap.pack.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
</head>
<body style="background:#ececec;">
 <div class=" head fixPosition width100 topPosition">
    <div class=" hd rePosition">
      <span class="topLeft"><a href="#" onClick="WeixinJSBridge.call('closeWindow');"><img src="bootstrap3/images/back_ico.png" width="50"  ></a></span>
      <ul>
        <li class="navlist01">我的预约</li>
      </ul>
    </div>
  </div> 

<!-- 预约办事表单 -->
  <div class="contant">
    <div class="bconetnt">
      
      <div class="yt_bg" >
        <span class="Left yt yt_b">预约时段</span>
        <span class="Right yr">预约编号</span>
      </div>
      <div class="yt_m" id="newMessage">
        <%-- <div class="yt_list">
          <a href="#">
          <span class="Left yt ">2016-12-28 08:30-09:30</span>
          <span class="Right yr">10002754</span>
          </a>
        </div>
        <div class="yt_list">
          <a href="#">
          <span class="Left yt ">2016-12-28 08:30-09:30</span>
          <span class="Right yr">10002754</span>
          </a>
        </div> --%>
      </div>
      
      <div class="yt_m wq" id="oldMessage">
        <div class="wq_t">往期预约</div>
        <%-- <div class="wq_list">
			没有相关预约信息
        </div>
        <div class="wq_list">
        	<span class="Left yt ">2016-12-28 08:30-09:30</span>
        	<span class="Right yr">10002754</span>
        </div> --%>
      </div>
      
    </div>
  </div>
  <div class=" foot fixPosition width100 bottomPosition">
    <div align="center" style="line-height:25px;"><span style='font-size:12px;'>版权所有：三水区行政服务中心&nbsp;&nbsp;技术支持：南邮英科</span></div>    
  </div>
</body>
<script type="text/javascript">
$(function(){

	getList();
	
});
<%-- var code = "<%=code%>"; --%>
function getList(){
	$.ajax({
		url:'YuYues!userYuYuesJson',
		type:'POST',
		dataType:"JSON",
		data:{
			//code:code
			openid:'<%=openid%>'
		},
		success:function(r){
			console.log(r.old);
			console.log(r.future);
			layoutPage(r);
			
		},
		error:function(e){
			
		}
	});
}
function layoutPage(r){
	var old=r.old;
	var future=r.future;
	//加载往期预约
	if(old.length>0){
		for(var i in old){
			var time=old[i].ydate.substring(0,10)+" "+old[i].ystime+"-"+old[i].yetime;
			$("#oldMessage").append(
				"<div class='wq_list'><a href='YuYues!singleYuYue?no="+
				old[i].no+"&idcard="+old[i].idcard+
				"&type='><span class='Left yt'>"+
				time+
				"</span><span class='Right yr'>"+
				old[i].no+
				"</span></div>");
		}
	}else{
		$("#oldMessage").append("<div class='wq_list'>没有相关预约信息</div>");
	}
	//加载今天以后的预约
	if(future.length>0){
		for(var j in future){
			var time=future[j].ydate.substring(0,10)+" "+future[j].ystime+"-"+future[j].yetime;
			$("#newMessage").append(
				"<div class='yt_list'><a href='YuYues!singleYuYue?no="+
				future[j].no+"&idcard="+future[j].idcard+
				"&type=remove'><span class='Left yt'>"+
				time+
				"</span><span class='Right yr'>"+
				future[j].no+
				"</span></a></div>");
		}
	}else{
		$("#newMessage").append("<div class='yt_list'>没有相关预约信息</div>");
	}
}
</script>
</html>