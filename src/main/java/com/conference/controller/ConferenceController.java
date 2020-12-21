package com.conference.controller;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 14:04
 * @stuid 6109118041
 */

import com.conference.entity.Conference;
import com.conference.entity.Driver;
import com.conference.entity.Hotel;
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

    /**
     * 查询所有会议
     * @return
     */
    @RequestMapping("/showConferences")
    @ResponseBody
    public Result showConferences(){
        List<Conference> conferencesList = conferenceService.queryConferences();
        return Result.success("conferencesList",conferencesList);
    }

    /**
     * 查询指定id会议
     * @param conferenceId
     * @return
     */
    @RequestMapping("/showConference/{conferenceId}")
    @ResponseBody
    public Conference showConference(@PathVariable Integer conferenceId){
        Conference conference = conferenceService.queryConferenceByConferenceId(conferenceId);
        return conference;
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
    @GetMapping("/deleteConference")
    public Result deleteConference(@RequestParam Integer conferenceId) {
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

    @GetMapping("/queryConferenceByOrganizerId")
    public Result queryConferenceByOrganizerId(@RequestParam int OrganizerId) {
        List<Conference> queryConferenceByOrganizerId = conferenceService.queryConferenceByOrganizerId(OrganizerId);
        return Result.success("queryConferenceByOrganizerId", queryConferenceByOrganizerId);
    }
}

