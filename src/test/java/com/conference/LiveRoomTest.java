package com.conference;

import com.conference.dao.LiveRoomDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LiveRoomTest {
    @Autowired
    LiveRoomDao liveroomdao;
    @Test
    void contextLoads() {
            //liveroomdao.deleteLiveRoom(1,1);
        System.out.println(liveroomdao.addLiveRoom(1,2,1,"D111"));
    }
}
