package com.conference;

import com.conference.dao.FleetDao;
import com.conference.dao.HotelDao;
import com.conference.dao.LiveRoomDao;
import com.conference.entity.Fleet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: PwdTest
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/3 19:38
 */
@SpringBootTest
public class PwdTest {
    @Autowired
    LiveRoomDao liveroomdao;
    @Test
    void contextLoads() {
        System.out.println("ok");

    }


}
