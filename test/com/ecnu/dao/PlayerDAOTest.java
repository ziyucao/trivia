package com.ecnu.dao;

import com.ecnu.entities.PlayerEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertEquals(PlayerDAO.getPlayer("test_insert_and_get"), playerEntity);
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
        Assert.assertEquals(coins, 100);
    }

}