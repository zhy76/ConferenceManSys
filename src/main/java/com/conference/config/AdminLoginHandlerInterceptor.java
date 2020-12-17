package com.conference.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object loginAdminName = request.getSession().getAttribute("loginAdminName");

        if(loginAdminName == null )
        { //没有登录
            request.setAttribute("msg" ,"没有权限，请先登录");
            request.getRequestDispatcher("/admin/adminLogin.html").forward(request,response);
            return false;
        }
        else
        {
            return true;
        }

    }
}
