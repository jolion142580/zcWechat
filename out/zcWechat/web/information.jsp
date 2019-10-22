<%@ page
        import="com.opensymphony.xwork2.ActionContext" %><%--<%@ taglib prefix="s" uri="http://jakarta.apache.org/struts/tags-logic" %>--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="date" class="java.util.Date" scope="request">
</jsp:useBean>
<%--
  Created by IntelliJ IDEA.
  User: jolion
  Date: 2019-05-05
  Time: 09:59
  To change this template use File | Settings | File Templates.
--%>
<%
    Boolean result = true;
    String uuid = (String) session.getAttribute("uuid");
    String openid = null;
    if (uuid != null) {
//        openid = (String) application.getAttribute(uuid);
        openid = (String) ActionContext.getContext().getApplication().get(uuid);
    }
    if (openid != null && !openid.equals("")) {
        result = false;
    }
%>
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>上传资料记录</title>
    <style type="text/css">
        * {
            text-align: center;
            font-size: 16px;
        }

        .parLegend {
            color: #06c;
            font-weight: 800;
            background: white;
        }

        .subLegend {
            color: #3a8ee6;
            font-weight: 600;
            background: whitesmoke;
        }

        .par {
            background: whitesmoke;
            border-color: #0A93F1;
            border-style: dashed;
            border-width: 2px;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            -khtml-border-radius: 5px;
            border-radius: 5px;
            line-height: 30px;
            list-style: none;
            padding: 5px 10px;
            margin-bottom: 2px;
        }

        .sub {
            background: honeydew;
            border-color: #0d9dd2;
            border-style: solid;
            border-width: 2px;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            -khtml-border-radius: 5px;
            border-radius: 5px;
            line-height: 30px;
            list-style: none;
            padding: 5px 10px;
            margin-bottom: 2px;
        }

        .imgCard {
            height: 240px;
            width: 180px;
        }

        .img {
            height: 200px;
            width: 120px;

        }

        table {
            width: 100%;
            margin: 0 auto;
            min-width: 30%;
            font-family: verdana, arial, sans-serif;
            font-size: 11px;
            color: #333333;
            border-width: 1px;
            border-color: #999999;
            border-collapse: collapse;
        }

        table th {
            width: 25%;
            background-color: #c3dde0;
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #d4e3e5;
        }

        table tr {
            background-color: #d4e3e5;
        }

        table td {
            width: 25%;
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #d4e3e5;
        }
    </style>
    <script src="scripts/jquery.js"></script>
    <script src="scripts/jquery.qrcode.min.js"></script>
    <script src="js/config.js"></script>
    <script src="js/layer/layer.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <script type="text/javascript">
        var result = '<%=result%>';
        if (result == 'true') {
            window.location = "<%=basePath%>web/QRCodeImg.jsp";
        } else {
        }
        function showImg(imgUrl) {
            var img_infor = "<img class='showImg' src='" + imgUrl + "' />";
            layer.open({
                id: "img",
                type: 1,
                closeBtn: 1,
                shade: false,
                title: false, //不显示标题
                //skin: 'layui-layer-nobg', //没有背景色
                anim: 1,
                maxmin: true,
                moveOut: true,
                shadeClose: false,
                area: ['800px', '520px'],
                content: img_infor,//捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响    
                resizing: function (layero) {
                    // console.log(layero);
                }
            });
        }
    </script>
</head>
<body>
<div>
    <s:if test="informationToList.size()==0 and cardList.size()== 0">
        <fieldset>
            <legend><s:date name="#request.date" format="yyyy-MM-dd"/></legend>
            <h4>暂无提交资料记录</h4>
        </fieldset>
    </s:if>
    <s:else>
        <s:if test="cardList.size() != 0">
            <fieldset class="par">
                <legend class="parLegend">登录认证身份证</legend>
                <div>
                    <s:iterator value="cardList">
                        <img class="imgCard" src="fileInfo!showImgByWeb?id=<s:property value="id" />"
                             title="点击查看大图"
                             onclick="showImg('fileInfo!showImgByWeb?id=<s:property value="id"/>')">
                    </s:iterator>
                </div>
            </fieldset>
        </s:if>
        <s:if test="informationToList.size() != 0">
            <s:iterator value="informationToList" status="state">
                <s:iterator value="informationToList[#state.index]">
                    <fieldset class="par">
                        <legend class="parLegend"><s:property value="key"/></legend>
                        <s:iterator value="value" status="state2">
                            <s:iterator value="value[#state2.index]">
                                <fieldset class="sub">
                                    <legend class="subLegend"><s:property value="key"/></legend>
                                    <div>
                                        <s:iterator value="value">
                                            <img class="img" src="fileInfo!showImgByWeb?id=<s:property value="id" />"
                                                 title="点击查看大图"
                                                 onclick="showImg('fileInfo!showImgByWeb?id=<s:property value="id"/>')">
                                        </s:iterator>
                                    </div>
                                </fieldset>
                            </s:iterator>
                        </s:iterator>
                    </fieldset>
                </s:iterator>
            </s:iterator>
        </s:if>
    </s:else>
</div>
<div id="pc_qr_code">

</div>


</body>
</html>
