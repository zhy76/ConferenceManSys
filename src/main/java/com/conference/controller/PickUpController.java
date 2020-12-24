package com.conference.controller;

import com.conference.entity.Conference;
import com.conference.entity.JoinConference;
import com.conference.entity.PickUp;
import com.conference.service.*;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static java.lang.Math.abs;

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
    @Autowired
    private JoinConferenceService joinConferenceService;
    @Autowired
    private ConferenceService conferenceService;

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
    public Result getAllPickUp() {
        List<PickUp> getAllPickUp = pickUpService.findAllPickUp();
        return Result.success("getAllPickUp", getAllPickUp);
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
        System.out.println(getParticipantAllPickUp);
        return Result.success("getParticipantAllPickUp", getParticipantAllPickUp);
    }

    /**
     * 通过id司机查找自己待接送的接送记录 Api
     * /pickUp/getDriverWaitPickUp
     *
     * @param driverId Integer
     * @return result{}
     */
    @GetMapping("/getDriverWaitPickUp")
    public Result getDriverWaitPickUp(@RequestParam("driverId") Integer driverId) {
        List<PickUp> getDriverAllPickUp = pickUpService.findAllDriverPickUp(driverId);
        List<PickUp> getDriverWaitPickUp = new ArrayList<PickUp>();
        for (PickUp it : getDriverAllPickUp ) {
            if (it.isFinishPickup() == false && it.getReturnTime() != null) {
                getDriverWaitPickUp.add(it);
            }
        }
        return Result.success("getDriverWaitPickUp", getDriverWaitPickUp);
    }

    /**
     * 通过id司机查找自己已完成接送的接送记录 Api
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
            if (it.isFinishPickup() && it.getReturnTime() != null)
                getDriverDonePickUp.add(it);
        System.out.println(getDriverAllPickUp);
        return Result.success("getDriverWaitPickUp", getDriverDonePickUp);
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
        List<PickUp> getDriverAllPickUpNew = new ArrayList<PickUp>();
        for (PickUp it : getDriverAllPickUp)
            if (it.getReturnTime() != null)
                getDriverAllPickUpNew.add(it);
        return Result.success("getDriverAllPickUp", getDriverAllPickUpNew);
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

    /**
     * 车队接送记录 Api
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
     * 车队待完成接送记录 Api
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
     * 车队已完成接送记录 Api
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
            if (it.isFinishPickup() && it.getReturnTime() != null)
                getAllFleetDonePickUp.add(it);
        return Result.success("getAllFleetDonePickUp", getAllFleetDonePickUp);
    }

    /**
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
     * 将会议表内的所有需要接送的记录加入接送表
     */
    @GetMapping("/addAllNeedToPickUp")
    public Result addAllNeedToPickUp(@RequestParam("conferenceId") Integer conferenceId) {
        List<JoinConference> joinConferences = joinConferenceService.queryJoinConferenceByConferenceId(conferenceId);
        List<JoinConference> joinConference = new ArrayList<>();
        Conference conference = conferenceService.queryConferenceByConferenceId(conferenceId);
        for (JoinConference it : joinConferences) {
            if (it.getPickup()) {
                joinConference.add(it);
            }
        }
        List<PickUp> allPickUp = pickUpService.findAllFleetPickUp(conference.getFleetId());
//        System.out.println(allPickUp);
        int addNumber = 0;

        for (JoinConference it : joinConference) {
            int con = 0;
            int flag = 0;
            for (PickUp it1 : allPickUp)
                if (it1.getParticipantId() == it.getParticipantId()) flag = 1;
            if (flag == 1) {
                con++;
                continue;
            }
            PickUp pickUp = new PickUp();
            pickUp.setConferenceId(it.getConferenceId());
            pickUp.setParticipantId(it.getParticipantId());
            pickUp.setToTime(it.getToTime());
            pickUp.setReturnTime(it.getReturnTime());
            pickUp.setFleetId(conference.getFleetId());
            pickUp.setTrainNumber(it.getTrainNumber());
//            pickUp.setDriverId(driverId[con++]);
            pickUp.setDriverId(-1);// 未分配
            System.out.println(pickUp);
            addNumber += pickUpService.addPickUp(pickUp);
        }
        return Result.success("addAllNeedToPickUp", addNumber);
    }

    /**
     * 给车队用的
     *
     * @param conferenceId
     * @param driverId
     * @param participantId
     * @return
     */
    @GetMapping("/addOneNeedToPickUp")
    public Result addOneNeedToPickUp(@RequestParam("conferenceId") Integer conferenceId,
                                     @RequestParam("driverId") Integer driverId,
                                     @RequestParam("participantId") Integer participantId
    ) {
        JoinConference joinConference = joinConferenceService.queryJoinedConferenceByParticipantIdAndConferenceId(participantId, conferenceId);
        Conference conference = conferenceService.queryConferenceByConferenceId(conferenceId);
        List<PickUp> allPickUp = pickUpService.findAllFleetPickUp(conference.getFleetId());
        PickUp pickUp = new PickUp();
        pickUp.setConferenceId(joinConference.getConferenceId());
        pickUp.setParticipantId(joinConference.getParticipantId());
        pickUp.setToTime(joinConference.getToTime());
//        pickUp.setReturnTime(joinConference.getReturnTime());
        pickUp.setFleetId(conference.getFleetId());
        pickUp.setTrainNumber(joinConference.getTrainNumber());
        pickUp.setDriverId(driverId);
        for (PickUp it1 : allPickUp){
            if (it1.getParticipantId() == joinConference.getParticipantId() && it1.getConferenceId() == joinConference.getConferenceId()) {
                System.out.println(5);
                System.out.println(conferenceId);
                System.out.println(participantId);
                PickUp pickUpNew = pickUpService.findPickUp(participantId, conferenceId);
                System.out.println(pickUpNew);
//                System.out.println(6);
                if (pickUpNew.getDriverId() == driverId) {
                    System.out.println("----------------");
                    System.out.println(pickUpNew.getDriverId());
                    System.out.println(driverId);
                    return new Result(1, "已存在");
                }
                pickUpNew.setDriverId(driverId);

                System.out.println("-----------------------");
                System.out.println(pickUpNew);
                pickUpService.updatePickUpByConferenceIdAndParticipantId(pickUpNew);
                return new Result(1, "已存在");
            }
            System.out.println(4);
        }

        System.out.println("-----------------------");
        System.out.println(pickUp);
        pickUpService.addPickUp(pickUp);
        return Result.success();
    }

    /**
     * @param conferenceId
     * @param participantId
     * @return
     */
    @GetMapping("/getPickUpByConferenceIdAndParticipantId")
    public Result getPickUpByConferenceIdAndParticipantId(
            @RequestParam("participantId") Integer participantId,@RequestParam("conferenceId") Integer conferenceId) {
        PickUp pickUp = pickUpService.findPickUp(participantId, conferenceId);
        return Result.success("pickUp", pickUp);
    }

    /**
     * 得到司机待确认订单
     * /pickUp/getDriverConfirmPickUp
     * @param driverId
     * @return
     */
    @GetMapping("/getDriverConfirmPickUp")
    public Result getDriverConfirmPickUp(@RequestParam("driverId") Integer driverId) {
        List<PickUp> pickUp = pickUpService.findAllDriverPickUp(driverId);
        List<PickUp> confirmPickUp = new ArrayList<>();
        for (PickUp it : pickUp) {
            if (it.getReturnTime() == null) {
                confirmPickUp.add(it);
            }
        }
        return Result.success("pickUp", confirmPickUp);
    }

    /**
     * 司机确认
     * @param pickUpId
     * @param returnTime
     * @return
     */
    @GetMapping("/updateDriverConfirmPickUp")
    public Result updateDriverConfirmPickUp(@RequestParam("pickUpId") Integer pickUpId,
                                            @RequestParam("driverId") Integer driverId,
                                            @RequestParam("returnTime") String returnTime
    ) {

        /**
         * 接送时间 >= 到达时间
         * 接送时间 +30, -30,
         */

        PickUp pickUp = pickUpService.findPickUpById(pickUpId);
        List<PickUp> pickUpList = pickUpService.findAllDriverPickUp(driverId);
        // 2020-10-10 14:14:00
        //
        if (pickUp.getToTime().compareTo(returnTime) < 0) {
            return new Result(1, "接送时间不应小于到达时间");
        }
        if (!pickUp.getToTime().substring(0, 10).equals(returnTime.substring(0,10))) {
            return new Result(1, "接送时间与到达时间间隔过大");
        }
        for (PickUp it : pickUpList) {
            if (it.getReturnTime() == null || !pickUp.getToTime().substring(0, 10).equals(returnTime.substring(0,10))) {
                continue;
            }
            if (it.isFinishPickup() == true) {
                continue;
            }
            String confirmPickUpTime = returnTime.substring(10, 15);
            String pickUpTime = it.getReturnTime().substring(10, 15);
            int confirmPickUpTimeInt = Integer.valueOf(confirmPickUpTime.substring(0, 2)) * 60 + Integer.valueOf(confirmPickUpTime.substring(3, 5));
            int pickUpTimeInt = Integer.valueOf(pickUpTime.substring(0, 2)) * 60 + Integer.valueOf(pickUpTime.substring(3, 5));
            if (abs(confirmPickUpTimeInt - pickUpTimeInt) <= 30) {
                return new Result(1, "两次接送订单时间间隔应大于半个小时");
            }

        }

        pickUp.setReturnTime(returnTime);
        System.out.println(pickUp);
        pickUpService.updatePickUp(pickUp);
        return Result.success();
    }
}
