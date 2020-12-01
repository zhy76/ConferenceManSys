package com.conference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@Controller
public class TestController {
//    @GetMapping("/test")
    @RequestMapping("/test")
    public String hello(Model model) {
        model.addAttribute("msg", "<h1>hello</h1>");
        model.addAttribute("users", Arrays.asList("hh", "hsc"));

        return "test";
    }
}
