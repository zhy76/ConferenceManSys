package com.conference.controller;

import com.conference.entity.Admin;
import com.conference.service.AdminService;
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
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    //管理员端登录入口
    @RequestMapping("/admin")
    public String admin(){
        return "admin/adminLogin";
    }

    //处理管理员登录请求
    @PostMapping("/admin/adminLogin")
    @ResponseBody
    public Map<String,Object> adminLogin(@RequestBody Admin LoginAdmin,HttpSession session){
        //获取ajax中JSON传参的管理员账号和密码
        String adminAccount = LoginAdmin.getAdminAccount();
        String adminPass = LoginAdmin.getAdminPass();
        System.out.println(adminAccount + adminPass);
        Map<String,Object> map = new HashMap<>();
        if( !StringUtils.isEmpty(adminAccount) && !StringUtils.isEmpty(adminPass) && adminService.login(adminAccount,adminPass) != null )
        {//登录成功
            Admin admin = adminService.login(adminAccount,adminPass);
            session.setAttribute("loginAdminName", admin.getAdminName());
            session.setAttribute("admin",admin);
//            return "redirect:/管理员主界面.html";
            map.put("msg","success");
//            return "success";
        }
        else
        {
            //告诉管理员，登录失败!
            map.put("msg","账号或密码错误! ");
//            return "admin/adminLogin";
        }
        return map;
    }

    //进入管理员个人中心
    @GetMapping("/admin/adminPersonal")
    public String adminPersional(){
        return "admin/adminPersonal";
    }

    //处理管理员修改个人信息请求
    @PostMapping("/admin/adminUpdate")
    @ResponseBody
    public String adminUpdate(Admin admin, HttpSession session){
        session.setAttribute("loginAdminName", admin.getAdminName());
        session.setAttribute("admin",admin);
        adminService.updateAdmin(admin);
        return "success";
    }

}
