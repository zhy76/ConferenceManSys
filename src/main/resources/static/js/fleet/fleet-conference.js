// let organizer;
// let token;
// let fleet;
// let conference;
// let $fleetId;
// let $participantId;
// let joinConference;
// let driver;
// let pickUp;
// let participant;
function fleetConference() {
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) $('.back-to-top').fadeIn('slow');
        else $('.back-to-top').fadeOut('slow');
    });

    token = localStorage.getItem("hcs");
    console.log(token);
    $fleetId = parseJwt(token).fleetId;
    getAllFleetDriver($fleetId);
    $.ajax({
        async: false,
        url: "/conference/queryConferenceByFleetId",
        type: "get",
        dataType: "json",
        data: {
            "fleetId": $fleetId
        },
        success: function (data) {
            var listConference = "";
            // console.log(data);
            // console.log(typeof data)
            // data = JSON.parse(data)
            // console.log(typeof data)
            // console.log(data['data']['conferencesList'])
            var conferences = data['data']['conference'];
            // console.log(conferences);
            // console.log(conferences);
            for (var i = 0; i < conferences.length; i++) {
                // console.log(conferences[i]);
                var conferenceName = conferences[i].conferenceName;
                var conferenceLocation = conferences[i].conferenceLocation;
                var conferenceStart = conferences[i].conferenceStart;
                var conferenceEnd = conferences[i].conferenceEnd;
                var conferenceId = conferences[i].conferenceId;
                // console.log(conferenceId);
                var organizerId = conferences[i].organizerId;
                // alert(organizerId);
                // alert(typeof organizerId);

                getOrganizerInfo(organizerId);
                getNeedPickUpParticipant(conferenceId);

                var organizerUnit = organizer.organizerUnit;
                console.log(conferenceId);
                listConference += '   <div class="col-lg-3" style = "margin: 0px 10px;">' +
                    '                        <a href = "#">' +
                    '                            <div class="items">' +
                    '                                <img class="img-responsive" src="picture/conferenceImg.jpeg" />' +
                    '                                <h4>' + conferenceName + '</h4>' +
                    '                                <p> <span class="iconfont icon-dizhi"></span> ' + conferenceLocation + '</p>' +
                    '                                <p> <span class="iconfont icon-shijian"></span>' + conferenceStart + '-' + conferenceEnd + '</p>' +
                    '                                <p> <span class="iconfont icon-zhubandanwei"></span> ' + organizerUnit + '</p>' +
                    '                            </div>' +
                    '                        </a>' +
                    '<div class="box">' +
                    '    <div class="content">' +
                    '        <!--添加按钮及bootstrap的模态框-->' +
                    '        <div class="export">' +
                    '            <!--按钮开始-->' +
                    '            <button id="new_add" type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#renyuan' + conferenceId + '">' +
                    '                <span>安排</span>' +
                    '            </button>' +
                    '            <!--按钮结束-->' +
                    '            <div class="modal fade" id="renyuan' + conferenceId + '">' +
                    '                <div class="modal-dialog modal-lg modal_position">' +
                    '                    <div class="modal-content">' +
                    '                        <div class="modal-header">' +
                    '                            <h4 class="modal-title" id="">为' + conferenceName + '分配司机</h4>' +
                    '                            <button type="button" class="close" data-dismiss="modal">&times;</button>' +
                    '                        </div>\n' +
                    '                        <!-- 模态弹窗部分开始 -->' +
                    '                        <div class="modal-body">' +
                    '                            <table id="xztb" class="table">' +
                    '                                <!--新修改弹窗的样式-->' +
                    '                                <tbody>'
                listConference += `                            <table class="table table-striped dt-responsive nowrap" id="datatable">
                                <thead>
                                <tr>
                                    <th>参会者姓名</th>
                                    <th>电话号码</th>
                                    <th>到达时间</th>
                                    <th>离开时间</th>
                                    <th>航班</th>
                                    <th>司机姓名</th>
                                    <th>司机电话</th>
                                    <th>状态</th>
                                </tr>
                                </thead>
                                <tbody>`
                for (let it of joinConference) {
                    /**
                     * 通过会议id和参加者id查找司机id
                     * */
                    // getDriverInfo(it.driverId);
                    driver = null;
                    getPickUpByConferenceIdAndParticipantId(it.participantId, it.conferenceId);
                    console.log(it);
                    console.log(pickUp);
                    if (pickUp !== null&& pickUp !== "null" && pickUp.driverId !== -1) {
                        getDriverInfo(pickUp.driverId);
                    }
                    getParticipantInfo(it.participantId);
                    let driverName = "未分配";
                    let driverPhone = "未分配";
                    console.log(driver);
                    // alert(1111111111111);
                    if (driver != null && driver !== "null") {
                        driverName = driver['driverName'];
                        driverPhone = driver['driverPhone'];
                        // driver['driverName'] = "未分配";
                        // driver['driverPhone'] = "未分配";
                    }
                    listConference +=
                        `
                                <tr id="${it.participantId}">
                                    <td>${participant.participantName}</td>
                                    <td>${participant.participantPhone}</td>
                                    <td>${it.toTime}</td>
                                    <td>${it.returnTime}</td>
                                    <td>${it.trainNumber}</td>`
                    let flag = 0;
                    listConference += `<td><select class="form-control form-control-line" name="account" id='driverId${it.conferenceId}-${it.participantId}'>`
                    if (driverName === '未分配') {
                        flag = 1;
                        listConference += "<option value='-1'>未分配</option>";
                    } else {
                        listConference += "<option value='" + driver.driverId + "'>" + driver.driverId + '-' + driver.driverName + "</option>"
                    }
                    for (let it1 of allFleetDriver) {
                        if (flag === 0) {
                            // alert(11111);
                            if (driver.driverId === it1.driverId) continue;
                        }
                        listConference += "<option value='" + it1.driverId + "'>" + it1.driverId + '-' + it1.driverName + "</option>"
                    }
                    listConference +=
                        "                                                        </select>\n"
                    "</td>"
                    listConference +=      `<td>${driverPhone}</td>
                                <td>${driverName === "未分配"?"未分配":"已分配"}</td>
                                </tr>
                                `
                }
                listConference += '</tbody>\n                            ' +
                    '</table>' +
                    '                                </tbody>' +
                    '                            </table>' +
                    '                        </div>' +
                    '                        <div class="modal-footer">' +
                    '                            <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>' +
                    '                            <button id="add_btn" type="button" class="btn btn-success" onclick=\"arrangement(' + i + ',' + conferenceId + ')">安排</button>' +
                    '                        </div>' +
                    '                        <!--模态弹窗部分结束 -->' +
                    '                    </div>' +
                    '                </div>' +
                    '            </div>' +
                    '        </div>' +
                    '    </div>' +
                    '</div>' +
                    '                   </div>'
            }
            $('.card').empty();
            $('.card').html(listConference);
            $('#datatable').dataTable();
            if (data["code"] === 200) {
                console.log(data);
                // alert(data);
                // alert("获取会议数据成功");
            } else {
                alert("获取数据失败1");
            }
        },
        error: function () {
            alert("获取会议数据失败");
        },
    })
}

function getOrganizerInfo(organizerId) {
    $.ajax({
        async: false,
        url: "/organizer/getOrganizerInfo",
        type: "get",
        dataType: "json",
        data: {
            'organizerId': organizerId,
        },
        success: function (data) {
            // data = JSON.parse(data)
            organizer = data['data']['getOrganizerInfo'];
            // alert(organizer);
            console.log(organizer);
        },
        error: function () {
            alert("获取用户数据失败2");
        },
    });
}

function getPickUpByConferenceIdAndParticipantId(participantId, conferenceId) {
    $.ajax({
        async: false,
        url: "/pickUp/getPickUpByConferenceIdAndParticipantId",
        type: "get",
        dataType: "json",
        data: {

            'participantId': participantId,
            'conferenceId': conferenceId
        },
        success: function (data) {
            console.log(conferenceId);
            console.log(data);
            if (data['code'] !== 200) {
                alert("获取PickUp失败");
            } else
                pickUp = data['data']['pickUp'];
            console.log(pickUp);
        },
        error: function () {
            alert("获取PickUp2");
        },
    });
}

/**
 * 通过会议的id查所有需要接送的参与者
 *
 *
 */
function getNeedPickUpParticipant($conferenceId) {
    $.ajax({
        async: false,
        url: "/joinConference/queryJoinConferenceByConferenceId",
        type: "get",
        dataType: "json",
        data: {
            'conferenceId': $conferenceId,
        },
        success: function (data) {
            console.log()
            if (data['code'] !== 200) {
                alert("获取需要接送的参与者失败");
            } else
                joinConference = data['data']['joinConference'];
            console.log(joinConference);
        },
        error: function () {
            alert("获取需要接送的参与者失败2");
        },
    });
}

// 添加记录
function arrangement(i, conferenceId) {
    getNeedPickUpParticipant(conferenceId);
    console.log(joinConference);
    for (let i of joinConference) {
        let driverId = $("#driverId" + conferenceId + '-' +i.participantId).val();
        console.log(driverId);
        $.ajax({
            async: false,
            headers: {
                'token': token,
            },
            url: "/pickUp/addOneNeedToPickUp",
            type: "get",
            dataType: "json",
            data: {
                // 'driverId': driverName,
                'conferenceId': conferenceId,
                'driverId': driverId,
                'participantId': i.participantId
            },
            success: function (data) {
                console.log(data);
                if (data["code"] === 200 || data["message"] === "已存在") {
                    console.log("成功");
                    // driver = data["data"]["addOneNeedToPickUp"];
                    // console.log(driver);
                }
            },
            error: function () {
                alert("获取用户数据失败");
            },
        });
    }

    alert("分配成功");
    localStorage.setItem("function", "fleetConference()");
    // fleetConference();
    location.reload();
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
            console.log(data);
            if (data["code"] === 200) {
                driver = data["data"]["getDriverInfoById"];
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
 * 得到会议参加者信息
 * */
function getParticipantInfo($participantId) {
    $.ajax({
        async: false,
        url: "/participant/queryParticipantByParticipantId",
        type: "get",
        dataType: "json",
        data: {
            'participantId': $participantId,
        },
        success: function (data) {
            // console.log(data);
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