<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.WxJSSignUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.TokenHepl" %>

<%
    String token = TokenHepl.getaccessToken().getAccessToken();
    String jsapi_ticket = TokenHepl.jsapi_ticket;
    String url = WxJSSignUtil.getUrl();
    System.out.println("==url==" + url);
    System.out.println("jsapi_ticket==" + jsapi_ticket);
    Map map = WxJSSignUtil.sign(jsapi_ticket, url);

	/* String code = request.getParameter("code");
	String affairid=request.getParameter("affairid");
	String type = "";
	
	System.out.println("code:::::" + code + "----type-----" + type);
	String openid=(String)session.getAttribute("openid");
	
	if(code!=null && openid == null){
		OAuth oauth = new OAuth();
		openid=oauth.getOppenid(code);	
	} 
	System.out.println("----openid---"+openid); */
    String affairid = (String) session.getAttribute("affairid");
    String objindex = (String) session.getAttribute("objindex");
    String openid = (String) session.getAttribute("openid");
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
    <meta name="generator"
          content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎街道行政服务中心</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <link rel="shortcut icon" href="/favicon.ico"/>
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


<body ontouchstart>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <a class="button button-link button-nav pull-left" href="javascript:;"
               onclick="javascript:WeixinJSBridge.call('closeWindow');"><span class="icon icon-left main_color"></span></a>
            <h1 class="title main_color">事项材料</h1>
        </header>

        <div class="content">

            <div class="weui-cells__title">上传所需材料</div>
            <div class="weui-gallery" id="gallery">
                <span class="weui-gallery__img" id="galleryImg"></span>
                <div class="weui-gallery__opr">
                    <a href="javascript:" class="weui-gallery__del">
                        <i class="weui-icon-delete weui-icon_gallery-delete"></i>
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
    var count = 0; //记录该事项材料上传数
    function loadData() {

        var affairMaterial = "";
        $.post("ssAffairsMaterialsInfo!findMaterialsByAffairid", {
            affairid: '<%=affairid%>',
            matindex: '<%=objindex%>'
        }, function (data) {
            //alert(data);
            $("#item").empty();

            var sb = "";
            /* if(data.length<=0)content="<a href=\"javascript:;\"  class=\"weui-cell weui-cell_access\"> <div class=\"weui-cell__hd\" ><img src=\"icon/arrow_green.png\" alt=\"\" style=\"width:10px;margin-right:5px;display:block\"></div> <div class=\"weui-cell__bd\"> 没有找到相关的事项！ </div> <div class=\"weui-cell__ft\"></div></a>"; */
            affairMaterial = data;
            //alert(affiarMaterial);

            $.each(data, function (k, v) {
                //$.each(v, function(kk, vv) {

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
//                    sb += v.matname + '<a   href="ssAffairsMaterialsInfo!download?id=' + v.id + '">下载</a> </p>';
                    sb += v.matname + '<a   href="ssAffairsMaterialsInfo!download?id=' + v.id + '"><img style="width: 70px; vertical-align: middle;display: inline-block;" src="images/weixin/table.png"></a> </p>';
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
                sb += '						            <input id="' + v.id + 'uploaderInput" class="weui-uploader__input" onclick="uploaderInput(' + v.id + ',\'' + v.matname + '\')">';
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
            url: "WeChatUpload!count",
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

    function uploaderInput(id, matname) {
        checkCount(id, matname);
        wx.ready(function () {
            wx.chooseImage({
                // count: maxCount, // 最多9张 默认9
                count: maxCount - count, // 最多9张 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    var localIds = res.localIds;
                    checkCount();
                    syncUpload(localIds);
                }
            });

            var syncUpload = function (localIds) {
                checkCount(id, matname);
                if (count + localIds.length > maxCount) {
                    alert("该材料上传图片过多！")
                    return;
                }
                var localId = localIds.pop();
                wx.uploadImage({
                    localId: localId,
                    isShowProgressTips: 1,//显示进度提示
                    success: function (res) {
                        var serverId = res.serverId; // 返回图片的服务器端ID
                        //将获取到的 mediaId 传入后台 方法savePicture
                        $.post("WeChatUpload!savePicture", {
                            affairid:<%=affairid%>,
                            mediaId: serverId,
                            openid: '<%=openid%>',
                            remark: matname,
                            materialid: id,
                            onlineApplyId: '<%=onlineApplyId%>'
                        }, function (res) {
                            res = eval('(' + res + ')');
                            if (res.res == 'true') {
                                $('#' + id + 'uploaderFiles').append("<li id='" + serverId + "' name='" + id + "uploaderFile'  class='weui-uploader__file' style='background-image:url(WeChatUpload!IoReadImage?mediaId=" + serverId + ")' onclick='lookFile(this)'></li>");
                            }
                            //alert($("li[name='"+id+"uploaderFile']").length);
                            $('#' + id + 'uploaderInfo').text($("li[name='" + id + "uploaderFile']").length + "/" + maxCount);
                            if ($("li[name='" + id + "uploaderFile']").length >= maxCount) {
                                $('#' + id + 'uploaderBox').attr("style", "display:none");
                            }
                        })

                        if (localIds.length > 0) {
                            syncUpload(localIds);
                        }

                    },
                    fail: function (res) {
                        alertModal('上传图片失败，请重试')
                    }

                });
            };
        })

    }

    function lookFile(that) {
        index = $(that).index();
        $("#galleryImg").attr("style", that.getAttribute("style"));
        $("#gallery").fadeIn(100);

    }

    $(function () {

        loadData();
        wx.config({
            debug: false,
            appId: weChat.APPID, // 必填，公众号的唯一标识
            timestamp: '<%=map.get("timestamp") %>', // 必填，生成签名的时间戳
            nonceStr: '<%=map.get("nonceStr") %>', // 必填，生成签名的随机串
            signature: '<%=map.get("signature") %>',// 必填，签名，见附录1
            jsApiList: ['chooseImage', 'uploadImage']
            // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });


        $("#gallery").on("click", function () {
            $("#gallery").fadeOut(100);
        });
        //删除图片
        $(".weui-gallery__del").click(function () {
            //$("#uploaderFiles").find("li").eq(index).remove();
            var imgStyle = $("#galleryImg").attr("style");
            var mediaId = imgStyle.substring(imgStyle.indexOf("=") + 1, imgStyle.length - 1);
            var id = $("#" + mediaId).parent().attr("id").replace("uploaderFiles", "");

            $("#" + mediaId).remove();
            //alert(imgStyle.substring(imgStyle.indexOf("=")+1,imgStyle.length-1));
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
                WeixinJSBridge.call('closeWindow');
            }
            $("#showTooltips").show();
        })
    }

</script>

