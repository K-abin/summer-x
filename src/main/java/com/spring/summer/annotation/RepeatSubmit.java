package com.spring.summer.annotation;

import java.lang.annotation.*;

/**
 * @Author CXB
 * @ClassName RepeatSubmit
 * @date 2022/9/2 17:54
 * @Description 防止表单重复提交
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RepeatSubmit {

    /**
     * 间隔时间(毫秒) 小于此时间视为重复提交
     * @return
     */
    public int interval() default 5000;

    /**
     * 提示消息
     * @return
     */
    public String message() default "不允许重复提交，请稍后再试";
}
