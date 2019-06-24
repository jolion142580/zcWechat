<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
    <link rel="stylesheet" href="css/guide-style.css" type="text/css"/>

    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>

    <link rel="stylesheet" href="yms/window/weiXinAppointment/css/common.css" type="text/css"></link>
    <script type="text/javascript" src="yms/window/weiXinAppointment/js/common.js"></script>

    <style type="text/css">
        .main_color {
            color: #35af98;
        }

        .weui-grid__label {
            white-space: normal;
        }

        .weui-grid {
            height: 5rem;
        }
    </style>


    <script type="text/javascript">
        var deptId_ = '';
        var areaId_ = '';


        //控制页面 matterType 1法人，3 人事
        function controlPage(listDivId, matterType, isShowBar, isShowHeader) {
            $('[class *= "mark_div"]').hide();
            $('[class *= "li_page_tab"]').attr('class', 'left li_page_tab');
            $('#' + listDivId + '_li').attr('class', 'left cur li_page_tab');

            $('#' + listDivId).show();
            $('#matterType').val(matterType);
            $('#matterName').val('');
            if (isShowBar == 1) {
                $('#search_bar').show();
            } else {
                $('#search_bar').hide();
            }

            if (isShowHeader == 1) {
                $('#header_').show();
            } else {
                $('#header_').hide();
            }
        }


        function resetList() {

            $.post("ssBaseDicInfo!findAllByLeftItem", function (data) {
                //alert(data);
                $("#LifeItem").empty();

                var content = "";
                $.each(data, function (k, v) {
                    var cname = v.cname;
                    if (v.cname.indexOf("<") != -1) {
                        var suffix = v.cname.substr(0, v.cname.lastIndexOf("<"));
                        var prefix = v.cname.substr(v.cname.lastIndexOf(">"), v.cname.length);
                        cname = suffix + "<br\>" + prefix;
                        console.log("有值");
                    }

                    content += "<a href=\"ssAffairsInfo!findByItem?sortid=" + v.baseDicId + "\" class=\"weui-grid js_grid\"><div class=\"weui-grid__icon\" style=\"width:45px;height:45px\"><img src=\"" + v.iconPath + "\" alt=\"\" ></div><p class=\"weui-grid__label\">" + cname + "</p></a>";

                });
                $("#LifeItem").append(content);
            }, "json");

        }


    </script>

</head>


<body>

<div class="header" id="header_">
    <div class="special-tabs clear">
        <div class="tabs left" style='width:100%'>
            <div class="clear">
                <ul>
                    <li class="left cur li_page_tab" style='width:25%' id="personal_list_div_li"><a
                            onclick="controlPage('personal_list_div',3,1,1);">职能部门事项</a></li>
                    <li class="left li_page_tab" style='width:25%' id="legalPerson_list_div_li"><a
                            onclick="controlPage('legalPerson_list_div',1,1,1);resetList()">人生事项</a></li>
                    <!-- <li class="left li_page_tab" style='width:25%' id="common_list_div_li"><a onclick="controlPage('common_list_div','',0,1);commonEvent()">常用事项</a></li>
                    <li class="left li_page_tab" style='width:25%' id="his_list_div_li"><a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/affairhis!findbyopenid&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect">历史查询</a></li> -->
                </ul>
            </div>
        </div>
        <!-- <a onclick="controlPage('area_list_div','',0,0)" class="ico-area left" id="divName_box">常用事项</a> -->
    </div>

    <div class="search-bar" id="search_bar">
        <a href="ssAffairsInfo!findByBaseDicId?departid=all">
            <input type="search" class="input-txt" placeholder="搜索" id="keyword" value="">
        </a>
        <input type="hidden" id="old_keyword">
    </div>

</div>

<!-- 部门事项 -->
<div class="content-3 mark_div" id="personal_list_div" style="display: block;">
    <div class="lobby">

        <div class="weui-grids" style="background-color: white;">
            <form name="searchForm" method="post" action="">
                <s:iterator value="ssBaseDicsList">
                    <%-- <a href="ssAffairsGuideInfo!findByAffairCodeId?baseDicId=<s:property value="cname" />" class="weui-grid js_grid"> --%>
                    <a href="ssAffairsInfo!findByDepartId?departid=<s:property value="baseDicId" />"
                       class="weui-grid js_grid">
                        <div class="weui-grid__icon">
                            <img src="<s:property value="iconPath" />" alt=""/>
                        </div>
                        <font class="weui-grid__label">
                            <s:property escape="false" value="cname"/>
                        </font>
                    </a>
                </s:iterator>
            </form>
        </div>


    </div>
</div>

<!-- 人生事项 -->
<div class="content-3 mark_div" id="legalPerson_list_div" style="display: none;">
    <input type="hidden" id="page" value="1">
    <div class="lobby">

        <div class="weui-grids" style="background-color: white;" id="LifeItem">

            <!--  <a href="ssAffairsInfo!findByBaseDicId?baseDicId=13" class="weui-grid js_grid">
             <div class="weui-grid__icon" style="width:45px;height:45px">
            <img src="icon/icoa01.png" alt="">
            </div>
           <p class="weui-grid__label"> 区卫生和计划生育局 </p>
            </a> -->

        </div>


    </div>
</div>
<marquee id=go1 onMouseOver=go1.stop() onMouseOut=go1.start() scrollamount=2 scrolldelay=100
         direction=left>电脑端网址: <a href="http://zcxzfwzx.chancheng.gov.cn/">http://zcxzfwzx.chancheng.gov.cn/</a>
</marquee>
<div class=" foot fixPosition width100 bottomPosition iff">
    <div align="center" style="line-height:25px;">
        <span style='font-size:12px;'>张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
    </div>
</div>


</body>
</html>
