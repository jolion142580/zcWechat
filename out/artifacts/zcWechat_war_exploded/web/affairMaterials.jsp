<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.WxJSSignUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.TokenHepl" %>
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";

%>
<%
    //    String token = TokenHepl.getaccessToken().getAccessToken();
//    String jsapi_ticket = TokenHepl.jsapi_ticket;
//    String url = WxJSSignUtil.getUrl();
//    System.out.println("==url==" + url);
//    System.out.println("jsapi_ticket==" + jsapi_ticket);
//    Map map = WxJSSignUtil.sign(jsapi_ticket, url);
    String affairid = (String) session.getAttribute("affairid");
    String objindex = (String) session.getAttribute("objindex");
    String openid = (String) session.getAttribute("openid");
    String onlineApplyId = (String) session.getAttribute("onlineApplyId");
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta name="generator"
          content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎街道行政服务中心</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <%--<link rel="shortcut icon" href="/favicon.ico"/>--%>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/sm.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/guide-style.css" type="text/css"/>

    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="js/jquery-weui.js"></script>
    <script type="text/javascript" src="lib/zepto.js"></script>
    <script type="text/javascript" src="js/config.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <style type="text/css">
        .main_color {
            color: #FFFFFF;
        }

        .weui-cells {
            margin-top: 0;
        }

        .weui-search-bar {
            background-color: #0490E4;
        }

        .weui-search-bar__form {
            background-color: #0490E4;
        }

        .weui-search-bar__cancel-btn {
            color: #FFFFFF;
        }
    </style>
</head>


<body style="width: 100%;height: 90%;" ontouchstart>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <a class="button button-link button-nav pull-left" href="javascript:;"
               onclick="skip()"><span class="icon icon-left main_color"></span></a>
            <h1 class="title main_color">事项材料</h1>
        </header>

        <div class="content">

            <div class="weui-cells__title">上传所需材料</div>
            <div class="weui-gallery" id="gallery">
                <span class="weui-gallery__img" id="galleryImg"></span>

                <div class="weui-gallery__opr"
                     style="right:120px;line-height: 28px;height: 28px;bottom: 64px;background: rgba(0, 0, 0, 0); ">
                    <a href="javascript:" class="gallery__del" style="float: right;"><%--class="weui-gallery__del"--%>
                        <img src="zcWechatImage/del.jpg" width="28px" height="28px" title="点击删除"
                             style="background: rgba(0, 0, 0, 0.25);">
                        <%--<i class="weui-icon-delete weui-icon_gallery-delete"></i>--%>
                    </a>
                </div>
            </div>
            <div id="item">

            </div>
            <div class="weui-btn-area">
                <a class="weui-btn weui-btn_primary" href="javascript:" onClick="commit();"
                   id="showTooltips" style="background-color: #911edb">提交并保存</a>
            </div>

        </div>
    </div>
</div>

</body>

</html>
<script type="text/javascript">
    var maxCount = 9;
    var count = 0;

    function loadData() {

        var affairMaterial = "";
        $.post("ssAffairsMaterialsInfo!findMaterialsByAffairid", {
            affairid: '<%=affairid%>',
            matindex: '<%=objindex%>'
        }, function (data) {
            //alert(data);
            $("#item").empty();

            var sb = "";
            affairMaterial = data;
            $.each(data, function (k, v) {
                sb += '<div class="weui-cells weui-cells_form">';
                sb += '						<div class="weui-cell">';
                sb += '						    <div class="weui-cell__bd">';
                sb += '						      <div class="weui-uploader">';
                sb += '						        <div class="weui-uploader__hd">';
                sb += '						          <p class="weui-uploader__title">';
                if (v.ismust == 1) {
                    sb += '<span style="color:red;">* </span>';
                }
//

                if (v.localpath != '') {
                    sb += v.matname + '<a   href="ssAffairsMaterialsInfo!download?id=' + v.id + '"><img style="width: 70px; vertical-align: middle;display: inline-block;" src="images/weixin/table.png"></a> </p>';
                }
                else {
                    sb += v.matname + '</p>';
                }
                sb += '						          <div class="weui-uploader__info" id="' + v.id + 'uploaderInfo">0/9</div>';
                sb += '						        </div>';
                sb += '						        <div class="weui-uploader__bd">';
                sb += '						          <ul class="weui-uploader__files" id="' + v.id + 'uploaderFiles" >';
                sb += '						       			';
                sb += '						          </ul>';
                sb += '						          <div class="weui-uploader__input-box" id="' + v.id + 'uploaderBox">';
                sb += '						            <input id="' + v.id + 'uploaderInput" type="file" multiple="multiple" accept="image/*" class="weui-uploader__input" onchange="uploaderInput(' + v.id + ',\'' + v.matname + '\')">';
                sb += '						          </div>';
                sb += '						        </div>';
                sb += '						      </div>';
                sb += '						    </div>';
                sb += '						  </div>';
                sb += '						</div>';
            });
            $("#item").append(sb);
        }, "json").complete(function () {
            loadFile(affairMaterial);
        });

    }


    function loadFile(affairMaterial) {
        $.each(affairMaterial, function (k, v) {
            $.post('WeChatUpload!findByOpenId', {
                openid: '<%=openid%>',
                materialid: v.id,
                onlineApplyId: '<%=onlineApplyId%>'
            }, function (data) {
                if (data.length > 0) {
                    var d = eval("(" + data + ")")
                    $.each(d, function (k1, v1) {
                        if (v1.mediaId != undefined) {
                            $('#' + v.id + 'uploaderFiles').append("<li id='" + v1.mediaId + "' name='" + v.id + "uploaderFile'  class='weui-uploader__file' style='background-image:url(WeChatUpload!IoReadImage?mediaId=" + v1.mediaId + ")' onclick='lookFile(this)'></li>");
                            $('#' + v.id + 'uploaderInfo').text($("li[name='" + v.id + "uploaderFile']").length + "/" + maxCount);
                        }
                    })
                }
                if ($("li[name='" + v.id + "uploaderFile']").length >= maxCount) {
                    $('#' + v.id + 'uploaderBox').attr("style", "display:none");
                }
            });
        })
    }

    function uploaderInput(id, matname) {
        var size = 1024 * 1024 * 2;
        var bol = false;
        // var fileList = $("#"+id+"uploaderInput").files;
        var docObj = document.getElementById(id + "uploaderInput");
        var fileList = docObj.files;
        //检测文件是否为图片
        for (var i = 0; i < fileList.length; i++) {
            var type = fileList[i]["type"];
            if (size <= fileList[i]["size"]) {
                alert("请选择小于2MB的图片！");
                return;
            }
            if (type.indexOf("image/") == -1) {
                alert("请选择图片类型！");
                return;
            }
        }
        //检测材料是否到达9张
        $.ajax({
            type: "post",
            async: false,
            url: "<%=basePath%>WeChatUpload!count",
            data: {
                "materialid": id,
                "onlineApplyId": "<%=onlineApplyId%>",
                "openid": "<%=openid%>",
                "affairid": "<%=affairid%>",
                "remark": matname
            },
            success: function (data) {
                data = eval("(" + data + ")");
                if (data.size + fileList.length > 9) {
                    alert("该材料上传图片过多！")
                    bol = true;
                }
            }
        });
        if (bol) {
            return;
        }
        for (var i = 0; i < fileList.length; i++) {
            var formData = new FormData();
            formData.append('file', fileList[i]); // 固定格式
            formData.append("openid", "<%=openid%>");
            formData.append("affairid", "<%=affairid%>");
            formData.append("remark", matname);
            formData.append("materialid", id);
            formData.append("onlineApplyId", "<%=onlineApplyId%>");
            $.ajax({
                url: "<%=basePath%>WeChatUpload!savePictureToWeb",
                type: "POST",
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    data = eval("(" + data + ")");
                    $('#' + id + 'uploaderFiles').append("<li id='" + data.mediaId + "' name='" + id + "uploaderFile'  class='weui-uploader__file' style='background-image:url(WeChatUpload!IoReadImage?mediaId=" + data.mediaId + ")' onclick='lookFile(this)'></li>");
                    $('#' + id + 'uploaderInfo').text($("li[name='" + id + "uploaderFile']").length + "/" + maxCount);
                    if ($("li[name='" + id + "uploaderFile']").length >= maxCount) {
                        $('#' + id + 'uploaderBox').attr("style", "display:none");
                    }
                    //清除选中文件
                    $("#" + id + "uploaderInput").val("");
                },
                fail: function (data) {
                    alert("上传图片失败")
                }
            });
        }
    }

    function lookFile(that) {
        index = $(that).index();
        $("#galleryImg").attr("style", that.getAttribute("style"));
        $("#galleryImg").attr("data-value", that.getAttribute("id"));
        $("#gallery").fadeIn(100);

    }

    $(function () {
        loadData();
        $("#gallery").on("click", function () {
            $("#gallery").fadeOut(100);
        });
        //删除图片
        $(".gallery__del").click(function () {
            // var imgStyle = $("#galleryImg").attr("style");
            var mediaId = $("#galleryImg").attr("data-value");
            // mediaId = imgStyle.substring(imgStyle.indexOf("=") + 1, imgStyle.length - 1);
            var id = $("#" + mediaId).parent().attr("id").replace("uploaderFiles", "");
            $("#" + mediaId).remove();
            $.post("WeChatUpload!delFile", {mediaId: mediaId}, function (res) {
                $('#' + id + 'uploaderInfo').text($("li[name='" + id + "uploaderFile']").length + "/" + maxCount);
                if ($("li[name='" + id + "uploaderFile']").length < maxCount) {
                    $('#' + id + 'uploaderBox').attr("style", "display:block");
                }

            })

        });


    });

    function commit() {
        var iscommit = "";
        $(".weui-uploader__title > span").each(function () {
            // let this = $(this);
            console.log($(this).parent().parent().next().find("li").length);
            if ($(this).parent().parent().next().find("li").length < 1) {
                iscommit = "false";
                return false;
            } else {
                iscommit = "true";
            }

            if (iscommit == "" && $('.weui-uploader__file').length < 1) {
                iscommit = "false";
                return false;
            } else {
                iscommit = "true";
            }

        });
        if (iscommit == "true") {
            postAffairs();
        }
        else {
            if (confirm("你还有重要资料未上传，确认提交吗？")) {
                //点击确定后操作
                postAffairs();
            }

        }


    }

    function postAffairs() {
        // $("#showTooltips").hide();

        $.post("onlineApply!modify", {myid: '<%=onlineApplyId%>'}, function (data) {

            if (eval("(" + data + ")").flag == 1) {
                alert("提交成功");
                window.location = "<%=basePath%>web!newHistory";
            }
            $("#showTooltips").show();
        })
    }

</script>
<script type="text/javascript">
    function skip() {
        if (confirm("资料未提交，是否退出？")) {
            window.location = "<%=basePath%>web!newHistory";
        }
    }
</script>
