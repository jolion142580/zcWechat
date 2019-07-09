<%@page import="com.gdyiko.zcwx.po.SsAffairGuide" %>
<%@page import="com.gdyiko.zcwx.service.SsAffairsGuideService" %>
<%@page import="com.gdyiko.tool.BeanUtilEx" %>
<%@page import="com.gdyiko.zcwx.po.SsAffairs" %>
<%@page import="com.gdyiko.zcwx.service.SsAffairsService" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";

    String baseDic = "", baseDicId = "", type = "";
    baseDic = request.getParameter("baseDic");
    baseDicId = request.getParameter("baseDicId");
    type = request.getParameter("baseDicType");
//     System.out.println("sessionID_1:"+session.getId());
    session.removeAttribute("baseDic");
    session.removeAttribute("baseDicId");
    session.removeAttribute("baseDicType");
    session.setAttribute("baseDic", baseDic);
    session.setAttribute("baseDicId", baseDicId);
    session.setAttribute("baseDicType", type);

    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
    SsAffairsService ssAffairsService = (SsAffairsService) ctx.getBean("ssAffairsService");
    SsAffairsGuideService ssAffairsGuideService = (SsAffairsGuideService) ctx.getBean("ssAffairsGuideService");
    List<SsAffairs> ssAffairsList = new ArrayList<SsAffairs>();

    if (type.equals("depart")) {
        SsAffairs ssAffairs2 = new SsAffairs();
        ssAffairs2.setDepartid(baseDicId);
        ssAffairsList = ssAffairsService.findEqualByEntity(ssAffairs2, BeanUtilEx.getNotNullEscapePropertyNames(ssAffairs2));

    }
    if (type.equals("life")) {
        SsAffairs ssAffairs2 = new SsAffairs();
        ssAffairs2.setSortid(baseDicId);
        ssAffairsList = ssAffairsService.findEqualByEntity(ssAffairs2, BeanUtilEx.getNotNullEscapePropertyNames(ssAffairs2));
    }
    SsAffairGuide ssAffairGuide = new SsAffairGuide();
    if (ssAffairsList.size() > 0) {
        SsAffairGuide ssAffairGuide1 = new SsAffairGuide();
        ssAffairGuide1.setAffairid(ssAffairsList.get(0).getAffairid());
        ssAffairGuide = ssAffairsGuideService.findEqualByEntity(ssAffairGuide1, BeanUtilEx.getNotNullEscapePropertyNames(ssAffairGuide1)).get(0);
    }

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
    <script src="scripts/base.js" type="text/javascript"></script>
    <script src="scripts/hammer.min.js" type="text/javascript"></script>
    <script src="js/layer/layer.js" type="text/javascript"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <style type="text/css">

        .header {
            z-index: 1;
            display: block;
            width: 100%;
            height: 56px;
            line-height: 48px;
            background: #0490E4;
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

        /* .area {

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
          .layui-layer-white {
             background: white
         }*/
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

        .content_tip {
            position: absolute;
            right: 0;
            top: 0;
            /*width: 38%*/
            width: 20%;
        }
    </style>
    <%--覆盖样式--%>
    <style type="text/css">
        .content_formlist {
            position: absolute;
            left: 0;
            top: 0;
            /*width: 38%*/
            width: 30%;
        }

        .content_imgcontent {
            position: absolute;
            right: 20%;
            top: 0;
            display: block;
            /*width: 62%;*/
            width: 50%;
            overflow: hidden
        }

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
        function changeResult(affairId) {
            //alert(affairId);
            $.post("<%=basePath%>ssAffairsGuideInfo!findByAffairIdToWeb", {affairid: affairId}, function (result) {
                //alert(result);
                //console.log(result);

                $("#according").html(result.according);
                $("#condition").html(result.condition);
                $("#material").html(result.material);
                $("#procedures").html(result.material);
                $("#xrndomu").html(result.xrndomu);
                $("#site").html(result.site);
                $("#time").html(result.time);
                $("#inquire").html(result.inquire);
                $("#charge").html(result.charge);
                $("#sepcialversion").html(result.sepcialversion);
                $("#procedures").html(result.procedures);

                $("#affairid").val(affairId);

                if (result.isonline == "true") {
                    $("#wsbsDiv").show();
                } else {
                    $("#wsbsDiv").hide();
                }
            }, "json");
        }

    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            var isonline = '<%=ssAffairGuide.getIsonline()%>';
            if (isonline == 'true') {
                $("#wsbsDiv").show();

            }

        })

        function onLineAffair() {
            if (!window.parent.vailFlag()) {
                return;
            }
            var affairid = $("#affairid").val();
            <%--location.href = "<%=basePath%>ssAffairsObjectInfo!findByAffairIdToWeb?affairid=" + affairid;--%>
            window, location = "<%=basePath%>ssAffairsObjectInfo!findByAffairIdToWeb?affairid=" + affairid;
        }
    </script>
</head>


<%-- onload="vailToken();" --%>
<body style="width: 100%;height: 100%;">
<div style="width: 100%;height: 100%;">
    <div class="header titlebar">
        <h2 class="title"><%=baseDic.replace("<br/>", "") %>
        </h2>
        <a href="ssBaseDicInfo!findAllByBaseDicTypeToWeb" class="btn_back link_checkJump"
        ><span class="ic ic_back"></span></a>
    </div>
    <div class="contain show_subcontent show_review" style="height: 90%;">

        <div class="content content_subcontent transition"
             style="touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);width:100%;height: 90%;">
            <div class="content content_form content_formlist" id="content_formlist">
                <%--<div class="title_formNeed" >办事指南</div>--%>
                <div class="scroll_parent" style="height:100%;">
                    <div class="scroll scroll_content auto_plus">
                        <%--<dt class="title_list"></dt>--%>
                        <div data-bind="foreach:angencyData">
                            <%

                                for (int k = 0; k < ssAffairsList.size(); k++) {
                                    SsAffairs ssaffairs1 = (SsAffairs) ssAffairsList.get(k);
                                    if (k == 0) {

                            %>
                            <dd data-bind="click:angencyDetail" class="angency selection"
                                onclick="changeResult(<%=ssaffairs1.getAffairid() %>)">
                                <div class="item_form"><p class="form_name"
                                                          data-bind="text:angencyName"><%=ssaffairs1.getAffairname() %>
                                </p><span class="ic ic_arrowright"></span></div>
                            </dd>
                            <%
                            } else {
                            %>

                            <dd data-bind="click:angencyDetail" class="angency"
                                onclick="changeResult(<%=ssaffairs1.getAffairid() %>)">
                                <div class="item_form"><p class="form_name"
                                                          data-bind="text:angencyName"><%=ssaffairs1.getAffairname() %>
                                </p><span class="ic ic_arrowright"></span></div>
                            </dd>

                            <%
                                    }
                                }
                            %>
                            <dt class="title_list"></dt>
                            <dt class="title_list"></dt>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content content_imgcontent">
                <div id="scrollDiv" class="content_review" style="height: 90%;">
                    <div class="scroll body_review">

                        <input type="hidden" id="affairid" name="affairid" value="<%=ssAffairGuide.getAffairid() %>"/>

                        <p class="pt">一、办理依据：</p>
                        <p id="according"><%=ssAffairGuide.getAccording() == null ? "" : ssAffairGuide.getAccording() %>
                        </p>
                        <p class="pt">二、办理条件：</p>
                        <p id="condition"><%=ssAffairGuide.getCondition() == null ? "" : ssAffairGuide.getCondition() %>
                        </p>
                        <p class="pt">三、申请材料：</p>
                        <p id="material"><%=ssAffairGuide.getMaterial() == null ? "" : ssAffairGuide.getMaterial() %>
                        </p>
                        <p class="pt">四、办理流程：</p>
                        <p id="procedures"><%=ssAffairGuide.getProcedures() == null ? "" : ssAffairGuide.getProcedures() %>
                        </p>
                        <p class="pt">五、办理部门：</p>
                        <p id="xrndomu"><%=ssAffairGuide.getXrndomu() == null ? "" : ssAffairGuide.getXrndomu() %>
                        </p>
                        <p class="pt">六、受理地址：</p>
                        <p id="site"><%=ssAffairGuide.getSite() == null ? "" : ssAffairGuide.getSite() %>
                        </p>
                        <p class="pt">七、办结时限：</p>
                        <p id="time"><%=ssAffairGuide.getTime() == null ? "" : ssAffairGuide.getTime() %>
                        </p>
                        <p class="pt">八、咨询查询：</p>
                        <p id="inquire"><%=ssAffairGuide.getInquire() == null ? "" : ssAffairGuide.getInquire() %>
                        </p>
                        <p class="pt">九、收费依据和标准：</p>
                        <p id="charge"><%=ssAffairGuide.getCharge() == null ? "" : ssAffairGuide.getCharge() %>
                        </p>
                        <p class="pt">十、特别说明：</p>
                        <p id="sepcialversion"><%=ssAffairGuide.getSepcialversion() == null ? "" : ssAffairGuide.getSepcialversion() %>
                        </p>


                    </div>
                </div>
                <%--
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
                           <span class="ic ic_fullScreen_out fullScreen_show"><img
                                   src="images/ic_outfullscreen.png"></span>
                           <span class="status_fullscreen">全屏</span>
                       </b>
                   </div>
                   --%>
                <div class="caution">※该表仅供参考，实际以窗口前台表格为准</div>
                <div id="wsbsDiv" class="wsbsDiv">
                    <div class="wsbs_cur"></div>
                    <b id="wsbs" class="wsbs" onclick="onLineAffair();">
                        <span style="position: absolute; left: 24px; top: 20%; margin-top: -8px;">
							 <img src="images/dy.png" style="width: 18px;height: 18px;"> 
							</span>
                        <span>网上办事</span>
                    </b>
                </div>

            </div>
            <div class="content content_tip">
                <div style="text-align: center;font-size: 16px;">
                    <img src="zcWechatImage/zcWechart.5.jpg">
                    <br>
                    <p>关注上方公众号进行认证绑定;</p>
                    <p>已绑定请忽略</p>
                    <br>
                    <img src="zcWechatImage/bottom.jpg" width="50px" height="50px">
                    <p>微信扫码登录；</p>
                    <br>
                    <img src="zcWechatImage/bottom.jpg" width="50px" height="50px">
                    <p>【网上办事】选择事项填写；</p>
                    <br>
                    <img src="zcWechatImage/bottom.jpg" width="50px" height="50px">
                    <p>电脑或者微信扫码上传资料。</p>
                </div>
            </div>
        </div>

    </div>
</div>

<script src="scripts/knockout-3.4.0.js" type="text/javascript"></script>
<script src="scripts/getUrlParam.js"></script>
<script src="ctrl/service.js" type="text/javascript"></script>
<!-- <script src="ctrl/formviewerModel.js" type="text/javascript"></script> -->
</body>
</html>
