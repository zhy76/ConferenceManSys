package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.entity.Driver;
import com.conference.entity.Fleet;
import com.conference.service.DriverService;
import com.conference.service.FleetService;
import com.conference.service.PickUpService;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import io.jsonwebtoken.Claims;
import org.apache.shiro.dao.DataAccessException;
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
     *
     * @param fleet {
     *              <p>
     *              <p>
     *              }
     * @return result {
     * "status":
     * "message":
     * "data": {}
     */
    @PostMapping("/register")
    public Result register(@Valid @RequestBody Fleet fleet) {
        int addNumber = fleetService.addFleet(fleet);
        if (addNumber > 0) {
            String token = tokenService.getToken(addNumber);
            return Result.success("token", token);
        } else {
            return new Result(ResultCode.FAIL);
        }
    }

    /**
     * @param fleet
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Fleet fleet) {
        Fleet fleetForBase = fleetService.findFleetByPhone(fleet.getFleetPhone());
        System.out.println(fleetForBase);
//        User userForBase =userDao.findByUsername(loginUser.getUserName());
        if (fleetForBase == null) {
            return new Result(ResultCode.UnknownAccountException);
        } else {
            if (!fleetForBase.getFleetPass().equals(fleet.getFleetPass())) {
                return new Result(ResultCode.IncorrectCredentialsException);
            } else {
                String token = tokenService.getToken(fleetForBase);
                return Result.success("token", token);
            }
        }
    }

    /**
     * @return
     */
    @GetMapping("/getAllFleet")
    public Result getAllFleet() {
        List<Fleet> getAllFleet = fleetService.findAllFleet();
        return Result.success("getALLFleet", getAllFleet);
    }


    /**
     * http://localhost:8081/fleet/deleteFleet
     * 按照id删除车队
     *
     * @param fleetId
     * @return
     */
    @GetMapping("/deleteFleet")
    public Result deleteFleet(@RequestParam("fleetId") Integer fleetId) {
        fleetService.deleteFleetById(fleetId);
        return Result.success();
    }

    /**
     * 车队自己改信息
     * /fleet/updateFleet
     *
     * @param fleet
     * @param request
     * @return
     */
    @PostMapping("/updateFleet")
    public Result updateDriver(@RequestBody Fleet fleet, HttpServletRequest request) {

//        System.out.println(request.getHeader("token"));
//        Claims claims = tokenService.parseToken(login(fleet).toString());
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        fleet.setFleetId((Integer) claims.get("fleetId"));
        fleetService.updateFleet(fleet);
        return Result.success();
    }
}
