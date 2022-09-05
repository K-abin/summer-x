package com.spring.summer.mapper;

import com.spring.summer.admin.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * @Author CXB
 * @ClassName SysMenuMapper
 * @date 2022/9/2 14:35
 * @Description TODO
 */
public interface SysMenuMapper {


    /**
     * 根据用户ID获取用户权限
     * @param userId
     * @return
     */
    public List<String> selectMenuPermissionByUserId(Long userId);

    /**
     * 根据用户IＤ来查询菜单树
     * @param userId
     * @return
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 查询全部菜单，管理员查询全部菜单
     * @return
     */
    List<SysMenu> selectMenuTreeAll();
}
