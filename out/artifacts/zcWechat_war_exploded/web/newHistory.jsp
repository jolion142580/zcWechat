<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";

%>

<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">--%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>张槎街道行政服务中心</title>
    <meta name="viewport"
          content="width=device-width,height=device-height,initial-scale=1,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="content/reflow.css">
    <link rel="stylesheet" href="content/interaction_pc.css">
    <script src="scripts/jquery.min.js" type="text/javascript"></script>
    <script src="scripts/hammer.min.js" type="text/javascript"></script>
    <script src="scripts/base.js" type="text/javascript"></script>
    <script src="js/layer/layer.js" type="text/javascript"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <style type="text/css">
        .content_review {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
        .content_review .body_review {
            float: left;
            /*width: 85%;*/
            height: 100%;
            margin: 0 auto;
            width: 99%;
            border: 0px solid #fff;
            background: #fff;
            -webkit-transition: none;
            /*-o-transition: none;*/
            transition: none;
        }

        .header {
            z-index: 1;
            display: block;
            width: 100%;
            height: 56px;
            line-height: 48px;
            background: #0490E4;;
            color: #fff;
            overflow: hidden !important;
        }

        .showInformation {
            width: 100%;
            height: 100%;
            overflow: hidden;
            border-width: 0px;
            border-left-width: 1px;
        }

        #name {
            margin-left: 16px;
        }

        p {
            font-size: 16px;
        }

        .area {

            background-color: green;
            height: auto;
            border: 2px solid;
            margin: auto;
        }

        .rightarea {
            float: left;
            width: 30%;
            height: 300px;
            background-color: red;
        }

        .leftarea {
            float: left;
            width: 30%;
            height: 300px;
            background-color: yellow;
        }

        p.pt {
            color: #3b95eb;
            font-weight: bolder;
            margin: 8px;
            font-size: 16px;
        }

        .layui-layer-white {
            background: white
        }

    </style>
    <!--覆盖样式 -->
    <style type="text/css">
        .control_form {
            position: absolute;
            bottom: 64px;
            right: 180px;
            min-width: 104px;
            height: 48px;
            line-height: 48px;
            text-align: center;
            border-radius: 4px;
            color: #fff;
            overflow: hidden;
        }

        .tips_toFullScreen {
            z-index: 2;
            position: absolute;
            right: 0;
            bottom: 147px;
            padding: 8px 8px 8px 12px;
            font-size: 14px;
            line-height: 24px;
            background: #4d4d4d;
            border-radius: 4px;
            border-top-right-radius: 0;
            border-bottom-right-radius: 0;
            color: #fff;
            -webkit-transition: all .8s ease;
            -o-transition: all .8s ease;
            transition: all .8s ease;
        }

        .form_name {
            font-size: 12px;
        }

        .content_formlist {
            position: absolute;
            left: 0;
            top: 0;
            width: 28%;
        }

        .content_imgcontent {
            position: absolute;
            right: 0;
            top: 0;
            display: block;
            width: 72%;
            overflow: hidden
        }
    </style>
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
    <%-- 菜单点击 --%>
    <script type="text/javascript">
        $(function () {
            var onlineApplyId = $("#onlineApplyId").val();
            if (onlineApplyId != undefined && onlineApplyId != null) {
                $("#showInformation").attr("src", "web!showInformation?id=" + onlineApplyId);
            }
        });
        function affairBtn(onlineApplyId) {
            $("#showInformation").attr("src", "web!showInformation?id=" + onlineApplyId);
        }
    </script>

    <script src="scripts/knockout-3.4.0.js" type="text/javascript"></script>
    <script src="scripts/getUrlParam.js"></script>
    <script src="ctrl/service.js" type="text/javascript"></script>
    <!-- <script src="ctrl/formviewerModel.js" type="text/javascript"></script> -->
</head>
<%--onload="vailToken();"--%>
<body style="height: 100%">
<div class="header titlebar">
    <div class="logo">
        <h1 class="title_app">办事记录</h1>
    </div>
</div>
<div class="contain show_subcontent show_review" style="height: 100%;">
    <%--  <div class="content content_matter content_matterlist">
          <div class="scroll_parent">

          </div>
      </div>--%>
    <div class="content content_subcontent transition bgWhile"
         style="touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);width:100%;height: 90%">
        <div class="content content_form content_formlist" id="content_formlist">
            <%--  <div class="title_formNeed"></div>--%>
            <div class="scroll_parent" style="">
                <div class="scroll scroll_content auto_plus">
                    <dt class="title_list"></dt>
                    <div>
                        <c:if test="${onlineApplyList.size() == 0}">
                            <dd class="angency" >
                                <div class="item_form"><p class="form_name">暂无办事记录</p><span
                                        class="ic ic_arrowright"></span>
                                </div>
                            </dd>
                        </c:if>
                        <c:forEach items="${onlineApplyList}" var="onlineApply" varStatus="order">
                            <c:if test="${order.index == 0}">
                                <input type="hidden" id="onlineApplyId" value="${onlineApply.id}">
                                <dd class="angency selection" onclick="affairBtn('${onlineApply.id}')">
                                    <div class="item_form"><p class="form_name">${onlineApply.name}</p><span
                                            class="ic ic_arrowright"></span>
                                    </div>
                                </dd>
                            </c:if>
                            <c:if test="${order.index != 0}">
                                <dd class="angency" onclick="affairBtn('${onlineApply.id}')">
                                    <div class="item_form"><p class="form_name">${onlineApply.name}</p><span
                                            class="ic ic_arrowright"></span>
                                    </div>
                                </dd>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <div class="content content_imgcontent" >
            <div id="scrollDiv" class="content_review">
                <div class="scroll body_review">
                    <iframe id="showInformation" class="showInformation">

                    </iframe>

                </div>

            </div>
            <div class="tips_toFullScreen" style="right:140px">
                <span class="clicktips">点击全屏按钮全屏查看</span>
                <span class="btn btn_iknow">我知道了</span>
                <span class="img_fingers"></span>
            </div>
            <div class="control_form">
                <div class="backbg"></div>
                <div class="fullCover prevent"></div>
                <div class="zoomctrl fullScreen_show">
                    <b class="btn btn_zoom btn_zoom_in">
                        <span class="ic ic_zoom_in"><img src="images/ic_zoom_in.png"></span>
                    </b>
                    <p class="zoomX">100%</p>
                    <b class="btn btn_zoom btn_zoom_out">
                        <span class="ic ic_zoom_out"><img src="images/ic_zoom_out.png"></span>
                    </b>
                </div>

                <b class="btn btn_screenContorl btn_fullScreen" style="right:-10px">
                    <span class="ic ic_fullScreen fullScreen_hide"><img src="images/ic_fullscreen.png"></span>
                    <span class="ic ic_fullScreen_out fullScreen_show"><img src="images/ic_outfullscreen.png"></span>
                    <span class="status_fullscreen">全屏</span>
                </b>
            </div>
        </div>
    </div>
</div>
<%--<script type="text/javascript" >
    var yulan = document.querySelector(".content_subcontent");
    var mc = new Hammer(yulan);
    mc.on('panstart', subcontent_dragable_start);
    mc.on('panend', subcontent_dragable_end);

    function subcontent_dragable_start(ev) {
        if ($('.fullScreenMode').length <= 0) {
            $('.content_subcontent').removeClass('transition');
        }
    }

    function subcontent_dragable_move(ev) {
        origin_left = $(".content_subcontent").css('left');
        minDragLeft = $(window).width() * .2;
        minDragLeft_percent = '20%';
        dragLeft = ev.deltaX * .8 + minDragLeft;
        /*if($('fullScreenMode').length>0){
            $(".content_subcontent").css('left',minDragLeft_percent);
        }*/
        if (dragLeft <= minDragLeft || $('.fullScreenMode').length > 0 || Math.abs(ev.deltaX) < 100) {
            $(".content_subcontent").css('left', minDragLeft_percent);
        } else {
            $(".content_subcontent").css('left', dragLeft);
        }
    }

    function subcontent_dragable_end(ev) {
        if ($('.fullScreenMode').length <= 0) {
            minDragLeft = $(window).width() * .2;
            minDragLeft_percent = '20%';
            outMinLeft = $(window).width() * .2 + $(window).width() * .8 * .3;
            dragLeft = ev.deltaX * .8 + minDragLeft;
            $('.content_subcontent').addClass('transition');
            if (dragLeft > outMinLeft) {
                $('.show_subcontent').removeClass('show_subcontent');
                $(".content_subcontent").css('left', '100%');
                $('.content_matterlist .selection').removeClass('selection')
            } else {
                $(".content_subcontent").css('left', minDragLeft_percent);
            }
        }
    }


    (function ($) {
        $.fn.extend({
            Scroll: function (opt, callback) {
                //参数初始化
                if (!opt) var opt = {};
                var _btnUp = $("#" + opt.up);//Shawphy:向上按钮
                var _btnDown = $("#" + opt.down);//Shawphy:向下按钮
                var timerID;
                var _this = this.eq(0).find("ul:first");
                var lineH = _this.find("li:first").height(), //获取行高
                    line = opt.line ? parseInt(opt.line, 10) : parseInt(this.height() / lineH, 10), //每次滚动的行数，默认为一屏，即父容器高度
                    speed = opt.speed ? parseInt(opt.speed, 10) : 500; //卷动速度，数值越大，速度越慢（毫秒）
                timer = opt.timer //?parseInt(opt.timer,10):3000; //滚动的时间间隔（毫秒）
                if (line == 0) line = 1;
                var upHeight = 0 - line * lineH;
                //滚动函数
                var scrollUp = function () {
                    _btnUp.unbind("click", scrollUp); //Shawphy:取消向上按钮的函数绑定
                    _this.animate({
                        marginTop: upHeight
                    }, speed, function () {
                        for (i = 1; i <= line; i++) {
                            _this.find("li:first").appendTo(_this);
                        }
                        _this.css({marginTop: 0});
                        _btnUp.bind("click", scrollUp); //Shawphy:绑定向上按钮的点击事件
                    });
                }
                //Shawphy:向下翻页函数
                var scrollDown = function () {
                    _btnDown.unbind("click", scrollDown);
                    for (i = 1; i <= line; i++) {
                        _this.find("li:last").show().prependTo(_this);
                    }
                    _this.css({marginTop: upHeight});
                    _this.animate({
                        marginTop: 0
                    }, speed, function () {
                        _btnDown.bind("click", scrollDown);
                    });
                }
                //Shawphy:自动播放
                var autoPlay = function () {
                    //if(timer)timerID = window.setInterval(scrollUp,timer);
                };
                var autoStop = function () {
                    // if(timer)window.clearInterval(timerID);
                };
                //鼠标事件绑定
                _this.hover(autoStop, autoPlay).mouseout();
                _btnUp.css("cursor", "pointer").click(scrollUp).hover(autoStop, autoPlay);//Shawphy:向上向下鼠标事件绑定
                _btnDown.css("cursor", "pointer").click(scrollDown).hover(autoStop, autoPlay);
            }
        })
    })(jQuery);
    $(document).ready(function () {
         $("#scrollDiv").Scroll({line: 4, speed: 500, timer: 3000, up: "btn1", down: "btn2"});
    });
</script>--%>
</body>
</html>
