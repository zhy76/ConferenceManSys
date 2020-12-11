package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.entity.Driver;
import com.conference.service.DriverService;
import com.conference.service.PickUpService;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: DriverController
 * @Description: TODO  司机的分派算法：
 * 参考因素
 * 1. 司机的未完成订单量
 * 2. 司机的从现在到未来第一个订单的时间长度（司机当前空闲时间）
 * @Author: Lance
 * @Date: 2020/12/2 18:52
 */
// /driver/register
@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private PickUpService pickUpService;


    /**
     * 删除司机 Api
     *
     * @param driverId (the driver id),
     * @return {
     * "code": 200,
     * "message": "成功"
     * }
     */
    @GetMapping("/deleteDriver")
    public Result deleteDriver(@RequestParam int driverId) {
        int driverNum = driverService.deleteDriverById(driverId);
//        if (driverNum < 1) return new Result(ResultCode.FAIL);
        return Result.success();
    }

    /**
     * 司机注册 Api
     *
     * @param driver {
     *               "driverId": Integer, auto increment,
     *               "driverName": String, not null,
     *               "carNumber": String, not null,
     *               "fleetId": Integer, 外键
     *               "driverPass": String, not null, 六位以上
     *               "isAssign": Boolean, not null 默认false
     *               }
     * @return result {
     * "status":
     * "message":
     * "data": {}
     * }
     */
    @PostMapping("/register")
    public Result register(@Valid @RequestBody Driver driver) {
        if (driver.getDriverPhone() == null || driver.getDriverPass() == null) {
            return new Result(ResultCode.IllegalArgumentException);
        }
        int addNumber = driverService.addDriver(driver);
        if (addNumber > 0) {
            String token = tokenService.getToken(addNumber);
            return Result.success("token", token);
        } else {
            return new Result(ResultCode.FAIL);
        }
    }

    /**
     * 司机登入 Api
     *
     * @param driver
     * @return result {}
     */
    @PostMapping("/login")
    public Result login(@RequestBody Driver driver) {
        if (driver.getDriverPhone() == null || driver.getDriverPass() == null) {
            return new Result(ResultCode.IllegalArgumentException);
        }
        Driver driverForBase = driverService.findDriverByPhone(driver.getDriverPhone());
//        System.out.println(driverForBase);
        if (driverForBase == null) {
            return new Result(ResultCode.UnknownAccountException);
        } else {
            if (!driverForBase.getDriverPass().equals(driver.getDriverPass())) {
                return new Result(ResultCode.IncorrectCredentialsException);
            } else {
                String token = tokenService.getToken(driverForBase);
                return Result.success("token", token);
            }
        }
    }

    /**
     * 司机自己修改自己的信息
     *
     * @param driver
     * @param
     * @return result {}
     */
    @PostMapping("/updateDriver")
    public Result updateDriver(@Valid @RequestBody Driver driver, HttpServletRequest request) {
        System.out.println(request.getHeader("token"));
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        driver.setDriverId((Integer) claims.get("driverId"));
        driver.setAssign(driverService.findDriverById(driver.getDriverId()).getAssign());
//        System.out.println(driver.getDriverId());
        driverService.updateDriver(driver);
        return Result.success();
    }

    /**
     * 根据id查找车队所有的司机
     * /driver/getAllFleetDriver
     *
     * @param fleetId
     * @return result {}
     */
    @GetMapping("/getAllFleetDriver")
    public Result getAllFleetDriver(@RequestParam("fleetId") Integer fleetId) {
        List<Driver> getAllFleetDriver = driverService.findFleetAllDriver(fleetId);
        if (fleetId == null)
            return new Result(ResultCode.IllegalArgumentException);
        return Result.success("getAllFleetDriver", getAllFleetDriver);
    }

    /**
     * 查找所有的司机
     * /driver/getAllDriver
     *
     * @return result {}
     */
    @GetMapping("/getAllDriver")
    public Result getAllDriver() {
        List<Driver> getAllDriver = driverService.findAllDriver();
        return Result.success("getAllDriver", getAllDriver);
    }
}
