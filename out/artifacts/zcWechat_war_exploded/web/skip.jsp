
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>跳转界面</title>
    <script type="text/javascript">
        parent.window.location = '<%=basePath%>web!login.action'
    </script>
</head>
<body>

</body>
</html>
