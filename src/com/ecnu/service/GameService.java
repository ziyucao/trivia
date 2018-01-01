package com.ecnu.service;

import com.ecnu.dao.AvailableGroupDAO;
import com.ecnu.dao.PlayerDAO;
import com.ecnu.dao.QuestionDAO;
import com.ecnu.entities.AvailableGroupEntity;
import com.ecnu.entities.PlayerEntity;

import java.util.ArrayList;
import java.util.Random;

public class GameService {

    private static final int QUESTION_SUM = 20;
    private static final int MAX_COINS = 6;

    public static int gameIsEnd(PlayerEntity pe)
    {
        ArrayList<PlayerEntity> players = PlayerDAO.getPlayersInGroup(pe);
        if (players != null)
        {
            for (int i = 0; i < players.size(); i++)
            {
                if (players.get(i) != null && players.get(i).getCoins() == MAX_COINS)
                {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @param pe
     * @return question id
     */
    public static int diceAndGetQuestion(PlayerEntity pe, int dice)
    {
        if (pe != null)
        {
            if (pe.getIsPunished() == 1)
            {
                if (dice % 2 == 1)
                {
                    pe.setIsPunished(0);
                    pe.setPosition((pe.getPosition() + dice) % QUESTION_SUM);
                }
                else
                {
                    pe.setIsAnswering(0);
                }
            }
            else
            {
                pe.setPosition((pe.getPosition() + dice) % QUESTION_SUM);
            }

            PlayerDAO.updatePlayer(pe);

            if (pe.getIsPunished() == 1)
            {
                return -1;
            }
            return pe.getPosition() + 1;
        }
        else
        {
            return -1;
        }
    }

    public static void answering(PlayerEntity pe, int questionId, String option)
    {
        boolean isCorrect = QuestionDAO.checkAnswer(questionId, option);
        if (pe != null)
        {
            if (isCorrect)
            {
                pe.setCoins(pe.getCoins() + 1);
            }
            else
            {
                pe.setIsPunished(1);
            }
            pe.setIsAnswering(0);

            PlayerDAO.updatePlayer(pe);
        }
    }

    public static void clearWhenGameOver(PlayerEntity pe)
    {
        ArrayList<PlayerEntity> players= PlayerDAO.getPlayersInGroup(pe);
        if (players != null)
        {
            for (int i = 0; i < players.size(); i++)
            {
                PlayerDAO.deletePlayer(players.get(i));
            }

            int groupId = pe.getGroupId();
            AvailableGroupDAO.insertAvailableGroup(new AvailableGroupEntity(groupId));

        }
    }

    public static void nextPlayer(PlayerEntity pe)
    {
        ArrayList<PlayerEntity> players = PlayerDAO.getPlayersInGroup(pe);
        if (players !=  null)
        {
            int nextId = -1;
            for (int i = 0; i < players.size(); i++)
            {
                if (players.get(i).getUserId().equals(pe.getUserId()))
                {
                    nextId = i + 1 >= players.size() ? 0 : i + 1;
                }
            }

            if (nextId >= 0 && nextId < players.size())
            {
                PlayerEntity nextPlayer = players.get(nextId);
                nextPlayer.setIsAnswering(1);
                PlayerDAO.updatePlayer(nextPlayer);
            }
        }
    }

    public static void playerQuit(PlayerEntity pe)
    {
        if (pe != null)
        {
            PlayerDAO.deletePlayer(pe);
        }
    }

    public static int dicing()
    {
        return new Random().nextInt(6) + 1;
    }
}
