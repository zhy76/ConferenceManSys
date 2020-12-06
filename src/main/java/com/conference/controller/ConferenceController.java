package com.conference.controller;

import com.conference.entity.Conference;
import com.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/4 23:44
 **/
@Controller
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @RequestMapping("/conference/showConferences")
    @ResponseBody
    public List<Conference> showConferences(){
        List<Conference> conferencesList = conferenceService.queryConferences();
        return conferencesList;
    }

    //转发到修改会议信息页面
    @GetMapping("/conference/updateConference/{conferenceId}")
    public String getUpdateConference(@PathVariable("conferenceId") Integer conferenceId, Model model){
        Conference conference = conferenceService.queryConferenceById(conferenceId);
        model.addAttribute("conference",conference);
        return "conference/updateConference";
    }

    //修改会议信息
    @PostMapping("/conference/updateConference")
    @ResponseBody
    public String postUpdateConference(Conference conference){
        conferenceService.updateConference(conference);
        return "success";
    }

    //删除会议信息
    @GetMapping("/conference/deleteConference/{conferenceId}")
    @ResponseBody
    public String getDeleteConference(@PathVariable("conferenceId") Integer conferenceId){
        conferenceService.deleteConference(conferenceId);
        return "success";
    }

}
