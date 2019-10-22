<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.*" %>
 <%@page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>


<%
String code = request.getParameter("code");
	OAuth oauth=new OAuth();
    //String openid=oauth.getOppenid(code);
     String openid="ovh5dxGh-9EXBe-fFYD5IU1fSW4k";
    System.out.println("2222---"+openid);
    
 %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="generator"
    content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39" />
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>蓬江邑门式</title>
   
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
     <link rel="shortcut icon" href="/favicon.ico" />
	<!-- <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css" /> -->
	 <link rel="stylesheet" href="css/sm.min.css" type="text/css" />  
	<link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css" />
	<link rel="stylesheet" href="lib/weui.min.css" type="text/css" />
	<link rel="stylesheet" href="css/guide-style.css" type="text/css" />

    <style type="text/css">
         .main_color{
         color:#FFFFFF;}
.weui-navbar + .weui-tab__bd  {
    padding-top: 6rem;
}
p {
    margin: 0;
}
.content{
background-color:#FFF;
}
.re_num{
font-size:2rem;
color:#8fc320;
}
.weui-bar__item--on .re_num {
    color: #f39801;
}
.weui-navbar__item.weui-bar__item--on {
background-image: url(icon/selected.png);
    background-repeat: no-repeat;
    background-size: 100%;
    background-position-y: 100%;
	background-color:#FFF;
}
.weui-navbar__item{
background-color:#FFF;
}
.weui-navbar__item:after{
border-right:0px;
}
.placeholder{
color:#888;
}

.rtext{
font-size:14px;
color:#555;
}
.weui-cells{
margin-top:0px;
}

</style>
  </head>
  <body ontouchstart="">
  <div class="page-group">
    <div class="page page-current">
      <header class="bar bar-nav"
      style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
      <!-- <img src="images/bar_nav_left_purple.png" style="position: absolute;left: -0.5rem;height: 2.2rem;" />  -->
      <a class="button button-link button-nav pull-left" href="javascript:;" onclick="WeixinJSBridge.call('closeWindow');">
        <span class="icon icon-left main_color"></span>
      </a>
      <h1 class="title" style="color:#FFFFFF">我的预约记录</h1></header>
      <!-- <div class="header-bar" style="background-color: #911edb">
         <div>PERSONAL INFORMATION REGISTRATION</div>
      </div> -->
      <div class="content">
        <div class="weui-tab">
          <div class="weui-navbar">
          <a class="weui-navbar__item weui-bar__item--on" href="#tab1" >
            <span>本月预约</span>
            <span class="re_num" id="new">0</span>
          </a> 
          <a class="weui-navbar__item" href="#tab2">
            <span>往期预约</span>
            <span class="re_num" id="old">0</span>
          </a></div>
          <div class="weui-tab__bd">
            <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
              <div class="weui-flex" style="text-align:center;background-color:#efefef;margin: 0.5rem;padding: 0.5rem;">
                <div class="weui-flex__item">
                  <div class="placeholder">预约时段</div>
                </div>
                <div class="weui-flex__item">
                  <div class="placeholder">预约网点</div>
                </div>
                <div class="weui-flex__item">
                  <div class="placeholder">预约编号</div>
                </div>
                
              </div>
              <div class="weui-cells" id="newMessage">

              </div>
            </div>
            <div id="tab2" class="weui-tab__bd-item">
              <div class="weui-flex" style="text-align:center;background-color:#efefef;margin: 0.5rem;padding: 0.5rem;">
                <div class="weui-flex__item">
                  <div class="placeholder">预约时段</div>
                </div>
                <div class="weui-flex__item">
                  <div class="placeholder">预约网点</div>
                </div>
                <div class="weui-flex__item">
                  <div class="placeholder">预约编号</div>
                </div>
               
              </div>
              <div class="weui-cells" id="oldMessage">
              
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="lib/jquery-2.1.4.js"></script> 
  <script src="js/jquery-weui.js"></script>
  <%-- <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script> --%>
<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
 <script type="text/javascript">
 $(function(){

	getList();
	
});
function getList(){
	$.ajax({
		url:'yuYuesFull!userYuYuesFullJson',
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

var timestamp = Date.parse(new Date());
var old=r.old;
	var future=r.future;
	//加载往期预约
	$("#old").text(old.length);
	if(old.length>0){
		for(var i in old){
			var time=old[i].ydate.substring(0,10)+" "+old[i].ystime+"-"+old[i].yetime;
			$("#oldMessage").append(
				"<div class='weui-cell'><div class='weui-cell__bd' style='float:left;'>"+
				"<a class='weui-flex' style='text-align:center;' href='YuYues!singleYuYue?no="+old[i].no+"&idcard="+old[i].idcard+"&type=' >"+
				"<div class='weui-flex__item'>"+
				"<div class='rtext'>"+old[i].ydate.substring(0,10)+"<p>"+old[i].ystime+"-"+old[i].yetime+"</p></div>"+
				"</div><div class='weui-flex__item'>"+
				"<div class='rtext'>"+old[i].streetname+"</div>"+
				"</div>><div class='weui-flex__item'>"+
				"<div class='rtext'>"+old[i].no+"</div>"+
				"</div></a></div><div calss='weui-flex__item'>"+
				"<button id=\"old_"+i+"\" style='float:left;display:block;' onclick=\"cancelYuYue('"+old[i].no+"','"+old[i].idcard+"','"+old[i].ydate.substring(0,10)+"','"+old[i].ystime+"')\" class='weui-btn weui-btn_primary' style='line-height:1.565;height:28px;'>取消</button>"+
				"</div></div>");
						var s_time = old[i].ydate.substring(0,10)+" "+old[i].ystime;
						s_time = new Date(Date.parse(s_time.replace(/-/g, "/")));
						
						var date1 = s_time.getTime();
						var times=new Date();
				//	alert(date1-timestamp);
					var date3=date1-timestamp;
					var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数
				var hours=Math.floor(leave1/(3600*1000));
				//alert(hours);
						
				if ( old[i].state != 0 || hours < 2) {
			
							$("#old_"+i).addClass("weui-btn_disabled");
							$("#old_"+i).removeAttr("onclick");
					}
		};
	}else{
		$("#oldMessage").append(
			"<div class='weui-cell'><div class='weui-cell__bd'><div class='weui-flex' style='text-align:center;'><div class='weui-flex__item'>没有相关预约信息"+
			"</div></div></div></div>");
	}
	
	$("#new").text(future.length);
	
	//加载今天以后的预约
	if(future.length>0){
		for(var j in future){
			var time=future[j].ydate.substring(0,10)+" "+future[j].ystime+"-"+future[j].yetime;
			$("#newMessage").append(
				"<div class='weui-cell'><div class='weui-cell__bd' style='float:left;'>"+
				"<a class='weui-flex' style='text-align:center;display: flex;'  href='YuYues!singleYuYue?no="+future[j].no+"&idcard="+future[j].idcard+"&type=remove' >"+
				"<div class='weui-flex__item'>"+
				"<div class='rtext'>"+future[j].ydate.substring(0,10)+"<p>"+future[j].ystime+"-"+future[j].yetime+"</p></div>"+
				"</div><div class='weui-flex__item'>"+
				"<div class='rtext'>"+future[j].streetname+"</div>"+
				"</div><div class='weui-flex__item'>"+
				"<div class='rtext'>"+future[j].no+"</div>"+
				"</div></a></div><div calss='weui-flex__item' style='display: flex;'>"+
				"<button id=\"future_"+j+"\" style='float:left;display:block;' onclick=\"cancelYuYue('"+future[j].no+"','"+future[j].idcard+"','"+future[j].ydate.substring(0,10)+"','"+future[j].ystime+"')\" class='weui-btn weui-btn_primary' style='line-height:1.565;height:28px;'>取消</button>"+
				"</div></div>");
				
				var s_time = future[j].ydate.substring(0,10)+" "+future[j].ystime;
						s_time = new Date(Date.parse(s_time.replace(/-/g, "/")));
						
						var date1 = s_time.getTime();
						var times=new Date();
				//	alert(date1-timestamp);
					var date3=date1-timestamp;
					var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数
				var hours=Math.floor(leave1/(3600*1000));
				//alert(hours);
						
				if ( future[j].state != 0 || hours < 2) {
			
							$("#future_"+j).addClass("weui-btn_disabled");
							//$("#future_"+j).removeClass("weui-btn_disabled");
							$("#future_"+j).removeAttr("onclick");
					}
		}
	}else{
		$("#newMessage").append(
		"<div class='weui-cell'><div class='weui-cell__bd'><div class='weui-flex' style='text-align:center;'><div class='weui-flex__item'>没有相关预约信息"+
		"</div></div></div></div>");
	}

}


// 取消预约
function cancelYuYue(no,idcard,ydate,ystime) {

		if (no == null || idcard == null || no == '' || idcard == '') {
			return;
		}
		
		 $.confirm("确定要取消该预约吗？", "确认取消?", function() {
         $.ajax({
				url : 'YuYues!cancelYuYue',
				data : {
					no : no,
					idcard : idcard,
					ydate : ydate,
					ystime : ystime
					},
				type : "POST",
				dataType : "JSON",
				success : function(r) {
							//alert(r.msg);
							 $.alert(r.msg);
							window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaec78dd064e22ada&redirect_uri=http://zhengqiao.ss.gov.cn/ssWechat/wdyy_2.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
						},
				error : function(e) {
						}
					});
        }, function() {
          //取消操作
        });

	}
 </script>
  </body>
</html>
