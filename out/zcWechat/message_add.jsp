<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String code = request.getParameter("code");
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
<title>办理人信息</title>
<link rel="stylesheet" href="yms/window/weiXinAppointment/css/common.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="yms/window/weiXinAppointment/js/common.js"></script>
<script type="text/javascript" src="yms/window/onlineAppointment/js/id-num-validator.js"></script>
<script type="text/javascript" src="js/infoYz.js"></script>
<script type="text/javascript" src="js/smscheck.js"></script>
</head>

<body>
	<div class="content-2">
	<label class="lobby"><h3>办事人姓名</h3></label>
	<form id="accountform" action="" method="post">
		<div class="form">
		
			<div class="form-main clear">
         		<!-- <label class="ico-1 left">办事人姓名</label> -->
         		<div class="txt "style="width: 100%">
         			<input type="text" id="title" name="title" value="" class="input-txt" placeholder="请填写建议标题或内容摘要......" />
         		</div>
         	</div>
         	<div class="form-main clear" style="height: 150px;width: 100%">
         		<div class="txt" >
         			<textarea id="content" name="content" onclick="jiancha2()" style="height: 150px;width: 175%" rows="4"  value=""  class="input-txt" placeholder="详细描述建议内容......" ></textarea>
         		</div>
         	</div>
         		<div class="form-main clear">

         		<div class="txt " style="width: 100%">
         			<input type="text" id="complaint_Name" name="complaint_Name" value="" class="input-txt" placeholder="请填写你的姓名(不填写即为匿名用户)......" />
         		</div>
         	</div>
         	<div class="form-main clear">
				<label class="ico-4 left">选择手机号</label>
         		<div class="txt right" >
         			
         			<!-- <input type="text" id="phone" name="phone" value="" class="input-txt" placeholder="请输入手机号码" /> -->
         			<select class="input-select" name="phone" id="phone">
         				  <!-- <option value="" selected="selected">--请选择预约地点--</option> -->
                    </select>
         		</div>
         	</div>
         	
         	<div class="form-main clear">
         		<label class="ico-8 left">验证码</label>
         		<div class="txt right">
         			<input type="text" id="random_num" name="random_num" value="" style="width: 50%" class="input-txt" placeholder="请输入验证码" />
         			<img id="randomming" class="input-txt"
					src="" onclick="reloadCode()"   style="width: 35%;" height="40px"
					title="点击更换验证码" alt="点击更换验证码" /> 
					<input type="hidden" id="codeResult">
         		</div>
         		<div class="right"></div>
         	<input type="hidden" id="openid" name="openid" value="" />
         		 
         </div>
       
		</div>
		<div class="big-btn" id="submitDiv">
        	<a href="javascript:sub();" id='submit1'>确认提交</a>
        </div>
		</form>
	</div>
</body>
<script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8" charset="gb2312"></script>
<script type="text/javascript">
	$(function(){
		loadData();
		reloadCode();
		
	});
	function reloadCode() 
	{ 
		var image = document.getElementById("randomming");
		if(null!=image){
			image.src="image.jsp?"+Math.random();
		}
	
	}
	function loadData(){

		$.post('ssUserInfo!findByOpenId', {code:<%=code%>}, function(data) {
		
		var d =eval(data);
		
		if(d==null||d==''){
	 			window.location.href ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/relation.jsp?type=liuyan&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	 		}else
	       if(d!=null&&d!=undefined&&d!=''){
			
		       	$('#openid').val(d[0].openid);
				var content='';
				$.each(d,function (i,val){
	 			//alert(val.phone);
				content="<option value='"+val.phone+"'>"+val.phone+"</option>";
	 			});
	 		
	 		
	 			$("#phone").append(content);
	       }
	    
		},'json'); 

	}
	
	function sub(){
		if(	$("#content").val()==""||$("#content").val()==null){
			alert("填写内容不完整，请填写...");
			return
		}
		var vc = checkYanzhengma();
		if(!vc){
				return;
		}

		//把数据提交到投诉管理后台
		toBackground();
	}
	
	function toBackground(){
       	var myDate = new Date();
					//获取当前年
		var year=myDate.getFullYear();
					//获取当前月
		var month=myDate.getMonth()+1;
					//获取当前日
		var date=myDate.getDate(); 
		var ho=myDate.getHours();       //获取当前小时数(0-23)
		var m=myDate.getMinutes();     //获取当前分钟数(0-59)
		var s=myDate.getSeconds(); 
            		//var type = $("#selectlb").val();
            		//alert(type)
        var status = "已提交办理中";
        var show = "否";
            		//var unit = $("#selectwd").val();
         var content = $("#content").val();
         var pho = $("#pho").val();
         var title = $("#title").val();
		 var time = year+"-"+month+"-"+date+"    "+ho+":"+m+":"+s;
					//var time = year+"年"+month+"月"+date+"日"+ho+"时"+m+"分"+s+"秒";
            		//alert(time);
         var num = "SZPY"+month+date+m+s;
         var remark = "评议";
         var name=$("#complaint_Name").val();
          if(name==""||name==null){
            	name= "匿名用户";
           }
           $.ajax(
			   {
			    type: "post",
			    url: "/newqueue/Complaint!save.action",
				data : {
								//complaint_YwOrPeo:type,
					complaint_Content:content,
					complaint_Pho:pho,
					complaintTime:time,
								//complaint_Unit:unit,
					complaint_Num:num,
					complaint_Title:title,
					complaint_Status:status,
					complaint_Remark:remark,
					complaint_Name:name,
					complaint_Show:show
					},
							//contentType: "application/json; charset=utf-8",
					dataType : "text",
					success : function(data) {
						//$.ajax(phone);
						alert("留言成功");
						location.reload();
								return;
						},
					error : function() {
								/*  alert("error");
								  alert(data); */
								  alert("留言失败");
								//return;
						}
			 });
		} 

	//验证码校验
     function checkYanzhengma() {
		callServer();
				//alert(document.getElementById('codeResult').value);
		if(document.getElementById("codeResult").value.indexOf("false")!=-1){//ietester在此处不能取得验证码
			alert("验证码错误！");
					//document.getElementById("image").src="img.jsp";
			reloadCode();
			return false;
		}else{
			return true;
			}
		}
	function callServer(){
		gHttpObj=getXMLHTTPObj();
		try{
		    var code = document.getElementById("random_num").value;
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

	function jiancha1(){
					
		if($("#selectwd").val()==""||$("#selectwd").val()==null&&$("#selectlb").val()==""||$("#selectlb").val()==null){
				alert("请选择建议网点和类别");
			}else if($("#selectlb").val()==""||$("#selectlb").val()==null){
				alert("请选择建议类别");
			}else if($("#selectwd").val()==""||$("#selectwd").val()==null){
				alert("请选择建议网点");
			}
						
	}
	function jiancha2(){
	/* if($("#selectwd").val()==""||$("#selectwd").val()==null&&$("#selectlb").val()==""||$("#selectlb").val()==null){
			alert("请选择建议网点和类别");
		}else if($("#selectlb").val()==""||$("#selectlb").val()==null){
			alert("请选择建议类别");
		}else if($("#selectwd").val()==""||$("#selectwd").val()==null){
			alert("请选择建议网点");
		}else  */if($("#title").val()==""||$("#title").val()==null){
			alert("请填写标题或内容摘要");
		}
						
	}
</script>

</html>
