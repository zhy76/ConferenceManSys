package com.conference.service;

import com.conference.entity.Driver;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: DriverService
 * @Description:
 * @Author: Lance
 * @Date: 2020/12/5 17:20
 */
public interface DriverService {

    List<Driver> findAllDriver();

    List<Driver> findFleetAllDriver(Integer fleetId);

    Driver findDriverById(Integer driverId);

    Driver findDriverByPhone(String driverPhone);

    int updateDriver(Driver driver);

    int addDriver(Driver driver);

    int deleteDriverById(Integer driverId);

    int updateDriverIsAssign(Integer driverId, Boolean isAssign);


}
