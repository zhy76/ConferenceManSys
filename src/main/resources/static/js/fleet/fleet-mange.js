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
            "                                                    <td>" + i.driverId + "</td>\n" +
            "                                                    <td>" + i.driverName + "</td>\n" +
            "                                                    <td>" + i.driverPhone + "</td>\n" +
            "                                                    <td>" + i.carNumber + "</td>\n" +
            "                                                    <td>" + (i.assign ? "是" : "否") + "</td>\n" +
            // "                                    <td><button type='button' class=\"btn btn-info\" onclick=\"finish(this)\" >完成</button>" +
            "                                    <td><button type='button' class=\"btn btn-info\" style=\"background-color:#ff4b00\" onclick=\"deleteDriver(this)\" >删除</button>" +
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

/**
 * 删除司机功能
 * @param driverTable
 */
function deleteDriver(driverTable) {
    $driverId = $(driverTable).parent().parent("tr").children('td').eq(0).html();//从0开始
    console.log($driverId);
    if (confirm("确定删除吗？")) {

        $.ajax({
            async: false,
            headers: {
                'token': token,
            },
            url: "/driver/deleteDriver",
            type: "get",
            dataType: "json",
            data: {
                "driverId": $driverId
            },
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    alert("删除成功");
                    // location.reload();
                } else {
                    alert("删除失败");
                    // location.reload();
                }
            },
        });
    }
}

/**
 * 得到所有的接送
 * @param $fleetId
 */
let getAllFleetPickUp;

function getAllFleetDriverPickUp($fleetId) {
    // /pickUp/getAllFleetPickUp
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/pickUp/getAllFleetPickUp",
        type: "get",
        dataType: "json",
        data: {
            "fleetId": $fleetId
        },
        success: function (jsonData, result) {
            console.log(jsonData);
            console.log(result);
            if (jsonData['code'] === 200) {
                getAllFleetPickUp = jsonData['data']['getAllFleetPickUp'];

                // location.reload();
            } else {
                alert("删除失败");
                // location.reload();
            }
        },
    });
}

/**
 * 得到司机的信息
 */
function getDriverInfo($driverId) {
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/driver/getDriverInfoById",
        type: "get",
        dataType: "json",
        data: {
            'driverId': $driverId,
        },
        success: function (data) {
            console.log($driverId);
            console.log(data);
            if (data["code"] === 200) {
                driver = data["data"]["getDriverInfoById"];
                console.log(driver);
                alert("driver")
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
 * 通过会议id查名称
 */
function getConferenceName($conferenceId) {
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/conference/showConferenceById",
        type: "get",
        dataType: "json",
        data: {
            'conferenceId': $conferenceId,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                // alert("con");
                conference = data["data"]["conference"];
                console.log(conference);
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
 * 展示待车队的接送
 */

function showAllFleetAllPickUp() {
    getAllFleetDriverPickUp($fleetId);
    getAllFleetDriver($fleetId)
    console.log(getAllFleetPickUp);
    let $htmlStart =
        "                            <div class=\"row\">\n" +
        "                                <div class=\"col-md-12\">\n" +
        "                                    <div class=\"panel panel-default collapsed\">\n" +
        "                                        <div class=\"panel-heading\">\n" +
        "                                            所有接送记录\n" +
        "                                        </div>\n" +
        "                                        <div class=\"panel-body\">\n" +
        "                                            <table class=\"table table-striped dt-responsive nowrap\" id=\"datatable\">\n" +
        "                                                <thead>\n" +
        "                                                <tr>\n" +
        "                                                    <th>接送号</th>\n" +
        // "                                                    <th>司机</th>\n" +
        "                                                    <th>参会者</th>\n" +
        "                                                    <th>会议</th>\n" +
        "                                                    <th>航班</th>\n" +
        "                                                    <th>到达时间</th>\n" +
        "                                                    <th>离开时间</th>\n" +
        // "                                                    <th>状态</th>\n" +
        "                                                    <th>司机</th>\n" +
        "                                                </tr>\n" +
        "                                                </thead>\n" +
        "                                                <tbody>\n"
    let $html = "";

    for (let i of getAllFleetPickUp) {
        // if (i.finishPickup === true && flag === 1) {
        //     continue;
        // } else if (i.finishPickup === false && flag === 2) {
        //
        // }
        console.log(typeof i.driverId);
        getConferenceName(i.conferenceId);
        // getDriverInfo(i.driverId);
        queryParticipantByParticipantId(i.participantId);
        let driverName = "未分配";
        if (i.driverId !== -1) {
            getDriverInfo(i.driverId);driverName = driver.driverName;
        } else {
            driverName = "未分配";
        }

        console.log(conference);
        $html +=
            "                                                <tr>\n" +
            "                                                    <td>" + i.pickUpId + "</td>\n" +
            // "                                                    <td>" + driver.driverName + "</td>\n" +
            "                                                    <td>" + participant.participantName + "</td>\n" +
            "                                                    <td>" + conference.conferenceName + "</td>\n" +
            "                                                    <td>" + i.trainNumber + "</td>\n" +
            "                                                    <td>" + i.toTime + "</td>\n" +
            "                                                    <td>" + i.returnTime + "</td>\n" +
            "                                                    <td>" + driverName + "</td>\n"
            // "                                                    <td>" + (i.finishPickup ? "是" : "否") + "</td>\n" +
        //     "                                    <td><select class=\"form-control form-control-line\" name=\"account\" id='driverId'>"
        // for (let it of allFleetDriver) {
        //     $html += "<option value='" + it.driverId + "'>" + it.driverId + '-' + it.driverName + "</option>"
        // }
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
/**
 * 展示已分配车队的接送
 */

function showAllFleetAssignPickUp() {
    getAllFleetDriverPickUp($fleetId);
    getAllFleetDriver($fleetId)
    console.log(getAllFleetPickUp);
    let $htmlStart =
        "                            <div class=\"row\">\n" +
        "                                <div class=\"col-md-12\">\n" +
        "                                    <div class=\"panel panel-default collapsed\">\n" +
        "                                        <div class=\"panel-heading\">\n" +
        "                                            所有接送记录\n" +
        "                                        </div>\n" +
        "                                        <div class=\"panel-body\">\n" +
        "                                            <table class=\"table table-striped dt-responsive nowrap\" id=\"datatable\">\n" +
        "                                                <thead>\n" +
        "                                                <tr>\n" +
        "                                                    <th>接送号</th>\n" +
        // "                                                    <th>司机</th>\n" +
        "                                                    <th>参会者</th>\n" +
        "                                                    <th>会议</th>\n" +
        "                                                    <th>航班</th>\n" +
        "                                                    <th>到达时间</th>\n" +
        "                                                    <th>离开时间</th>\n" +
        // "                                                    <th>状态</th>\n" +
        "                                                    <th>分配司机</th>\n" +
        "                                                    <th>操作</th>\n" +
        "                                                </tr>\n" +
        "                                                </thead>\n" +
        "                                                <tbody>\n"
    let $html = "";

    for (let i of getAllFleetPickUp) {
        // if (i.finishPickup === true && flag === 1) {
        //     continue;
        // } else if (i.finishPickup === false && flag === 2) {
        //
        // }
        if (i.driverId === null || i.driverId === 'null') continue;
        getConferenceName(i.conferenceId);
        getDriverInfo(i.driverId);

        queryParticipantByParticipantId(i.participantId);
        console.log(conference);
        $html +=
            "                                                <tr>\n" +
            "                                                    <td>" + i.pickUpId + "</td>\n" +
            // "                                                    <td>" + driver.driverName + "</td>\n" +
            "                                                    <td>" + participant.participantName + "</td>\n" +
            "                                                    <td>" + conference.conferenceName + "</td>\n" +
            "                                                    <td>" + i.trainNumber + "</td>\n" +
            "                                                    <td>" + i.toTime + "</td>\n" +
            "                                                    <td>" + i.returnTime + "</td>\n" +
            // "                                                    <td>" + (i.finishPickup ? "是" : "否") + "</td>\n" +
            "                                    <td><select class=\"form-control form-control-line\" name=\"account\" id='driverId'>"
        if (driver === null || driver ==='null') {
            $html += "<option value='" + null + "'>未分配</option>";
        } else {
            $html += "<option value='" + i.driverId + "'>" + i.driverId + '-' + driver.driverName + "</option>";
        }

        for (let it of allFleetDriver) {
            if (it.driverId === i.driverId) continue;
            $html += "<option value='" + it.driverId + "'>" + it.driverId + '-' + it.driverName + "</option>"
        }
        $html +=
            "                                                        </select>\n" +
            "</td>\n" +
            "                                    <td><button type='button' class=\"btn btn-info\" onclick=\"setDriver(this)\">修改</button></td>\n" +
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

/**
 * 分派司机
 */
function setDriver($self) {
    let $pickUpId = $($self).parent().parent("tr").children('td').eq(0).html();//从0开始
    alert($pickUpId);
    if (1) {
        $.ajax({
            // async: false,
            type: "get",
            url: '/pickUp/updatePickUp',
            contentType: "json",
            // headers: { 'token': localStorage.getItem("conNCU") },
            data: {
                "pickUpId": $pickUpId,
                "driverId": $("#driverId").val(),
                "fleetId": $fleetId
            },
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    alert("分配成功");
                    // showDriverInfo(driver)
                    location.reload();
                } else {
                    alert("分配失败");
                    location.reload();
                }
            },
        });
    } else {
        alert("分配失败2！");
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


function showFleetConference() {
    getConference($fleetId);

}


/**
 * 通过车队查会议
 * @param $fleetId
 */
function getConference($fleetId) {
    $.ajax({
        async: false,
        // headers: {
        //     'token': token,
        // },
        url: "/conference/queryConferenceByFleetId",
        type: "get",
        dataType: "json",
        data: {
            'fleetId': $fleetId,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                participant = data["data"]["conference"];
                console.log(participant);
            } else {
                alert("获取会议数据失败");
            }
        },
        error: function () {
            alert("获取会议数据失败");
        },
    });
}