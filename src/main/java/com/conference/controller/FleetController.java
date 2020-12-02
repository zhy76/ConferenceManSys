package com.conference.controller;

import com.conference.entity.Driver;
import com.conference.entity.Fleet;
import com.conference.mapper.FleetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: FleetController
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/2 20:40
 */
@RestController
//@RequestMapping("/fleet")
public class FleetController {

    @Autowired
    private FleetMapper fleetMapper;

    @GetMapping("/getAllFleet")
    public List<Fleet> getAllDriver() {
        System.out.println(fleetMapper.findAllFleet());
        return fleetMapper.findAllFleet();
    }

    @GetMapping("/deleteFleet/{id}")
    public void deleteFleet(@PathVariable int id) {
        fleetMapper.deleteFleetById(id);
    }
}
