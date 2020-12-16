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
//        System.out.println(conferenceDao.queryConferences());
//        System.out.println(conferenceDao.updateConference(new Conference(1,1,1,1,"刘涔宇", Timestamp.valueOf("2008-08-08 12:10:00") , Timestamp.valueOf("2008-08-10 12:10:00"),"信工e224","阿巴阿巴")));
//        System.out.println(conferenceDao.deleteConference(2));
        System.out.println(conferenceDao.updateConference(new Conference(5,1,1,1,"凌宸","2008-08-07 12:10:00","2008-08-11 12:10:00","信工e224","阿巴阿巴")));
        System.out.println(conferenceDao.queryConferenceByConferenceId(5));
        System.out.println(conferenceDao.queryConferences());
    }
}
