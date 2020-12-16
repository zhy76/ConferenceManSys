function getBothId(liveTable){
    $participantId = $(liveTable).parent().parent("tr").children('td').eq(1).html();//从0开始
    $conferenceId = $(liveTable).parent().parent("tr").children('td').eq(2).html();
}
//重新设置房间号
function resetLiveRoom(liveTable){
    $participantId = $(liveTable).parent().parent("tr").children('td').eq(1).html();//从0开始
    $conferenceId = $(liveTable).parent().parent("tr").children('td').eq(2).html();
    if (1) {
        $.ajax({
            // async: false,
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
                    location.reload();
                }
            },
        });
    }

}