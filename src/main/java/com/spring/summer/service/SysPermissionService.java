package com.spring.summer.service;

import com.spring.summer.admin.SysUser;

import java.util.Set;

/**
 * @Author CXB
 * @ClassName SysPermissionService
 * @date 2022/9/5 13:39
 * @Description TODO
 */
public interface SysPermissionService {
    /**
     * 获取用户信息
     * @param user
     * @return
     */
    public Set<String> getRolePermission(SysUser user);

    /**
     * 获取用户权限
     * @param user
     * @return
     */
    public Set<String> getMenuPermission(SysUser user);
}
