﻿function IsMobile(s) {

    var field = /^0?(13|14|15|17|18|19)[0-9]{9}$/g;
    var flg = false;
    var field13 = /^13\d{9}$/g;
    var field15 = /^15[0,2,1,3,5,6,7,8,9]\d{8}$/g;
    var field16 = /^17[0,2,1,3,5,6,7,8,9]\d{8}$/g;
    var field18 = /^18[6,7,8,9,0]\d{8}$/g;
    if ((field13.exec(s)) || (field15.exec(s)) || (field18.exec(s)) || field16.exec(s)) {
        flg = true;
    }
    else {
        flg = false;
    }
    return flg;

}

//新身份证验证
function checkIdcard(idcard) {
    var Errors = new Array(0, 1, 2, 3, 4);
    var area = {
        11: "北京",
        12: "天津",
        13: "河北",
        14: "山西",
        15: "内蒙古",
        21: "辽宁",
        22: "吉林",
        23: "黑龙江",
        31: "上海",
        32: "江苏",
        33: "浙江",
        34: "安徽",
        35: "福建",
        36: "江西",
        37: "山东",
        41: "河南",
        42: "湖北",
        43: "湖南",
        44: "广东",
        45: "广西",
        46: "海南",
        50: "重庆",
        51: "四川",
        52: "贵州",
        53: "云南",
        54: "西藏",
        61: "陕西",
        62: "甘肃",
        63: "青海",
        64: "宁夏",
        65: "新疆",
        71: "台湾",
        81: "香港",
        82: "澳门",
        91: "国外"
    }
    var idcard, Y, JYM;
    var S, M;
    var idcard_array = new Array();
    idcard_array = idcard.split("");
    if (area[parseInt(idcard.substr(0, 2))] == null) return Errors[4];
    switch (idcard.length) {
        case 15:
            if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 )) {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
            }
            else {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
            }
            if (ereg.test(idcard))
                return Errors[0];
            else
                return Errors[2];
            break;
        case 18:
            if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0 )) {
                //  ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
                ereg = /^[1-9][0-9]{5}[1-2][0-9]{3}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
            }
            else {
                // ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
                ereg = /^[1-9][0-9]{5}[1-2][0-9]{3}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
            }
            if (ereg.test(idcard)) {  //
                S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7 + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9 + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10 + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5 + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8 + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4 + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2 + parseInt(idcard_array[7]) * 1 + parseInt(idcard_array[8]) * 6 + parseInt(idcard_array[9]) * 3;
                Y = S % 11;
                M = "F";
                JYM = "10X98765432";
                M = JYM.substr(Y, 1);
                if (M == idcard_array[17])
                    return Errors[0];
                else
                    return Errors[3];
            }
            else
                return Errors[2];
            break;
        default:
            return Errors[1];
            break;
    }
}

function IsNoid(e) {
    var flag = 0;
    var v15 = /^\d\d\d\d\d\d\d\d\d\d\d\d\d\d\d$/;
    var v18 = /^\d\d\d\d\d\d\d\d\d\d\d\d\d\d\d\d\d[0-9|Xx]$/;
    var area = {
        11: "北京",
        12: "天津",
        13: "河北",
        14: "山西",
        15: "内蒙古",
        21: "辽宁",
        22: "吉林",
        23: "黑龙江",
        31: "上海",
        32: "江苏",
        33: "浙江",
        34: "安徽",
        35: "福建",
        36: "江西",
        37: "山东",
        41: "河南",
        42: "湖北",
        43: "湖南",
        44: "广东",
        45: "广西",
        46: "海南",
        50: "重庆",
        51: "四川",
        52: "贵州",
        53: "云南",
        54: "西藏",
        61: "陕西",
        62: "甘肃",
        63: "青海",
        64: "宁夏",
        65: "xingjiang",
        71: "台湾",
        81: "香港",
        82: "澳门",
        91: "国外"
    };
    if (e.length == 10 || e.length == 8) {
        var check = IsHKID(e);
        if (check == false) {
            flag = 3;
        }
    } else if (area[parseInt(e.substr(0, 2))] == null) {
        //alert("diqufeifa");
        flag = 5;
    } else if (e.length == 15) {
        if (!e.match(v15)) {
            alert(e.length);
            flag = 1;
            //return false;
        }
        var year = e.substring(6, 8);
        var month = e.substring(8, 10);
        var day = e.substring(10, 12);
        var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
        // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
        if (temp_date.getYear() != parseFloat(year)
            || temp_date.getMonth() != parseFloat(month) - 1
            || temp_date.getDate() != parseFloat(day)) {
            //alert("youwentia!!");
            flag = 4;
            //return false;
        }
    } else if (e.length == 18) {
        if (!e.match(v18)) {
            //alert("18位身份证号码应为数字,最后一位可为x");
            flag = 2;
            //return false;
        }
        var year = e.substring(6, 10);
        var month = e.substring(10, 12);
        var day = e.substring(12, 14);
        var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
        // 这里用getFullYear()获取年份，避免千年虫问题
        if (temp_date.getFullYear() != parseFloat(year)
            || temp_date.getMonth() != parseFloat(month) - 1
            || temp_date.getDate() != parseFloat(day)) {
            //alert("18weiyouwentia!!");
            flag = 4;
            //return false;   
        }
    }
    /* else if(e.length==10||e.length==8){
         var check =IsHKID(e);
         if(check==false){
             flag = 3;
         }
     }*/
    else {
        //alert("身份证号码应为15或18位");
        flag = 3;
        //return false;
    }
    return flag;
}

function IsHKID(str) {

    var strValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    if (str.length < 8)

        return false;

    if (str.charAt(str.length - 3) == '(' && str.charAt(str.length - 1) == ')')

        str = str.substring(0, str.length - 3) + str.charAt(str.length - 2);

    str = str.toUpperCase();

    var hkidPat = /^([A-Z]{1,2})([0-9]{6})([A0-9])$/;

    var matchArray = str.match(hkidPat);

    if (matchArray == null)

        return false;

    // the character part, numeric part and check digit part

    var charPart = matchArray[1];

    var numPart = matchArray[2];

    var checkDigit = matchArray[3];

    // calculate the checksum for character part

    var checkSum = 0;

    if (charPart.length == 2) {

        checkSum += 9 * (10 + strValidChars.indexOf(charPart.charAt(0)));

        checkSum += 8 * (10 + strValidChars.indexOf(charPart.charAt(1)));

    } else {

        checkSum += 9 * 36;

        checkSum += 8 * (10 + strValidChars.indexOf(charPart));

    }

    // calculate the checksum for numeric part

    for (var i = 0, j = 7; i < numPart.length; i++, j--)

        checkSum += j * numPart.charAt(i);

    // verify the check digit

    var remaining = checkSum % 11;

    var verify = remaining == 0 ? 0 : 11 - remaining;

    return verify == checkDigit || (verify == 10 && checkDigit == 'A');

}

function yz() {
    var sex = $("input[name='sex']:checked").val();
    if ($("#name").val() == "") {
        alert("姓名不能为空");
        $("#name").focus();
        return false;
    }
    else if (sex == undefined || sex == "") {
        alert("请选择性别");
        return false;
    }
    else if ($("#brithday").val() == "") {
        alert("请选择出生日期");
        return false;
    }

    else if ($("#name").val().match(/\d+/g)) {
        alert("姓名不能出现数字");
        $("#name").focus();
        return false;
    } else if ($("#certificate").val() == "") {
        alert("证件号码不能为空");
        $("#certificate").focus();
        return false;
    } else if ($("#stagedate").val() == "请选择") {
        alert("请选择时间");
        return false;
    } else if ($("#stagedatetime").val() == "请选择") {
        alert("请选择时间段");
        return false;
    } else {
        if ($("#phone").val() == "") {
            alert("手机号不能为空");
            $("#mobile").focus();
            return false;
        }
        if (!IsMobile($("#phone").val())) {
            alert("请输入正确的手机号");
            $("#mobile").focus();
            return false;
        }
        if ($("#idCard").val()) {
            //验证通过!","身份证号码位数不对!","身份证号码出生日期超出范围或含有非法字符!","身份证号码校验错误!","身份证地区非法!
            var flag = checkIdcard($("#idCard").val());
            if (flag == 1) {
                alert("身份证号码位数不对");
                $("#idCard").focus();
                return false;
            }
            if (flag == 2) {
                alert("身份证号码出生日期超出范围或含有非法字符");
                $("#idCard").focus();
                return false;
            }
            if (flag == 3) {
                alert("身份证号码校验错误!");
                $("#idCard").focus();
                return false;
            }
            if (flag == 4) {
                alert("身份证地区非法!");
                $("#idCard").focus();
                return false;
            }
            if (flag == 5) {
                alert("身份证号码地区非法");
                $("#idCard").focus();
                return false;
            }

        }
        if ($("#random_num").val() == "") {
            alert("验证码不能为空");
            $("#random_num").focus();
            return false;
        }

        if ($.trim($("#address").val()) == "") {
            alert("请填写地址");
            $("#address").focus();
            return false;
        }

        console.log();
        var s = $("#imgArr").val();
        var im =  s.split(",");
        if (im.length < maxCount) {

            alert("请上传身份证正反面");
            return false;
        }
    }
    return true;
}
