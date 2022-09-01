package com.spring.summer.controller;

import com.spring.summer.admin.common.AjaxResult;
import com.spring.summer.admin.common.LoginBody;
import com.spring.summer.common.utils.Constants;
import com.spring.summer.service.impl.SysLoginService;

import org.aspectj.weaver.ast.Var;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author CXB
 * @ClassName LoginController
 * @date 2022/8/17 13:40
 * @Description  登录信息
 */
@RestController
public class LoginController {

@Resource
private SysLoginService sysLoginService;


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
}

