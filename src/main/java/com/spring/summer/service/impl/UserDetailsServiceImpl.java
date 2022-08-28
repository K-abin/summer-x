package com.spring.summer.service.impl;

import com.spring.summer.admin.SysUser;
import com.spring.summer.admin.common.LoginUser;
import com.spring.summer.exception.ServiceException;
import com.spring.summer.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author CXB
 * @ClassName UserDetailsServiceImpl
 * @date 2022/8/25 15:41
 * @Description TODO
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (user.equals("")){
            throw new ServiceException("登录用户"+username+"不存在");
        }
        return createLoginUser(user);
    }

    private UserDetails createLoginUser(SysUser user) {

        return new LoginUser();
    }
}
