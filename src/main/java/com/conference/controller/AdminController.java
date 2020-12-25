package com.conference.controller;

import com.conference.entity.Admin;
import com.conference.service.AdminService;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//import org.thymeleaf.util.StringUtils;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/5 11:57
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private TokenService tokenService;
    //处理管理员登录请求
    @PostMapping("/login")
    public Result adminLogin(@RequestBody Admin loginAdmin){
        //获取ajax中JSON传参的管理员账号和密码
        System.out.println(loginAdmin);
        String adminAccount = loginAdmin.getAdminAccount();
        String adminPass = loginAdmin.getAdminPass();
        System.out.println(adminAccount + adminPass);
        if (loginAdmin.getAdminAccount() == null || loginAdmin.getAdminPass() == null) {
            return new Result(ResultCode.IllegalArgumentException);
        }
        Admin adminForBase = adminService.queryAdminByAccountAndPass(adminAccount,adminPass);
        System.out.println(adminForBase);
        if(adminForBase == null){
            System.out.println("账号不存在");
            return new Result(ResultCode.UnknownAccountException);
        }else {
            if (!adminForBase.getAdminPass().equals(loginAdmin.getAdminPass())){
                return new Result(ResultCode.IncorrectCredentialsException);
            }else {
                String token = tokenService.getToken(adminForBase);
                return Result.success("token", token);
            }
        }
    }

    //进入管理员个人中心
    @PostMapping("/showAdminPersonal")
    public Result showAdminPersonal(HttpServletRequest request){
        //根据登录时的账号和密码来返回完整的管理员信息
        String token = request.getHeader("conNCU");
        Claims claims = tokenService.parseToken(token);
        Integer adminId = (Integer) claims.get("adminId");
        Admin admin = adminService.queryAdminByAdminId(adminId);
        return Result.success(admin);
    }

    //处理管理员修改个人信息请求
    @PostMapping("/adminUpdate")
    public Result adminUpdate(Admin admin){
        System.out.println(admin);
        adminService.updateAdmin(admin);
        return Result.success();
    }
}
