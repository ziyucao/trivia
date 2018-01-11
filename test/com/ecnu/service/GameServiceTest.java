package com.ecnu.service;

import com.ecnu.dao.AvailableGroupDAO;
import com.ecnu.dao.PlayerDAO;
import com.ecnu.entities.PlayerEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameServiceTest
{

    private static int MAX_PLAYERS_IN_ONE_GROUP = 4;
    private static int TEST_GROUP_ID = 666;
    private static int TEST_QUESTION_INDEX = 1;
    private static String CORRECT_ANSWER = "b";
    private static String WRONG_ANSWER = "d";
    private static int ODD_DICE = 5;
    private static int EVEN_DICE = 6;

    private List<PlayerEntity> players = new ArrayList<>();
    @Before
    public void setUp()
    {
        for (int i = 0; i < MAX_PLAYERS_IN_ONE_GROUP; i++)
        {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setUserId("testPlayer" + i);
            playerEntity.setGroupId(TEST_GROUP_ID);
            playerEntity.setCoins(0);
            playerEntity.setIsPunished(0);
            playerEntity.setPosition(1);
            playerEntity.setIdInGroup(i);
            PlayerDAO.insertPlayer(playerEntity);
            players.add(playerEntity);
        }
    }

    @After
    public void tearDown()
    {
        for (int i = 0; i < MAX_PLAYERS_IN_ONE_GROUP; i++)
        {
            PlayerEntity pe = new PlayerEntity();
            pe.setUserId("testPlayer" + i);
            PlayerDAO.deletePlayer(pe);
        }
    }

    @Test
    public void check_if_game_is_end_when_one_player_get_six_coins()
    {
        PlayerEntity playerEntity = players.get(0);
        playerEntity.setCoins(GameService.MAX_COINS);
        PlayerDAO.updatePlayer(playerEntity);
        Assert.assertEquals(0, GameService.gameIsEnd(playerEntity));
    }

    @Test
    public void check_if_player_position_add_as_the_same_as_dice()
    {
        int dice = GameService.dicing();
        PlayerEntity playerEntity = players.get(0);
        int positionNow = (dice + playerEntity.getPosition()) % GameService.QUESTION_SUM;
        GameService.diceAndGetQuestion(playerEntity, dice);
        int positionActual = PlayerDAO.getPlayer(playerEntity.getUserId()).getPosition();
        Assert.assertEquals(positionNow, positionActual);
    }

    @Test
    public void check_if_coins_added_after_answering_correctly_and_go_to_next_player()
    {
        PlayerEntity playerEntity = players.get(0);
        GameService.answering(playerEntity, TEST_QUESTION_INDEX, CORRECT_ANSWER);
        GameService.nextPlayer(playerEntity);
        playerEntity = PlayerDAO.getPlayer(players.get(0).getUserId());
        PlayerEntity nextPlayer = PlayerDAO.getPlayer(players.get(1).getUserId());
        Assert.assertEquals(0, (int) playerEntity.getIsPunished());
        Assert.assertEquals(1, (int) playerEntity.getCoins());
        Assert.assertEquals(1, (int) nextPlayer.getIsAnswering());
    }

    @Test
    public void check_if_get_punished_after_wrong_answer_and_then_get_odd_dice()
    {
        PlayerEntity playerEntity = players.get(0);
        GameService.answering(playerEntity, TEST_QUESTION_INDEX, WRONG_ANSWER);
        playerEntity = PlayerDAO.getPlayer(players.get(0).getUserId());
        Assert.assertEquals(1, (int) playerEntity.getIsPunished());
        Assert.assertEquals(0, (int) playerEntity.getCoins());
        GameService.diceAndGetQuestion(playerEntity, ODD_DICE);
        Assert.assertEquals(0, (int) playerEntity.getIsPunished());
        Assert.assertEquals(0, (int) playerEntity.getCoins());
    }

    @Test
    public void check_if_get_punished_after_wrong_answer_and_then_get_even_dice()
    {
        PlayerEntity playerEntity = players.get(2);
        GameService.answering(playerEntity, TEST_QUESTION_INDEX, WRONG_ANSWER);
        playerEntity = PlayerDAO.getPlayer(players.get(2).getUserId());
        Assert.assertEquals(1, (int) playerEntity.getIsPunished());
        Assert.assertEquals(0, (int) playerEntity.getCoins());
        int result = GameService.diceAndGetQuestion(playerEntity, EVEN_DICE);
        Assert.assertEquals(1, (int) playerEntity.getIsPunished());
        Assert.assertEquals(0, (int) playerEntity.getCoins());
        Assert.assertEquals(-1, result);
    }

    @Test
    public void check_if_players_of_a_game_cleared_after_game_over()
    {
        GameService.clearWhenGameOver(players.get(0));
        AvailableGroupDAO.deleteAvailableGroup(TEST_GROUP_ID);
        Assert.assertTrue(PlayerDAO.getPlayersInGroup(players.get(1)).isEmpty());
    }

    @Test
    public void check_update_player_information_in_the_group()
    {
        ModelMap modelMap = new ModelMap();
        GameService.updatePlayerInformationInTheGroup(modelMap, "testPlayer0");
        Assert.assertEquals(players.get(0).getUserId(), modelMap.get("name0"));
        Assert.assertEquals(players.get(1).getCoins(), modelMap.get("coin1"));
        Assert.assertEquals(players.get(2).getIsAnswering(), modelMap.get("answering2"));
        Assert.assertEquals(players.get(3).getIsPunished(), modelMap.get("punished3"));
        Assert.assertEquals("testPlayer0", modelMap.get("myName"));
    }

    @Test
    public void check_null_param_returns()
    {
        PlayerEntity playerEntity = null;
        ModelMap modelMap = new ModelMap();
        Assert.assertEquals(-1, GameService.gameIsEnd(playerEntity));
        Assert.assertEquals(-1, GameService.diceAndGetQuestion(playerEntity, GameService.dicing()));
        Assert.assertEquals(0, GameService.updatePlayerInformationInTheGroup(modelMap, "testPlayer66"));
    }
}