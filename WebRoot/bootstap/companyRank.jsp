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
<script type="text/JavaScript" src="My97DatePicker/WdatePicker.js"></script> 
<!-- <script type="text/javascript" src="js/demo.dashboard.js"></script> --->
<link rel="stylesheet" type="text/css"
	href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script> 
<!-- <script type="text/javascript" src="js/demo.dashboard.js"></script> --->


<title>经营户排名统计</title>

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
				<div class="mws-panel grid_8" id="selectArea">
                	<div class="mws-panel-header">
                    	<span class="mws-i-24 i-sign-post">整体信息
						&nbsp; &nbsp; &nbsp;起始时间
			    <input id="startTime" onChange="onChooseYear()"  style="width:70px" type="text"/>
                <img onclick="WdatePicker({el:'startTime'})" src="My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                    	&nbsp; &nbsp; &nbsp;结束时间 	
			    <input id="endTime" onChange="onChooseYear()"  style="width:70px" type="text"/>
                <img onclick="WdatePicker({el:'endTime'})" src="My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
						</span>
                    </div>
                    <div class="mws-panel-body">
                        <div class="mws-wizard clearfix">
                            <ul id="scope">
							    <li  class="current" id="三水区市场监管局">
                                    <a class="mws-ic-16 ic-chart-bar"  onClick="changeArea('三水区市场监管局')">三水区市场监管局</a>
                                </li> 
								<li  id="白坭">
                                    <a class="mws-ic-16 ic-chart-bar"  onClick="changeArea('白坭')">白坭</a>
                                </li>
                                <li  id="西南">
                                    <a class=" mws-ic-16 ic-chart-bar" onClick="changeArea('西南')">西南</a>
                                </li>
								<li id="芦苞">
                                    <a  class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('芦苞')">芦苞</a>
                                </li>
								<li id="乐平">
                                    <a class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('乐平')">乐平</a>
                                </li>
								<li id="大塘">
                                    <a class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('大塘')">大塘</a>
                                </li>
								<li id="南山">
                                    <a class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('南山')">南山</a>
                                </li>
                               <li id="云东海">
                                    <a class=" mws-ic-16 ic-chart-bar"  onClick="changeArea('云东海')">云东海</a>
                                </li>
                            </ul>
                        </div>
				        <div class="mws-wizard clearfix">
                            <ul id="areaName">

                            </ul>
                        </div>
					</div>
				</div>
            	
                
            	<div class="mws-panel grid_8">
                	<div class="mws-panel-header">
                    	<span class="mws-i-24 i-graph">经营户被抽检次数排名信息统计 
                    	</span>
                    	       
                    </div>
                    <div class="mws-panel-body">
                    	<div class="mws-panel-content" style="padding:0px">
	                    	<div id="chart1" style="width:100%; height:0px;"></div>
                        </div>
                    </div>
                </div>
                
                
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
function getChart(dataString) {
	    eval("var ja="+dataString+";");
		 var labels ='';
		  var fvalue ='';
		for(var i=0;i<ja.length;i++){
			 var tempname = ja[i].label;
			 var tempfvalue = ja[i].value;
			 labels = labels +' {"label": "'+tempname+'"}';
			 fvalue= fvalue +' {"value": "'+tempfvalue+'"}';	 
			 if(i==ja.length-1){		 
			 }else{
			 	labels=labels+",";
			 	fvalue=fvalue+",";
			 }
		}
		labels ='['+labels +']';
		fvalue ='['+fvalue +']';
		if  ( FusionCharts( "chartId5_flash" ) )  FusionCharts( "chartId5_flash" ).dispose();
		var jsondata = '{ "chart": {    "bgcolor": "#F0FFF0","bgalpha": "100", "numdivlines": "9","showvalues": "1","baseFontSize":"14","showalternatevgridcolor": "1","numvisibleplot": "12","plotgradientcolor": "","plotfillalpha": "30","palette": "4","scrollbtnpadding": "2","scrollheight": "26","scrollbtnwidth": "40","canvaspadding": "25","unescapeLinks":"0"},"categories": [    {      "fontsize": "12",      "font": "Arial",      "category": '+labels+'    }  ],  "dataset": [    {       "showvalues": "1",      "alpha": "90",          "seriesname": "品种抽检数量",      "data": '+fvalue+'    }],  "trendlines": [    {      "line": [        {          "alpha": "20",                  "istrendzone": "1",                   "showontop": "0",          "thickness": "1"      }      ]    }  ],  "styles": {    "definition": [      {        "name": "Anim1",        "duration": "1",        "start": "0",        "param": "_xScale",        "type": "animation"      },      {        "name": "Anim2",        "duration": "1",        "start": "0",        "param": "_alpha",        "type": "animation"      },      {        "name": "myCaptionFont",        "type": "font",        "align": "left"      }    ],    "application": [      {        "styles": "Anim1, Anim2",        "toobject": "TRENDLINES"      },      {        "styles": "myCaptionFont",        "toobject": "Caption"      }    ]  }}';
		var d = document.getElementById("chart1");
		var width = d.offsetWidth ;
		var height = d.offsetHeight ;
		var chart = new FusionCharts("../easyui/FusionCharts_XT_Evaluation/Charts/ScrollColumn2D.swf","chartId5_flash",
				width, height, "0", "0");
		chart.setJSONData( jsondata );
		
		chart.render("chart1");
	}
 
	$(function(){
		var str='';
		str = '<li id='+"西南镇街"+' class="current"><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"西南镇街"+"'"+')">'+"西南镇街"+'</a></li>'
		+'<li id='+"白坭镇街"+'><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"白坭镇街"+"'"+')">'+"白坭镇街"+'</a></li>'
		+'<li id='+"芦苞镇街"+'><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"芦苞镇街"+"'"+')">'+"芦苞镇街"+'</a></li>'
		+'<li id='+"乐平镇街"+'><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"乐平镇街"+"'"+')">'+"乐平镇街"+'</a></li>'
		+'<li id='+"大塘镇街"+'><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"大塘镇街"+"'"+')">'+"大塘镇街"+'</a></li>'
		+'<li id='+"南山镇街"+'><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"南山镇街"+"'"+')">'+"南山镇街"+'</a></li>'
		+'<li id='+"云东海镇街"+'><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"云东海镇街"+"'"+')">'+"云东海镇街"+'</a></li>';
	    addElementLi("areaName",str); 
		var divheight = document.getElementById("selectArea").offsetHeight;
		var tempheight= document.body.clientHeight-130- divheight;
		$("#chart1").height(tempheight);   
		var id = $("#scope .current").attr("id");
		var ids = $("#areaName .current").attr("id");
		var myDate = new Date();
		var nowtime = myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-01";
		var year = document.getElementById('startTime').value = nowtime;
		var month = document.getElementById('endTime').value = myDate.getFullYear()+"-"+(myDate.getMonth()+2)+"-01";
        ajaxXml(year,month,ids,id);	
	});
	function ajaxXml(year,month,market,scope){
	var str="";
    $.ajax({   
          type:"POST",  
         url:"../report!getCompanyRank.action",  
		          data:{
         	year:year,
         	month:month,
			type:market,
			area:scope
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
}
	function changeArea(id){
	///alert(id);
	$("#scope li").removeClass("current");
	$("#"+id).addClass("current");
    if(id=="三水区市场监管局"){
		var str='';
		//var market = "'"+"西南"+"'";
		str = '<li id='+"西南镇街"+' class="current"><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"西南镇街"+"'"+')">'+"西南镇街"+'</a></li>'
		+'<li id='+"白坭镇街"+'><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"白坭镇街"+"'"+')">'+"白坭镇街"+'</a></li>'
		+'<li id='+"芦苞镇街"+'><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"芦苞镇街"+"'"+')">'+"芦苞镇街"+'</a></li>'
		+'<li id='+"乐平镇街"+'><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"乐平镇街"+"'"+')">'+"乐平镇街"+'</a></li>'
		+'<li id='+"大塘镇街"+'><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"大塘镇街"+"'"+')">'+"大塘镇街"+'</a></li>'
		+'<li id='+"南山镇街"+'><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"南山镇街"+"'"+')">'+"南山镇街"+'</a></li>'
		+'<li id='+"云东海镇街"+'><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+"'"+"云东海镇街"+"'"+')">'+"云东海镇街"+'</a></li>';
	    addElementLi("areaName",str); 
	    var id = $("#scope .current").attr("id");
		var ids = $("#areaName .current").attr("id");
	    var year = document.getElementById('startTime').value;
        var month = document.getElementById('endTime').value;	
        ajaxXml(year,month,ids,id);	
		var divheight = document.getElementById("selectArea").offsetHeight;
		var tempheight= document.body.clientHeight-130- divheight;
		$("#chart1").height(tempheight); 
	}
    else{
		 $.ajax({   
         type:"POST",  
         url:"../SsMarketInfo!getCompanyRankMarket.action",  
         data:{
             samId:id
         },
           dataType:"text",  
          success:function(result){   //function1()
			///alert(datastr);
	    	eval("var list="+result+";");
			var str='';
			
            for(var i=0;i<list.length;i++){
				var market = "'"+list[i].name+"'";
				if(i==0){
				str = str + '<li id='+list[i].name+'  class="current"><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+market+')">'+list[i].name+'</a></li>'; 
				}else{
                str = str + '<li id='+list[i].name+'  ><a class=" mws-ic-16 ic-chart-bar"  onClick="changeMarket('+market+')">'+list[i].name+'</a></li>';  
				}
	        }
			  addElementLi("areaName",str); 
			  var id = $("#scope .current").attr("id");
			  var ids = $("#areaName .current").attr("id");
			  var year = document.getElementById('startTime').value;
			  var month = document.getElementById('endTime').value;		
              ajaxXml(year,month,ids,id);	
    //        $(".selectorDanwei").val(list[0].name); 
        var divheight = document.getElementById("selectArea").offsetHeight;
		var tempheight= document.body.clientHeight-130- divheight;
		$("#chart1").height(tempheight);   
		   
        } , 
         failure:function (result) {   
            alert('Failed');   
         } 
      }); 
	}
	}
	function changeMarket(id){
		$("#areaName li").removeClass("current");
	    $("#"+id).addClass("current");
		var id = $("#scope .current").attr("id");
		var ids = $("#areaName .current").attr("id");
	    var year = document.getElementById('startTime').value;
        var month = document.getElementById('endTime').value;	
        ajaxXml(year,month,ids,id);	
	}
　　function addElementLi(obj,context) {
　　　　var ul = document.getElementById(obj);
        ul.innerHTML = context;
　　}
	function onChooseYear(){
		var id = $("#scope .current").attr("id");
		var ids = $("#areaName .current").attr("id");
	    var year = document.getElementById('startTime').value;
        var month = document.getElementById('endTime').value;	
        ajaxXml(year,month,ids,id);	
	}
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

	</script>
</body>
</html>
