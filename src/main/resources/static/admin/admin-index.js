/*获取token里面的用户数据*/
function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}

$(function () {
    //获取token
    token = localStorage.getItem("conNCU");
    console.log(typeof (token));
    console.log(token);
    if (token == null || token == "null") {
        console.log("no token");
    } else {
        var adminId = parseJwt(token).adminId;/*获取用户信息*/
        console.log(parseJwt(token));
        console.log(adminId);
    }

    //判断是否为未登录用户
    if (token == null || token == "null" || typeof (adminId) == "undefined" || adminId == undefined) {//未登录
        console.log("未登录");
        localStorage.setItem("conNCU", null);
        $.alert({
            title: '提示信息',
            content: '请先登录!',
        });
        window.location.href = "login.html";
    }

}
)

