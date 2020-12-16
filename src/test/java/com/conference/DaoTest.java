package com.conference;

//import com.conference.dao.DriverDao;
import com.conference.dao.FleetDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DaoTest {

    @Autowired
    FleetDao fleetDao;

    @Test
    void contextLoads() {
        System.out.println(fleetDao.findAllFleet());

    }

}
