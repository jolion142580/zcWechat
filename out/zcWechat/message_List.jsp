<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.*" %>

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
<title>留言信息</title>
<link rel="stylesheet" href="yms/window/weiXinAppointment/css/common.css" type="text/css"></link>
<link rel="stylesheet" href="lib/weui.min.css" type="text/css"></link>
<link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"></link>
<style type="text/css">
.bottom {  
    background-color: #f7f7fa;   
    border:1px solid #C0C3C9;   
    text-align:center;   
    z-index:999;   
    position:fixed;   
    bottom:0;   
    left:0;   
    width:100%;   
    height:78px;   
    /* line-height:40px;  */ 
 /* for IE6 */   
    _position:absolute;   
    _top: expression(documentElement.scrollTop + documentElement.clientHeight-this.offsetHeight);  
    overflow:visible;   
}  

.weui-tabbar__icon {
	display: inline-block;
	width: 50px;
	height: 50px;
}

</style>
<!-- <link rel="stylesheet" href="css/pullToRefresh.css" type="text/css"></link>
<link rel="stylesheet" href="css/reset.css" type="text/css"></link> -->
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="yms/window/weiXinAppointment/js/common.js"></script>
<script type="text/javascript" src="lib/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/jquery-weui.min.js"></script>
<%-- <script type="text/javascript" src="js/iscroll.js"></script> --%>
<%-- <script type="text/javascript" src="js/pullToRefresh.mini.js"></script> --%>
</head>

<script type="text/javascript">

	var kt=1;
	var ys=0;

           function gotoUp(){
           		if(parseInt($("#kt").val())==1|| parseInt($("#kt").val())==""){
           			alert("当前为第一页");
           			return
           		}
		   		kt = parseInt($("#kt").val())-1;
		   		//alert($("#ys").text());
		   		ys = 0;
		   		gotofy(kt,ys);
		   		
		   
		   }
		   function gotoDown(){
		   		var kt = parseInt($("#kt").val())+1;//从kt获取回来的，加一页进入下一页
		   		
		   		var p = parseInt($("#ys").val()); //余数
		   		var ysl = parseInt($("#ysl").val());//页数
		   		//alert(p);
		   		if(ysl<kt){
           			alert("当前为最后一页");
           			return
           		}
		   		if(ysl == kt){
		   		 ys=p;
		   		}
		   		gotofy(kt,ys);
		   }
		   
		   function gotofy(kt,ys){
		  // alert();
		   $("#kt").val(kt);//把初始值传到p保存
		   $("#ys").val(ys);//把余数参数传到p保存
		   	$("#list").empty();
		   //	alert(kt);
		    var iy=0;
			var ih=5;
			if(kt==1){
				iy = 1*kt-1;
			}else{
				iy = 5*kt-5;
			}
			//if(yushu ==0){
			if(ys==0){
				ih = 5*kt;
			}else{
				ih = 5*(kt-1)+ys;
			}
				
			$.ajax(
			       {
			               type: "post",
			               url:'complaint!findLikeByEntitypy',
							data : {
							},
							//contentType: "application/json; charset=utf-8",
							dataType : "json",
							success : function(r) {

							//	var r=data.rows;
								
								var  p= "";
								var  a ="<div align='center'><span onclick='gotoUp()'>上一页</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
								console.log(r);
								//alert(data.length);
								var yushu = r.length%5;
								//alert(yushu);1
								var abq = Math.ceil(r.length/5);
								$("#ysl").val(abq);
								if(yushu != 0 && r.length < 5){
									ih = yushu;
								}
								
								for(k=0;k < abq;k++){
									if (k==(abq-1)){
										a += "<a  onclick='gotofy(kt=this.text,"+yushu+")'>"+(k+1)+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
										//alert(yushu);
										$("#ys").val(yushu);
									}else{
										a += "<a onclick='gotofy(kt=this.text,0)'>"+(k+1)+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
									}
									
									//alert(a);
								}
								a += "<span onclick='gotoDown()' >下一页</span></div>";
								//$.each(r,function (i,val){
								for(i = iy; i < ih; i++){
								var name = r[i].complaint_Name;
								if(r[i].complaint_Name.trim()=='') {
									name='匿名用户';
								}else if(r[i].complaint_Name!='匿名用户'){
									name=r[i].complaint_Name.substring(0,1)+'**';
								} 

					 
				p+="<div class=\"appointment-main\"><h5>"+name+"  于  "+r[i].complaintTime+" 发表 </h5><h3 style=\"color: #999\">"+r[i].complaint_Content+"</h3>"+
					 "<h5 style='color:#1dabec;'>回复</h5><h3 style='margin-top:10px'>"+r[i].complaint_Reply+"</h3><div class=\"clear\"><h5 class=\"right\">"+r[i].complaintReplyTime.substring(0,10)+"</h5></div></div>";
				}
				
				//}); 
								p += a;
								$("#wrapper ul").append(p);
								
								
							},
							error : function() {
								 alert("加载信息失败，请重试！");
								
								return;
							}
						});
		   
		   }

	 function getList(){
	 
	  $("#kt").val(kt);//把初始值传到p保存
		   $("#ys").val(ys);//把余数参数传到p保存
		   	$("#jyhf").empty();
		   //	alert(kt);
		    var iy=0;
			var ih=5;
			if(kt==1){
				iy = 1*kt-1;
			}else{
				iy = 5*kt-5;
			}
			//if(yushu ==0){
			if(ys==0){
				ih = 5*kt;
			}else{
				ih = 5*(kt-1)+ys;
			}
	 
			var el, li, i;																		
			el =document.querySelector("#wrapper ul");									
			el.innerHTML='';	
			
		$.ajax({
			url:'/newqueue/Complaint!showJsonDatagrid',
			type:'POST',
			dataType:"JSON",
			data:{
				rows:5,
				page:generatedCount
			},
			success:function(date){
			var r =eval(date.rows);
				console.log(date);
				var list="";

				 $.each(r,function (i,val){
				var name = val.complaint_Name;
				if(val.complaint_Name!='匿名用户'&&val.complaint_Name.trim()!=''){
					name=val.complaint_Name.substring(0,1)+'**';
				}else if(val.complaint_Name.trim()=='') {
					name='匿名用户';
				}

					 li = document.createElement('li');
				li.innerHTML ="<div class=\"appointment-main\"><h5>"+name+"  于  "+val.complaintTime+" 发表 </h5><h3>"+val.complaint_Content+"</h3>"+
					 "<h5 style='color:#1dabec;'>回复</h5><h3>"+val.complaint_Reply+"</h3><div class=\"clear\"><h5 class=\"right\">"+val.complaintReplyTime.substring(0,10)+"</h5></div></div>";
				
				el.insertBefore(li, el.childNodes[0]);	
				}); 

				++generatedCount;
				if(generatedCount>=Math.ceil(date.total/5)-1){
					generatedCount=Math.ceil(date.total/5)-1;
				} 

			},
			error:function(e){
				alert("加载信息失败，请重试！");
									
				return;
			}
		});
	} 
	
	
	/* function Refresh() {														
		setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
			var el, li, i;																		
			el =document.querySelector("#wrapper ul");									
			el.innerHTML='';																
			
			$.ajax({
		   		url:'/newqueue/Complaint!showJsonDatagrid',
				type:'POST',
				dataType:"JSON",
				data:{
				rows:5,
				page:generatedCount
				
				},
		     success: function (date) {
		     	var r =eval(date.rows);
				console.log(r);
				var list="";
				$.each(r,function (i,val){
				var name = val.complaint_Name;
				if(val.complaint_Name!='匿名用户'&&val.complaint_Name.trim()!=''&&val.complaint_Name.trim()!=null){
					name=val.complaint_Name.substring(0,1)+'**';
				}else if(val.complaint_Name.trim()==''||val.complaint_Name.trim()=='NULL') {
					name='匿名用户';
				}
				li = document.createElement('li');
				li.innerHTML ="<div class=\"appointment-main\"><h5>"+name+"  于  "+val.complaintTime+" 发表 </h5><h3>"+val.complaint_Content+"</h3>"+
					 "<h5 style='color:#1dabec;'>回复</h5><h3>"+val.complaint_Reply+"</h3><div class=\"clear\"><h5 class=\"right\">"+val.complaintReplyTime.substring(0,10)+"</h5></div></div>";
				
				el.insertBefore(li, el.childNodes[0]);	 
				});
				++generatedCount;
				if(generatedCount>=Math.ceil(date.total/5)-1){
					generatedCount=Math.ceil(date.total/5)-1;
				} 
				
		     }
		    });
																								 
			wrapper.refresh();

			}, 1000);
		}
		 */
	/* function Load() {
		setTimeout(function () {
			var el, li, i;
			el =document.querySelector("#wrapper ul");
			
			$.ajax({
		   		url:'/newqueue/Complaint!showJsonDatagrid',
				type:'POST',
				dataType:"JSON",
				data:{
				rows:5,
				page:generatedCount
					//code:code
				},
		     success: function (date) {
		     	var r =eval(date.rows);
				console.log(r);
				var list="";
				$.each(r,function (i,val){
				var name = val.complaint_Name;
				if(val.complaint_Name!='匿名用户'&&val.complaint_Name.trim()!=''&&val.complaint_Name.trim()!=null){
					name=val.complaint_Name.substring(0,1)+'**';
				}else if(val.complaint_Name.trim()==''||val.complaint_Name.trim()=='NULL') {
					name='匿名用户';
				}
				li = document.createElement('li');
				li.innerHTML ="<div class=\"appointment-main\"><h5>"+name+"  于  "+val.complaintTime+" 发表 </h5><h3>"+val.complaint_Content+"</h3>"+
					 "<h5 style='color:#1dabec;'>回复</h5><h3>"+val.complaint_Reply+"</h3><div class=\"clear\"><h5 class=\"right\">"+val.complaintReplyTime.substring(0,10)+"</h5></div></div>";
				
				el.insertBefore(li, el.childNodes[0]);	 
					
				});
				generatedCount++;
				if(generatedCount>=Math.ceil(date.total/5)-1){
					generatedCount=Math.ceil(date.total/5)-1;
				} 
		     }
		    });
			
			wrapper.refresh();
	
		}, 1000);	
	} */
	
 $(function(){
 
gotofy(kt,ys);

/* $("#yanz").attr('href',"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/message_add.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect"); */
$("#yanz").attr('href',"message_add.jsp");
/* $("#yanz").click(function () {
	                 //  window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaec78dd064e22ada&redirect_uri=http://zhengqiao.ss.gov.cn/wxfb/WxServlet?tiaoznum=1&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect';
						
	            });
	 */
	});
</script>

<body>
	
	<div class="header">

	</div>
	<div class="appointment">
			<h3>用户留言</h3>
		<div id="wrapper" class="appointment-box">
			<ul id='list'>
				
			</ul>
			<div style="display: none;"><p id="kt"></p><p id="ys"></p><p id="ysl"></p></div>
			<div align="center" style="line-height:25px;">
			<span style="font-size:12px;">版权所有：蓬江邑门式&nbsp;&nbsp;技术支持：南邮英科</span>
		</div>
		</div>
		
				<div style="height:60px"></div>
		<div class="bottom">
			<a id="yanz" href=""
				class="weui-tabbar__item">
				<div class="weui-tabbar__icon">
					<img src="icon/ADD.png" alt="我要建议" />
					<p class="weui-tabbar__label" style="margin:0px">我要建议</p>
				</div>
				
			</a>
		</div>
	</div>
</body>
</html>