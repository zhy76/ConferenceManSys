package com.conference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: AjaxController
 * @Description: That's enough.
 * @Author: Lance
 * @Date: 2020/12/6 17:07
 */

@Controller
//@RequestMapping("/ajax")
public class AjaxController {
    @RequestMapping("/login")
    public String test() {
        return "popupsignin.html";
    }
//    @RequestMapping("/a1")
}
