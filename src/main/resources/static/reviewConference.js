var JoinConference={};
// function getBothId(liveTable){
//     $participantPhone = $(liveTable).parent().parent("tr").children('td').eq(1).html();//从0开始
//     $conferenceId = $(liveTable).parent().parent("tr").children('td').eq(2).html();
//     queryParticipantByParticipantPhone($participantPhone);
//     $participantId=participant.participantId;
// }
$(function () {
    queryConferenceByConferenceId();
});
function showJoinParticipant(i){
    let $html="<div class=\"row\">\n" +
        "            <div class=\"col-md-12\">\n" +
        "                <div class=\"panel panel-default collapsed\">\n" +
        "                    <div class=\"panel-heading\">\n" +
        "                        申请加入会议表\n" +
        "                    </div>\n" +
        "                    <div class=\"panel-body\">\n" +
        '                       <table id=\"datatable' + i + '\" class=\"table table-striped dt-responsive nowrap\">\n' +
        "                            <thead>\n" +
        "                                <tr>\n" +
        "                                    <th>参会者姓名</th>\n" +
        "                                    <th>参会者电话</th>\n" +
        "                                    <th>会议编号</th>\n" +
        "                                    <th>是否需要接送</th>\n" +
        "                                    <th>是否需要住宿</th>\n" +
        "                                    <th>航班号</th>\n" +
        "                                    <th>是否审核</th>\n" +
        "                                    <th>操作</th>\n" +
        "                                </tr>\n" +
        "                            </thead>\n" +
        "\n" +
        "                            <tbody>\n"

    for (let i of JoinConference) {

             $participantId=i.participantId;
            queryParticipantByParticipantId($participantId);
            $html +=
                "                                                <tr>\n" +
                "                                                    <td>" + participant.participantName + "</td>\n" +
                "                                                    <td>" + participant.participantPhone + "</td>\n" +
                "                                                    <td>" + i.conferenceId + "</td>\n"
        if (i.pickup==false){
            $html +="                                                    <td>" + '否' + "</td>\n"
        }
        else{
            $html +="                                                    <td>" + '是' + "</td>\n"
        }
        if (i.isPutup==0){
            $html +="                                                    <td>" + '否' + "</td>\n"
        }
        else{
            $html +="                                                    <td>" + '是' + "</td>\n"
        }

        $html +=    "                                                    <td>" + i.trainNumber + "</td>\n"
        if (i.confirm==0){
            $html +="                                                    <td>" + '否' + "</td>\n"
            $html += "                                    <td>" +
                '                                     <button type="button" class="btn btn-success" onclick=\"confirmAJoinedConferenceById(' + i.participantId + ',' + i.conferenceId + ')\">同意加入</button>'+
                '                                     <button type="button" class="btn btn-danger" onclick=\"cancelAJoinedConferenceById(' + i.participantId + ',' + i.conferenceId + ')\">移出会议</button>'+
                "                                                   </td>\n" +
                "                                                </tr>\n";
        }
        else{
            $html +="                                                    <td>" + '是' + "</td>\n"
            $html += "                                    <td>" +
                '                                     <button type="button" class="btn btn-danger" onclick=\"cancelAJoinedConferenceById(' + i.participantId + ',' + i.conferenceId + ')\">移出会议</button>'+
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
    //$("#reviewConference").empty();
    $("#reviewConference").append($html+$htmlEnd);
    $('#datatable'+i).dataTable();
}
function queryConferenceByConferenceId(){
    queryConferenceByOrganizerId();
    for (let i in $queryConferenceByOrganizerId){
        var ConferenceId = $queryConferenceByOrganizerId[i].conferenceId;
        $.ajax({
            async: false,
            type: "get",
            dataType: "json",
            url: "/joinConference/queryConferenceByConferenceId",
            contentType: "application/json",
            data:{
                "conferenceId":ConferenceId
            },
            success: function (data) {
                //data = JSON.parse(data)
                if(data['code']==200){
                    JoinConference = data["data"]["queryConferenceByConferenceId"];
                    console.log(JoinConference);
                    if (JoinConference.length!=0){
                        showJoinParticipant(i);

                    }
                }else{
                    alert("获取信息失败！"+ data['message'])
                    // location.reload();
                }

            },
            error: function () {
                alert("获取用户数据失败!");
            },
        });
    }

}

function cancelAJoinedConferenceById(participantId,conferenceId){
    if(confirm("确定拒绝吗？")){
        $.ajax({
            async: false,
            headers: {
                'token': token,
            },
            url: "/joinConference/cancelAConference",
            type: "get",
            dataType: "json",
            data: {
                "participantId": participantId,
                "conferenceId": conferenceId
            },
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    alert("移除成功");
                    location.reload();
                } else {
                    alert("移除失败");
                    //location.reload();
                }
            },
        });
    }
}

function confirmAJoinedConferenceById(participantId,conferenceId){
    if(confirm("确定同意吗？")){
        $.ajax({
            async: false,
            headers: {
                'token': token,
            },
            url: "/joinConference/confirmAConference",
            type: "get",
            dataType: "json",
            data: {
                "participantId": participantId,
                "conferenceId": conferenceId
            },
            success: function (jsonData, result) {
                console.log(jsonData);
                console.log(result);
                if (jsonData['code'] === 200) {
                    alert("审核成功");
                    location.reload();
                } else {
                    alert("审核失败");
                    //location.reload();
                }
            },
        });
    }
}