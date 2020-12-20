package com.conference.service.impl;

import com.conference.dao.DriverDao;
import com.conference.dao.FleetDao;
import com.conference.entity.Driver;
import com.conference.entity.Fleet;
import com.conference.service.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: FleetService
 * @Description: TODO 外键约束
 * @Author: Lance
 * @Date: 2020/12/2 21:16
 */
@Service("FleetService")
public class FleetServiceImpl implements FleetService {

    @Autowired
    private FleetDao fleetDao;

    @Autowired
    private DriverDao driverDao;

    @Override
    public List<Fleet> findAllFleet() {
        return fleetDao.findAllFleet();
    }

    @Override
    public Fleet findFleetById(Integer fleetId) {
        return fleetDao.findFleetById(fleetId);
    }

    public Fleet findFleetByPhone(String fleetPhone) {
        return fleetDao.findFleetByPhone(fleetPhone);
    }

    /**
     * 1. 设置空闲司机，解决外键约束
     * 2. null？？？
     * @param fleetId
     * @return
     */
    @Override
    public int deleteFleetById(Integer fleetId) {
        List<Driver> driverList = driverDao.findFleetAllDriver(fleetId);
        for (Driver driver : driverList) {
            driverDao.updateDriver(driver.getDriverId(),driver.getDriverName(),driver.getCarNumber(),null,driver.getDriverPass(),driver.getDriverPhone(),driver.getAssign());
        }
//        driverDao.updateDriver();
        return fleetDao.deleteFleetById(fleetId);
    }

    @Override
    public int addFleet(Fleet fleet) {
        return fleetDao.addFleet(fleet.getFleetName(), fleet.getFleetPass(), fleet.getFleetPhone());
    }

    @Override
    public int updateFleet(Fleet fleet) {
        return fleetDao.updateFleet(fleet.getFleetId(), fleet.getFleetName(), fleet.getFleetPass(),fleet.getFleetPhone());
    }

}
