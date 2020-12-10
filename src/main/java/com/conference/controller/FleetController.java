package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.entity.Driver;
import com.conference.entity.Fleet;
import com.conference.dao.FleetDao;
import com.conference.service.DriverService;
import com.conference.service.FleetService;
import com.conference.service.PickUpService;
import com.conference.service.TokenService;
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
     * /fleet/register
     * @param fleet
     * @return
     */
    @PostMapping("/register")
    public Object register(@Valid @RequestBody Fleet fleet) {
        JSONObject jsonObject = new JSONObject();
        if (fleet.getFleetPhone() == null || fleet.getFleetPass() == null) {
            jsonObject.put("message", "表单错误");
            return jsonObject;
        }
        int addNumber = fleetService.addFleet(fleet);
        if (addNumber > 0) {
            String token = tokenService.getToken(addNumber);
            jsonObject.put("token", token);
            return jsonObject;
        } else {
            jsonObject.put("message", "注册失败");
            return jsonObject;
        }
    }

    /**
     * /fleet/login
     * @param fleet
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody Fleet fleet) {
        JSONObject jsonObject = new JSONObject();
        if (fleet.getFleetPhone() == null || fleet.getFleetPass() == null) {
//            System.out.println("null");
            jsonObject.put("message", "表单错误");
            return jsonObject;
        }
        Fleet fleetForBase = fleetService.findFleetByPhone(fleet.getFleetPhone());
        System.out.println(fleetForBase);
//        User userForBase =userDao.findByUsername(loginUser.getUserName());
        if (fleetForBase == null) {
            jsonObject.put("message", "登录失败,用户不存在");
            return jsonObject;
        } else {
            if (!fleetForBase.getFleetPass().equals(fleet.getFleetPass())) {
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = tokenService.getToken(fleetForBase);
                jsonObject.put("token", token);
                return jsonObject;
            }
        }
    }
    @GetMapping("/getAllFleet")
    public List<Fleet> getAllDriver() {
        return fleetService.findAllFleet();
    }

    /**
     * http://localhost:8081/fleet/deleteFleet
     * 按照id删除车队
     * @param fleetId
     */
    @GetMapping("/deleteFleet")
    public int deleteFleet(@RequestParam("fleetId")Integer fleetId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "删除成功");
        return fleetService.deleteFleetById(fleetId);
    }

    /**
     * /fleet/updateFleet
     * @param fleet
     * @param request
     * @return
     */
    @PostMapping("/updateFleet")
    public Object updateDriver(@RequestBody Fleet fleet, HttpServletRequest request) {

        JSONObject result=new JSONObject();
//        System.out.println(request.getHeader("token"));
//        Claims claims = tokenService.parseToken(login(fleet).toString());
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        fleet.setFleetId((Integer) claims.get("fleetId"));
        try {
            /**
             * @TODO debug
             */
//            fleetService.updateFleet(fleet.getFleetId());
        } catch (DataAccessException e) {
            throw new RuntimeException("修改失败");
        }
        result.put("state",1);
        return result.toJSONString();
    }
}
