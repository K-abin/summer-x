package com.spring.summer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SummerApplicationTests {

    @Test
    void contextLoads() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        String string = uuid.toString();
        String replace = string.replace("-", "");
        System.out.println(replace);
    }

}
