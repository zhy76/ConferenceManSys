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

@Controller
@RequestMapping("/conference")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    /**
     * 查询所有会议
     * @return
     */
    @RequestMapping("/showConferences")
    @ResponseBody
    public List<Conference> showConferences(){
        List<Conference> conferencesList = conferenceService.queryConferences();
        return conferencesList;
    }

    /**
     * 查询指定id会议
     * @param conferenceId
     * @return
     */
    @RequestMapping("/showConference/{conferenceId}")
    @ResponseBody
    public Conference showConference(@PathVariable Integer conferenceId){
        Conference conference = conferenceService.queryConferenceById(conferenceId);
        return conference;
    }

    /**
     * 修改对应会议信息
     * @param
     * @return
     */
    @RequestMapping("/updateConference")
    @ResponseBody
    public Result updateConference(@PathVariable Conference conference){
        conferenceService.updateConference(conference);
        return Result.success();
    }

    /**
     * 删除指定会议
     * @param conferenceId
     * @return
     */
    @GetMapping("/deleteConference")
    public Result deleteConference(@RequestParam Integer conferenceId) {
        int conferenceNum = conferenceService.deleteConference(conferenceId);
//        if (driverNum < 1) return new Result(ResultCode.FAIL);
        return Result.success();
    }
}


