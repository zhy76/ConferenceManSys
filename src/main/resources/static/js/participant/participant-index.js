

let participant;
let token;
let $participantId;
$(function (){
    let $participantPhone;
    token = localStorage.getItem("xj");
    console.log(typeof (token));
    console.log(token);
    if (token == null || token === "null" || token === "undefined") {
        console.log("no token");
    } else {
        $participantId = parseJwt(token).participantId;/*获取用户信息*/
        console.log($participantId);
    }
    /**
     * 点击->个人信息
     */
    $("#to-info a").click(function () {
            showParticipantInfo();
        }
    )
})

function getParticipantInfo($participantId) {
    $.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/participant/getParticipantInfo",
        type: "get",
        dataType: "json",
        data: {
            'participantId': $participantId,
        },
        success: function (data) {
            console.log(data);
            if (data["code"] === 200) {
                participant = data["data"]["getParticipantInfo"];
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

function showParticipantInfo() {
    getParticipantInfo($participantId);
    console.log(participant);
    let $html = "<div class=\"tab-content\">\n" +
        "                                    <div class=\"tab-pane\" id=\"个人信息\" role=\"tabpanel\">\n" +
        "                                        <div class=\"card-body\">\n" +
        "                                            <form class=\"form-horizontal form-material\">\n" +
        "                                                <br>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label class=\"col-md-12\">姓名</label>\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "                                                    <input type=\"text\" class=\"form-control form-control-line\"\n" +
        "                                                        name='participantName' id='participantName' value=\"" + participant.participantName + "\">\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label class=\"col-md-12\">性别</label>\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "<!--                                                        <input type=\"text\" class=\"form-control form-control-line\" value=\"Jessie J\">-->\n" +
        "                                                        <input type=\"radio\"  name=\"participantSex\" value=\"M\"/>男 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
        "                                                        <input type=\"radio\"  name=\"participantSex\" value=\"F\"/>女\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label class=\"col-md-12\">身份证号</label>\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "                                                        <input type=\"text\" class=\"form-control form-control-line\"\n" +
        "                                                        name='participantIdCard' id='participantIdCard' value=\"" + participant.participantIdCard + "\">\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label for=\"example-email\" class=\"col-md-12\">邮箱</label>\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "                                                        <input type=\"email\" class=\"form-control form-control-line\"\n"+
        "                                                         name='participantEmail' id='participantEmail' value=\"" + participant.participantEmail + "\">\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label class=\"col-md-12\">密码</label>\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "                                                         <input type=\"password\" value=\"" + participant.participantPass + "\"\n" +
        "                                                        name='participantPass' class=\"form-control form-control-line\" id='participantPass'>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label class=\"col-md-12\">手机号</label>\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "                                                       <input type=\"text\" value=\"" + participant.participantPhone + "\"\n" +
        "                                                        name='participantPhone' class=\"form-control form-control-line\" id='participantPhone'>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label class=\"col-md-12\">职位</label>\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "                                                        <input type=\"text\" value=\"" + participant.participantJob + "\"\n" +
        "                                                        name='participantJob' class=\"form-control form-control-line\" id='participantJob'>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <label class=\"col-md-12\">工作单位</label>\n" +
        "                                                    <div class=\"col-md-12\">\n" +
        "                                                         <input type=\"text\" value=\"" + participant.participantWorkUnit + "\"\n" +
        "                                                        name='participantWorkUnit' class=\"form-control form-control-line\" id='participantWorkUnit'>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                                <br/>\n" +
        "                                                <br/>\n" +
        "                                                <div class=\"form-group\">\n" +
        "                                                    <div class=\"col-sm-12 text-center\" >\n" +
        "                                                        <input type='submit' class=\"btn-info\" onclick='' value='更新'>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                            </form>\n" +
        "                                        </div>\n" +
        "                                    </div>\n" +
        "                                </div>"
    // 清空节点
    $(".jumbotron").empty();
    $(".jumbotron").append($html);
}
/*获取token里面的用户数据*/
function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}