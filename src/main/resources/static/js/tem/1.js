// "use strict";
// Object.defineProperty(exports, "__esModule", { value: true });
var jquery_1 = require("jquery");
var driver;
var token;
$(function () {
    console.log("glz");
    var $driverId;
    var $driverPhone;
    getDriverInfo($driverId);
})
// jquery_1.default(function () {
//     console.log("glz");
//     var $driverId;
//     var $driverPhone;
//     getDriverInfo($driverId);
// });
/**
 * 得到司机的信息
 */
var glz = {
    name: 'glz'
};
console.log(glz['name']);
function getDriverInfo($driverId) {
    jquery_1.default.ajax({
        async: false,
        headers: {
            'token': token,
        },
        url: "/driver/getDriverInfo",
        type: "get",
        dataType: "json",
        data: {
            'driverId': $driverId,
        },
        success: function (data) {
            driver = data;
        },
        error: function () {
            alert("获取用户数据失败");
        },
    });
}
/**
 * 清空登入时清空用户信息
 */
function clearDriverInfo() {
    driver = undefined;
}
//# sourceMappingURL=1.js.map