package com.spring.summer.service;

import com.spring.summer.admin.SysUser;

/**
 * @Author CXB
 * @ClassName SysUserService
 * @date 2022/8/20 8:57
 * @Description TODO
 */

public interface SysUserService {
    /**
     *  通过用户名查询用户
     * @param username 用户名
     * @return 用户对象信息
     */
    SysUser selectUserByUserName(String username);
}
