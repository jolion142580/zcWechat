<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gdyiko.base.po.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<%

java.util.Calendar a=java.util.Calendar.getInstance();
int year = a.get(java.util.Calendar.YEAR);
int month = a.get(java.util.Calendar.MONTH);
%>
<%
	String  loginid = "";
	
	String memid="";
	
	String username = "";
	
	String depname = "";
	
	MemGeninf memGeninf = null;
	
	boolean isAdmin=false;
	if(session.getAttribute("memid")!=null){
		memid = (String)session.getAttribute("memid");			
	}
	
	if(session.getAttribute("loginid")!=null){
		loginid = (String)session.getAttribute("loginid");			
	}
	if(session.getAttribute("username")!=null){
		username = (String)session.getAttribute("username");			
	}
	
	if(session.getAttribute("user")!=null){
		memGeninf = (MemGeninf)session.getAttribute("user");			
	}
	if("administrator".equals(loginid)){
		isAdmin = true;
	}
	if(memGeninf.getRolGenInfs()!=null){
		Iterator rolIterator = memGeninf.getRolGenInfs().iterator();
		if(rolIterator.hasNext()){
			RolGeninf rolGeninf=  (RolGeninf)rolIterator.next();
			if(rolGeninf.getDepGeninf()!=null){
				depname = rolGeninf.getDepGeninf().getName();
			}
		}
	}
	

        
%>

<!-- Required Stylesheets -->
<link rel="stylesheet" type="text/css" href="css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/text.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/fonts/ptsans/stylesheet.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/fluid.css" media="screen" />

<link rel="stylesheet" type="text/css" href="css/mws.style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/icons/icons.css" media="screen" />

<!-- Demo and Plugin Stylesheets -->
<link rel="stylesheet" type="text/css" href="css/demo.css" media="screen" />

<link rel="stylesheet" type="text/css" href="plugins/colorpicker/colorpicker.css" media="screen" />
<link rel="stylesheet" type="text/css" href="plugins/jimgareaselect/css/imgareaselect-default.css" media="screen" />
<link rel="stylesheet" type="text/css" href="plugins/fullcalendar/fullcalendar.css" media="screen" />
<link rel="stylesheet" type="text/css" href="plugins/fullcalendar/fullcalendar.print.css" media="print" />
<link rel="stylesheet" type="text/css" href="plugins/tipsy/tipsy.css" media="screen" />
<link rel="stylesheet" type="text/css" href="plugins/sourcerer/Sourcerer-1.2.css" media="screen" />
<link rel="stylesheet" type="text/css" href="plugins/jgrowl/jquery.jgrowl.css" media="screen" />
<link rel="stylesheet" type="text/css" href="plugins/spinner/spinner.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jui/jquery.ui.css" media="screen" />

<!-- Theme Stylesheet -->
<link rel="stylesheet" type="text/css" href="css/mws.theme.css" media="screen" />

<!-- JavaScript Plugins -->

<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>

<script type="text/javascript" src="plugins/jimgareaselect/jquery.imgareaselect.min.js"></script>
<script type="text/javascript" src="plugins/jquery.dualListBox-1.3.min.js"></script>
<script type="text/javascript" src="plugins/jgrowl/jquery.jgrowl.js"></script>
<script type="text/javascript" src="plugins/jquery.filestyle.js"></script>
<script type="text/javascript" src="plugins/fullcalendar/fullcalendar.min.js"></script>
<script type="text/javascript" src="plugins/jquery.dataTables.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="plugins/flot/excanvas.min.js"></script>
<![endif]-->
<script type="text/javascript" src="plugins/flot/jquery.flot.min.js"></script>
<script type="text/javascript" src="plugins/flot/jquery.flot.pie.min.js"></script>
<script type="text/javascript" src="plugins/flot/jquery.flot.stack.min.js"></script>
<script type="text/javascript" src="plugins/flot/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="plugins/colorpicker/colorpicker.js"></script>
<script type="text/javascript" src="plugins/tipsy/jquery.tipsy.js"></script>
<script type="text/javascript" src="plugins/sourcerer/Sourcerer-1.2.js"></script>
<script type="text/javascript" src="plugins/jquery.placeholder.js"></script>
<script type="text/javascript" src="plugins/jquery.validate.js"></script>
<script type="text/javascript" src="plugins/jquery.mousewheel.js"></script>
<script type="text/javascript" src="plugins/spinner/ui.spinner.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>

<script type="text/javascript" src="js/mws.js"></script>
<script type="text/javascript" src="js/demo.js"></script>
<script type="text/javascript" src="js/themer.js"></script>
<script type="text/JavaScript" src="My97DatePicker/WdatePicker.js"></script> 
<!-- <script type="text/javascript" src="js/demo.dashboard.js"></script> --->
<link rel="stylesheet" type="text/css"
	href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script> 


<title>狮山行政服务中心一门式排队叫号统计</title>

<style type="text/css"> 
.big_text{
	font-size:14px;
	
}
.spantemp{
	font-size:10px;
	
}
body {
  font-family: "Avenir", "Helvetica Neue", Helvetica, Arial, sans-serif;
 
}
</style> 

</head>
	<div id="dlgfoodsamplinglist" class="easyui-dialog"
		data-options="title:'test',iconCls:'icon-save'"
		style="width:1000px;height:500px;padding:0px;display:none;top:40px;overflow:hidden;"
		closed="true">
		<iframe scrolling="auto" id='dlgfoodsamplinglistframe' frameborder="0"
			src="" style="width:100%;height:100%;"></iframe>

	</div>
<body style="position:fixed;width:100%" >
	<!-- Header Wrapper -->

        
        	<!-- Main Container -->
			<!-- 统计信息 -->
			<br/>
            <div class="container" width="100%">
				<div class="mws-panel grid_8" style="width:100%">
                	<div class="mws-panel-header">
                    	<span id="btdiv" class="mws-i-24 i-sign-post">整体信息
						&nbsp; &nbsp; &nbsp;起始时间
			    <input id="startTime" onChange="onChooseYear()"  style="width:70px" type="text"/>
                <img onclick="WdatePicker({el:'startTime'})" src="My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                    	&nbsp; &nbsp; &nbsp;结束时间 	
			    <input id="endTime" onChange="onChooseYear()"  style="width:70px" type="text"/>
                <img onclick="WdatePicker({el:'endTime'})" src="My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                    	&nbsp; &nbsp; &nbsp;所属区域 	
                    	<select class="selectorArea" onChange="onChooseArea()">
                    	</select>
                    	&nbsp; &nbsp; &nbsp;抽检单位 	
                    	<select class="selectorDanwei" onChange="onChooseYear()">
										
                    	</select>
						&nbsp; &nbsp; &nbsp;

						</span>
                    </div>
                    <div class="mws-panel-body">
				<div class="mws-report-container clearfix" style="padding-top:15px">
                	<a class="mws-report" href="#">
                    	<span class="mws-report-icon mws-ic ic-calendar-view-day"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title big_text" >今天检测：</span>
                            <span class="mws-report-value" id="today">0</span>
                        </span>
                    </a>

                	<a class="mws-report" href="#">
                    	<span class="mws-report-icon mws-ic ic-calendar-view-month"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title big_text">本月检测：</span>
                            <span class="mws-report-value" id="thisMonth">0</span>
                        </span>
                    </a>

                	<a class="mws-report" href="#">
                    	<span class="mws-report-icon mws-ic ic-calendar-view-week"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title big_text">全年合计：</span>
                            <span class="mws-report-value" id="thisYear">0</span>
                        </span>
                    </a>
                    
                	<a class="mws-report" href="#">
                    	<span class="mws-report-icon mws-ic ic-chart-pie"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title big_text">完成率：</span>
                            <span class="mws-report-value" id="finish" >0%</span>
                        </span>
                    </a>
                    <a class="mws-report" href="#">
                    	<span class="mws-report-icon mws-ic ic-exclamation"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title big_text">合格率：</span>
                            <span class="mws-report-value" id="unqualified" >0%</span>
                        </span>
                    </a>

                </div>
					</div>
				</div>
            	
                
            	<div class="mws-panel grid_5" style="width:100%;">
                	<div class="mws-panel-header">
                    	<span class="mws-i-24 i-graph">抽样信息统计 
                    	</span>
                    	       
                    </div>
                    <div class="mws-panel-body">
                    	<div class="mws-panel-content" style="padding:0px">
	                    	<div id="chart1" style="width:100%;"></div>
                        </div>
                    </div>
                </div>
                

                
                
            </div>
            <!-- End Main Container -->
            <!-- Footer -->

            <!-- End Footer -->
            


<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>

<script type="text/javascript" src="../easyui/FusionCharts_XT_Evaluation/Charts/FusionCharts.js"></script>
<script type="text/javascript" src="../easyui/FusionCharts_XT_Evaluation/Gallery/assets/prettify/prettify.js"></script>
<script type="text/javascript" src="../easyui/FusionCharts_XT_Evaluation/Gallery/assets/ui/js/json2.js"></script>
<script type="text/javascript" src="../easyui/FusionCharts_XT_Evaluation/Gallery/assets/ui/js/lib.js"></script>
<!--[if IE 6]>
        <script type="text/javascript" src="easyui/FusionCharts_XT_Evaluation/Gallery/assets/ui/js/DD_belatedPNG_0.0.8a-min.js"></script>
        <script>
          /* select the element name, css selector, background etc */
          DD_belatedPNG.fix('img');

          /* string argument can be any CSS selector */
        </script>
		  <p>&nbsp;</p>
		  <P align="center"></P>
        <![endif]-->



<script  type="text/javascript" >
var changeCount=0;
/*var dataString ='{"chart": {"caption": "Airline Delay Causes","showpercentvalues": "1","showlegend": "1","showlabels": "0","showvalues": "1","showpercentageinlabel": "1"},"data": [{"value":"14.94","label": "Weather","color": "429EAD"},{"value": "19.17","label": "Volume","color": "4249AD"},{"value": "7.14","label": "Closed Runway","color": "AD42A2"},{ "value": "7.75","label":"Others","color": "D4AC31"}]}';*/
//图形1
	//图形3
function getChart(dataString) {
		if  ( FusionCharts( "chartId_flash" ) )  FusionCharts( "chartId_flash" ).dispose();
		var jsondata ='{"chart": {"bgcolor": "406181, 6DA5DB","bgalpha": "100","basefontcolor": "FFFFFF","baseFontSize":"14","canvasbgalpha": "0","canvasbordercolor": "FFFFFF","divlinecolor": "#FF1493","divlinealpha": "100","numvdivlines": "10","vdivlineisdashed": "1","showalternatevgridcolor": "1","linecolor": "BBDA00","labelDisplay":"WRAP","anchorradius": "4","anchorbgcolor": "BBDA00","anchorbordercolor": "FFFFFF","anchorborderthickness": "2","showvalues":"1","tooltipbgcolor": "406181","tooltipbordercolor": "406181","alternatehgridalpha": "5","unescapeLinks":"0","howalternatevgridcolor":"1"},"data": '+dataString+'}';
		var d = document.getElementById("chart1");
		var width = d.offsetWidth ;
		var height = d.offsetHeight ;
		var chart = new FusionCharts("../easyui/FusionCharts_XT_Evaluation/Charts/Column3D.swf","chartId_flash",
				width, height, "0", "0");
		chart.setJSONData( jsondata );
		
		chart.render("chart1");
	}

//异步读取数据
function ajaxXml(year1,month1,area1,danwei){
	//获得基本情况
	  $.ajax({   
          type:"POST",  
         url:"../report!getTimeTotalJson.action",  
         data:{
             year:year1,
             area:area1,
             month:month1,
             type:danwei
         },
           dataType:"text",  
          success:function(result){   //function1()
			///alert(datastr);
			getChart(result);
	
        } , 
         failure:function (result) {   
            alert('Failed');   
         } 
  }); 
    $.ajax({   
          type:"POST",  
         url:"../report!getUserTimeTestJson",  
         data:{
             year:year1,
             area:area1,
             month:month1,
             type:danwei
         },
           dataType:"text",  
          success:function(result){
          	///alert(result);
          	eval("var list = "+result);
            document.getElementById("today").innerText=list[0].value;
			document.getElementById("thisMonth").innerText=list[1].value;
			document.getElementById("thisYear").innerText=list[2].value;
			var str  = ((parseInt(list[5].value))/(parseInt(list[4].value))).toFixed(2)*100;
			str = parseInt(str);
			if((isNaN(str))||(list[4].value==0)){
			str = "0%";
			document.getElementById("unqualified").innerText=str;
			}else{
			str = str + "%";
			document.getElementById("unqualified").innerText=str;
			} 
			var str1  = ((parseInt(list[4].value))/(parseInt(list[3].value))).toFixed(2)*100;
			str1 = parseInt(str1);
			if((isNaN(str1))||(list[3].value==0)){
			str1 = "0%";
			document.getElementById("finish").innerText=str1;
			}else{
			str1 = str1 + "%";
			document.getElementById("finish").innerText=str1;
			}  
        } , 
         failure:function (result) {   
            alert('Failed');   
         } 
  });
}
 
	$(function() {
		 $("#mws-jui-dialog").hide(); 
	     $("#mws-jui-dialog1").hide(); 
		 $("#mws-jui-dialog2").hide(); 

		if("<%=depname%>"=="三水区食品药品监督管理局"){
		$(".selectorArea").append("<option value='三水区食品药品监督管理局' selected>三水区管理局</option>");
        $(".selectorArea").append("<option value='三水区食品药品监督管理局西南分局'>三水区西南分局</option>");
        $(".selectorArea").append("<option value='三水区食品药品监督管理局白坭分局'>三水区白坭分局</option>");
        $(".selectorArea").append("<option value='三水区食品药品监督管理局芦苞分局'>三水区芦苞分局</option>");
        $(".selectorArea").append("<option value='三水区食品药品监督管理局乐平分局'>三水区乐平分局</option>");
		$(".selectorArea").append("<option value='三水区食品药品监督管理局大塘分局'>三水区大塘分局</option>");
		$(".selectorArea").append("<option value='三水区食品药品监督管理局南山分局'>三水区南山分局</option>");
		$(".selectorArea").append("<option value='三水区食品药品监督管理局云东海分局'>三水区云东海分局</option>");
		$(".selectorArea").append("<option value='市场方'>市场方</option>");
		$(".selectorArea").append("<option value='学校食堂'>学校食堂</option>");
		$(".selectorArea").append("<option value='超市'>超市</option>");
		$(".selectorArea").append("<option value='区局快检室'>区局快检室</option>");
		$(".selectorArea").append("<option value='区局快检车'>区局快检车</option>");
		var html ="<button onClick='getExcel()'>导出Excel</button>";
        document.getElementById("btdiv").innerHTML+=html;
		}else if("<%=depname%>"=="三水区食品药品监督管理局西南分局"){
			$(".selectorArea").append("<option value='三水区食品药品监督管理局西南分局'>三水区西南分局</option>");
			$(".selectorArea").append("<option value='市场方'>市场方</option>");
		}else if("<%=depname%>"=="三水区食品药品监督管理局白坭分局"){
			$(".selectorArea").append("<option value='三水区食品药品监督管理局白坭分局'>三水区西南分局三水区白坭分局</option>");
			$(".selectorArea").append("<option value='市场方'>市场方</option>");
		}else if("<%=depname%>"=="三水区食品药品监督管理局芦苞分局"){
			$(".selectorArea").append("<option value='三水区食品药品监督管理局芦苞分局'>三水区芦苞分局</option>");
			$(".selectorArea").append("<option value='市场方'>市场方</option>");
		}else if("<%=depname%>"=="三水区食品药品监督管理局乐平分局"){
			$(".selectorArea").append("<option value='三水区食品药品监督管理局乐平分局'>三水区乐平分局</option>");
			$(".selectorArea").append("<option value='市场方'>市场方</option>");
		}else if("<%=depname%>"=="三水区食品药品监督管理局大塘分局"){
			$(".selectorArea").append("<option value='三水区食品药品监督管理局大塘分局'>三水区大塘分局</option>");
			$(".selectorArea").append("<option value='市场方'>市场方</option>");
		}else if("<%=depname%>"=="三水区食品药品监督管理局南山分局"){
			$(".selectorArea").append("<option value='三水区食品药品监督管理局南山分局'>三水区南山分局</option>");
			$(".selectorArea").append("<option value='市场方'>市场方</option>");
		}else if("<%=depname%>"=="三水区食品药品监督管理局云东海分局"){
			$(".selectorArea").append("<option value='三水区食品药品监督管理局云东海分局'>三水区云东海分局</option>");
			$(".selectorArea").append("<option value='市场方'>市场方</option>");
		}else if("<%=depname%>"=="市场方"){
			$(".selectorArea").append("<option value='市场方'>市场方</option>");
		}else if("<%=depname%>"=="学校食堂"){
			$(".selectorArea").append("<option value='学校食堂'>学校食堂</option>");
		}else if("<%=depname%>"=="超市"){
			$(".selectorArea").append("<option value='超市'>超市</option>");
		}else if("<%=depname%>"=="区局快检室"){
			$(".selectorArea").append("<option value='区局快检室'>区局快检室</option>");
		}else if("<%=depname%>"=="区局快检车"){
			$(".selectorArea").append("<option value='区局快检车'>区局快检车</option>");
		}
		
		var myDate = new Date();
		var nowtime = myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
		document.getElementById('startTime').value = nowtime;
		document.getElementById('endTime').value = getNewDay(nowtime,1);
		onChooseArea();
		var year = nowtime;
        var month = getNewDay(nowtime,1);
        var area = $(".selectorArea").val();
        var danwei = $(".selectorDanwei").val();
		ajaxXml(year,month,area,danwei);
		var tempheight= document.body.clientHeight- 300;
		var tempwidth= document.body.clientWidth-200;
		var tempwidth1= tempwidth*0.6;

		$("#chart1").height(tempwidth1);
		 $("#chart1").height(tempheight);
	});
function onChooseArea(){
	  var area = $(".selectorArea").val();
	  var year = document.getElementById('startTime').value;
      var month = document.getElementById('endTime').value;
      $(".selectorDanwei").html("");
	  $.ajax({   
          type:"POST",  
         url:"../SsMarketInfo!getMarket.action",  
         data:{
             samId:area
         },
           dataType:"text",  
          success:function(result){   //function1()
			///alert(datastr);
			eval("var list="+result+";");
            for(var i=0;i<list.length;i++){
                $(".selectorDanwei").append("<option value='"+list[i].name+"' >"+list[i].name+"</option>");
	        }
            $(".selectorDanwei").val(list[0].name); 
            var danwei = $(".selectorDanwei").val();
		    ajaxXml(year,month,area,danwei);
        } , 
         failure:function (result) {   
            alert('Failed');   
         } 
      }); 
}	

function onChooseYear(){
		var year = document.getElementById('startTime').value;
        var month = document.getElementById('endTime').value;
        var danwei = $(".selectorDanwei").val();
		var area = $(".selectorArea").val();
		ajaxXml(year,month,area,danwei);
}
String.prototype.replaceAll = function(s1,s2){ 
return this.replace(new RegExp(s1,"gm"),s2); 
}
	//当前时间加天数
function getNewDay(dateTemp,days) {  
    var dateTemp = dateTemp.split("-");  
    var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]); //转换为MM-DD-YYYY格式    
    var millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);  
    var rDate = new Date(millSeconds);  
    var year = rDate.getFullYear();  
    var month = rDate.getMonth() + 1;  
    if (month < 10) month = "0" + month;  
    var date = rDate.getDate();  
    if (date < 10) date = "0" + date;  
    return (year + "-" + month + "-" + date);  
}  
function getExcel(){
	var year = document.getElementById('startTime').value;
    var month = document.getElementById('endTime').value;
    window.open("../report!demoExcel?year="+year+"&month="+month+"&area=time");
} 
//弹出完成值列表
function showlist1(){
	        var str = '';
			var year = document.getElementById('startTime').value;
			var month = document.getElementById('endTime').value;
	        var area = $(".selectorArea").val();
            var danwei = $(".selectorDanwei").val();
			var str1="time";
			$('#dlgfoodsamplinglistframe')[0].src = '../VFoodSamplingCount.action?samId='+str+'&year='+year+'&FlagStr='+str1+'&depart='+encodeURI(encodeURI(area))+'&username='+encodeURI(encodeURI(danwei))+'&month='+month;
			$('#dlgfoodsamplinglist').css('display', 'block');

			$('#dlgfoodsamplinglist').dialog({
				resizable : true,
				draggable : true,
				maximizable : true,
				collapsible : true,
				minimizable : false,
				maximized : true,
				shadow : true,
				window : true,
				modal:true,
				title : '食品安全快速检测抽样信息'
			}).window('open');
}

//弹出列表
function showlist3(value){
	        var str = '';
			if(value=="果蔬"){
				str='guoshu';
			}else if(value=="其他食品"){
				str='qitashipin';
			}else if(value=="肉类"){
				str='roulei';
			}else if(value=="市民送检"){
				str='shiminsongjian';
			}else if(value=="日常快检"){
				str='richangsongjian';
			}else if(value=="专项快检"){
				str='zhuanxiangkuaijian';
			}else if(value=="不合格"){
				str='buhege';
			}else if(value=="合格"){
				str='hege';
			}
		    var year = document.getElementById('startTime').value;
			var month = document.getElementById('endTime').value;
			var str1="time";
			var area = $(".selectorArea").val();
            var danwei = $(".selectorDanwei").val();
			$('#dlgfoodsamplinglistframe')[0].src = '../VFoodSamplingCount.action?samId='+str+'&year='+year+'&FlagStr='+str1+'&depart='+encodeURI(encodeURI(area))+'&username='+encodeURI(encodeURI(danwei))+'&month='+month;
			$('#dlgfoodsamplinglist').css('display', 'block');
			$('#dlgfoodsamplinglist').dialog({
				resizable : true,
				draggable : true,
				maximizable : true,
				collapsible : true,
				minimizable : false,
				maximized : true,
				shadow : true,
				window : true,
				modal:true,
				title : '食品安全快速检测抽样信息'
			}).window('open');
}


/*
function change(){
	var areaStr = "ss;bn;xn;lb;lp;dt;ns;ydh";
	var area = areaStr.split(";");
	if(check_second<30){
		changeCount=changeCount+1;
		var temp =changeCount%8;
		changeArea(area[temp]);
	}
	
}
*/
///setInterval("change();",20000);
	</script>
</body>
</html>
