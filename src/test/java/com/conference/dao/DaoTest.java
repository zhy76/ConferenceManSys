package com.conference.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: DriverDaoTest
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/3 14:55
 */
@SpringBootTest
public class DaoTest {
    @Autowired
    DriverDao driverDao;

    @Autowired
    FleetDao fleetDao;

    @Test
    void contextLoads() {
        System.out.println(fleetDao.findFleetById(1));
//        System.out.println(driverDao.findFleetAllDriver(1));

    }

}
