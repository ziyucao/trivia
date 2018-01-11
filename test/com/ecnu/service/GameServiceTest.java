package com.ecnu.service;

import com.ecnu.dao.PlayerDAO;
import com.ecnu.entities.PlayerEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameServiceTest
{

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
        for (int i = 0; i < 4; i++)
        {
            PlayerEntity pe = new PlayerEntity();
            pe.setUserId("testPlayer" + i);
            PlayerDAO.deletePlayer(pe);
        }
    }

    @Test
    public void check_if_game_is_end_when_one_player_get_six_coins()
    {
        List<PlayerEntity> players = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setUserId("testPlayer" + i);
            playerEntity.setGroupId(666);
            playerEntity.setIdInGroup(i);
            PlayerDAO.insertPlayer(playerEntity);
            players.add(playerEntity);
        }
        PlayerEntity playerEntity = players.get(0);
        playerEntity.setCoins(6);
        PlayerDAO.updatePlayer(playerEntity);
        Assert.assertEquals(0, GameService.gameIsEnd(playerEntity));
    }
}