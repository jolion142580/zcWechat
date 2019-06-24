<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
%>
<%
    session.removeAttribute("baseDic");
    session.removeAttribute("baseDicId");
    session.removeAttribute("baseDicType");
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
    <link rel="stylesheet" href="content/keyboard.css" type="text/css"></link>
    <link rel="stylesheet" href="content/reflow.css">
    <script src="scripts/jquery.min.js" type="text/javascript"></script>
    <script src="scripts/base.js" type="text/javascript"></script>
    <script src="scripts/hammer.min.js" type="text/javascript"></script>
    <script src="js/layer/layer.js" type="text/javascript"></script>
    <link rel="stylesheet" href="content/interaction_pc.css">
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <style type="text/css">
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

        p {
            font-size: 16px;
        }

        p.pt {
            color: #3b95eb;
            font-weight: bolder;
            margin: 8px;
            font-size: 16px;
        }

        input::-webkit-input-placeholder {
            /* placeholder颜色  */
            color: white;
        }

        .wsbsDiv {
            display: none;
            position: absolute;
            /*bottom: 64px;*/
            bottom: 24px;
            right: 320px;
            min-width: 130px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            border-radius: 4px;
            color: #fff;
            overflow: hidden;
        }

        .wsbs_cur {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background: #000;
            opacity: .4;
        }

        .wsbs {
            font-size: 14px;
            position: absolute;
            right: 0;
            top: 0;
            padding: 0 24px 0 48px;
            font-weight: normal;
            cursor: pointer;
            /*right: -10px*/
        }

    </style>
    <%--覆盖样式--%>
    <style type="text/css">

        .caution {
            /*right: 0px;*/
            font-size: 14px;
            background: rgba(0, 0, 0, 0.25);
            background: transparent \9;
            zoom: 1 \8;
            -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=@bghexa, endColorstr=@bghexa)";
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#800000 0, endColorstr=#800000 0);
            position: absolute;
            /*bottom: 96px;*/
            line-height: 24px;
            /*margin: 24px 32px;*/
            /*padding: 8px 16px;*/
            margin: 24px 0px;
            padding: 6px 12px;
            font-weight: bold;
            color: #fff;
            border-radius: 4px;
            -webkit-transition: all .16s ease;
            -o-transition: all .16s ease;
            transition: all .16s ease;
        }

        .control_form {
            position: absolute;
            bottom: 24px;
            right: 180px;
            min-width: 104px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            border-radius: 4px;
            color: #fff;
            overflow: hidden;
        }

        .tips_toFullScreen {
            z-index: 2;
            position: absolute;
            right: 0;
            bottom: 96px;
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
    <script type="text/javascript">
        function onLineAffair() {
            if(!window.parent.vailFlag()){
                return ;
            }
            var affairid = $("#affairid").val();
            <%--location.href = "<%=basePath%>ssAffairsObjectInfo!findByAffairIdToWeb?affairid=" + affairid;--%>
            window.location = "<%=basePath%>ssAffairsObjectInfo!findByAffairIdToWeb?affairid=" + affairid;
        }

        function changeResult(affairId) {
            //alert(affairId);
            $.post("<%=basePath%>ssAffairsGuideInfo!findByAffairIdToWeb", {affairid: affairId}, function (result) {
                //alert(result);
                //alert(result);
                $('#searchResult').css("display", "block");
                $('.content_imgcontent').show();
                $('.content_review').scrollTop(0);
                $("#affairName").html(result.affairName);
                $("#according").html(result.according);
                $("#condition").html(result.condition);
                $("#material").html(result.material);
                $("#procedures").html(result.procedures);
                $("#xrndomu").html(result.xrndomu);
                $("#site").html(result.site);
                $("#time").html(result.time);
                $("#inquire").html(result.inquire);
                $("#charge").html(result.charge);
                $("#sepcialversion").html(result.sepcialversion);

                $("#affairid").val(affairId);

                if (result.isonline == "true") {
                    $("#wsbsDiv").show();
                } else {
                    $("#wsbsDiv").hide();
                }

            }, "json");
        }

    </script>

</head>
<%-- onload="vailToken();" --%>
<body>
<div class="header titlebar">
    <a href="ssBaseDicInfo!findAllByBaseDicTypeToWeb" jslink="ssBaseDicInfo!findAllByBaseDicTypeToWeb"
       class="btn_back link_checkJump"><span class="ic ic_back"></span></a>
    <div class="searchInput" style="width: 781px;">
        <span class="ic ic_search"></span>
        <input type="text" placeholder="搜索事项名称…" class='numkeyboard' data-bind="value:keyword,textInput: keyword"
               style="width: 701px;">
        <span class="ic ic_clearinput"></span>
    </div>
</div>
<div class="contain content_allForm content_result" style="height: 588px;">
    <div class="item_result">
        <img src="images/ic_search_big.png" height="240" width="240" alt="">
        <div>输入事项名称搜索相关办事指南</div>
    </div>
    <div id="content" class="content content_form content_formlist search_content" style="width: 100%;display:none">
        <div class="scroll_parent">
            <div id="searchResult" class="scroll_content auto_plus" data-bind="foreach:searchData">

            </div>
        </div>
    </div>

    <div class="content content_subcontent transition"
         style="touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); left: 100%;">
        <div id="searchResult1" class="content content_imgcontent content_imgcontent_search" style="display: none;">
            <div class="content_review" zoomx="1" style="height:  90%;">
                <div class="body_review">

                    <input type="hidden" id="affairid" name="affairid" value=""/>

                    <p class="pt">一、办理依据：</p>
                    <p id="according"></p>
                    <p class="pt">二、办理条件：</p>
                    <p id="condition"></p>
                    <p class="pt">三、申请材料：</p>
                    <p id="material"></p>
                    <p class="pt">四、办理程序：</p>
                    <p id="procedures"></p>
                    <p class="pt">五、办理部门：</p>
                    <p id="xrndomu"></p>
                    <p class="pt">六、受理地址：</p>
                    <p id="site"></p>
                    <p class="pt">七、办结时限：</p>
                    <p id="time"></p>
                    <p class="pt">八、咨询查询：</p>
                    <p id="inquire"></p>
                    <p class="pt">九、收费依据和标准：</p>
                    <p id="charge"></p>
                    <p class="pt">十、特别说明：</p>
                    <p id="sepcialversion"></p>

                </div>
            </div>

            <div class="tips_toFullScreen" style="right:140px">
                <span class="clicktips">点击全屏按钮全屏查看</span>
                <span class="btn btn_iknow">我知道了</span>
                <span class="img_fingers"></span>
            </div>
            <div class="control_form">
                <!-- <p class="pageNum">
                    <span class="ic ic_back btn"></span>
                    第<span>1</span>页/共<span>1</span>页
                    <span class="ic ic_go btn"></span>
                </p> -->
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
                <b class="btn btn_screenContorl btn_fullScreen"><%--style="right:-10px"--%>
                    <span class="ic ic_fullScreen fullScreen_hide"><img src="images/ic_fullscreen.png"></span>
                    <span class="ic ic_fullScreen_out fullScreen_show"><img src="images/ic_outfullscreen.png"></span>
                    <span class="status_fullscreen">全屏</span>
                </b>
            </div>
            <div class="caution">※该表仅供参考，实际以窗口前台表格为准</div>
            <div id="wsbsDiv" class="wsbsDiv" style="right: 320px;">
                <div class="wsbs_cur"></div>
                <b class="wsbs" id="wsbs" onclick="onLineAffair();">
							<span style="position: absolute; left: 24px; top: 20%; margin-top: -8px;">
							 <img src="images/dy.png" style="width: 18px;height: 18px;"> 
							</span>
                    <span>网上办事</span>
                </b>
            </div>

        </div>

    </div>
</div>
<%--<div id="QRCodeImg" class="hide">
    <div>
        <img id="imgId" src="" width="200px" height="200px">
        <div style="text-align:center;bottom:10px">使用手机微信扫一扫</div>
    </div>
</div>--%>
<%--<script type="text/javascript">

    function openQRCode() {
        $.post('QRCode!createUUID', function (data) {
            $("#imgId").attr("src", "QRCode!createQRCode");
            scanLayer = layer.open({
                type: 1,
                title: "微信扫一扫",
                closeBtn: 0,
                area: '200px',
                skin: 'layui-layer-white',
                shadeClose: true,
                content: $('#QRCodeImg'),
                success: function (layero, index) {

                    countdown = 25;
                    settime();

                }
            });

        });

    }

    function settime() {
        if (countdown == 5) {
            layer.close(scanLayer);
            clearTimeout(t);

        } else {
            scanFun();
            countdown--;
            t = setTimeout(function () {
                settime()
            }, 1000)
        }

    }

    function scanFun() {
        var affairid = $("#affairid").val();
        //location.href="QRCode!scanQRCode?affairid="+affairid;
        $.post('QRCode!scanQRCode', function (data) {

            var data1 = eval("(" + data + ")");
            if (data1.state = "success") {
                clearTimeout(t);
                location.href = "ssAffairsObjectInfo!findByAffairIdToWeb?affairid=" + affairid;
            }

        });
    }

    /*——————————滑动收起预览——————————*/
    var yulan = document.querySelector(".content_subcontent");
    var mc = new Hammer(yulan);
    mc.on('panstart', subcontent_dragable_start);
    mc.on('pan', subcontent_dragable_move);
    mc.on('panend', subcontent_dragable_end);

    function subcontent_dragable_start(ev) {
        if ($('.fullScreenMode').length <= 0) {
            $('.content_subcontent').removeClass('transition');
        }
    }

    function subcontent_dragable_move(ev) {
        origin_left = $(".content_subcontent").css('left');
        minDragLeft = $(window).width() * .5;
        minDragLeft_percent = '50%';
        dragLeft = ev.deltaX * .8 + minDragLeft;
        /*if($('fullScreenMode').length>0){
            $(".content_subcontent").css('left',minDragLeft_percent);
        }*/
        if (dragLeft <= minDragLeft || $('.fullScreenMode').length > 0 || Math.abs(ev.deltaX) < 100) {
            $(".content_subcontent").css('left', minDragLeft_percent);
            $('.search_content').css('width', minDragLeft_percent);
        } else {
            $(".content_subcontent").css('left', dragLeft);
            $('.search_content').css('width', dragLeft);
        }
    }

    function subcontent_dragable_end(ev) {
        if ($('.fullScreenMode').length <= 0) {
            minDragLeft = $(window).width() * .5;
            minDragLeft_percent = '50%';
            outMinLeft = $(window).width() * .5 + $(window).width() * .8 * .3;
            dragLeft = ev.deltaX * .8 + minDragLeft;
            $('.content_subcontent').addClass('transition');
            if (dragLeft > outMinLeft) {
                $('.show_subcontent').removeClass('show_subcontent');
                $(".content_subcontent").css('left', '100%');
                $('.search_content').css('width', '100%');
                $('.selection').removeClass('selection');
            } else {
                $(".content_subcontent").css('left', minDragLeft_percent);
                $('.search_content').css('width', minDragLeft_percent);
            }
        }
    }

    /* 	var count = 60; //间隔函数，1秒执行
        var curCount;//当前剩余秒数
        var InterValObj;

    function print(){
        curCount=count;
            //alert($('#according').text().replace(new RegExp("<br>","g"),"\n"));
            //console.log($('#according').text().replace(/<br\/>/g, "\n"));
             var affairNamePrint=$('#affairName').text()
            var printAccording=$('#according').html().replace(new RegExp("<br>","g"),"\\n");
            var printCondition=$('#condition').html().replace(new RegExp("<br>","g"),"\\n");
            var printMaterial=$('#material').html().replace(new RegExp("<br>","g"),"\\n");
            var printProcedures=$('#procedures').html().replace(new RegExp("<br>","g"),"\\n");
        //	var printXrndomu=$('#xrndomu').html().replace(new RegExp("<br>","g"),"\\n");
            var printSite=$('#site').html().replace(new RegExp("<br>","g"),"\\n");
            var printTime=$('#time').html().replace(new RegExp("<br>","g"),"\\n");
            //var printInquire=$('#inquire').html().replace(new RegExp("<br>","g"),"\\n");
            var printCharge=$('#charge').html().replace(new RegExp("<br>","g"),"\\n");
            var printSepcialversion=$('#sepcialversion').html().replace(new RegExp("<br>","g"),"\\n");

            try
               {
            // window.external.printWord("{'项目名称':'"+affairNamePrint+"','政策依据':'"+printAccording+"','受理条件':'"+printCondition+"','申请材料':'"+printMaterial+"',','受理地址':'"+printSite+"','办结时限':'"+printTime+"',','收费标准':'"+printCharge+"','特别说明':'"+printSepcialversion+"'}");
            window.external.printWord("{\"办理对象\":\""+printCondition+"\",\"办理依据\":\""+printAccording+"\",\"办理条件\":\""+printCondition+"\",\"提交材料\":\""+printMaterial+"\",\"办理流程\":\""+printProcedures+"\",\"办事窗口\":\""+printSite+"\",\"承诺办理期限\":\""+printTime+"\",\"法定办理期限\":\""+printTime+"\",\"收费依据和标准\":\""+printCharge+"\",\"特别说明\":\""+printSepcialversion+"\"}");
            $("#print").removeAttr('onclick');
                $("#print").html( + curCount + "s 后获取");
             InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器，1秒执行一次
               }
            catch(err)
               {
               alert(err);
               $("#print").removeAttr('onclick');
                $("#print").html( + curCount + "s 后获取");
             InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器，1秒执行一次
               }

            //window.external.printWord("{'项目名称':'"+affairNamePrint+"','政策依据':'"+printAccording+"','受理条件':'"+printCondition+"','申请材料':'"+printMaterial+"',','受理地址':'"+printSite+"','办结时限':'"+printTime+"',','收费标准':'"+printCharge+"','特别说明':'"+printSepcialversion+"'}");

        }
            //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);// 停止计时器
           // $("#print").removeAttr("disabled");// 启用按钮
           // $("#print").removeAttr('onclick');
           $("#print").attr("onclick", "printword()");


            $("#print").html("<span style=\"position: absolute; left: 24px; top: 50%; margin-top: -8px;\" >"+
                             "<img src=\"images/dy.png\" style=\"width: 18px;height: 18px;\">"+
                            "</span>"+
                            "<span>打印</span>");
        }else {

            curCount--;
            $("#print").html(+ curCount + "s 后获取");
        }
    } */

</script>--%>
<script src="scripts/knockout-3.4.0.js" type="text/javascript"></script>
<script src="ctrl/search.js" type="text/javascript"></script>
</body>
</html>
