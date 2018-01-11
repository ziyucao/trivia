package com.ecnu.dao;

import com.ecnu.entities.PlayerEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PlayerDAOTest
{

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setUserId("test_insert_and_get");
        PlayerDAO.deletePlayer(playerEntity);
        playerEntity.setUserId("test_update_and_get");
        PlayerDAO.deletePlayer(playerEntity);
        for (int i = 0; i < 4; i++)
        {
            PlayerEntity pe = new PlayerEntity();
            pe.setUserId("testPlayer" + i);
            PlayerDAO.deletePlayer(pe);
        }
    }

    @Test
    public void insert_player_entity_and_get_the_same_player()
    {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setUserId("test_insert_and_get");
        playerEntity.setIsAnswering(1);
        playerEntity.setIsPunished(1);
        playerEntity.setPosition(15);
        playerEntity.setCoins(5);
        playerEntity.setGroupId(8);
        playerEntity.setIdInGroup(1);
        PlayerDAO.insertPlayer(playerEntity);
        Assert.assertEquals(playerEntity, PlayerDAO.getPlayer("test_insert_and_get"));
    }

    @Test
    public void update_player_entity_and_get_the_player()
    {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setUserId("test_update_and_get");
        playerEntity.setIsAnswering(1);
        playerEntity.setIsPunished(1);
        playerEntity.setPosition(15);
        playerEntity.setCoins(5);
        playerEntity.setGroupId(8);
        playerEntity.setIdInGroup(1);
        PlayerDAO.insertPlayer(playerEntity);
        playerEntity.setCoins(100);
        PlayerDAO.updatePlayer(playerEntity);
        int coins = PlayerDAO.getPlayer("test_update_and_get").getCoins();
        Assert.assertEquals(100, coins);
    }

    @Test
    public void test_get_player_in_group()
    {
        List<PlayerEntity> players = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setUserId("testPlayer" + i);
            playerEntity.setGroupId(888);
            playerEntity.setIdInGroup(i);
            PlayerDAO.insertPlayer(playerEntity);
            players.add(playerEntity);
        }
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setGroupId(888);
        Assert.assertEquals(players, PlayerDAO.getPlayersInGroup(playerEntity));
    }

}