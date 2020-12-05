package com.conference.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/3 22:30
 **/
@SpringBootTest
public class ConferenceMapperTest {
    @Autowired
    private ConferenceMapper conferenceMapper;

    @Test
    public void contextLoads(){
       System.out.println(conferenceMapper.queryConferences());
       //System.out.println(conferenceMapper.updateConference(new Conference(2,1,1,1,"刘涔宇",Timestamp.valueOf("2008-08-08 12:10:00") ,Timestamp.valueOf("2008-08-10 12:10:00"),"信工e224","阿巴阿巴")));
       System.out.println(conferenceMapper.deleteConference(2));
    }
}
