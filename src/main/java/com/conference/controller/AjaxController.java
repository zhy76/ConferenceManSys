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
@RequestMapping("/ajax")
public class AjaxController {

    @RequestMapping("/a1")
    public void ajax(String name, HttpServletResponse response) throws IOException {
        if ("admin".equals(name)) {
            response.getWriter().print("true");
        } else {
            response.getWriter().println("false");
        }
    }

}
