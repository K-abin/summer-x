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

}
