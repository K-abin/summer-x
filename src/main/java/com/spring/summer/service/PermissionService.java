package com.spring.summer.service;

import com.spring.summer.admin.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author CXB
 * @ClassName PermissionService
 * @date 2022/9/2 13:38
 * @Description 用户权限处理
 */
@Component
public class PermissionService {

    private static final Logger log = LoggerFactory.getLogger(PermissionService.class);

    @Autowired
    private SysRoleService roleService;

    @Resource
    private SysMenuService menuService;

    /**
     * 获取用户角色
     * @param user
     * @return
     */
    public Set<String> getRolePermission(SysUser user){
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

    public Set<String> getMenuPermission(SysUser user){
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
