package com.conference.service.imp;

import com.conference.dao.DriverDao;
import com.conference.entity.Driver;
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
public class DriverServiceImp {

    @Autowired
    private DriverDao driverDao;

    public List<Driver> findAllDriver() {
        return driverDao.findAllDriver();
    }

//    public List<Driver> findFleetAllDriver {
//
////        return driverDao.findAllDriver();
//    }








}
