package com.conference.controller;

import com.conference.entity.Conference;
import com.conference.service.ConferenceService;
import com.conference.util.result.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/11/30 22:04
 * @sno 6109118015
 */

@Controller
@RequestMapping("/conference")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

//    @RequestMapping("/showConferences")
//    @ResponseBody
//    public List<Conference> showConferences(){
//        List<Conference> conferencesList = conferenceService.queryConferences();
//        return conferencesList;
//    }
//
//    @RequestMapping("/showConference/{conferenceId}")
//    @ResponseBody
//    public Conference showConference(@PathVariable Integer conferenceId){
//        Conference conference = conferenceService.queryConferenceByConferenceId(conferenceId);
//        return conference;
//    }
    /**
     * 查找所有的司机 Api
     * /driver/getAllDriver
     *
     * @return result {}
     */
//    @GetMapping("/getAllDriver")
//    public Result getAllDriver() {
//        List<Driver> getAllDriver = driverService.findAllDriver();
////        return Result.success("getAllDriver", getAllDriver);
//    }
    /**
     * 查找所有的司机 Api
     * /driver/getAllDriver
     *
     * @return result {}
     */
    @GetMapping("/showConferences")
    public Result showConferences(){
        List<Conference> conferencesList = conferenceService.queryConferences();
        return Result.success("conferencesList",conferencesList);
    }

    @GetMapping("/showConference/{conferenceId}")
    public Result showConference(@PathVariable Integer conferenceId){
        Conference conference = conferenceService.queryConferenceByConferenceId(conferenceId);
        return Result.success("conference",conference);
    }

}
