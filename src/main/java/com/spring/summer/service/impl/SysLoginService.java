package com.spring.summer.service.impl;

import com.spring.summer.admin.common.LoginBody;
import com.spring.summer.admin.common.LoginUser;
import com.spring.summer.common.utils.RedisCache;
import com.spring.summer.component.TokenService;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author CXB
 * @ClassName SysLoginService
 * @date 2022/8/20 8:51
 * @Description TODO
 */
@Service
public class SysLoginService {
    @Resource
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;


    Authentication authenticate = null;

    /**
     * 登录验证
     * @param loginBody
     * @return
     */
    public String login(LoginBody loginBody){
//        用户登录验证
        authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));
//        登录用户的信息
        LoginUser userInformation = (LoginUser) authenticate.getPrincipal();

//        生成token
      return tokenService.create(userInformation);

    }



}
