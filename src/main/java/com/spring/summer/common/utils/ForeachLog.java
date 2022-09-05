package com.spring.summer.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.TokenList;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author CXB
 * @ClassName ForeachLog
 * @date 2022/9/5 11:09
 * @Description TODO
 */
@Slf4j
public class ForeachLog {

    /**
     * 日志输出控制台
     * @param tList list 类型
     * @param msg
     * @param <T>
     */
    public static <T> void mateList(List<T> tList,String msg){

        for (T  t: tList
             ) {
           log.info(msg+"{}:"+t);
           log.info("----------------"+msg+"结束"+"--------------");
        }
    }

    /**
     * 日志输出控制台
     * @param tSet set 类型
     * @param msg
     * @param <T>
     */
    public static <T> void mateSet(Set<T> tSet, String msg){

        for (T  t: tSet
        ) {
            log.info(msg+"{}:"+t);
            log.info("----------------"+msg+"结束"+"--------------");
        }
    }

    /**
     * 日志输出控制台
     * @param list 类型
     * @param <T>
     */
    public static <T> void MenuList(List<T> list){
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()){
           log.info(" iterator.next()");
        }
    }


}
