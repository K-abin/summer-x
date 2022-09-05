package com.spring.summer.common.utils;

/**
 * @Author CXB
 * @ClassName StringUtils
 * @date 2022/8/28 9:50
 * @Description TODO
 */

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 判断一个字符串是否未非空字符串
     * @param string
     * @return true 非空  false 空
     */
    public static boolean isNotEmpty(String string){
        return !isEmpty(string);
    }



    public static boolean isNull(Object object){
        return  object == null;
    }

    /**
     * 是否为http(s)://开头
     *
     * @param link 链接
     * @return 结果
     */
    public static boolean ishttp(String link) {
        return StringUtils.startsWithAny(link, Constants.HTTP, Constants.HTTPS);
    }

}
