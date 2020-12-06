package com.conference.controller;

import com.conference.entity.Participant;
import com.conference.service.ParticipantService;
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
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @RequestMapping("/participant/showParticipants")
    @ResponseBody
    public List<Participant> showParticipants(){
        List<Participant> participantsList = participantService.queryParticipants();
        return participantsList;
    }

    //转发到修改参加者信息页面
    @GetMapping("/participant/updateParticipant/{participantId}")
    public String getUpdateParticipant(@PathVariable("participantId") Integer participantId, Model model){
        Participant participant = participantService.queryParticipantByParticipantId(participantId);
        model.addAttribute("participant",participant);
        return "participant/updateParticipant";
    }

    //修改参加者信息
    @PostMapping("/participant/updateParticipant")
    @ResponseBody
    public String postUpdateParticipant(Participant participant){
        participantService.updateParticipant(participant);
        return "success";
    }

    //删除参加者
    @GetMapping("/participant/deleteParticipant/{participantId}")
    @ResponseBody
    public String getDeleteParticipant(@PathVariable("participantId") Integer participantId){
        participantService.deleteParticipant(participantId);
        return "success";
    }

}
