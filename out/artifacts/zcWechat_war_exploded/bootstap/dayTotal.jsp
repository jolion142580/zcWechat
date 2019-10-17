<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<%

java.util.Calendar a=java.util.Calendar.getInstance();
int year = a.get(java.util.Calendar.YEAR);
int month = a.get(java.util.Calendar.MONTH);
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

<!-- <script type="text/javascript" src="js/demo.dashboard.js"></script> --->


<title>三水区抽检平台</title>

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
@-webkit-keyframes  twinkling{    /*透明度由0到1*/
    0%{
       opacity:0; /*透明度为0*/
     }
    100%{
       opacity:1; /*透明度为1*/
    }
  }
@-webkit-keyframes  bgyellow{0%{background:rgba(255,255,0,0.18);}
25%{background:rgba(255,255,0,0.5);}
50%{background:rgba(255,255,0,0.1);}
75%{background:rgba(255,255,0,0.3);}
100%{background:rgba(255,255,0,0.18);}
} 
@-webkit-keyframes bgred{0%{background:rgba(255,255,0,0.18);}
25%{background:rgba(255,0,0,0.5);}
50%{background:rgba(255,0,0,0.1);}
75%{background:rgba(255,0,0,0.3);}
100%{background:rgba(255,0,0,0.18);}
} 
@-webkit-keyframes bgwhite{
from {background:white;}
to {background:white;}
}
} 
@-webkit-keyframes heart_beat{0%{-webkit-transform:scale(1)}
25%{-webkit-transform:scale(1.70)}
50%{-webkit-transform:scale(0.9)}
75%{-webkit-transform:scale(1.55)}
100%{-webkit-transform:scale(1)}}


@-ms-keyframes  twinkling{    /*透明度由0到1*/
    0%{
       opacity:0; /*透明度为0*/
     }
    100%{
       opacity:1; /*透明度为1*/
    }
  }
@-ms-keyframes  bgyellow{0%{background:rgba(255,255,0,0.18);}
25%{background:rgba(255,255,0,0.5);}
50%{background:rgba(255,255,0,0.1);}
75%{background:rgba(255,255,0,0.3);}
100%{background:rgba(255,255,0,0.18);}
} 
@-ms-keyframes bgred{0%{background:rgba(255,255,0,0.18);}
25%{background:rgba(255,0,0,0.5);}
50%{background:rgba(255,0,0,0.1);}
75%{background:rgba(255,0,0,0.3);}
100%{background:rgba(255,0,0,0.18);}
} 
@-ms-keyframes bgwhite{
from {background:white;}
to {background:white;}
}
} 
@-ms-keyframes heart_beat{0%{-webkit-transform:scale(1)}
25%{-webkit-transform:scale(1.70)}
50%{-webkit-transform:scale(0.9)}
75%{-webkit-transform:scale(1.55)}
100%{-webkit-transform:scale(1)}}
</style> 

</head>

<body style="position:fixed;width:100%" >
	<!-- Header Wrapper -->

        
        	<!-- Main Container -->
			<!-- 统计信息 -->
			<br/>
            <div class="container" width="100%">
				<div class="mws-panel grid_8">
                	<div class="mws-panel-header">
                    	<span class="mws-i-24 i-sign-post">整体信息
						&nbsp; &nbsp; &nbsp;统计年份
                    	<select class="selector" onChange="onChooseYear()">
											<option value="<%=year-4%>"><%=year-4%></option>
                    						<option value="<%=year-3%>"><%=year-3%></option>
                    						<option value="<%=year-2%>"><%=year-2%></option>
                    						<option value="<%=year-1%>"><%=year-1%></option>
                    						<option value="<%=year%>" selected><%=year%></option>
                    	</select>
                    	&nbsp; &nbsp; &nbsp;统计月份 	
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
						</span>
                    </div>
                    <div class="mws-panel-body">
                        <div class="mws-wizard clearfix">
                            <ul>
							    <li  class="current" id="ss">
                                    <a class="mws-ic-16 ic-chart-bar"  onClick="changeArea('ss')">三水区市场监管局</a>
                                </li> 
                                <li  id="xn">
                                    <a class=" mws-ic-16 ic-chart-bar" onClick="changeArea('xn')">西南</a>
                                </li>
                                <li  id="bn">
                                    <a class="mws-ic-16 ic-chart-bar"  onClick="changeArea('bn')">白坭</a>
                                </li>
								<li id="lb">
                                    <a  class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('lb')">芦苞</a>
                                </li>
								<li id="lp">
                                    <a class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('lp')">乐平</a>
                                </li>
								<li id="dt">
                                    <a class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('dt')">大塘</a>
                                </li>
								<li id="ns">
                                    <a class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('ns')">南山</a>
                                </li>
                               <li id="ydh">
                                    <a class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('ydh')">云东海</a>
                                </li>
								<li id="qt">
                                    <a class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('qt')">其他</a>
                                </li>
                            </ul>
                        </div>
				<div class="mws-report-container clearfix" style="padding-top:15px">
                	<a class="mws-report" href="#" onClick="showDayList()">
                    	<span class="mws-report-icon mws-ic ic-calendar-view-day"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title big_text" >今天检测：</span>
                            <span class="mws-report-value" id="today">0</span>
                        </span>
                    </a>

                	<a class="mws-report" href="#" onClick="showMonthList()">
                    	<span class="mws-report-icon mws-ic ic-calendar-view-month"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title big_text">本月检测：</span>
                            <span class="mws-report-value" id="thisMonth">0</span>
                        </span>
                    </a>
			        <a class="mws-report" href="#" >
                    	<span class="mws-report-icon mws-ic ic-calendar-view-week"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title big_text">本月任务量：</span>
                            <span class="mws-report-value" id="thisMonthMission">0</span>
                        </span>
                    </a>
                    

                	<a class="mws-report" href="#" onClick="showYearList()">
                    	<span class="mws-report-icon mws-ic ic-date-magnify"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title big_text">全年合计：</span>
                            <span class="mws-report-value" id="thisYear">0</span>
                        </span>
                    </a>
			
                	<a class="mws-report" href="#">
                    	<span class="mws-report-icon mws-ic ic-chart-pie"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title big_text" >本月完成率：</span>
                            <span class="mws-report-value" id="finish" >0%</span>
                        </span>
                    </a>
                    <a class="mws-report" href="#" onClick="showDayFailList()" id="todayfailcss" style="background:#FFFFFF;">
                    	<span class="mws-report-icon mws-ic ic-exclamation"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title big_text" style="color:#000000;">本日不合格率：</span>
                            <span class="mws-report-value" id="unqualified" style="color:#000000;">0%</span>
                        </span>
                    </a>

                </div>
					</div>
				</div>
            	
                
            	<div class="mws-panel grid_5">
                	<div class="mws-panel-header">
                    	<span class="mws-i-24 i-graph">区域月份被抽样信息统计 
                    	</span>
                    	       
                    </div>
                    <div class="mws-panel-body">
                    	<div class="mws-panel-content" style="padding:0px">
	                    	<div id="chart1" style="width:100%; height:0px;"></div>
                        </div>
                    </div>
                </div>
                
            	<div class="mws-panel grid_3">
                	<div class="mws-panel-header">
                    	<span class="mws-i-24 i-books-2">区域整体月份合格率</span>
                    </div>
                    <div class="mws-panel-body" style="font-size:10px">
                        <div class="mws-panel-content" style="padding:0px">
	                    	<div id="chart2" style="width:100%; height:0px;"></div>
                        </div>
                    </div>
                </div>
                
                
            </div>
            <!-- End Main Container -->
            <div id="mws-jui-dialog" class="mws-i-24 i-graph" >
                <div id="chart_more" style="width:100%; height:370px;"></div>
            </div>
			<div id="mws-jui-dialog1" class="mws-i-24 i-graph" >
			                    <select class="selectortype"  onChange="onChooseType()">
											<option value="commodity" selected>一类产品</option>
                    						<option value="minClass">二类产品</option>
                    						<option value="smallClass">三类产品</option>
                    						<option value="product">四类产品</option>

                    			</select>
								<input type="hidden"  id="shichang" >
                <div id="chart_more1" style="width:100%; height:370px;"></div>
            </div>
			<div id="mws-jui-dialog2"  class="mws-i-24 i-graph" >

			                    <select class="selectortype2" onChange="onChooseType2()">
											<option value="commodity" selected>一类产品</option>
                    						<option value="minClass">二类产品</option>
                    						<option value="smallClass">三类产品</option>
                    						<option value="product">四类产品</option>

                    			</select>
								<input type="hidden"  id="shichang2" >
                <div id="chart_more2" style="width:100%; height:370px;"></div>
            </div>
			<div id="mws-jui-dialog3"  class="modal-dialog" style="padding:0px">
            <iframe scrolling="auto" id='dlgfoodsamplinglistframe' frameborder="0"
			src="" style="width:100%;height:100%;"></iframe>
            </div>
			<div id="mws-jui-dialog4"  class="modal-dialog" style="padding:0px">
            <iframe scrolling="auto" id='dlgfailframe' frameborder="0"
			src="" style="width:100%;height:100%;"></iframe>
            </div>
			<div id="mws-jui-dialog5"  class="modal-dialog" style="padding:0px">
            <iframe scrolling="auto" id='dlgyearframe' frameborder="0"
			src="" style="width:100%;height:100%;"></iframe>
            </div>
			<div id="mws-jui-dialog6"  class="modal-dialog" style="padding:0px">
            <iframe scrolling="auto" id='dlgmonthframe' frameborder="0"
			src="" style="width:100%;height:100%;"></iframe>
            </div>
			<div id="mws-jui-dialog7"  class="modal-dialog" style="padding:0px">
            <iframe scrolling="auto" id='dlgdayframe' frameborder="0"
			src="" style="width:100%;height:100%;"></iframe>
            </div>
			<div id="mws-jui-dialog8"  class="modal-dialog" style="padding:0px">
            <iframe scrolling="auto" id='dlgdayfailframe' frameborder="0"
			src="" style="width:100%;height:100%;"></iframe>
            </div>
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
var  strAll="";
var  strtype1="";
var  strAll1="";
var  strtype2="";
function getChart1(dataString) {
		 eval("var ja="+dataString+";");
		 ///alert(ja);
		 //分组名称
		 var labels ='';
		 //抽样数
		 var value1 = '';
		 //不合格
		 var fvalue = '';
		 //任务数
		 var nvalue = '';
		 //实际抽样合计
		 var sumvalue1 = 0;
		 
		 //实际抽样合计
		 var sumfvalue = 0;
		if  ( FusionCharts( "chartId_flash" ) )  FusionCharts( "chartId_flash" ).dispose();
		///var jsondata ='{"chart": {"showpercentvalues": "1","showlegend": "1","showlabels": "1","showvalues": "1","showpercentageinlabel": "1"},"data": '+dataString+'}';
		for(var i=0;i<ja.length;i++){
			 var tempname = ja[i].name;
			 var tempvalue1 = ja[i].num;
			 var tempfvalue = ja[i].fnum;
			 var tempnvalue = ja[i].nnum;
			 var linkstr = "'"+ja[i].name+"'";
			 var link ='javascript:myJS('+linkstr+')';
			 var links ='javascript:myJSs('+linkstr+')';
			 var linkw ='javascript:myJSw('+linkstr+')';
			 labels = labels +' {"label": "'+tempname+'"}';
			 sumvalue1 = sumvalue1 + parseInt(tempvalue1);
			 sumfvalue = sumfvalue + parseInt(tempfvalue);
			 value1= value1 +' {"value": "'+tempvalue1+'","link":"'+links+'"}';
			 fvalue= fvalue +' {"value": "'+tempfvalue+'","link":"'+linkw+'"}';
			 nvalue= nvalue +' {"value": "'+tempnvalue+'","link":"'+link+'"}';
			 if(i==ja.length-1){
			 
			 }else{
			 	labels=labels+",";
			 	value1=value1+",";
			 	fvalue=fvalue+",";
			 	nvalue=nvalue+",";
			 }
		}
		labels ='['+labels +']';
		value1 ='['+value1 +']';
		fvalue ='['+fvalue +']';
		nvalue ='['+nvalue +']';
		var jsondata = '{ "chart": {   "useroundedges": "1",   "caption": "抽检数量", "formatNumberScale":"0",   "legendborderalpha": "0",    "basefontsize": "12",    "basefont": "Arial",    "numdivlines": "4",    "animation": "1",    "rotatenames": "0",    "palette": "2","unescapeLinks":"0"},"categories": [    {      "fontsize": "12",      "font": "Arial",      "category": '+labels+'    }  ],  "dataset": [    {      "showvalues": "0",      "alpha": "90",      "color": "66FF33",      "seriesname": "完成量",      "data": '+value1+'    },    {      "showvalues": "0",      "alpha": "90",      "color": "FF3366",      "seriesname": "不合格量",      "data": '+fvalue+'    } ,    {      "showvalues": "0",      "alpha": "90",      "color": "3366FF",      "seriesname": "目标量",      "data": '+nvalue+'    } ],  "trendlines": [    {      "line": [        {          "alpha": "20",          "color": "8BBA00",          "istrendzone": "1",            "showontop": "0",          "thickness": "1"     }      ]    }  ],  "styles": {    "definition": [      {        "name": "Anim1",        "duration": "1",        "start": "0",        "param": "_xScale",        "type": "animation"      },      {        "name": "Anim2",        "duration": "1",        "start": "0",        "param": "_alpha",        "type": "animation"      },      {        "name": "myCaptionFont",        "type": "font",        "align": "left"      }    ],    "application": [      {        "styles": "Anim1, Anim2",        "toobject": "TRENDLINES"      },      {        "styles": "myCaptionFont",        "toobject": "Caption"      }    ]  }}';
		var d = document.getElementById("chart1");
		var width = d.offsetWidth ;
		var height = d.offsetHeight ;
		///if (GALLERY_RENDERER && GALLERY_RENDERER.search(/javascript|flash/i)==0)  FusionCharts.setCurrentRenderer(GALLERY_RENDERER);  
		var chart = new FusionCharts("../easyui/FusionCharts_XT_Evaluation/Charts/MSColumn2D.swf","chartId_flash",width, height, "0", "0");
		chart.setJSONData( jsondata );
		///chart.setDataURL(url);
		 chart.render("chart1");
		 
		 getChart2(sumfvalue,sumvalue1)
	}
//图形2
function getChart2(fail,all) {
		if  ( FusionCharts( "chartId1_flash" ) )  FusionCharts( "chartId1_flash" ).dispose();
		///if  ( FusionCharts( "ChartId" ) )  FusionCharts( "ChartId" ).dispose();
		var jsondata ='{  "chart": {       "animation": "1","formatNumberScale":"0",    "showvalues": "1",     "showlabels": "1",    "startingangle": "200",     "basefontsize": "11",    "basefontcolor": "2F2F2F",   "bordercolor": "C6D2DF",   "bgalpha": "70",   "bgcolor": "ECF5FF",   "chartrightmargin": "40",  "unescapeLinks":"0"  },  "data": [    {      "value": "'+(all-fail)+'",   "color": "00ACE8",   "label": "合格量"   },    {      "value": "'+fail+'",  "color": "C295F2",    "label": "不合格量"   }     ],"styles": {"definition": [{"name": "Font_0","type": "font","font": "Calibri","size": "14","bold": "1","ishtml": "0"},{"name": "Font_1","type": "font","size": "15","color": "000080","ishtml": "0"}],"application": [{"toobject": "DATALABELS","styles": "Font_0"},{"toobject": "CAPTION","styles": "Font_1"}]}}';
	
		var d = document.getElementById("chart2");
		var width = d.offsetWidth ;
		var height = d.offsetHeight ;
		////if (GALLERY_RENDERER && GALLERY_RENDERER.search(/javascript|flash/i)==0)  FusionCharts.setCurrentRenderer(GALLERY_RENDERER);  
		var chart = new FusionCharts("../easyui/FusionCharts_XT_Evaluation/Charts/Doughnut2D.swf","chartId1_flash",
				width, height, "0", "0");
		chart.setJSONData( jsondata );
		
		chart.render("chart2");
	}
	//图形3
function getChart3(dataString) {
		if  ( FusionCharts( "chartId3_flash" ) )  FusionCharts( "chartId3_flash" ).dispose();
		var jsondata ='{"chart": {"bgcolor": "406181, 6DA5DB","formatNumberScale":"0","bgalpha": "100","basefontcolor": "FFFFFF","baseFontSize":"14","canvasbgalpha": "0","canvasbordercolor": "FFFFFF","divlinecolor": "#FF1493","divlinealpha": "100","numvdivlines": "10","vdivlineisdashed": "1","showalternatevgridcolor": "1","linecolor": "BBDA00","anchorradius": "4","anchorbgcolor": "BBDA00","anchorbordercolor": "FFFFFF","anchorborderthickness": "2","showvalues":"1","tooltipbgcolor": "406181","tooltipbordercolor": "406181","alternatehgridalpha": "5","unescapeLinks":"0","howalternatevgridcolor":"1"},"data": '+dataString+'}';
		var d = document.getElementById("chart_more");
		var width = d.offsetWidth ;
		var height = d.offsetHeight ;
		var chart = new FusionCharts("../easyui/FusionCharts_XT_Evaluation/Charts/Column3D.swf","chartId3_flash",
				width, height, "0", "0");
		chart.setJSONData( jsondata );
		
		chart.render("chart_more");
	}
	//图形4
function getChart4(dataString) {
	    eval("var ja="+dataString+";");
		 var labels ='';
		  var fvalue ='';
		for(var i=0;i<ja.length;i++){
			 var tempname = ja[i].label;
			 var tempfvalue = ja[i].value;
			 var links ="javascript:showFinishList('"+tempname+"')";
			 labels = labels +' {"label": "'+tempname+'"}';
			 fvalue= fvalue +' {"value": "'+tempfvalue+'","link":"'+links+'"}';
			 if(i==ja.length-1){
			 
			 }else{
			 	labels=labels+",";
			 	fvalue=fvalue+",";
			 }
		}
		labels ='['+labels +']';
		fvalue ='['+fvalue +']';
		if  ( FusionCharts( "chartId4_flash" ) )  FusionCharts( "chartId4_flash" ).dispose();
		var jsondata = '{ "chart": {    "bgcolor": "#F0FFF0","bgalpha": "100","formatNumberScale":"0", "numdivlines": "9","showvalues": "1","baseFontSize":"14","showalternatevgridcolor": "1","numvisibleplot": "12","plotgradientcolor": "","plotfillalpha": "30","palette": "4","scrollbtnpadding": "2","scrollheight": "26","scrollbtnwidth": "40","canvaspadding": "25","unescapeLinks":"0"},"categories": [    {      "fontsize": "12",      "font": "Arial",      "category": '+labels+'    }  ],  "dataset": [    {       "showvalues": "1",      "alpha": "90",          "seriesname": "品种抽检数量",      "data": '+fvalue+'    }],  "trendlines": [    {      "line": [        {          "alpha": "20",                  "istrendzone": "1",                   "showontop": "0",          "thickness": "1"        }      ]    }  ],  "styles": {    "definition": [      {        "name": "Anim1",        "duration": "1",        "start": "0",        "param": "_xScale",        "type": "animation"      },      {        "name": "Anim2",        "duration": "1",        "start": "0",        "param": "_alpha",        "type": "animation"      },      {        "name": "myCaptionFont",        "type": "font",        "align": "left"      }    ],    "application": [      {        "styles": "Anim1, Anim2",        "toobject": "TRENDLINES"      },      {        "styles": "myCaptionFont",        "toobject": "Caption"      }    ]  }}';
		var d = document.getElementById("chart_more1");
		var width = d.offsetWidth ;
		var height = d.offsetHeight ;
		var chart = new FusionCharts("../easyui/FusionCharts_XT_Evaluation/Charts/ScrollColumn2D.swf","chartId4_flash",
				width, height, "0", "0");
		chart.setJSONData( jsondata );
		
		chart.render("chart_more1");
	}
		//图形5
function getChart5(dataString) {
	    eval("var ja="+dataString+";");
		 var labels ='';
		  var fvalue ='';
		for(var i=0;i<ja.length;i++){
			 var tempname = ja[i].label;
			 var tempfvalue = ja[i].value;
			 var links ="javascript:showFailList('"+tempname+"')";
			 labels = labels +' {"label": "'+tempname+'"}';
			 fvalue= fvalue +' {"value": "'+tempfvalue+'","link":"'+links+'"}';

			 
			 if(i==ja.length-1){
			 
			 }else{
			 	labels=labels+",";
			 	fvalue=fvalue+",";
			 }
		}
		labels ='['+labels +']';
		fvalue ='['+fvalue +']';
		if  ( FusionCharts( "chartId5_flash" ) )  FusionCharts( "chartId5_flash" ).dispose();
		var jsondata = '{ "chart": {    "bgcolor": "#F0FFF0","bgalpha": "100","formatNumberScale":"0", "numdivlines": "9","showvalues": "1","baseFontSize":"14","showalternatevgridcolor": "1","numvisibleplot": "12","plotgradientcolor": "","plotfillalpha": "30","palette": "4","scrollbtnpadding": "2","scrollheight": "26","scrollbtnwidth": "40","canvaspadding": "25","unescapeLinks":"0"},"categories": [    {      "fontsize": "12",      "font": "Arial",      "category": '+labels+'    }  ],  "dataset": [    {       "showvalues": "1",      "alpha": "90",          "seriesname": "品种抽检数量",      "data": '+fvalue+'    }],  "trendlines": [    {      "line": [        {          "alpha": "20",                  "istrendzone": "1",                   "showontop": "0",          "thickness": "1"      }      ]    }  ],  "styles": {    "definition": [      {        "name": "Anim1",        "duration": "1",        "start": "0",        "param": "_xScale",        "type": "animation"      },      {        "name": "Anim2",        "duration": "1",        "start": "0",        "param": "_alpha",        "type": "animation"      },      {        "name": "myCaptionFont",        "type": "font",        "align": "left"      }    ],    "application": [      {        "styles": "Anim1, Anim2",        "toobject": "TRENDLINES"      },      {        "styles": "myCaptionFont",        "toobject": "Caption"      }    ]  }}';
		var d = document.getElementById("chart_more2");
		var width = d.offsetWidth ;
		var height = d.offsetHeight ;
		var chart = new FusionCharts("../easyui/FusionCharts_XT_Evaluation/Charts/ScrollColumn2D.swf","chartId5_flash",
				width, height, "0", "0");
		chart.setJSONData( jsondata );
		
		chart.render("chart_more2");
	}
	
	//统计三水区
	function getChart6(dataString) {
		 eval("var ja="+dataString+";");
		 ///alert(ja);
		 //分组名称
		 var labels ='';
		 //抽样数
		 var value1 = '';
		 //不合格
		 var fvalue = '';
		 //任务数
		 var nvalue = '';
		 //实际抽样合计
		 var sumvalue1 = 0;
		 
		 //实际抽样合计
		 var sumfvalue = 0;
		if  ( FusionCharts( "chartId_flash" ) )  FusionCharts( "chartId_flash" ).dispose();
		///var jsondata ='{"chart": {"showpercentvalues": "1","showlegend": "1","showlabels": "1","showvalues": "1","showpercentageinlabel": "1"},"data": '+dataString+'}';
		for(var i=0;i<ja.length;i++){
			 var tempname = ja[i].label;
			 var tempvalue1 = ja[i].value1;
			 var tempfvalue = ja[i].value2;
			 var tempnvalue = ja[i].value3;
			 var linkstr = "'"+ja[i].label+"'";
			 var link ='javascript:myJS('+linkstr+')';
			 var links ='javascript:myJSs('+linkstr+')';
			 var linkw ='javascript:myJSw('+linkstr+')';
			 labels = labels +' {"label": "'+tempname+'"}';
			 sumvalue1 = sumvalue1 + parseInt(tempvalue1);
			 sumfvalue = sumfvalue + parseInt(tempfvalue);
			 value1= value1 +' {"value": "'+tempvalue1+'","link":"'+links+'"}';
			 fvalue= fvalue +' {"value": "'+tempfvalue+'","link":"'+linkw+'"}';
			 nvalue= nvalue +' {"value": "'+tempnvalue+'","link":"'+link+'"}';
			 if(i==ja.length-1){
			 
			 }else{
			 	labels=labels+",";
			 	value1=value1+",";
			 	fvalue=fvalue+",";
			 	nvalue=nvalue+",";
			 }
		}
		labels ='['+labels +']';
		value1 ='['+value1 +']';
		fvalue ='['+fvalue +']';
		nvalue ='['+nvalue +']';
		var jsondata = '{ "chart": {   "useroundedges": "1",   "caption": "抽检数量",    "legendborderalpha": "0", "formatNumberScale":"0",   "basefontsize": "12",    "basefont": "Arial",    "numdivlines": "4",    "animation": "1",    "rotatenames": "0",    "palette": "2","unescapeLinks":"0"},"categories": [    {      "fontsize": "12",      "font": "Arial",      "category": '+labels+'    }  ],  "dataset": [    {      "showvalues": "0",      "alpha": "90",      "color": "66FF33",      "seriesname": "完成量",      "data": '+value1+'    },    {      "showvalues": "0",      "alpha": "90",      "color": "FF3366",      "seriesname": "不合格量",      "data": '+fvalue+'    } ,    {      "showvalues": "0",      "alpha": "90",      "color": "3366FF",      "seriesname": "目标量",      "data": '+nvalue+'    } ],  "trendlines": [    {      "line": [        {          "alpha": "20",          "color": "8BBA00",          "istrendzone": "1",            "showontop": "0",          "thickness": "1"     }      ]    }  ],  "styles": {    "definition": [      {        "name": "Anim1",        "duration": "1",        "start": "0",        "param": "_xScale",        "type": "animation"      },      {        "name": "Anim2",        "duration": "1",        "start": "0",        "param": "_alpha",        "type": "animation"      },      {        "name": "myCaptionFont",        "type": "font",        "align": "left"      }    ],    "application": [      {        "styles": "Anim1, Anim2",        "toobject": "TRENDLINES"      },      {        "styles": "myCaptionFont",        "toobject": "Caption"      }    ]  }}';
		var d = document.getElementById("chart1");
		var width = d.offsetWidth ;
		var height = d.offsetHeight ;
		///if (GALLERY_RENDERER && GALLERY_RENDERER.search(/javascript|flash/i)==0)  FusionCharts.setCurrentRenderer(GALLERY_RENDERER);  
		var chart = new FusionCharts("../easyui/FusionCharts_XT_Evaluation/Charts/MSColumn2D.swf","chartId_flash",width, height, "0", "0");
		chart.setJSONData( jsondata );
		///chart.setDataURL(url);
		 chart.render("chart1");
		 
		 getChart2(sumfvalue,sumvalue1)
	}
	//统计三水区
	function getChart7(dataString) {
		 eval("var ja="+dataString+";");
		 ///alert(ja);
		 //分组名称
		 var labels ='';
		 //抽样数
		 var value1 = '';
		 //不合格
		 var fvalue = '';
		 //任务数
		 var nvalue = '';
		 //实际抽样合计
		 var sumvalue1 = 0;
		 
		 //实际抽样合计
		 var sumfvalue = 0;
		if  ( FusionCharts( "chartId_flash" ) )  FusionCharts( "chartId_flash" ).dispose();
		///var jsondata ='{"chart": {"showpercentvalues": "1","showlegend": "1","showlabels": "1","showvalues": "1","showpercentageinlabel": "1"},"data": '+dataString+'}';
		for(var i=0;i<ja.length;i++){
			 var tempname = ja[i].label;
			 var tempvalue1 = ja[i].value1;
			 var tempfvalue = ja[i].value2;
			 var tempnvalue = ja[i].value3;
			 var linkstr = "'"+ja[i].label+"'";
			 var link ='javascript:myJS('+linkstr+')';
			 var links ='javascript:myJSs('+linkstr+')';
			 var linkw ='javascript:myJSw('+linkstr+')';
			 labels = labels +' {"label": "'+tempname+'"}';
			 sumvalue1 = sumvalue1 + parseInt(tempvalue1);
			 sumfvalue = sumfvalue + parseInt(tempfvalue);
			 value1= value1 +' {"value": "'+tempvalue1+'","link":"'+links+'"}';
			 fvalue= fvalue +' {"value": "'+tempfvalue+'","link":"'+linkw+'"}';
			 nvalue= nvalue +' {"value": "'+tempnvalue+'","link":"'+link+'"}';
			 if(i==ja.length-1){
			 
			 }else{
			 	labels=labels+",";
			 	value1=value1+",";
			 	fvalue=fvalue+",";
			 	nvalue=nvalue+",";
			 }
		}
		labels ='['+labels +']';
		value1 ='['+value1 +']';
		fvalue ='['+fvalue +']';
		nvalue ='['+nvalue +']';
		var jsondata = '{ "chart": {   "useroundedges": "1",   "caption": "抽检数量",    "legendborderalpha": "0",  "formatNumberScale":"0",  "basefontsize": "12",    "basefont": "Arial",    "numdivlines": "4",    "animation": "1",    "rotatenames": "0",    "palette": "2","unescapeLinks":"0"},"categories": [    {      "fontsize": "12",      "font": "Arial",      "category": '+labels+'    }  ],  "dataset": [    {      "showvalues": "0",      "alpha": "90",      "color": "66FF33",      "seriesname": "完成量",      "data": '+value1+'    },    {      "showvalues": "0",      "alpha": "90",      "color": "FF3366",      "seriesname": "不合格量",      "data": '+fvalue+'    } ,    {      "showvalues": "0",      "alpha": "90",      "color": "3366FF",      "seriesname": "目标量",      "data": '+nvalue+'    } ],  "trendlines": [    {      "line": [        {          "alpha": "20",          "color": "8BBA00",          "istrendzone": "1",            "showontop": "0",          "thickness": "1"     }      ]    }  ],  "styles": {    "definition": [      {        "name": "Anim1",        "duration": "1",        "start": "0",        "param": "_xScale",        "type": "animation"      },      {        "name": "Anim2",        "duration": "1",        "start": "0",        "param": "_alpha",        "type": "animation"      },      {        "name": "myCaptionFont",        "type": "font",        "align": "left"      }    ],    "application": [      {        "styles": "Anim1, Anim2",        "toobject": "TRENDLINES"      },      {        "styles": "myCaptionFont",        "toobject": "Caption"      }    ]  }}';
		var d = document.getElementById("chart1");
		var width = d.offsetWidth ;
		var height = d.offsetHeight ;
		///if (GALLERY_RENDERER && GALLERY_RENDERER.search(/javascript|flash/i)==0)  FusionCharts.setCurrentRenderer(GALLERY_RENDERER);  
		var chart = new FusionCharts("../easyui/FusionCharts_XT_Evaluation/Charts/MSColumn2D.swf","chartId_flash",width, height, "0", "0");
		chart.setJSONData( jsondata );
		///chart.setDataURL(url);
		 chart.render("chart1");
		 
		 getChart2(sumfvalue,sumvalue1)
	}
//异步读取数据
function ajaxXml(id,year,month){
	//获得基本情况
  if(id=="ss"){
	  $.ajax({   
          type:"POST",  
         url:"../report!getQuTotal",  
         data:{
         	year:year,
         	month:month
         },
           dataType:"text",  
          success:function(result){
          	///alert(result);
          	
          	getChart6(result);
          	
        } , 
         failure:function (result) {   
            alert('Failed');   
         } 
  }); 
  }
  else if(id=="qt"){
	 $.ajax({   
         type:"POST",  
         url:"../report!getQiTaTotal",  
         data:{
         	year:year,
         	month:month
         },
           dataType:"text",  
          success:function(result){
          	///alert(result);
          	
          	getChart7(result);
          	
        } , 
         failure:function (result) {   
            alert('Failed');   
         } 
  });  
  }
  else{
  $.ajax({   
          type:"POST",  
         url:"../report!getAreaMonthCountJson",  
         data:{
         	year:year,
         	month:month,
         	area:id
         },
           dataType:"text",  
          success:function(result){
          	///alert(result);
          	
          	getChart1(result);
          	
        } , 
         failure:function (result) {   
            alert('Failed');   
         } 
  });  
  }
  if(id=="qt"){
	  $.ajax({   
          type:"POST",  
         url:"../report!getQiTaTest",  
         data:{
         	area:id,
			year:year,
         	month:month
         },
           dataType:"text",  
          success:function(result){
          	///alert(result);
          	eval("var list = "+result);
            document.getElementById("today").innerText=list[0].value1;
			document.getElementById("thisMonth").innerText=list[0].value2;
			document.getElementById("thisYear").innerText=list[0].value4;
			if((isNaN(list[0].value3))||(list[0].value3==0)){
				document.getElementById("thisMonthMission").innerText=0;
			}else{
			    document.getElementById("thisMonthMission").innerText=list[0].value3;
			}
			var str  = ((parseInt(list[0].value5))/(parseInt(list[0].value1))).toFixed(2)*100;
			str = parseInt(str);
			$("#todayfailcss").css({"-ms-animation":"bgwhite 2s infinite ease-in-out"});
			$("#todayfailcss").css({"-webkit-animation":"bgwhite 2s infinite ease-in-out"});
			if((isNaN(str))||(list[0].value1==0)){
			str = "0%";
			document.getElementById("unqualified").innerText=str;
			}else{
			if((str>=3)&&(str<=5)){
				$("#todayfailcss").css({"-ms-animation":"bgyellow 2s infinite ease-in-out"});
				$("#todayfailcss").css({"-webkit-animation":"bgyellow 2s infinite ease-in-out"});
			}else if(str>5){
				$("#todayfailcss").css({"-ms-animation":"bgred 1s infinite ease-in-out"});
				$("#todayfailcss").css({"-webkit-animation":"bgred 1s infinite ease-in-out"});
			}
			str = str + "%";
			document.getElementById("unqualified").innerText=str;
			} 
			var str1  = ((parseInt(list[0].value2))/(parseInt(list[0].value3))).toFixed(2)*100;
			str1 = parseInt(str1);
			if((isNaN(str1))||(list[0].value3==0)){
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
  }else{
    $.ajax({   
          type:"POST",  
         url:"../report!getTodayTest",  
         data:{
         	area:id,
			year:year,
         	month:month
         },
           dataType:"text",  
          success:function(result){
          	///alert(result);
          	eval("var list = "+result);
            document.getElementById("today").innerText=list[0].value;
			document.getElementById("thisMonth").innerText=list[1].value;
			document.getElementById("thisYear").innerText=list[2].value;
			if((isNaN(list[5].value))||(list[5].value==0)){
				document.getElementById("thisMonthMission").innerText=0;
			}else{
			    document.getElementById("thisMonthMission").innerText=list[5].value;
			}
			var str  = ((parseInt(list[3].value))/(parseInt(list[4].value))).toFixed(2)*100;
			str = parseInt(str);
			$("#todayfailcss").css({"-ms-animation":"bgwhite 2s infinite ease-in-out"});
			$("#todayfailcss").css({"-webkit-animation":"bgwhite 2s infinite ease-in-out"});
			if((isNaN(str))||(list[4].value==0)){
			str = "0%";
			document.getElementById("unqualified").innerText=str;
			}else{
			if((str>=3)&&(str<=5)){
				$("#todayfailcss").css({"-ms-animation":"bgyellow 2s infinite ease-in-out"});
				$("#todayfailcss").css({"-webkit-animation":"bgyellow 2s infinite ease-in-out"});
			}else if(str>5){
				$("#todayfailcss").css({"-ms-animation":"bgred 1s infinite ease-in-out"});
				$("#todayfailcss").css({"-webkit-animation":"bgred 1s infinite ease-in-out"});
			}
			str = str + "%";
			document.getElementById("unqualified").innerText=str;
			} 
			var str1  = ((parseInt(list[6].value))/(parseInt(list[5].value))).toFixed(2)*100;
			str1 = parseInt(str1);
			if((isNaN(str1))||(list[5].value==0)){
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
}
 
	$(function() {
		 $("#mws-jui-dialog").hide(); 
	     $("#mws-jui-dialog1").hide(); 
		 $("#mws-jui-dialog2").hide(); 

        $(".selectormonth").val("<%=month+1%>");
		var id = $(".current").attr("id");
		var year = $(".selector").val();
		var month = $(".selectormonth").val();
		ajaxXml(id,year,month);
		var tempheight= document.body.clientHeight- 350;
		var tempwidth= document.body.clientWidth-200;
		var tempwidth1= tempwidth*0.6;
		var tempwidth2= tempwidth*0.4;
		$("#chart1").height(tempwidth1);
		 $("#chart2").height(tempwidth2);
		 $("#chart1").height(tempheight);
		 $("#chart2").height(tempheight);	
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
function onChooseType(){
	var str = document.getElementById("shichang").value;
	var yearstr = $(".selector").val();
	var monthstr = $(".selectormonth").val();
	var type = $(".selectortype").val();
	strtype1=type;
	ajaxCommodityXml1(str,yearstr,monthstr,type);
}
function onChooseType2(){
	var str = document.getElementById("shichang2").value;
	var yearstr = $(".selector").val();
	var monthstr = $(".selectormonth").val();
	var type = $(".selectortype2").val();
	strtype2=type;
	ajaxCommodityXml2(str,yearstr,monthstr,type);
}
String.prototype.replaceAll = function(s1,s2){ 
return this.replace(new RegExp(s1,"gm"),s2); 
}
//弹出图3
function myJS(value){
	var strname=value;
	var yearstr = $(".selector").val();
	var monthstr = $(".selectormonth").val();
	//value = encodeURI(encodeURI(value));
     ajaxCommodityXml(value,yearstr,monthstr);
	///
	$("#mws-jui-dialog").dialog({
		autoOpen: false, 
		title: monthstr+"月份"+strname+"各品种任务量", 
		modal: true, 
		width: 800, 
		buttons: [{
				text: "关闭", 
				click: function() {
					$( this ).dialog( "close" );
				}}]
	});
	
	$("#mws-jui-dialog").dialog("option", {modal: true}).dialog("open");
	
}
//弹出图4
function myJSs(value){
	var strname=value;
	document.getElementById("shichang").value=value;
	var yearstr = $(".selector").val();
	var monthstr = $(".selectormonth").val();
	var type = $(".selectortype").val();
	//value = encodeURI(encodeURI(value));
     ajaxCommodityXml1(value,yearstr,monthstr,type);
	///
	$("#mws-jui-dialog1").dialog({
		autoOpen: false, 
		title: monthstr+"月份"+strname+"各品种完成量", 
		modal: true, 
		width: 800, 
		buttons: [{
				text: "关闭", 
				click: function() {
					$( this ).dialog( "close" );
				}}]
	});
	
	$("#mws-jui-dialog1").dialog("option", {modal: true}).dialog("open");
	
}
//弹出图5
function myJSw(value){
	var strname=value;
	document.getElementById("shichang2").value=value;
	var yearstr = $(".selector").val();
	var monthstr = $(".selectormonth").val();
	var type = $(".selectortype2").val();
	//value = encodeURI(encodeURI(value));
     ajaxCommodityXml2(value,yearstr,monthstr,type);
	///
	$("#mws-jui-dialog2").dialog({
		autoOpen: false, 
		title: monthstr+"月份"+strname+"各品种任务不合格量", 
		modal: true, 
		width: 800, 
		buttons: [{
				text: "关闭", 
				click: function() {
					$( this ).dialog( "close" );
				}}]
	});
	
	$("#mws-jui-dialog2").dialog("option", {modal: true}).dialog("open");
	
}
function ajaxCommodityXml(value1,year,month){
	var id = $(".current").attr("id");
	var str="";
	if(id=='ss'){
		str = id;
	}else if(id=='qt'){
		str = id;
	}
  $.ajax({   
          type:"POST",  
         url:"../report!getCommodity.action?area="+encodeURI(encodeURI(value1))+"&year="+year+"&month="+month+"&day="+str,  
           dataType:"text",  
          success:function(result){   //function1()
			///alert(datastr);
			getChart3(result);
	
        } , 
         failure:function (result) {   
            alert('Failed');   
         } 
  });   
}
function ajaxCommodityXml1(value1,year,month,type){
	var id = $(".current").attr("id");
	var str="";
	if(id=='ss'){
		str = id;
	}else if(id=='qt'){
		str = id;
	}
	strAll=value1;
	strtype1=type;
    $.ajax({   
          type:"POST",  
         url:"../report!getCommodityFinish.action?area="+encodeURI(encodeURI(value1))+"&year="+year+"&month="+month+"&type="+type+"&day="+str,  
           dataType:"text",  
          success:function(result){   //function1()
			///alert(datastr);
			getChart4(result);
	
        } , 
         failure:function (result) {   
            alert('Failed');   
         } 
  });   
}
function ajaxCommodityXml2(value1,year,month,type){
	var id = $(".current").attr("id");
	var str="";
	if(id=='ss'){
		str = id;
	}else if(id=='qt'){
		str = id;
	}
	strAll1=value1;
	strtype2=type;
  $.ajax({   
          type:"POST",  
         url:"../report!getCommodityFail.action?area="+encodeURI(encodeURI(value1))+"&year="+year+"&month="+month+"&type="+type+"&day="+str,  
           dataType:"text",  
          success:function(result){   //function1()
			///alert(datastr);
			getChart5(result);
	
        } , 
         failure:function (result) {   
            alert('Failed');   
         } 
  });   
}
//弹出完成值列表
function showFinishList(value){
			var id = $(".current").attr("id");
			var strname1=strAll;      
			var year = $(".selector").val();
			var month = $(".selectormonth").val();
			var str = strtype1+'finish';
			var str1="month";
			$('#dlgfoodsamplinglistframe')[0].src = '../VFoodSamplingCount.action?samId='+str+'&year='+year+'&FlagStr='+str1+'&month='+month+'&username='+encodeURI(encodeURI(value))+'&depart='+encodeURI(encodeURI(strname1));
			$("#mws-jui-dialog3").dialog({
					autoOpen: true, 
					title:month+"月份"+strname1+value+"完成量列表",
					modal: true, 
					width: "100%",
					height:550
	});
	
	$("#mws-jui-dialog3").dialog("option", {modal: true}).dialog("open");
}
//弹出不合格列表
function showFailList(value){
			var id = $(".current").attr("id");
			var strname2=strAll1;      
			var year = $(".selector").val();
			var month = $(".selectormonth").val();
			var str = strtype2+'fail';
			var str1="month";
			$('#dlgfailframe')[0].src = '../VFoodSamplingCount.action?samId='+str+'&year='+year+'&FlagStr='+str1+'&month='+month+'&username='+encodeURI(encodeURI(value))+'&depart='+encodeURI(encodeURI(strname2));
			$("#mws-jui-dialog4").dialog({
					autoOpen: true, 
					title:month+"月份"+strname2+value+"不合格列表",
					modal: true, 
					width: "100%",
					height:550
	});
	
	$("#mws-jui-dialog4").dialog("option", {modal: true}).dialog("open");
}
//弹出年抽检列表
function showYearList(){
			var myDate = new Date();
			var depart = getStrname();   
			var year = $(".selector").val();
			var month = '';
			var str = 'finish';
			var value = '';
			var str1="year";
			var name = depart;
			if(name==''){
				name='三水区';
			}
			$('#dlgyearframe')[0].src = '../VFoodSamplingCount.action?samId='+str+'&year='+year+'&FlagStr='+str1+'&month='+month+'&username='+encodeURI(encodeURI(value))+'&depart='+encodeURI(encodeURI(depart));
			$("#mws-jui-dialog5").dialog({
					autoOpen: true, 
					title:year+"年"+name+"完成列表",
					modal: true, 
					width: "100%",
					height:550
	});
	
	$("#mws-jui-dialog5").dialog("option", {modal: true}).dialog("open");
}
//弹出月抽检列表
function showMonthList(){
	  		var myDate = new Date();
			var depart = getStrname();   
			var year = $(".selector").val();
			var month = $(".selectormonth").val();
			var str = 'finish';
			var value = '';
			var str1="month";
			var name = depart;
			if(name==''){
				name='三水区';
			}
			$('#dlgmonthframe')[0].src = '../VFoodSamplingCount.action?samId='+str+'&year='+year+'&FlagStr='+str1+'&month='+month+'&username='+encodeURI(encodeURI(value))+'&depart='+encodeURI(encodeURI(depart));
			$("#mws-jui-dialog6").dialog({
					autoOpen: true, 
					title:year+"年"+month+"月份"+name+"完成列表",
					modal: true, 
					width: "100%",
					height:550
	});
	
	$("#mws-jui-dialog6").dialog("option", {modal: true}).dialog("open");
}
//弹出今日抽检列表
function showDayList(){
		  	var myDate = new Date();
			var nowtime = myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
			var depart = getStrname();   
			var year = nowtime;
			var month = getNewDay(nowtime,1);;
			var str = 'finish';
			var value = '';
			var str1="time";
			var name = depart;
			if(name==''){
				name='三水区';
			}
			$('#dlgdayframe')[0].src = '../VFoodSamplingCount.action?samId='+str+'&year='+year+'&FlagStr='+str1+'&month='+month+'&username='+encodeURI(encodeURI(value))+'&depart='+encodeURI(encodeURI(depart));
			$("#mws-jui-dialog7").dialog({
					autoOpen: true, 
					title:nowtime+"完成列表",
					modal: true, 
					width: "100%",
					height:550
	});
	
	$("#mws-jui-dialog7").dialog("option", {modal: true}).dialog("open");
}
//弹出今日抽检不合格列表
function showDayFailList(){
		  	var myDate = new Date();
			var nowtime = myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
			var depart = getStrname();   
			var year = nowtime;
			var month = getNewDay(nowtime,1);;
			var str = 'finishbuhege';
			var value = '';
			var str1="time";
			var name = depart;
			if(name==''){
				name='三水区';
			}
			$('#dlgdayfailframe')[0].src = '../VFoodSamplingCount.action?samId='+str+'&year='+year+'&FlagStr='+str1+'&month='+month+'&username='+encodeURI(encodeURI(value))+'&depart='+encodeURI(encodeURI(depart));
			$("#mws-jui-dialog8").dialog({
					autoOpen: true, 
					title:nowtime+"完成列表",
					modal: true, 
					width: "100%",
					height:550
	});
	
	$("#mws-jui-dialog8").dialog("option", {modal: true}).dialog("open");
}
function getStrname(){
			var id = $(".current").attr("id");
			var yearstrname="";
			if(id=="ss"){
				yearstrname="";
			}else if(id=="xn"){
				yearstrname="西南";
			}else if(id=="bn"){
				yearstrname="白坭";
			}else if(id=="lb"){
				yearstrname="芦苞";
			}else if(id=="lp"){
				yearstrname="乐平";
			}else if(id=="dt"){
				yearstrname="大塘";
			}else if(id=="ns"){
				yearstrname="南山";
			}else if(id=="ydh"){
				yearstrname="云东海";
			}else if(id=="qt"){
				yearstrname="区局快检";
			}
			return yearstrname;
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
