package com.conference.dao;

import com.conference.entity.Conference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/3 22:30
 **/
@SpringBootTest
public class ConferenceDaoTest {
    @Autowired
    private ConferenceDao conferenceDao;

    @Test
    public void contextLoads(){
       System.out.println(conferenceDao.queryConferences());
       System.out.println(conferenceDao.updateConference(new Conference(1,1,1,1,"刘涔宇", Timestamp.valueOf("2008-08-08 12:10:00") ,Timestamp.valueOf("2008-08-10 12:10:00"),"信工e224","阿巴阿巴")));
       System.out.println(conferenceDao.deleteConference(2));
    }
}
