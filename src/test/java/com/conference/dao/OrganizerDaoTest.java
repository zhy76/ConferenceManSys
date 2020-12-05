package com.conference.dao;

import com.conference.entity.Organizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/4 22:52
 **/
@SpringBootTest
public class OrganizerDaoTest {

    @Autowired
    private OrganizerDao organizerDao;

    @Test
    public void contextLoad(){
        System.out.println(organizerDao.queryOrganizers());
        System.out.println(organizerDao.queryOrganizerByOrganizerUnit("信息工程学院"));
        System.out.println(organizerDao.updateOrganizer(new Organizer(3,"111","111","食品学院","111@qq.com")));

        System.out.println(organizerDao.deleteOrganizer(3));
    }
}
