<<<<<<< HEAD
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

	Date addDate = holiday.getIncomeDate(new Date(),true);
	String date = format.format(addDate);
	String weekDay = convert(addDate.getDay());
	//System.out.println(":::::::::::::"+addDate);

	Date addDate2 = holiday.getIncomeDate(addDate,true);
	String date2 = format.format(addDate2);
	String weekDay2 = convert(addDate2.getDay());
	//System.out.println(":::::::::::::"+addDate2);

	Date addDate3 = holiday.getIncomeDate(addDate2,true);
	String date3 = format.format(addDate3);
	String weekDay3 = convert(addDate3.getDay());

	Date addDate4 = holiday.getIncomeDate(addDate3,true);
	String date4 = format.format(addDate4);
	String weekDay4 = convert(addDate4.getDay());

	Date addDate5 = holiday.getIncomeDate(addDate4,true);
	String date5 = format.format(addDate5);
	String weekDay5 = convert(addDate5.getDay());
%>

<%
//String street = request.getParameter("street");
//System.out.println("1212112-=-==-1212-1=-=-="+request.getParameter("paramVal"));
String paramVal = session.getAttribute("paramVal").toString();
String[] params=paramVal.split("_");
String serverTypeId = params[0]; 
//value:ZH001、GA001
String serverTypeNo = params[1];
String twoLevelAffairId = params[2];

//System.out.println("----======"+serverTypeNo);
 %>
<!DOCTYPE html>
<html>
<head>
<!-- Mobile Devices Support @begin -->
<meta content="application/xhtml+xml;charset=GBK" http-equiv="Content-Type">
<meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
<meta content="no-cache" http-equiv="pragma">
<meta content="0" http-equiv="expires">
<meta content="telephone=no, address=no" name="format-detection">
<meta name="apple-mobile-web-app-capable" content="yes" /> <!-- apple devices fullscreen -->
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<!-- Mobile Devices Support @end -->
<title>填写信息</title>
<link rel="stylesheet" href="yms/window/weiXinAppointment/css/common.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="yms/window/weiXinAppointment/js/common.js"></script>
<script type="text/javascript" src="yms/window/onlineAppointment/js/id-num-validator.js"></script>






</head>

<script type="text/javascript">

//验证码
function reloadCode() { 
	var image = document.getElementById("randomming");
	if(null!=image){
		image.src="image.jsp?"+Math.random();
	}
}

	$(document).ready(function(){
		 $(function(){
		 
		 $("#idcard").val($("#idcard2").val());
		$("#phone").val($("#phone2").val());
			
			$.post('<%=basePath%>Street!findAll' ,function(result) {
					  //alert(result);
					result.sort(sortBy('id', false, parseInt));	
					var content="<option value=\"\" >--请选择预约地点--</option>";			
					$.each(result, function(k, v) {
		       			
		                //alert(v.name);
		                content+="<option value='"+v.no+"'>"+v.name+"</option>";
		                
		            });
		 			$("#street").append(content);
					
		        }, "json");
		        
		    loadData();
		 	getAffairs();
			reloadCode();
		  });
	});
	
	function getStreet(street){
	 $("#ydate  option[value=''] ").attr("selected",true)
}

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
	
	//通过日期获得剩余号数
function getCount(ydate){
	
	var street=$('#street').val();
	
	var serverTypeNo = '<%=serverTypeNo%>';
	
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
			street:street,
			businessType:serverTypeNo.substring(0,2),
			weight:$("#weight").val()
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
	
	function isMobile(mobile) {
		var pattern = /^1[34578]\d{9}$/;  
	    if (pattern.test(mobile)) {  
	        return true;  
	    }else{
	    	return false;
	    }
	} 
	function checkUserData(){
		var userName = $("#name").val();
		var cardNo = $("#idcard").val();
		var phoneNo = $("#phone").val();
		var cardType = "身份证";
		if(userName==""){
			alert("用户名不能为空！");
			return false;
		}
		if(cardType =="身份证"){
			var info = getIdCardInfo(cardNo);
			if(!info.isTrue){
				alert("证件号码有误！");
				return false;
			}
		}
		if(!isMobile(phoneNo)){
			alert("手机号码有误！");
			return false;
		}
		//校验验证码
		var vc = checkYanzhengma();
		if(!vc){
			return false;
		}
		return true;
	}
	
	// 选择时间段
	function selectTimePeriod(startTime, endTime,time){
		$("#ystime").val(startTime);
		$("#yetime").val(endTime);
		$("#time").val($('#'+time).html());
		if($('#'+time).html()<=0){
			alert("不能预约剩余名额为0的时段！");
			return;
		}
	}
	
	$(document).ready(function(){
	 //$("#submit1").text("提交中...");
	 //$("#submitDiv").html('<span>提交中...</span>');
	 //$("#submit1").attr("disabled", false);

	 
		//判断是否同意协议
		/* $("#agreeprotocol").bind("click",function(){
			if($("#agreeprotocol").attr("class")=="checked"){
				$("#submitDiv").html('<a href="javascript:submitForm();">确认提交</a>');
			}else{
				$("#submitDiv").html('<span>确认提交</span>');
			}
		}); */
		//$("#submitDiv").html('<a href="javascript:submitForm();">确认提交</a>');
	});
	
	function getAffairs(){

		$.ajax({
			url:"<%=basePath%>BusinessWeight!findAffair",
			async: false, 
			type:"post",
			data:{"id":'<%=twoLevelAffairId%>'},
			dataType:"json",
			success: function (data) {
			var htmlContext = "";
				$("#affair").val(data.servertypeName+"-"+data.businessName);
				//alert(data.serverTypeNo);
				$("#stype").val(data.serverTypeNo);
				$("#businessName").val(data.businessName);
				$("#weight").val(data.weight);
            	//alert(data.v);
            	//$("#"+id).html(htmlContext);
				
	            
	        },
	        error: function (request) {
	            alert("数据加载错误");//请求失败
	        }
		});
	
	}
	
	function submitForm(){
	
		var street=$('#street').val();
		var streetText = $("#street option:selected").text();
		if($("#ystime").val() == ""){
			alert("请选择时间段！");
			return false;
		}
		if($("#time").val()==0){
			alert("不能预约剩余名额为0的时段！");
			return false;
		}
		
		
		if(checkUserData()){
		 $("#submitDiv").html("<span>提交中...</span>");
			//ajax提交表单
	$.ajax({
		url:'YuYues!saveYuYues',
		data:$("#appointmentForm").serialize(),
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
				 "预约业务："+$('#affair').val()+"\n"+
				 "订单号："+result.booking_no+"\n"+
				 "预约时间："+result.date+" "+result.s_time+"至"+result.e_time+"\n"+
				 "预约受理点："+streetText+"\n"+
				 "您的预约已成功办理，请在预约时间内携带相关证件到现场取号处，凭身份证或订单号（预约号）取号。一个预约号仅可办理一件本人业务，如有问题可以咨询现场工作人员。（若要取消预约请在《个人办事》-《在线预约》-《预约记录》中进行取消）。预约后不前来办事，累积达3次将会列入黑名单，不能再使用预约功能。",
				MsgUrl:'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/wdyy_2.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect',
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
				$("#submitDiv").html('<a href="javascript:submitForm();">确认提交</a>');
			}
		},
		error:function(e){
			alert("预约失败！请检查网络");
			$("#submitDiv").html('<a href="javascript:submitForm();">确认提交</a>');
			//console.log(e);
		}
	});
		}
	}
	
	function loadData(){
	var openid = $("#openid").val();
	$.post('ssUserInfo!findByOpenId', { openid:openid}, function(data) {
	
       if(data.length>0){
       		var d =eval("("+data+")");
       		var name = "",idcard="",phone ="",address="";
       		
       		$.each(d,function (i,val){
				name +=  "<option value=\""+val.name+"\" >"+val.name+"</option>";
				idcard+="<option value=\""+val.idCard+"\" >"+val.idCard+"</option>";
				phone+="<option value=\""+val.phone+"\" >"+val.phone+"</option>";
				address+="<option value=\""+val.address+"\" >"+val.address+"</option>";
			});
			
			$("#name").append(name);
			$("#idcard2").append(idcard);
			$("#idcard").val($("#idcard2").val());
			$("#phone2").append(phone);
			$("#phone").val($("#phone2").val());
			$("#address").append(address);
       }
       checkaddress();
	}); 
}
	
	function setIndex(){
		var index=$('#name').prop('selectedIndex');
		//alert(index);
		$("#idcard2").get(0).selectedIndex=index;
		$("#phone2").get(0).selectedIndex=index;
		$("#address").get(0).selectedIndex=index;
		
		$("#idcard").val($("#idcard2").val());
		$("#phone").val($("#phone2").val());
		
		checkaddress();
	}
	
	function checkaddress(){
	
 		$.each($("#street option"),function (i,val){
 			var temp = val.text;
 			var street =temp.substring(0,2);
 			var address = $('#address').val();
		 	if(address.indexOf(street)!=-1){
		 		val.selected = true;
		 	}
 		});
 		
	}
</script>
<body>
	
	<div class="content-2">
		<form id="appointmentForm" action="" method="post">
		
		<input type="hidden" name="openid" id="openid" value='<s:property value="openid"/>'>
		<input type="hidden" name="ystime" id="ystime" value="">
   		<input type="hidden" name="yetime" id="yetime" value="">
   		<input type="hidden" name="time" id="time" value="">
		<input type="hidden" name="stype" id="stype" value=""> 
		<input type="hidden" name="weight" id="weight" value=""> 
		<input type="hidden" name="businessName" id="businessName" value=""> 
		<select class="input-select"  disabled="disabled"  style="display:none" name="address"  id="address">
		</select>
         <div class="form">
         <div  class="form-main clear" >
         		<a href="http://ymswx.pjq.gov.cn/pjWechat/ssBaseDicInfo!findAllByBaseDicType?baseDicType=D001" >
         		<label class="ico-7 left">办事指南</label></a>
         </div>
         	<div class="form-main clear">
         		<label class="ico-7 left">预约事项</label>
         		<div class="txt right">
         			<input type="text" readonly="readonly" id="affair" name="affair" value='' class="input-txt" />
         		</div>
         	</div>
         
         	<div class="form-main clear">
         		<label class="ico-1 left">预约人</label>
         		<div class="txt right">
         			<select class="input-select" name="name" onchange="setIndex()" id="name">
                    </select>
         		</div>
         	</div>

         	<div class="form-main clear">
         		<label class="ico-3 left">证件号码</label>
         		<div class="txt right">
         			<input type="text" readonly="readonly" class="input-txt" name="idcard" id="idcard" value="">
         			<%-- <input type="text" id="idcard" name="idcard"  value='<s:property value="idcard"/>' class="input-txt" placeholder="预约人身份证号码" /> --%>
         			<select class="input-select" name="idcard2" disabled="disabled"  style="display:none"  id="idcard2">
         			
                    </select>
         		</div>
         	</div>
         	<div class="form-main clear">
         		<label class="ico-4 left">手机号码</label>
         		<div class="txt right">
         			<input type="text" readonly="readonly" class="input-txt" name="phone" id="phone" value="">
         			<%-- <input type="text" id="phone" name="phone" value="<s:property value="phone"/>"  class="input-txt" placeholder="预约人常用手机号码" /> --%>
         			<select class="input-select" name="phone2" disabled="disabled" style="display:none" id="phone2">
         			
                    </select>
         		</div>
         	</div>
         	<div class="form-main clear">
         		<label class="ico-6 left">预约地点</label>
         		<div class="txt right">
         			<select class="input-select" name="street" onchange="getStreet(this.value);" id="street">
         				  <!-- <option value="" selected="selected">--请选择预约地点--</option> -->
                    </select>
         		</div>
         	</div>
         	<div class="form-main clear">
         		<label class="ico-5 left">预约日期</label>
         		<div class="txt right">
         			<select class="input-select" name="ydate" onchange="getCount(this.value);" id="ydate">
         				  <option value="" selected="selected">--请选择预约时间--</option>
						  <option value="<%=date %>"><%=date %> (<%=weekDay %>)</option>
						  <option value="<%=date2 %>"><%=date2 %> (<%=weekDay2 %>)</option>
						  <option value="<%=date3 %>"><%=date3 %> (<%=weekDay3 %>)</option>
						  <option value="<%=date4 %>"><%=date4 %> (<%=weekDay4 %>)</option>
						  <option value="<%=date5 %>"><%=date5 %> (<%=weekDay5 %>)</option>
                    </select>
         			<!-- <a href="list-2.html">请选择预约日期</a> -->
         		</div>
         	</div>
         	<div class="form-main clear">
         		<label class="ico-8 left">验证码</label>
         		<div class="txt right">
         			<input type="text" id="code" name="code" value="" style="width: 50%" class="input-txt" placeholder="请输入验证码" />
         			<img id="randomming" class="input-txt"
					src="" onclick="reloadCode()"   style="width: 35%;" height="40px"
					title="点击更换验证码" alt="点击更换验证码" /> 
					<input type="hidden" id="codeResult"/>
         		</div>
         		
         </div>
         </div>
         <div class="book-time">
            <h3>请选择预约时间段</h3>
            <div class="time-main clear" id="timePeriod">
                <%-- <li class="left">
                	<a href="javascript:selectTimePeriod('08:30','09:30','time0')">08:30-09:30&nbsp;剩余 <spa id="time0" >0</spa></a>
                </li> --%>
                <li class="left">
                	<a href="javascript:selectTimePeriod('09:30','10:30','time1')">09:30-10:30&nbsp;剩余 <spa id="time1" >0</spa></a>
                </li>
                <li class="left">
                	<a href="javascript:selectTimePeriod('10:30','11:30','time2')">10:30-11:30&nbsp;剩余<spa id="time2" >0</spa></a>
                </li>
                <li class="left">
                	<a href="javascript:selectTimePeriod('14:30','15:30','time3')">14:30-15:30&nbsp;剩余 <spa id="time3" >0</spa></a>
                </li>
                <li class="left">
                	<a href="javascript:selectTimePeriod('15:30','16:30','time4')">15:30-16:30&nbsp;剩余 <spa id="time4" >0</spa></a>
                </li>
                <%-- <li class="left">
                	<a href="javascript:selectTimePeriod('16:30','17:30','time5')">16:30-17:30&nbsp;剩余 <spa id="time5" >0</spa></a>
                </li>  --%>
            </div>
		</div>
		
       <%-- 	<div class="protocol-box">
       		<span id="agreeprotocol" class="checked">我已同意</span><a href="weiXinAppointmentAction.struts?actionType=toProtocolPage&fromSystem=">《江门市邑门式网上预约协议》</a>
       	</div> --%>
        <div class="big-btn" id="submitDiv">
        	<a href="javascript:submitForm();" id='submit1'>确认提交</a>
        </div>
        </form> 
	</div>
</body>
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

=======
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



<%
//String street = request.getParameter("street");
//System.out.println("1212112-=-==-1212-1=-=-="+request.getParameter("paramVal"));
String paramVal = session.getAttribute("paramVal").toString();
String[] params=paramVal.split("_");
String serverTypeId = params[0]; 
//value:ZH001、GA001
String serverTypeNo = params[1];
String twoLevelAffairId = params[2];

//System.out.println("----======"+serverTypeNo);
 %>
<!DOCTYPE html>
<html>
<head>
<!-- Mobile Devices Support @begin -->
<meta content="application/xhtml+xml;charset=GBK" http-equiv="Content-Type">
<meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
<meta content="no-cache" http-equiv="pragma">
<meta content="0" http-equiv="expires">
<meta content="telephone=no, address=no" name="format-detection">
<meta name="apple-mobile-web-app-capable" content="yes" /> <!-- apple devices fullscreen -->
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<!-- Mobile Devices Support @end -->
<title>填写信息</title>
<link rel="stylesheet" href="yms/window/weiXinAppointment/css/common.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="yms/window/weiXinAppointment/js/common.js"></script>
<script type="text/javascript" src="yms/window/onlineAppointment/js/id-num-validator.js"></script>


</head>

<script type="text/javascript">

//验证码
function reloadCode() { 
	var image = document.getElementById("randomming");
	if(null!=image){
		image.src="image.jsp?"+Math.random();
	}
}

	$(document).ready(function(){
		 $(function(){
		 
		 $("#idcard").val($("#idcard2").val());
		$("#phone").val($("#phone2").val());
			
			$.post('<%=basePath%>Street!findAll' ,function(result) {
					  //alert(result);
					result.sort(sortBy('id', false, parseInt));	
					var content="<option value=\"\" >--请选择预约地点--</option>";			
					$.each(result, function(k, v) {
		       			
		                //alert(v.name);
		                content+="<option value='"+v.no+"'>"+v.name+"</option>";
		                
		            });
		 			$("#street").append(content);
					
		        }, "json");
		        
		    loadData();
		 	getAffairs();
			reloadCode();
		  });
	});
	
	function getStreet(street){
	 $("#ydate  option[value=''] ").attr("selected",true)
}

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
	
	//通过日期获得剩余号数
function getCount(ydate){
	
	var street=$('#street').val();
	
	var serverTypeNo = '<%=serverTypeNo%>';
	
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
			street:street,
			businessType:serverTypeNo.substring(0,2),
			weight:$("#weight").val()
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
	
	function isMobile(mobile) {
		var pattern = /^1[34578]\d{9}$/;  
	    if (pattern.test(mobile)) {  
	        return true;  
	    }else{
	    	return false;
	    }
	} 
	function checkUserData(){
		var userName = $("#name").val();
		var cardNo = $("#idcard").val();
		var phoneNo = $("#phone").val();
		var cardType = "身份证";
		if(userName==""){
			alert("用户名不能为空！");
			return false;
		}
		if(cardType =="身份证"){
			var info = getIdCardInfo(cardNo);
			if(!info.isTrue){
				alert("证件号码有误！");
				return false;
			}
		}
		if(!isMobile(phoneNo)){
			alert("手机号码有误！");
			return false;
		}
		//校验验证码
		var vc = checkYanzhengma();
		if(!vc){
			return false;
		}
		return true;
	}
	
	// 选择时间段
	function selectTimePeriod(startTime, endTime,time){
		$("#ystime").val(startTime);
		$("#yetime").val(endTime);
		$("#time").val($('#'+time).html());
		if($('#'+time).html()<=0){
			alert("不能预约剩余名额为0的时段！");
			return;
		}
	}
	
	$(document).ready(function(){
	 //$("#submit1").text("提交中...");
	 //$("#submitDiv").html('<span>提交中...</span>');
	 //$("#submit1").attr("disabled", false);

	 
		//判断是否同意协议
		/* $("#agreeprotocol").bind("click",function(){
			if($("#agreeprotocol").attr("class")=="checked"){
				$("#submitDiv").html('<a href="javascript:submitForm();">确认提交</a>');
			}else{
				$("#submitDiv").html('<span>确认提交</span>');
			}
		}); */
		//$("#submitDiv").html('<a href="javascript:submitForm();">确认提交</a>');
	});
	
	function getAffairs(){

		$.ajax({
			url:"<%=basePath%>BusinessWeight!findAffair",
			async: false, 
			type:"post",
			data:{"id":'<%=twoLevelAffairId%>'},
			dataType:"json",
			success: function (data) {
			var htmlContext = "";
				$("#affair").val(data.servertypeName+"-"+data.businessName);
				//alert(data.serverTypeNo);
				$("#stype").val(data.serverTypeNo);
				$("#businessName").val(data.businessName);
				$("#weight").val(data.weight);
            	//alert(data.v);
            	//$("#"+id).html(htmlContext);
				
	            
	        },
	        error: function (request) {
	            alert("数据加载错误");//请求失败
	        }
		});
	
	}
	
	function submitForm(){
	
		var street=$('#street').val();
		var streetText = $("#street option:selected").text();
		if($("#ystime").val() == ""){
			alert("请选择时间段！");
			return false;
		}
		if($("#time").val()==0){
			alert("不能预约剩余名额为0的时段！");
			return false;
		}
		
		
		if(checkUserData()){
		 $("#submitDiv").html("<span>提交中...</span>");
			//ajax提交表单
	$.ajax({
		url:'YuYues!saveYuYues',
		data:$("#appointmentForm").serialize(),
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
				 "预约业务："+$('#affair').val()+"\n"+
				 "订单号："+result.booking_no+"\n"+
				 "预约时间："+result.date+" "+result.s_time+"至"+result.e_time+"\n"+
				 "预约受理点："+streetText+"\n"+
				 "您的预约已成功办理，请在预约时间内携带相关证件到现场取号处，凭身份证或订单号（预约号）取号。一个预约号仅可办理一件本人业务，如有问题可以咨询现场工作人员。（若要取消预约请在《个人办事》-《在线预约》-《预约记录》中进行取消）。预约后不前来办事，累积达3次将会列入黑名单，不能再使用预约功能。",
				MsgUrl:'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/wdyy_2.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect',
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
				$("#submitDiv").html('<a href="javascript:submitForm();">确认提交</a>');
			}
		},
		error:function(e){
			alert("预约失败！请检查网络");
			$("#submitDiv").html('<a href="javascript:submitForm();">确认提交</a>');
			//console.log(e);
		}
	});
		}
	}
	
	function loadData(){
	var openid = $("#openid").val();
	$.post('ssUserInfo!findByOpenId', { openid:openid}, function(data) {
	
       if(data.length>0){
       		var d =eval("("+data+")");
       		var name = "",idcard="",phone ="",address="";
       		
       		$.each(d,function (i,val){
				name +=  "<option value=\""+val.name+"\" >"+val.name+"</option>";
				idcard+="<option value=\""+val.idCard+"\" >"+val.idCard+"</option>";
				phone+="<option value=\""+val.phone+"\" >"+val.phone+"</option>";
				address+="<option value=\""+val.address+"\" >"+val.address+"</option>";
			});
			
			$("#name").append(name);
			$("#idcard2").append(idcard);
			$("#idcard").val($("#idcard2").val());
			$("#phone2").append(phone);
			$("#phone").val($("#phone2").val());
			$("#address").append(address);
       }
       checkaddress();
	}); 
}
	
	function setIndex(){
		var index=$('#name').prop('selectedIndex');
		//alert(index);
		$("#idcard2").get(0).selectedIndex=index;
		$("#phone2").get(0).selectedIndex=index;
		$("#address").get(0).selectedIndex=index;
		
		$("#idcard").val($("#idcard2").val());
		$("#phone").val($("#phone2").val());
		
		checkaddress();
	}
	
	function checkaddress(){
	
 		$.each($("#street option"),function (i,val){
 			var temp = val.text;
 			var street =temp.substring(0,2);
 			var address = $('#address').val();
		 	if(address.indexOf(street)!=-1){
		 		val.selected = true;
		 	}
 		});
 		
	}
</script>
<body>
	
	<div class="content-2">
		<form id="appointmentForm" action="" method="post">
		
		<input type="hidden" name="openid" id="openid" value='<s:property value="openid"/>'>
		<input type="hidden" name="ystime" id="ystime" value="">
   		<input type="hidden" name="yetime" id="yetime" value="">
   		<input type="hidden" name="time" id="time" value="">
		<input type="hidden" name="stype" id="stype" value=""> 
		<input type="hidden" name="weight" id="weight" value=""> 
		<input type="hidden" name="businessName" id="businessName" value=""> 
		<select class="input-select"  disabled="disabled"  style="display:none" name="address"  id="address">
		</select>
         <div class="form">
         <div  class="form-main clear" >
         		<a href="http://ymswx.pjq.gov.cn/pjWechat/ssBaseDicInfo!findAllByBaseDicType?baseDicType=D001" >
         		<label class="ico-7 left">办事指南</label></a>
         </div>
         	<div class="form-main clear">
         		<label class="ico-7 left">预约事项</label>
         		<div class="txt right">
         			<input type="text" readonly="readonly" id="affair" name="affair" value='' class="input-txt" />
         		</div>
         	</div>
         
         	<div class="form-main clear">
         		<label class="ico-1 left">预约人</label>
         		<div class="txt right">
         			<select class="input-select" name="name" onchange="setIndex()" id="name">
                    </select>
         		</div>
         	</div>

         	<div class="form-main clear">
         		<label class="ico-3 left">证件号码</label>
         		<div class="txt right">
         			<input type="text" readonly="readonly" class="input-txt" name="idcard" id="idcard" value="">
         			<%-- <input type="text" id="idcard" name="idcard"  value='<s:property value="idcard"/>' class="input-txt" placeholder="预约人身份证号码" /> --%>
         			<select class="input-select" name="idcard2" disabled="disabled"  style="display:none"  id="idcard2">
         			
                    </select>
         		</div>
         	</div>
         	<div class="form-main clear">
         		<label class="ico-4 left">手机号码</label>
         		<div class="txt right">
         			<input type="text" readonly="readonly" class="input-txt" name="phone" id="phone" value="">
         			<%-- <input type="text" id="phone" name="phone" value="<s:property value="phone"/>"  class="input-txt" placeholder="预约人常用手机号码" /> --%>
         			<select class="input-select" name="phone2" disabled="disabled" style="display:none" id="phone2">
         			
                    </select>
         		</div>
         	</div>
         	<div class="form-main clear">
         		<label class="ico-6 left">预约地点</label>
         		<div class="txt right">
         			<select class="input-select" name="street" onchange="getStreet(this.value);" id="street">
         				  <!-- <option value="" selected="selected">--请选择预约地点--</option> -->
                    </select>
         		</div>
         	</div>
         	<div class="form-main clear">
         		<label class="ico-5 left">预约日期</label>
         		<div class="txt right">
         			<select class="input-select" name="ydate" onchange="getCount(this.value);" id="ydate">
         				  <option value="" selected="selected">--请选择预约时间--</option>

                    </select>
         			<!-- <a href="list-2.html">请选择预约日期</a> -->
         		</div>
         	</div>
         	<div class="form-main clear">
         		<label class="ico-8 left">验证码</label>
         		<div class="txt right">
         			<input type="text" id="code" name="code" value="" style="width: 50%" class="input-txt" placeholder="请输入验证码" />
         			<img id="randomming" class="input-txt"
					src="" onclick="reloadCode()"   style="width: 35%;" height="40px"
					title="点击更换验证码" alt="点击更换验证码" /> 
					<input type="hidden" id="codeResult"/>
         		</div>
         		
         </div>
         </div>
         <div class="book-time">
            <h3>请选择预约时间段</h3>
            <div class="time-main clear" id="timePeriod">
                <%-- <li class="left">
                	<a href="javascript:selectTimePeriod('08:30','09:30','time0')">08:30-09:30&nbsp;剩余 <spa id="time0" >0</spa></a>
                </li> --%>
                <li class="left">
                	<a href="javascript:selectTimePeriod('09:30','10:30','time1')">09:30-10:30&nbsp;剩余 <spa id="time1" >0</spa></a>
                </li>
                <li class="left">
                	<a href="javascript:selectTimePeriod('10:30','11:30','time2')">10:30-11:30&nbsp;剩余<spa id="time2" >0</spa></a>
                </li>
                <li class="left">
                	<a href="javascript:selectTimePeriod('14:30','15:30','time3')">14:30-15:30&nbsp;剩余 <spa id="time3" >0</spa></a>
                </li>
                <li class="left">
                	<a href="javascript:selectTimePeriod('15:30','16:30','time4')">15:30-16:30&nbsp;剩余 <spa id="time4" >0</spa></a>
                </li>
                <%-- <li class="left">
                	<a href="javascript:selectTimePeriod('16:30','17:30','time5')">16:30-17:30&nbsp;剩余 <spa id="time5" >0</spa></a>
                </li>  --%>
            </div>
		</div>
		
       <%-- 	<div class="protocol-box">
       		<span id="agreeprotocol" class="checked">我已同意</span><a href="weiXinAppointmentAction.struts?actionType=toProtocolPage&fromSystem=">《江门市邑门式网上预约协议》</a>
       	</div> --%>
        <div class="big-btn" id="submitDiv">
        	<a href="javascript:submitForm();" id='submit1'>确认提交</a>
        </div>
        </form> 
	</div>
</body>
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


function getdate(){
    var json = ${dayJson};
    //	alert();
    var serverTypeNo = '<%=serverTypeNo%>';

    for(var key in json){

        //　alert(key+':'+json[key]);
        if(json[key].indexOf("六")!=-1){
            $('#ydate').append("<option value=\""+key+"\" >"+key+" "+json[key]+"</option>");
        }
        if(serverTypeNo.indexOf('ZL')==-1&&serverTypeNo.indexOf('GL')==-1){
            $('#ydate').append("<option value=\""+key+"\" >"+key+" "+json[key]+"</option>");
        }

    }
}
</script>
</html>

>>>>>>> withoutWechatInterface
