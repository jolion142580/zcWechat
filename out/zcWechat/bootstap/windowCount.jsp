<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%

java.util.Calendar a=java.util.Calendar.getInstance();
int year = a.get(java.util.Calendar.YEAR);

%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />




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

<!-- <script type="text/javascript" src="js/demo.dashboard.js"></script> --->



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

<body>

	<!-- Themer -->  
	<!--
	<div id="mws-themer">
    	<div id="mws-themer-hide"></div>
        <div id="mws-themer-content">
        	<div class="mws-themer-section">
	        	<label for="mws-theme-presets">Presets</label> <select id="mws-theme-presets"></select>
            </div>
            <div class="mws-themer-separator"></div>
            <div class="mws-themer-section">
                <ul>
                    <li><span>Base Color</span> <div id="mws-base-cp" class="mws-cp-trigger"></div></li>
                    <li><span>Text Color</span> <div id="mws-text-cp" class="mws-cp-trigger"></div></li>
                    <li><span>Text Glow Color</span> <div id="mws-textglow-cp" class="mws-cp-trigger"></div></li>
                </ul>
            </div>
            <div class="mws-themer-separator"></div>
            <div class="mws-themer-section">
            	<ul>
                    <li><span>Text Glow Opacity</span> <div id="mws-textglow-op"></div></li>
                </ul>
            </div>
            <div class="mws-themer-separator"></div>
            <div class="mws-themer-section">
	            <button class="mws-button red small" id="mws-themer-getcss">Get CSS</button>
            </div>
        </div>
        <div id="mws-themer-css-dialog">
        	<div class="mws-form">
            	<div class="mws-form-row" style="padding:0;">
		        	<div class="mws-form-item">
                    	<textarea cols="auto" rows="auto" readonly="readonly"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
	-->
    <!-- Themer End -->
    

	<!-- Header Wrapper -->

    <%@include  file="header.jsp" %>
    <!-- Main Wrapper -->
    <div id="mws-wrapper">
    	<!-- Necessary markup, do not remove -->
		<div id="mws-sidebar-stitch"></div>
		<div id="mws-sidebar-bg"></div>
		<%@include  file="menu.jsp" %>
        
        
        
        <!-- Container Wrapper -->
        <div id="mws-container" class="clearfix">
        
        	<!-- Main Container -->
			<!-- 统计信息 -->
            <div class="container" >
				<div class="mws-panel grid_8">
                	<div class="mws-panel-header">
                    	<span class="mws-i-24 i-sign-post">行政区域</span>
                    </div>
                    <div class="mws-panel-body">
                        <div class="mws-wizard clearfix">
                            <ul>
                                <li  class="current" id="ss">
                                    <a class="mws-ic-16 ic-chart-bar"  onClick="changeArea('ss')">狮山行政服务中心</a>
                                </li>
                                <li  id="xt">
                                    <a href="#" class=" mws-ic-16 ic-chart-bar" onClick="changeArea('xt')">小塘行政服务中心</a>
                                </li>
								<li id="sg">
                                    <a href="#" class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('sg')">松岗行政服务中心</a>
                                </li>
								<li id="gy">
                                    <a href="#" class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('gy')">官窑行政服务中心</a>
                                </li>
								<li id="dp">
                                    <a href="#" class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('dp')">大圃行政服务中心</a>
                                </li>
								<li id="lc">
                                    <a href="#" class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('lc')">罗村行政服务中心</a>
                                </li>
                               
                            </ul>
                        </div>
						<form class="mws-form" >
						<div class="mws-form-inline">
                    			<div class="mws-form-row" >
                    				<label>数据统计年份</label>
                    				<div class="mws-form-item large">
                    					<select class="selector" onChange="onChooseYear()">
											<option value="<%=year-4%>"><%=year-4%></option>
                    						<option value="<%=year-3%>"><%=year-3%></option>
                    						<option value="<%=year-2%>"><%=year-2%></option>
                    						<option value="<%=year-1%>"><%=year-1%></option>
                    						<option value="<%=year%>" selected><%=year%></option>
                    					</select>
                    				</div>
									<label>数据统计月份</label>
                    				<div class="mws-form-item large">
                    					<select class="selectormonth" onChange="onChooseYear()">
											<option value="1" selected>1</option>
                    						<option value="2">2</option>
                    						<option value="3">3</option>
                    						<option value="4">4</option>
                    						<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
											<option value="8">8</option>
											<option value="9">9</option>
											<option value="10">10</option>
											<option value="11">11</option>
											<option value="11">12</option>
                    					</select>
                    				</div>
                    			</div>
						</div>
						</form>
						
					
					</div>
				</div>
            	
                
            	<div class="mws-panel grid_8">
                	<div class="mws-panel-header">
                    	<span class="mws-i-24 i-graph">窗口效能统计</span>
                    </div>
                    <div class="mws-panel-body">
                    	<div class="mws-panel-content" style="padding:0px">
	                    	<div id="chart1" style="width:100%; height:400px;"></div>
                        </div>
                    </div>
                </div>
                
 
                
				
                
            </div>
            <!-- End Main Container -->
            
            <!-- Footer -->
            <div id="mws-footer">
            	软件版本：南邮英科一门式数据展示平台V2.0
            </div>
            <!-- End Footer -->
            
        </div>
        <!-- End Container Wrapper -->
        
    </div>
    <!-- End Main Wrapper -->

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>

<script type="text/javascript" src="easyui/FusionCharts_XT_Evaluation/Charts/FusionCharts.js"></script>
<script type="text/javascript" src="easyui/FusionCharts_XT_Evaluation/Gallery/assets/prettify/prettify.js"></script>
<script type="text/javascript" src="easyui/FusionCharts_XT_Evaluation/Gallery/assets/ui/js/json2.js"></script>
<script type="text/javascript" src="easyui/FusionCharts_XT_Evaluation/Gallery/assets/ui/js/lib.js"></script>
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

/*var dataString ='{"chart": {"caption": "Airline Delay Causes","showpercentvalues": "1","showlegend": "1","showlabels": "0","showvalues": "1","showpercentageinlabel": "1"},"data": [{"value":"14.94","label": "Weather","color": "429EAD"},{"value": "19.17","label": "Volume","color": "4249AD"},{"value": "7.14","label": "Closed Runway","color": "AD42A2"},{ "value": "7.75","label":"Others","color": "D4AC31"}]}';*/
//图形1
function getChart1(dataString) {
		if  ( FusionCharts( "chartId" ) )  FusionCharts( "chartId" ).dispose();
		var d = document.getElementById("chart1");
		var width = d.offsetWidth ;
		var height = d.offsetHeight ;
		var chart = new FusionCharts("easyui/FusionCharts_XT_Evaluation/Charts/MSColumn2D.swf","chartId",width, height, "0", "0");
		chart.setJSONData( dataString );
		///chart.setDataURL(url);
		 chart.render("chart1");
	}

	
//异步读取数据
function ajaxXml(id,year,month){
	//获得狮山情况
	
  
  $.ajax({   
          type:"POST",  
         url:"QueueHist!getWindowEfficientJson.action?year="+year+"&address="+id+"&month="+month,  
           dataType:"text",  
          success:function(result){   //function1()
          	
			///alert(datastr);
			getChart1(result);
			///getChart2(datastr);
        } , 
         failure:function (result) {   
            alert('Failed');   
         } 
  });  
   
}
	$(function() {
		var year = $(".selector").val();
		var id = $(".current").attr("id");
		var month = $(".selectormonth").val();
		///alert(year);
		///alert($(".current").attr("id"));
		ajaxXml(id,year,month);
		///alert("aa");
	});
	
function changeArea(id){
	///alert(id);
	$("li").removeClass("current");
	$("#"+id).addClass("current");
	var year = $(".selector").val();
	var month = $(".selectormonth").val();
	ajaxXml(id,year,month);
}

function onChooseYear(){
	var id = $(".current").attr("id");
	var year = $(".selector").val();
	var month = $(".selectormonth").val();
	//alert(year);
	//alert(id);
	ajaxXml(id,year,month);
}

String.prototype.replaceAll = function(s1,s2){ 
return this.replace(new RegExp(s1,"gm"),s2); 
}

	</script>
</body>
</html>
