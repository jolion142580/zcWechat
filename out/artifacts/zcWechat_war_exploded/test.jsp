<%@page import="com.gdyiko.zcwx.weixinUtils.CustomMessageAPI"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.gdyiko.zcwx.weixinUtils.TemplateData"%>
<%@page import="com.gdyiko.zcwx.weixinUtils.WxTemplate"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="js/jquery-weui.js"></script>
<script type="text/javascript" src="lib/zepto.js"></script>

  </head>
 <%
 Map<String,TemplateData> m = new HashMap<String,TemplateData>();

TemplateData first = new TemplateData();
first.setColor("#000000");
first.setValue("你好，你提交申请项目的资助待审核。");
m.put("first", first);
TemplateData keyword1 = new TemplateData();
keyword1.setColor("#328392");
keyword1.setValue("张三");
m.put("keyword1", keyword1);

TemplateData keyword2 = new TemplateData();
keyword2.setColor("#328392");
keyword2.setValue("居住证办理");
m.put("keyword2", keyword2);

TemplateData keyword3 = new TemplateData();
keyword3.setColor("#328392");
keyword3.setValue(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()));
m.put("keyword3", keyword3);

TemplateData keyword4 = new TemplateData();
keyword4.setColor("#328392");
keyword4.setValue("初审中");
m.put("keyword4", keyword4);

TemplateData remark = new TemplateData();
remark.setColor("#929232");
remark.setValue("请关注审核状态！");
m.put("remark", remark);

WxTemplate template = new WxTemplate();
template.setUrl("http://www.baidu.com");
template.setTouser("oEyt00yz55O7DYPXt6fVGQIjYZmo");
template.setTopcolor("#000000");
template.setTemplate_id("WLkSsDqQtqCeJyy33EbwFyC_CrOHi_FjS1WfaTtYhdM");
template.setData(m);

CustomMessageAPI api = new CustomMessageAPI();
String result = api.sendTemplateMessage(template);
System.out.println("......"+result);

 
  %>
  <body>
<a href="146_1362.pdf">附件</a>
  </body>
</html>
