package com.ecnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {
    @RequestMapping("/")
    public String WelcomeController() {
        return "welcome";
    }

    @RequestMapping(value = "/waitingroom", method = RequestMethod.POST)
    public String login(ModelMap model, @RequestParam String name) {
        model.addAttribute("name",name);
        return "waitingroom";
    }
}
