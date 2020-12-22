package com.conference.config;

import com.conference.interceptor.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/23 1:05
 **/
public class ConferenceWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") //所有的路径都拦截
                .excludePathPatterns("/登录New","/注册New","/admin/**") ; //除了主页和CSS JS 等非页面文件
    }
}
