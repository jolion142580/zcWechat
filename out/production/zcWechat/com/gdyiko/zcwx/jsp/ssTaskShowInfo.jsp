<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<%
	String contextpath = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ contextpath + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content='<s:property value="modelTitle"/>'>
<meta name='<s:property value="modelTitle"/>'
	content='<s:property value="modelTitle"/>'>
<title><s:property value="modelTitle" />
</title>
<link rel="stylesheet" type="text/css"
	href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="easyui/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script> 
<!--[if lt IE 9]>
    <script src="easyui/json2.js"></script>
<![endif]-->
<style type="text/css">
.txtStyle {
	background-color: #FFFFFF;
	font-size: 10pt;
}

.tdTitle {
	text-align: right;
	color: #1C568A;
	padding-right: 10px;
	background-color: #F1F6FF;
}

.tdEditor {
	text-align: left;
	background-color: #FFFFFF;
	padding-left: 0px;
}
/*tr{
	height:20px;
}*/
</style>
</head>
<body  class="easyui-layout" style="margin:0px">
	<!--查询界面-->

	<div id="toolSeach" style="padding-top:8px;">
	企业名称：<input class="easyui-validatebox easyui-textbox" type="text"  id="mingcheng" name="companyName" size="13" />
	抽检单位：<input class="easyui-validatebox easyui-textbox" type="text"  id="danwei" name="userName" size="13" />
	所属市场：<input class="easyui-validatebox easyui-textbox" type="text"  id="shichang" name="scope" size="13" />
	抽检日期：<input class="easyui-datebox" type="text" id="timestart" dataoptions="editable:false" size="13" />	至 <input class="easyui-datebox" dataoptions="editable:false" type="text" id="timeend" size="13" />
	 <a onclick="searchs()" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
	</div>

   
		<div id="layout2"  class="easyui-layout" data-options="fit:true">  
				 <div data-options="region:'north',split:false,border:false,height:'35px'"> 
					<div id="tt" class="easyui-tabs" style="width:100%;height:32;"></div>   
				</div>
				<div data-options="region:'center',split:false,border:false"> 
				<table id="dg" class="easyui-datagrid">
	            </table>
	            <div id="toolbar"></div>
				</div>
		</div>

    <!-- 新增界面 -->
	<div id="dlg" class="easyui-dialog"
		data-options="title:'test',iconCls:'icon-save'"
		style="width:818px;height:225px;padding:0px;display:none;top:50px"
		closed="true">

		<div class="easyui-layout" data-options="fit:true"
			style="padding:0px;">
			<div data-options="region:'center',border:false"
				style="padding:0px;background:#fff;border:0px solid #ccc;">
				<form id="fm" method="post" style="padding:0px;">
					<table width='800px' height='100%' border='0'
						style="border-color:#DFEAFB;background-color:#DFEAFB;font-size:10pt">
						<tr height='28'>
							<td class='tdTitle'><label>所属抽检范围:</label>
							</td>
							<td class='tdEditor' colspan='3'><input
								class="easyui-validatebox easyui-textbox" type="text" validType="length[0,100]"
								name="areaName"
								data-options="required:false,width:'100%'">
							</td>
							
						</tr>	
						     <tr height='28'>
							<td class='tdTitle'><label>企业名称:</label>
							</td>
							<td class='tdEditor' colspan='1'><input
								class="easyui-textbox" type="text"
								name="companyName" id="companyName" validType="length[0,100]"
								data-options="required:false,width:'100%'">
							</td>
							<td class='tdTitle'><label>企业地址:</label>
							</td>
							<td class='tdEditor' colspan='1'><input
								class="easyui-textbox" type="text" name="address" validType="length[0,20]"
								data-options="required:false,width:'100%'">
							</td>
						</tr>					
						     <tr height='28'>
							<td class='tdTitle'><label>市场:</label>
							</td>
							<td class='tdEditor' colspan='3'><input
								class="easyui-validatebox easyui-combobox" type="text" validType="length[0,100]"
								id='market' name="market"
								data-options="required:false,width:'100%'">
							</td>
						</tr>					
						     <tr height='28'>
							<td class='tdTitle'><label>品种:</label>
							</td>
							<td class='tdEditor' colspan='3'><input
								class="easyui-validatebox easyui-textbox" type="text" validType="length[0,300]"
								name="commodity"
								data-options="required:false,width:'100%'">
							</td>

						</tr>				
						     <tr height='28'>
							<td class='tdTitle'><label>地址:</label>
							</td>
							<td class='tdEditor' colspan='1'><input
								class="easyui-validatebox easyui-textbox" type="text" validType="length[0,50]"
								name="address"
								data-options="required:false,width:'100%'">
							</td>
							<td class='tdTitle'><label>检验批量:</label>
							</td>
							<td class='tdEditor' colspan='1'><input
								class="easyui-textbox" type="text" name="testSum" validType="length[0,20]"
								data-options="required:false,width:'100%'">
							</td>
						</tr>				     				
						<tr height='0px'>
							<th width='160'></th>
							<th width='240'></th>
							<th width='160'></th>
							<th width='240'></th>
						</tr>
					</table>
					<input type="hidden"
						name="id" style='width:100%'>
					<input type="hidden"
						name="creator" id="creator" style='width:100%'>
				</form>

			</div>
		</div>

	</div>

	<script type="text/javascript">

		/*
		 初始化系统
		 */
		 var titleAll = '';//用来保存tab标签的title值
		var url;
		var datagridId = 'dg';
$(function() {
		$('#tt').tabs('add', {
			title : '三水区',
			fit : true,
			closable : false,
			selected : true
		});
		$('#tt').tabs('add', {
			title : '镇街',
			fit : true,
			closable : false,
			selected : false
		});
		$('#tt').tabs('add', {
			title : '市场',
			fit : true,
			closable : false,
			selected : false
		});
		$('#tt').tabs('add', {
			title : '学校食堂超市',
			fit : true,
			closable : false,
			selected : false
		});
			$('#' + datagridId).resizeDataGrid(0, 0, 300, 600);

			$(window).resize(function() {
				$('#' + datagridId).resizeDataGrid(0, 0, 300, 600);

			});
			$('#dg').datagrid({
				title : '任务查询信息',
				iconCls : 'icon-edit',//图标  
				//loadMsg: '',
				width : 'auto',
				height : '600',
				nowrap : false,
				fit:true,
				striped : true,
				border : false,
				collapsible : false,//是否可折叠的  
				url : 'SsTaskSettingInfo!showTaskJsonDatagridListByPage.action', ///'listApp.action'
				idField : 'id',
				singleSelect : true,//是否单选  
				pagination : true,//分页控件  
				rownumbers : true,//行号  
				showFooter : true,
				pageSize : 20,
				//设置特殊行样式
				//rowStyler:function (index,row){
				//alert(row["ID"]+row.ID);

				//	return 'background:#ffffff';
				//},{"title":"收文状态","field":"SWZT","width":"80","align":"center","sortable":true,"resizable":true},
				///		 {"title":"CCCC","field":"BB","width":"80","align":"center","sortable":true,"resizable":true}
				columns : [ <s:property value="columnJson" escape="false" /> ],
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				} ] ],
				toolbar : [{
					text : '修改',
					iconCls : 'icon-edit',
					handler : function() {
						editDBOBJECT();
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						destroyDBOBJECT();
					}
				}]
			});
			$(".datagrid-toolbar").prepend($("#toolSeach"));
			$("#toolSeach").append("<hr size='0.5'>");
			$('#market').combobox({
				url : 'SsMarketInfo!findMarket',
				valueField : 'name',
				textField : 'name',
				editable : true,
				onSelect : function(rec) {
					$('#market').combobox('setValue', rec.name);
				}
			});
        });
			$('#tt').tabs({
					onSelect : function(title, index) {
					$('#dg').datagrid('loadData', {
					total : 0,
					rows : []
					});
						if (title == '三水区') {
						query = {
						userName : title
						};
						} else if (title == '镇街') {
						query = {
						userName : title
						};
						} 
						else if (title == '市场') {
						query = {
						userName : title
						};
						} 
						else if (title == '学校食堂超市') {
						query = {
						userName : title
						};
						} 
						titleAll = title;
					$('#dg').datagrid('options').queryParams = query;
					$('#dg').datagrid('reload');
					}
					});
		$.fn.extend({
		
			resizeDataGrid : function(heightMargin, widthMargin, minHeight,
					minWidth) {
				var height = $(document.body).height() - heightMargin;
				var width = $(document.body).width() - widthMargin;

				height = height < minHeight ? minHeight : height;
				width = width < minWidth ? minWidth : width;

				$(this).datagrid('resize', {
					height : height,
					width : width
				});
			}

		});
			function editDBOBJECT(){
		   $('#dlg').css('display','block');
		   var row = $('#dg').datagrid('getSelected');
		   if(row){
		            $('#dlg').dialog({
		            resizable:true,   //可改变尺寸
		            draggable:true,  //可以拖动的
		            maximizable:true,  //可以最大化
		            collapsible:true, //可折叠的
		            minimizable:false, //不可以最小化
		            maximized:false,
		            shadow:true,  //显示阴影
		            window:true, // 显示窗口
		            title : '任务修改',
		            toolbar:[{
		              text:'保存',
		              iconCls:'icon-edit',
		              handler:function(){
		              saveDBOBJECT();
		              }
		            },{
		            text:'取消',
		            iconCls:'icon-cancel',
		            handler:function(){
		            		   $('#dlg').dialog('close');         	           } }
		            ]  
		            }).window('open');
					
		            $('#fm').form('load',row);
		            url = '<s:property value="modelModifyUrl"/>'+row.id;
		   }
		}
		function destroyDBOBJECT(){ 
		 var row = $('#dg').datagrid('getSelected');
		 if(row){
		            $.messager.confirm('Confirm','是否确认删除这条数据？',function(r){
		            if(r){
		             $.post(
		             '<s:property value="modelDelUrl"/>',
		             {myid:row.id},
	                  function(result) {
							if (result.errorMsg) {
								$.messager.alert('警告', result.errorMsg);
							} else {
								$.messager.alert('提示', "删除成功！");
								//$('#dlg').dialog('close'); // close the dialog
								$('#dg').datagrid('reload'); // reload the DBOBJECT data
							     }
							},'json');}}
		             );
		           }
		    }
        function saveDBOBJECT(){
            		$('#fm').form('submit', {
				url : url,
				onSubmit : function() {
					var flag = $(this).form('validate');
					///alert(flag);
					if (flag) {
						$.messager.progress();
					}else{
					     $.messager.alert("警告","保存失败","info");
					}
					return flag;
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.errorMsg) {
						$.messager.alert('警告', result.errorMsg);
					} else {
						$.messager.alert('提示', "保存成功！");
						$('#dlg').dialog('close'); // close the dialog
						$('#dg').datagrid('reload'); // reload the DBOBJECT data
					}
					$.messager.progress("close");
				}
			});
         }
				//搜索
		function searchs(){
			var query;
			if($("#danwei").val()!=''){
			query={
			companyName:$("#mingcheng").val(),
			userName:$("#danwei").val(),scope:$("#shichang").val(),
			startDate:$("#timestart").datebox('getValue'),
			endDate:$("#timeend").datebox('getValue')
			}; //把查询条件拼接成JSON
			}else{
			query={
			companyName:$("#mingcheng").val(),
			scope:$("#shichang").val(),
			startDate:$("#timestart").datebox('getValue'),
			endDate:$("#timeend").datebox('getValue'),
			userName : titleAll
			}; //把查询条件拼接成JSON
			}
			$("#dg").datagrid('options').queryParams=query; //把查询条件赋值给datagrid内部变量
			$("#dg").datagrid('reload'); //重新加载
		}
		function fixWidth(precend) {
			return document.body.clientWidth * precend;
		}

		$.fn.datebox.defaults.formatter = function(date) {
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			//return m+'/'+d+'/'+y;
			return y + '-' + m + '-' + d;
		}
	</script>
	<style type="text/css">

</style>


</body>
</html>