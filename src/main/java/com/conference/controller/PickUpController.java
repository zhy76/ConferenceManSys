package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.entity.PickUp;
import com.conference.service.DriverService;
import com.conference.service.FleetService;
import com.conference.service.PickUpService;
import com.conference.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PickUpController
 * @Description: That's enough.
 * @Author: Lance
 * @Date: 2020/12/5 21:27
 */
@RestController
@RequestMapping("/pickUp")
public class PickUpController {


    @Autowired
    private TokenService tokenService;
    @Autowired
    private FleetService fleetService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private PickUpService pickUpService;

    // 确认完成接送Complete pickup
    @RequestMapping("/completePickUp")
    public Object completePickUp(@RequestParam("pickUpId")Integer pickUpId) {
        JSONObject result=new JSONObject();
        PickUp pickUp = pickUpService.findPickUpById(pickUpId);
        pickUp.setFinishPickup(true);
        pickUpService.updatePickUp(pickUp);
        result.put("state",1);
        return result.toJSONString();
    }



//    public Object completePickUp(@RequestParam("participantId")Integer participantId,
//                                 @RequestParam("driverId")Integer driverId,
//                                 @RequestParam("conferenceId")Integer conferenceId) {
//        JSONObject result=new JSONObject();
//        PickUp pickUp = pickUpService.findPickUp(participantId, driverId, conferenceId);
//        pickUp.setFinishPickup(true);
//        pickUpService.updatePickUp(pickUp);
//        result.put("state",1);
//        return result.toJSONString();
//    }
}
