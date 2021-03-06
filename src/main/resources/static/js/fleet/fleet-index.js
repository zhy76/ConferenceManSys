let driver;
let fleet;
let pickUp;
let token;
let mes;
let conference;
let participant;
let allFleetDriver;
let $driverId;
let $fleetId;
let $participantId;
let joinConference;

$(function () {
    let $fleetPhone;
    /*获取token*/
    token = localStorage.getItem("conNCU");
    console.log(typeof (token));
    console.log(token);
    if (token == null || token === "null" || token === "undefined") {
        console.log("no token");
    } else {
        // let str = "1".replace('1', ' ');
        // alert(str);
        $fleetId = parseJwt(token).fleetId;/*获取用户信息*/
        console.log($fleetId);
    }
    if (token == null || token == "null" || typeof ($fleetId) == "undefined" || $fleetId == undefined) {//未登录
        console.log("未登录");
        localStorage.setItem("conNCU", null);
        alert("请先登录！");
        window.location.href = "login.html";
    }

    if (localStorage.getItem("function") !== "null" && localStorage.getItem("function") !== null) {
        eval(localStorage.getItem("function"));
        localStorage.setItem("function", null);
    }
    getFleetInfo($fleetId);
    // showFleetInfo();
    console.log($('#btn').val);
    $('#btn').click();

//showFleetInfo();
    $("#to-info a").click(function () {
        console.log("success");
        showFleetInfo();
    })
//接送管理
    $("#to-pick-up a").click(function () {
        showAllFleetAssignPickUp()
        $('#datatable').dataTable();
    })
    //接送查询
    $("#to-query-pick-up a").click(function () {
        showAllFleetAllPickUp();
        $('#datatable').dataTable();
    })

//司机管理
    $("#to-driver a").click(function () {
        //console.log("success");

        getAllFleetDriver($fleetId);
        // alert(2);
    })
    //查看所有记录


    //会议订单管理
    $("#to-conference a").click(function () {
        fleetConference();
        // alert(3);
        // localStorage.setItem("hcs", token);
        // window.location.href = "../../fleet-conference.html";
    })

    /*点击 退出登录 按钮*/
    $("#login-out").click(function () {
        clearDriverInfo();
        localStorage.setItem("conNCU", null);
        alert("退出成功");
        window.location.href = "login.html";
    })
    // submitChange();
})

/**
 * 所有司机的接送记录
 */
function getAllFleetDriverPickUp($fleetId) {
    $.ajax({
        // async: false,
        headers: {
            'token': token,
        },
        url: "/pickUp/getAllFleetPickUp",
        type: "get",
        dataType: "json",
        data: {
            'fleetId': $fleetId,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                fleet = data["data"]["getAllFleetPickUp"];
                console.log(fleet);
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
/**
 * 得到登入车队的信息
 */

function getFleetInfo($fleetId) {
    $.ajax({
        // async: false,
        headers: {
            'token': token,
        },
        url: "/fleet/getFleetInfo",
        type: "get",
        dataType: "json",
        data: {
            'fleetId': $fleetId,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                fleet = data["data"]["getFleetInfo"];
                console.log(fleet);
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
function clearFleetInfo() {
    driver = undefined;
}

//# sourceMappingURL=1.js.map


/*获取token里面的用户数据*/
function parseJwt(token) {
    var base64Url = token.split('.')[1];
    // alert(token);
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}

/**
 * 车队信息
 */

function showFleetInfo() {
    getFleetInfo($fleetId);
    console.log(fleet);
    let $html =
        "<form class=\"form-horizontal form-material\" id='fleetInfo' onsubmit='return false;'>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label class=\"col-md-12\">车队名</label>\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "                                                        <input class=\"form-control form-control-line\" type=\"text\"\n" +
        "                                                               value=\"" + fleet.fleetName + "\" id='fleetName' name='fleetName'>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label class=\"col-md-12\">电话号码</label>\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "                                                        <input class=\"form-control form-control-line\" type=\"text\"\n" +
        "                                                               value=\"" + fleet.fleetPhone + "\" id='fleetPhone' name='fleetPhone'>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label class=\"col-md-12\">密码</label>\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "                                                        <input class=\"form-control form-control-line\" type=\"password\"\n" +
        "                                                               value=\"" + fleet.fleetPass + "\" id='fleetPass' name='fleetPass'>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label class=\"col-md-12\">重复密码</label>\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "                                                        <input class=\"form-control form-control-line\" type=\"password\"\n" +
        "                                                               value=\"" + fleet.fleetPass + "\" id='repeatFleetPass' name='repeatFleetPass'>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <br/>\n" +
        "                                                <br/>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <div class=\"col-sm-12 text-center\">\n" +
        "                                                        <button class=\"btn btn-info\" onclick='submitChange()'>更新</button>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                            </form>"
    // 清空节点
    $(".jumbotron").empty();
    $(".jumbotron").append($html);
}


/**
 * 车队信息表单前端验证
 */
function validForm() {
    // alert()
    // return $("#driverInfo");
    /*关键在此增加了一个return，返回的是一个validate对象，这个对象有一个form方法，返回的是是否通过验证*/
    // alert(1);
    // console.log($("#driverInfo"));
    // alert(1);
    return $("#fleetInfo").validate({
        rules: {
            fleetName: {
                required: true,
                minlength: 2,
                maxlength: 13
            },
            fleetPass: {
                minlength: 5,
                maxlength: 20
            },
            repeatFleetPass: {
                minlength: 5,
                maxlength: 20,
                equalTo: "#fleetPass"
            },
            fleetPhone: {
                minlength: 11,
                maxlength: 11
            }
        },
        messages: {
            fleetName: {
                minlength: "姓名名至少包含2个字符",
                maxlength: "用户名长度不能超过13个字符"
            },
            fleetPass: {
                minlength: "密码长度不能少于6个字符",
                maxlength: "密码长度不能多于20个字符"
            },
            repeatFleetPass: {
                minlength: "密码长度不能少于6个字符",
                maxlength: "密码长度不能多于20个字符",
                equalTo: "两次密码输入不一致"
            },
            fleetPhone: {
                minlength: "请输入正确的电话号码",
                maxlength: "请输入正确的电话号码"
            }
        }
    });
}

/**
 * 更新车队信息
 */
function submitChange() {
    /**
     * @TODO : 前端验证
     *
     */
    if (validForm().form()) {
        $.ajax({
            async: false,
            type: "POST",
            url: '/fleet/updateFleet',
            contentType: "application/json",
            headers: {'token': localStorage.getItem("conNCU")},
            data: JSON.stringify({
                "fleetName": $("#fleetName").val(),
                "fleetPass": $("#fleetPass").val(),
                "fleetPhone": $("#fleetPhone").val()
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
        showFleetInfo();

    } else {
        alert("信息格式有误，请重新填写！");
    }
}
