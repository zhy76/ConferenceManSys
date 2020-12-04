package com.conference;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: PwdTest
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/3 19:38
 */
@SpringBootTest
public class PwdTest {
    // 合并成功

    //注入StringEncryptor
    @Autowired
    StringEncryptor encryptor;

    @Test
    public void encry() {
        String usernameE = encryptor.encrypt("admin");
        String usernameD = encryptor.decrypt(usernameE);
        System.out.println(usernameE);
        System.out.println(usernameD);
    }
}
