package com.tc.config;
 
import com.tc.interceptor.LoginInterceptor;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * 配置静态资源映射
 *
 * @author sunziwen
 * @version 1.0
 * @date 2018-11-16 14:57
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] STATIC_URL = new String[]{"/css/**","/images/**","/js/**","/lib/**"};
    private static final String[] FORWORD_STATIC_URL = new String[]{"/static/css/**","/static/images/**","/static/js/**","/static/lib/**"};
    /**
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler(STATIC_URL).addResourceLocations(FORWORD_STATIC_URL);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin","/admin/login");
    }

    @Bean
    public HandlerInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
}