package com.it.boot.config;

import com.it.boot.interceptor.LoginInterceptor;
//import com.it.boot.interceptor.RedisUrlCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
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

    /*
    * Filter,Interceptor  几乎拥有相同的功能？
    * 1.Filter是servlet定义的原生组件。好处：脱离spring应用也能使用
    * 2.Interceptor是Spring定义的接口。可以使用Spring的自动装配等功能
    * */
//    @Autowired
//    RedisUrlCountInterceptor redisUrlCountInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")    //所有请求都会被拦截
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**");   //放行请求

//        registry.addInterceptor(redisUrlCountInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**");
    }
}
