package com.conference.dao;

import com.conference.entity.JoinConference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
//        System.out.println(joinConferenceDao.joinAConference(new JoinConference(4,13,true,false,
//                "2008-08-07 10:10:00","2008-08-10 13:10:00","G1234",true)));
//        System.out.println(joinConferenceDao.joinAConference(new JoinConference(4,6,1,false,
//                "2008-08-17 10:10:00","2008-08-18 13:10:00","G1642")));
//
//        System.out.println(joinConferenceDao.cancelAJoinedConferenceById(1,1));
//        List<JoinConference> joinConferences = joinConferenceDao.queryConferenceByParticipantId(4);
//        System.out.println(joinConferences);
//        System.out.println(joinConferences.size());
        System.out.println(joinConferenceDao.queryUnConfirmConferenceByParticipantId(1));
    }
}
