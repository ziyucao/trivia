package com.ecnu.dao;

import com.ecnu.entities.PlayerEntity;
import com.ecnu.service.PlayerService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    public static void insertPlayer(PlayerEntity pe)
    {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();
        s.save(pe);
        t.commit();
        s.close();
    }

    /**
     * delete by user_id
     *
     * */
    public static void deletePlayer(PlayerEntity pe)
    {
        if (pe != null)
        {
            Session s = DBConnection.getSession();
            Transaction t = s.beginTransaction();

            Query q = s.createQuery("delete from PlayerEntity where id = ?");
            q.setParameter(0, pe.getUserId());
            q.executeUpdate();

            t.commit();
            s.close();
        }
    }

    public static void updatePlayer(PlayerEntity pe)
    {
        if (pe != null)
        {
            Session s = DBConnection.getSession();
            Transaction t = s.beginTransaction();

            s.update(pe);

            t.commit();
            s.close();
        }
    }

    public static PlayerEntity getPlayer(String id)
    {
        PlayerEntity pe = null;

        Session s = DBConnection.getSession();
        Query q = s.createQuery("from PlayerEntity where userId = ?");
        q.setParameter(0, id);
        List result = q.list();

        if (result != null && result.size() != 0) {
            pe = (PlayerEntity) result.get(0);
        }

        s.close();
        return pe;
    }

    public static ArrayList<PlayerEntity> getPlayersInGroup(PlayerEntity pe)
    {
        ArrayList<PlayerEntity> players = new ArrayList<>();

        Session s = DBConnection.getSession();
        if (pe != null)
        {
            int groupId = pe.getGroupId();
            Query q = s.createQuery("from PlayerEntity where groupId = ? order by idInGroup asc");
            q.setParameter(0, groupId);
            List result = q.list();

            if (result != null && result.size() != 0)
            {
                for (int i = 0; i < result.size(); i++)
                {
                    players.add((PlayerEntity)result.get(i));
                }
            }
        }

        s.close();
        return players;
    }

    public static String getIdFromGroupByIdInGroup(PlayerEntity pe, int idInGroup)
    {
        if (pe != null)
        {
            ArrayList<PlayerEntity> players = getPlayersInGroup(pe);
            if (players != null && players.size() != 0)
            {
                PlayerEntity target = null;
                for (int i = 0; i < players.size(); i++)
                {
                    if (idInGroup == players.get(i).getIdInGroup())
                    {
                        return players.get(i).getUserId();
                    }
                }
            }
        }
        return "";
    }

}
