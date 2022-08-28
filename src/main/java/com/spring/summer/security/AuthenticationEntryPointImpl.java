package com.spring.summer.security;

import com.spring.summer.common.utils.HttpStatus;
import net.sf.jsqlparser.util.SelectUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @Author CXB
 * @ClassName AuthenticationEntryPointImpl
 * @date 2022/8/21 19:03
 * @Description 认证失败处理类，返回未授权
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {


    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        try {
            int code = HttpStatus.UNAUTHORIZED;
            response.setStatus(code);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("认证失败，无法访问系统资源");
        } catch (Exception exception){
            e.printStackTrace();
        }
    }
}
