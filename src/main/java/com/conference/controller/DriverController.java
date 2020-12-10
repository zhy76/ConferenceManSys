package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.entity.Driver;
import com.conference.dao.DriverDao;
import com.conference.entity.PickUp;
import com.conference.service.DriverService;
import com.conference.service.PickUpService;
import com.conference.service.TokenService;
import com.conference.service.impl.PickUpServiceImpl;
import com.conference.service.impl.TokenServiceImpl;
import io.jsonwebtoken.Claims;
import org.apache.ibatis.jdbc.Null;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * @ClassName: DriverController
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/2 18:52
 */
// /driver/register
@RestController
@RequestMapping("/Driver")
public class DriverController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DriverDao driverDao;

    //    @Resource(name = "driverServiceImpl")
    @Autowired
    private DriverService driverService;

    @Autowired
    private PickUpService pickUpService;


//    @GetMapping("/getAllDriver")
//    public List<Driver> findAllDriver() {
//        System.out.println(driverDao.findAllDriver());
//        return driverDao.findAllDriver();
//    }

    @GetMapping("/deleteDriver/{id}")
    public void deleteDriver(@PathVariable int id) {
        driverDao.deleteDriverById(id);
    }

    // /addDriver/ggg/123/1/123/123
    @PostMapping("/register")
    public Object register(@Valid @RequestBody Driver driver) {
        JSONObject jsonObject = new JSONObject();
        if (driver.getDriverName() == null || driver.getDriverPass() == null) {
            jsonObject.put("message", "表单错误");
            return jsonObject;
        }
        int addNumber = driverService.addDriver(driver);
        if (addNumber > 0) {
            String token = tokenService.getToken(addNumber);
            jsonObject.put("token", token);
            return jsonObject;
        } else {
            jsonObject.put("message", "注册失败");
            return jsonObject;
        }
//        return jsonObject;
    }

    @PostMapping("/login")
    public Object login(@RequestBody Driver driver) {
        JSONObject jsonObject = new JSONObject();
        if (driver.getDriverPhone() == null || driver.getDriverPass() == null) {
//            System.out.println("null");
            jsonObject.put("message", "表单错误");
            return jsonObject;
        }
        Driver driverForBase = driverService.findDriverByPhone(driver.getDriverPhone());
        System.out.println(driverForBase);
//        User userForBase =userDao.findByUsername(loginUser.getUserName());
        if (driverForBase == null) {
            jsonObject.put("message", "登录失败,用户不存在");
            return jsonObject;
        } else {
            if (!driverForBase.getDriverPass().equals(driver.getDriverPass())) {
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = tokenService.getToken(driverForBase);
                jsonObject.put("token", token);
                return jsonObject;
            }
        }
    }

    // 司机自己通过司机id查找所有的接送记录
    @RequestMapping("/getDriverAllPickUpById")
    public List<PickUp> getDriverAllPickUp(@RequestParam(value ="driverId")Integer driverId) {
        return pickUpService.findAllDriverPickUp(driverId);
    }

    // 司机修改自己的信息
    @PostMapping("/updateDriver")
//    @GetMapping("/updateDriver/{driverId}/{driverName}/{carNumber}/{fleetId}/{driverPass}/{driverPhone}")
    public Object updateDriver(@RequestBody Driver driver, HttpServletRequest request) {
        JSONObject result=new JSONObject();
        System.out.println(request.getHeader("token"));
        Claims claims = tokenService.parseToken(request.getHeader("token"));


        driver.setDriverId((Integer) claims.get("driverId"));
        driver.setAssign(driverService.findDriverById(driver.getDriverId()).getAssign());
        System.out.println(driver.getDriverId());
        try {
           driverService.updateDriver(driver);
        } catch (DataAccessException e) {
            throw new RuntimeException("修改失败");
        }
        result.put("state",1);
        return result.toJSONString();
    }

}
