var hotel ={};
var liveRoom={};
var token;
var $hotelId;
var $participantId;
var participant={};
var $participantPhone;
var $conferenceId;
var room={};
$(function () {

    //获取token
    token = localStorage.getItem("conNCU");
    console.log(typeof (token));
    console.log(token);
    if (token == null || token == "null") {
        console.log("no token");
    } else {
        $hotelId = parseJwt(token).hotelId;/*获取用户信息*/
        getHotelInfo($hotelId);
        $(".img-circle").attr("src",hotel.hotelPhoto);
        console.log(parseJwt(token));
        console.log($hotelId);
    }
    //判断是否为未登录用户

    if (token == null || token == "null" || typeof ($hotelId) == "undefined" || $hotelId == undefined) {//未登录
        console.log("未登录");
        localStorage.setItem("conNCU", null);
        alert("请先登录！");
        window.location.href = "登录New.html";
    }
    if (localStorage.getItem("function") !== "null" && localStorage.getItem("function") !== null) {
        eval(localStorage.getItem("function"));
        localStorage.setItem("function", null);
    }
//获取酒店信息
    $("#get-hotel a").click(function () {
            console.log("信息获取");
            showHotelInfo();
    })
    $("#get-hotel-up").click(function () {
        console.log("信息获取");
        showHotelInfo();
    })
//安排住宿
    $("#to-wait-live a").click(function () {
        //console.log("success");
        findAllLiveRoomByHotelId();
        doLiveRoom();
    })
//住宿情况查询
    $("#to-all-live a").click(function () {
        //console.log("success");
        findAllLiveRoomByHotelId();
        showLiveRoomTable();
    })
    //查看酒店房间情况
    $("#to-check-room").click(function () {
        getRoomByHotelId();
        showRoom();
    })
    //点击 退出登录 按钮
    $(".login-out").click(function () {
        clearHotelInfo();
        //localStorage.clear();
        localStorage.setItem("conNCU", null);
        alert("退出成功");
        window.location.href = "登录New.html";
    })


});
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
                alert("更新头像成功!");
                location.reload();
                // $.alert({
                //     title: '提示信息',
                //     content: '更新头像成功!',
                // });
            } else {
                alert("更新头像失败!");
                // $.alert({
                //     title: '提示信息',
                //     content: '更新头像失败!',
                // });
            }
        },
        error:function() {
            alert("更新异常!");
            // $.alert({
            //     title: '提示信息',
            //     content: '更新异常!',
            // });
        }
    });
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

function  findAllLiveRoomByHotelId(){
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/liveRoom/findAllLiveRoomByHotelId",
        type: "get",
        dataType: "json",
        data: {
            'hotelId': $hotelId,
        },
        success: function (data) {
            //console.log(data);
            if (data["code"] === 200) {
                liveRoom = data["data"]["findAllLiveRoomByHotelId"];
                console.log(liveRoom);
            } else {
                alert("获取用户数据失败");
            }
        },
        error: function () {
            alert("获取用户数据失败!");
        },
    });
}

function showLiveRoomTable(){
    let $html="<div class=\"row\">\n" +
        "            <div class=\"col-md-12\">\n" +
        "                <div class=\"panel panel-default collapsed\">\n" +
        "                    <div class=\"panel-heading\">\n" +
        "                        入住情况表\n" +
        "                    </div>\n" +
        "                    <div class=\"panel-body\">\n" +
        "                        <table id=\"datatable\" class=\"table table-striped dt-responsive nowrap\">\n" +
        "                            <thead>\n" +
        "                                <tr>\n" +
        "                                    <th>入住人姓名</th>\n" +
        "                                    <th>入住人电话</th>\n" +
        "                                    <th>会议编号</th>\n" +
        "                                    <th>房间号</th>\n" +
        "                                    <th>操作</th>\n" +
        "                                </tr>\n" +
        "                            </thead>\n" +
        "\n" +
        "                            <tbody>\n"
    for (let i of liveRoom) {
        if (i.roomId!=null){
            $participantId=i.participantId;
            queryParticipantByParticipantId($participantId);
            $html +=
                "                                                <tr>\n" +
                "                                                    <td>" + participant.participantName + "</td>\n" +
                "                                                    <td>" + participant.participantPhone + "</td>\n" +
                "                                                    <td>" + i.conferenceId + "</td>\n" +
                "                                                    <td>" + i.roomId + "</td>\n" +
                "                                    <td><button type='button' class=\"btn btn-info\" onclick=\"resetLiveRoom(this)\" >重置</button>" +
                "                                        <button type='button' class=\"btn btn-danger\" onclick=\"deleteLiveRoomByAll(this)\">删除</button>"+
                "                                                   </td>\n" +
                "                                                </tr>\n";
        }

    }
        let $htmlEnd =
        "                            </tbody>\n" +
        "                        </table>\n" +
        "\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "        <!--end row-->\n" +
        "\n" +
        "        <!--end page content-->"
    // 清空节点
    $(".jumbotron").empty();
    $(".jumbotron").append($html+$htmlEnd);
    $('#datatable').dataTable();
}

function doLiveRoom(){
    let $html="<div class=\"row\">\n" +
        "            <div class=\"col-md-12\">\n" +
        "                <div class=\"panel panel-default collapsed\">\n" +
        "                    <div class=\"panel-heading\">\n" +
        "                        需入住人员表\n" +
        "                    </div>\n" +
        "                    <div class=\"panel-body\">\n" +
        "                        <table id=\"datatable\" class=\"table table-striped dt-responsive nowrap\">\n" +
        "                            <thead>\n" +
        "                                <tr>\n" +
        "                                    <th>入住人姓名</th>\n" +
        "                                    <th>入住人电话</th>\n" +
        "                                    <th>会议编号</th>\n" +
        "                                    <th>房间号</th>\n" +
        "                                    <th>操作</th>\n" +
        "                                </tr>\n" +
        "                            </thead>\n" +
        "\n" +
        "                            <tbody>\n"
    for (let i in liveRoom) {
        if (liveRoom[i].roomId==null){
            console.log(i);
            $participantId=liveRoom[i].participantId;
            queryParticipantByParticipantId($participantId);
            $html +=
                "                                                <tr>\n" +
                "                                                    <td>" + participant.participantName + "</td>\n" +
                "                                                    <td>" + participant.participantPhone + "</td>\n" +
                "                                                    <td>" + liveRoom[i].conferenceId + "</td>\n" +
                '                                   <td><input type="text" size="5px" id="roomId'+ i + '"></td>\n' +
                '                                   <td><button type="button" class="btn btn-info" onclick="updateLiveRoom( this ,' + i + ')">提交</button></td>\n'+
                "                                                </tr>\n";
        }
    }
    let $htmlEnd ="                            </tbody>\n" +
        "                        </table>\n" +
        "\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>"
    // 清空节点
    $(".jumbotron").empty();
    $(".jumbotron").append($html+$htmlEnd);
    $('#datatable').dataTable();
}
//给参会者分配房间
function updateLiveRoom(liveTable,i){
    getBothId(liveTable);
    var roomId = $("#roomId"+i).val();
    console.log(roomId);
    //检验输入房间是否存在
    $.ajax({
        async: false,
        // headers: {
        //     'token': token,
        // },
        url: "/room/getRoomByRoomId",
        type: "get",
        dataType: "json",
        data: {
            'hotelId': $hotelId,
            'roomId':roomId
        },
        success: function (data) {
            //console.log(data);
            if (data["code"] === 200) {
                Room = data["data"]["getRoomByRoomId"];
                console.log(Room);
            } else {
                alert("获取用户数据失败");
            }
        },
        error: function () {
            alert("获取用户数据失败!");
        },
    });
    //房间存在且没有安排
    if (Room!=null&&Room.isLive==0) {
        $.ajax({
            async: false,
            type: "POST",
            url: '/liveRoom/updateLiveRoom',
            contentType: "application/json",
           // headers: { 'token': localStorage.getItem("conNCU") },
            data: JSON.stringify({
                "participantId": $participantId,
                "hotelId":$hotelId,
                "conferenceId": $conferenceId,
                "roomId": roomId
            }),
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    //alert("设置成功");
                    //将房间设置为已经安排
                    $.ajax({
                        async: false,
                        type: "POST",
                        url: '/room/updateRoom',
                        contentType: "application/json",
                        // headers: { 'token': localStorage.getItem("conNCU") },
                        data: JSON.stringify({
                            "roomId": roomId,
                            "hotelId":$hotelId,
                            "isLive": 1
                        }),
                        success: function (jsonData, result) {
                            console.log(jsonData);
                            console.log(result);
                            if (jsonData['code'] === 200) {
                                alert("设置成功");

                            } else {
                                alert("设置失败");
                                //location.reload();
                            }
                        },
                    });
                    //location.reload();
                } else {
                    alert("设置失败");
                    //location.reload();
                }
            },
        });
        findAllLiveRoomByHotelId();
        doLiveRoom();
    }
    else if (Room!=null&&Room.isLive==1){
        alert("房间已经安排，请检查！");
    }
    else {
        alert("输入房间号不存在！");
    }
}

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
            if (data["code"] === 200) {
                hotel = data["data"]["getHotelInfo"];
                console.log(hotel);
                $("#head_photo").attr("src",hotel.hotelPhoto);
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
    let $html= " <div class=\"container\">\n" +
        "            <!--        页面内容-->\n" +
        "            <div class=\"row\" style=\"margin-top:30px;\">\n" +
        "\n" +
        "                <div class=\"col-sm-3\">\n" +
        "                    <div class=\"panel panel-default\">\n" +
        "                        <div class=\"panel-body\">\n" +
        "                            <!--                        头像展示-->\n" +
        "                            <div class=\"head_photo_container\">\n" +
        "                                <img id=\"head_photo\" class=\"img-responsive center-block\" src=\""+hotel.hotelPhoto+"\" style=\"\">\n" +
        "                            </div>\n" +
        "                            <hr>\n" +
        "\n" +
        "                            <!--                        换头像-->\n" +
        "                            <div align=\"center\">\n" +
        "                                <form id=\"form_submit_photo\" class=\"form-horizontal\">\n" +
        "                                    <input type=\"hidden\" name=\"role\" value=\"hotel\">\n" +
        "                                    <input type=\"hidden\" name=\"roleId\" value=\"" + hotel.hotelId + "\" id=\"roleId\">\n" +
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
        //getHotelInfo($hotelId);
        $html+="                <div class=\"col-sm-8\">\n" +
        "                    <div class=\"panel panel-default\">\n" +
        "                        <div class=\"panel-body\">\n" +
        "                            <div class=\"row\" style=\"margin-left: 20px; margin-right: 20px;\">\n" +
        "                                <h3>酒店信息</h3>\n" +
        "                                <hr>\n" +
        "                                <form id=\"hotelForm\" class=\"form-horizontal\" action=\"\">\n" +
           " <input type=\"hidden\" name=\"hotelId\" id=\"hotelId\" value=\"\">"+
        "                                    <div class=\"form-group\">\n" +
        "                                        <label class=\"control-label col-sm-3\"><label\n" +
        "                                                for=\"hotelName\">酒店名：</label></label>\n" +
        "                                        <div class=\"col-sm-7\"><input type=\"text\" name=\"hotelName\" value=\"" + hotel.hotelName + "\"\n" +
        "                                                class=\"form-control\" maxlength=\"40\" required=\"\" id=\"hotelName\"\n" +
        "                                                placeholder=\"酒店名\"></div>\n" +
        "                                        <span class=\"text-danger small\"></span>\n" +
        "                                    </div>\n" +
        "\n" +
        "                                    <div class=\"form-group\">\n" +
        "                                        <label class=\"control-label col-sm-3\"><label\n" +
        "                                                for=\"hotelPhone\">电话号码:</label></label>\n" +
        "                                        <div class=\"col-sm-7\"><input type=\"text\" name=\"hotelPhone\" value=\"" + hotel.hotelPhone + "\"\n" +
        "                                                class=\"form-control\" style=\"margin-top: 0;\" maxlength=\"50\" required=\"\"\n" +
        "                                                id=\"hotelPhone\" placeholder=\"电话号码\"></div>\n" +
        "                                        <span class=\"text-danger small\"></span>\n" +
        "                                    </div>\n" +
        "\n" +
        "                                    <div class=\"form-group\">\n" +
        "                                        <label class=\"control-label col-sm-3\"><label\n" +
        "                                                for=\"hotelLocation\">地址:</label></label>\n" +
        "                                        <div class=\"col-sm-7\"><input type=\"text\" name=\"hotelLocation\" value=\"" + hotel.hotelLocation + "\"\n" +
        "                                                class=\"form-control\" style=\"margin-top: 0;\" maxlength=\"50\" required=\"\"\n" +
        "                                                id=\"hotelLocation\" placeholder=\"地址\" ></div>\n" +
        "                                        <span class=\"text-danger small\"></span>\n" +
        "                                    </div>\n" +
        "\n" +
        "                                    <div class=\"form-group\">\n" +
        "                                        <label class=\"control-label col-sm-3\"><label for=\"hotelPass\">密码:</label></label>\n" +
        "                                        <div class=\"col-sm-7\"><input type=\"password\" name=\"hotelPass\" class=\"form-control\"\n" +
        "                                                maxlength=\"40\" id=\"hotelPass\" value=\"" + hotel.hotelPass + "\"></div>\n" +
        "                                        <span class=\"text-danger small\"></span>\n" +
        "                                    </div>\n" +
        "\n" +
        "                                    <div class=\"form-group\">\n" +
        "                                        <label class=\"control-label col-sm-3\"><label\n" +
        "                                                for=\"repeatHotelPass\">重复密码:</label></label>\n" +
        "                                        <div class=\"col-sm-7\"><input type=\"password\" name=\"repeatHotelPass\"\n" +
        "                                                class=\"form-control\" maxlength=\"40\" id=\"repeatHotelPass\" value=\"" + hotel.hotelPass + "\"></div>\n" +
        "                                        <span class=\"text-danger small\"></span>\n" +
        "                                    </div>\n" +
        "\n" +
        "                                    <div class=\"form-group\">\n" +
        "                                        <label class=\"control-label col-sm-3\"><label\n" +
        "                                                for=\"hotelInfo\">酒店介绍:</label></label>\n" +
        "                                        <div class=\"col-sm-7\"><textarea name=\"hotelInfo\" cols=\"40\" rows=\"4\"\n" +
        "                                                class=\"form-control\" maxlength=\"500\" id=\"hotelInfo\">" +hotel.hotelInfo+ "</textarea></div>\n" +
        "                                        <span class=\"text-danger small\"></span>\n" +
        "                                    </div>\n" +
        "\n" +
        "\n" +
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
    // 清空节点
    $(".jumbotron").empty();
    $(".jumbotron").append($html);
}
/*退出登陆时清空用户信息*/
function clearHotelInfo() {
    hotel=undefined;
}

//信息表单前端验证

function validForm() {
    jQuery.validator.addMethod("mobile", function(value, element) {
        var length = value.length;
        var mobile = /^1[3456789]\d{9}$/
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "手机号码格式错误");
    return $("#hotelForm").validate({
        rules: {
            hotelName: {
                required: true,
                minlength: 1,
                maxlength: 13,
            },
            hotelPass: {
                minlength: 6,
                maxlength: 20
            },
            repeatHotelPass: {
                minlength: 6,
                maxlength: 20,
                equalTo: "#hotelPass"
            },
            hotelPhone: {
                minlength: 11,
                maxlength: 11,
                mobile: true
            },
            hotelLocation: {
                minlength: 2,
                maxlength: 20
            }
        },
        messages: {
            hotelName: {
                minlength: "姓名名至少包含1个字符",
                maxlength: "用户名长度不能超过13个字符"
            },
            hotelPass: {
                minlength: "密码长度不能少于6个字符",
                maxlength: "密码长度不能多于20个字符"
            },
            repeatHotelPass: {
                minlength: "密码长度不能少于6个字符",
                maxlength: "密码长度不能多于20个字符",
                equalTo: "两次密码输入不一致"
            },
            hotelPhone: {
                minlength: "请输入正确的电话号码",
                maxlength: "请输入正确的电话号码"
            },
            hotelLocation: {
                minlength: "地址描述过短",
                maxlength: "地址描述过长"
            }
        }
    });
}

//酒店信息更新
function submitChange() {
    //console.log("修改中...");
    if (!validForm().form()) {
        alert("信息有误")
        return;
    }
        $.ajax({
             async: false,
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
                    // localStorage.setItem("function", "showHotelInfo()");
                    // location.reload();
                } else {
                    alert("修改失败");
                    //location.reload();
                }
            },
        });
    // for (let i = 0; i < 500000000; i++) {
    //
    // }
    //     showHotelInfo();
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
/*获取token里面的用户数据*/
function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}
