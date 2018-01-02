package com.ecnu.dao;

import com.ecnu.entities.PlayerEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ding Donglai
 */
public final class PlayerDAO
{

    private PlayerDAO()
    {
    }

    /**
     * @param playerEntity : player you want to insert
     */
    public static void insertPlayer(final PlayerEntity playerEntity)
    {
        final Session session = DBConnection.getSession();
        final Transaction transaction = session.beginTransaction();
        session.save(playerEntity);
        transaction.commit();
        session.close();
    }

    /**
     * delete by playerEntity.userId
     *
     * @param playerEntity : player you want to delete
     */
    public static void deletePlayer(final PlayerEntity playerEntity)
    {
        if (playerEntity != null)
        {
            final Session session = DBConnection.getSession();
            final Transaction transaction = session.beginTransaction();

            final Query query = session.createQuery("delete from PlayerEntity where id = ?");
            query.setParameter(0, playerEntity.getUserId());
            query.executeUpdate();

            transaction.commit();
            session.close();
        }
    }

    /**
     * @param playerEntity : player you want to update
     */
    public static void updatePlayer(final PlayerEntity playerEntity)
    {
        if (playerEntity != null)
        {
            final Session session = DBConnection.getSession();
            final Transaction transaction = session.beginTransaction();

            session.update(playerEntity);

            transaction.commit();
            session.close();
        }
    }

    /**
     * @param playerId : userId of Player who you want
     */
    public static PlayerEntity getPlayer(final String playerId)
    {
        PlayerEntity playerEntity = null;

        final Session session = DBConnection.getSession();
        final Query query = session.createQuery("from PlayerEntity where userId = ?");
        query.setParameter(0, playerId);
        final List result = query.list();

        if (result != null && !result.isEmpty())
        {
            playerEntity = (PlayerEntity) result.get(0);
        }

        session.close();
        return playerEntity;
    }

    /**
     * @param playerEntity : the player in the group that you want
     */
    public static List<PlayerEntity> getPlayersInGroup(final PlayerEntity playerEntity)
    {
        final List<PlayerEntity> players = new ArrayList<>();

        final Session session = DBConnection.getSession();
        if (playerEntity != null)
        {
            final int groupId = playerEntity.getGroupId();
            final Query query = session.createQuery("from PlayerEntity where groupId = ? order by idInGroup asc");
            query.setParameter(0, groupId);
            final List result = query.list();

            if (result != null && !result.isEmpty())
            {
                for (final Object resultPlayer : result)
                {
                    players.add((PlayerEntity) resultPlayer);
                }
            }
        }
        session.close();
        return players;
    }

}
