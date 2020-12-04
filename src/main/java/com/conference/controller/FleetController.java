package com.conference.controller;

import com.conference.entity.Fleet;
import com.conference.dao.FleetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private FleetDao fleetDao;

    @GetMapping("/getAllFleet")
    public List<Fleet> getAllDriver() {
        System.out.println(fleetDao.findAllFleet());
        return fleetDao.findAllFleet();
    }

    @GetMapping("/deleteFleet/{id}")
    public void deleteFleet(@PathVariable int id) {
        fleetDao.deleteFleetById(id);
    }

    @GetMapping("/addFleet/{fleetName}/{fleetPass}/{fleetPhone}")
    public void addFleet(@PathVariable String fleetName, @PathVariable  String fleetPass, @PathVariable  String fleetPhone) {
        fleetDao.addFleet(fleetName, fleetPass, fleetPhone);
    }

}
