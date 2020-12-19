function getAllFleetDriver($fleetId) {
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/driver/getAllFleetDriver",
        type: "get",
        dataType: "json",
        data: {
            'fleetId': $fleetId,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                allFleetDriver = data["data"]["getAllFleetDriver"];
                console.log(fleet);
            } else {
                alert("获取用户数据失败");
            }
        },
        error: function () {
            alert("获取用户数据失败");
        },
    });
    showFleetDriver();
}

function showFleetDriver() {
    console.log(allFleetDriver);
    let $htmlStart =
        "                            <div class=\"row\">\n" +
        "                                <div class=\"col-md-12\">\n" +
        "                                    <div class=\"panel panel-default collapsed\">\n" +
        "                                        <div class=\"panel-heading\">\n" +
        "                                            司机列表\n" +
        "                                        </div>\n" +
        "                                        <div class=\"panel-body\">\n" +
        "                                            <table class=\"table table-striped dt-responsive nowrap\" id=\"datatable\">\n" +
        "                                                <thead>\n" +
        "                                                <tr>\n" +
        "                                                    <th>司机id</th>\n" +
        "                                                    <th>司机姓名</th>\n" +
        "                                                    <th>司机电话</th>\n" +
        "                                                    <th>司机车牌</th>\n" +
        "                                                    <th>是否空闲</th>\n" +
        "                                                    <th>操作</th>\n" +
        "                                                </tr>\n" +
        "                                                </thead>\n" +
        "                                                <tbody>\n"
    let $html = "";

    for (let i of allFleetDriver) {
        // if (i.finishPickup === true) {
        //     continue;
        // }
        // queryParticipantByParticipantId(i.participantId);
        // console.log(participant);
        $html +=
            "                                                <tr>\n" +
            "                                                    <td>" + fix(i.driverId, 4) + "</td>\n" +
            "                                                    <td>" + i.fleetName + "</td>\n" +
            "                                                    <td>" + i.fleetPhone + "</td>\n" +
            "                                                    <td>" + i.trainNumber + "</td>\n" +
            "                                                    <td>" + i.toTime + "</td>\n" +
            "                                                    <td>" + i.returnTime + "</td>\n" +
            "                                                    <td>" + (i.finishPickup ? "是" : "否") + "</td>\n" +
            "                                    <td><button type='button' class=\"btn btn-info\" onclick=\"finish(this)\" >完成</button>" +
            "                                                </tr>\n";
    }
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






function getBothId(liveTable) {
    $participantPhone = $(liveTable).parent().parent("tr").children('td').eq(1).html();//从0开始
    $conferenceId = $(liveTable).parent().parent("tr").children('td').eq(2).html();
    queryParticipantByParticipantPhone($participantPhone);
    $participantId = participant.participantId;
}

//重新设置房间号
function resetLiveRoom(liveTable) {
    getBothId(liveTable);
    if (1) {
        $.ajax({
            // async: false,
            type: "POST",
            url: '/liveRoom/updateLiveRoom',
            contentType: "application/json",
            // headers: { 'token': localStorage.getItem("conNCU") },
            data: JSON.stringify({
                "participantId": $participantId,
                "hotelId": $hotelId,
                "conferenceId": $conferenceId,
                "roomId": null
            }),
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    alert("重置成功");
                    // showDriverInfo(driver)
                    location.reload();
                } else {
                    alert("重置失败");
                    location.reload();
                }
            },
        });
    } else {
        alert("信息格式有误，请重新填写！");
    }
}

//删除住宿记录
function deleteLiveRoomByAll(liveTable) {
    if (confirm("确定删除吗？")) {
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
                "hotelId": $hotelId,
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
                    location.reload();
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