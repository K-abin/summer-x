package com.spring.summer.mapper;

import com.spring.summer.admin.SysUser;

/**
 * @Author CXB
 * @ClassName UserMapper
 * @date 2022/8/25 18:39
 * @Description TODO
 */

public interface UserMapper {


    public SysUser selectUserByUserName(String username);
}
