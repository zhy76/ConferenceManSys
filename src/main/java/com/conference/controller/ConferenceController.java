package com.conference.controller;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 14:04
 * @stuid 6109118041
 */

import com.conference.entity.Conference;
import com.conference.service.ConferenceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/conference")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;
    @RequestMapping("/showConferences")
    @ResponseBody
    public List<Conference> showConferences(){
        List<Conference> conferencesList = conferenceService.queryConferences();
        return conferencesList;
    }

    @RequestMapping("/showConference/{conferenceId}")
    @ResponseBody
    public Conference showConference(@PathVariable Integer conferenceId){
        Conference conference = conferenceService.queryConferenceById(conferenceId);
        return conference;
    }


}

