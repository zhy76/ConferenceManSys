let driver;
let pickUp;
let token;
let mes;
let $driverId;
let $participantId;
$(function () {
    let $driverPhone;
    /*获取token*/
    token = localStorage.getItem("conNCU");
    console.log(typeof (token));
    console.log(token);
    if (token == null || token === "null" || token === "undefined") {
        console.log("no token");
    } else {
        $driverId = parseJwt(token).driverId;/*获取用户信息*/
    }
    if (token == null || token == "null" || typeof ($driverId) == "undefined" || $driverId == undefined) {//未登录
        console.log("未登录");
        localStorage.setItem("conNCU", null);
        $.alert({
            title: '提示信息',
            content: '请先登录！',
        });
        window.location.href = "login.html";
    } else if (parseJwt(token).role !== "driver") {
        $.alert({
            title: '提示信息',
            content: 'token错误',
        });
        window.location.href = "login.html";
    }

    getDriverInfo($driverId);
    $(".img-circle").attr("src",driver.driverPhoto);
    console.log($driverId);
    /**
     * 点击->个人信息
     */
    $("#img-circle a ").click(function () {
        // console.log("信息获取");
        showDriverInfo();
    })
    $("#to-info a").click(function () {
            // console.log("信息获取");
            showDriverInfo();
        }
    )
    $("#to-confirm-pick a").click(function () {
        // getDriverConfirmPickUp($driverId);
        showDriverConfirmPickUp($driverId)
    })
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
        localStorage.setItem("conNCU", null);
        $.alert({
            title: '提示信息',
            content: '退出成功',
        });
        window.location.href = "login.html";
    })

    // $('#driverInfo').submit(function () {	//这次我们这么绑定
    //     alert(1);
    //     return false;	//还是要return false, 跟上面一样的道理
    // });
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
                $.alert({
                    title: '提示信息',
                    content: '获取用户数据失败',
                });
            }
        },
        error: function () {
            $.alert({
                title: '提示信息',
                content: '获取用户数据失败',
            });
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
                $.alert({
                    title: '提示信息',
                    content: '获取司机数据失败',
                });
            }
        },
        error: function () {
            $.alert({
                title: '提示信息',
                content: '获取司机数据失败',
            });
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
function headPhotoUpload() {
    var form = new FormData(document.getElementById("form_submit_photo"));//把表单的对象作为一个参数
    $.ajax({
        type:"POST",
        url:"/file/headPhotoUpload",
        data:form,
        headers:{"conNCU": localStorage.conNCU},
        dataType:"json",
        processData:false,
        contentType:false,
        success:function(result) {

            if (result.message == "成功") {
                // alert("更新头像成功!");

                $.alert({
                    title: '提示信息',
                    content: '更新头像成功!',
                });
                location.reload();
            } else {
                // alert("更新头像失败!");
                $.alert({
                    title: '提示信息',
                    content: '更新头像失败!',
                });
            }
        },
        error:function() {
            // alert("更新异常!");
            $.alert({
                title: '提示信息',
                content: '更新异常!',
            });
        }
    });

    // localStorage.setItem('function', 'showDriverInfo()');

}
//预览图片
function onLoadImage() {
    var file=$('#ingredient_file').get(0).files[0];
    var reader = new FileReader();
    //将文件以Data URL形式读入页面
    reader.readAsDataURL(file);
    reader.onload = function(e){
        //显示文件
        $(".head_photo_container").html('<img id="head_photo" class="img-responsive center-block" src="' + this.result +'" alt="" />');
    }
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
    let $html= " <div class=\"container\">\n" +
        "            <!--        页面内容-->\n" +
        "            <div class=\"row\" style=\"margin-top:30px;\">\n" +
        "\n" +
        "                <div class=\"col-sm-3\">\n" +
        "                    <div class=\"panel panel-default\">\n" +
        "                        <div class=\"panel-body\">\n" +
        "                            <!--                        头像展示-->\n" +
        "                            <div class=\"head_photo_container\">\n" +
        "                                <img id=\"head_photo\" class=\"img-responsive center-block\" src=\""+  driver.driverPhoto +"\" style=\"\">\n" +
        "                            </div>\n" +
        "                            <hr>\n" +
        "\n" +
        "                            <!--                        换头像-->\n" +
        "                            <div align=\"center\">\n" +
        "                                <form id=\"form_submit_photo\" class=\"form-horizontal\">\n" +
        "                                    <input type=\"hidden\" name=\"role\" value=\"driver\">\n" +
        "                                    <input type=\"hidden\" name=\"roleId\" value=\"" + driver.driverId + "\" id=\"roleId\">\n" +
        "                                    <label class=\"btn btn-default btn-file\">\n" +
        "                                        更新头像\n" +
        "                                        <input id=\"ingredient_file\" type=\"file\" style=\"display: none;\" name=\"file\"\n" +
        "                                            required=\"\" onchange=\"onLoadImage()\">\n" +
        "                                    </label>\n" +
        "                                    <label class=\"btn btn-default btn-file\">\n" +
        "                                        确认更新\n" +
        "                                        <input id=\"submit_head_photo\" type=\"button\" style=\"display: none;\"\n" +
        "                                            class=\"btn btn-primary\" onclick='headPhotoUpload()' />\n" +
        "                                    </label>\n" +
        "                                </form>\n" +
        "                            </div>\n" +
        "\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n"
    getDriverInfo($driverId);
    $html+=
        "                <div class=\"col-sm-6\">\n" +
        "                    <div class=\"panel panel-default\">\n" +
        "                        <div class=\"panel-body\">\n" +
        "                            <div class=\"row\" style=\"margin-left: 20px; margin-right: 20px;\">\n" +
        "                                <h3>司机信息</h3>\n" +
        "                                <hr>\n" +
        "                                <form id=\"driverInfo\" class=\"form-horizontal\" action=\"\">\n" +
        " <input type=\"hidden\" name=\"driverId\" id=\"driverId\" value=\"\">"+
        "                                    <div class=\"form-group\">\n" +
        "                                        <label class=\"control-label col-sm-3\"><label\n" +
        "                                                for=\"driveName\">司机姓名：</label></label>\n" +
        "                                        <div class=\"col-sm-7\"><input type=\"text\" name=\"driverName\" value=\"" + driver.driverName + "\"\n" +
        "                                                class=\"form-control\" maxlength=\"40\" required=\"\" id=\"driverName\"\n" +
        "                                                placeholder=\"酒店名\"></div>\n" +
        "                                        <span class=\"text-danger small\"></span>\n" +
        "                                    </div>\n" +
        "\n" +
        "                                    <div class=\"form-group\">\n" +
        "                                        <label class=\"control-label col-sm-3\"><label\n" +
        "                                                for=\"hotelPhone\">电话号码:</label></label>\n" +
        "                                        <div class=\"col-sm-7\"><input type=\"text\" name=\"driverPhone\" value=\"" + driver.driverPhone + "\"\n" +
        "                                                class=\"form-control\" style=\"margin-top: 0;\" maxlength=\"50\" required=\"\"\n" +
        "                                                id=\"driverPhone\" placeholder=\"电话号码\"></div>\n" +
        "                                        <span class=\"text-danger small\"></span>\n" +
        "                                    </div>\n" +
        "                                    <div class=\"form-group\">\n" +
        "                                        <label class=\"control-label col-sm-3\"><label\n" +
        "                                                for=\"repeatHotelPass\">车牌号码:</label></label>\n" +
        "                                        <div class=\"col-sm-7\"><input type=\"text\" name=\"carNumber\"\n" +
        "                                                class=\"form-control\" maxlength=\"40\" id=\"carNumber\" value=\"" + driver.carNumber + "\"></div>\n" +
        "                                        <span class=\"text-danger small\"></span>\n" +
        "                                    </div>\n" +
        "                                    <div class=\"form-group\">\n" +
        "                                        <label class=\"control-label col-sm-3\"><label\n" +
        "                                                for=\"repeatHotelPass\">车队:</label></label>\n" +
        "                                                <div  class=\"col-sm-7\" >                  " +
        "                                                        <select class='form-control'  name=\"account\" id='fleetId'>\n"
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
            "</div>\n"+
        "                                    </div>\n" +
        "                                    <div class=\"form-group\">\n" +
        "                                        <label class=\"control-label col-sm-3\"><label for=\"driverPass\">密码:</label></label>\n" +
        "                                        <div class=\"col-sm-7\"><input type=\"password\" name=\"driverPass\" class=\"form-control\"\n" +
        "                                                maxlength=\"40\" id=\"driverPass\" value=\"" + driver.driverPass + "\"></div>\n" +
        "                                        <span class=\"text-danger small\"></span>\n" +
        "                                    </div>\n" +
        "\n" +
        "                                    <div class=\"form-group\">\n" +
        "                                        <label class=\"control-label col-sm-3\"><label\n" +
        "                                                for=\"repeatHotelPass\">重复密码:</label></label>\n" +
        "                                        <div class=\"col-sm-7\"><input type=\"password\" name=\"repeatDriverPass\"\n" +
        "                                                class=\"form-control\" maxlength=\"40\" id=\"repeatDriverPass\" value=\"" + driver.driverPass + "\"></div>\n" +
        "                                        <span class=\"text-danger small\"></span>\n" +
        "                                    </div>\n" +
        "                                    <div class=\"col-xs-offset-5\">\n" +
        "                                        <button type=\"button\" class=\"btn btn-info\" style=\"border-radius: 5px\"\n" +
        "                                            onclick=submitChange(); return false;>更新信息</button>\n" +
        "                                    </div>\n" +
        "\n" +
        "                                </form>\n" +
        "\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "\n" +
        "            </div>\n" +
        "        </div>"
    // let $html =
    //     "                                <ul class=\"nav nav-tabs profile-tab\" role=\"tablist\">\n" +
    //     "                                    <li class=\"nav-item active\"><a class=\"nav-link\" data-toggle=\"tab\" href=\"#个人信息\" role=\"tab\">个人信息</a>\n" +
    //     "                                    </li>\n" +
    //     "                                </ul>" +
    //     "<br>" +
    //
    //     "                                        <form  class=\"form-horizontal form-material\" id='driverInfo'>\n" +
    //     "                                            <div class=\"form-group\">\n" +
    //     "                                                <label class=\"col-md-12\">姓名</label>\n" +
    //     "                                                <div class=\"col-md-12\">\n" +
    //     "                                                    <input type=\"text\" class=\"form-control form-control-line\"\n" +
    //     "                                                        name='driverName' id='driverName' value=\"" + driver.driverName + "\">\n" +
    //     "                                                </div>\n" +
    //     "                                            </div>\n" +
    //     "                                            <div class=\"form-group\">\n" +
    //     "                                                <label class=\"col-md-12\">密码</label>\n" +
    //     "                                                <div class=\"col-md-12\">\n" +
    //     "                                                    <input type=\"password\" value=\"" + driver.driverPass + "\"\n" +
    //     "                                                        name='driverPass' class=\"form-control form-control-line\" id='driverPass'>\n" +
    //     "                                                </div>\n" +
    //     "                                            </div>\n" +
    //     "                                            <div class=\"form-group\">\n" +
    //     "                                                <label class=\"col-md-12\">重复密码</label>\n" +
    //     "                                                <div class=\"col-md-12\">\n" +
    //     "                                                    <input type=\"password\" value=\"" + driver.driverPass + "\"\n" +
    //     "                                                        name='repeatDriverPass' class=\"form-control form-control-line\" id='repeatDriverPass'>\n" +
    //     "                                                </div>\n" +
    //     "                                            </div>\n" +
    //     "                                            <div class=\"form-group\">\n" +
    //     "                                                <label class=\"col-md-12\">电话</label>\n" +
    //     "                                                <div class=\"col-md-12\">\n" +
    //     "                                                    <input type=\"text\" value=\"" + driver.driverPhone + "\"\n" +
    //     "                                                        name='driverPhone' class=\"form-control form-control-line\" id='driverPhone'>\n" +
    //     "                                                </div>\n" +
    //     "                                            </div>\n" +
    //     "                                            <div class=\"form-group\">\n" +
    //     "                                                <label class=\"col-md-12\">车牌号</label>\n" +
    //     "                                                <div class=\"col-md-12\">\n" +
    //     "                                                    <input type=\"text\" value=\"" + driver.carNumber + "\"\n" +
    //     "                                                        name='carNumber' class=\"form-control form-control-line\" id='carNumber'>\n" +
    //     "                                                </div>\n" +
    //     "                                            </div>\n" +
    //     "                                                <div class=\"form-group\">\n" +
    //     "                                                    <label class=\"col-md-12\">所属车队</label>\n" +
    //     "<!--                                                    <label class=\"col-sm-2 control-label\">Select</label>-->\n" +
    //     "                                                    <div class=\"col-md-12\">\n" +
    //     "                                                        <select class=\"form-control form-control-line\" name=\"account\" id='fleetId'>\n"
    // if (driver.fleetId === null) {
    //     $html += "                                                            <option value='" + null + "'>" + "请选择您的车队" + "</option>\n";
    //     for (let it in allFleet) {
    //         if (it === vis) continue;
    //         $html += "                                                            <option value='" + allFleet[it].fleetId + "'>" + allFleet[it].fleetName + "</option>\n"
    //     }
    // } else {
    //     let fleetNameTem;
    //     // console.log(driver.fleetId);
    //     // console.log(typeof driver.fleetId);
    //     // alert(driver.fleetId);
    //
    //     for (let it in allFleet) {
    //         // alert(allFleet[it].fleetId);
    //         // console.log(allFleet[it].fleetId);
    //         // console.log(typeof allFleet[it].fleetId);
    //         if (allFleet[it].fleetId == driver.fleetId) {
    //             console.log(allFleet[it].fleetName);
    //             fleetNameTem = allFleet[it].fleetName;
    //             vis = it;
    //             break;
    //         }
    //     }
    //     $html += "                                                            <option value='" + driver.fleetId + "'>" + fleetNameTem + "</option>\n"
    //     for (let it in allFleet) {
    //         if (it === vis) continue;
    //         $html += "                                                            <option value='" + allFleet[it].fleetId + "'>" + allFleet[it].fleetName + "</option>\n"
    //     }
    //     $html += "                                                            <option value='" + null + "'>" + "退出当前车队" + "</option>\n";
    // }
    //
    //
    // $html +=
    //     "                                                        </select>\n" +
    //     "                                                    </div>\n" +
    //     "                                                </div>" +
    //     "                                            <div class=\"form-group\">\n" +
    //     "                                                <label class=\"col-md-12\">个人介绍</label>\n" +
    //     "                                                <div class=\"col-md-12\">\n" +
    //     "                                                    <textarea rows=\"5\" class=\"form-control form-control-line\">\n" +
    //     "                                                    啊哈！\n" +
    //     "                                                </textarea>\n" +
    //     "                                                </div>\n" +
    //     "                                            </div>\n" +
    //     "                                            <br />\n" +
    //     "                                            <br />\n" +
    //     "                                            <div class=\"form-group\">\n" +
    //     "                                                <div class=\"col-sm-12 text-center\">\n" +
    //     "<button type=\"submit\" id=\"submit\"  class=\"btn btn-info\" onclick='submitChange()'>更新</button>" +
    //     // "                                                    <input type='submit' class=\"btn-info\" onclick='submitChange()' value='更新'>\n" +
    //     "                                                </div>\n" +
    //     "                                            </div>\n" +
    //     "                                        </form>\n" +
    //     "                                    </div>\n" +
    //     "                                </div>"
    // 清空节点
    $(".card").empty();
    $(".card").append($html);
}

/**
 *
 * @returns {jQuery|*}
 */

function validForm() {
    jQuery.validator.addMethod("mobile", function(value, element) {
        var length = value.length;
        var mobile = /^1[3456789]\d{9}$/
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "手机号码格式错误");
    return $("#driverInfo").validate({
        rules: {
            driverName: {
                required: true,
                minlength: 1,
                maxlength: 10,
            },
            driverPass: {
                minlength: 6,
                maxlength: 20
            },
            repeatDriverPass: {
                minlength: 6,
                maxlength: 20,
                equalTo: "#driverPass"
            },
            driverPhone: {
                minlength: 11,
                maxlength: 11,
                mobile: true
            },
            carNumber: {
                minlength: 6,
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
    if (!validForm().form()) {
        alert("信息有误")
        return;
    }
    $.ajax({
        async: false,
        type: "POST",
        url: '/driver/updateDriver',
        contentType: "application/json",
        headers: {'token': localStorage.getItem("conNCU")},
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
                $.alert({
                    title: '提示信息',
                    content: '修改成功',
                });
                // showDriverInfo(driver);
                // location.reload();
            } else {
                console.log('失败');
                $.alert({
                    title: '提示信息',
                    content: '修改失败',
                });
                // location.reload();
            }
        },
    });
    showDriverInfo();
    // showDriverInfo();
}



