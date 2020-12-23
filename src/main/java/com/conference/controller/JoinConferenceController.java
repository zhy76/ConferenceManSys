package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.dao.ConferenceDao;
import com.conference.dao.JoinConferenceDao;
import com.conference.entity.Conference;
import com.conference.entity.JoinConference;
import com.conference.service.ConferenceService;
import com.conference.service.JoinConferenceService;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/11 20:07
 * @sno 6109118015
 */
@RestController
@RequestMapping("/joinConference")
public class JoinConferenceController {


    @Autowired
    private TokenService tokenService;

    @Autowired
    private JoinConferenceService joinConferenceService;

    @Autowired
    private ConferenceService conferenceService;


    //参加者加入一个会议
//    @PostMapping("/joinAConference")
//    public Object joinAConference(@RequestBody JoinConference joinConference){
//        JSONObject jsonObject =new JSONObject();
//        if(joinConference.getParticipantId()==null||joinConference.getConferenceId()==null){
//            System.out.println("null");
//            jsonObject.put("message", "表单错误");
//            return jsonObject;
//        }
//        //获取当前会议的开始和结束时间
//        Conference conference = conferenceService.queryConferenceByConferenceId(joinConference.getConferenceId());
//        String start = conference.getConferenceStart();
//        String end = conference.getConferenceEnd();
//        //获得当前参会者已经参加的所有参会记录
//        List<JoinConference> alreadyJoinConferences = joinConferenceService.queryConferenceByParticipantId(joinConference.getParticipantId());
//        for (int i = 0; i < alreadyJoinConferences.size(); i++) {
//            Conference conf = conferenceService.queryConferenceByConferenceId(alreadyJoinConferences.get(i).getConferenceId());
//            String starts = conf.getConferenceStart();
//            String ends = conf.getConferenceEnd();
//            if ((start.compareTo(starts) >= 0 && start.compareTo(ends) <= 0)
//                || (end.compareTo(starts) >= 0 && end.compareTo(ends) <= 0)
//                ||(start.compareTo(starts) <= 0 && end.compareTo(ends) >= 0 )){
//                    jsonObject.put("message","当前会议时间与已选会议时间冲突！");
//                    return jsonObject;
//                }
//        }
//        int addNumber = joinConferenceService.joinAConference(joinConference);
//        if (addNumber > 0) {
//            String token = tokenService.getToken(addNumber);
//            jsonObject.put("token", token);
//            return jsonObject;
//        } else {
//            jsonObject.put("message", "加入失败！");
//            return jsonObject;
//        }
//    }

    @PostMapping("/joinAConference")
    public Result joinAConference(@RequestBody JoinConference joinConference){
        System.out.println(joinConference);
        if(joinConference.getParticipantId()==null||joinConference.getConferenceId()==null){
            return new Result(ResultCode.BindException);
        }
        //获取当前会议的开始和结束时间
        Conference conference = conferenceService.queryConferenceByConferenceId(joinConference.getConferenceId());
        String start = conference.getConferenceStart();
        String end = conference.getConferenceEnd();
        //获得当前参会者已经参加的所有参会记录
        List<JoinConference> alreadyJoinConferences = joinConferenceService.queryConferenceByParticipantId(joinConference.getParticipantId());
        for (int i = 0; i < alreadyJoinConferences.size(); i++) {
            Conference conf = conferenceService.queryConferenceByConferenceId(alreadyJoinConferences.get(i).getConferenceId());
            String starts = conf.getConferenceStart();
            String ends = conf.getConferenceEnd();
            if ((start.compareTo(starts) >= 0 && start.compareTo(ends) <= 0)
                    || (end.compareTo(starts) >= 0 && end.compareTo(ends) <= 0)
                    ||(start.compareTo(starts) <= 0 && end.compareTo(ends) >= 0 )){
               return new Result(2,"时间冲突");
            }
        }
        int addNumber = joinConferenceService.joinAConference(joinConference);
        if (addNumber > 0) {
            String token = tokenService.getToken(addNumber);
            return Result.success("token", token);
        } else {
            return new Result(ResultCode.FAIL);
        }
    }

//    @GetMapping("/cancelAConference")
//    public Object cancelAJoinedConferenceById(@RequestParam Integer participantId,@RequestParam Integer conferenceId){
//        JSONObject jsonObject = new JSONObject();
//        int cancelNumber = joinConferenceService.cancelAJoinedConferenceById(participantId,conferenceId);
//        if (cancelNumber > 0) {
////            String token = tokenService.getToken(cancelNumber);
////            jsonObject.put("token", token);
//            jsonObject.put("message", "取消成功！");
//            return jsonObject;
//        } else {
//            jsonObject.put("message", "取消失败！");
//            return jsonObject;
//        }
//    }

    @GetMapping("/cancelAConference")
    public Result cancelAJoinedConferenceById(@RequestParam Integer participantId,@RequestParam Integer conferenceId){
        int cancelNumber = joinConferenceService.cancelAJoinedConferenceById(participantId,conferenceId);
        if (cancelNumber > 0) {
            return Result.success("cancelAConference","取消成功");
        } else {
            return new Result(ResultCode.FAIL);
        }
    }


    @GetMapping("/queryJoinedConference")
    public Result queryJoinedConferenceByParticipantId(@RequestParam Integer participantId){
        List<JoinConference> joinConferenceList = joinConferenceService.queryConferenceByParticipantId(participantId);
        return Result.success("joinConferenceList",joinConferenceList);
    }

    @GetMapping("/queryJoinConferenceByConferenceId")
    public Result queryJoinConferenceByConferenceId(@RequestParam Integer conferenceId) {
        List<JoinConference> joinConferences = joinConferenceService.queryJoinConferenceByConferenceId(conferenceId);
        List<JoinConference> joinConference = new ArrayList<>();
        for (JoinConference it : joinConferences) {
            if (it.getPickup()) {
                joinConference.add(it);
            }
        }
        return Result.success("joinConference", joinConference);
    }

    @GetMapping("/queryJoinedConferenceByParticipantIdAndConferenceId")
    public Result queryJoinedConferenceByParticipantIdAndConferenceId(@RequestParam Integer participantId,
                                                                      @RequestParam Integer conferenceId){
        JoinConference joinConference = joinConferenceService.queryJoinedConferenceByParticipantIdAndConferenceId(participantId, conferenceId);
        return Result.success("joinConference",joinConference);
    }
}
