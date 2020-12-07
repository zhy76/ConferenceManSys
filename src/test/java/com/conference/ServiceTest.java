package com.conference;

import com.conference.dao.DriverDao;
import com.conference.dao.FleetDao;
import com.conference.service.impl.DriverServiceImpl;
import com.conference.service.impl.FleetServiceImpl;
import com.conference.service.impl.PickUpServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: ServiceTest
 * @Description: That's enough.
 * @Author: Lance
 * @Date: 2020/12/5 18:05
 */
@SpringBootTest
public class ServiceTest {

//    @Autowired()

    @Autowired
    private FleetServiceImpl fleetServiceImpl;
    @Autowired
    private DriverServiceImpl driverServiceImpl;

    @Autowired
    DriverDao driverDao;
    @Autowired
    private PickUpServiceImpl pickUpServiceImpl;
    @Autowired
    private FleetDao fleetDao;

    @Test
    void contextLoads() {


//        System.out.println(fleetDao.findAllFleet());
        System.out.println(fleetServiceImpl.findAllFleet());
    }
    @Test
    void fleetTest() {
//        Driver driver = new Driver(4, "hsc", "888888", null, "123456", "19914665732", false);
//        System.out.println(driverServiceImp.addDriver(driver));
        System.out.println(driverDao.addDriver("hsc", "888888", null, "123456", "19914665732"));
//        System.out.println(driverServiceImp.findDriverByPhone("12121212"));
//        System.out.println(fleetServiceImp.findAllFleet());
//        System.out.println(fleetServiceImp.findFleetById(1));
//        System.out.println(fleetDao.deleteFleetById(1));
////        System.out.println(fleetServiceImp.addFleet());
//        System.out.println(fleetServiceImp.deleteFleetById(1));

    }
}
