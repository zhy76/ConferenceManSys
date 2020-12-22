package com.conference.dao;

import com.conference.entity.Organizer;
import com.conference.service.OrganizerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/18 10:02
 * @sno 6109118015
 */
@SpringBootTest
public class OrganizerDaoTest {

    @Autowired
    private OrganizerDao organizerDao;

    @Autowired
    private OrganizerService organizerService;


    @Test
    void contextLoads(){
        Organizer organizer = organizerDao.findOrganizerById(1);
        System.out.println(organizer);

        Organizer organizer1 = organizerService.findOrganizerById(1);
        System.out.println(organizer1);
    }
}
