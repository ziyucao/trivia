package com.ecnu.service;

import com.ecnu.dao.*;
import com.ecnu.entities.AvailableGroupEntity;
import com.ecnu.entities.CurrentGroupEntity;
import com.ecnu.entities.PlayerEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {

    public static boolean login(PlayerEntity pe)
    {
        boolean result = true;
        Session s = DBConnection.getSession();

        pe.setCoins(0);
        pe.setPosition(0);
        pe.setIsAnswering(0);
        pe.setIsPunished(0);

        String hql = "select userId from PlayerEntity where userId = ?";
        Query q = s.createQuery(hql);
        q.setParameter(0, pe.getUserId());
        List l = q.list();

        if (l == null || l.size() == 0) {

            List<CurrentGroupEntity> cg = s.createQuery("from CurrentGroupEntity").list();
            CurrentGroupEntity cge = null;
            if (cg == null || cg.size() == 0) {
                cge = new CurrentGroupEntity();
                cge.setId(1);
                cge.setPlayerSum(1);
                pe.setGroupId(1);
                pe.setIdInGroup(1);
                s.save(cge);
            } else {
                cge = cg.get(0);
                int oldId = cge.getId();
                pe.setGroupId(cge.getId());
                pe.setIdInGroup(cge.getPlayerSum() + 1);

                if (cge.getPlayerSum() == 3) {
                    List<AvailableGroupEntity> al = s.createQuery("select id from AvailableGroupEntity ").list();
                    if (al == null || al.size() == 0)
                        cge.setId(cge.getId() + 1);
                    else {
                        cge.setId(al.get(0).getId());

                        AvailableGroupDAO.deleteAvailableGroup(cge.getId());
                    }
                    cge.setPlayerSum(0);
                }
                else {
                    cge.setPlayerSum(cge.getPlayerSum() + 1);
                }
                /**
                 * update 不能修改主键
                 */
//                Transaction t = s.beginTransaction();
//                hql = "update CurrentGroupEntity as c set id = ?, playerSum = ? where id = ?";
//                q = s.createQuery(hql);
//                q.setParameter(0, oldId);
//                q.setParameter(1, cge.getId());
//                q.setParameter(2, cge.getPlayerSum());
//                q.executeUpdate();
//                t.commit();

                CurrentGroupDAO.deleteCurrentGroup(oldId);
            }
            CurrentGroupDAO.insertCurrentGroup(cge);
            PlayerDAO.insertPlayer(pe);
        }
        else
        {
            result = false;
        }
        s.close();
        return result;
    }

    /**
     * return value > 0 : (game is end)the index of players in group
     * return value = -1 : game is not end
     *
     * */
    public static int gameIsEnd(PlayerEntity pe)
    {
        ArrayList<PlayerEntity> players = PlayerDAO.getPlayersInGroup(pe);
        if (players != null)
        {
            for (int i = 0; i < players.size(); i++)
            {
                if (players.get(i) != null && players.get(i).getCoins() == 6)
                    return i;
            }
        }
        return -1;
    }

    public static void updateState(PlayerEntity pe, int questionId, String answer)
    {
        if (pe != null)
        {
            boolean isAnswerCorrrect = QuestionDAO.checkAnswer(questionId, answer);
            if (isAnswerCorrrect)
            {
                pe.setCoins(pe.getCoins() + 1);
            }
            else
            {
                pe.setIsPunished(1);
            }
        }
    }
}
