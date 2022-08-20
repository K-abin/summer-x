package com.spring.summer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SummerApplicationTests {

    @Test
    void contextLoads() {
        UUID uuids = UUID.randomUUID();
        StringBuilder stringBuilder = null;
        String[] split = uuids.toString().split("");
        for (int i = 0; i < split.length - 1; i++) {
            if (split[i] == "-") {
                stringBuilder.append("");
            }
            stringBuilder = stringBuilder.append(split[i]);

            System.out.println(stringBuilder);
        }
    }
}