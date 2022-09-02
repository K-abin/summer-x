package com.spring.summer.service.impl;

import com.spring.summer.admin.SysMenu;
import com.spring.summer.mapper.SysMenuMapper;
import com.spring.summer.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author CXB
 * @ClassName SysMenuServiceImpl
 * @date 2022/9/2 21:01
 * @Description TODO
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {


    @Resource
    private SysMenuMapper sysMenuMapper;


    @Override
    public Set<String> selectMenuPermissionByUserId(Long userId) {
        List<String> sysMenus = sysMenuMapper.selectMenuPermissionByUserId(userId);
        Set<String> menuPer = new HashSet<>();
        for (String menus : sysMenus) {
            if (menus.equals(null)) {
                menuPer.addAll(Arrays.asList(menus.trim().split(",")));
            }
        }

        return menuPer;
    }
}
