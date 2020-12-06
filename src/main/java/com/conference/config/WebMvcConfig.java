package com.conference.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/5 23:31
 * @sno 6109118015
 */

public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //当访问localhost:8080/的时候，会自动跳转到templates下的conference页面
//        registry.addViewController("/").setViewName("conference");
//        registry.addViewController("/conference.html").setViewName("conference");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
//        registry.addViewController("/admin/adminMain.html").setViewName("admin/adminMain");
//        registry.addViewController("/admin/adminLogin.html").setViewName("admin/adminLogin");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
}
