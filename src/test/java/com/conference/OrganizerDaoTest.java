package com.conference;

import com.conference.dao.ConferenceDao;
import com.conference.dao.OrganizerDao;
import com.conference.entity.Conference;
import com.conference.entity.Organizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 11:54
 * @stuid 6109118041
 */
@SpringBootTest
public class OrganizerDaoTest {
    @Autowired
    private OrganizerDao organizerDao;

    @Test
    public void contextLoads(){
        System.out.println(organizerDao.updateOrganizer(1,"132141","信工","22222","22222"));
    }
}
