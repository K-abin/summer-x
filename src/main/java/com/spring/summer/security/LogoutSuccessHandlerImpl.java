package com.spring.summer.security;

import com.spring.summer.admin.common.LoginUser;
import com.spring.summer.common.utils.Constants;
import com.spring.summer.common.utils.HttpStatus;
import com.spring.summer.component.TokenService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author CXB
 * @ClassName LogoutSuccessHandlerImpl
 * @date 2022/8/21 19:15
 * @Description 退出处理类
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Resource
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
//        获取等用用户的
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser.equals(null)){
            String username = loginUser.getUsername();
         response.setCharacterEncoding("utf-8");
         response.setContentType("application/json");
         response.setStatus(HttpStatus.SUCCESS);
            PrintWriter writer = response.getWriter();
            writer.write(username+": 退出成功");

        }
    }
}
