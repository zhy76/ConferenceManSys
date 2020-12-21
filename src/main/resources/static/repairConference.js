
let $queryConferenceByOrganizerId;
function queryConferenceByOrganizerId(){
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/conference/queryConferenceByOrganizerId",
        type: "get",
        dataType: "json",
        data: {
            'OrganizerId': $organizerId,
        },
        success: function (data) {
            //console.log(data);
            if (data["code"] === 200) {
                $queryConferenceByOrganizerId = data["data"]["queryConferenceByOrganizerId"];
                console.log($queryConferenceByOrganizerId);
            } else {
                alert("获取用户数据失败");
            }
        },
        error: function () {
            alert("获取用户数据失败!");
        },
    });
}
let hotel;
function getHotelById(hotelId) {
    $.ajax({
        async: false,
        // headers: {
        //     'token': token,
        // },
        url: "/hotel/getHotelById",
        type: "get",
        dataType: "json",
        data: {
            'hotelId': hotelId,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                hotel = data["data"]["getHotelById"];
                console.log(hotel);
            } else {
                alert("获取酒店信息失败！");
            }
        },
        error: function () {
            alert("获取酒店信息失败！");
        },
    });
}
let getfleet;
function findFleetById(fleetId) {
    $.ajax({
        async: false,
        // headers: {
        //     'token': token,
        // },
        url: "/fleet/findFleetById",
        type: "get",
        dataType: "json",
        data: {
            'fleetId': fleetId,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                getfleet = data["data"]["findFleetById"];
                console.log(getfleet);

                // console.log(chooseFleetName);
            } else {
                alert("获取车队数据失败");
            }
        },
        error: function () {
            alert("获取车队数据失败");
        },
    });
}
//显示组织者组织的会议
function showMyConference(){
    var  $listConference="";
    queryConferenceByOrganizerId();
    for(var i in $queryConferenceByOrganizerId){
        console.log(i);
        console.log($queryConferenceByOrganizerId[i]);
        var conferenceName = $queryConferenceByOrganizerId[i].conferenceName;
        var conferenceLocation = $queryConferenceByOrganizerId[i].conferenceLocation;
        var conferenceStart = $queryConferenceByOrganizerId[i].conferenceStart;
        var conferenceEnd = $queryConferenceByOrganizerId[i].conferenceEnd;
        var conferenceId = $queryConferenceByOrganizerId[i].conferenceId;
        var organizerId = $queryConferenceByOrganizerId[i].organizerId;
        var hotelId= $queryConferenceByOrganizerId[i].hotelId;
        var fleetId=$queryConferenceByOrganizerId[i].fleetId;
        console.log(hotelId);
        console.log(fleetId);
        var conferenceInfo=$queryConferenceByOrganizerId[i].conferenceInfo;
        getOrganizerInfo(organizerId);
        findFleetById(fleetId);
        getHotelById(hotelId);
        console.log(getfleet);
        //var chooseFleetName=getfleet["fleetName"];
        getAllHotel();
        getAllFleet();
        var organizerUnit = organizer.organizerUnit;
        $listConference +=  '<div class="col-lg-3" style = "margin: 0px 10px;">' +
            '                        <a href = "#">' +
            '                            <div class="items">' +
            '                                <img class="img-responsive" src="picture/img1.jpg" />' +
            '                                <h4>'+ conferenceName +'</h4>' +
            '                                <p> <span class="iconfont icon-dizhi"></span> '+conferenceLocation+'</p>' +
            '                                <p> <span class="iconfont icon-shijian"></span>'+conferenceStart + '-' +conferenceEnd +'</p>' +
            '                                <p> <span class="iconfont icon-zhubandanwei"></span> '+organizerUnit+'</p>' +
            '                            </div>' +
            '                        </a>' +
            '<div class="box">' +
            '    <div class="content">' +
            '        <!--添加按钮及bootstrap的模态框-->' +
            '        <div class="export">' +
            '            <!--按钮开始-->' +
            '            <button id="repair" type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#renyuan'+conferenceId+'">' +
            '                <span>修改</span>' +
            '            </button>' +
            '            <button id="deleteConference" type="button" class="btn btn-danger" onclick="deleteConference(' + conferenceId + ')">' +
            '                <span>删除</span>' +
            '            </button>'+
            '            <!--按钮结束-->' +
            '            <div class="modal fade" id="renyuan'+conferenceId+'">' +
            '                <div class="modal-dialog modal-lg modal_position">' +
            '                    <div class="modal-content">' +
            '                        <div class="modal-header">' +
            '                            <h4 class="modal-title" id="">修改会议</h4>' +
            '                            <button type="button" class="close" data-dismiss="modal">&times;</button>' +
            '                        </div>\n' +
            '                        <!-- 模态弹窗部分开始 -->' +
            '                        <div class="modal-body" id="modal'+conferenceId+'">' +
            '<table id="xztb" class="table">' +
                '                                <!--新修改弹窗的样式-->' +
                '                                <tbody>' +
            '                                <tr>' +
            '                                      <td class="tb_bg"><label for="">会议名称</label></td>' +
            '                                      <td><input type="text" name="conName" id="conName' + i + '" value=\"'+conferenceName+'\" ></td>' +
            '                                </tr>' +
                    '                                <tr>' +
                        '                                    <td class="tb_bg"><label for="">会议开始时间</label></td>' +
                        '                                    <td><input type="text" name="startTime" id="startTime' + i + '" placeholder="2020-12-10 09:00:00" value=\"'+conferenceStart+'\"></td>' +
                        '                                </tr>' +
                    '                                <tr>' +
                        '                                    <td class="tb_bg"><label for="">结束时间</label></td>' +
                        '                                    <td><input type="text" name="endTime" id="endTime' + i + '" placeholder="2020-12-12 12:00:00" value=\"'+conferenceEnd+'\"></td>' +
                        '                                </tr>' +
            '                                <tr>' +
            '                                      <td class="tb_bg"><label for="">会议地点</label></td>' +
            '                                      <td><input type="text" name="conLocation" id="conLocation' + i + '" value=\"'+conferenceLocation+'\" ></td>' +
            '                                </tr>' +
            '                                <tr>' +
            '                                      <td class="tb_bg"><label for="">会议相关信息</label></td>' +
            '<td><textarea rows="5" class="form-control form-control-line" id="conInfo' + i + '" >'+conferenceInfo+
            '</textarea></td>' +
            '                                </tr>' +
            '                                <tr>' +
            '                                      <td class="tb_bg"><label for="">酒店选择</label></td>' +
            '<td>'+
            "                                                    <select class=\"form-control form-control-line\" name=\"conhotel\"\n" +
            '                                                       id="conHotel'+ i + '">\n' +
            "                                                        <option value=\""+hotelId+"\"> "+hotel.hotelName+"</option>\n"
        for(let i in allHotel){
            if (allHotel[i].hotelId==hotelId) continue;
            $listConference += "  <option value='" + allHotel[i].hotelId + "'>" + allHotel[i].hotelName + "</option>\n"
        }
        $listConference += '</td>'+'                                </tr>' +
            '                                <tr>' +
            '                                      <td class="tb_bg"><label for="">车队选择</label></td>' +
            '<td>'+
            "                                                    <select class=\"form-control form-control-line\" name=\"conFleet\"\n" +
            '                                                      id="conFleet'+ i + '">\n'+
            "                                                        <option value=\""+fleetId+"\"> "+getfleet.fleetName+"</option>\n"
        for(let i in allFleet){
            if (allFleet[i].fleetId==fleetId) continue;
            $listConference += "  <option value='" + allFleet[i].fleetId + "'>" + allFleet[i].fleetName + "</option>\n"
        }
        $listConference += '</td>'+'                                </tr>' +
                    '                                </tbody>' +
                '                            </table>' +
            '</div>'+
            '                        <div class="modal-footer">' +
            '                            <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>' +
            '                            <button id="add_btn" type="button" class="btn btn-success" onclick=\"updateConference(' + i + ',' + conferenceId + ')">修改</button>' +
            '                        </div>' +
            '                        <!--模态弹窗部分结束 -->' +
            '</div>'+
            '</div>'+
            '</div>'+
            '</div>'+'</div>'+'</div>'+
            '                           </div>'

    }
    // 清空节点
    $("#myConference").html($listConference);
    //$("#myConference").append($listConference);
}

//修改会议信息
function updateConference(i,conferenceId){
    let conName = $("#conName"+i).val();
    let startTime = $("#startTime"+i).val();
    let endTime = $("#endTime"+i).val();
    let conLocation = $("#conLocation"+i).val();
    let conHotel = $("#conHotel"+i).val();
    let conFleet = $("#conFleet"+i).val();
    let conInfo = $("#conInfo"+i).val();
    $.ajax({
        async: false,
        type: "POST",
        dataType: "json",
        url: "/conference/updateConference",
        contentType: "application/json",
        data:JSON.stringify ({
            "organizerId":$organizerId,
            "fleetId":conFleet,
            "hotelId":conHotel,
            "conferenceName":conName,
            "conferenceStart":startTime,
            "conferenceEnd":endTime,
            "conferenceLocation": conLocation,
            "conferenceInfo":conInfo,
            "conferenceId":conferenceId
        }),
        success: function (data) {
            //data = JSON.parse(data)
            if(data['code']==200){
                alert("修改成功！");
                location.reload();
            }else{
                alert("修改失败！"+ data['message'])
                location.reload();
            }
            console.log(data)
        },
        error: function () {
            alert("获取用户数据失败!");
        },
    });
}
//删除会议
function deleteConference(conferenceId){
    $.ajax({
        async: false,
        type: "get",
        dataType: "json",
        url: "/conference/deleteConference",
        contentType: "application/json",
        data:{
            "conferenceId":conferenceId
        },
        success: function (data) {
            //data = JSON.parse(data)
            if(data['code']==200){
                alert("删除成功！");
                location.reload();
            }else{
                alert("删除失败！"+ data['message'])
                location.reload();
            }
            console.log(data)
        },
        error: function () {
            alert("获取用户数据失败!");
        },
    });
}








