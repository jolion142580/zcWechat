<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.*" %>
 <%@page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>


<%
	String code = request.getParameter("code");
 	OAuth oauth=new OAuth();
    String openid=oauth.getOppenid(code); 
    
 %>
<!DOCTYPE html>
<html>
<head>
<!-- Mobile Devices Support @begin -->
<meta content="application/xhtml+xml;charset=UTF-8" http-equiv="Content-Type">
<meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
<meta content="no-cache" http-equiv="pragma">
<meta content="0" http-equiv="expires">
<meta content="telephone=no, address=no" name="format-detection">
<meta name="apple-mobile-web-app-capable" content="yes" /> <!-- apple devices fullscreen -->
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<!-- Mobile Devices Support @end -->
<title>我的预约</title>
<link rel="stylesheet" href="yms/window/weiXinAppointment/css/common.css" type="text/css"></link>
<link rel="stylesheet" href="lib/weui.min.css" type="text/css"></link>
<link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="yms/window/weiXinAppointment/js/common.js"></script>
<script type="text/javascript" src="lib/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/jquery-weui.min.js"></script>

</head>
<script type="text/javascript">



	// 取消预约
function cancelYuYue(no,idcard,ydate,ystime,state) {

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
					ystime : ystime,
					state  :state
					},
				type : "POST",
				dataType : "JSON",
				success : function(r) {
							//alert(r.msg);
							 $.alert(r.msg);
							window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/wdyy_2.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
						},
				error : function(e) {
						}
					});
        }, function() {
          //取消操作
        });

	}
	//历史记录
/* 	function toMyHistory(){
		location.href="weiXinAppointmentAction.struts?actionType=toHistoryAppointment&openid=ojPtkwopQCd3wXfU8vZeT8kg3FJo&fromSystem=";
	} */
	var data_old;
	var data_future;
	var street;
	
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
			data_future=r.future;
			data_old=r.old;
			layoutPage(data_future);
			//alert(data_future);
		},
		error:function(e){
			
		}
	});
}
	
	function layoutPage(r){
	var timestamp = Date.parse(new Date());
	$("#yuyuelist").empty();
	if(r==data_future){
		$('#future').addClass('cur');
		$('#old').removeClass('cur');
	}else{
		$('#old').addClass('cur');
		$('#future').removeClass('cur');
	}
	
	$('#size').text(r.length==null?0:r.length);
	//var data =eval(r);
	if(r.length>0){
		for(var i in r){
		var state="";
		
		if(r[i].state==1){
			state="已签到";
		}else if(r[i].state==0){
			state="未签到";
		}if(r[i].state==2){
			state="已取消";
		}
		var time=r[i].ydate.substring(0,10)+" "+r[i].ystime+"-"+r[i].yetime;
		
		var list = "<li class=\"orange-app\"><h4 id='no'>预约号："+r[i].no+"</h4><div class=\"appointment-main\">"+
				"<h5>预约人：</h5><p >"+r[i].name+"</p><h5>证件号码：</h5><p>"+r[i].idcard"</p>"+
				"<h5>预约大厅：</h5><p >"+r[i].streetname+"</p><h5>预约事项：</h5><p>"+r[i].stypename+"-"+r[i].businessName+"</p>"+
				"<h5>预约时间：</h5><p><span id=\"ydate\">"+r[i].ydate.substring(0,10)+"</span>&nbsp;"+
				"<span id=\"ystime\">"+r[i].ystime+"</span>"+"-"+
				"<span id=\"yetime\">"+r[i].yetime+"</span>"+"</p>"+
				"<h5>状态：</h5><div class=\"stat\">"+state+"</div><div class=\"clear\">";
				if(r[i].state==1){
					list+="<a id=\"cancel_"+i+"\" style=\"display:block;color:#d5d5d6\" href=\"javascript:cancelYuYue('"+r[i].no+"','"+r[i].idcard+"','"+r[i].ydate.substring(0,10)+"','"+r[i].ystime+"','"+r[i].state+"')\" class=\"cancel-btn right\">取消预约</a>";
					list+="<spa  class=\"right\">&nbsp;&nbsp;&nbsp;&nbsp;</spa><a class=\"pingyi-btn right\" href=\"PingJia!getPingJiaPage.action?myid="+r[i].id+"\">评价</a>";
//					list+="<spa  class=\"right\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</spa><a class=\"pingyi-btn right\" href=\"http://ymswx.pjq.gov.cn/pjWechat/ssBaseDicInfo!findAllByBaseDicType?baseDicType=D001\">办事指南</a>";
				}else if(r[i].state==0){
					list+="<a id=\"cancel_"+i+"\" style=\"display:block;\" href=\"javascript:cancelYuYue('"+r[i].no+"','"+r[i].idcard+"','"+r[i].ydate.substring(0,10)+"','"+r[i].ystime+"','"+r[i].state+"')\" class=\"cancel-btn right\">取消预约</a>";
					list+="<spa  class=\"right\">&nbsp;&nbsp;&nbsp;&nbsp;</spa><a class=\"pingyi-btn right\" href=\"PingJia!getPingJiaPage.action?myid="+r[i].id+"\">评价</a>";
//					list+="<spa  class=\"right\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</spa><a class=\"pingyi-btn right\" href=\"http://ymswx.pjq.gov.cn/pjWechat/ssBaseDicInfo!findAllByBaseDicType?baseDicType=D001\">办事指南</a>";
				}else if(r[i].state==2){
					list=list;
				}
					list+="<spa  class=\"right\">&nbsp;&nbsp;&nbsp;&nbsp;</spa><a class=\"bszn-btn right\" href=\"http://ymswx.pjq.gov.cn/pjWechat/ssBaseDicInfo!findAllByBaseDicType?baseDicType=D001\">办事指南</a>";
				
				//list+="<spa  class=\"right\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</spa><a class=\"pingyi-btn right\" href=\"PingJia!getPingJiaPage.action?myid="+r[i].id+"\">评价</a>"+
				list+="</div></div></li>"+"</div></div></li>"+
				"<input type=\"hidden\" id=\"idcard\" value=\""+r[i].idcard+"\" />";
		
		$("#yuyuelist").append(list);
			
			var s_time = r[i].ydate.substring(0,10)+" "+r[i].ystime;
						s_time = new Date(Date.parse(s_time.replace(/-/g, "/")));
						var date1 = s_time.getTime();
						
				if ( r[i].state == 0 && date1 > timestamp) {
							$("#cancel_"+i).css("display", "block");
					}
			}
		}else{
			$('#yuyuelist').append(
				"<div '><div ><div  style='text-align:center;'><div >没有相关预约信息"+
				"</div></div></div></div>");
		} 
	}
	


	 $(function(){

	getList();
	
	});
</script>
	
<body>
	
	<div class="header">
		<div class="tabs">
			<div class="clear">
				<ul>
					<li class="cur left" id='future'><a href="javascript:layoutPage(data_future);">本月预约</a></li>
					<li class=" left" id='old'><a href="javascript:layoutPage(data_old);">往期预约</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="content-1 appointment">
			<h3>当前共有<span id="size"></span>条预约内容</h3>
		<div class="appointment-box">
			<ul id='yuyuelist'>
				
			</ul>
		</div>
	</div>
</body>
</html>