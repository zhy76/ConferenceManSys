var hotel ={};
var token;
var $hotelId;
$(function () {

    /*获取token*/
    token = localStorage.getItem("conNCU");//?????
    console.log(typeof (token));
    console.log(token);
    if (token == null || token == "null") {
        console.log("no token");
    } else {
        $hotelId = parseJwt(token).hotelId;/*获取用户信息*/
        console.log(parseJwt(token));
        console.log($hotelId);
    }
    /*判断是否为未登录用户*/

    if (token == null || token == "null" || typeof ($hotelId) == "undefined" || $hotelId == undefined) {//未登录
        console.log("未登录");
        localStorage.setItem("conNCU", null);
        alert("请先登录！");
        window.location.href = "登录.html";
    }

    $("#get-hotel a").click(function () {
            console.log("信息获取");
            showHotelInfo();
    })
    /*点击 退出登录 按钮*/
    $(".login-out").click(function () {
        clearHotelInfo();
        //localStorage.clear();
        localStorage.setItem("conNCU", null);
        alert("退出成功");
        window.location.href = "登录.html";
    })

});
function getHotelInfo($hotelId) {
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/hotel/getHotelInfo",
        type: "post",
        dataType: "json",
        data: {
            'hotelId': $hotelId,
        },
        success: function (data) {
            console.log(data);
            // hotel.hotelId = data.getHotelInfo.hotelId;
            // hotel.hotelInfo = data.getHotelInfo.hotelInfo;
            // hotel.hotelLocation = data.getHotelInfo.hotelLocation;
            // hotel.hotelName = data.getHotelInfo.hotelName;
            // hotel.hotelPass = data.getHotelInfo.hotelPass;
            // hotel.hotelPhone = data.getHotelInfo.hotelPhone;
            if (data["code"] === 200) {
                hotel = data["data"]["getHotelInfo"];
                console.log(hotel);
            } else {
                alert("获取信息失败！");
            }
        },
        error: function () {
            alert("获取信息失败！");
        },
    });
}

function  showHotelInfo(){
    getHotelInfo($hotelId);
    let $html=" <div class=\"page-wrapper\">\n"+"<div class=\"container-fluid\">\n" +
        "                <!-- Row -->\n" +
        "                <div class=\"row\">\n" +
        "                    <!-- Column -->\n" +
        "                    <div class=\"col-sm-11\">\n" +
        "                        <div class=\"card\">\n" +
        "                            <!-- Nav tabs -->\n" +
        "                            <ul class=\"nav nav-tabs profile-tab\" role=\"tablist\">\n" +
        "                                <li class=\"nav-item\"> <a class=\"nav-link\" data-toggle=\"tab\" href=\"#酒店信息\"\n" +
        "                                        role=\"tab\">酒店信息</a> </li>\n" +
        "                            </ul>\n" +
        "                            <!-- Tab panes -->\n" +
        "                            <div class=\"tab-content\">\n" +
        "                                <div class=\"tab-pane\" id=\"酒店信息\" role=\"tabpanel\">\n" +
        "                                    <div class=\"card-body\">\n" +
        "                                        <form class=\"form-horizontal form-material\">\n" +
        "                                            <br>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">酒店名</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"text\" class=\"form-control form-control-line\" id=\"hotelName\"value=\""+hotel.hotelName+"\">\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">电话号码</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"text\" class=\"form-control form-control-line\" id=\"hotelPhone\" value=\""+hotel.hotelPhone+"\">\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">地址</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"text\" class=\"form-control form-control-line\" id=\"hotelLocation\" value=\""+hotel.hotelLocation+"\">\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">密码</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"password\" id=\"hotelPass\" value=\""+hotel.hotelPass+"\"\n" +
        "                                                        class=\"form-control form-control-line\">\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">重复密码</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                               <input type=\"password\" value=\""+hotel.hotelPass+"\"\n" +
        "                                                   class=\"form-control form-control-line\" id=\"repeatHotelPass\">\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">酒店介绍</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <textarea rows=\"5\" class=\"form-control form-control-line\"id=\"hotelInfo\">\n" +
                                                            "\n" +hotel.hotelInfo+
        "                                                </textarea>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <br />\n" +
        "                                            <br />\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <div class=\"col-sm-12 text-center\">\n" +
        "                                                   <button type=\"button\" class=\"btn btn-info\" onclick='submitChange()'>更新</button>" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                        </form>\n" +
        "                                    </div>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                    <!-- Column -->\n" +
        "                </div>\n" +
        "                <!-- Row -->\n" +
        "\n" +
        "            </div>"+"</div>"
    // 清空节点
    $(".jumbotron").empty();
    $(".jumbotron").append($html);
}
/*退出登陆时清空用户信息*/
function clearHotelInfo() {
    hotel=undefined;
}
/*base 64 加密字符串*/
function encodeStr(str) {
    // console.log(str);
    return new Base64().encode(str);
}

/*base 64 加密*/
function Base64() {
    // private property
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    // public method for encoding
    this.encode = function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = _utf8_encode(input);
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output +
                _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
                _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
        }
        return output;
    }

    // public method for decoding
    this.decode = function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        if (input === "undefined" || typeof (input) == "undefined") {
            input = " ";
        } else {
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");/*修改过*/
        }
        while (i < input.length) {
            enc1 = _keyStr.indexOf(input.charAt(i++));
            enc2 = _keyStr.indexOf(input.charAt(i++));
            enc3 = _keyStr.indexOf(input.charAt(i++));
            enc4 = _keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
        }
        output = _utf8_decode(output);
        return output;
    }

    // private method for UTF-8 encoding
    _utf8_encode = function (string) {
        string = string.replace(/\r\n/g, "\n");
        var utftext = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }
        return utftext;
    }

    // private method for UTF-8 decoding
    _utf8_decode = function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while (i < utftext.length) {
            c = utftext.charCodeAt(i);
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            } else if ((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i + 1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i + 1);
                c3 = utftext.charCodeAt(i + 2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
        return string;
    }
}
//信息表单前端验证
function validformHotel() {
    // alert()
    // return $("#driverInfo");
    /*关键在此增加了一个return，返回的是一个validate对象，这个对象有一个form方法，返回的是是否通过验证*/
    return $(".self-form").validate({
        rules: {
            hotelName: {
                minlength: 2,
                maxlength: 13
            },
            hotelPhone: {
                minlength: 11,
                maxlength: 11
            },
            hotelLocation: {
                minlength: 2,
                maxlength: 8
            },
            hotelPass: {
                minlength: 6,
                maxlength: 20,

            },
            repeatHotelPass: {
                minlength: 6,
                maxlength: 20,
                equalTo: "#hotelPass"
            }
        },
        messages: {
            hotelName: {
                minlength: "用户名至少包含1个字符",
                maxlength: "用户名长度不能超过13个字符"
            },
            hotelPhone: {
                minlength: "请输入正确的电话号码",
                maxlength: "请输入正确的电话号码"
            },
            hotelLocation: {
                minlength: "居住地名称长度过短",
                maxlength: "居住地长度过长"
            },
            hotelPass: {
                minlength: "密码长度不能少于6个字符",
                maxlength: "密码长度不能多于20个字符",

            },
            repeatHotelPass: {
                minlength: "密码长度不能少于6个字符",
                maxlength: "密码长度不能多于20个字符",
                equalTo: "两次密码输入不一致"
            }

        }
    });
}
//酒店信息更新
function submitChange() {
    //console.log("修改中...");
    if (1) {
        $.ajax({
            // async: false,
            type: "POST",
            url: '/hotel/updateHotel',
            contentType: "application/json",
            headers: { 'token': localStorage.getItem("conNCU") },
            data: JSON.stringify({
                "hotelName": $("#hotelName").val(),
                "hotelLocation": $("#hotelLocation").val(),
                "hotelPhone": $("#hotelPhone").val(),
                "hotelPass": $("#hotelPass").val(),
                "hotelInfo": $("#hotelInfo").val(),
            }),
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    alert("修改成功");
                    // showDriverInfo(driver)
                    location.reload();
                } else {
                    alert("修改失败");
                    location.reload();
                }
            },
        });
    } else {
        alert("信息格式有误，请重新填写！");
    }
}
/*获取token里面的用户数据*/
function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}
