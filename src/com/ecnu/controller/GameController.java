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

    @RequestMapping(value = "/gameroom", method = RequestMethod.POST)
    public String GameController(ModelMap model, @RequestParam String name)
    {
        PlayerEntity pe = PlayerDAO.getPlayer(name);
        ArrayList<PlayerEntity> players = PlayerDAO.getPlayersInGroup(pe);

        //如果房间人满
        if (players != null && players.size() == 4)
        {
            model.addAttribute("name1", players.get(1).getUserId());
            model.addAttribute("name2", players.get(2).getUserId());
            model.addAttribute("name3", players.get(3).getUserId());
            model.addAttribute("name4", players.get(4).getUserId());
            model.addAttribute("coin1", players.get(1).getCoins());
            model.addAttribute("coin2", players.get(2).getCoins());
            model.addAttribute("coin3", players.get(3).getCoins());
            model.addAttribute("coin4", players.get(4).getCoins());
            model.addAttribute("myName", name);
            model.addAttribute("isAnswering", pe.getIsAnswering());
            model.addAttribute("isPunished", pe.getIsPunished());

            return "gameroom";
        }
        //否则
        else
        {
            model.addAttribute("name", name);
            return "waitingroom";
        }
    }

    @RequestMapping(value = "/rolling")
    public String rolling(ModelMap model, @RequestParam String name)
    {
        int dice = GameService.dicing();
        PlayerEntity pe = PlayerDAO.getPlayer(name);
        int questionId = GameService.diceAndGetQuestion(pe, dice);
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

        model.addAttribute("dice", dice);
        model.addAttribute("isRolled", true);

        return "gameroom";
    }

    @RequestMapping(value = "/answer", method = RequestMethod.POST)
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
        else
        {
            GameService.nextPlayer(pe);
        }

        model.addAttribute("isAnswered", true);

        return "gameroom";
    }

}
