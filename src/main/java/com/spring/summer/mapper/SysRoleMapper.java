package com.spring.summer.mapper;

import com.spring.summer.admin.SysRole;

import java.util.List;
import java.util.Set;

/**
 * @Author CXB
 * @ClassName SysRoleMapper
 * @date 2022/9/2 14:20
 * @Description TODO
 */
public interface SysRoleMapper {

    /**
     * 根据用户ID,查询角色权限
     * @param userId
     * @return
     */
    public List<SysRole> selectRolePermissionByUserId(Long userId);



}
