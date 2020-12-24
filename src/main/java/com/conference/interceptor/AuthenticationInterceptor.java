package com.conference.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.conference.service.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;


public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        boolean isUser=request.getRequestURI().substring(0,6).matches("/User/.*");
        boolean isWebMaster=request.getRequestURI().matches("/webMaster/.*");
        boolean isAdmin=request.getRequestURI().matches("/admin/.*");
        if(request.getRequestURI().equals("/admin/adminLogin")||request.getRequestURI().equals("/User/login")
                ||request.getRequestURI().equals("/User/register"))
            return true;
        if(isUser||isAdmin||isWebMaster){
            if(token == null){
                throw new RuntimeException("无token，请重新登录");
            }
            Claims claims;
            try {
                claims = tokenService.parseToken(token);
            }catch (Exception e){
                throw new RuntimeException("401");
            }


            Date timeExpiration = new Date((long)claims.get("timeExpiration"));
            if( new Date().after(timeExpiration)){
                throw new RuntimeException("token已过期");
            }

            int type = (int) claims.get("type");
            if(isUser){
                if(type >= 0){
                    return true;
                }else {
                    throw new RuntimeException("权限不够");
                }

            }else if(isWebMaster){
                if(type >= 1){
                    return true;
                }else {
                    throw new RuntimeException("权限不够");
                }
            }else if(isAdmin){
                if(type >= 2){
                    return true;
                }else {
                    throw new RuntimeException("权限不够");
                }

            }
        }

        return true;
    }


}
