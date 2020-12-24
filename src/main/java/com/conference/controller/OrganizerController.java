package com.conference.controller;

import com.conference.entity.Admin;
import com.conference.entity.Organizer;
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

 * @author 左海余 / 刘涔宇
 * @description
 * @date 2020/12/16 21:10   2020/12/20 16:23
 * @stuid 6109118041
 */

@RestController
@RequestMapping("/organizer")
public class OrganizerController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OrganizerService organizerService;

//    /**
//     * 删除司机 Api
//     * /driver/deleteDriver
//     *
//     * @param driverId (the driver id),
//     * @return {
//     * "code": 200,
//     * "message": "成功"
//     * }
//     */
//    @GetMapping("/deleteDriver")
//    public Result deleteDriver(@RequestParam Integer driverId) {
//        int driverNum = driverService.deleteDriverById(driverId);
////        if (driverNum < 1) return new Result(ResultCode.FAIL);
//        return Result.success();
//    }

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
                System.out.println(token);
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


//    /**
//     * 管理员修改司机的信息 Api
//     * /driver/adminUpdateDriver
//     *
//     * @param driver {}
//     * @return result {}
//     */
//    @PostMapping("/adminUpdateDriver")
//    public Result adminUpdateDriver(@Validated({DriverRegister.class}) @RequestBody Driver driver) {
//        driverService.updateDriver(driver);
//        return Result.success();
//    }

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

//    /**
//     * 根据id查找车队所有的司机 Api
//     * /driver/getAllFleetDriver
//     *
//     * @param fleetId
//     * @return result {}
//     */
//    @GetMapping("/getAllFleetDriver")
//    public Result getAllFleetDriver(@RequestParam("fleetId") Integer fleetId) {
//        List<Driver> getAllFleetDriver = driverService.findFleetAllDriver(fleetId);
//        if (fleetId == null)
//            return new Result(ResultCode.IllegalArgumentException);
//        return Result.success("getAllFleetDriver", getAllFleetDriver);
//    }
//
//    /**
//     * 查找所有的司机 Api
//     * /driver/getAllDriver
//     *
//     * @return result {}
//     */
//    @GetMapping("/getAllDriver")
//    public Result getAllDriver() {
//        List<Driver> getAllDriver = driverService.findAllDriver();
//        return new Result(2, "时间冲突");
////        return Result.success("getAllDriver", getAllDriver);
//    }
//
    /**
     * 查找登入组织者的所有信息
     * /organizer/getOrganizerInfo
     * @param
     * @return
     */
    @GetMapping("/getOrganizerInfo")
    public Result getOrganizerInfo(@RequestParam Integer organizerId) {
        Organizer getOrganizerInfo = organizerService.findOrganizerById(organizerId);
        System.out.println(getOrganizerInfo);
        return Result.success("getOrganizerInfo", getOrganizerInfo);
    }

    @GetMapping("/showOrganizerPersonal")
    public Result showOrganizerPersonal(HttpServletRequest request){
        //根据登录时的账号和密码来返回完整的信息
        String token = request.getHeader("token");
        Claims claims = tokenService.parseToken(token);
        Integer organizerId = (Integer) claims.get("organizerId");
        Organizer organizer = organizerService.findOrganizerById(organizerId);
        return Result.success(organizer);
    }
    /**
     * 管理员管理组织者模块
     */

    /**
     * 管理员查看所有组织者信息
     * @return
     */
    @PostMapping("/showOrganizers")
    @ResponseBody
    public Result showOrganizers(){
        List<Organizer> organizersList = organizerService.findAllOrganizer();
        return  Result.success(organizersList);
    }

    /**
     * 管理员根据ID查看组织者信息
     * @param organizerId
     * @return
     */
    @RequestMapping("/queryOrganizerByOrganizerId")
    public Result queryOrganizerByOrganizerId(@RequestParam int organizerId){
        Organizer queryOrganizerByOrganizerId = organizerService.findOrganizerById(organizerId);
        return Result.success("queryOrganizerByOrganizerId",queryOrganizerByOrganizerId);
    }

    //管理员修改组织者信息页面
    //修改组织者信息
    @PostMapping("/updateOrganizerByAdmin")
    public Result postUpdateOrganizer(Organizer organizer){
//        System.out.println(organizer);
        int status = organizerService.updateOrganizer(organizer);
//        System.out.println(status);
        return Result.success();
    }

    //管理员删除某组织者
    @PostMapping("/deleteOrganizerByAdmin/{organizerId}")
    @ResponseBody
    public Result getDeleteOrganizer(@PathVariable("organizerId") Integer organizerId){
        organizerService.deleteOrganizerById(organizerId);
        return Result.success();
    }
}
