package com.conference.dao;

import com.conference.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/3 20:44
 **/
@SpringBootTest
public class AdminDaoTest {
    @Autowired
    private AdminDao adminDao;

    @Test
    public void contextLoads(){
    }
}
