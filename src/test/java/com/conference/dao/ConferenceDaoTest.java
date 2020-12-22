package com.conference.dao;

import com.conference.entity.Conference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/6 22:00
 * @sno 6109118015
 */
@SpringBootTest
public class ConferenceDaoTest {
    @Autowired
    private ConferenceDao conferenceDao;

    @Test
    public void contextLoads(){
    }
}
