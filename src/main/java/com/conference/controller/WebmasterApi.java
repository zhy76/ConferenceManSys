package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// /webmaster/test
@RestController
@RequestMapping("/webmaster")
public class WebmasterApi {
    @RequestMapping("/test")
    public Object test(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("webmastertest", "you can get customer");
        return jsonObject;
    }
}
