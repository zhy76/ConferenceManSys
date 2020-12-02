package com.conference.controller;

import com.conference.entity.Driver;
import com.conference.mapper.DriverMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
    private DriverMapper driverMapper;
    @GetMapping("/getAllDriver")
    public List<Driver> findAllDriver() {
        System.out.println(driverMapper.findAllDriver());
        return driverMapper.findAllDriver();
    }

    @GetMapping("/deleteDriver/{id}")
    public void deleteFleet(@PathVariable int id) {
        driverMapper.deleteDriverById(id);
    }


}
