package com.spring.summer.service.impl;

import com.spring.summer.admin.SysUser;
import com.spring.summer.service.SysMenuService;
import com.spring.summer.service.SysPermissionService;
import com.spring.summer.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author CXB
 * @ClassName SysPermissionServiceImpl
 * @date 2022/9/5 13:40
 * @Description TODO
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private static final Logger log = LoggerFactory.getLogger(SysPermissionService.class);

    @Autowired
    private SysRoleService roleService;

    @Resource
    private SysMenuService menuService;

    /**
     * 获取用户角色
     * @param user
     * @return
     */
    @Override
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles  = new HashSet<>();
        //判断是否是管理员
        if (user.isAdmin()){
            roles.add("admin");
        }else {
//            通过Id获取user对应的权限
            Set<String> rolePermiss = roleService.selectRolePermissionByUserId(user.getUserId());
            roles.addAll(rolePermiss);
        }

        for (String role : roles) {
            log.info("用户名称：{}"+user.getUserName()+"用户角色：{}"+role);
        }
        return roles;
    }

    @Override
    public Set<String> getMenuPermission(SysUser user) {
        Set<String> permissions = new HashSet<>();
        if(user.isAdmin()){
            permissions.add("*:*:*");
        }else {
            Set<String> menus = menuService.selectMenuPermissionByUserId(user.getUserId());
            permissions.addAll(menus);
            for (String permission : permissions) {
                log.info("用户权限：{}"+permission);
            }

        }
        return permissions;
    }


}
