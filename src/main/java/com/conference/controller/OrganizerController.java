package com.conference.controller;

import com.conference.entity.Organizer;
import com.conference.service.OrganizerService;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import com.conference.util.vaild.OrganizerLogin;
import com.conference.util.vaild.OrganizerRegister;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @author 左海余
 * @description
 * @date 2020/12/16 21:10
 * @stuid 6109118041
 */
@RestController
@RequestMapping("/organizer")
public class OrganizerController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OrganizerService organizerService;

    /**
     * 组织者注册 Api
     * /organizer/register
     */
    @PostMapping("/register")
    public Result register(@Validated({OrganizerRegister.class}) @RequestBody Organizer organizer) {
//        if (driver.getDriverPhone() == null || driver.getDriverPass() == null) {
//            return new Result(ResultCode.IllegalArgumentException);
//        }
        int addNumber = organizerService.addOrganizer(organizer);
        if (addNumber > 0) {
            String token = tokenService.getToken(addNumber);
            return Result.success("token", token);
        } else {
            return new Result(ResultCode.FAIL);
        }
    }

    /**
     * 组织者登录 Api
     * /organizer/login
     */
    @RequestMapping("/login")
    public Result login(@Validated({OrganizerLogin.class}) @RequestBody Organizer organizer) {
        Organizer organizerForBase = organizerService.findOrganizerByPhone(organizer.getOrganizerPhone());
        if (organizerForBase == null) {
            return new Result(ResultCode.UnknownAccountException);
        } else {
            if (!organizerForBase.getOrganizerPass().equals(organizer.getOrganizerPass())) {
                return new Result(ResultCode.IncorrectCredentialsException);
            } else {
                String token = tokenService.getToken(organizerForBase);
                return Result.success("token", token);
            }
        }
    }

    /**
     * 组织者登出 Api
     * @TODO 登出
     * /organizer/logout
     * @return
     */
    @PostMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success();
    }


    /**
     * 组织者自己修改自己的信息 Api
     * /organizer/updateOrganizer
     */
    @RequestMapping("/updateOrganizer")
    public Result updateDriver(@Validated({OrganizerRegister.class}) @RequestBody Organizer organizer, HttpServletRequest request) {
        System.out.println("POST------------------------------------------------");
        System.out.println(request.getHeader("token"));
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        organizer.setOrganizerId((Integer) claims.get("organizerId"));
        organizerService.updateOrganizer(organizer);
        return Result.success();
    }


    /**
     * 查找登入组织者的所有信息
     * /organizer/getOrganizerInfo
     * @param request
     * @return
     */
    @GetMapping("/getOrganizerInfo")
    public Result getOrganizerInfo(HttpServletRequest request) {
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        Organizer getOrganizerInfo = organizerService.findOrganizerById((Integer) claims.get("organizerId"));
        return Result.success("getOrganizerInfo", getOrganizerInfo);
    }
}