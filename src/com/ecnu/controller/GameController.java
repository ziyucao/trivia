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
import java.util.List;

/**
 * @author CaoZiyu
 */
@Controller
public class GameController
{
    public static final String WAITING_ROOM_PAGE = "waitingroom";
    public static final String GAME_ROOM_PAGE = "gameroom";
    public static final String WELCOME_PAGE = "welcome";


    @RequestMapping(value = "/gameroom", method = RequestMethod.POST)
    public String gameController(final ModelMap model, @RequestParam final String name)
    {
        String redirect = WAITING_ROOM_PAGE;
        //如果房间人满
        if (updatePlayerInformationInTheGroup(model, name) == 4)
        {
            redirect = GAME_ROOM_PAGE;
        }
        //否则
        else
        {
            model.addAttribute("name", name);
        }
        return redirect;
    }

    @RequestMapping(value = "/rolling", method = RequestMethod.POST)
    public String rolling(final ModelMap model, @RequestParam final String name)
    {
        final int dice = GameService.dicing();
        final PlayerEntity playerEntity = PlayerDAO.getPlayer(name);
        final int questionId = GameService.diceAndGetQuestion(playerEntity, dice);
        if (questionId < 0)
        {
            GameService.nextPlayer(playerEntity);
            updatePlayerInformationInTheGroup(model, name);
        } else
        {
            final String questionStatement = QuestionDAO.getQuestion(questionId);
            final List<String> options = QuestionDAO.getOption(questionId);
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
        }

        return GAME_ROOM_PAGE;
    }

    @RequestMapping(value = "/answer", method = RequestMethod.POST)
    public String answerQuestion(final ModelMap model, @RequestParam final String name, @RequestParam final int questionId, @RequestParam final String option)
    {
        final PlayerEntity playerEntity = PlayerDAO.getPlayer(name);
        GameService.answering(playerEntity, questionId, option);
        final int playerId = GameService.gameIsEnd(playerEntity);
        if (playerId >= 0)
        {
            updatePlayerInformationInTheGroup(model, name);
            GameService.clearWhenGameOver(playerEntity);
            // isEnd==2  ->  you win
            model.addAttribute("isEnd", 2);
        } else
        {
            GameService.nextPlayer(playerEntity);
            updatePlayerInformationInTheGroup(model, name);
        }
        return GAME_ROOM_PAGE;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String gameUpdateController(final ModelMap model, @RequestParam final String name)
    {
        if (updatePlayerInformationInTheGroup(model, name) == 0)
        {
            // isEnd==1  ->  you lose
            model.addAttribute("isEnd", 1);
        }
        return GAME_ROOM_PAGE;
    }

    @RequestMapping(value = "/end", method = RequestMethod.POST)
    public String gameEndController()
    {
        return WELCOME_PAGE;
    }


    private int updatePlayerInformationInTheGroup(final ModelMap model, final String name)
    {
        final PlayerEntity playerEntity = PlayerDAO.getPlayer(name);
        final List<PlayerEntity> players = PlayerDAO.getPlayersInGroup(playerEntity);

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
