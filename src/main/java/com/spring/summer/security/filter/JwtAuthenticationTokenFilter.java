package com.spring.summer.security.filter;

import com.spring.summer.admin.common.LoginUser;
import com.spring.summer.component.TokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author CXB
 * @ClassName JwtAuthenticationTokenFilter
 * @date 2022/8/20 14:10
 * @Description 拦截器拦截请求，检查是否拥有jwt,jwt是否和redis中的一致
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        LoginUser loginUser = tokenService.getLoginUser(request);
//        if (!(loginUser.equals(null)) && SecurityUtils)
    }
}
