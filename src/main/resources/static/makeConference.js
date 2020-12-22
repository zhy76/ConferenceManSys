function addConference(){

    if (1) {
        $.ajax({
            // async: false,
            type: "POST",
            url: '/conference/addConference',
            contentType: "application/json",
            headers: { 'token': localStorage.getItem("zhy") },
            data: JSON.stringify({
                "conferenceName": $("#conferenceName").val(),
                "conferenceStart": $("#conferenceStart").val(),
                "conferenceEnd": $("#conferenceEnd").val(),
                "conferenceLocation": $("#conferenceLocation").val(),
                "conferenceInfo": $("#conferenceInfo").val(),
                "organizerId":$organizerId,
                "fleetId":$("#chooseFleetId").val(),
                "hotelId":$("#chooseHotelId").val()
            }),
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    alert("生成会议成功");
                    location.reload();
                } else {
                    alert("生成会议失败");
                    location.reload();
                }
            },
        });
    } else {
        alert("信息格式有误，请重新填写！");
    }
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
                console.log(allFleet);
                allFleet = allFleet["getAllFleet"];
                console.log(allFleet);
            } else {
                alert("获取车队数据失败");
            }
        },
        error: function () {
            alert("获取车队数据失败");
        },
    });

}
let allHotel;
function getAllHotel() {
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/hotel/getAllHotel",
        type: "get",
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                allHotel = data["data"];
                allHotel = allHotel["getAllHotel"];
                console.log(allHotel);
            } else {
                alert("获取酒店数据失败");
            }
        },
        error: function () {
            alert("获取酒店数据失败");
        },
    });

}

function showMethod(){
    getAllHotel();
    getAllFleet();
    let $html="<div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">酒店选择</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <select class=\"form-control form-control-line\" name=\"hotel\"\n" +
        "                                                        id='chooseHotelId'>\n" +
        "                                                        <option value=\"null\"> --请选择会议酒店--</option>\n"
    for(let i in allHotel){
        $html += "  <option value='" + allHotel[i].hotelId + "'>" + allHotel[i].hotelName + "</option>\n"
    }
    $html +=  "                                                    </select>\n" +
        "                                                </div>\n" +
        "                                            </div>"
    $html +="<div class=\"form-group\">\n" +
        "                                                <label class=\"col-md-12\">车队选择</label>\n" +
        "                                                <div class=\"col-md-12\">\n" +
        "                                                    <select class=\"form-control form-control-line\" name=\"hotel\"\n" +
        "                                                        id='chooseFleetId'>\n" +
        "                                                        <option value=\"null\">--请选择会议车队--</option>\n"
    for (let i in allFleet) {
        $html += "   <option value='" + allFleet[i].fleetId + "'>" + allFleet[i].fleetName + "</option>\n"
    }
    $html += "                                                    </select>\n" +
        "                                                </div>\n" +
        "                                            </div>"
    // 清空节点
    $("#choose-method").empty();
    $("#choose-method").append($html);
}
