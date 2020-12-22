package com.conference.controller;

/**
 * @author 左海余 刘涔宇
 * @description
 * @date 2020/12/11 14:04   2020/12/21 16:58
 * @stuid 6109118041
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.conference.entity.Conference;
import com.conference.entity.Fleet;
import com.conference.entity.Hotel;
import com.conference.entity.Organizer;
import com.conference.service.ConferenceService;
import com.conference.service.FleetService;
import com.conference.service.HotelService;
import com.conference.service.OrganizerService;
import com.conference.util.result.Result;
import com.conference.util.vaild.OrganizerRegister;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/conference")
public class ConferenceController {
    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private OrganizerService organizerService;

    @Autowired
    private FleetService fleetService;

    @Autowired
    private HotelService hotelService;

    @PostMapping("/showConferences")
    public Result showConferences(){
        List<Conference> conferencesList = conferenceService.queryConferences();
        return Result.success("conferencesList",conferencesList);
    }

    @PostMapping("/showConferenceById")
    public Result showConference(@RequestParam Integer conferenceId){
        Conference conference = conferenceService.queryConferenceByConferenceId(conferenceId);
        return Result.success("conference",conference);
    }

//    /**
//     * 通过车队id查会议
//     */
//    @GetMapping("/queryConferenceByFleetId")
//    public Result queryConferenceByFleetId(@RequestParam Integer fleetId) {
//        List<Conference> conference = conferenceService.queryConferenceByFleetId(fleetId);
//        return Result.success("conference", conference);
//    }


    /**
     * 管理员模块 by 刘涔宇
     */
    //管理员展示所有会议
    @PostMapping("/showConferencesByAdmin")
    public Result showConferencesByAdmin(){

        List<Conference> conferencesList = conferenceService.queryConferences();
        List <JSONObject> trueConferencesList = new LinkedList<>();
        for( Conference conference :  conferencesList)
        { //遍历每个会议，查询出各个ID对应的name属性
            Organizer organizer = organizerService.findOrganizerById(conference.getOrganizerId());
            Fleet fleet = fleetService.findFleetById(conference.getFleetId());
            Hotel hotel = hotelService.getHotelById(conference.getHotelId());
            JSONObject object = new JSONObject();
            object.put("conferenceId",conference.getConferenceId());
            object.put("conferenceName",conference.getConferenceName());

            object.put("organizerId",conference.getOrganizerId());
            object.put("organizerUnit",organizer.getOrganizerUnit());

            object.put("conferenceStart",conference.getConferenceStart());
            object.put("conferenceEnd",conference.getConferenceEnd());
            object.put("conferenceLocation",conference.getConferenceLocation());

            object.put("hotelId",conference.getHotelId());
            object.put("hotelName",hotel.getHotelName());

            object.put("fleetId",conference.getFleetId());
            object.put("fleetName",fleet.getFleetName());

            object.put("conferenceInfo",conference.getConferenceInfo());
            trueConferencesList.add(object);
        }
        return Result.success("trueConferencesList",trueConferencesList);
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
}
