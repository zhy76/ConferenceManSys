package com.conference.controller;

import com.conference.entity.Conference;
import com.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/4 23:44
 **/
@RestController
@RequestMapping("/conference")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @RequestMapping("/showConferences")
    @ResponseBody
    public List<Conference> showConferences(){
        List<Conference> conferencesList = conferenceService.queryConferences();
        System.out.println(conferencesList);
        return conferencesList;
    }
}
