$(function() {
    let role = $("#signin_form").val();
    console.log(role);
    if (role == "participant") {
        showParticipantForm();
    } else if (role == "organizer") {
        showOrganizerForm();
        //$("#signForm").empty();
    } else if (role == "fleet") {
        showFleetForm();
    } else if (role == "driver") {
        showDriverForm();
    } else if (role == "hotel") {
        showHotelForm();
    }

    $("#signin").click(function () {
        role = $("#signin_form").val();
        let phone = $("#phone").val();
        let password = $("#password").val();
        if (role == "participant") {
            let participantEmail = $("#email").val();
            let participantName = $("#participantName").val();
            $.ajax({
                type: "POST",
                dataType: "json",
                url: '/participant/register',
                contentType: "application/json",
                data: JSON.stringify({
                    "participantEmail": "" + participantEmail,
                    "participantPhone": "" + phone,
                    "participantPass": "" + password,
                    "participantName": "" + participantName,
                    "participantSex": "男"
                }),
                success: function (jsonData, result) {
                    console.log("data is :" + result);
                    if(jsonData["code"]!=200){
                        alert(jsonData["message"]);
                        console.log(jsonData);
                    }
                    else if (jsonData["data"]["token"]) {

                        localStorage.setItem("conNCU", jsonData["data"]["token"]);

                        alert("注册成功");
                        window.location.href = role + "-index.html";
                    } else {
                        alert(result.message);
                    }
                }
            });
        }
        else if (role == "organizer") {
            let organizerUnit = $("#unit").val();
            $.ajax({
                type: "POST",
                dataType: "json",
                url: '/organizer/register',
                contentType: "application/json",
                data: JSON.stringify({
                    "organizerPhone": "" + phone,
                    "organizerPass": "" + password,
                    "organizerUnit": "" + organizerUnit,
                    "organizerEmail": ""
                }),
                success: function (jsonData, result) {
                    console.log("data is :" + result);
                    if(jsonData["code"]!=200){
                        alert(jsonData["message"]);
                    }
                    else if (jsonData["data"]["token"]) {

                        localStorage.setItem("conNCU", jsonData["data"]["token"]);

                        alert("注册成功");
                        window.location.href = role + "-index.html";
                    } else {
                        alert(result.message);
                    }
                }
            });
        }
        else if (role == "driver") {
            let driverName = $("#driverName").val();
            let carNumber = $("#carNumber").val();
            $.ajax({
                type: "POST",
                dataType: "json",
                url: '/driver/register',
                contentType: "application/json",
                data: JSON.stringify({
                    "driverPhone": "" + phone,
                    "driverPass": "" + password,
                    "driverName": "" + driverName,
                    "carNumber": "" + carNumber
                }),
                success: function (jsonData, result) {
                    console.log("data is :" + result);
                    if(jsonData["code"]!=200){
                        alert(jsonData["message"]);
                    }
                    else if (jsonData["data"]["token"]!=null) {

                        localStorage.setItem("conNCU", jsonData["data"]["token"]);

                        alert("注册成功");
                        window.location.href = role + "-index.html";
                    } else {
                        alert(result.message);
                    }
                }
            });
        }
        else if (role == "fleet") {
            let fleetName = $("#fleetName").val();
            $.ajax({
                type: "POST",
                dataType: "json",
                url: '/fleet/register',
                contentType: "application/json",
                data: JSON.stringify({
                    "fleetPhone": "" + phone,
                    "fleetPass": "" + password,
                    "fleetName": "" + fleetName
                }),
                success: function (jsonData, result) {
                    console.log("data is :" + result);
                    if(jsonData["code"]!=200){
                        alert(jsonData["message"]);
                    }
                    else if (jsonData["data"]["token"]) {

                        localStorage.setItem("conNCU", jsonData["data"]["token"]);

                        alert("注册成功");
                        window.location.href = role + "-index.html";
                    } else {
                        alert(result.message);
                    }
                }
            });
        }
        else if (role == "hotel") {
            let hotelName = $("#hotelName").val();
            let hotelLocation = $("#hotelLocation").val();
            $.ajax({
                type: "POST",
                dataType: "json",
                url: '/hotel/register',
                contentType: "application/json",
                data: JSON.stringify({
                    "hotelPhone": "" + phone,
                    "hotelPass": "" + password,
                    "hotelName": "" + hotelName,
                    "hotelLocation": "" + hotelLocation,
                    "hotelInfo": "写点什么吧.."
                }),
                success: function (jsonData, result) {
                    console.log("data is :" + result);
                    if(jsonData["code"]!=200){
                        alert(jsonData["message"]);
                    }
                    else if (jsonData["data"]["token"]!=null) {

                        localStorage.setItem("conNCU", jsonData["data"]["token"]);

                        alert("注册成功");
                        window.location.href = role + "-index.html";
                    } else {
                        alert(result.message);
                    }
                }
            });
        }

    })
})

function getRole(){
    let role = $("#signin_form").val();
    console.log(role);
    if(role=="participant"){
        showParticipantForm();
    }
    else if(role=="organizer"){
        showOrganizerForm();
        //$("#signForm").empty();
    }
    else if(role=="fleet"){
        showFleetForm();
    }
    else if(role=="driver"){
        showDriverForm();
    }
    else if(role=="hotel"){
        showHotelForm();
    }
}
function check(){
    $("#email").on("blur keyup",function(){
        var val = $("#email").val();

        var reg_tel = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; //电子邮箱正则表达式
        if( val == '' )
        {
            $("#email-icon").removeClass();
            $("#email-icon").addClass('glyphicon glyphicon-remove');
            $("#email-icon").css({"color":"red","display":"block"});
            $("#email-icon").css({"display":"block"});
            $("#emailInfo").show('.2s');
            $("#emailInfo strong").text(" 邮箱不可以为空");
        }

        else if( reg_tel.test(val) == false)
        {
            $("#email-icon").removeClass();
            $("#email-icon").addClass('glyphicon glyphicon-remove');
            $("#email-icon").css({"color":"red","display":"block"});
            $("#email-icon").css({"display":"block"});
            $("#emailInfo").show('.2s');
            $("#emailInfo strong").text(" 邮箱格式不正确");
        }

        else
        {
            $("#email-icon").removeClass();
            $("#email-icon").addClass('glyphicon glyphicon-ok');
            $("#emailInfo").hide('.2s');
            $("#email-icon").css({"color":"green","display":"block"});
        }

    })
    $("#participantName").on("blur keyup",function(){
        var val = $("#participantName").val();
        if( val == '' )
        {
            $("#participantName-icon").removeClass();
            $("#participantName-icon").addClass('glyphicon glyphicon-remove');
            $("#participantName-icon").css({"color":"red","display":"block"});
            $("#participantName-icon").css({"display":"block"});
            $("#participantNameInfo").show('.2s');
            $("#participantNameInfo strong").text(" 姓名不可以为空");
        }
        else
        {
            $("#participantName-icon").removeClass();
            $("#participantName-icon").addClass('glyphicon glyphicon-ok');
            $("#participantNameInfo").hide('.2s');
            // $("#phoneInfo").css({"display":"none"});
            $("#participantName-icon").css({"color":"green","display":"block"});
        }
    })
    $("#unit").on("blur keyup",function(){
        var val = $("#unit").val();
        if( val == '' )
        {
            $("#unit-icon").removeClass();
            $("#unit-icon").addClass('glyphicon glyphicon-remove');
            $("#unit-icon").css({"color":"red","display":"block"});
            $("#unit-icon").css({"display":"block"});
            $("#unitInfo").show('.2s');
            $("#unitInfo strong").text(" 所属单位不可以为空");
        }
        else
        {
            $("#unit-icon").removeClass();
            $("#unit-icon").addClass('glyphicon glyphicon-ok');
            $("#unitInfo").hide('.2s');
            // $("#phoneInfo").css({"display":"none"});
            $("#unit-icon").css({"color":"green","display":"block"});
        }
    })
    $("#fleetName").on("blur keyup",function(){
        var val = $("#fleetName").val();
        if( val == '' )
        {
            $("#fleetName-icon").removeClass();
            $("#fleetName-icon").addClass('glyphicon glyphicon-remove');
            $("#fleetName-icon").css({"color":"red","display":"block"});
            $("#fleetName-icon").css({"display":"block"});
            // $("#emailInfo").css({"display":"block"});
            $("#fleetNameInfo").show('.2s');
            $("#fleetNameInfo strong").text(" 车队名称不可以为空");
        }
        else
        {
            $("#fleetName-icon").removeClass();
            $("#fleetName-icon").addClass('glyphicon glyphicon-ok');
            $("#fleetNameInfo").hide('.2s');
            $("#fleetName-icon").css({"color":"green","display":"block"});
        }
    })
    $("#driverName").on("blur keyup",function(){
        var val = $("#driverName").val();
        if( val == '' )
        {
            $("#driverName-icon").removeClass();
            $("#driverName-icon").addClass('glyphicon glyphicon-remove');
            $("#driverName-icon").css({"color":"red","display":"block"});
            $("#driverName-icon").css({"display":"block"});
            $("#driverNameInfo").show('.2s');
            $("#driverNameInfo strong").text(" 司机姓名不可以为空");
        }
        else
        {
            $("#driverName-icon").removeClass();
            $("#driverName-icon").addClass('glyphicon glyphicon-ok');
            $("#driverNameInfo").hide('.2s');
            $("#driverName-icon").css({"color":"green","display":"block"});
        }
    })
    $("#carNumber").on("blur keyup",function(){
        var val = $("#carNumber").val();
        if( val == '' )
        {
            $("#carNumber-icon").removeClass();
            $("#carNumber-icon").addClass('glyphicon glyphicon-remove');
            $("#carNumber-icon").css({"color":"red","display":"block"});
            $("#carNumber-icon").css({"display":"block"});
            $("#carNumberInfo").show('.2s');
            $("#carNumberInfo strong").text(" 车牌号不可以为空");
        }
        else
        {
            $("#carNumber-icon").removeClass();
            $("#carNumber-icon").addClass('glyphicon glyphicon-ok');
            $("#carNumberInfo").hide('.2s');
            $("#carNumber-icon").css({"color":"green","display":"block"});
        }
    })
    $("#hotelName").on("blur keyup",function(){
        var val = $("#hotelName").val();
        if( val == '' )
        {
            $("#hotelName-icon").removeClass();
            $("#hotelName-icon").addClass('glyphicon glyphicon-remove');
            $("#hotelName-icon").css({"color":"red","display":"block"});
            $("#hotelName-icon").css({"display":"block"});
            $("#hotelNameInfo").show('.2s');
            $("#hotelNameInfo strong").text(" 酒店名称不可以为空");
        }
        else
        {
            $("#hotelName-icon").removeClass();
            $("#hotelName-icon").addClass('glyphicon glyphicon-ok');
            $("#hotelNameInfo").hide('.2s');
            $("#hotelName-icon").css({"color":"green","display":"block"});
        }
    })
    $("#hotelLocation").on("blur keyup",function(){
        var val = $("#hotelLocation").val();
        if( val == '' )
        {
            $("#hotelLocation-icon").removeClass();
            $("#hotelLocation-icon").addClass('glyphicon glyphicon-remove');
            $("#hotelLocation-icon").css({"color":"red","display":"block"});
            $("#hotelLocation-icon").css({"display":"block"});
            $("#hotelLocationInfo").show('.2s');
            $("#hotelLocationInfo strong").text(" 酒店位置不能为空");
        }
        else
        {
            $("#hotelLocation-icon").removeClass();
            $("#hotelLocation-icon").addClass('glyphicon glyphicon-ok');
            $("#hotelLocationInfo").hide('.2s');
            $("#hotelLocation-icon").css({"color":"green","display":"block"});
        }
    });

}
function  showHotelForm(){
    let $html="<!--/.form-group -->\n" +
        "<div class=\"form-group\">\n" +
        "    <label for=\"hotelName\"><span class=\"glyphicon glyphicon-user\" style=\"color: rgb(140, 132, 251);\"></span>酒店名</label>\n" +
        "    <input type=\"text\" class=\"form-control\" id=\"hotelName\" placeholder=\"请输入酒店名\" onclick=\"check()\">\n" +
        "    <span id=\"hotelName-icon\" class=\"glyphicon glyphicon-remove\"\n" +
        "        style=\"float:right ; color: red; font-size: 20px;margin-top: -35px; display: none;\"></span>\n" +
        "    <div id=\"hotelNameInfo\" style=\"color:red; font-weight: bold; display: none;\"><span\n" +
        "            class=\"glyphicon glyphicon-exclamation-sign\"></span><strong></strong></div>\n" +
        "</div>\n" +
        "<!--/.form-group -->\n" +
        "<!--/.form-group -->\n" +
        "<div class=\"form-group\">\n" +
        "    <label for=\"hotelLocation\"><span class=\"glyphicon glyphicon-exclamation-sign\" style=\"color: rgb(140, 132, 251);\"></span>酒店位置</label>\n" +
        "    <input type=\"text\" class=\"form-control\" id=\"hotelLocation\" placeholder=\"请写下酒店位置\" onclick=\"check()\">\n" +
        "    <span id=\"hotelLocation-icon\" class=\"glyphicon glyphicon-remove\"\n" +
        "        style=\"float:right ; color: red; font-size: 20px;margin-top: -35px; display: none;\"></span>\n" +
        "    <div id=\"hotelLocationInfo\" style=\"color:red; font-weight: bold; display: none;\"><span\n" +
        "            class=\"glyphicon glyphicon-exclamation-sign\"></span><strong></strong></div>\n" +
        "</div>\n" +
        "<!--/.form-group -->\n"
    // 清空节点
    $("#signForm").empty();
    $("#signForm").append($html);
}
function showDriverForm(){
    let $html="<!--/.form-group -->\n" +
        "<div class=\"form-group\">\n" +
        "    <label for=\"driverName\"><span class=\"glyphicon glyphicon-user\" style=\"color: rgb(140, 132, 251);\"></span> 姓名</label>\n" +
        "    <input type=\"text\" class=\"form-control\" id=\"driverName\" placeholder=\"请输入您的姓名\" onclick=\"check()\">\n" +
        "    <span id=\"driverName-icon\" class=\"glyphicon glyphicon-remove\"\n" +
        "        style=\"float:right ; color: red; font-size: 20px;margin-top: -35px; display: none;\"></span>\n" +
        "    <div id=\"driverNameInfo\" style=\"color:red; font-weight: bold; display: none;\"><span\n" +
        "            class=\"glyphicon glyphicon-exclamation-sign\"></span><strong></strong></div>\n" +
        "</div>\n" +
        "<!--/.form-group -->\n" +
        "<!--/.form-group -->\n" +
        "<div class=\"form-group\">\n" +
        "    <label for=\"carNumber\"><span class=\"glyphicon glyphicon-remove\" style=\"color: rgb(140, 132, 251);\"></span>车牌号</label>\n" +
        "    <input type=\"text\" class=\"form-control\" id=\"carNumber\" placeholder=\"请输入车牌号\" onclick=\"check()\">\n" +
        "    <span id=\"carNumber-icon\" class=\"glyphicon glyphicon-remove\"\n" +
        "        style=\"float:right ; color: red; font-size: 20px;margin-top: -35px; display: none;\"></span>\n" +
        "    <div id=\"carNumberInfo\" style=\"color:red; font-weight: bold; display: none;\"><span\n" +
        "            class=\"glyphicon glyphicon-exclamation-sign\"></span><strong></strong></div>\n" +
        "</div>\n" +
        "<!--/.form-group -->\n"
    // 清空节点
    $("#signForm").empty();
    $("#signForm").append($html);
}
function  showFleetForm(){
    let $html="<!--/.form-group -->\n" +
        "<div class=\"form-group\">\n" +
        "    <label for=\"fleetName\"><span class=\"glyphicon glyphicon-user\" style=\"color: rgb(140, 132, 251);\"></span> 车队名字</label>\n" +
        "    <input type=\"text\" class=\"form-control\" id=\"fleetName\" placeholder=\"请输入车队名字\" onclick=\"check()\">\n" +
        "    <span id=\"fleetName-icon\" class=\"glyphicon glyphicon-remove\"\n" +
        "        style=\"float:right ; color: red; font-size: 20px;margin-top: -35px; display: none;\"></span>\n" +
        "    <div id=\"fleetNameInfo\" style=\"color:red; font-weight: bold; display: none;\"><span\n" +
        "            class=\"glyphicon glyphicon-exclamation-sign\"></span><strong></strong></div>\n" +
        "</div>\n" +
        "<!--/.form-group -->\n"
    // 清空节点
    $("#signForm").empty();
    $("#signForm").append($html);
}
function showOrganizerForm(){

    let $html="<!--/.form-group -->\n" +
        "<div class=\"form-group\">\n" +
        "    <label for=\"unit\"><span class=\"glyphicon glyphicon-user\" style=\"color: rgb(140, 132, 251);\"></span> 所属单位</label>\n" +
        "    <input type=\"text\" class=\"form-control\" id=\"unit\" placeholder=\"请输入所属单位\" onclick=\"check()\">\n" +
        "    <span id=\"unit-icon\" class=\"glyphicon glyphicon-remove\"\n" +
        "        style=\"float:right ; color: red; font-size: 20px;margin-top: -35px; display: none;\"></span>\n" +
        "    <div id=\"unitInfo\" style=\"color:red; font-weight: bold; display: none;\"><span\n" +
        "            class=\"glyphicon glyphicon-exclamation-sign\"></span><strong></strong></div>\n" +
        "</div>\n" +
        "<!--/.form-group -->"
    // 清空节点
    $("#signForm").empty();
    $("#signForm").append($html);
}
function showParticipantForm(){

   let  $html="<!--/.form-group -->\n" +
        "<div class=\"form-group\">\n" +
        "    <label for=\"participantName\"><span class=\"glyphicon glyphicon-user\" style=\"color: rgb(140, 132, 251);\"></span> 姓名</label>\n" +
        "    <input type=\"text\" class=\"form-control\" id=\"participantName\" placeholder=\"请输入您的姓名\" onclick=\"check()\">\n" +
        "    <span id=\"participantName-icon\" class=\"glyphicon glyphicon-remove\"\n" +
        "        style=\"float:right ; color: red; font-size: 20px;margin-top: -35px; display: none;\"></span>\n" +
        "    <div id=\"participantNameInfo\" style=\"color:red; font-weight: bold; display: none;\"><span\n" +
        "            class=\"glyphicon glyphicon-exclamation-sign\"></span><strong></strong></div>\n" +
        "</div>\n" +
        "<!--/.form-group -->"
    $html+="<!--/.form-group -->\n" +
        "\n" +
        "<div class=\"form-group\">\n" +
        "    <label for=\"email\"><span class=\"glyphicon glyphicon-envelope\" style=\"color: rgb(140, 132, 251);\"></span> 邮箱</label>\n" +
        "    <input type=\"text\" class=\"form-control\" id=\"email\" placeholder=\"请输入邮箱\" onclick=\"check()\">\n" +
        "    <span id=\"email-icon\" class=\"glyphicon glyphicon-remove\"\n" +
        "        style=\"float:right ; color: red; font-size: 20px;margin-top: -35px;display: none;\"></span>\n" +
        "    <!-- <strong style=\"float:right ; font-size: 15px; color:red; margin-top: -30px; \"><span id = \"account-icon\" class=\"glyphicon glyphicon-remove\" style = \"color: red;vertical-align:middle;margin-top: -3px;\"></span>邮箱地址不合法 </strong> -->\n" +
        "    <div id=\"emailInfo\" style=\"color:red; font-weight: bold; display: none;\"><span\n" +
        "            class=\"glyphicon glyphicon-exclamation-sign\"></span><strong></strong></div>\n" +
        "</div>\n" +
        "<!--/.form-group -->\n"
    // 清空节点
    $("#signForm").empty();
    $("#signForm").append($html);
}

$("#login").click(function () {
    let role = $("#role").val();
    // console.log($("#role").val());
    alert(role);
    var userAccount = $("#userAccount").val();
    var accountPass = $("#accountPass").val();
    var phoneKey = role + "Phone";
    var passKey = role + "Pass";
    var json = {
        // phoneKey: "" + driverPhone,
        // passKey: "" + driverPass
    };
    json[phoneKey] = userAccount;
    json[passKey] = accountPass;
    console.log(json);

    console.log(userAccount);
    $.ajax({
        type: "POST",
        dataType: "json",
        url: role +  '/login',
        contentType: "application/json",
        data: JSON.stringify(json),// 方法将一个 JavaScript 对象或值转换为 JSON 字符串
        success: function (jsonData, result) {
            console.log("data is :" + result);
            if (jsonData["data"]["token"]) {
                localStorage.setItem("conNCU", jsonData["data"]["token"]);
                window.location.href = role + "-index.html";
            } else {
                console.log(jsonData["data"]["token"]);
                alert("登录失败，请重试");
            }
        }
    });
})

