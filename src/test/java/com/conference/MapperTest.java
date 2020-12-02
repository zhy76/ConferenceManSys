package com.conference;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName: MapperTest
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/2 15:56
 */
@SpringBootTest
public class MapperTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void sqlTest() {
//        jdbcTemplate.
    }
}
