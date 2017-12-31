package com.ecnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CaoZiyu
 */
@Controller
public class GameController
{

    @RequestMapping(value = "/gameroom")
    public String GameController(ModelMap model, @RequestParam String name)
    {
        //如果房间人满
        if (true)
        {
            return "gameroom";
        }
        //否则
        else
        {
            model.addAttribute("name", name);
            return "waitingroom";
        }
    }
}
