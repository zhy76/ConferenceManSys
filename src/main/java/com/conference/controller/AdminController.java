package com.conference.controller;

import com.conference.entity.Admin;
import com.conference.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

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
    public String adminLogin(@RequestParam("adminAccount") String adminAccount , @RequestParam("adminPass") String adminPass , Model model , HttpSession session){
//        System.out.println(adminAccount + adminPass);

        if( !StringUtils.isEmpty(adminAccount) && !StringUtils.isEmpty(adminPass) && adminService.login(adminAccount,adminPass) != null )
        {//登录成功
            Admin admin = adminService.login(adminAccount,adminPass);
            session.setAttribute("loginAdminName", admin.getAdminName());
            session.setAttribute("admin",admin);
            return "redirect:/admin/adminMain.html";
        }
        else
        {
            //告诉管理员，登录失败!
            model.addAttribute("msg","用户名或密码错误！");
            return "admin/adminLogin";
        }
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
