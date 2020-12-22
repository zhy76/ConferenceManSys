package com.conference.interceptor;

import com.conference.entity.Fleet;
import com.conference.service.TokenService;
import com.conference.service.impl.TokenServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/22 22:51
 **/
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录检查逻辑,ajax请求头添加携带token操作！
        String token = request.getHeader("token");
        System.out.println(token);
        token = token == null ? "" : token ;
        Claims claims = tokenService.parseToken(token);
        Date preDate = (Date)claims.get("timeExpiration");
        Date curDate = new Date();
        Long expire = (curDate.getTime() - preDate.getTime())/1000 - TokenServiceImpl.expiration;//计算token的剩余存活时间
        if( expire > 0 ) //如果还存活，说明是登录状态就直接放行
        {
            //放行
            return true;
        }
        //否则，就请求转发到登录页面
        request.getRequestDispatcher("/登录New.html").forward(request,response);
        return false;
    }
}
