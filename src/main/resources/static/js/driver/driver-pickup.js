// "use strict";
// Object.defineProperty(exports, "__esModule", { value: true });
// var jquery_1 = require("jquery");
let driver;
let pickUp;
let token;
let mes;
let $driverId;
let $participantId;
$(function () {

    let $driverPhone;
    /*获取token*/
    token = localStorage.getItem("hcs");
    console.log(typeof (token));
    console.log(token);
    if (token == null || token === "null" || token === "undefined") {
        console.log("no token");
    } else {
        $driverId = parseJwt(token).driverId;/*获取用户信息*/
        console.log($driverId);
    }

    getDriverAllPickUp();
    showPickUpTable();
    /**
     * 点击->个人信息
     */
    $("#to-info a").click(function () {

            showDriverInfo();

        }
    )
    $("#to-wait-pick a").click(function () {
        getDriverAllPickUp();
        showPickUpTable();
    })
})

/**
 * 得到登入司机的信息
 */

function getDriverInfo($driverId) {
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/driver/getDriverInfo",
        type: "get",
        dataType: "json",
        data: {
            'driverId': $driverId,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                driver = data["data"]["getDriverInfo"];
                console.log(driver);
            } else {
                alert("获取用户数据失败");
            }
        },
        error: function () {
            alert("获取用户数据失败");
        },
    });
}

/**
 * 清空登入时清空用户信息
 */
function clearDriverInfo() {
    driver = undefined;
}

//# sourceMappingURL=1.js.map


/*获取token里面的用户数据*/
function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}

/**
 *
 */

function showDriverInfo() {
    getDriverInfo($driverId);
    console.log(driver);
    let $html = "<div class=\"tab-pane\" role=\"tabpanel\">\n" +
        "                                    <div class=\"card-body\">\n" +
        "                                        <form  class=\"form-horizontal form-material\" id='driverInfo' >\n" +
        "                                            <br>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">姓名</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"text\" class=\"form-control form-control-line\"\n" +
        "                                                        name='driverName' id='driverName' value=\"" + driver.driverName + "\">\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        // "                                            <div class=\"form-group\">\n" +
        // "                                                <label for=\"example-email\" class=\"col-md-12\">邮箱</label>\n" +
        // "                                                <div class=\"col-md-12\">\n" +
        // "                                                    <input type=\"email\" \n" +
        // "                                                        class=\"form-control form-control-line\" name=\"example-email\"\n" +
        // "                                                        id=\"example-email\">\n" +
        // "                                                </div>\n" +
        // "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">密码</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"password\" value=\"" + driver.driverPass + "\"\n" +
        "                                                        name='driverPass' class=\"form-control form-control-line\" id='driverPass'>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">重复密码</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"password\" value=\"" + driver.driverPass + "\"\n" +
        "                                                        name='repeatDriverPass' class=\"form-control form-control-line\" id='repeatDriverPass'>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">电话</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"text\" value=\"" + driver.driverPhone + "\"\n" +
        "                                                        name='driverPhone' class=\"form-control form-control-line\" id='driverPhone'>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">车牌号</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"text\" value=\"" + driver.carNumber + "\"\n" +
        "                                                        name='carNumber' class=\"form-control form-control-line\" id='carNumber'>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +

        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">个人介绍</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <textarea rows=\"5\" class=\"form-control form-control-line\">\n" +
        "                                                    啊哈！\n" +
        "                                                </textarea>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <br />\n" +
        "                                            <br />\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <div class=\"col-sm-12 text-center\">\n" +
        "                                                    <input type='submit' class=\"btn-info\" onclick='submitChange()' value='更新'>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                        </form>\n" +
        "                                    </div>\n" +
        "                                </div>"
    // 清空节点
    $(".jumbotron").empty();
    $(".jumbotron").append($html);
}


/**
 * 司机信息表单前端验证
 */
function validform() {
    // alert()
    // return $("#driverInfo");
    /*关键在此增加了一个return，返回的是一个validate对象，这个对象有一个form方法，返回的是是否通过验证*/
    alert(1);
    console.log($("#driverInfo"));
    alert(1);
    return $("#driverInfo").validate({
        rules: {
            driverName: {
                minlength: 2,
                maxlength: 13
            },
            driverPass: {
                minlength: 5,
                maxlength: 20
            },
            repeatDriverPass: {
                minlength: 5,
                maxlength: 20,
                equalTo: "#repeatDriverPass"
            },
            driverPhone: {
                minlength: 11,
                maxlength: 11
            },
            carNumber: {
                minlength: 2,
                maxlength: 8
            }
        },
        messages: {
            driverName: {
                minlength: "姓名名至少包含2个字符",
                maxlength: "用户名长度不能超过13个字符"
            },
            driverPass: {
                minlength: "密码长度不能少于6个字符",
                maxlength: "密码长度不能多于20个字符"
            },
            repeatDriverPass: {
                minlength: "密码长度不能少于6个字符",
                maxlength: "密码长度不能多于20个字符",
                equalTo: "两次密码输入不一致"
            },
            driverPhone: {
                minlength: "请输入正确的电话号码",
                maxlength: "请输入正确的电话号码"
            },
            carNumber: {
                minlength: "请输入正确的车牌号码",
                maxlength: "请输入正确的车牌号码"
            }
        }
    });
}

/**
 * 更新司机信息
 */
function submitChange() {
    /**
     * @TODO : 前端验证
     * validform().form()
     */
    if (1) {
        $.ajax({
            // async: false,
            type: "POST",
            url: '/driver/updateDriver',
            contentType: "application/json",
            headers: {'token': localStorage.getItem("hcs")},
            data: JSON.stringify({
                "driverName": $("#driverName").val(),
                "carNumber": $("#carNumber").val(),
                "fleetId": Number($("#fleetId").val()),
                "driverPass": $("#driverPass").val(),
                "driverPhone": $("#driverPhone").val()
            }),
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    console.log('成功');
                    alert("修改成功");
                    // showDriverInfo(driver);
                    location.reload();
                } else {
                    console.log('失败');
                    alert("修改失败");
                    location.reload();
                }
            },
        });
        for (let i = 0; i < 170000000; i++) {
            /**
             * 意义不明的代码，
             * 不加会有bug
             * 170000000
             * 二分
             */
        }
        // showDriverInfo();
    } else {
        alert("信息格式有误，请重新填写！");
    }
}


function getDriverAllPickUp() {
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/pickUp/getDriverAllPickUp",
        type: "get",
        dataType: "json",
        data: {
            'driverId': $driverId,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                pickUp = data["data"]["getDriverAllPickUp"];
                console.log(driver);
            } else {
                alert("获取用户数据失败");
            }
        },
        error: function () {
            alert("获取用户数据失败!");
        },
    });
}

function getParticipantNameById($participantId) {

}

// $(document).ready(function () {
//     $('#datatable').dataTable();
// });
function showPickUpTable() {
    let $htmlStart =
        "                            <div class=\"row\">\n" +
        "                                <div class=\"col-md-12\">\n" +
        "                                    <div class=\"panel panel-default collapsed\">\n" +
        "                                        <div class=\"panel-heading\">\n" +
        "                                            接送记录\n" +
        "                                        </div>\n" +
        "                                        <div class=\"panel-body\">\n" +
        "                                            <table class=\"table table-striped dt-responsive nowrap\" id=\"datatable\">\n" +
        "                                                <thead>\n" +
        "                                                <tr>\n" +
        "                                                    <th>接送单号</th>\n" +
        "                                                    <th>姓名</th>\n" +
        "                                                    <th>火车航班</th>\n" +
        "                                                    <th>到达时间</th>\n" +
        "                                                    <th>离开时间</th>\n" +
        "                                                    <th>是否完成</th>\n" +
        "                                                </tr>\n" +
        "                                                </thead>\n" +

        "                                                <tbody>\n"
    let $html;
    // console.log(pickUp);
    for (let i of pickUp) {
        $html +=
            "                                                <tr>\n" +
            "                                                    <td>" + i.pickUpId + "</td>\n" +
            "                                                    <td>" + i.participantId + "</td>\n" +
            "                                                    <td>" + i.driverId + "</td>\n" +
            "                                                    <td>" + i.conferenceId + "</td>\n" +
            "                                                    <td>" + i.trainNumber + "</td>\n" +
            "                                                    <td>" + i.toTime + "</td>\n" +
            "                                                </tr>\n";
    }
    // let $html =
    //     "                                                <tr>\n" +
    //     "                                                    <td>Tiger Nixon</td>\n" +
    //     "                                                    <td>System Architect</td>\n" +
    //     "                                                    <td>Edinburgh</td>\n" +
    //     "                                                    <td>61</td>\n" +
    //     "                                                    <td>2011/04/25</td>\n" +
    //     "                                                    <td>$320,800</td>\n" +
    //     "                                                </tr>\n" +
    //     "                                                <!--g-->\n" +
    //     "                                                <tr>\n" +
    //     "                                                    <td>Garrett Winters</td>\n" +
    //     "                                                    <td>Accountant</td>\n" +
    //     "                                                    <td>Tokyo</td>\n" +
    //     "                                                    <td>63</td>\n" +
    //     "                                                    <td>2011/07/25</td>\n" +
    //     "                                                    <td>$170,750</td>\n" +
    //     "                                                </tr>\n"


    let $htmlEnd =
        "                                                </tbody>\n" +
        "                                            </table>\n" +
        "\n" +
        "                                        </div>\n" +
        "                                    </div>\n" +
        "                                </div>\n" +
        "                            </div><!--end row-->";

    // 清空节点
    $(".jumbotron").empty();
    $(".jumbotron").append($htmlStart + $html + $htmlEnd);
    $('#datatable').dataTable();
}


/***********************************************************************************************************************/

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