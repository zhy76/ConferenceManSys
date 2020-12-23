package com.conference.interceptor;

import com.conference.entity.Fleet;
import com.conference.service.TokenService;
import com.conference.service.impl.TokenServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/22 22:51
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 处理OPTIONS请求(预检请求)，具体看参阅“参考链接>>>http的协议的跨域cors 和 options请求的一些理解”
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Content-Type", "text/html;charset=utf-8");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return false;
        }
        //登录检查逻辑,ajax请求头添加携带token操作！
        String token = request.getHeader("token");
        token = token == null ? "" : token;
        try {
            Claims claims = tokenService.parseToken(token);
            Date preDate = (Date) claims.get("timeExpiration");
            Date curDate = new Date();
            Long expire = (curDate.getTime() - preDate.getTime()) / 1000 - TokenServiceImpl.expiration;//计算token的剩余存活时间
            if (expire > 0) //如果还存活，说明是登录状态就直接放行
               return true;   //放行

        } catch (Exception e) {
            //否则，就请求转发到登录页面
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=UTF-8");
            String url = "/login.html";
            response.sendRedirect(url);
            return false;
        }
        return false;
    }
}
