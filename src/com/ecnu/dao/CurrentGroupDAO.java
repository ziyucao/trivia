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
        final Session session = DBConnection.getSession();
        final Transaction transaction = session.beginTransaction();

        session.save(cge);

        transaction.commit();
        session.close();
    }

    /**
     * delete by group_id
     */
    public static void deleteCurrentGroup(final int groupId)
    {
        final Session session = DBConnection.getSession();
        final Transaction transaction = session.beginTransaction();

        final Query query = session.createQuery("delete from CurrentGroupEntity where id = ?");
        query.setParameter(0, groupId);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }
}
