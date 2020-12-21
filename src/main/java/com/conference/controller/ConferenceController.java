package com.conference.controller;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 14:04
 * @stuid 6109118041
 */

import com.conference.entity.Conference;
import com.conference.entity.Driver;
import com.conference.entity.Organizer;
import com.conference.service.ConferenceService;
import com.conference.util.result.Result;
import com.conference.util.vaild.DriverRegister;
import com.conference.util.vaild.OrganizerRegister;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conference")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @GetMapping("/showConferences")
    public Result showConferences(){
        List<Conference> conferencesList = conferenceService.queryConferences();
        return Result.success("conferencesList",conferencesList);
    }

    @GetMapping("/showConferenceById")
    public Result showConference(@RequestParam Integer conferenceId){
        Conference conference = conferenceService.queryConferenceByConferenceId(conferenceId);
        System.out.println(conference);
        return Result.success("conference",conference);
    }

//    /**
//     * 修改对应会议信息
//     * @param
//     * @return
//     */
//    @RequestMapping("/updateConference/{conferenceId}")
//    @ResponseBody
//    public Result updateConference(@PathVariable("conferenceId") Integer conferenceId){
//        conferenceService.updateConference(conference);
//        return Result.success();
//    }

    /**
     * 删除指定会议
     * @param conferenceId
     * @return
     */
    @GetMapping("/deleteConference/{conferenceId}")
    public Result deleteConference(@PathVariable("conferenceId") Integer conferenceId) {
        conferenceService.deleteConference(conferenceId);
//        if (driverNum < 1) return new Result(ResultCode.FAIL);
        return Result.success();
    }

    /**
     * 生成会议
     * @param
     * @return
     */
    @PostMapping("/addConference")
    public Result addConference(@Validated({OrganizerRegister.class}) @RequestBody Conference conference) {
        conferenceService.addConference(conference);
//        if (driverNum < 1) return new Result(ResultCode.FAIL);
        return Result.success();
    }

    /**
     * 修改会议
     * @param conference
     * @return
     */
    @PostMapping("/updateConference")
    public Result updateConference(@Validated({OrganizerRegister.class}) @RequestBody Conference conference) {
        conferenceService.updateConference(conference);
        return Result.success();
    }

    /**
     * 通过车队id查会议
     */
    @GetMapping("/queryConferenceByFleetId")
    public Result queryConferenceByFleetId(@RequestParam Integer fleetId) {
        Conference conference = conferenceService.queryConferenceByFleetId(fleetId);
        return Result.success("conference", conference);
    }
}