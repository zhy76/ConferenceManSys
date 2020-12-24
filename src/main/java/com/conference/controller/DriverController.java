package com.conference.controller;

import com.conference.entity.Driver;
import com.conference.entity.Fleet;
import com.conference.service.DriverService;
import com.conference.service.FleetService;
import com.conference.service.PickUpService;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import com.conference.util.vaild.DriverLogin;
import com.conference.util.vaild.DriverRegister;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * @ClassName: DriverController
 * @Description:
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

    @Autowired
    private FleetService fleetService;


    /**
     * 删除司机 Api
     * /driver/deleteDriver
     *
     * @param driverId (the driver id),
     * @return {
     * "code": 200,
     * "message": "成功"
     * }
     */
    @GetMapping("/deleteDriver")
    public Result deleteDriver(@RequestParam Integer driverId) {
        int driverNum = driverService.deleteDriverById(driverId);
//        if (driverNum < 1) return new Result(ResultCode.FAIL);
        return Result.success();
    }

    /**
     * 司机注册 Api
     * /driver/register
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
    public Result register(@Validated({DriverRegister.class}) @RequestBody Driver driver) {
//        if (driver.getDriverPhone() == null || driver.getDriverPass() == null) {
//            return new Result(ResultCode.IllegalArgumentException);
//        }
        int addNumber = driverService.addDriver(driver);
        Driver driverForBase = driverService.findDriverByPhone(driver.getDriverPhone());
        if (addNumber > 0) {
            String token = tokenService.getToken(driverForBase);
            return Result.success("token", token);
        } else {
            return new Result(ResultCode.FAIL);
        }
    }

    /**
     * 司机登入 Api
     * /driver/login
     *
     * @param driver {
     *               "driverName": String, not null,
     *               "driverPass": String, not null, 六位以上
     *               }
     * @return result {
     * "status":
     * "message":
     * "data": {}
     * }
     */
    @RequestMapping("/login")
    public Result login(@Validated({DriverLogin.class}) @RequestBody Driver driver) {
        Driver driverForBase = driverService.findDriverByPhone(driver.getDriverPhone());
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
     * 司机登出 Api
     *
     * @return
     * @TODO 登出
     * /driver/logout
     */
    @PostMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success();
    }


    /**
     * 管理员修改司机的信息 Api
     * /driver/adminUpdateDriver
     *
     * @param driver {}
     * @return result {}
     */
    @PostMapping("/adminUpdateDriver")
    public Result adminUpdateDriver(@Validated({DriverRegister.class}) @RequestBody Driver driver) {
        System.out.println("修改中...");
        System.out.println(driver);
        driverService.updateDriver(driver);
        return Result.success();
    }

    /**
     * 司机自己修改自己的信息 Api
     * /driver/updateDriver
     *
     * @param driver  {}
     * @param request
     * @return result {}
     */
    @RequestMapping("/updateDriver")
    public Result updateDriver(@Validated({DriverRegister.class}) @RequestBody Driver driver, HttpServletRequest request) {
        System.out.println("POST------------------------------------------------");
        System.out.println(request.getHeader("token"));
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        driver.setDriverId((Integer) claims.get("driverId"));
        driver.setAssign(driverService.findDriverById(driver.getDriverId()).getAssign());
        driverService.updateDriver(driver);
        return Result.success();
    }

    /**
     * 根据id查找车队所有的司机 Api
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
     * 查找所有的司机 Api
     * /driver/getAllDriver
     *
     * @return result {}
     */
    @GetMapping("/getAllDriver")
    public Result getAllDriver() {
        List<Driver> getAllDriver = driverService.findAllDriver();
//        return new Result(2, "时间冲突");
        return Result.success("getAllDriver", getAllDriver);
    }

    /**
     * 查找登入司机的所有信息
     * /driver/getDriverInfo
     *
     * @param request
     * @return
     */
    @GetMapping("/getDriverInfo")
    public Result getDriverInfo(HttpServletRequest request) {
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        Driver getDriverInfo = driverService.findDriverById((Integer) claims.get("driverId"));
        System.out.println("/getDriverInfo");
        return Result.success("getDriverInfo", getDriverInfo);
    }

    /**
     * 司机自己查找登入司机的所有信息
     * /driver/getDriverInfo
     *
     * @param driverId
     * @return
     */
    @GetMapping("/getDriverInfoById")
    public Result getDriverInfoById(@RequestParam("driverId") Integer driverId) {
        Driver getDriverInfo = driverService.findDriverById(driverId);
        System.out.println("/getDriverInfoById");
        return Result.success("getDriverInfoById", getDriverInfo);
    }

    /**
     * 管理员查找所有的司机 Api
     * /driver/getAllDriver
     *
     * @return result {}
     */
    @GetMapping("/getAllDriverByAdmin")
    public Result getAllDriverByAdmin() {
        List<Driver> getAllDriver = driverService.findAllDriver();
        Map<Integer, List<Object>> map = new HashMap<>(); //找到对应的Map
        for (int i = 0; i < getAllDriver.size(); i++) {
            Fleet fleet = fleetService.findFleetById(getAllDriver.get(i).getFleetId());
            map.put(i, new ArrayList<>(Arrays.asList(getAllDriver.get(i), fleet)));
        }
        return Result.success("getAllDriver", map);
    }

    /**
     * 管理员查找指定id的司机 Api
     * /driver/getAllDriver
     *
     * @return result {}
     */
    @PostMapping("/updateDriverByAdmin")
    public Result updateDriverByAdmin(@RequestParam Integer driverId) {
        Driver driver = driverService.findDriverById(driverId);
        System.out.println(driver);
        Map<String, List<Object>> map = new HashMap<>(); //找到对应的Map
        Fleet fleet = fleetService.findFleetById(driver.getFleetId());
        map.put("getDriverById", new ArrayList<>(Arrays.asList(driver, fleet)));
        return Result.success(map);
    }

    /**
     * 管理员修改司机的信息
     * /driver/updateDriverByAdmin
     *
     * @param driver {}
     * @return result {}
     */
    @PostMapping("/submitDriverByAdmin")
    public Result submitDriverByAdmin(@RequestBody Driver driver) {
//        System.out.println(driver);
        driverService.updateDriver(driver);
        return Result.success();
    }
}
