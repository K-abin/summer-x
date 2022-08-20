package com.spring.summer.common.utils;

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
//    生成uuid 切去掉“-”线
    public static String UUIDFast(){
        UUID uuids = UUID.randomUUID();
        String stringBuilder = "";
        String[] split = uuids.toString().split("");
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("-") ) {
                continue;
            }else {
                stringBuilder += split[i];
            }
        }
        return stringBuilder;
    }

};

