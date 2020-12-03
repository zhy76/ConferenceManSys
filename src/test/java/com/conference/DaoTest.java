package com.conference;

import com.conference.dao.DriverDao;
import com.conference.dao.FleetDao;
import com.conference.dao.PickUpDao;
import com.conference.entity.PickUp;
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

    @Autowired
    PickUpDao pickUpDao;

    @Test
    void contextLoads() {
//        System.out.println(pickUpDao.updatePickUpByDriverId(1,1,"1","2020-10-10 10:50","2020-10-10 10:40",false));
//        System.out.println(pickUpDao.updatePickUpByParticipantId(1,1,"1","2020-10-10 10:30","2020-10-10 10:40",false));
//        System.out.println(pickUpDao.findAllFleetPickUp(1));
//        System.out.println(pickUpDao.addPickUp(1,1,"1","2020-10-10 10:20","2020-10-10 10:40",false));
//        System.out.println(driverDao.updateDriverIsAssign(1, true));
        System.out.println(fleetDao.findFleetById(1));
//        System.out.println(driverDao.findFleetAllDriver(1));

    }

}
