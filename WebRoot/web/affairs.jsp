<%@page import="com.gdyiko.zcwx.po.SsAffairGuide"%>
<%@page import="com.gdyiko.zcwx.service.SsAffairsGuideService"%>
<%@page import="com.gdyiko.tool.BeanUtilEx"%>
<%@page import="com.gdyiko.zcwx.po.SsAffairs"%>
<%@page import="com.gdyiko.zcwx.service.SsAffairsService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String baseDic="",baseDicId="",type="";
baseDic=new String(request.getParameter("baseDic").getBytes("ISO-8859-1"),"utf-8");
baseDicId=request.getParameter("baseDicId");
type=request.getParameter("baseDicType");

ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
SsAffairsService ssAffairsService = (SsAffairsService) ctx.getBean("ssAffairsService");  
SsAffairsGuideService ssAffairsGuideService = (SsAffairsGuideService) ctx.getBean("ssAffairsGuideService");
List<SsAffairs> ssAffairsList=new ArrayList<SsAffairs>();

if(type.equals("depart")){
	SsAffairs ssAffairs2= new SsAffairs();
	ssAffairs2.setDepartid(baseDicId);
	ssAffairsList = ssAffairsService.findEqualByEntity(ssAffairs2, BeanUtilEx.getNotNullEscapePropertyNames(ssAffairs2));
	
}
if(type.equals("life")){
	SsAffairs ssAffairs2= new SsAffairs();
	ssAffairs2.setSortid(baseDicId);
	ssAffairsList = ssAffairsService.findEqualByEntity(ssAffairs2, BeanUtilEx.getNotNullEscapePropertyNames(ssAffairs2));
}

SsAffairGuide ssAffairGuide1 = new SsAffairGuide();
ssAffairGuide1.setAffairid(ssAffairsList.get(0).getAffairid());
SsAffairGuide ssAffairGuide= ssAffairsGuideService.findEqualByEntity(ssAffairGuide1, BeanUtilEx.getNotNullEscapePropertyNames(ssAffairGuide1)).get(0);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>张槎街道行政服务中心</title>
    
	<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="X-UA-Compatible"  content="IE=edge,chrome=1">
	
	<link rel="stylesheet" href="content/reflow.css">
	
	<link rel="stylesheet" href="content/interaction_pc.css"></head>

	<script src="scripts/jquery.min.js" type="text/javascript"></script>
	<script src="scripts/base.js" type="text/javascript"></script>
	<script src="scripts/hammer.min.js" type="text/javascript"></script>
	<script src="js/layer/layer.js" type="text/javascript"></script>
	
<style type="text/css">
p{
	font-size:16px;
}
.area
        {

            background-color: green;
            height: auto;
            border: 2px solid;
            margin: auto;
        }
        .rightarea
        {
            float: left;
            width: 30%;
            height: 300px;
            background-color: red;
        }
        .leftarea
        {
            float: left;
            width: 30%;
            height: 300px;
            background-color: yellow;
        }
p.pt{
	color:#3b95eb;
	font-weight:bolder;
	margin:8px;
	font-size:16px;
}

.iff{
	
	z-index:10000;
}
.fixPosition {
    position: fixed;
}
.width100 {
    width: 100%;
}
.foot {
    border-top: #ddd 2px solid;
    background: #f8f8f8;
}
.bottomPosition {
    bottom: 0;
}

.layui-layer-white{background:white}

</style>

  </head>
  
<script type="text/javascript">
  	function changeResult(affairId){
  	//alert(affairId);
  		 $.post("<%=basePath%>ssAffairsGuideInfo!findByAffairIdToWeb",{ affairid: affairId} ,function(result) {
			//alert(result);
			//console.log(result);

			$("#according").html(result.according);
			$("#condition").html(result.condition);
			$("#material").html(result.material);
			$("#procedures").html(result.material);
			$("#xrndomu").html(result.xrndomu);
			$("#site").html(result.site);
			$("#time").html(result.time);
			$("#inquire").html(result.inquire);
			$("#charge").html(result.charge);
			$("#sepcialversion").html(result.sepcialversion);
			$("#procedures").html(result.procedures);
			
			$("#affairid").val(affairId);

			if(result.isonline=="true"){
				$("#wsbsDiv").show();				
			}else{
				$("#wsbsDiv").hide();
			}
        }, "json"); 
  	}
  
  </script>
  
  <body>

    <div class="header titlebar">
		<h2 class="title" data-bind="text:model.themeName()"><%=baseDic %></h2>
		<a href="ssBaseDicInfo!findAllByBaseDicTypeToWeb" class="btn_back link_checkJump" jslink="ssBaseDicInfo!findAllByBaseDicTypeToWeb"><span class="ic ic_back"></span></a>
	</div>
	<div class="contain show_subcontent show_review" style="height: 588px;">
		<div class="content content_matter content_matterlist">
			<div class="scroll_parent">
				<div class="scroll_content" data-bind="foreach:matterData">
					
					
				</div>
			</div>
		</div>
		<div class="content content_subcontent transition" style="touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);width:100%">
			<div class="content content_form content_formlist" id="content_formlist">
				<div class="title_formNeed">办事指南</div>
				<div class="scroll_parent" style="height: 540px;">
					<div class="scroll scroll_content auto_plus">
						<dt class="title_list"></dt>
						<div data-bind="foreach:angencyData">
						
						 <%
						
							for(int k =0 ;k<ssAffairsList.size();k++){
	         					SsAffairs ssaffairs1 = (SsAffairs)ssAffairsList.get(k);
	         					if(k==0){
	         					
						 %>
						 <dd data-bind="click:angencyDetail" class="angency selection" onclick="changeResult(<%=ssaffairs1.getAffairid() %>)"><div class="item_form" ><p class="form_name" data-bind="text:angencyName"><%=ssaffairs1.getAffairname() %></p><span class="ic ic_arrowright"></span></div></dd>
						 <%
						 	}else{
						 %>
						 
						 	<dd data-bind="click:angencyDetail" class="angency" onclick="changeResult(<%=ssaffairs1.getAffairid() %>)"><div class="item_form"><p class="form_name" data-bind="text:angencyName"><%=ssaffairs1.getAffairname() %></p><span class="ic ic_arrowright"></span></div></dd>
						 
						 <%
						 	
						 	}
						 
						 }
						  %> 
							
							
						</div>
					</div>
				</div>
			</div>
			<div class="content content_imgcontent">
				<div id="scrollDiv" class="content_review">
					<div  class="scroll body_review" style="float: left;width: 75%" data-bind="foreach:picData">
					
					<input type="hidden" id="affairid" name="affairid" value="<%=ssAffairGuide.getAffairid() %>" />
					
					<p class="pt">一、办理依据：</p>
					<p id="according"><%=ssAffairGuide.getAccording()==null?"":ssAffairGuide.getAccording() %></p>
					<p class="pt">二、办理条件：</p>
					<p id="condition"><%=ssAffairGuide.getCondition()==null?"":ssAffairGuide.getCondition() %></p>
					<p class="pt">三、申请材料：</p>
					<p id="material"><%=ssAffairGuide.getMaterial()==null?"":ssAffairGuide.getMaterial() %></p>
					<p class="pt">四、办理程序：</p>
					<p id="procedures"><%=ssAffairGuide.getProcedures()==null?"":ssAffairGuide.getProcedures() %></p>
					<p class="pt">五、办理部门：</p>
					<p id="xrndomu"><%=ssAffairGuide.getXrndomu()==null?"":ssAffairGuide.getXrndomu() %></p>
					<p class="pt">六、受理地址：</p>
					 <p id="time"><%=ssAffairGuide.getSite()==null?"":ssAffairGuide.getSite() %></p>
					<p class="pt">七、办结时限：</p>
					 <p id="time"><%=ssAffairGuide.getTime()==null?"":ssAffairGuide.getTime() %></p>
					<p class="pt">八、咨询查询：</p>
					 <p id="charge"><%=ssAffairGuide.getInquire()==null?"":ssAffairGuide.getInquire() %></p>
					 <p class="pt">九、收费依据和标准：</p>
					 <p id="charge"><%=ssAffairGuide.getCharge()==null?"":ssAffairGuide.getCharge() %></p>
					<p class="pt">十、特别说明：</p>
					<p id="sepcialversion"><%=ssAffairGuide.getSepcialversion()==null?"":ssAffairGuide.getSepcialversion() %></p>
					
					
					</div>
					<%--  二维码图片 
					<div style="width: 20%;float: right;">
					<div style="width: 11%;right: 2.7%; top: 50px; position: fixed;">
						<img style="" id="qr"
							src="QRCode_img.jsp?affairid=<%=ssAffairGuide.getAffairid()%>" />
						<div style="font-size: 12px;text-align: center;">
							微信扫一扫</br>标记当前信息
							<span id="btn2">向上</span>
							<span id="btn1">向下</span>
						</div>
					</div>
					
					</div> --%>
					
				</div>
				<div class="tips_toFullScreen" style="right:140px">
					<span class="clicktips">点击全屏按钮全屏查看</span>
					<span class="btn btn_iknow">我知道了</span>
					<span class="img_fingers" ></span>
				</div>
				<div class="control_form" >
					<div class="backbg"></div>
					<div class="fullCover prevent"></div>
					<div class="zoomctrl fullScreen_show">
						<b class="btn btn_zoom btn_zoom_in">
							<span class="ic ic_zoom_in"><img src="images/ic_zoom_in.png"></span>
						</b>
						<p class="zoomX">100%</p>
						<b class="btn btn_zoom btn_zoom_out">
							<span class="ic ic_zoom_out"><img src="images/ic_zoom_out.png"></span>
						</b>
					</div>
					
					<b class="btn btn_screenContorl btn_fullScreen" style="right:-10px">
						<span class="ic ic_fullScreen fullScreen_hide"><img src="images/ic_fullscreen.png"></span>
						<span class="ic ic_fullScreen_out fullScreen_show"><img src="images/ic_outfullscreen.png"></span>
						<span class="status_fullscreen">全屏</span>
					</b>
				</div>
				<div class="caution">※该表仅供参考，实际以窗口前台表格为准</div>

				
					<div id="wsbsDiv"  style="display: none;position: absolute;bottom: 16px;right: 320px;min-width: 130px;height: 48px;line-height: 48px;text-align: center;border-radius: 4px;color: #fff;overflow: hidden;">
						<div  style="position: absolute;left: 0;top: 0;width: 100%;height: 100%;background: #000;opacity: .4;"></div>
						
						
						<b id="wsbs" onclick="openQRCode();" style="position: absolute;right: 0;top: 0;padding: 0 24px 0 48px;font-weight: normal;cursor: pointer;right:-10px" >
							<span style="position: absolute; left: 24px; top: 50%; margin-top: -8px;" >
							 <img src="images/dy.png" style="width: 18px;height: 18px;"> 
							</span>
							
							<span>网上办事</span>
						</b>
					</div>

			</div>
			
		</div>
		
	</div>

<div class=" foot fixPosition width100 bottomPosition iff">
					<div align="center" style="line-height:25px;">
						<span style='font-size:12px;'>张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
					</div>
			</div>


<div id="QRCodeImg" class="hide">
<div>
<img id="imgId" src="" width="200px" height="200px">
<div style="text-align:center;bottom:10px">使用手机微信扫一扫</div>
</div>
</div>  


<script>

var countdown;
var t;
var scanLayer;


$(document).ready(function(){
	var isonline='<%=ssAffairGuide.getIsonline()%>';
	if(isonline=='true'){
		$("#wsbsDiv").show();
		
	} 

}) 


function openQRCode(){
	
	$.post('QRCode!createUUID', function(data) {
		$("#imgId").attr("src","QRCode!createQRCode"); 
		scanLayer = layer.open({
			  type: 1,
			  title: "微信扫一扫",
			  closeBtn: 0,
			  area: '200px',
			  skin: 'layui-layer-white', 
			  shadeClose: true,
			  content: $('#QRCodeImg'),
			  success: function(layero, index){
				  
				  countdown=25;
				  settime();

			  }
		});
			
	});

/* scanLayer = layer.open({
  type: 1,
  title: "微信扫一扫",
  closeBtn: 0,
  area: '200px',
  skin: 'layui-layer-white', //没有背景色
  shadeClose: true,
  content: $('#QRCodeImg')
});

countdown=25;
settime(); */

}

function settime() {
        if (countdown == 5) {
        	layer.close(scanLayer); 
            clearTimeout(t);
            
        } else {
            scanFun();
            countdown--;
            t=setTimeout(function() {
                settime()
            },1000)
        }
 	
}

function scanFun(){
	var affairid=$("#affairid").val();
	//location.href="QRCode!scanQRCode?affairid="+affairid;
	$.post('QRCode!scanQRCode', function(data) {
		
		var data1 = eval("(" + data + ")");
		if(data1.state="success"){
			clearTimeout(t);
			location.href="ssAffairsObjectInfo!findByAffairIdToWeb?affairid="+affairid;
		}
		
	});
}


	/*——————————滑动收起预览——————————*/
	var yulan = document.querySelector(".content_subcontent");
	var mc = new Hammer(yulan);
	mc.on('panstart',subcontent_dragable_start);
	mc.on('pan',subcontent_dragable_move);
	mc.on('panend',subcontent_dragable_end);
	function subcontent_dragable_start(ev) {
		if($('.fullScreenMode').length<=0 ){
			$('.content_subcontent').removeClass('transition');
		}
	}
	function subcontent_dragable_move(ev) {
		origin_left=$(".content_subcontent").css('left');
		minDragLeft=$(window).width()*.2;
		minDragLeft_percent='20%';
		dragLeft=ev.deltaX*.8+minDragLeft;
		/*if($('fullScreenMode').length>0){
			$(".content_subcontent").css('left',minDragLeft_percent);
		}*/
		if(dragLeft<=minDragLeft || $('.fullScreenMode').length>0 || Math.abs(ev.deltaX)<100){
			$(".content_subcontent").css('left',minDragLeft_percent);
		}else{
			$(".content_subcontent").css('left',dragLeft);
		}
	}
	function subcontent_dragable_end(ev) {
		if($('.fullScreenMode').length<=0){
			minDragLeft=$(window).width()*.2;
			minDragLeft_percent='20%';
			outMinLeft=$(window).width()*.2+$(window).width()*.8*.3;
			dragLeft=ev.deltaX*.8+minDragLeft;
			$('.content_subcontent').addClass('transition');
			if(dragLeft>outMinLeft){
				$('.show_subcontent').removeClass('show_subcontent');
				$(".content_subcontent").css('left','100%');
				$('.content_matterlist .selection').removeClass('selection')
			}else{
				$(".content_subcontent").css('left',minDragLeft_percent);
			}
		}
	}
	
var count = 60; //间隔函数，1秒执行  
	var curCount;//当前剩余秒数  
	var InterValObj;
	
	function print(){
	curCount=count;
		//alert($('#according').text().replace(new RegExp("<br>","g"),"\n"));
		//console.log($('#according').text().replace(/<br\/>/g, "\n"));
		 var affairNamePrint=$('#affairName').text()
		var printAccording=$('#according').html().replace(new RegExp("<br>","g"),"\\n");
		var printCondition=$('#condition').html().replace(new RegExp("<br>","g"),"\\n");
		var printMaterial=$('#material').html().replace(new RegExp("<br>","g"),"\\n");
		var printProcedures=$('#procedures').html().replace(new RegExp("<br>","g"),"\\n");
	//	var printXrndomu=$('#xrndomu').html().replace(new RegExp("<br>","g"),"\\n");
		var printSite=$('#site').html().replace(new RegExp("<br>","g"),"\\n");
		var printTime=$('#time').html().replace(new RegExp("<br>","g"),"\\n");
		//var printInquire=$('#inquire').html().replace(new RegExp("<br>","g"),"\\n");
		var printCharge=$('#charge').html().replace(new RegExp("<br>","g"),"\\n");
		var printSepcialversion=$('#sepcialversion').html().replace(new RegExp("<br>","g"),"\\n");

		try
		   {
		// window.external.printWord("{'项目名称':'"+affairNamePrint+"','政策依据':'"+printAccording+"','受理条件':'"+printCondition+"','申请材料':'"+printMaterial+"',','受理地址':'"+printSite+"','办结时限':'"+printTime+"',','收费标准':'"+printCharge+"','特别说明':'"+printSepcialversion+"'}");
		window.external.printWord("{\"办理对象\":\""+printCondition+"\",\"办理依据\":\""+printAccording+"\",\"办理条件\":\""+printCondition+"\",\"提交材料\":\""+printMaterial+"\",\"办理流程\":\""+printProcedures+"\",\"办事窗口\":\""+printSite+"\",\"承诺办理期限\":\""+printTime+"\",\"法定办理期限\":\""+printTime+"\",\"收费依据和标准\":\""+printCharge+"\",\"特别说明\":\""+printSepcialversion+"\"}");
		$("#print").removeAttr('onclick');
			$("#print").html( + curCount + "s 后获取");  
     	InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器，1秒执行一次
		   }
		catch(err)
		   {
		   alert(err);
		   $("#print").removeAttr('onclick');
			$("#print").html( + curCount + "s 后获取");  
     	InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器，1秒执行一次
		   }
		
		//window.external.printWord("{'项目名称':'"+affairNamePrint+"','政策依据':'"+printAccording+"','受理条件':'"+printCondition+"','申请材料':'"+printMaterial+"',','受理地址':'"+printSite+"','办结时限':'"+printTime+"',','收费标准':'"+printCharge+"','特别说明':'"+printSepcialversion+"'}");
		
	}
		//timer处理函数  
function SetRemainTime() {  
    if (curCount == 0) {                
        window.clearInterval(InterValObj);// 停止计时器  
       // $("#print").removeAttr("disabled");// 启用按钮  
       // $("#print").removeAttr('onclick');
       $("#print").attr("onclick", "printword()");
         
         
        $("#print").html("<span style=\"position: absolute; left: 24px; top: 50%; margin-top: -8px;\" >"+
						 "<img src=\"images/dy.png\" style=\"width: 18px;height: 18px;\">"+ 
						"</span>"+
						"<span>打印</span>");
    }else {  
    	
        curCount--;  
        $("#print").html(+ curCount + "s 后获取");  
    }  
}

(function($){
$.fn.extend({
 Scroll:function(opt,callback){
 //参数初始化
 if(!opt) var opt={};
 var _btnUp = $("#"+ opt.up);//Shawphy:向上按钮
 var _btnDown = $("#"+ opt.down);//Shawphy:向下按钮
 var timerID;
 var _this=this.eq(0).find("ul:first");
 var lineH=_this.find("li:first").height(), //获取行高
  line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10), //每次滚动的行数，默认为一屏，即父容器高度
  speed=opt.speed?parseInt(opt.speed,10):500; //卷动速度，数值越大，速度越慢（毫秒）
  timer=opt.timer //?parseInt(opt.timer,10):3000; //滚动的时间间隔（毫秒）
 if(line==0) line=1;
 var upHeight=0-line*lineH;
 //滚动函数
 var scrollUp=function(){
  _btnUp.unbind("click",scrollUp); //Shawphy:取消向上按钮的函数绑定
  _this.animate({
  marginTop:upHeight
  },speed,function(){
  for(i=1;i<=line;i++){
   _this.find("li:first").appendTo(_this);
  }
  _this.css({marginTop:0});
  _btnUp.bind("click",scrollUp); //Shawphy:绑定向上按钮的点击事件
  });
 }
 //Shawphy:向下翻页函数
 var scrollDown=function(){
  _btnDown.unbind("click",scrollDown);
  for(i=1;i<=line;i++){
  _this.find("li:last").show().prependTo(_this);
  }
  _this.css({marginTop:upHeight});
  _this.animate({
  marginTop:0
  },speed,function(){
  _btnDown.bind("click",scrollDown);
  });
 }
 //Shawphy:自动播放
 var autoPlay = function(){
  //if(timer)timerID = window.setInterval(scrollUp,timer);
 };
 var autoStop = function(){
 // if(timer)window.clearInterval(timerID);
 };
  //鼠标事件绑定
 _this.hover(autoStop,autoPlay).mouseout();
 _btnUp.css("cursor","pointer").click( scrollUp ).hover(autoStop,autoPlay);//Shawphy:向上向下鼠标事件绑定
 _btnDown.css("cursor","pointer").click( scrollDown ).hover(autoStop,autoPlay);
 } 
})
})(jQuery);
$(document).ready(function(){
 $("#scrollDiv").Scroll({line:4,speed:500,timer:3000,up:"btn1",down:"btn2"});
});
</script>
<script src="scripts/knockout-3.4.0.js" type="text/javascript"></script>
<script src="scripts/getUrlParam.js"></script>
<script src="ctrl/service.js" type="text/javascript"></script>
<!-- <script src="ctrl/formviewerModel.js" type="text/javascript"></script> -->
</body>
</html>
