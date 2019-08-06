﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page import="com.gdyiko.zcwx.po.SsUserInfo" %>

<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";

    String affairid = (String) session.getAttribute("affairid");
    String objindex = (String) session.getAttribute("objindex");
    SsUserInfo user = (SsUserInfo) session.getAttribute("user");
    String openid = user.getId();
    String onlineApplyId = (String) session.getAttribute("onlineApplyId");

    //修改附件后清空onlineApplyId,否则申请不需要填表事项时会读取上一个事项内容
    //session.removeAttribute("onlineApplyId");
	/* if(this.model.getOnlineApplyId()==null){
				this.model.setOnlineApplyId("onlineApplyId");//申请人办理事项
	} */
	/* if(affairid.equals(""))affairid="affairid";
	if(openid.equals(""))openid="openid";
	if(onlineApplyId.equals(""))onlineApplyId="onlineApplyId"; */
	/* String openid="1111";
	String code="22222";
	String type="33333"; */
    //System.out.println("----aaaaa1123123affairid---" + affairid + "=====" + onlineApplyId);
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
    <link rel="shortcut icon" href="/favicon.ico"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" href="/css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="/css/sm.min.css" type="text/css"/>
    <link rel="stylesheet" href="/css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="/lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="/css/guide-style.css" type="text/css"/>

    <script type="text/javascript" src="/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="/js/jquery-weui.js"></script>
    <script type="text/javascript" src="/lib/zepto.js"></script>
    <script type="text/javascript" src="/js/config.js"></script>

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
                <div class="weui-gallery__opr">
                    <a href="javascript:" class="weui-gallery__del">
                        <i class="weui-icon-delete weui-icon_gallery-delete">点击删除</i>
                    </a>
                </div>
            </div>
            <div id="item">

            </div>
            <div class="weui-btn-area">
                <a class="weui-btn weui-btn_primary" href="javascript:" onClick="commit();"
                   id="showTooltips" style="background-color: #911edb">提交并保存</a>
            </div>
            <input type="hidden" id = "imgArr">
        </div>
    </div>
</div>

</body>

</html>
<script type="text/javascript">
    var maxCount = 9;
    var count = 0; //记录该事项材料上传数
    function loadData() {

        var affairMaterial = "";
        $.post("/ssAffairsMaterialsInfo!findMaterialsByAffairid", {
            affairid: '<%=affairid%>',
            matindex: '<%=objindex%>'
        }, function (data) {
            //alert(data);
            console.log(data);
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
                    sb += v.matname + '<a   href="/ssAffairsMaterialsInfo!download?id=' + v.id + '"><img style="width: 70px; vertical-align: middle;display: inline-block;" src="images/weixin/table.png"></a> </p>';
                }
                else {
                    sb += v.matname + '</p>';
                }
                sb += '						          <div class="weui-uploader__info" id="' + v.id + 'uploaderInfo">0/9</div>';
                sb += '						        </div>';
                sb += '						        <div class="weui-uploader__bd">';
                sb += '						          <ul class="weui-uploader__files" id="' + v.id + 'uploaderFiles" >';
                sb += '						              <!-- <li class="weui-uploader__file" style="background-image:url(images/pic_160.png)"></li> -->';
                sb += '						       			';
                sb += '						          </ul>';
                sb += '						          <div class="weui-uploader__input-box" id="' + v.id + 'uploaderBox">';
                sb += '						            <input type="file" id="' + v.id + 'uploaderInput" accept="image/*" multiple class="weui-uploader__input" onchange="imgChange(' + v.id + ',\'' + v.matname + '\')">';
                sb += '						          </div>';
                sb += '						        </div>';
                sb += '						      </div>';
                sb += '						    </div>';
                sb += '						  </div>';
                sb += '						</div>';

                //});
            });
            $("#item").append(sb);
        }, "json").complete(function () {
            //完成后操作
            //alert("加载完成");
            loadFile(affairMaterial);
        });

    }

    /*  function download() {
          $("#download").attr("href", "ssAffairsMaterialsInfo!download");
      }*/


    function loadFile(affairMaterial) {
        //alert(affairMaterial);
        $.each(affairMaterial, function (k, v) {
            $.post('/WeChatUpload!findByOpenId', {
                openid: '<%=openid%>',
                materialid: v.id,
                onlineApplyId: '<%=onlineApplyId%>'
            }, function (data) {

                if (data.length > 0) {
                    var d = eval("(" + data + ")")

                    $.each(d, function (k1, v1) {
                        if (v1.mediaId != undefined) {
                            $('#' + v.id + 'uploaderFiles').append("<li id='" + v1.id  + "' name='" + v.id + "uploaderFile'  class='weui-uploader__file' style='background-image:url(/WeChatUpload!IoReadImage?myid=" + v1.id + ")' onclick='lookFile(this)'></li>");
                            //alert($("li[name='"+id+"uploaderFile']").length);
                            $('#' + v.id + 'uploaderInfo').text($("li[name='" + v.id + "uploaderFile']").length + "/" + maxCount);
                            if ($("li[name='" + v.id + "uploaderFile']").length >= maxCount) {
                                $('#' + v.id + 'uploaderBox').attr("style", "display:none");
                            }
                        }
                    })

                }

            });
        })
    }

    function checkCount(id, matname) {
        $.ajax({
            type: "post",
            async: false,
            url: "/WeChatUpload!count",
            data: {
                "materialid": id,
                "onlineApplyId": "<%=onlineApplyId%>",
                "openid": "<%=openid%>",
                "affairid": "<%=affairid%>",
                "remark": matname
            },
            success: function (data) {
                data = eval("(" + data + ")");
                count = data.size;
            }
        });
    }

    var userId = "<%=openid%>";
    //存放文件数组
    var imgArr = [];
    function imgChange(id, matname) {
        checkCount(id, matname);
        //获取点击的文本框
        var file = document.getElementById(id+"uploaderInput");
        //存放图片的父级元素
        var imgContainer = document.getElementById(id+"uploaderFiles");
        //获取的图片文件
        var fileList = file.files;
        //文本框的父级元素
        var input = document.getElementsByClassName("weui-uploader__input-box")[0];
        var s = $("#imgArr").val();
        if (s==""){
        } else{
            imgArr = s.split(",");
        }
        console.log(imgArr);
        checkCount(id, matname);
        if (count + fileList.length > maxCount) {
            alert("该材料上传图片过多！")
            return;
        }
        for (var i = 0; i < fileList.length; i++) {
            $.showLoading("图片上传中...");
            syncUpload(id,matname,fileList[i],imgArr,fileList.length,i,imgContainer);
        };
    }

    var syncUpload = function (id, matname,file,imgArr,filecount,i,imgContainer) {

        var formData = new FormData();
        formData.append("file",file);
        formData.append("openid",userId);
        formData.append("remark",matname);
        formData.append("materialid",id);
        formData.append("onlineApplyId",'<%=onlineApplyId%>');
        formData.append("affairid","<%=affairid%>");

        $.ajax({
            url:"/WeChatUpload!savePicture",
            type : 'POST',
            data : formData,
            processData: false,
            contentType: false,
            dataType:"json",
            success: function (data) {
                console.log(data);
                if (data.res == true) {
                    var imgUrl = data.filepath;
                    var s =imgUrl.split("?myid=");
                    imgArr.push(imgUrl);
                    $("#imgArr").val(imgArr);
                    var img = document.createElement("li");
                    img.setAttribute("style", "background-image:url(/" + imgUrl + ")");
                    img.setAttribute("class", "weui-uploader__file");
                    img.setAttribute("name", id+"uploaderFile");
                    img.setAttribute("id", s[1]);
                    img.setAttribute("onclick", "lookFile(this)");
                    imgContainer.appendChild(img);

                    $('#' + id + 'uploaderInfo').text($("li[name='" + id + "uploaderFile']").length + "/" + maxCount);
                    if ($("li[name='" + id + "uploaderFile']").length >= maxCount) {
                        $('#' + id + 'uploaderBox').attr("style", "display:none");
                    }
                    if (i==filecount-1){
                        $.hideLoading();
                    }
                }else{
                    $.alert('上传图片失败，请重试');
                    $.hideLoading();
                }
            }
        });
    };

    //存放点击元素的name，用于获取ID
    var uid = "";
    function lookFile(that) {
        index = $(that).index();
        $("#galleryImg").attr("style", that.getAttribute("style"));
        uid = that.getAttribute("name");
        $("#gallery").fadeIn(100);
    }

    $(function () {

        loadData();

        $("#gallery").on("click", function () {
            $("#gallery").fadeOut(100);
            uid = "";
        });
        //删除图片
        $(".weui-gallery__del").click(function () {
            $.confirm({
                title: '提醒',
                text: '是否删除图片',
                onOK: function () {
                    var imgStyle = $("#galleryImg").attr("style");
                    var mediaId = imgStyle.substring(imgStyle.indexOf("=") + 1, imgStyle.length - 1);
                    var id = uid.replace("uploaderFile", "");

                    $.post("/WeChatUpload!delFile", {myid: mediaId}, function (res) {
                        if (res.res=="true"){
                            $("#" + mediaId).remove();
                            $('#' + id + 'uploaderInfo').text($("li[name='" + id + "uploaderFile']").length + "/" + maxCount);
                            if ($("li[name='" + id + "uploaderFile']").length < maxCount) {
                                $('#' + id + 'uploaderBox').attr("style", "display:block");
                            }
                        }
                        uid = "";
                    },"json");
                }
            });
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

        $.post("/onlineApply!modify", {myid: '<%=onlineApplyId%>'}, function (data) {

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

