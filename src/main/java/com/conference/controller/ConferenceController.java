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
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.Normalizer;
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
    public Result showConferencesByAdmin(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
//        PageHelper.startPage(pageNum,pageSize); //第1页个数为10
//        List<Conference> conferencesList = conferenceService.queryConferences();
//        System.out.println(conferencesList.size());
        List<JSONObject> trueConferencesList = conferenceService.selectAll(pageNum,pageSize);
//        for( Conference conference :  conferencesList)
//        { //遍历每个会议，查询出各个ID对应的name属性
//            Organizer organizer = organizerService.findOrganizerById(conference.getOrganizerId());
//            Fleet fleet = fleetService.findFleetById(conference.getFleetId());
//            Hotel hotel = hotelService.getHotelById(conference.getHotelId());
//            JSONObject object = new JSONObject();
//            object.put("conferenceId",conference.getConferenceId());
//            object.put("conferenceName",conference.getConferenceName());
//
//            object.put("organizerId",conference.getOrganizerId());
//            object.put("organizerUnit",organizer.getOrganizerUnit());
//
//            object.put("conferenceStart",conference.getConferenceStart());
//            object.put("conferenceEnd",conference.getConferenceEnd());
//            object.put("conferenceLocation",conference.getConferenceLocation());
//
//            object.put("hotelId",conference.getHotelId());
//            object.put("hotelName",hotel.getHotelName());
//
//            object.put("fleetId",conference.getFleetId());
//            object.put("fleetName",fleet.getFleetName());
//
//            object.put("conferenceInfo",conference.getConferenceInfo());
//            trueConferencesList.add(object);
//        }
//        PageInfo<JSONObject> pi  = new PageInfo<>(trueConferencesList);
//        System.out.println(pi.getTotal());
        return Result.success("trueConferencesList",trueConferencesList);
    }

    @PostMapping("/updateConferenceByAdmin/{conferenceId}/{organizerId}/{hotelId}/{fleetId}")
    public Result updateConferenceByAdmin(@PathVariable Integer conferenceId,@PathVariable Integer organizerId,@PathVariable Integer hotelId,@PathVariable Integer fleetId){
        Conference conference = conferenceService.queryConferenceByConferenceId(conferenceId);
        System.out.println(conference);
        Organizer organizer = organizerService.findOrganizerById(organizerId);
        Fleet fleet = fleetService.findFleetById(fleetId);
        Hotel hotel = hotelService.getHotelById(hotelId);
        Map<String, Object> map = new HashMap<>();
        map.put("conference",conference);
        map.put("organizer",organizer);
        map.put("fleet",fleet);
        map.put("hotel",hotel);
        return Result.success(map);
    }

    @PostMapping("/submitUpdateConferenceByAdmin")
    public Result submitUpdateConferenceByAdmin(@RequestParam Map<String,Object> map){
        Integer conferenceId = Integer.parseInt(map.get("conferenceId").toString()) ;
        String conferenceName = (String) map.get("conferenceName");
        Integer organizerId = Integer.parseInt(map.get("conferenceOrganizerUnitId").toString());
        String organizerUnit = (String) map.get("conferenceOrganizerUnit");
        String conferenceStart = (String) map.get("conferenceStart");
        String conferenceEnd = (String) map.get("conferenceEnd");
        String conferenceLocation = (String) map.get("conferenceLocation");
        Integer hotelId = Integer.parseInt(map.get("hotelId").toString());
        String hotelName = (String) map.get("hotelName");
        Integer fleetId = Integer.parseInt(map.get("fleetId").toString());
        String fleetName = (String) map.get("fleetName");
        String conferenceInfo = (String) map.get("conferenceInfo");
        conferenceService.updateConference(new Conference(conferenceId,organizerId,fleetId,hotelId,conferenceName,
                conferenceStart,conferenceEnd,conferenceLocation,conferenceInfo));
        return Result.success();
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

