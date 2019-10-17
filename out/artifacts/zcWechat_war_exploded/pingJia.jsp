<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="com.gdyiko.zcwx.weixinUtils.Holiday"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path + "/";
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
<title>评价</title>
<link rel="stylesheet" href="bootstrap3/css/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="yms/window/weiXinAppointment/css/common.css" type="text/css"></link>
<link rel="stylesheet" href="css/star-rating.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="yms/window/weiXinAppointment/js/common.js"></script>
<script type="text/javascript" src="yms/window/onlineAppointment/js/id-num-validator.js"></script>
<script type="text/javascript" src="js/star-rating.js"></script>
<style type="text/css">

.appraise-btn{
    width:20%;
	height:44px;
	line-height:44px;
	text-align:center;
	
	color: black;
	background: #D9D9D9;
	
	border: 1px solid #ccc;
	
	border-radius:6px;
}
.appraise-btn:hover{
	/* background:#F80; */
	cursor: pointer;
}
.appraise-btn.selected{
	background:#F80;
	color: white;
	border-top-width: 0px;
	border-left-width: 0px;
}

.textarea{
	width: 100%;
	height: 100%;
	
	border-radius:6px;
	
	padding: 0px;
}

#appointmentForm .rowTitle{
	/* background: #2ca7e2; */
	background:#f99715;
    font-weight: bolder;
    color: white;
	padding-left:3%;
}


</style>

<script type="text/javascript">
	
	$(function(){
			$('#allstart').change(function(){
			var start=$(this).val();
			if(start<=3){
				$("#second").css('display','block');
				$("#third").css('display','block'); 
				$("#fourth").css('display','block');  
			}else{
				$("#second").css('display','none');
				$("#second").val('0');
				$("#third").css('display','none');
				$("#third").val('0');
				$("#fourth").css('display','none');
				$("#fourth").val('0');
			}
		});
    
	
		$("#ydate").val( (function(value){if(typeof(value) == "string"){return value.length > 10 ? value.substring(0,10) : value;}else{return value;}})("${yuYues.ydate}") );
		//设置预约时段
		$("#yTime").val( "${yuYues.ystime}" + "-" + "${yuYues.yetime}" );
		
		var streetStr = getStreetCode("${yuYues.street}");//获取街道的拼音编码
		//judgeState("${yuYues.no}",streetStr);//获取预约记录的状态信息
		//$("#allstart").rating({"readonly": "true"});
		
	});
	
	/**
	 * 用于判断当前页面的状态,并根据页面进行不同的操作
	 *
	 * @param id	预约记录主键
	 * @param streetStr	街道编码
	 * 
	 * @return 0:未取号;1:未办理,null;2:已办理;3:已评价
	 */
	function judgeState(id,streetStr)
	{
		$.post("PingJia!getQueueInfo?streetStr="+streetStr+"&id="+id,{},function(result){
			console.log(result);
			
			var state = 0;
			if( result && result.flag && result.flag == "0" )
			{
				alert("系统错误");
				pageBack();//返回
			}
			else if( result == undefined || result == null || result.length <= 0 )
			{
				state = 0;
			}
			else if( result[0] && result[0].H_isjh == 0 )
			{
				state = 1;
			}
			else if( result[0] && result[0].H_isjh == 1 )
			{
				if( "${yuYues.appraiseResult}" && "${yuYues.appraiseResult}".replace(/(^\s*)|(\s*$)/g, '') != "" )
				{
					$("#H_counter").val(result[0].H_counter);
					$("#h_number").val(result[0].h_number);
					$("#calltime").val(result[0].calltime);
					state = 3;
				}
				else
				{
					$("#H_counter").val(result[0].H_counter);
					$("#h_number").val(result[0].h_number);
					$("#calltime").val(result[0].calltime);
					state = 2;
				}
			}
			getHandleInfo(state);//通过状态设置页面
		},"json");
	}
	/**
	*获取选择中的内容，
	*/
	function getusingdate(){
	var using ="";
		$(".protocol-box .checked").each(function(){
		    using+=$(this).text()+",";
		});
		$('#USING').val(using.substring(0, using.length-1));
	}
	


	/**
	 * 页面存在3中状态:未办理、已办理、已评价;
	 *
	 *	默认为已办理状态
	 *
	 * 未办理: 不可进行评价,不显示相关信息,存在返回按钮
	 * 已办理: 可以进行评价,显示相关信息,存在提交按钮
	 * 已评价: 不可进行评价,显示相关信息,存在返回按钮
	 *
	 * param state{String}	0:未取号;1:未办理;2:已办理;3:已评价
	 *
	 */
	function getHandleInfo(state)
	{
		if( state == 0 || state == 1 )
		{
			if( state == 0 )
			{
				$("#handleResult").val("未取号");
			}
			else if( state == 1 )
			{
				$("#handleResult").val("未办理");
			}
		
			$("#handlerId").css("display","none");
			
			//将提交按钮改为返回按钮
			$("#submitAndBackBtn").html("返回");
			$("#submitAndBackBtn").attr("href","javascript:pageBack();");
		}
		else if( state == 2 )
		{
			$("#handleResult").val("已办理");
		
			attitudeSelect($(".initialize"),"3");//设置默认评价状态
		}
		else if( state == 3 )
		{
			$("#handleResult").val("已评价");
			
			attitudeSelect($(".value-"+"${yuYues.appraiseResult}"), "${yuYues.appraiseResult}" );//设置评价状态
			$(".appraise-btn").removeAttr("onclick");//移除评价按钮上的click事件
			$("#OtherAdvice").val("${yuYues.otherAdvice}");//设置评价建议
			$("#OtherAdvice").prop("readonly","true");//使评价建议只读
			$("#UseAdvice").val("${yuYues.useAdvice}");//设置评价建议
			$("#UseAdvice").prop("readonly","true");//使评价建议只读
			
			var using = "${yuYues.USING}".split(",");
			$.each(using, function(k, v) {
				
				if(v==$('#c_1').html()){
					
					$('#c_1').toggleClass('checked');
					
				}
				if(v==$('#c_2').html()){
					$('#c_2').toggleClass('checked');
				}
				if(v==$('#c_3').html()){
					$('#c_3').toggleClass('checked');
				}
				if(v==$('#c_4').html()){
					$('#c_4').toggleClass('checked');
				}
				if(v==$('#c_5').html()){
					$('#c_5').toggleClass('checked');
				}
				if(v==$('#c_6').html()){
					$('#c_6').toggleClass('checked');
				}
			});
			$('.protocol-box span').unbind("click");
			//将提交按钮改为返回按钮
			$("#submitAndBackBtn").html("返回");
			$("#submitAndBackBtn").attr("href","javascript:pageBack();");
		}
	}
	
	/**
	 * 点击评分按钮:非常满意、满意、一般、不满意的点击事件处理程序
	 * @param value	1、不满意；2、一般；3、满意；4、非常满意；
	 */
	 var attitudeSelect = (function(){
	 	var oldElement = null;
	 	
	 	return function(that,value){
	 		if( oldElement != null )
	 		{
	 			$(oldElement).removeClass("selected");
	 		}
	 		
	 		oldElement = that;
	 	
	 		$(that).addClass("selected");
			$("#appraiseResult").val(value);
	 	};
	 })();
	
	/**
	 * 点击确认提交按钮的事件处理程序,提交成功后,返回主页面
	 */
	function submitForm()
	{
	getusingdate();
	
		$.post("PingJia!pingjiaDO.action" , $("#appointmentForm").serialize() , 
			function(result){
				if(result.flag == "1")
				{
					alert("谢谢您的评价");
					pageBack();//页面返回
					/* window.location.href = "${backUrl}"; */
				}
				else
				{
					alert("评价失败");
					$('#USING').val("");
				}
			} , 
			"json"
		);
	}
	
	
	/**
	 * 页面返回
	 */
	function pageBack()
	{
		window.location.href = "${backUrl}";
	}
	
	/**
	 * 根据街道的no编码，返回映射的拼音编码
	 * @param streetNo	街道的no编码
	 * @return 环市hs、白沙bs、潮连cl、荷塘ht、棠下tx、杜阮dr、堤东td
	 */
	function getStreetCode(streetNo)
	{
		if( streetNo == "0" )
		{
			return "hs";
		}
		else if( streetNo == "1" )
		{
			return "tx";
		}
		else if( streetNo == "2" )
		{
			return "cl";
		}
		else if( streetNo == "3" )
		{
			return "ht";
		}
		else if( streetNo == "4" )
		{
			return "bs";
		}	
		else if( streetNo == "5" )
		{
			return "dr";
		}
		else if( streetNo == "6" )
		{
			return "td";
		}
	}
	
</script>
	
</head>

<body>
	
	<div class="content-2">
		<form id="appointmentForm" method="post">
		<input type="hidden" name="id" value="${yuYues.id}"/><!-- 预约id主键 -->
        <div class="form" style="margin-left:8px;margin-right:8px">
        	
        	<!-- start 预约信息 -->
         	<div class="form-main clear">
         		<label class=" left">预约人</label>
         		<div class="txt right">
         			<input type="text" name="name" value="${yuYues.name}" class="input-txt" readonly="readonly" placeholder="预约人姓名" />
         		</div>
         	</div>
         	<div class="form-main clear">
         		<label class=" left">预约时间</label><!-- 格式：2017-07-12  -->
         		<div class="txt right">
         			<input type="text" id="ydate" name="ydate" class="input-txt" readonly="readonly" placeholder="预约时间" />
         		</div>
         	</div>
         	<div class="form-main clear">
         		<label class=" left">预约时段</label>
         		<div class="txt right">
         			<input type="text" id="yTime" class="input-txt" readonly="readonly" placeholder="预约时段" />
         		</div>
         	</div>
         	<div class="form-main clear">
         		<label class=" left">预约网点</label>
         		<div class="txt right">
         			<input type="text" id="phoneNo" name="phoneNo" value="${streetName}" class="input-txt" readonly="readonly" placeholder="预约网点" />
         		</div>
         	</div>
         	<!-- end 预约信息 -->
         	
         	<!-- 办理的结果信息 -->
         	<div class="form-main clear">
         		<label class=" left">办理情况</label>
         		<div class="txt right">
         			<input type="text"id="handleResult" class="input-txt" readonly="readonly" placeholder="办理结果" />
         		</div>
         	</div>
         	
         	<div id="handlerId" style="display: block;">
	         	<!-- start 办理信息 -->
	         	<div class="form-main clear">
	         		<label class=" left">办理窗口</label>
	         		<div class="txt right">
	         			<input type="text" id="H_counter" class="input-txt" readonly="readonly" placeholder="办理窗口" />
	         		</div>
	         	</div>
	         	
	         	<div class="form-main clear">
	         		<label class="left">办票号</label>
	         		<div class="txt right">
	         			<input type="text" id="h_number" class="input-txt" readonly="readonly" placeholder="办票号" />
	         		</div>
	         	</div>
	         	
	         	<div class="form-main clear">
	         		<label class="left">叫号时间</label>
	         		<div class="txt right">
	         			<input type="text" id="calltime" class="input-txt" readonly="readonly" placeholder="叫号时间" />
	         		</div>
	         	</div>
	         	<!-- end 办理信息 -->
	         	
	         	<!-- start 服务评价 -->
	         	<div class="form-main clear rowTitle" style="text-align: left;">
	         		<div></div><!-- 图标 -->
	         		<div>请问您本次办事使用了邑门式微信公众号哪些服务功能?（多选）</div>
	         	</div>
	         	<div class="form-main clear" >
		         	<label class="protocol-box left" style="text-align:left;width: 45%" >
		         		<span id='c_1' class='' style="font-size:15px">在线办事预约</span>
		         	</label>
		         	<label class="protocol-box txt right" style="text-align:left;width: 55%">
		         		<span id='c_2' class='' style="font-size:15px">办事指南查询</span>
		         	</label>
	         	</div>
	         	<div class="form-main clear" >
		         	<label class="protocol-box left"  style="text-align:left;width: 45%" >
	         		<span id='c_3' class='' style="font-size:15px"> 排队叫号提醒</span>
		         	</label>
		         	<label class="protocol-box txt right" style="text-align:left;width: 55%">
		         		<span id='c_4' class='' style="font-size:15px">中心现场拥挤度查询</span>
		         	</label>
	         	</div>
	         	<div class="form-main clear" >
		         	<label class="protocol-box left"  style="text-align:left;width: 45%" >
	         		<span id='c_5' class='' style="font-size:15px">中心导航</span>
		         	</label>
		         	<label class="protocol-box txt right" style="text-align:left;width: 55%">
		         		<span id='c_6' class='' style="font-size:15px">业务办理进度追踪</span>
		         	</label>
	         	</div>
				<input id="USING" name="USING" type="hidden">
	         	
	         	<div id='frist'>
		         	<div class="form-main clear rowTitle" style="text-align: left;">
		         		<div></div><!-- 图标 -->
		         		<div>请您对本次办事的整体满意度进行评分（五星评分）</div>
		         	</div>
		         
		         	<div class="form-main clear " style="text-align: center;">
			         	<div class="container">
			         		<input id="allstart" name='allstart'  type="number" value='${yuYues.allstart}'  class="rating" min=0 max=5 step=1>
			         	
			         	</div>
		         	</div>  	
	         	
	         		<!-- <input class="appraise-btn value-4" type="button" value="非常满意" onclick="attitudeSelect(this,'4');">
	         		<input class="appraise-btn initialize value-3" type="button" value="满意" onclick="attitudeSelect(this,'3');">
	         		<input class="appraise-btn value-2" type="button" value="一般" onclick="attitudeSelect(this,'2');">
	         		<input class="appraise-btn value-1" type="button" value="不满意" onclick="attitudeSelect(this,'1');">
	         		
	         		<input id="appraiseResult" name="appraiseResult" style="display: none;"> -->
	         	</div>
	         	<div id='second' style="display: none;">
		         	<div class="form-main clear rowTitle" style="text-align: left;">
		         		<div></div><!-- 图标 -->
		         		<div>请您对服务大厅的管理秩序进行评分（五星评分）</div>
		         	</div>
		         
		         	<div class="form-main clear " style="text-align: center;">
			         	<div class="container">
			         		<input id="glstart" name='glstart' type="number"  value='${yuYues.glstart}' class="rating" min=0 max=5 step=1 >
			         	
			         	</div>
		         	</div>
	         	</div>
	         	<div id='third' style="display: none;">
		         	<div class="form-main clear rowTitle" style="text-align: left;">
		         		<div></div><!-- 图标 -->
		         		<div>请您对工作人员的服务质量进行评分（五星评分）</div>
		         	</div>
		         
		         	<div class="form-main clear " style="text-align: center;">
			         	<div class="container">
			         		<input id="fwstart" name='fwstart' type="number"  value='${yuYues.fwstart}' class="rating" min=0 max=5 step=1  >
			         	
			         	</div>
	
		         	</div>
	         	</div>
	         	<div id="fourth"  style="display: none;">
	         	<div class="form-main clear rowTitle" style="text-align: left;">
	         		<div></div><!-- 图标 -->
	         		<div>请您对服务大厅为退伍军人、老弱残孕、抱婴者开放绿色通道的指引清晰度进行评分（五星评分）</div>
	         	</div>
	         
	         	<div class="form-main clear " style="text-align: center;">
		         	<div class="container">
		         		<input id="tdstart" name='tdstart' type="number" class="rating"  value='${yuYues.tdstart}' min=0 max=5 step=1  >
		         	
		         	</div>

	         	</div>
	         	</div>
	         	<div class="form-main clear rowTitle" style="text-align: left;">
	         		<div></div><!-- 图标 -->
	         		<div>如您在使用邑门式微信服务号功能（如在线预约、叫号提醒、办事指南查询）过程中出现问题或有优化建议，请留言给我们：</div>
	         	</div>
	         	<div class="form-main clear" style="padding: 5px 20px;height: 62px;">
	         		<!-- <input class="input-txt textarea" type="text" name="appraiseAdvice" multiple="multiple" placeholder="请输入评价建设内容" /> -->
	         		<textarea class="textarea" id="UseAdvice" name="UseAdvice" placeholder="请输入服务建议内容"></textarea>
	         	</div>
	         	<div class="form-main clear rowTitle" style="text-align: left;">
	         		<div></div><!-- 图标 -->
	         		<div>如您对邑门式的服务有其他意见建议，请留言给我们：</div>
	         	</div>
	         	<div class="form-main clear" style="padding: 5px 20px;height: 62px;">
	         		<!-- <input class="input-txt textarea" type="text" name="appraiseAdvice" multiple="multiple" placeholder="请输入评价建设内容" /> -->
	         		<textarea class="textarea" id="OtherAdvice" name="OtherAdvice" placeholder="请输入服务建议内容"></textarea>
	         	</div>
	         	<!-- end 服务评价 -->
	         </div>
        </div>
        <div class="big-btn" id="submitDiv" style="display: block;">
        	<a id="submitAndBackBtn" href="javascript:submitForm();">确认提交</a>
        </div>
        </form> 
	</div>
	
</body>
</html>

