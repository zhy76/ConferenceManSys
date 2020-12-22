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
import java.util.*;

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
     * ȷ����ɽ��� Api
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
     * ͨ��idɾ������ Api
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
     * ��ӽ��� Api
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
     * �������еĽ��� Api
     * /pickUp/getAllPickUp
     *
     * @return result{}
     */
    @GetMapping("/getAllPickUp")
    public Result getAllPickUp() {
        List<PickUp> getAllPickUp = pickUpService.findAllPickUp();
        return Result.success("getAllPickUp", getAllPickUp);
    }

    /**
     * �˿�ͨ��id�������еĽ��ͼ�¼ Api
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
     * ͨ��id˾�������Լ������͵Ľ��ͼ�¼ Api
     * /pickUp/getDriverWaitPickUp
     *
     * @param driverId Integer
     * @return result{}
     */
    @GetMapping("/getDriverWaitPickUp")
    public Result getDriverWaitPickUp(@RequestParam("driverId") Integer driverId) {
        List<PickUp> getDriverAllPickUp = pickUpService.findAllDriverPickUp(driverId);
        List<PickUp> getDriverWaitPickUp = new ArrayList<PickUp>();
        for (PickUp it : getDriverAllPickUp) {
            if (it.isFinishPickup() == false) {
                getDriverWaitPickUp.add(it);
            }
        }
        return Result.success("getDriverWaitPickUp", getDriverWaitPickUp);
    }
    /**
     * ͨ��id˾�������Լ�����ɽ��͵Ľ��ͼ�¼ Api
     * /pickUp/getDriverDonePickUp
     *
     * @param driverId Integer
     * @return result{}
     */
    @GetMapping("/getDriverDonePickUp")
    public Result getDriverDonePickUp(@RequestParam("driverId") Integer driverId) {
        List<PickUp> getDriverAllPickUp = pickUpService.findAllDriverPickUp(driverId);
        List<PickUp> getDriverDonePickUp = new ArrayList<PickUp>();
        for (PickUp it : getDriverAllPickUp)
            if (it.isFinishPickup())
                getDriverDonePickUp.add(it);
        return Result.success("getDriverWaitPickUp", getDriverDonePickUp);
    }
    /**
     * ͨ��id˾�������Լ����еĽ��ͼ�¼ Api
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
     * ������ͼ�¼ Api
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

    /**
     * ���ӽ��ͼ�¼ Api
     * /pickUp/getAllFleetPickUp
     *
     * @param fleetId Integer
     * @return result{}
     */
    @GetMapping("/getAllFleetPickUp")
    public Result getAllFleetPickUp(@RequestParam("fleetId") Integer fleetId) {
        List<PickUp> getAllFleetPickUp = pickUpService.findAllFleetPickUp(fleetId);
        return Result.success("getAllFleetPickUp", getAllFleetPickUp);
    }

    /**
     * ���Ӵ���ɽ��ͼ�¼ Api
     * /pickUp/getAllFleetWaitPickUp
     *
     * @param fleetId Integer
     * @return result{}
     */
    @GetMapping("/getAllFleetWaitPickUp")
    public Result getAllFleetWaitPickUp(@RequestParam("fleetId") Integer fleetId) {
        List<PickUp> getAllFleetPickUp = pickUpService.findAllFleetPickUp(fleetId);
        List<PickUp> getAllFleetWaitPickUp = new ArrayList<PickUp>();
        for (PickUp it : getAllFleetPickUp)
            if (!it.isFinishPickup())
                getAllFleetWaitPickUp.add(it);
        return Result.success("getAllFleetWaitPickUp", getAllFleetWaitPickUp);
    }

    /**
     * ��������ɽ��ͼ�¼ Api
     * /pickUp/getAllFleetDonePickUp
     *
     * @param fleetId Integer
     * @return result{}
     */
    @GetMapping("/getAllFleetDonePickUp")
    public Result getAllFleetDonePickUp(@RequestParam("fleetId") Integer fleetId) {
        List<PickUp> getAllFleetPickUp = pickUpService.findAllFleetPickUp(fleetId);
        List<PickUp> getAllFleetDonePickUp = new ArrayList<>();
        for (PickUp it : getAllFleetPickUp)
            if (it.isFinishPickup())
                getAllFleetDonePickUp.add(it);
        return Result.success("getAllFleetDonePickUp", getAllFleetDonePickUp);
    }

    /**
     *
     * @return
     */
    @GetMapping("/updatePickUp")
    public Result updatePickUp(@RequestParam("pickUpId") Integer pickUpId,
                               @RequestParam("driverId") Integer driverId,
                               @RequestParam("fleetId") Integer fleetId) {
        PickUp pickUp = pickUpService.findPickUpById(pickUpId);
        pickUp.setDriverId(driverId);
        pickUp.setFleetId(fleetId);
        pickUpService.updatePickUp(pickUp);
        return Result.success();
    }

    /**
     *
     * @return
     */
    @GetMapping("/getPickUpByconferenceIdAndparticipantId")
    public Result getPickUpByconferenceIdAndparticipantId() {


        return Result.success();
    }
}