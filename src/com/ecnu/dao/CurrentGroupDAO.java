package com.ecnu.dao;

import com.ecnu.entities.CurrentGroupEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public final class CurrentGroupDAO
{

    private CurrentGroupDAO()
    {
    }

    public static void insertCurrentGroup(final CurrentGroupEntity cge)
    {
        final Session s = DBConnection.getSession();
        final Transaction t = s.beginTransaction();

        s.save(cge);

        t.commit();
        s.close();
    }

    /**
     * delete by group_id
     */
    public static void deleteCurrentGroup(final int groupId)
    {
        final Session s = DBConnection.getSession();
        final Transaction t = s.beginTransaction();

        final Query q = s.createQuery("delete from CurrentGroupEntity where id = ?");
        q.setParameter(0, groupId);
        q.executeUpdate();

        t.commit();
        s.close();
    }
}
