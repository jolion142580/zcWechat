<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String url=request.getParameter("url");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>

	<script type="text/javascript">
		$(function() {
            banding();
        });
        
        function banding(){
       $.post('long2short!geturl',{ShortUrl:'<%=url%>'},function(result) {
				 location.href=result;
		        }, "text");
		}
	</script>

  </head>
  <body>
  
  
  </body>

</html>
