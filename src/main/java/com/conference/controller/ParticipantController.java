package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.dao.ParticipantDao;
import com.conference.entity.Participant;
import com.conference.service.ParticipantService;
import com.conference.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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




}
