package com.spring.summer.service.impl;

import com.spring.summer.admin.SysUser;
import com.spring.summer.mapper.UserMapper;
import com.spring.summer.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.PrimitiveIterator;

/**
 * @Author CXB
 * @ClassName SysUserServiceImpl
 * @date 2022/8/25 18:37
 * @Description TODO
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public SysUser selectUserByUserName(String username) {
        return userMapper.selectUserByUserName(username);
    }
}
