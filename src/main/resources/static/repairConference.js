function queryConferenceByOrganizerId(){
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/liveRoom/findAllLiveRoomByHotelId",
        type: "get",
        dataType: "json",
        data: {
            'hotelId': $hotelId,
        },
        success: function (data) {
            //console.log(data);
            if (data["code"] === 200) {
                liveRoom = data["data"]["findAllLiveRoomByHotelId"];
                console.log(liveRoom);
            } else {
                alert("获取用户数据失败");
            }
        },
        error: function () {
            alert("获取用户数据失败!");
        },
    });
}

function  findAllLiveRoomByHotelId(){
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/liveRoom/findAllLiveRoomByHotelId",
        type: "get",
        dataType: "json",
        data: {
            'hotelId': $hotelId,
        },
        success: function (data) {
            //console.log(data);
            if (data["code"] === 200) {
                liveRoom = data["data"]["findAllLiveRoomByHotelId"];
                console.log(liveRoom);
            } else {
                alert("获取用户数据失败");
            }
        },
        error: function () {
            alert("获取用户数据失败!");
        },
    });
}