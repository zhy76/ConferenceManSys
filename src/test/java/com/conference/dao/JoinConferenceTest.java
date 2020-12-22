package com.conference.dao;

import com.conference.entity.JoinConference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/6 22:20
 * @sno 6109118015
 */
@SpringBootTest
public class JoinConferenceTest {

    @Autowired
    private JoinConferenceDao joinConferenceDao;

    @Test
    void contextLoads(){
    }
}
