package com.conference;

//import com.conference.dao.DriverDao;
import com.conference.dao.FleetDao;
import com.conference.dao.HotelDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DaoTest {
    @Autowired
    HotelDao hotelDao;
//    @Autowired
//    DriverDao driverDao;
    @Autowired
    FleetDao fleetDao;

    @Test
    void contextLoads() {
        System.out.println(fleetDao.findAllFleet());
//        System.out.println(driverDao.findAllDriver());
        System.out.println(hotelDao.findAllHotel());
    }

}
