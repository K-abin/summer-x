package com.spring.summer.service;

import java.util.Set;

/**
 * @Author CXB
 * @ClassName SysRoleService
 * @date 2022/9/2 13:41
 * @Description 用户角色service
 */

public interface SysRoleService {

    /**
     * 根据用户ID,查询角色权限
     * @param userId
     * @return
     */
    public Set<String> selectRolePermissionByUserId(Long userId);



}
