package com.it.boot.config;

import com.it.boot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
* 1.编写一个拦截器实现HandlerInterceptor接口
* 2.拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）
* 3.指定拦截规则，如果拦截所有，静态资源也会被拦截
*
* @EnableWebMvc：全面接管SpringMvc
*
* */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")    //所有请求都会被拦截
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**","/sql");   //放行请求
    }
}
