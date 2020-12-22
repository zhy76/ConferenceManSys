let JoinConference;
// function getBothId(liveTable){
//     $participantPhone = $(liveTable).parent().parent("tr").children('td').eq(1).html();//从0开始
//     $conferenceId = $(liveTable).parent().parent("tr").children('td').eq(2).html();
//     queryParticipantByParticipantPhone($participantPhone);
//     $participantId=participant.participantId;
// }
$(function () {
    queryConferenceByConferenceId();
});
function showJoinParticipant(){
    let $html="<div class=\"row\">\n" +
        "            <div class=\"col-md-12\">\n" +
        "                <div class=\"panel panel-default collapsed\">\n" +
        "                    <div class=\"panel-heading\">\n" +
        "                        申请加入会议表\n" +
        "                    </div>\n" +
        "                    <div class=\"panel-body\">\n" +
        "                        <table id=\"datatable\" class=\"table table-striped dt-responsive nowrap\">\n" +
        "                            <thead>\n" +
        "                                <tr>\n" +
        "                                    <th>参会者姓名</th>\n" +
        "                                    <th>参会者电话</th>\n" +
        "                                    <th>会议编号</th>\n" +
        "                                    <th>是否需要接送</th>\n" +
        "                                    <th>是否需要住宿</th>\n" +
        "                                    <th>航班号</th>\n" +
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
                "                                                    <td>" + i.conferenceId + "</td>\n" +
                "                                                    <td>" + i.isPickup + "</td>\n" +
                "                                                    <td>" + i.isPutup + "</td>\n" +
                "                                                    <td>" + i.trainNumber + "</td>\n" +
                "                                    <td>" +
                "                                        <button type='button' class=\"btn btn-danger\" onclick=\"deleteLiveRoomByAll(this)\">删除</button>"+
                "                                                   </td>\n" +
                "                                                </tr>\n";
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

    $("#reviewConference").append($html+$htmlEnd);
    $('#datatable').dataTable();
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
                    showJoinParticipant();
                }else{
                    alert("获取信息失败！"+ data['message'])
                    // location.reload();
                }
                console.log(data)
            },
            error: function () {
                alert("获取用户数据失败!");
            },
        });
    }
    //$("#reviewConference").empty();
}