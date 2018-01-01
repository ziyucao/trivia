package com.ecnu.controller;

import com.ecnu.dao.PlayerDAO;
import com.ecnu.dao.QuestionDAO;
import com.ecnu.entities.PlayerEntity;
import com.ecnu.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

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

    @RequestMapping(value = "/gameroom", method = RequestMethod.POST)
    public String dicing(ModelMap model, @RequestParam String name)
    {
        PlayerEntity pe = PlayerDAO.getPlayer(name);
        int questionId = GameService.diceAndGetQuestion(pe);
        String questionStatement = QuestionDAO.getQuestion(questionId);
        ArrayList<String> options = QuestionDAO.getOption(questionId);
        if (questionStatement != null)
        {
            model.addAttribute("question", questionStatement);
        }
        if (options != null && options.size() == 4)
        {
            model.addAttribute("optionA", options.get(0));
            model.addAttribute("optionB", options.get(1));
            model.addAttribute("optionC", options.get(2));
            model.addAttribute("optionD", options.get(3));
        }

        return "gameroom";
    }

    @RequestMapping(value = "/gameroom", method = RequestMethod.POST)
    public String answerQuestion(ModelMap model, @RequestParam String name, @RequestParam int questionId, @RequestParam String option)
    {
        PlayerEntity pe = PlayerDAO.getPlayer(name);
        GameService.answering(pe, questionId, option);
        int playerId = GameService.gameIsEnd(pe);
        if (playerId > 0)
        {
            GameService.clearWhenGameOver(pe);
            model.addAttribute("winnerId", playerId);
            return "welcome";
        }

        return "gameroom";
    }
}
