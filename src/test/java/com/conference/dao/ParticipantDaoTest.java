package com.conference.dao;

import com.conference.entity.Participant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/6 13:42
 * @sno 6109118015
 */
@SpringBootTest
public class ParticipantDaoTest {
    @Autowired
    private ParticipantDao participantDao;

    @Test
    void contextLoads() {
        System.out.println(participantDao.queryParticipants());
        System.out.println(participantDao.queryParticipantByParticipantName("刘涔宇1"));

//        System.out.println(participantDao.addAParticipant(new Participant(3,"abc","abc","abc","11111","11111","1111111","男","1111111111111")));
        System.out.println(participantDao.queryParticipants());
        System.out.println(participantDao.updateParticipant(new Participant(2,"abc","abc","abc","11111","11111","11111111111","男","1111111111111")));
        System.out.println(participantDao.queryParticipants());
        System.out.println(participantDao.deleteParticipant(2));
        System.out.println(participantDao.queryParticipants());

    }

}
