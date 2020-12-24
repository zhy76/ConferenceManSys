/**
 * 接送id, 参会姓名,电话号码,火车航班
 * 到达时间, 离开时间, 是否完成
 * @type {{}}
 */
let pickUpTable = {};

/*获取token里面的用户数据*/
function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}


function getDriverAllPickUp() {
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/pickUp/getDriverAllPickUp",
        // url: "/pickUp/getDriverWaitPickUp",
        type: "get",
        dataType: "json",
        data: {
            'driverId': $driverId,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                console.log(data["data"]);
                pickUp = data["data"]["getDriverAllPickUp"];
                console.log(pickUp);
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
                // console.log(participant);
            } else {
                $.alert({
                    title: '提示信息',
                    content: '获取参会者数据失败',
                });
            }
        },
        error: function () {
            $.alert({
                title: '提示信息',
                content: '获取参会者数据失败',
            });
        },
    });
}

// $(document).ready(function () {
//     $('#datatable').dataTable();
// });
/**
 *
 */
function showWaitPickUpTable() {

    getDriverAllPickUp();

    let $htmlStart =
        "                            <div class=\"row\">\n" +
        "                                <div class=\"col-md-12\">\n" +
        "                                    <div class=\"panel panel-default collapsed\">\n" +
        "                                        <div class=\"panel-heading\">\n" +
        "                                            待接送记录\n" +
        "                                        </div>\n" +
        "                                        <div class=\"panel-body\">\n" +
        "                                            <table class=\"table table-striped dt-responsive nowrap\" id=\"datatable\">\n" +
        "                                                <thead>\n" +
        "                                                <tr>\n" +
        "                                                    <th>接送单号</th>\n" +
        "                                                    <th>姓名</th>\n" +
        "                                                    <th>电话号码</th>\n" +
        "                                                    <th>航班</th>\n" +
        "                                                    <th>到达时间</th>\n" +
        "                                                    <th>接送时间</th>\n" +
        "                                                    <th>操作</th>\n" +
        "                                                </tr>\n" +
        "                                                </thead>\n" +
        "                                                <tbody>\n"
    let $html = "";

    console.log(pickUp);
    for (let i of pickUp) {
        if (i.finishPickup === true) {
            continue;
        }
        // if (i.finishPickup === true && flag === 1) {
        //     continue;
        // } else if (i.finishPickup === false && flag === 2) {
        //
        // }
        // console.log(i.participantId);
        queryParticipantByParticipantId(i.participantId);
        console.log(participant);
        $html +=
            "                                                <tr>\n" +
            "                                                    <td>" + fix(i.pickUpId, 4) + "</td>\n" +
            "                                                    <td>" + participant.participantName + "</td>\n" +
            "                                                    <td>" + participant.participantPhone + "</td>\n" +
            "                                                    <td>" + i.trainNumber + "</td>\n" +
            "                                                    <td>" + i.toTime + "</td>\n" +
            "                                                    <td>" + i.returnTime + "</td>\n" +
            // "                                                    <td><input type='datetime-local' value=\"2020-12-20T13:59:59\" min=\"2020-09-24T13:59:59\" max=\"2029-09-24T13:59:59\"/></td>\n" +
            // "                                                    <td>" + i.returnTime + "</td>\n" +
            "                                    <td><button type='button' class=\"btn btn-info\" onclick=\"finish(this)\" >完成</button></td>\n" +
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
    $(".card").empty();
    $(".card").append($htmlStart + $html + $htmlEnd);
    $('#datatable').dataTable();
}

/**
 *
 *
 */
function showAllPickUpTable() {

    getDriverAllPickUp();

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
        "                                                    <th>接送单号</th>\n" +
        "                                                    <th>姓名</th>\n" +
        "                                                    <th>电话号码</th>\n" +
        "                                                    <th>火车航班</th>\n" +
        "                                                    <th>到达时间</th>\n" +
        "                                                    <th>接送时间</th>\n" +
        "                                                    <th>是否完成</th>\n" +
        "                                                </tr>\n" +
        "                                                </thead>\n" +
        "                                                <tbody>\n"
    let $html = "";

    console.log(pickUp);
    for (let i of pickUp) {
        // if (i.finishPickup === true && flag === 1) {
        //     continue;
        // } else if (i.finishPickup === false && flag === 2) {
        //
        // }
        console.log(i.participantId);
        queryParticipantByParticipantId(i.participantId);
        console.log(participant);
        $html +=
            "                                                <tr>\n" +
            "                                                    <td>" + fix(i.pickUpId, 4) + "</td>\n" +
            "                                                    <td>" + participant.participantName + "</td>\n" +
            "                                                    <td>" + participant.participantPhone + "</td>\n" +
            "                                                    <td>" + i.trainNumber + "</td>\n" +
            "                                                    <td>" + i.toTime + "</td>\n" +
            // <input type="date">
            // "                                                    <td><input type=\"date\"></td>\n" +
            "                                                    <td>" + i.returnTime + "</td>\n" +
            "                                                    <td>" + (i.finishPickup ? "是" : "否") + "</td>\n" +
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
    $(".card").empty();
    $(".card").append($htmlStart + $html + $htmlEnd);
    $('#datatable').dataTable();
}

function getPickUpId(btn) {
    /**
     * 找表的
     * @type {jQuery}
     */
    pickUpId = $(btn).parent().parent("tr").children('td').eq(0).html();//从0开始
}

function finish(btn) {
//完成订单
    getPickUpId(btn);
    if (1) {
        $.ajax({
            async: false,
            type: "GET",
            url: '/pickUp/completePickUp',
            contentType: "json",
            // headers: { 'token': localStorage.getItem("conNCU") },
            data: {
                "pickUpId": pickUpId
            },
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    $.alert({
                        title: '提示信息',
                        content: '接送成功',
                    });
                    showWaitPickUpTable(1);
                    // location.reload();
                } else {
                    $.alert({
                        title: '提示信息',
                        content: '完成失败',
                    });
                }
            },
        });
    }
}


function getDriverConfirmPickUp($driverId) {
    if (1) {
        $.ajax({
            async: false,
            type: "GET",
            url: '/pickUp/getDriverConfirmPickUp',
            contentType: "json",
            data: {
                "driverId": $driverId
            },
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    pickUp = jsonData['data']['pickUp'];
                    // location.reload();
                } else {
                    $.alert({
                        title: '提示信息',
                        content: jsonData['message'],
                    });
                    // location.reload();
                }
            },
        });
    } else {
        alert("信息格式有误，请重新填写！");
    }
}


function showDriverConfirmPickUp($driverId) {
    getDriverConfirmPickUp($driverId)
    let $htmlStart =
        "                            <div class=\"row\">\n" +
        "                                <div class=\"col-md-12\">\n" +
        "                                    <div class=\"panel panel-default collapsed\">\n" +
        "                                        <div class=\"panel-heading\">\n" +
        "                                            待确认记录\n" +
        "                                        </div>\n" +
        "                                        <div class=\"panel-body\">\n" +
        "                                            <table class=\"table table-striped dt-responsive nowrap\" id=\"datatable\">\n" +
        "                                                <thead>\n" +
        "                                                <tr>\n" +
        "                                                    <th>接送单号</th>\n" +
        "                                                    <th>姓名</th>\n" +
        "                                                    <th>电话号码</th>\n" +
        "                                                    <th>航班</th>\n" +
        "                                                    <th>到达时间</th>\n" +
        "                                                    <th>接送时间</th>\n" +
        "                                                    <th>操作</th>\n" +
        "                                                </tr>\n" +
        "                                                </thead>\n" +
        "                                                <tbody>\n"
    let $html = "";
    var newDate = new Date();
    let dateToday = "" + newDate.getFullYear() + '-' + fix((newDate.getMonth() + 1), 2) + '-'  + fix(newDate.getDate(), 2) + 'T' + fix(newDate.getHours(),2) + ':' + fix(newDate.getMinutes(), 2);
    // alert(dateToday);
    console.log(pickUp);
    for (let i of pickUp) {
        if (i.finishPickup === true) {
            continue;
        }
        queryParticipantByParticipantId(i.participantId);
        console.log(participant);
        $html +=
            "                                                <tr>\n" +
            "                                                    <td>" + fix(i.pickUpId, 4) + "</td>\n" +
            "                                                    <td>" + participant.participantName + "</td>\n" +
            "                                                    <td>" + participant.participantPhone + "</td>\n" +
            "                                                    <td>" + i.trainNumber + "</td>\n" +
            "                                                    <td>" + i.toTime + "</td>\n" +
            "                                                    <td><input type='datetime-local' id=\"pickUpTime\" value='" + dateToday + "''/></td>\n" +
            // "                                                    <td><input type='datetime-local' value=\"2020-12-20T13:59:59\" min=\"2020-09-24T13:59:59\" max=\"2029-09-24T13:59:59\"/></td>\n" +
            // "                                                    <td>" + i.returnTime + "</td>\n" +
            "                                    <td><button type='button' class=\"btn btn-info\" onclick=\"confirm(this)\" >确认</button></td>\n" +
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
    $(".card").empty();
    $(".card").append($htmlStart + $html + $htmlEnd);
    $('#datatable').dataTable();
}

function confirm(btn) {
    // alert(1);
    let time = $(btn).parent().parent("tr").children('td').eq(5).children().val().replaceAll('T', ' ');
    let pickUpId = Number($(btn).parent().parent("tr").children('td').eq(0).html());
    $.ajax({
        async: false,
        type: "GET",
        url: '/pickUp/updateDriverConfirmPickUp',
        contentType: "json",
        // headers: { 'token': localStorage.getItem("conNCU") },
        data: {
            "pickUpId": pickUpId,
            "driverId": $driverId,
            "returnTime": time
        },
        success: function (jsonData, result) {
            console.log(jsonData);
            console.log(result);
            if (jsonData['code'] === 200) {
                $.alert({
                    title: '提示信息',
                    content: '确认成功',
                });
                // alert("确认成功");
                showDriverConfirmPickUp($driverId);
                // location.reload();
            } else {
                $.alert({
                    title: '提示信息',
                    content: '确认失败',
                });
                // alert("确认失败");
                // location.reload();
            }
        },
    });
}