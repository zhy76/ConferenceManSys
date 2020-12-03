package com.conference.mapper;

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
public class AdminMapperTest {
    @Autowired
    private AdminMapper adminmapper;

    @Test
    public void contextLoads(){
        List<Admin> lists = adminmapper.queryAdmins();
        for(Admin list : lists)
        System.out.println(list);
        System.out.println(adminmapper.queryAdminByAccountAndPass("admin","lcy"));
        //System.out.println(adminmapper.addAdmin(new Admin(null , "yk" , "yk" ,"yk" )));
    }
}
