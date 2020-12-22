import $ from "jquery";
interface Driver {
    driverId: number;
    driverName: string;
    carNumber: number;
    fleetId: number;
    driverPass: string;
    driverPhone: string;
    isAssign: boolean;
}
let driver: Driver;
let token: any;

$(function () {
    console.log("glz");
    let $driverId;
    let $driverPhone;
    getDriverInfo($driverId);
})
/**
 * 得到司机的信息
 */

let glz={
    name:'glz'
}
console.log(glz['name']);
function getDriverInfo($driverId) {
    $.ajax({
        async:false,
        headers: {
            'token': token,
        },
        url: "/driver/getDriverInfo",
        type: "get",
        dataType: "json",
        data: {
            'driverId': $driverId,
        },
        success: function (data:Driver) {
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

