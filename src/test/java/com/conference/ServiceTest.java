package com.conference;

import com.conference.controller.PickUpController;
import com.conference.dao.DriverDao;
import com.conference.dao.FleetDao;
import com.conference.dao.JoinConferenceDao;
import com.conference.dao.PickUpDao;
import com.conference.entity.Driver;
import com.conference.entity.PickUp;
import com.conference.service.PickUpService;
import com.conference.service.impl.DriverServiceImpl;
import com.conference.service.impl.FleetServiceImpl;
import com.conference.service.Impl.PickUpServiceImpl;
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
    private JoinConferenceDao joinConferenceDao;

    @Autowired
    DriverDao driverDao;
    @Autowired
    private PickUpService pickUpService;
    @Autowired
    private FleetDao fleetDao;
    private PickUpController pickUpController;
    @Autowired
    private PickUpDao pickUpDao;
    @Test
    void contextLoads() {
        String s1 = "14:31";
        String s2 = "40:01";
        System.out.println(s1.compareTo(s2));
//        System.out.println(joinConferenceDao.queryJoinConferenceByConferenceId(1));
//        System.out.println(pickUpService.findPickUp(2,1));
//        System.out.println(pickUpService.findPickUp(1, 2));
//        System.out.println(pickUpService.updatePickUpByConferenceIdAndParticipantId(new PickUp(4,1,1,1,1,"000000","2020-10-10 20:00:00","2020-10-10 20:00:00", true)));
//        System.out.println(joinConferenceDao.queryJoinedConferenceByParticipantIdAndConferenceId(1,1));
//        System.out.println(pickUpDao.findPickUpByParticipantIdAndConferenceId(1, 1));
//        System.out.println(joinConferenceDao.queryJoinConferenceByConferenceId(1));
//        System.out.println(pickUpDao.updatePickUp());
//
//        System.out.println(fleetDao.findAllFleet());
//        System.out.println(fleetServiceImpl.findAllFleet());
    }
    @Test
    void fleetTest() {
//        System.out.println(fleetDao.findFleetById(1));
        //System.out.println(fleetDao.updateFleet(1, "hhh", "123456", "19914665733"));

        //System.out.println(pickUpDao.findPickUpById(1));
//    System.out.println(pickUpService.findPickUpById(1));
      //  Driver driver = new Driver(4, "hsc", "888888", null, "123456", "19914665732", false);
     //System.out.println(driverServiceImpl.addDriver(driver));
//        System.out.println(driverDao.addDriver("hsc", "888888", null, "123456", "19914665732"));
//        System.out.println(driverServiceImp.findDriverByPhone("12121212"));
//        System.out.println(fleetServiceImp.findAllFleet());
//        System.out.println(fleetServiceImp.findFleetById(1));
//        System.out.println(fleetDao.deleteFleetById(1));
////        System.out.println(fleetServiceImp.addFleet());
//        System.out.println(fleetServiceImp.deleteFleetById(1));

    }
}
