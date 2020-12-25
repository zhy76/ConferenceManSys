package com.conference.config;

//import com.example.bbs.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
//    absoluteImgPath: D://conference/
//    sonImgPath: /headphoto/
    @Value("${absoluteImgPath}")
    String absoluteImgPath;

    @Value("${sonImgPath}")
    String sonImgPath;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
//    }

//    @Bean
//    AuthenticationInterceptor authenticationInterceptor(){
//        return new AuthenticationInterceptor();
//    }

//    absoluteImgPath = D://bbsImage/
//    sonImgPath = /images/

    /**
     * file:D://conference//headphoto/
     * file:D://headphoto//headphoto/ca96385e-fd58-40b3-b2ef-b27fac757235.jpg
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(sonImgPath + "**").addResourceLocations("file:"+absoluteImgPath);
//        System.out.println(registry);
    }
}
