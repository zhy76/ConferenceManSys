package com.conference.controller;

import com.conference.entity.Admin;
import com.conference.service.AdminService;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
//import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    public Result showAdminPersonal(@PathVariable String adminAccount,@PathVariable String adminPass){
        //根据登录时的账号和密码来返回完整的管理员信息
        Admin admin = adminService.queryAdminByAccountAndPass(adminAccount,adminPass);
        return Result.success(admin);
    }

    //处理管理员修改个人信息请求
    @PostMapping("/adminUpdate")
    public Result adminUpdate(Admin admin, HttpSession session){

        adminService.updateAdmin(admin);
        return Result.success();
    }
}
