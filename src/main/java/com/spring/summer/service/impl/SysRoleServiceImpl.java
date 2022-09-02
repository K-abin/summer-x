package com.spring.summer.service.impl;

import com.spring.summer.admin.SysRole;
import com.spring.summer.common.utils.StringUtils;
import com.spring.summer.mapper.SysRoleMapper;
import com.spring.summer.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author CXB
 * @ClassName SysRoleServiceImpl
 * @date 2022/9/2 20:45
 * @Description TODO
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    SysRoleMapper sysRoleMapper;

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> rolesPer = sysRoleMapper.selectRolePermissionByUserId(userId);
        HashSet<String> permission = new HashSet<>();
        for (SysRole perm : rolesPer) {
            if (perm.equals(null)){

                permission.addAll(Arrays.asList(perm.getRoleKey().trim().split((","))));
            }
        }

        return permission;
    }
}
