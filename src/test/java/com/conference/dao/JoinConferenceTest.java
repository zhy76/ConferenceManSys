package com.conference.dao;

import com.conference.entity.JoinConference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/6 22:20
 * @sno 6109118015
 */
@SpringBootTest
public class JoinConferenceTest {

    @Autowired
    private JoinConferenceDao joinConferenceDao;

    @Test
    void contextLoads(){
//        System.out.println(joinConferenceDao.joinAConference(new JoinConference(3,1,1,false,
//                Timestamp.valueOf("2008-08-07 10:10:00"),Timestamp.valueOf("2008-08-10 13:10:00"),"G1234")));
//
//        System.out.println(joinConferenceDao.cancelAJoinedConferenceById(3,1));
        List<JoinConference> joinConferences = joinConferenceDao.queryConferenceByParticipantId(3);
        System.out.println(joinConferences.get(0));
        System.out.println(joinConferences.size());
    }
}
