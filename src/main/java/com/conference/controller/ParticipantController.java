package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.dao.JoinConferenceDao;
import com.conference.dao.ParticipantDao;
import com.conference.entity.JoinConference;
import com.conference.entity.Participant;
import com.conference.service.JoinConferenceService;
import com.conference.service.ParticipantService;
import com.conference.service.TokenService;
import io.jsonwebtoken.Claims;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/3 18:45
 * @sno 6109118015
 */

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ParticipantDao participantDao;

    @Autowired
    private ParticipantService participantService;



    //参加者注册
    @PostMapping("/register")
    public Object register(@Valid @RequestBody Participant participant) {
        JSONObject jsonObject = new JSONObject();
        if (participant.getParticipantName() == null || participant.getParticipantPass() == null) {
            jsonObject.put("message", "表单错误");
            return jsonObject;
        }
        int addNumber = participantService.addAParticipant(participant);
        if (addNumber > 0) {
            String token = tokenService.getToken(addNumber);
            jsonObject.put("token", token);
            return jsonObject;
        } else {
            jsonObject.put("message", "注册失败");
            return jsonObject;
        }
//        return jsonObject;
    }

    //参加者登录
    @PostMapping("/login")
    public Object login(@RequestBody Participant participant) {
        JSONObject jsonObject = new JSONObject();
        if (participant.getParticipantPhone() == null || participant.getParticipantPass() == null) {
            System.out.println("null");
            jsonObject.put("message", "表单错误");
            return jsonObject;
        }
        Participant participantForBase = participantDao.queryParticipantByParticipantPhone(participant.getParticipantPhone());
        //   Driver driverForBase = driverDao.findDriverByPhone(driver.getDriverPhone());
        System.out.println(participantForBase);
//        User userForBase =userDao.findByUsername(loginUser.getUserName());
        if (participantForBase == null) {
            jsonObject.put("message", "登录失败,用户不存在");
            return jsonObject;
        } else {
            if (!participantForBase.getParticipantPass().equals(participant.getParticipantPass())) {
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = tokenService.getToken(participantForBase);
                jsonObject.put("token", token);
                return jsonObject;
            }
        }
    }

    //参加者修改自己信息
    @PostMapping("/updateParticipant")
    public Object updateParticipant(@RequestBody Participant participant, HttpServletRequest request) {
        JSONObject result=new JSONObject();
        System.out.println(request.getHeader("token"));
        Claims claims = tokenService.parseToken(request.getHeader("token"));

        participant.setParticipantId((Integer) claims.get("participantId"));
        System.out.println(participant.getParticipantId());
        try {
            participantService.updateParticipant(participant);
        } catch (DataAccessException e) {
            throw new RuntimeException("修改失败");
        }
        result.put("state",1);
        return result.toJSONString();
    }



}
