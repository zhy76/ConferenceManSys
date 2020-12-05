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
        List<Admin> lists = adminDao.queryAdmins();
        for(Admin list : lists)
        System.out.println(list);
        System.out.println(adminDao.queryAdminByAccountAndPass("admin","lcy"));
        System.out.println(adminDao.addAdmin(new Admin(null , "yk" , "yk" ,"yk" )));
        System.out.println(adminDao.updateAdmin(new Admin(1,"lyc","lyc","adminlyc")));
        System.out.println(adminDao.deleteAdmin(6));
    }
}
