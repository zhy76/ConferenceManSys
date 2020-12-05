package com.conference.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/4 22:52
 **/
@SpringBootTest
public class OrganizerMapperTest {

    @Autowired
    private OrganizerMapper organizerMapper;

    @Test
    public void contextLoad(){
        //System.out.println(organizerMapper.queryOrganizers());
        //System.out.println(organizerMapper.queryOrganizerByOrganizerUnit("信息工程学院"));
        //System.out.println(organizerMapper.updateOrganizer(new Organizer(3,"111","111","食品学院","111@qq.com")));

        System.out.println(organizerMapper.deleteOrganizer(3));
    }
}
