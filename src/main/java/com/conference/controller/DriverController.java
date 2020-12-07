package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.entity.Driver;
import com.conference.dao.DriverDao;
import com.conference.service.DriverService;
import com.conference.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    private TokenServiceImpl tokenServiceImpl;

    @Autowired
    private DriverDao driverDao;

//    @Resource(name = "driverServiceImpl")
    @Autowired
    private DriverService driverService;


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
        JSONObject jsonObject=new JSONObject();
        if(driver.getDriverName() == null || driver.getDriverPass() == null){
            jsonObject.put("message","表单错误");
            return jsonObject;
        }
        int addNumber = driverService.addDriver(driver);
        if(addNumber>0){
            String token= tokenServiceImpl.getToken(addNumber);
            jsonObject.put("token",token);
            return jsonObject;
        }else{
            jsonObject.put("message","注册失败");
            return jsonObject;
        }
//        return jsonObject;
    }

    @PostMapping("/login")
    public Object login(@RequestBody Driver driver) {
        JSONObject jsonObject = new JSONObject();
        if (driver.getDriverPhone() == null || driver.getDriverPass() == null) {
            System.out.println("null");
            jsonObject.put("message","表单错误");
            return jsonObject;
        }
        Driver driverForBase = driverDao.findDriverByPhone(driver.getDriverPhone());
        System.out.println(driverForBase);
//        User userForBase =userDao.findByUsername(loginUser.getUserName());
        if(driverForBase == null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!driverForBase.getDriverPass().equals(driver.getDriverPass())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenServiceImpl.getToken(driverForBase);
                jsonObject.put("token", token);
                return jsonObject;
            }
        }
    }

// /updateDriver/1/hh/88888/1/1/12121212
    @GetMapping("/updateDriver/{driverId}/{driverName}/{carNumber}/{fleetId}/{driverPass}/{driverPhone}")
    public void updateDriver(@PathVariable int driverId, @PathVariable String driverName,
                             @PathVariable String carNumber, @PathVariable int fleetId,
                             @PathVariable String driverPass, @PathVariable String driverPhone) {
        driverDao.updateDriver(driverId,driverName, carNumber, fleetId,
        driverPass, driverPhone, false);
    }
}
