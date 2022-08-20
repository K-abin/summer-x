package com.spring.summer.admin.common;

import lombok.Data;

/**
 * @Author CXB
 * @ClassName LoginBody
 * @date 2022/8/20 8:45
 * @Description TODO
 */
@Data
public class LoginBody {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

}
