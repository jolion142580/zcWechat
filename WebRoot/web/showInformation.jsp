<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<jsp:useBean id="date" class="java.util.Date" scope="request">
</jsp:useBean>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>事项记录</title>
    <script src="scripts/jquery.js"></script>
    <script src="scripts/jquery.qrcode.min.js"></script>
    <script src="js/config.js"></script>
    <script src="js/layer/layer.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <style type="text/css">
        .stateTop {
            text-align: center;
            background: #0490E4;
            color: white;
            height: 32px;
            /*margin: 0px;*/
            /*border: 0px;*/
        }

        .textRed {
            color: red;
        }

        .img {
            height: 200px;
            width: 120px;

        }
    </style>
    <script type="text/javascript">
        function vailOnLineApply(id) {
            var data = {"myid": id};
            $.get("web!vailOnlineApply", data, function (res) {
                res = eval("(" + res + ")");
                if (res.flag != "0") {
                    loadEWM(res.affairid, res.objindex, id);
                }
                else {
                    layer.msg(res.Msg);
                }
            })
        }

        function loadEWM(affairid, objindex, onlineApplyId) {
            var param = "content=2&affairid=" + affairid + "&objindex=" + objindex + "&onlineApplyId=" + onlineApplyId;
            parent.window.location = "<%=basePath%>web/upLoadQRCode.jsp?" + param;
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
                area: ['520px', '400px'],
                content: img_infor,//捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响    
                resizing: function (layero) {
                    // console.log(layero);
                }
            });
        }
    </script>
    <%--初始化检测--%>
    <script type="text/javascript">
        function vailToken() {
            $.ajaxSettings.async = false;
            $.get("web!vailToken", function (data) {
                data = eval("(" + data + ")");
                if (data.flag == 'false') {
                    parent.window.location = "<%=basePath%>web!login";
                }
            })
            $.ajaxSettings.async = true;
        };
    </script>
</head>
<%--onload="vailToken();"--%>
<body style="height: 90%;">
<s:if test="onlineApply.approvedOrNot == null">
    <div style="text-align: center;height: 32px;" onclick="vailOnLineApply('<s:property value="onlineApply.id"/>')">
        <button style="width: 100%;height: 32px;font-size: 16px;">补充资料</button>
    </div>
</s:if>
<div class="stateTop">
    <span>审核状态：</span>
    <span>
        <s:if test="onlineApply.state=='STATE'">
            待资料上传
        </s:if>
        <s:else>
            <s:property value="onlineApply.state"/>
        </s:else>
        </span>
    <span style="margin-left: 16px;">结果：</span>
    <span class="textRed">
            <s:if test="onlineApply.approvedOrNot == 1">
                预审通过
            </s:if>
            <s:elseif test="onlineApply.approvedOrNot == 0">
                预审不通过
            </s:elseif>
            <s:else>

            </s:else>
    </span>

</div>
<s:if test="fileList.size()==0">
    <fieldset>
        <legend>无资料</legend>
        <h4>暂无提交资料记录</h4>
    </fieldset>
</s:if>
<s:else>
    <s:iterator value="fileList" status="state">
        <s:iterator value="fileList[#state.index]">
            <fieldset class="">
                <legend class=""><b><s:property value="key"/></b></legend>
                    <%--<s:iterator value="value" status="state2">--%>
                <div>
                    <s:iterator value="value">
                        <img class="img" src="fileInfo!showImgByWeb?id=<s:property value="id" />"
                             title="点击查看大图"
                             onclick="showImg('fileInfo!showImgByWeb?id=<s:property value="id"/>')">
                    </s:iterator>
                </div>
                    <%--</s:iterator>--%>
            </fieldset>
        </s:iterator>
    </s:iterator>
</s:else>
</body>
</html>
