package com.conference.controller;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 14:04
 * @stuid 6109118041
 */

import com.conference.entity.Conference;
import com.conference.service.ConferenceService;
import com.conference.util.result.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/showConference/{conferenceId}")
    public Result showConference(@PathVariable Integer conferenceId){
        Conference conference = conferenceService.queryConferenceByConferenceId(conferenceId);
        return Result.success("conference",conference);
    }
}

