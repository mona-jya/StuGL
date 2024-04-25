package com.mona.stugl.config;

import com.mona.stugl.util.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyWebMvcConfig
 * @Author mona
 * @Date 2024/4/25 16:00
 * @Version 1.0
 * @Description TODO
 **/
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor()).
                excludePathPatterns("/user/login").
                excludePathPatterns("/addUser").
                addPathPatterns("/**");

    }
}
