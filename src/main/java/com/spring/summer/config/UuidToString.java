package com.spring.summer.config;

import java.util.UUID;

/**
 * @Author CXB
 * @ClassName UuidToString
 * @date 2022/8/14 12:23
 * @Description UUID处理
 */

public class UuidToString {

//    去掉uuid中的“-”
    public static String UUIDToUtils(UUID uuid){
        String UuToString = uuid.toString();
        String replace = UuToString.replace("-", "");
        return replace;
    }

}
