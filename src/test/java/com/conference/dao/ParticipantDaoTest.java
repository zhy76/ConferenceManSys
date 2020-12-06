package com.conference.dao;

import com.conference.entity.Participant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/4 22:30
 **/
@SpringBootTest
public class ParticipantDaoTest {
    @Autowired
    private ParticipantDao participantDao;

    @Test
    public void contextLoads(){
        System.out.println(participantDao.queryParticipants());
//        System.out.println(participantDao.queryParticipantByParticipantName("刘涔宇"));
//        System.out.println(participantDao.updateParticipant(new Participant(2,"abc","abc","abc","11111","11111","1111111","男","1111111111111")));
//        System.out.println(participantDao.deleteParticipant(2));

    }
}
