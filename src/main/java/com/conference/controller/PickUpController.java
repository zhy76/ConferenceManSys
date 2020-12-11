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
import java.util.List;

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

    /**
     * 查找所有的接送
     * /pickUp/getAllPickUp
     * @return
     */
    @GetMapping("/getAllPickUp")
    public List<PickUp> getAllPickUp() {
        return pickUpService.findAllPickUp();
    }

    /**
     * 乘客通过id查找所有的接送记录
     * /pickUp/getParticipantAllPickUp
     * @test done
     * @param participantId
     * @return
     */
    @GetMapping("/getParticipantAllPickUp")
    public List<PickUp> getParticipantAllPickUp(@RequestParam("participantId")Integer participantId){
        return pickUpService.findAllParticipantPickUp(participantId);
    }

    /**
     * 司机自己通过id查找所有的接送记录
     * /pickUp/getDriverAllPickUp
     * @test done
     * @param driverId
     * @return
     */
    @GetMapping("/getDriverAllPickUp")
    public List<PickUp> getDriverAllPickUp(@RequestParam("driverId")Integer driverId) {
        return pickUpService.findAllDriverPickUp(driverId);
    }


    /**
     * 会议接送记录
     * @param conferenceId
     * @return
     */
    @GetMapping("/getDriverAllPickUp")
    public List<PickUp> getAllConferencePickUp(@RequestParam("conferenceId")Integer conferenceId) {
        return pickUpService.findAllConferencePickUp(conferenceId);
    }
}
