package com.spring.summer.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author CXB
 * @ClassName ServletUtils
 * @date 2022/9/2 18:04
 * @Description TODO
 */

public class ServletUtils {


    public static void responseResult(HttpServletResponse response,String msg){
        PrintWriter writer =null;
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
             writer = response.getWriter();
            writer.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.close();
            writer.flush();
        }

    }
}
