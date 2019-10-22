<%@ page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    //获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    String currCode = request.getParameter("currCode");
    String userName = request.getParameter("username");
//     userName = URLDecoder.decode(userName,"UTF-8");
    System.out.println(userName + "==================================");
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
    <style type="text/css">
        .main_color {
            color: #FFFFFF;
        }

        .weui-vcode-btn {
            margin: 0px;
        }

        .weui-search-bar__box .weui-icon-search {
            line-height: 44px;
        }

        .weui-search-bar__form {
            background-color: #FFF;
        }

        .weui-search-bar__form:after {
            border-radius: 0px;
        }
    </style>
</head>
<body ontouchstart>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <!-- <img src="images/bar_nav_left_brown.png"
                style="position: absolute;left: -0.5rem;height: 2.2rem;" />  -->
            <a class="button button-link button-nav pull-left"
               href="javascript:history.back(-1);return false;">
                <div class="icon icon-left main_color"></div>
            </a>
            <h1 class="title main_color">跟单追踪结果</h1>
        </header>
        <!-- <div class="header-bar" style="background-color: #f27b03"></div> -->
        <div class="content" id="contant">
            <form id="accountform" action="" method="post"
                  style="border-right:1px solid #cecece; border-top:1px solid #cecece; border-bottom:1px solid #cecece; padding-bottom:30px; margin:15px; border-radius:10px; background:#FFF;display:none"
                  class="fonts">
                <input type="hidden" name="p_backUrl" value="">
                <input type="hidden" name="smstoken" value="dc8a4cae19ec498eb2b8691d896b7a78">
                <div id="fieldsetContent" style="display:none">
                    <div class="weui-cells weui-cells_form">

                        <div class="weui-cell">
                            <div class="weui-cell__bd">
                                <label class="weui-label">申请人</label>
                            </div>
                            <div class="weui-cell__bd">
                                <label class="weui-label" id="personName"></label>
                            </div>
                        </div>

                        <div class="weui-cell">
                            <div class="weui-cell__bd">
                                <label class="weui-label">事项名称</label>
                            </div>
                            <div class="weui-cell__bd">
                                <label class="weui-label" id="affairName"></label>
                            </div>
                        </div>

                        <div class="weui-cell">
                            <div class="weui-cell__bd">
                                <label class="weui-label">受理编号</label>
                            </div>
                            <div class="weui-cell__bd">
                                <label class="weui-label" id="currCode"></label>
                            </div>
                        </div>


                        <div class="weui-cell">
                            <div class="weui-cell__bd">
                                <label class="weui-label">办件状态</label>
                            </div>
                            <div class="weui-cell__bd">
                                <label class="weui-label" id="statusDesc"></label>
                            </div>
                        </div>


                        <div class="weui-cell">
                            <div class="weui-cell__bd">
                                <label class="weui-label">审批过程</label>
                            </div>
                        </div>
                    </div>

                    <div class="weui-cells weui-cells_form">
                        <div id="step" class="weui-cell">
                            <div class="weui-cell__bd">流程名称</div>
                            <div class="weui-cell__bd">流程结果</div>
                            <div class="weui-cell__bd">操作时间</div>
                            <div class="weui-cell__bd">部门</div>
                        </div>
                    </div>
                    <%--<div class="weui-cells__title">提示：资金到账以银行划账为准。</div>--%>
                </div>
            </form>
            <div class=" foot fixPosition width100 bottomPosition iff">
                <div align="center" style="line-height:25px;">
                    <span style='font-size:12px;'>版权所有：张槎街道行政服务中心&nbsp;&nbsp;技术支持：南邮英科</span>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="lib/jquery-2.1.4.js"></script>
<script src="lib/fastclick.js"></script>
<script>
    $(function () {
        FastClick.attach(document.body);
    });
</script>
<script src="js/jquery-weui.js"></script>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
    $(function () {

        $.post("<%=basePath%>affairProgress!findByAffairCode", {
                currCode: "<%=currCode%>",
                userName: "<%=userName%>"
            },

            function (result) {
                if (result.status == "1") {
                    $("#fieldsetContent").css('display',
                        'block');
                    $("#accountform").css('display', 'block');

                    $("#personName")
                        .html(
                            isEqualsNull(result.data.user_name));
                    $("#currCode")
                        .html(
                            isEqualsNull(result.data.cur_affaircode));
                    $("#statusDesc")
                        .html(isEqualsNull(result.data.result));
                    $("#affairName")
                        .html(isEqualsNull(result.data.affair_name));


                    /*  if (result.data[0].mail_status == null || result.data[0].mail_status == '') {
                          $("#mailStatus").css('display', 'none');
                      }*/
                    /* if (result.data[0].mail_status == '0') {
                         $("#mailStatus").html("没状态");
                     }
                     if (result.data[0].mail_status == '1') {
                         $("#mailStatus").html("待寄");
                     }
                     if (result.data[0].mail_status == '2') {
                         $("#mailStatus").html("已寄出");
                     }
                     $("#mailNum")
                         .html(isEqualsNull(result.data[0].mail_num));*/


                    /* if (result.data[0].status == 0)
                         $("#isEnd").html("已完成");
                     else
                         $("#isEnd").html("未完成");
                     $("#nextFlow").html(
                         isEqualsNull(result.data[0].status_desc));*/

                    var steps = "";
                    $.each(result.data.auditrecords, function (k, v) {
                        /* alert(v);
                         alert(v.flow_name);*/
                        //alert(v.RECORDTIME);
                        steps += "<div class=\"weui-cell\">";
                        steps += "<div class=\"weui-cell__bd\">" + v.flow_name
                            + "</div>";
                        steps += "<div class=\"weui-cell__bd\">" + v.flow_result
                            + "</div>";
                        steps += "<div class=\"weui-cell__bd\">" + v.operatortime
                            + "</div>";
                        steps += "<div class=\"weui-cell__bd\">" + isEqualsNull(v.depart_name)
                            + "</div>";
                        steps += "</div>";
                    });

                    $("#step").after(steps);
                }

//                }
                if (result.status == "0") {
                    $("#fieldsetContent").empty();
                    $("#fieldsetContent")
                        .css('display', 'none');
                    $("#accountform")
                        .css('display', 'block');
                    var t = "<div align=\"center\" style=\" margin:15px; padding:15px 0 200px 0; background:#fff;\">"
                        + result.msg + "</div>";
                    $("#accountform").append(t);
                }
            }

            ,
            "json"
        )
        ;

    });

    function isEqualsNull(content) {
        return content == null ? "" : content;
    }
</script>

</body>
</html>
