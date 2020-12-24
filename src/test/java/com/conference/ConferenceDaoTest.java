package com.conference;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 10:35
 * @stuid 6109118041
 */

import com.conference.dao.ConferenceDao;
import com.conference.entity.Conference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.sql.Timestamp;


@SpringBootTest
public class ConferenceDaoTest {
    @Autowired
    private ConferenceDao conferenceDao;

    @Test
    public void contextLoads(){
    }
}
