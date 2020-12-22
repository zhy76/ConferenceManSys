
function getBothId(liveTable){
    $participantPhone = $(liveTable).parent().parent("tr").children('td').eq(1).html();//从0开始
    $conferenceId = $(liveTable).parent().parent("tr").children('td').eq(2).html();
    queryParticipantByParticipantPhone($participantPhone);
    $participantId=participant.participantId;
}
//重新设置房间号
function resetLiveRoom(liveTable){
    getBothId(liveTable);
    if (1) {
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
                "roomId":null
            }),
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    alert("重置成功");

                    //$(".container").load(location.href + ".container");
                    //location.reload();
                } else {
                    alert("重置失败");
                    //location.reload();
                }
            },
        });
        for (let i=0;i<500000000;i++){

        }
        showLiveRoomTable();
    } else {
        alert("信息格式有误，请重新填写！");
    }
}

//删除住宿记录
function deleteLiveRoomByAll(liveTable){
    if(confirm("确定删除吗？")){
        getBothId(liveTable);
        $.ajax({
            async: false,
            headers: {
                'token': token,
            },
            url: "/liveRoom/deleteLiveRoomByAll",
            type: "get",
            dataType: "json",
            data: {
                "participantId": $participantId,
                "hotelId":$hotelId,
                "conferenceId": $conferenceId
            },
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    alert("删除成功");
                    location.reload();
                } else {
                    alert("删除失败");
                    //location.reload();
                }
            },
        });
    }

}
function queryParticipantByParticipantId($participantId) {
    $.ajax({
        async: false,
        // headers: {
        //     'token': token,
        // },
        url: "/participant/queryParticipantByParticipantId",
        type: "get",
        dataType: "json",
        data: {
            'participantId': $participantId,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                participant = data["data"]["queryParticipantByParticipantId"];
                console.log(participant);
            } else {
                alert("获取用户数据失败");
            }
        },
        error: function () {
            alert("获取用户数据失败");
        },
    });
}
function queryParticipantByParticipantPhone($participantPhone) {
    $.ajax({
        async: false,
        // headers: {
        //     'token': token,
        // },
        url: "/participant/queryParticipantByParticipantPhone",
        type: "get",
        dataType: "json",
        data: {
            'participantPhone': $participantPhone,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                participant = data["data"]["queryParticipantByParticipantPhone"];
                console.log(participant);
            } else {
                alert("获取用户数据失败");
            }
        },
        error: function () {
            alert("获取用户数据失败");
        },
    });
}
//查找酒店房间
function  getRoomByHotelId(){
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/room/getRoomByHotelId",
        type: "get",
        dataType: "json",
        data: {
            'hotelId': $hotelId,
        },
        success: function (data) {
            //console.log(data);
            if (data["code"] === 200) {
                room = data["data"]["getRoomByHotelId"];
                console.log(room);
            } else {
                alert("获取用户数据失败");
            }
        },
        error: function () {
            alert("获取用户数据失败!");
        },
    });
}
//展示房间
function showRoom(){
    let $html="<div class=\"row\">\n" +
        "            <div class=\"col-md-12\">\n" +
        "                <div class=\"panel panel-default collapsed\">\n" +
        "                    <div class=\"panel-heading\">\n" +
        "                        房间表\n" +
        "                    </div>\n" +
        "                    <div class=\"panel-body\">\n" +
        '<div class="box">' +
        '    <div class="content">' +
        '        <!--添加按钮及bootstrap的模态框-->' +
        '        <div class="export">' +
        '            <!--按钮开始-->' +
        '            <button id="repair" type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#renyuan">' +
        '                <span>增加房间</span>' +
        '            </button>' +
        '            <!--按钮结束-->' +
        '            <div class="modal fade" id="renyuan">' +
        '                <div class="modal-dialog modal-lg modal_position">' +
        '                    <div class="modal-content">' +
        '                        <div class="modal-header">' +
        '                            <h4 class="modal-title" id="">增加房间</h4>' +
        '                            <button type="button" class="close" data-dismiss="modal">&times;</button>' +
        '                        </div>\n' +
        '                        <!-- 模态弹窗部分开始 -->' +
        '                        <div class="modal-body" id="modal">' +
        '<table id="xztb" class="table">' +
        '                                <!--新修改弹窗的样式-->' +
        '                                <tbody>' +
        '                                <tr>' +
        '                                      <td class="tb_bg"><label for="">房间号码</label></td>' +
        '                                      <td><input type="text" name="roomName" id="roomName" value=\"\" ></td>' +
        '                                </tr>' +
        '                                <tr>' +
        '                                    <td class="tb_bg"><label for="">房间类型</label></td>' +
        '                                    <td><input type="text" name="roomType" id="roomType" placeholder="单人间" value=\"\"></td>' +
        '                                </tr>' +
        '                                </tbody>' +
        '                            </table>' +
        '</div>'+
        '                        <div class="modal-footer">' +
        '                            <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>' +
        '                            <button id="add_btn" type="button" class="btn btn-success" onclick=\"addRoom()\">增加</button>' +
        '                        </div>' +
        '                        <!--模态弹窗部分结束 -->' +
        '</div>'+
        '</div>'+
        '</div>'+
        '</div>'+'</div>'+'</div>'+
        '<hr/>'+
        "                        <table id=\"datatable\" class=\"table table-striped dt-responsive nowrap\">" +
        "                            <thead>\n" +
        "                                <tr>\n" +
        "                                    <th>房间标号</th>\n" +
        "                                    <th>房间类型</th>\n" +
        "                                    <th>是否安排</th>\n" +
        "                                    <th>管理操作</th>\n" +
        "                                </tr>\n" +
        "                            </thead>\n" +
        "\n" +
        "                            <tbody>\n"
    for (let i of room) {
            $html +=
                "                                                <tr>\n" +
                "                                                    <td>" + i.roomId + "</td>\n" +
                "                                                    <td>" + i.roomType + "</td>\n"
        if(i.isLive==1){
            $html +="                                                    <td>" + '是' + "</td>\n"
        }
        else{
            $html +="                                                    <td>" + '否' + "</td>\n"
        }
        $html += "                                    <td><button type='button' class=\"btn btn-info\" onclick=\"updateRoom(1,this)\" >入住</button>" +
                "                                        <button type='button' class=\"btn btn-info\" onclick=\"updateRoom(0,this)\" >退房</button>" +
                "                                        <button type='button' class=\"btn btn-danger\" onclick=\"deleteRoomById(this)\">删除</button>"+
                "                                                   </td>\n" +
                "                                                </tr>\n";
        }

    $html+=
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
    $(".jumbotron").append($html);
    $('#datatable').dataTable();
}

function updateRoom(a,roomTable){
    var $roomId = $(roomTable).parent().parent("tr").children('td').eq(0).html();
    var $roomType = $(roomTable).parent().parent("tr").children('td').eq(1).html();
    if (1) {
        $.ajax({
            async: false,
            type: "POST",
            url: '/room/updateRoom',
            contentType: "application/json",
            // headers: { 'token': localStorage.getItem("conNCU") },
            data: JSON.stringify({
                "roomId": $roomId,
                "hotelId":$hotelId,
                "isLive": a,
                "roomType":$roomType
            }),
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    alert("设置成功");
                    location.reload();
                } else {
                    alert("设置失败");
                    //location.reload();
                }
            },
        });
        // for (let i=0;i<500000000;i++){
        //
        // }
        // showRoom();
    } else {
        alert("信息格式有误！");
    }
}
function deleteRoomById(roomTable){
    var $roomId = $(roomTable).parent().parent("tr").children('td').eq(0).html();
    if(confirm("确定删除吗？")){
        $.ajax({
            async: false,
            url: "/room/deleteRoomById",
            type: "get",
            dataType: "json",
            data: {
                'hotelId': $hotelId,
                "roomId": $roomId,
            },
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    alert("删除成功");
                    location.reload();
                } else {
                    alert("删除失败");
                    //location.reload();
                }
            },
        });
    }
}
function addRoom(){
    var $roomId= $("#roomName").val();
    var roomType = $("#roomType").val();
    // alert($roomId);
    // alert(roomType);
    $.ajax({
        async: false,
        type: "POST",
        url: '/room/addRoom',
        contentType: "application/json",
        // headers: { 'token': localStorage.getItem("conNCU") },
        data: JSON.stringify({
            "roomId": $roomId,
            "hotelId":$hotelId,
            "isLive": 0,
            "roomType":roomType
        }),
        success: function (jsonData, result) {
            console.log(jsonData);
            console.log(result);
            if (jsonData['code'] === 200) {
                alert("设置成功");
                location.reload();
            } else {
                alert("设置失败");
                //location.reload();
            }
        },
    });
}