package com.ecnu.service;

import com.ecnu.dao.AvailableGroupDAO;
import com.ecnu.dao.CurrentGroupDAO;
import com.ecnu.dao.DBConnection;
import com.ecnu.dao.PlayerDAO;
import com.ecnu.entities.AvailableGroupEntity;
import com.ecnu.entities.CurrentGroupEntity;
import com.ecnu.entities.PlayerEntity;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerServiceTest
{

    @Test
    public void login_with_a_new_valid_name_to_new_db()
    {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setUserId("testPlayerLogin");
        PlayerService.login(playerEntity);
        final Session session = DBConnection.getSession();
        final List<CurrentGroupEntity> currentGEntities = session.createQuery("from CurrentGroupEntity").list();
        session.close();
        CurrentGroupEntity currentGEntity = currentGEntities.get(0);
        Assert.assertEquals(1, currentGEntity.getId());
        Assert.assertEquals(1, (int) currentGEntity.getPlayerSum());
        playerEntity = PlayerDAO.getPlayer(playerEntity.getUserId());
        Assert.assertEquals(1, (int) playerEntity.getIdInGroup());
        Assert.assertEquals(1, (int) playerEntity.getIsAnswering());
        PlayerDAO.deletePlayer(playerEntity);
        CurrentGroupDAO.deleteCurrentGroup(currentGEntity.getId());
    }

    @Test
    public void login_with_a_name_and_then_another()
    {
        PlayerEntity playerEntity1 = new PlayerEntity();
        playerEntity1.setUserId("testPlayerLogin");
        PlayerService.login(playerEntity1);
        PlayerEntity playerEntity2 = new PlayerEntity();
        playerEntity2.setUserId("testPlayerAnother");
        PlayerService.login(playerEntity2);
        final Session session = DBConnection.getSession();
        final List<CurrentGroupEntity> currentGEntities = session.createQuery("from CurrentGroupEntity").list();
        session.close();
        CurrentGroupEntity currentGEntity = currentGEntities.get(0);
        Assert.assertEquals(2, (int) PlayerDAO.getPlayer(playerEntity2.getUserId()).getIdInGroup());
        Assert.assertEquals(1, currentGEntity.getId());
        Assert.assertEquals(2, (int) currentGEntity.getPlayerSum());
        PlayerDAO.deletePlayer(playerEntity1);
        PlayerDAO.deletePlayer(playerEntity2);
        CurrentGroupDAO.deleteCurrentGroup(currentGEntity.getId());
    }

    @Test
    public void login_with_two_same_name_the_second_will_be_denied()
    {
        PlayerEntity playerEntity1 = new PlayerEntity();
        playerEntity1.setUserId("testPlayerLogin");
        PlayerService.login(playerEntity1);
        PlayerEntity playerEntity2 = new PlayerEntity();
        playerEntity2.setUserId("testPlayerLogin");
        boolean result = PlayerService.login(playerEntity2);
        Assert.assertFalse(result);
        PlayerDAO.deletePlayer(playerEntity1);
        final Session session = DBConnection.getSession();
        final List<CurrentGroupEntity> currentGEntities = session.createQuery("from CurrentGroupEntity").list();
        session.close();
        CurrentGroupEntity currentGEntity = currentGEntities.get(0);
        CurrentGroupDAO.deleteCurrentGroup(currentGEntity.getId());
    }

    @Test
    public void login_with_4_players()
    {
        PlayerEntity playerEntity1 = new PlayerEntity();
        playerEntity1.setUserId("testPlayerLogin1");
        PlayerService.login(playerEntity1);
        PlayerEntity playerEntity2 = new PlayerEntity();
        playerEntity2.setUserId("testPlayerLogin2");
        PlayerService.login(playerEntity2);
        PlayerEntity playerEntity3 = new PlayerEntity();
        playerEntity3.setUserId("testPlayerLogin3");
        PlayerService.login(playerEntity3);
        PlayerEntity playerEntity4 = new PlayerEntity();
        playerEntity4.setUserId("testPlayerLogin4");
        PlayerService.login(playerEntity4);
        Assert.assertEquals(1, (int) playerEntity1.getGroupId());
        Assert.assertEquals(1, (int) playerEntity2.getGroupId());
        Assert.assertEquals(1, (int) playerEntity3.getGroupId());
        Assert.assertEquals(1, (int) playerEntity4.getGroupId());
        final Session session = DBConnection.getSession();
        final List<CurrentGroupEntity> currentGEntities = session.createQuery("from CurrentGroupEntity").list();
        session.close();
        CurrentGroupEntity currentGEntity = currentGEntities.get(0);
        Assert.assertEquals(2, currentGEntity.getId());
        Assert.assertEquals(0, (int) currentGEntity.getPlayerSum());
        PlayerDAO.deletePlayer(playerEntity1);
        PlayerDAO.deletePlayer(playerEntity2);
        PlayerDAO.deletePlayer(playerEntity3);
        PlayerDAO.deletePlayer(playerEntity4);
        CurrentGroupDAO.deleteCurrentGroup(currentGEntity.getId());
    }

    @Test
    public void login_the_4th_player_with_available_group()
    {
        AvailableGroupEntity availableGroupEntity = new AvailableGroupEntity();
        availableGroupEntity.setId(8);
        AvailableGroupDAO.insertAvailableGroup(availableGroupEntity);
        CurrentGroupEntity currentGroupEntity = new CurrentGroupEntity();
        currentGroupEntity.setId(6);
        currentGroupEntity.setPlayerSum(3);
        CurrentGroupDAO.insertCurrentGroup(currentGroupEntity);
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setUserId("testPlayerLogin");
        PlayerService.login(playerEntity);
        Assert.assertEquals(6, (int) playerEntity.getGroupId());
        final Session session = DBConnection.getSession();
        final List<CurrentGroupEntity> currentGEntities = session.createQuery("from CurrentGroupEntity").list();
        final List<AvailableGroupEntity> aGEntities = session.createQuery("from AvailableGroupEntity").list();
        session.close();
        currentGroupEntity = currentGEntities.get(0);
        Assert.assertEquals(8, currentGroupEntity.getId());
        Assert.assertTrue(aGEntities.isEmpty());
        PlayerDAO.deletePlayer(playerEntity);
        CurrentGroupDAO.deleteCurrentGroup(currentGroupEntity.getId());
    }
}