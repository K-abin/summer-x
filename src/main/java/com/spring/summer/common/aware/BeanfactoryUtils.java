package com.spring.summer.common.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @Author CXB
 * @ClassName BeanfactoryUtils
 * @date 2022/9/2 9:16
 * @Description TODO
 */

public class BeanfactoryUtils implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        BeanfactoryUtils.beanFactory = beanFactory;
    }

    public static<T> T getBean(String beanName){
        return (T)beanFactory.getBean(beanName);
    }

}
