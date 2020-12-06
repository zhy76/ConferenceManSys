package com.conference;

import com.conference.dao.FleetDao;
import com.conference.dao.HotelDao;
import com.conference.dao.LiveRoomDao;
import com.conference.entity.Fleet;
import org.junit.jupiter.api.Test;
//import org.junit.Test;

//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: PwdTest
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/3 19:38
 */
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@RunWith(SpringRunner.class)
@SpringBootTest
public class PwdTest {
    @Autowired
    LiveRoomDao liveroomdao;
    @Test
     void contextLoads() {
        System.out.println(liveroomdao.findAllLiveRoom());

    }


}
