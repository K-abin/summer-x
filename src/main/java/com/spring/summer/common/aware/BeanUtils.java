package com.spring.summer.common.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author CXB
 * @ClassName BeanUtils
 * @date 2022/9/2 8:59
 * @Description Bean工具类， 通过工具类可以调用任意bean
 */
@Component
public class BeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }

    public static<T> T getBean(String beanName){
        return (T)applicationContext.getBean(beanName);
    }
}
