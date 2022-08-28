package com.spring.summer.security.filter;

import com.spring.summer.admin.common.LoginUser;
import com.spring.summer.common.utils.SecurityUtils;
import com.spring.summer.component.TokenService;
import io.lettuce.core.ScriptOutputType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.ElementType;

/**
 *  @Author CXB
 * @ClassName JwtAuthenticationTokenFilter
 * @date 2022/8/20 14:10
 * @Description 拦截器拦截请求，检查是否拥有jwt,jwt是否和redis中的一致
 * 这个拦截器不会拦截掉请求，
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
        String requestURI = request.getRequestURI();

        if (requestURI.equals("/captchaImage")){
            System.err.println("=======================================================艾克bihui输");
            filterChain.doFilter(request,response);
            return;
        }
        LoginUser loginUser = tokenService.getLoginUser(request);
        System.err.println(loginUser+"------------------------------------------------------------");;
          if (!(loginUser.equals(null)) && !(SecurityUtils.getAuthentication().equals(null))){
            tokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
