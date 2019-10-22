<%--<%@ taglib prefix="s" uri="http://jakarta.apache.org/struts/tags-logic" %>--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="date" class="java.util.Date" scope="request">
</jsp:useBean>
<%
    Boolean result = true;
    String uuid = (String) session.getAttribute("uuid");
    String openid = null;
    if (uuid != null) {
        openid = (String) application.getAttribute(uuid);
//        openid = (String) ActionContext.getContext().getApplication().get(uuid);
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
    <title>办事记录</title>
    <style type="text/css">
        * {
            text-align: center;
            font-size: 16px;
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

        .parLegend {
            color: #06c;
            font-weight: 800;
            background: white;
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
    <style type="text/css">
        #divTip {
            border: 1px solid #c0c0c0;
            margin: 0 auto;
            padding: 5px;
            background: #f0f0f0;
            background: #EDFBC0;
        }

        #tipBtn {
            position: absolute;
            background: #EDFBC0;
            left: 311;
            top: 815;
            visibility: hidden;
        }

        .tipBtn {
            float: right;
            CURSOR: hand;
            color: red;
            font-weight: bold;
            font-size: 12px
        }
    </style>
    <script src="scripts/jquery.js"></script>
    <script src="scripts/jquery.qrcode.min.js"></script>
    <script type="text/javascript" src="js/config.js"></script>
    <script type="text/javascript" src="js/layer/layer.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
</head>
<body>
<div>

    <div id="tipBtn" onmouseover="clearInterval(interval)" onmouseout="interval = setInterval('changePos()', delay)"
         align="middle">
        <span class="tipBtn" onclick="clearInterval(interval);tipBtn.style.visibility = 'hidden'">关闭</span>
        <div id="divTip">
            <h4>提示</h4>
            <p>
                <u>审核状态为:“STATE”和“待资料上传”的事项</u>
            </p>
            <p>
                <u>点击后扫码上传资料完成提交</u>
            </p>
        </div>
    </div>

    <table>
        <thead>
        <tr>
            <th><label>事项名称</label></th>
            <th><label>审核状态</label></th>
            <th><label>审核结果</label></th>
            <th><label>申请时间</label></th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <s:if test="historyToList.size()==0">
        <fieldset class="par">
            <legend class="parLegend"><s:date name="#request.date" format="yyyy-MM-dd"/></legend>
            <h4>暂无事务提交</h4>
        </fieldset>
    </s:if>
    <s:else>
        <s:iterator value="historyToList" status="state">

            <s:iterator value="historyToList[#state.index]">
                <fieldset class="par">
                    <legend class="parLegend"><s:property value="key"/></legend>
                    <table>
                        <tbody>
                        <s:iterator value="value">
                            <tr onclick="showAffair('<s:property value="id"/>','<s:property value="state"/>')"
                                onmouseover="this.style.backgroundColor='#ffff66';"
                                onmouseout="this.style.backgroundColor='#d4e3e5';">
                                <td><span><s:property value="affairName"/></span></td>
                                <td><span><s:property value="state"/></span></td>
                                <s:if test='approvedOrNot  == 0'>
                                    <td><span>预审不通过</span></td>
                                </s:if>
                                <s:elseif test='approvedOrNot eq 1'>
                                    <td><span>预审通过</span></td>
                                </s:elseif>
                                <s:else>
                                    <td><span>等待审核</span></td>
                                </s:else>
                                <td><span><s:property value="creattime"/></span></td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </fieldset>

            </s:iterator>
        </s:iterator>
    </s:else>
</div>
<div id="pc_qr_code">

</div>
<script type="text/javascript">
    var result = '<%=result%>';
    if (result == 'true') {
        window.location = "<%=basePath%>web/QRCodeImg.jsp";
    } else {

    }

    function tip(obj, state) {
        if (state == "待资料上传" || state == "STATE") {
            $(obj).attr("title", "点击上传资料完成提交");
        }
        else {
            $(obj).attr("title", "请留意公众号通知");
        }
    }

    function showAffair(id, state) {
        <%--var openid = '<%=openId%>';--%>
        if (state != "待资料上传" && state != "STATE") {
            return;
        }
        layer.confirm('是否上传资料？', {
                btn: ['确定', '取消']
            }, function () {
                //后台验证 防止用户修改本地信息提交
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
        )

    }

    function loadEWM(affairid, objindex, id) {
        $("#pc_qr_code").empty();
        var content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + weChat.APPID
            + "&redirect_uri=" + weChat.weChatDNSURL
            + "onlineApply!isrelation?affairid=" + affairid + "_affairMaterialsByPC2Wechart_" + objindex + "_"
            + id + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

        $('#pc_qr_code').qrcode({
            render: "canvas",
            width: 200,
            height: 200,
            correctLevel: 0,
            text: content,
            background: "#ffffff",
            foreground: "black",
            src: ""
        });

        layer.open({
            type: 1,
            closeBtn: 1,
            shade: false,
            skin: 'layui-layer-nobg',
            title: "微信扫码上传资料",
            anim: 1,
            resize: false,
            area: ['255px', '255px'],
            content: $('#pc_qr_code'),
            end: function () {
                $("#pc_qr_code").empty();
                location.href = "<%=basePath %>web!history";
            }
        })
        ;
    }

</script>
<script language=javascript>
    var xPos = 20;
    var yPos = document.body.clientHeight;
    var step = 1;
    var delay = 30;
    var height = 0;
    var Hoffset = 0;
    var Woffset = 0;
    var yon = 0;
    var xon = 0;
    var pause = true;
    var interval;
    tipBtn.style.top = yPos;

    function changePos() {
        width = document.body.clientWidth;
        height = document.body.clientHeight;
        Hoffset = tipBtn.offsetHeight;
        Woffset = tipBtn.offsetWidth;
        tipBtn.style.left = xPos + document.body.scrollLeft;
        tipBtn.style.top = yPos + document.body.scrollTop;
        if (yon) {
            yPos = yPos + step;
        }
        else {
            yPos = yPos - step;
        }
        if (yPos < 0) {
            yon = 1;
            yPos = 0;
        }
        if (yPos >= (height - Hoffset)) {
            yon = 0;
            yPos = (height - Hoffset);
        }
        if (xon) {
            xPos = xPos + step;
        }
        else {
            xPos = xPos - step;
        }
        if (xPos < 0) {
            xon = 1;
            xPos = 0;
        }
        if (xPos >= (width - Woffset)) {
            xon = 0;
            xPos = (width - Woffset);
        }
    }

    function start() {
        tipBtn.style.visibility = "visible";
        interval = setInterval('changePos()', delay);
    }

    start();
</script>
</body>
</html>
