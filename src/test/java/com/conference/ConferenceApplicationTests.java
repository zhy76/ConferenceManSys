package com.conference;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConferenceApplicationTests {

    @Test
    void contextLoads() {
        String a = "2020-10-10 10:00";
        String b = "2020-12-11 21:42";
        System.out.println(a.compareTo(b));
    }
}
