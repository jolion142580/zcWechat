<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎街道行政服务中心</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
 <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/sm.min.css" type="text/css"/>


    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/guide-style.css" type="text/css"/>

    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/infoYz.js"></script>
    <script type="text/javascript" src="js/smscheck.js"></script>
    <script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="js/jquery-weui.js"></script>
    <script type="text/javascript" src="lib/zepto.js"></script>
    <script type="text/javascript" src="js/security/aes.js"></script>
    <script type="text/javascript" src="js/security/security.js"></script>

    <script type="text/javascript" src="js/config.js"></script>

    <style type="text/css">


        .weui-cell__ft {
            text-align: center;
        }

        .weui-cell__ft .board {
            background-color: #efefef;
            border-radius: 10px;
            padding: 10px;
        }

        .main_color {
            color: #FFFFFF;
        }

    </style>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        /*图片上传*/

        html,
        body {
            width: 100%;
            height: 100%;
        }

        .container {
            width: 100%;
            height: 100%;
            overflow: auto;
            clear: both;
        }

        .z_photo {

            padding: 0.1rem;
            overflow: auto;
            clear: both;
            margin: 0.1rem auto;

        }

        .z_photo img {
            width: 3rem;
            height: 3rem;
        }

        .z_addImg {
            float: left;
            margin-right: 0.2rem;
        }

        .z_file {
            width: 3rem;
            height: 3rem;
            background: url(images/weixin/z_add.png) no-repeat;
            background-size: 100% 100%;
            float: left;
            margin-right: 0.2rem;
        }

        .z_file input::-webkit-file-upload-button {
            width: 3rem;
            height: 3rem;
            border: none;
            position: absolute;
            outline: 0;
            opacity: 0;
        }

        .z_file input#file {
            display: block;
            width: auto;
            border: 0;
            vertical-align: middle;
        }
        /*遮罩层*/

        .z_mask {
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, .5);
            position: fixed;
            top: 0;
            left: 0;
            z-index: 999;
            display: none;
        }

        .z_alert {
            width: 3rem;
            height: 2rem;
            border-radius: .2rem;
            background: #fff;
            font-size: .24rem;
            text-align: center;
            position: absolute;
            left: 50%;
            top: 50%;
            margin-left: -1.5rem;
            margin-top: -2rem;
        }

        .z_alert p:nth-child(1) {
            line-height: 1.5rem;
        }

        .z_alert p:nth-child(2) span {
            display: inline-block;
            width: 49%;
            height: .5rem;
            line-height: .5rem;
            float: left;
            border-top: 1px solid #ddd;
        }

        .z_cancel {
            border-right: 1px solid #ddd;
        }

    </style>
</head>
<body>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <a class="button button-link button-nav pull-left" href="javascript:;"
               onclick="javascript:WeixinJSBridge.call('closeWindow');"><span class="icon icon-left main_color"></span></a>
            <h1 class="title main_color">个人信息</h1>
        </header>

        <div class="content">


            <form id="accountform" action="" method="post">

                <input type="hidden" id="id" name="id" value="${userInfo.getId()}">
                <input type="hidden" id="imgfiles" name="imgfiles" value="${userInfo.getImgfiles()}">
                <div class="weui-cells__title">请确认你的个人信息</div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label class="weui-label">办事人姓名</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="name" name="name" value="${userInfo.getName()}" class="weui-input" type="text" placeholder="请输入办事人姓名"/>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label class="weui-label">身份证号码</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="idCard" name="idCard" value="${userInfo.getIdCard()}" onblur="writeToBrithday()" class="weui-input" type="text" placeholder="请输入身份证号码"/>
                        </div>

                    </div>

                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label">手机号码</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="phone" name="phone" class="weui-input" type="text" value="${userInfo.getPhone()}"
                                   placeholder="请输入手机号码"/>
                        </div>
                    </div>

                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label">性别</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input name="sex" type="radio" ${userInfo.sex=="男"?"checked":""} value="男"/>男
                            <input name="sex" type="radio"  ${userInfo.sex=="女"?"checked":""} value="女"/>女
                        </div>
                    </div>

                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label">出生日期</label></div>
                        <div class="weui-cell__bd">
                            <input class="weui-input" type="date" value="${userInfo.getBrithday()}" id="brithday" name="brithday">
                        </div>
                    </div>


                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label">联系地址</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="address" name="address" class="weui-input" type="text" value="${userInfo.getAddress()}"
                                   placeholder="请输入联系地址"/>
                        </div>
                    </div>
                    <div class="weui-cell weui-cell_vcode">
                        <div class="weui-cell__hd">
                            <label class="weui-label">验证码</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="random_num" name="random_num" class="weui-input" type="number"
                                   placeholder="请输入验证码"/>
                        </div>
                        <div class="weui-cell__ft">
                            <img id="randomming" class="weui-vcode-img"
                                 src="" onclick="reloadCode()" width="100%" height="40"
                                 title="点击更换验证码" alt="点击更换验证码"/>

                        </div>

                    </div>
                    <div class="weui-cell weui-cell_vcode">
                        <div class="weui-cell__hd">
                            <label class="weui-label">短信验证码</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="random_sms" name="random_sms" class="weui-input" type="number"
                                   placeholder="请输入验证码"/>

                        </div>


                        <div class="weui-cell__ft">
                            <input type="button" id="btnSendCode" name="btnSendCode" class="weui-btn weui-btn_primary"
                                   width="50%" style="background-color: #911edb" value="获取验证码" onclick="sendMessage()"/>
                        </div>

                    </div>
                </div>


                <div class="weui-cells__title">上传身份信息</div>
                <div class="weui-gallery" id="gallery">
                    <span class="weui-gallery__img" id="galleryImg"></span>
                    <div class="weui-gallery__opr">
                        <a href="javascript:" class="weui-gallery__del">
                            <i class="weui-icon-delete weui-icon_gallery-delete"></i>
                        </a>
                    </div>
                </div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
                            <div class="weui-uploader">
                                <div class="weui-uploader__hd">
                                    <p class="weui-uploader__title">身份证正反面</p>
                                    <div class="weui-uploader__info">0/2</div>
                                </div>
                                <div class="weui-uploader__bd">
                                    <ul class="weui-uploader__files" id="uploaderFiles">
                                    </ul>
                                    <div class="z_photo">
                                        <div class="z_file">
                                            <input type="file" name="file" id="file" value="" accept="image/*" multiple onchange="imgChange('z_photo','z_file');" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="weui-btn-area" style="margin-bottom: 30px;">
                    <div style="color:purple;font-size: 12px;text-align: center;">
                        温馨提示：香港居民身份证号码时，不需填添加括号，如M123456（7）则填写为M1234567。
                    </div>


                    <div style="color:purple;font-size: 14px;text-align: center;">

                        <label style="display: inline-block; margin-right: 0px; padding-right: 0px;" for="userProtocol"
                               class="weui-agree">
                            <input id="userProtocol" type="checkbox" class="weui-agree__checkbox">
                            <span class="weui-agree__text">阅读并同意</span>
                        </label>
                        <a href="userProtocol.jsp">《用户协议》</a>
                    </div>


                    <a class="weui-btn weui-btn_primary" href="javascript:" onClick="commit();"
                       id="showTooltips" style="background-color: #911edb">提交并保存</a>
                </div>

                <div align="center"
                     style="line-height:25px;width:100%;position: fixed;bottom: 0;z-index: 600;background-color: #efeff4;">
                    <span style="font-size:12px;">张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
                </div>

            </form>
            <input type="hidden" id = "imgArr">

            <div id="previewImg" class="weui-gallery" style="display: none">
                <span class="weui-gallery__img" style="background-image: url(112);"></span>
                <div class="weui-gallery__opr">
                    <a href="javascript:" class="weui-gallery__del">
                        <i class="weui-icon-delete weui-icon_gallery-delete"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8" charset="gb2312"></script>


<script type="text/javascript">
    var maxCount = 2;
    var count = 0;
    function commit() {

        if ($("#random_sms").val() == "") {
            alert("短信验证码不能为空");
            $("#random_sms").focus();
            return;
        }
        if (!$("#userProtocol").is(":checked")) {
            alert("请勾选用户协议");
            return false;
        }
        var check = yz();
        if (check == true) {
            var id = $('#id').val();
            var url = "";
            if (id==""){
                url = "ssUserInfoNoLogin!save";
            }else {
                url = "ssUserInfo!save";
            }

            $.post(url, {
                "random_num": $("#random_num").val(),
                "random_sms": $("#random_sms").val(),
                "id":$("#id").val(),
                "name": $("#name").val(),
                "idCard": Encrypt($("#idCard").val()),
                "phone": Encrypt($("#phone").val()),
                "sex": $("input[name='sex']:checked").val(),
                "brithday": $("#brithday").val(),
                "address": $("#address").val(),
                "imgfiles":$("#imgfiles").val()
            }, function (data) {
                console.log(data);
                if (data.flag == 1) {
                   // alert("绑定成功！");
                    $.alert("绑定成功","提示");
                    window.location.href = "index.jsp";
                    //WeixinJSBridge.call('closeWindow');
                }
                if (data.flag == "fail") {
                    // alert(eval("(" + data + ")").message);
                    $.alert(data.message,"提示");
                }


            },"json");
        } else {
            reloadCode();
        }
    }

    function reloadCode() {
        var image = document.getElementById("randomming");
        if (null != image) {
            image.src = "image.jsp?" + Math.random();
        }

    }

    //存放文件数组
    var imgArr = [];
    function loadData() {

        $.showLoading("数据加载中...");
        var file = document.getElementById("file");
        //存放图片的父级元素 'z_photo','z_file'
        var imgContainer = document.getElementsByClassName("z_photo")[0];
        //初始化数组
        imgArr = [];
        $("#imgArr").val(imgArr);
        var id = "${userInfo.getId()}";
        if(id==""){
            id = "null";
        }
        var simg = $("#imgfiles").val();
        var ss = [];
        if(simg!=""){
            ss = simg.split(",");
        }


        $.post('WeChatUpload!findByOpenId', {openid: id, remark: '身份证正反面'}, function (data) {
            if (data.length > 0) {
                var d = eval("(" + data + ")")
                $.each(d, function (k, v) {
                    //把获得得图片路径存放到数组
                    imgArr.push("WeChatUpload!IoReadImage?myid="+v.id);
                    ss.push(v.id);
                    //把数组存放到input
                    $("#imgArr").val(imgArr);
                    $(".weui-uploader__info").text(imgArr.length + "/" + maxCount);

                    var img = document.createElement("img");
                    img.setAttribute("src", "WeChatUpload!IoReadImage?myid="+v.id);
                    var imgAdd = document.createElement("div");
                    imgAdd.setAttribute("class", "z_addImg");
                    imgAdd.appendChild(img);
                    imgContainer.appendChild(imgAdd);

                    if ((k+1)==d.length){
                        $.hideLoading();
                    }
                });
                $("#imgfiles").val(ss);
            }else{
                $.hideLoading();
            }
        });
    }


    function imgChange(obj1, obj2) {
        //获取点击的文本框
        var file = document.getElementById("file");
        //存放图片的父级元素
        var imgContainer = document.getElementsByClassName(obj1)[0];
        //获取的图片文件
        var fileList = file.files;
        //文本框的父级元素
        var input = document.getElementsByClassName(obj2)[0];

        var s = $("#imgArr").val();
        if (s==""){

        } else{
            imgArr = s.split(",");
        }
        console.log(imgArr);
        //遍历获取到得图片文件，验证多选文件中得图片数量
        if (fileList.length>maxCount){
            $.alert("只能上传"+maxCount+"张图片","警告");
            return;
        }
        //验证已经上传得文件数量
        if (imgArr.length>maxCount){
            $.alert("只能上传"+maxCount+"张图片","警告");
            return;
        }
        //验证已上传文件及选择文件总和
        if ((fileList.length+imgArr.length)>maxCount){
            $.alert("只能上传"+maxCount+"张图片","警告");
            return;
        }

        for (var i = 0; i < fileList.length; i++) {
          //  count++;
            $.showLoading("图片上传中...");
            syncUpload(fileList[i],imgArr,fileList.length,i,imgContainer);
        };
    };

    //上传图片
    var syncUpload = function (file,imgArr,filecount,i,imgContainer) {
        var formData = new FormData();
        var id = "${userInfo.getId()}";
        console.log(id);
        formData.append("file",file);
        formData.append("openid",id);
        formData.append("remark",'身份证正反面');

        $.ajax({
            url:"WeChatUpload!savePicture",
            type : 'POST',
            data : formData,
            processData: false,
            contentType: false,
            dataType:"json",
            success: function (data) {
                // 将文件url放到隐藏的输入框里
                var imgUrl = data.filepath;
                var s =imgUrl.split("?myid=");

                var simg = $("#imgfiles").val();
                var ss = [];
                if(simg!=""){
                    ss = simg.split(",");
                }
                ss.push(s[1]);
                $("#imgfiles").val(ss);

                imgArr.push(imgUrl);
                $("#imgArr").val(imgArr);


                $(".weui-uploader__info").text(imgArr.length + "/" + maxCount);
                var img = document.createElement("img");
                img.setAttribute("src", imgUrl);
                var imgAdd = document.createElement("div");
                imgAdd.setAttribute("class", "z_addImg");
                imgAdd.appendChild(img);
                imgContainer.appendChild(imgAdd);
                 if (i==filecount-1){
                     $.hideLoading();
                 }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                // 失败
                console.log("error");
                $.hideLoading();
            }

        });

    }

    //点击获得图片路径
    var imgpath = "";
    $(function () {
        //加载验证码
        reloadCode();
        //加载图片
        loadData();
        //放大预览
        $(".z_photo").on('click', 'img',function(){
            var path =$(this).attr("src");
            imgpath = path;
            $("#previewImg").css("display","block");
            $('#previewImg .weui-gallery__img').css("background-image","url("+path+")");
        });
        //退出预览
        $(document).on('click', '.weui-gallery__img',function(){
            $("#previewImg").css("display","none");
            $('#previewImg .weui-gallery__img').css("background-image","");
            imgpath = "";
        });
        //点击删除
        $("#previewImg").on("click",".weui-gallery__del",function () {
            var s = imgpath.split("?myid=");
            if(s[1]!=null){
                $.confirm({
                    title: '提醒',
                    text: '是否删除图片',
                    onOK: function () {
                        //点击确认
                        $.ajax({
                            url:"WeChatUpload!remove?myid="+s[1],
                            type : 'get',
                            processData: false,
                            contentType: false,
                            dataType:"json",
                            success: function (data) {
                                console.log(data);
                                if(data.flag=="1"){
                                    $(".z_addImg").remove();
                                    $("#imgfiles").val("");
                                    loadData();
                                    $(".weui-uploader__info").text(imgArr.length + "/" + maxCount);
                                    $("#previewImg").css("display","none");
                                    $('#previewImg .weui-gallery__img').css("background-image","");
                                    imgpath = "";
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                // 失败
                                console.log("error");
                            }
                        });
                    }
                });
            }
        });

    });



    function writeToBrithday(){
        var idCard = $("#idCard").val();
        if(idCard.length<17){
            return ;
        }
        var brith = idCard.substring(6,10)+"-"+idCard.substring(10,12)+"-"+idCard.substring(12,14);
        $("#brithday").val(brith)
    }

    function getCookie(name)
    {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

        if(arr=document.cookie.match(reg))

            return unescape(arr[2]);
        else
            return null;
    }

    //px转换为rem
    (function(doc, win) {
        var docEl = doc.documentElement,
            resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
            recalc = function() {
                var clientWidth = docEl.clientWidth;
                if (!clientWidth) return;
                if (clientWidth >= 640) {
                    docEl.style.fontSize = '100px';
                } else {
                    docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
                }
            };

        if (!doc.addEventListener) return;
        win.addEventListener(resizeEvt, recalc, false);
        doc.addEventListener('DOMContentLoaded', recalc, false);
    })(document, window);
</script>

</html>
