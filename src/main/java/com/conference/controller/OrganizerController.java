package com.conference.controller;

import com.conference.entity.Organizer;
import com.conference.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/6 10:41
 **/
@Controller
public class OrganizerController {
    
    @Autowired
    private OrganizerService organizerService;

    @RequestMapping("/organizer/showOrganizers")
    @ResponseBody
    public List<Organizer> showOrganizers(){
        List<Organizer> organizersList = organizerService.queryOrganizers();
        return organizersList;
    }

    //转发到修改组织者信息页面
    @GetMapping("/organizer/updateOrganizer/{organizerId}")
    public String getUpdateOrganizer(@PathVariable("organizerId") Integer organizerId, Model model){
        Organizer organizer = organizerService.queryOrganizerByOrganizerId(organizerId);
        model.addAttribute("organizer",organizer);
        return "organizer/updateOrganizer";
    }

    //修改组织者信息
    @PostMapping("/organizer/updateOrganizer")
    @ResponseBody
    public String postUpdateOrganizer(Organizer organizer){
        organizerService.updateOrganizer(organizer);
        return "success";
    }

    //删除组织者
    @GetMapping("/organizer/deleteOrganizer/{organizerId}")
    @ResponseBody
    public String getDeleteOrganizer(@PathVariable("organizerId") Integer organizerId){
        organizerService.deleteOrganizer(organizerId);
        return "success";
    }
}
