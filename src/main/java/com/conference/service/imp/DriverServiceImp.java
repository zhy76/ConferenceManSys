package com.conference.service.imp;

import com.conference.dao.DriverDao;
import com.conference.entity.Driver;
import com.conference.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DriverService
 * @Description: TODO
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
    public List<Driver> findFleetAllDriver(int fleetId) {
        return driverDao.findFleetAllDriver(fleetId);
    }

    @Override
    public Driver findDriverById(int driverId) {
        return driverDao.findDriverById(driverId);
    }

    @Override
    public int updateDriver(Driver driver) {
        return driverDao.updateDriver(driver.getDriverId(), driver.getDriverName(), driver.getCarNumber(),
                driver.getFleetId(), driver.getDriverPass(), driver.getDriverPhone(), driver.getAssign());
    }

    /**
     * @todo 外键约束
     * @param driver
     * @return
     */
    @Override
    public int addDriver(Driver driver) {
        return driverDao.addDriver(driver.getDriverName(), driver.getCarNumber(), driver.getFleetId(),
                driver.getDriverPass(), driver.getDriverPhone());
    }

    /**
     * @todo 外键约束
     * @param driverId
     * @return
     */
    @Override
    public int deleteDriverById(int driverId) {
        return 0;
    }


    @Override
    public int updateDriverIsAssign(int driverId, boolean isAssign) {
        return 0;
    }
}
