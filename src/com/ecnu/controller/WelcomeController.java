package com.ecnu.controller;

import com.ecnu.service.PlayerService;
import com.ecnu.entities.PlayerEntity;
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
        String redirect;
        PlayerEntity pe = new PlayerEntity();
        pe.setUserId(name);
        if (PlayerService.login(pe))
        {
            redirect = "waitingroom";
            model.addAttribute("name", name);
        } else {
            redirect = "welcome";
            model.addAttribute("errorMessage", "用户名重复！");
        }
        return redirect;
    }


}
