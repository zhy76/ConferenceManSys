package com.conference.controller;

import com.conference.entity.PickUp;
import com.conference.service.DriverService;
import com.conference.service.FleetService;
import com.conference.service.PickUpService;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
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
     * 确认完成接送 Api
     * /pickUp/completePickUp
     *
     * @param pickUpId Integer
     * @return result {
     * <p>
     * }
     */
    @GetMapping("/completePickUp")
    public Result completePickUp(@RequestParam("pickUpId") Integer pickUpId) {
        PickUp pickUp = pickUpService.findPickUpById(pickUpId);
        pickUp.setFinishPickup(true);
        pickUpService.updatePickUp(pickUp);
        return Result.success();
    }

    /**
     * 通过id删除接送 Api
     * /pickUp/deletePickUp
     *
     * @param pickUpId Integer
     * @return result{}
     */
    @RequestMapping("/deletePickUp")
    public Result deletePickUp(@RequestParam("pickUpId") Integer pickUpId) {
        int deleteNum = pickUpService.deletePickUpId(pickUpId);
        if (deleteNum == 0) return new Result(ResultCode.FAIL);
        return Result.success();
    }

    /**
     * 添加接送 Api
     * /pickUp/addPickUp
     *
     * @param pickUp {}
     * @return result{}
     */
    @PostMapping("/addPickUp")
    public Result addPickUp(@Valid @RequestBody PickUp pickUp) {
        int addNumber = pickUpService.addPickUp(pickUp);
        if (addNumber == 0) return new Result(ResultCode.FAIL);
        return Result.success();
    }

    /**
     * 查找所有的接送 Api
     * /pickUp/getAllPickUp
     *
     * @return result{}
     */
    @GetMapping("/getAllPickUp")
    public List<PickUp> getAllPickUp() {
        return pickUpService.findAllPickUp();
    }

    /**
     * 乘客通过id查找所有的接送记录 Api
     * /pickUp/getParticipantAllPickUp
     *
     * @param participantId Integer
     * @return result{}
     */
    @GetMapping("/getParticipantAllPickUp")
    public Result getParticipantAllPickUp(@RequestParam("participantId") Integer participantId) {
        List<PickUp> getParticipantAllPickUp = pickUpService.findAllParticipantPickUp(participantId);
        return Result.success("getParticipantAllPickUp", getParticipantAllPickUp);
    }

    /**
     * 通过id司机查找自己所有的接送记录 Api
     * /pickUp/getDriverAllPickUp
     *
     * @param driverId Integer
     * @return result{}
     */
    @GetMapping("/getDriverAllPickUp")
    public Result getDriverAllPickUp(@RequestParam("driverId") Integer driverId) {
        List<PickUp> getDriverAllPickUp = pickUpService.findAllDriverPickUp(driverId);
        return Result.success("getDriverAllPickUp", getDriverAllPickUp);
    }


    /**
     * 会议接送记录 Api
     * /pickUp/getAllConferencePickUp
     *
     * @param conferenceId Integer
     * @return result{}
     */
    @GetMapping("/getAllConferencePickUp")
    public Result getAllConferencePickUp(@RequestParam("conferenceId") Integer conferenceId) {
        List<PickUp> getAllConferencePickUp = pickUpService.findAllConferencePickUp(conferenceId);
        return Result.success("getAllConferencePickUp", getAllConferencePickUp);
    }
}
