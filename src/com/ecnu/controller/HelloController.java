package com.ecnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/helloworld")

    public String HelloController() {
        return "helloworld";

    }
}
