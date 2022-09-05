package com.spring.summer.controller;

import com.spring.summer.admin.RouterVo;
import com.spring.summer.admin.SysMenu;
import com.spring.summer.admin.SysUser;
import com.spring.summer.admin.common.AjaxResult;
import com.spring.summer.admin.common.LoginBody;
import com.spring.summer.admin.common.LoginUser;
import com.spring.summer.common.utils.Constants;
import com.spring.summer.common.utils.ForeachLog;
import com.spring.summer.common.utils.SecurityUtils;
import com.spring.summer.service.SysMenuService;

import com.spring.summer.service.SysPermissionService;
import com.spring.summer.service.impl.SysLoginService;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @Author CXB
 * @ClassName LoginController
 * @date 2022/8/17 13:40
 * @Description  登录信息
 */
@RestController
@Slf4j
public class LoginController {

@Resource
private SysLoginService sysLoginService;

@Resource
private SysPermissionService permissionService;

@Resource
private SysMenuService menuService;

    /**
     *  登录成功后返回令牌
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody){
        System.out.println(loginBody);
        AjaxResult success = AjaxResult.success();
        String token = sysLoginService.login(loginBody);
        success.put(Constants.TOKEN,token);
        return success;
    }

    /**
     * 获取登录用户信息
     * @return 用户信息
     * roles 用户角色
     * permission 用户权限
     */
    @GetMapping("/getInfo")
    public AjaxResult getInfo(){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Set<String> roles = permissionService.getRolePermission(user);
        Set<String> permission = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user",user);
        ajax.put("role",roles);
        ajax.put("permission",permission);
        log.info("获取登录用户信息: {} "+ ajax);
        return ajax;

    }

    /**
     * 获取路由信息
     * @return
     */
    @GetMapping("/getRouters")
    public AjaxResult getRouters(){
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menu = menuService.selectMenuTreeByUserId(userId);
        List<RouterVo> routerVos = menuService.buildMenus(menu);
        return AjaxResult.success(routerVos);
    }
}

