package com.ecnu.service;

import com.ecnu.dao.AvailableGroupDAO;
import com.ecnu.dao.PlayerDAO;
import com.ecnu.dao.QuestionDAO;
import com.ecnu.entities.AvailableGroupEntity;
import com.ecnu.entities.PlayerEntity;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Random;

/**
 * @author Ding Donglai
 */
public final class GameService
{

    private static final int QUESTION_SUM = 20;
    private static final int MAX_COINS = 6;

    private GameService()
    {
    }

    public static int gameIsEnd(final PlayerEntity playerEntity)
    {
        final List<PlayerEntity> players = PlayerDAO.getPlayersInGroup(playerEntity);
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
     * @return question id
     */
    public static int diceAndGetQuestion(final PlayerEntity playerEntity, final int dice)
    {
        if (playerEntity != null)
        {
            if (playerEntity.getIsPunished() == 1)
            {
                if (dice % 2 == 1)
                {
                    playerEntity.setIsPunished(0);
                    playerEntity.setPosition((playerEntity.getPosition() + dice) % QUESTION_SUM);
                } else
                {
                    playerEntity.setIsAnswering(0);
                }
            } else
            {
                playerEntity.setPosition((playerEntity.getPosition() + dice) % QUESTION_SUM);
            }

            PlayerDAO.updatePlayer(playerEntity);

            if (playerEntity.getIsPunished() == 1)
            {
                return -1;
            }
            return playerEntity.getPosition() + 1;
        } else
        {
            return -1;
        }
    }

    public static void answering(final PlayerEntity playerEntity, final int questionId, final String option)
    {
        final boolean isCorrect = QuestionDAO.checkAnswer(questionId, option);
        if (playerEntity != null)
        {
            if (isCorrect)
            {
                playerEntity.setCoins(playerEntity.getCoins() + 1);
            } else
            {
                playerEntity.setIsPunished(1);
            }
            playerEntity.setIsAnswering(0);

            PlayerDAO.updatePlayer(playerEntity);
        }
    }

    public static void clearWhenGameOver(final PlayerEntity playerEntity)
    {
        final List<PlayerEntity> players = PlayerDAO.getPlayersInGroup(playerEntity);
        if (players != null)
        {
            for (final PlayerEntity player : players)
            {
                PlayerDAO.deletePlayer(player);
            }

            final int groupId = playerEntity.getGroupId();
            AvailableGroupDAO.insertAvailableGroup(new AvailableGroupEntity(groupId));

        }
    }

    public static void nextPlayer(final PlayerEntity playerEntity)
    {
        final List<PlayerEntity> players = PlayerDAO.getPlayersInGroup(playerEntity);
        if (players != null)
        {
            int nextId = -1;
            for (int i = 0; i < players.size(); i++)
            {
                if (players.get(i).getUserId().equals(playerEntity.getUserId()))
                {
                    nextId = i + 1 >= players.size() ? 0 : i + 1;
                }
            }

            if (nextId >= 0 && nextId < players.size())
            {
                final PlayerEntity nextPlayer = players.get(nextId);
                nextPlayer.setIsAnswering(1);
                PlayerDAO.updatePlayer(nextPlayer);
            }
        }
    }

    public static int updatePlayerInformationInTheGroup(final ModelMap model, final String name)
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

    public static int dicing()
    {
        return new Random().nextInt(6) + 1;
    }

}