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
    });
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
        "                                                    <input type=\"text\" class=\"form-control form-control-line\" value=\""+hotel.hotelName+"\">\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">电话号码</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"text\" class=\"form-control form-control-line\" value=\""+hotel.hotelPhone+"\">\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">地址</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"text\" class=\"form-control form-control-line\" value=\""+hotel.hotelLocation+"\">\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">密码</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"password\" value=\""+hotel.hotelPass+"\"\n" +
        "                                                        class=\"form-control form-control-line\">\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">酒店介绍</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <textarea rows=\"5\" class=\"form-control form-control-line\">\n" +
                                                            "\n" +hotel.hotelInfo+
        "                                                </textarea>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <br />\n" +
        "                                            <br />\n" +
        "                                            <div class=\"form-group\">\n" +
        "                                                <div class=\"col-sm-12 text-center\">\n" +
        "                                                    <button class=\"btn btn-info\">更新</button>\n" +
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
/*获取token里面的用户数据*/
function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}