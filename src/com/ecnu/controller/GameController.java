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
//        test
//        model.addAttribute("name0", 11);
//        model.addAttribute("name1", 22);
//        model.addAttribute("name2", 33);
//        model.addAttribute("name3", 44);
//        model.addAttribute("coin0", 1);
//        model.addAttribute("coin1", 1);
//        model.addAttribute("coin2", 1);
//        model.addAttribute("coin3", 1);
//        model.addAttribute("myName", name);
//        model.addAttribute("isAnswering", true);
//        model.addAttribute("isPunished", false);
//        return "gameroom";
        //如果房间人满
        if (updatePlayerInformationInTheGroup(model, name) == 4)
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

    @RequestMapping(value = "/rolling", method = RequestMethod.POST)
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
        model.addAttribute("isRolled", 1);
        model.addAttribute("questionId", questionId);
        updatePlayerInformationInTheGroup(model, name);

        return "gameroom";
    }

    @RequestMapping(value = "/answer", method = RequestMethod.POST)
    public String answerQuestion(ModelMap model, @RequestParam String name, @RequestParam int questionId, @RequestParam String option)
    {
        PlayerEntity pe = PlayerDAO.getPlayer(name);
        GameService.answering(pe, questionId, option);
        int playerId = GameService.gameIsEnd(pe);
        if (playerId >= 0)
        {
            GameService.clearWhenGameOver(pe);
            model.addAttribute("winnerId", playerId);
            return "welcome";
        } else
        {
            GameService.nextPlayer(pe);
        }

        updatePlayerInformationInTheGroup(model, name);

        return "gameroom";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String GameUpdateController(ModelMap model, @RequestParam String name)
    {
        if (updatePlayerInformationInTheGroup(model, name) == 0)
        {
            return "welcome";
        }
        return "gameroom";

    }
    private int updatePlayerInformationInTheGroup(ModelMap model, String name)
    {
        PlayerEntity pe = PlayerDAO.getPlayer(name);
        ArrayList<PlayerEntity> players = PlayerDAO.getPlayersInGroup(pe);

        if (players != null && players.size() == 4)
        {
            for (int i = 0; i < 4; i++)
            {
                model.addAttribute("name" + i, players.get(i).getUserId());
                model.addAttribute("coin" + i, players.get(i).getCoins());
                model.addAttribute("answering" + i, players.get(i).getIsAnswering());
                model.addAttribute("punished" + i, players.get(i).getIsPunished());

            }
            model.addAttribute("myName", name);
            return players.size();
        }
        return 0;
    }

}
