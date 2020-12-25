package com.conference.controller;

import com.conference.entity.Fleet;
import com.conference.service.DriverService;
import com.conference.service.FleetService;
import com.conference.service.PickUpService;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: FleetController
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/2 20:40
 */
@RestController
@RequestMapping("/fleet")
public class FleetController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private FleetService fleetService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private PickUpService pickUpService;


    /**
     * 车队注册 Api
     * /fleet/register
     *
     * @param fleet {
     *              "fleetId": Integer
     *              "fleetName": String
     *              "fleetPass": String
     *              "fleetPhone": String
     *              }
     * @return result {
     * "status":
     * "message":
     * "data": {}
     * }
     */
    @PostMapping("/register")
    public Result register(@Valid @RequestBody Fleet fleet) {
        int addNumber = fleetService.addFleet(fleet);
        if (addNumber > 0) {
            Fleet fleetByPhone = fleetService.findFleetByPhone(fleet.getFleetPhone());
            String token = tokenService.getToken(fleetByPhone);

            return Result.success("token", token);
        } else {
            return new Result(ResultCode.FAIL);
        }
    }
//    //参加者注册
//    @PostMapping("/register")
//    public Result register(@Valid @RequestBody Participant participant) {
//        int addNumber = participantService.addAParticipant(participant);
//
//        //System.out.println(addNumber);
//        if (addNumber > 0) {
//            Participant newParticipant = participantService.queryParticipantByParticipantPhone(participant.getParticipantPhone());
//            //String token = tokenService.getToken(addNumber);
//            System.out.println(newParticipant);
//            String token = tokenService.getToken(newParticipant);
//            // System.out.println(token);
//            return Result.success("token", token);
//        } else {
//            return new Result(ResultCode.FAIL);
//        }
//    }
    /**
     * 车队登入 Api
     * /fleet/login
     *
     * @param fleet {
     *              "fleetPass": String
     *              "fleetPhone": String
     *              }
     * @return result {
     * "status":
     * "message":
     * "data": {}
     * }
     */
    @PostMapping("/login")
    public Result login(@RequestBody Fleet fleet) {
        Fleet fleetForBase = fleetService.findFleetByPhone(fleet.getFleetPhone());
        System.out.println(fleet);
        System.out.println("111111111111111");
        if (fleetForBase == null) {
            return new Result(ResultCode.UnknownAccountException);
        } else {
            if (!fleetForBase.getFleetPass().equals(fleet.getFleetPass())) {
                System.out.println("error");
                return new Result(ResultCode.IncorrectCredentialsException);
            } else {
                String token = tokenService.getToken(fleetForBase);
                return Result.success("token", token);
            }
        }
    }

    /**
     * 车队登出 Api
     * /fleet/logout
     *
     * @return
     * @TODO 登出
     */
    @PostMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success();
    }

    /**
     * 查找所有车队 Api
     * /fleet/getAllFleet
     *
     * @return result {}
     */
    @GetMapping("/getAllFleet")
    public Result getAllFleet() {
        List<Fleet> getAllFleet = fleetService.findAllFleet();
        return Result.success("getAllFleet", getAllFleet);
    }

    /**
     * 查找所有车队 Api
     * /fleet/getAllFleet
     *
     * @return result {}
     */
    @PostMapping("/getAllFleetByAdmin")
    public Result getAllFleetByAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Fleet> getAllFleet = fleetService.findAllFleet();
        PageInfo<Fleet> pi = new PageInfo<>(getAllFleet);
        return Result.success("getAllFleet", pi.getList());
    }


    /**
     * 按照id删除车队 Api
     * /fleet/deleteFleet
     *
     * @param fleetId Integer
     * @return result {}
     */
    @GetMapping("/deleteFleet")
    public Result deleteFleet(@RequestParam("fleetId") Integer fleetId) {
        System.out.println(fleetId);
        fleetService.deleteFleetById(fleetId);
        return Result.success();
    }


    /**
     * 查找登入车队的所有信息
     * /fleet/getFleetInfo
     *
     * @param request
     * @return {}
     */
    @GetMapping("/getFleetInfo")
    public Result getFleetInfo(HttpServletRequest request) {
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        Fleet getFleetInfo = fleetService.findFleetById((Integer) claims.get("fleetId"));
        return Result.success("getFleetInfo", getFleetInfo);
    }


    /**
     * 车队自己改信息 Api
     * /fleet/updateFleet
     *
     * @param fleet   {}
     * @param request
     * @return result {}
     */
    @PostMapping("/updateFleet")
    public Result updateDriver(@RequestBody Fleet fleet, HttpServletRequest request) {
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        fleet.setFleetId((Integer) claims.get("fleetId"));
        fleetService.updateFleet(fleet);
        return Result.success();
    }

    /**
     *
     * @param fleetId
     * @return
     */
    @GetMapping("/findFleetById")
    public Result findFleetById(@RequestParam int fleetId){
        Fleet findFleetById =fleetService.findFleetById(fleetId);
        return Result.success("findFleetById", findFleetById);
    }

    /**
     * 管理员修改车队信息
     */
    @PostMapping("/updateFleetByAdmin")
    public Result updateFleetByAdmin(Fleet fleet) {
        fleetService.updateFleet(fleet);
        return Result.success();
    }
}
