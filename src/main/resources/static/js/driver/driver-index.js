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

    if (token == null || token == "null" || typeof ($driverId) == "undefined" || $driverId == undefined) {//未登录
        console.log("未登录");
        localStorage.setItem("hcs", null);
        alert("请先登录！");
        window.location.href = "popupsignin.html";
    }
    /**
     * 点击->个人信息
     */
    $("#to-info a").click(function () {
            console.log("信息获取");
            showDriverInfo();
        }
    )
    $("#to-wait-pick a").click(function () {
        getDriverAllPickUp();
        // for (let it of pickUp) {
        //     console.log(it);
        // }
        showWaitPickUpTable();
    })
    $("#to-all-pick a").click(function () {
        getDriverAllPickUp();
        for (let it of pickUp) {
            console.log(it);
        }
        showAllPickUpTable();
    })
    /*点击 退出登录 按钮*/
    $(".login-out").click(function () {
        clearDriverInfo();
        //localStorage.clear();
        localStorage.setItem("hcs", null);
        alert("退出成功");
        window.location.href = "popupsignin.html";
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
 * 数字+位数
 * @param num
 * @param length
 * @returns {string}
 */
function fix(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
}

let allFleet;

function getAllFleet() {
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/fleet/getAllFleet",
        type: "get",
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                // alert("获取车队数据成功");
                allFleet = data["data"];
                allFleet = allFleet["getAllFleet"];
                console.log(allFleet);
            } else {
                alert("获取车队数据失败");
            }
        },
        error: function () {
            alert("获取车队数据失败");
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
 * 司机信息
 */
function showDriverInfo() {
    getAllFleet();
    let vis = -1;
    console.log(allFleet);
    getDriverInfo($driverId);
    console.log(driver);
    let $html =
        // "                                <ul class=\"nav nav-tabs profile-tab\" role=\"tablist\">\n" +
        // "                                    <li class=\"nav-item active\"><a class=\"nav-link\" data-toggle=\"tab\" href=\"#个人信息\" role=\"tab\">个人信息</a>\n" +
        // "                                    </li>\n" +
        // "                                </ul>" +
        // "<br>" +
        "<div class=\"tab-pane\" role=\"tabpanel\">\n" +
        "                                    <div class=\"card-body\">\n" +
        "                                        <form  class=\"form-horizontal form-material\" id='driverInfo' >\n" +
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
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label class=\"col-md-12\">所属车队</label>\n" +
        "<!--                                                    <label class=\"col-sm-2 control-label\">Select</label>-->\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "                                                        <select class=\"form-control form-control-line\" name=\"account\" id='fleetId'>\n"
    if (driver.fleetId === null) {
        $html += "                                                            <option value='" + null + "'>" + "请选择您的车队" + "</option>\n";
        for (let it in allFleet) {
            if (it === vis) continue;
            $html += "                                                            <option value='" + allFleet[it].fleetId + "'>" + allFleet[it].fleetName + "</option>\n"
        }
    } else {
        let fleetNameTem;
        // console.log(driver.fleetId);
        // console.log(typeof driver.fleetId);
        // alert(driver.fleetId);

        for (let it in allFleet) {
            // alert(allFleet[it].fleetId);
            // console.log(allFleet[it].fleetId);
            // console.log(typeof allFleet[it].fleetId);
            if (allFleet[it].fleetId == driver.fleetId) {
                console.log(allFleet[it].fleetName);
                fleetNameTem = allFleet[it].fleetName;
                vis = it;
                break;
            }
        }
        $html += "                                                            <option value='" + driver.fleetId + "'>" + fleetNameTem + "</option>\n"
        for (let it in allFleet) {
            if (it === vis) continue;
            $html += "                                                            <option value='" + allFleet[it].fleetId + "'>" + allFleet[it].fleetName + "</option>\n"
        }
        $html += "                                                            <option value='" + null + "'>" + "退出当前车队" + "</option>\n";
    }


    $html +=
        "                                                        </select>\n" +
        "                                                    </div>\n" +
        "                                                </div>" +
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
                    // location.reload();
                } else {
                    console.log('失败');
                    alert("修改失败");
                    // location.reload();
                }
            },
        });
        for (let i = 0; i < 500000000; i++) {
            /**
             * 意义不明的代码，
             * 不加会有bug
             * 170000000
             * 二分
             */
        }
        showDriverInfo(driver);
        // showDriverInfo();
    } else {
        alert("信息格式有误，请重新填写！");
    }
}
