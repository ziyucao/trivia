package com.ecnu.service;

import com.ecnu.dao.*;
import com.ecnu.entities.CurrentGroupEntity;
import com.ecnu.entities.PlayerEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Ding Donglai
 */
public final class PlayerService
{

    private PlayerService()
    {
    }

    public static boolean login(final PlayerEntity playerEntity)
    {
        boolean result = true;
        final Session session = DBConnection.getSession();

        playerEntity.setCoins(0);
        playerEntity.setPosition(0);
        playerEntity.setIsAnswering(0);
        playerEntity.setIsPunished(0);

        final String hql = "select userId from PlayerEntity where userId = ?";
        final Query query = session.createQuery(hql);
        query.setParameter(0, playerEntity.getUserId());
        final List list = query.list();

        if (list == null || list.isEmpty())
        {

            final List<CurrentGroupEntity> currentGEntities = session.createQuery("from CurrentGroupEntity").list();
            CurrentGroupEntity currentGEntity = null;
            if (currentGEntities == null || currentGEntities.isEmpty())
            {
                currentGEntity = new CurrentGroupEntity();
                currentGEntity.setId(1);
                currentGEntity.setPlayerSum(1);
                playerEntity.setGroupId(1);
                playerEntity.setIdInGroup(1);
                session.save(currentGEntity);
            } else
            {
                currentGEntity = currentGEntities.get(0);
                final int oldId = currentGEntity.getId();
                playerEntity.setGroupId(currentGEntity.getId());
                playerEntity.setIdInGroup(currentGEntity.getPlayerSum() + 1);

                if (currentGEntity.getPlayerSum() == 3)
                {
                    final List<Integer> aGEntities = session.createQuery("select id from AvailableGroupEntity").list();
                    if (aGEntities == null || aGEntities.isEmpty())
                    {
                        currentGEntity.setId(currentGEntity.getId() + 1);
                    } else
                    {
                        currentGEntity.setId(aGEntities.get(0));

                        AvailableGroupDAO.deleteAvailableGroup(currentGEntity.getId());
                    }
                    currentGEntity.setPlayerSum(0);
                } else
                {
                    currentGEntity.setPlayerSum(currentGEntity.getPlayerSum() + 1);
                }
                CurrentGroupDAO.deleteCurrentGroup(oldId);
            }
            CurrentGroupDAO.insertCurrentGroup(currentGEntity);
            if (playerEntity.getIdInGroup() == 1)
            {
                playerEntity.setIsAnswering(1);
            }
            PlayerDAO.insertPlayer(playerEntity);
        } else
        {
            result = false;
        }
        session.close();
        return result;
    }
}
