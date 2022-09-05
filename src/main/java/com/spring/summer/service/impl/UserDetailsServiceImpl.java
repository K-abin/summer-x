package com.spring.summer.service.impl;

import com.spring.summer.admin.SysUser;
import com.spring.summer.admin.common.LoginUser;
import com.spring.summer.common.utils.StringUtils;
import com.spring.summer.exception.ServiceException;
import com.spring.summer.service.SysPermissionService;
import com.spring.summer.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

   private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Resource
    private SysUserService userService;

    @Resource
    private SysPermissionService permissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user)){
            log.info("登录用户：{} 不存在",username);
            throw new ServiceException("登录用户"+username+"不存在");
        }
        return createLoginUser(user);
    }


    public UserDetails createLoginUser(SysUser user){
        return new LoginUser(user.getUserId(),user.getDeptId(),user,permissionService.getMenuPermission(user));
    }

}
