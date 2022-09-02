package com.spring.summer.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.io.Serializable;
import java.util.Collection;

/**
 * 权限评估器
 * @Author CXB
 * @ClassName CustomPermission
 * @date 2022/9/2 18:51
 * @Description 将自定义的权限评估器注册到spring 容器中，它会自动生效
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    /**
     * 路径匹配符
     */
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 登录用户的权限和premission中的做比较
     * @param authentication
     * @param o
     * @param premission
     * @return
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object premission) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (antPathMatcher.match(authority.getAuthority(), ((String) premission))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
