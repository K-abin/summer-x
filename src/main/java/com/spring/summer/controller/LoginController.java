package com.spring.summer.controller;

import com.spring.summer.admin.common.AjaxResult;
import com.spring.summer.admin.common.LoginBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author CXB
 * @ClassName LoginController
 * @date 2022/8/17 13:40
 * @Description  登录信息
 */
@RestController
public class LoginController {



    /**
     *  登录成功后返回令牌
     * @param loginBody
     * @return
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody){

        AjaxResult success = AjaxResult.success();



    }





}
