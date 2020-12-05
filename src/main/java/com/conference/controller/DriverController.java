package com.conference.controller;

import com.conference.entity.Driver;
import com.conference.dao.DriverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: DriverController
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/2 18:52
 */

@RestController
//@RequestMapping("/driver")
public class DriverController {


    @Autowired
    private DriverDao driverDao;
    @GetMapping("/getAllDriver")
    public List<Driver> findAllDriver() {
        System.out.println(driverDao.findAllDriver());
        return driverDao.findAllDriver();
    }

    @GetMapping("/deleteDriver/{id}")
    public void deleteDriver(@PathVariable int id) {
        driverDao.deleteDriverById(id);
    }

    // /addDriver/ggg/123/1/123/123
    @GetMapping("/addDriver/{driverName}/{carNumber}/{fleetId}/{driverPass}/{driverPhone}")
    public void register(@PathVariable String driverName, @PathVariable String carNumber,@PathVariable int fleetId, @PathVariable String driverPass, @PathVariable String driverPhone) {

        driverDao.addDriver(driverName, carNumber, fleetId, driverPass, driverPhone);
        //        driverMapper.addDriver();
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
