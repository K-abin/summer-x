package com.spring.summer.config;
import com.spring.summer.security.CustomMethodSecurityExpressionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;

/**
 * @Author CXB
 * @ClassName ResourceConfig
 * @date 2022/9/2 17:34
 * @Description 通用配置
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {






//    /**
//     * 添加自定义拦截器
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
//    }

    @Bean
    CustomMethodSecurityExpressionHandler customMethodSecurityExpressionHandler(){
        return new CustomMethodSecurityExpressionHandler();
    }

}
