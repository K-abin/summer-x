package com.spring.summer.service.impl;
import com.spring.summer.admin.common.LoginBody;
import com.spring.summer.admin.common.LoginUser;
import com.spring.summer.component.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    /**
     * 登录验证
     * @param loginBody
     * @return
     */
    public String login(LoginBody loginBody){

        String username = loginBody.getUsername();

        String password = loginBody.getPassword();

        Authentication authentication = null;
//        用户登录验证
        try{
//            authenticate = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        }catch (Exception exception){

                throw new UsernameNotFoundException("用户名不存在！");

        }

//        登录用户的信息
        LoginUser userInformation = (LoginUser) authentication.getPrincipal();

//        生成token
      return tokenService.create(userInformation);

    }



}
