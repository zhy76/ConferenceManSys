package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.entity.PickUp;
import com.conference.service.DriverService;
import com.conference.service.FleetService;
import com.conference.service.PickUpService;
import com.conference.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    /**
     * 确认完成接送Complete pickup
     * @param pickUpId
     * @return
     */
    @RequestMapping("/completePickUp")
    public Object completePickUp(@RequestParam("pickUpId")Integer pickUpId) {
        JSONObject result=new JSONObject();
        PickUp pickUp = pickUpService.findPickUpById(pickUpId);
        pickUp.setFinishPickup(true);
        pickUpService.updatePickUp(pickUp);
        result.put("state",1);
        return result.toJSONString();
    }

    /**
     * 通过id删除接送
     * /pickUp/deletePickUp
     * @param pickUpId
     * @return
     */
    @RequestMapping("/deletePickUp")
    public int deletePickUp(@RequestParam("pickUpId")Integer pickUpId) {
        return pickUpService.deletePickUpId(pickUpId);
    }

    /**
     * 添加接送
     * /pickUp/addPickUp
     * @param pickUp
     * @return
     */
    @PostMapping("/addPickUp")
    public int addPickUp(@Valid @RequestBody PickUp pickUp) {
        JSONObject result=new JSONObject();

        int addNumber = pickUpService.addPickUp(pickUp);

        return addNumber;
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
