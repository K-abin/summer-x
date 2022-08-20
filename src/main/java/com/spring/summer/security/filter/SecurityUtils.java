package com.spring.summer.security.filter;

import com.spring.summer.admin.common.LoginUser;

import com.spring.summer.common.utils.HttpStatus;
import com.spring.summer.exception.ServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * @Author CXB
 * @ClassName SecurityUtils
 * @date 2022/8/20 20:55
 * @Description 安全工具类
 */

public class SecurityUtils {

    /**
     * 获取用户ID
     * @return
     */
    public static Long getUserId(){
        return getLoginUser().getUserId();
    }

    private static LoginUser getLoginUser() {
        try {
           return (LoginUser) getAuthentication().getPrincipal();
        }catch (Exception e){
            throw new ServiceException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    private static Authentication getAuthentication() {
       return SecurityContextHolder.getContext().getAuthentication();
    }

}
