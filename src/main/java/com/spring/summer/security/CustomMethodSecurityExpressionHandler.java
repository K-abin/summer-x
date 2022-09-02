package com.spring.summer.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

/**
 * @Author CXB
 * @ClassName CustomMethodSecurityExpressionHandler
 * @date 2022/9/2 20:01
 * @Description 自定义方法安全表达式处理程序
 */

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {

        CustomSecurityExpressionRoot customSecurityExpressionRoot = new CustomSecurityExpressionRoot(authentication);
        customSecurityExpressionRoot.setTrustResolver(getTrustResolver());
        customSecurityExpressionRoot.setPermissionEvaluator(getPermissionEvaluator());
//        角色层级处理
        customSecurityExpressionRoot.setRoleHierarchy(getRoleHierarchy());
        return customSecurityExpressionRoot;
    }
}
