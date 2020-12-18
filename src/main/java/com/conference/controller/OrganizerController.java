package com.conference.controller;

import com.conference.entity.Organizer;
import com.conference.service.OrganizerService;
import com.conference.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/18 10:45
 * @sno 6109118015
 */
@RestController
@RequestMapping("/organizer")
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;


    @GetMapping("/getOrganizerInfo")
    public Result getOrganizerInfo(@RequestParam Integer organizerId) {
        Organizer getOrganizerInfo = organizerService.findOrganizerById(organizerId);
        System.out.println(getOrganizerInfo);
        return Result.success("getOrganizerInfo", getOrganizerInfo);
    }

}
