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
        System.out.println(conferenceDao.queryConferences());
        System.out.println(conferenceDao.updateConference(new Conference(1,1,1,1,"左海余", Timestamp.valueOf("2008-08-08 12:10:00") ,Timestamp.valueOf("2008-08-10 12:10:00"),"信工e224","巴拉巴拉")));
        System.out.println(conferenceDao.queryConferences());
        System.out.println(conferenceDao.addConference(new Conference(null ,1,1,1,"左海余2", Timestamp.valueOf("2009-08-08 12:10:00") ,Timestamp.valueOf("2008-08-10 12:10:00"),"信工e224","add操作测试")));
        System.out.println(conferenceDao.addConference(new Conference(null ,1,2,1,"左海余3", Timestamp.valueOf("2009-08-08 12:10:00") ,Timestamp.valueOf("2008-08-10 12:10:00"),"信工e224","add操作测试")));
  System.out.println(conferenceDao.deleteConference(6));
    }
}
