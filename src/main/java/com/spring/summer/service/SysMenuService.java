package com.spring.summer.service;

import com.spring.summer.admin.RouterVo;
import com.spring.summer.admin.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * @Author CXB
 * @ClassName SysMenuService
 * @date 2022/9/2 13:41
 * @Description  用户菜单service
 */

public interface SysMenuService {

    /**
     * 根据用户ID获取用户权限
     * @param userId
     * @return
     */
    public Set<String> selectMenuPermissionByUserId(Long userId);

    List<RouterVo> buildMenus(List<SysMenu> menu);

    List<SysMenu> selectMenuTreeByUserId(Long userId);
}
