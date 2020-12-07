package com.conference;

import com.conference.dao.DriverDao;
import com.conference.dao.FleetDao;
import com.conference.entity.Driver;
import com.conference.entity.Fleet;
import com.conference.service.FleetService;
import com.conference.service.imp.DriverServiceImp;
import com.conference.service.imp.FleetServiceImp;
import com.conference.service.imp.PickUpServiceImp;
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
    private FleetServiceImp fleetServiceImp;
    @Autowired
    private DriverServiceImp driverServiceImp;

    @Autowired
    DriverDao driverDao;
    @Autowired
    private PickUpServiceImp pickUpServiceImp;
    @Autowired
    private FleetDao fleetDao;

    @Test
    void contextLoads() {


//        System.out.println(fleetDao.findAllFleet());
        System.out.println(fleetServiceImp.findAllFleet());
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
