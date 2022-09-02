package com.spring.summer.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

/**
 * @Author CXB
 * @ClassName CustomSecurityExpressionRoot
 * @date 2022/9/2 19:33
 * @Description security 注解的判断逻辑
 */

public class CustomSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;

    private Object returnObject;

//    路径匹配符
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public CustomSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    /**
     * 判断当前对象是否具备某个权限
     * @param permission
     * @return
     */
    public boolean hasPermission(String permission){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (antPathMatcher.match(authority.getAuthority(),permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否具有多个权限中的任意一个权限
     * @param permissions
     * @return
     */
    public boolean hasAnyPermissions(String ... permissions){
        if (permissions.length == 0 || permissions.equals(null)) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            for (String permission : permissions) {
                if (antPathMatcher.match(authority.getAuthority(),permission)){
                    return true;
                }
            }
        }
            return false;
    }

    /**
     * 多个权限都要拥有
     * @param permissions
     * @return
     */
    public boolean hasAllPermissions(String ... permissions){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (permissions == null || permissions.length ==0) {
            return false;
        }
        for (String permission : permissions) {
            boolean flag = false;
            for (GrantedAuthority authority : authorities) {
                if (antPathMatcher.match(authority.getAuthority(),permission)){
                    flag = true;
                }
            }if (!flag){
                return  false;
            }
        }
        return true;
    }

    @Override
    public void setFilterObject(Object o) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object o) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
