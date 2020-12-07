package com.conference.service.imp;

import com.conference.dao.DriverDao;
import com.conference.entity.Driver;
import com.conference.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DriverService
 * @Description: TODO 外键约束
 * @Author: Lance
 * @Date: 2020/12/2 21:11
 */
@Service("DriverService")
public class DriverServiceImp implements DriverService {

    @Autowired
    private DriverDao driverDao;

    @Override
    public List<Driver> findAllDriver() {
        return driverDao.findAllDriver();
    }

    @Override
    public List<Driver> findFleetAllDriver(Integer fleetId) {
        return driverDao.findFleetAllDriver(fleetId);
    }

    @Override
    public Driver findDriverById(Integer driverId) {
        return driverDao.findDriverById(driverId);
    }

    @Override
    public Driver findDriverByPhone(String driverPhone) {
        return driverDao.findDriverByPhone(driverPhone);
    }

    @Override
    public int updateDriver(Driver driver) {
        return driverDao.updateDriver(driver.getDriverId(), driver.getDriverName(), driver.getCarNumber(),
                driver.getFleetId(), driver.getDriverPass(), driver.getDriverPhone(), driver.getAssign());
    }

    /**
     * @param driver
     * @return
     * @todo 外键约束
     */
    @Override
    public int addDriver(Driver driver) {
        return driverDao.addDriver(driver.getDriverName(), driver.getCarNumber(), driver.getFleetId(),
                driver.getDriverPass(), driver.getDriverPhone());
    }

    /**
     * @param driverId
     * @return
     * @todo 外键约束
     */
    @Override
    public int deleteDriverById(Integer driverId) {
        return driverDao.deleteDriverById(driverId);
    }


    @Override
    public int updateDriverIsAssign(Integer driverId, Boolean isAssign) {
        return updateDriverIsAssign(driverId, isAssign);
    }
}
