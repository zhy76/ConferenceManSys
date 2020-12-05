package com.conference.service;

import com.conference.entity.Driver;

import java.util.List;

/**
 * @ClassName: DriverService
 * @Description:
 * @Author: Lance
 * @Date: 2020/12/5 17:20
 */
public interface DriverService {

    List<Driver> findAllDriver();

    List<Driver> findFleetAllDriver(int fleetId);

    Driver findDriverById(int driverId);

    int updateDriver(Driver driver);

    int addDriver(Driver driver);

    int deleteDriverById(int driverId);

    int updateDriverIsAssign(int driverId, boolean isAssign);
}
