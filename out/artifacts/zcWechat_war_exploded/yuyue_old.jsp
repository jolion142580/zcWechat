﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="com.gdyiko.zcwx.weixinUtils.Holiday"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta name="generator"
	content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>蓬江邑门式</title>
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
<style type="text/css">
.main_color {
	color: #FFFFFF;
}

.weui-cell__bd .number {
	color: #c40e24;
}
</style>
</head>
<%!String convert(int val) {
		String retStr = "";
		switch (val) {
			case 0 :
				return "星期日";
			case 1 :
				return "星期一";
			case 2 :
				return "星期二";
			case 3 :
				return "星期三";
			case 4 :
				return "星期四";
			case 5 :
				return "星期五";
			case 6 :
				return "星期六";
			default :
				break;
		}
		return retStr;
	}%>
<%
	//String join_id = request.getParameter("join_id");
	//System.out.println("------join_id-------"+join_id);

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	Holiday holiday = new Holiday();

	Date addDate = holiday.getIncomeDate(new Date());
	String date = format.format(addDate);
	String weekDay = convert(addDate.getDay());
	//System.out.println(":::::::::::::"+addDate);

	Date addDate2 = holiday.getIncomeDate(addDate);
	String date2 = format.format(addDate2);
	String weekDay2 = convert(addDate2.getDay());
	//System.out.println(":::::::::::::"+addDate2);

	Date addDate3 = holiday.getIncomeDate(addDate2);
	String date3 = format.format(addDate3);
	String weekDay3 = convert(addDate3.getDay());

	Date addDate4 = holiday.getIncomeDate(addDate3);
	String date4 = format.format(addDate4);
	String weekDay4 = convert(addDate4.getDay());

	Date addDate5 = holiday.getIncomeDate(addDate4);
	String date5 = format.format(addDate5);
	String weekDay5 = convert(addDate5.getDay());
%>
<body>
	<div class="page-group">
		<div class="page page-current">
			<header class="bar bar-nav"
	        style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
	        <!--<img src="images/bar_nav_left_red.png" style="position: absolute;left: -0.5rem;height: 2.2rem;" /> -->
	        <a class="button button-link button-nav pull-left" href="javascript:;" onclick="javascript:history.back(-1);">
	          <div class="icon icon-left main_color"></div>
	        </a>
	        <h1 class="title main_color">办事预约</h1>
			</header>
			
			<div class="content">
		<form id="accountform" action="" method="post" onsubmit="return checkYanzhengma()">
		<input type="hidden" name="ystime" id="ystime" value="">
    <input type="hidden" name="yetime" id="yetime" value="">
    <!-- <input type="hidden" name="street" id="street" value="0"> -->
    <input type="hidden" name="stype" id="stype" value="ZH001">
    
    <input type="hidden" name="openid" id="openid" value='<s:property value="openid"/>'>
          <div class="weui-cells__title">事项名称： 
          <span style="color:#f39900">综合业务</span></div>
          <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
              <div class="weui-cell__hd" >
                <label class="weui-label">办事人姓名：</label>
              </div>
              <div class="weui-cell__bd">
              
                <input name="name" class="weui-input" readonly="readonly"
				id="name" type="text" placeholder="请输入办事人姓名" value='<s:property value="name"/>'>
			<span class="help-block"></span>  
              </div>
            </div>
            <div class="weui-cell">
              <div class="weui-cell__hd">
                <label class="weui-label">身份证号码：</label>
              </div>
              <div class="weui-cell__bd">
                <input name="idcard" class="weui-input" readonly="readonly" 
				id="idcard" type="text" placeholder="请输入身份证号码" value='<s:property value="idcard"/>'>
			<span class="help-block"></span>  
              </div>
            </div>
            <div class="weui-cell">
              <div class="weui-cell__hd">
                <label for="" class="weui-label">联系电话：</label>
              </div>
              <div class="weui-cell__bd">
                <input name="phone" class="weui-input" readonly="readonly"
				id="phone" type="text" placeholder="请输入联系电话" value='<s:property value="phone"/>'>
			<span class="help-block"></span>  
              </div>
            </div>
            <div class="weui-cell weui-cell_select weui-cell_select-after">
              <div class="weui-cell__hd">
                <label for="" class="weui-label">预约地点：</label>
              </div>
              <div class="weui-cell__bd">
                <select name="street" onchange="getStreet(this.value);"  id="street" class="weui-select">
		 	  <option value="" selected="selected">--请选择预约地点--</option>
		    </select>
			<span class="help-block"></span>  
              </div>
            </div>
			<div class="weui-cell weui-cell_select weui-cell_select-after">
        <div class="weui-cell__hd"><label for="" class="weui-label">预约时间：</label></div>
        <div class="weui-cell__bd">
            <select name="ydate" onchange="getCount(this.value);" id="ydate" class="weui-select">
 	  <option value="" selected="selected">--请选择预约时间--</option>
	  <option value="<%=date %>"><%=date %> (<%=weekDay %>)</option>
	  <option value="<%=date2 %>"><%=date2 %> (<%=weekDay2 %>)</option>
	  <option value="<%=date3 %>"><%=date3 %> (<%=weekDay3 %>)</option>
	  <option value="<%=date4 %>"><%=date4 %> (<%=weekDay4 %>)</option>
	  <option value="<%=date5 %>"><%=date5 %> (<%=weekDay5 %>)</option>
    </select>
    <span class="help-block"></span> 
              </div>
      </div>
          </div>
          <div class="weui-cells__title">预约时段</div>
<div class="weui-cells weui-cells_checkbox">
      
      <label class="weui-cell weui-check__label" for="radio0">
        <div class="weui-cell__hd">
          <input type="radio" class="weui-check" name="radio" id="radio0"  value="0" checked="checked">
          <i class="weui-icon-checked"></i>
        </div>
        <div class="weui-cell__bd">
          <span>08:30-09:30&nbsp;&nbsp;剩余预约名额 <span id="time0" class="number">0</span></span>
        </div>
      </label>
      
      <label class="weui-cell weui-check__label" for="radio1">
        <div class="weui-cell__hd">
          <input type="radio" class="weui-check"  name="radio" id="radio1" value="1">
          <i class="weui-icon-checked"></i>
        </div>
        <div class="weui-cell__bd">
          <span>09:30-10:30&nbsp;&nbsp;剩余预约名额 <span id="time1" class="number">0</span></span>
        </div>
      </label>
	  
	  <label class="weui-cell weui-check__label" for="radio2">
        <div class="weui-cell__hd">
          <input type="radio" name="radio" class="weui-check" id="radio2" value="2">
          <i class="weui-icon-checked"></i>
        </div>
        <div class="weui-cell__bd">
          <span>10:30-11:30&nbsp;&nbsp;剩余预约名额<span id="time2" class="number">0</span></span>
        </div>
      </label>
      
      <label class="weui-cell weui-check__label" for="radio3">
        <div class="weui-cell__hd">
          <input type="radio" name="radio" class="weui-check" id="radio3" value="3">
          <i class="weui-icon-checked"></i>
        </div>
        <div class="weui-cell__bd">
         <span>14:30-15:30&nbsp;&nbsp;剩余预约名额<span id="time3" class="number">0</span></span>
        </div>
      </label>
      
      <label class="weui-cell weui-check__label" for="radio4">
        <div class="weui-cell__hd">
          <input type="radio" name="radio" class="weui-check" id="radio4" value="4">
          <i class="weui-icon-checked"></i>
        </div>
        <div class="weui-cell__bd">
         <span>15:30-16:30&nbsp;&nbsp;剩余预约名额<span id="time4" class="number">0</span></span>
        </div>
      </label>
      
      <label class="weui-cell weui-check__label" for="radio5">
        <div class="weui-cell__hd">
          <input type="radio" name="radio" class="weui-check" id="radio5" value="5">
          <i class="weui-icon-checked"></i>
        </div>
        <div class="weui-cell__bd">
         <span>16:30-17:30&nbsp;&nbsp;剩余预约名额<span id="time5" class="number">0</span></span>
        </div>
      </label>
    </div>
    <div class="weui-cells__title">验证码</div>
	<div class="weui-cell weui-cell_vcode">
		<div class="weui-cell__bd">
			<input id="code" name="code" class="weui-input" type="number" placeholder="请输入验证码" />
		</div>
		<div class="weui-cell__ft">
		<img id="randomming" class="weui-vcode-img"
			src="" onclick="reloadCode()"   height="40" 
			title="点击更换验证码" alt="点击更换验证码" /> 
		<input type="hidden" id="codeResult"></input>
		</div>
	</div>
	<div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:" id="button1id" onclick="save()" style="background-color: #911edb">提交并保存</a>
          </div>
		  </form>
		  <div class=" foot fixPosition width100 bottomPosition iff">
					<div align="center" style="line-height:25px;">
						<span style='font-size:12px;'>版权所有：蓬江邑门式&nbsp;&nbsp;技术支持：南邮英科</span>
					</div>
				</div>
	</div>


		</div>
	</div>
	
</body>

<script type="text/javascript">

//提交
function commit(){
	$.post('', $("form").serialize(), function(data) {});
}
//验证码
function reloadCode() { 
	var image = document.getElementById("randomming");
	if(null!=image){
		image.src="image.jsp?"+Math.random();
	}
}

 $(function(){
	reloadCode();
	
	$.post("<%=basePath%>Street!findAll" ,function(result) {
			  //alert(result);
			result.sort(sortBy('id', false, parseInt));	
			var content="";			
			$.each(result, function(k, v) {
       			
                //alert(v.name);
                content+="<option value='"+v.no+"'>"+v.name+"</option>";	
            });
 			$("#street").append(content);
			
        }, "json");

  });
  
  var sortBy = function (filed, rev, primer) {
    rev = (rev) ? -1 : 1;
    return function (a, b) {
        a = a[filed];
        b = b[filed];
        if (typeof (primer) != 'undefined') {
            a = primer(a);
            b = primer(b);
        }
        if (a < b) { return rev * -1; }
        if (a > b) { return rev * 1; }
        return 1;
    }
};

function getStreet(street){
	 $("#ydate  option[value=''] ").attr("selected",true)
}


//通过日期获得剩余号数
function getCount(ydate){
	
	var street=$('#street').val();
	if(street==""||street==null){
		alert("请选择预约地点！");
		return;
	}
	//alert(ydate);
	if(ydate==""){
		$('#time0').html(0);
		$('#time1').html(0);
		$('#time2').html(0);
		$('#time3').html(0);
		$('#time4').html(0);
		$('#time5').html(0);
		return;
	}
	$.ajax({
		url:'YuYues!getCount',
		data:{
			ydate:ydate,
			street:street
		},
		type:'POST',
		success:function(r){
			//alert(r);
			var obj = JSON.parse(r);
			$('#time0').html(obj.time0);
			$('#time1').html(obj.time1);
			$('#time2').html(obj.time2);
			$('#time3').html(obj.time3);
			$('#time4').html(obj.time4);
			$('#time5').html(obj.time5);
		},
		error:function(e){
			
		}
	});
}
//保存
function save(){
	var radio=$("input[name='radio']:checked").val();
	var ydate=$("#ydate").val();
	var street=$('#street').val();
	var streetText = $("#street option:selected").text();
	
	if(street==""||street==null){
		alert("请选择预约地点！");
		return;
	}
	//必须选择预约时间
	if(ydate==""||ydate==null){
		alert("请选择预约时间日期");
		return;
	}
	if(radio==""||radio==null){
		alert("请选择预约时间段");
		return;
	}
	if(radio==0){
		if($("#time0").html()<=0){
			alert("不能预约剩余名额为0的时段！");
			return;
		}
		$("#ystime").val("08:30");
		$("#yetime").val("09:30");
	}else if(radio==1){
		if($("#time1").html()<=0){
			alert("不能预约剩余名额为0的时段！");
			return;
		}
		$("#ystime").val("09:30");
		$("#yetime").val("10:30");
	}else if(radio==2){
		if($("#time2").html()<=0){
			alert("不能预约剩余名额为0的时段！");
			return;
		}
		$("#ystime").val("10:30");
		$("#yetime").val("11:30");
	}else if(radio==3){
		if($("#time3").html()<=0){
			alert("不能预约剩余名额为0的时段！");
			return;
		}
		$("#ystime").val("14:30");
		$("#yetime").val("15:30");
	}else if(radio==4){
		if($("#time4").html()<=0){
			alert("不能预约剩余名额为0的时段！");
			return;
		}
		$("#ystime").val("15:30");
		$("#yetime").val("16:30");
	}else if(radio==5){
		if($("#time5").html()<=0){
			alert("不能预约剩余名额为0的时段！");
			return;
		}
		$("#ystime").val("16:30");
		$("#yetime").val("17:30");
	}
	//alert($("#accountform").serialize());
	//校验验证码
	var vc = checkYanzhengma();
	if(!vc){
		return;
	}
	 $("#button1id").text("提交中...");
	 $("#button1id").attr("disabled", true);
	 //alert($("#accountform").serialize());
	//ajax提交表单
	$.ajax({
		url:'YuYues!saveYuYues',
		data:$("#accountform").serialize(),
		type:"POST",
		success:function(r){
			//alert(r);
			var result =JSON.parse(r);
			//console.log(result);
			var str='';
			//alert(result.code);
			if(result.code==1){
				str+="预约号:"+result.booking_no+"\n";
				str+="预约日期："+result.date+"\n";
				str+="时间段："+result.s_time+"至"+result.e_time+"\n";
				str+=result.msg;
				$.ajax({
				url :'sendMessage!send',
				data:{Title:'预约成功提醒',
				 Msg:"您好，"+$('#name').val()+"。\n\n"+
				 "预约业务："+"综合业务\n"+
				 "订单号："+result.booking_no+"\n"+
				 "预约时间："+result.date+" "+result.s_time+"至"+result.e_time+"\n"+
				 "预约受理点："+streetText+"\n"+
				 "您的预约已成功办理，请在预约时间内携带相关证件到现场取号处，凭身份证或订单号（预约号）取号。如有问题可以咨询现场工作人员。（若要取消预约请在《个人办事》-《在线预约》-《预约记录》中进行取消）。预约后不前来办事，累积达3次将会列入黑名单，不能再使用预约功能。",
				MsgUrl:'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx481172387f6fb7c5&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/wdyy_2.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect',
				openid:$("#openid").val() },
				type : "post",
				async : false,
				dataType : "json",
				error : function(XMLHttpRequest,textStatus,errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
				//alert("1--erro"); 
				},
				success : function(json, state) {
				alert(str);
					WeixinJSBridge.call('closeWindow');
				}	
		});
				WeixinJSBridge.call('closeWindow');
			}else{
				alert(result.msg);
				reloadCode();//预约失败刷新验证码
				$("#button1id").text("保存");
    	    	$("#button1id").attr("disabled", false);
			}
		},
		error:function(e){
			alert("预约失败！请检查网络");
			$("#button1id").text("保存");
    	    $("#button1id").attr("disabled", false);
			//console.log(e);
		}
	});
}
</script>
<script type="text/javascript">
//登录检测验证码
function checkYanzhengma() {
	callServer();
	//alert(document.getElementById('codeResult').value);
	if(document.getElementById('codeResult').value.indexOf("false")!=-1){//ietester在此处不能取得验证码
		alert('验证码错误！');
		//document.getElementById("image").src="img.jsp";
		reloadCode();
		return false;
	}else{
		//alert(document.getElementById("loginForm"));
		///document.getElementById("loginForm").onsubmit();
		return true;
	}
}

function callServer(){
	gHttpObj=getXMLHTTPObj();
	try{
	    var code = document.getElementById("code").value;
	   // alert("code--->"+code);
		if( gHttpObj ){
			//构造查询连接字符串
			var url = "chkImg.jsp?code=" + code;
			//打开连接
			gHttpObj.open("GET", url, false);
			//设置回调函数
			gHttpObj.onreadystatechange = retualServerValue;
			//发送请求
			gHttpObj.send(null);
		}else{
			alert("system error2");
		}
	}catch (e){
		alert("system error1");
	}
}

function getXMLHTTPObj(){
	var XMLHTTPObj = null;
	try{
		XMLHTTPObj = new ActiveXObject("Msxml2.XMLHTTP");
	}catch(e){
		try{
			XMLHTTPObj = new ActiveXObject("Microsoft.XMLHTTP");
		}catch(sc){
			XMLHTTPObj = null;
		}
	}
  
	if( !XMLHTTPObj && typeof XMLHttpRequest != "undefined" ){
		XMLHTTPObj = new XMLHttpRequest();
	}
	return XMLHTTPObj;
}

//回调处理函数
function retualServerValue(){
	try{
		if (gHttpObj.readyState == 1){
	
		}
		if (gHttpObj.readyState == 2){

		}
		if (gHttpObj.readyState == 3) {
   
		}
		if (gHttpObj.readyState == 4) {
			var response = gHttpObj.responseText;
			document.getElementById("codeResult").value=response;
		}
	}catch(e) {
      
	}
}
</script>
</html>
