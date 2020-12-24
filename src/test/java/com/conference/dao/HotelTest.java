package com.conference.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/23 16:37
 * @sno 6109118015
 */
@SpringBootTest
public class HotelTest {

    @Autowired
    private HotelDao hotelDao;

    @Test
    public void contextLoads(){
        System.out.println(hotelDao.getHotelById(1));
    }
}
