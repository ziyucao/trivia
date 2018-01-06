package com.ecnu.dao;

import com.ecnu.entities.AvailableGroupEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public final class AvailableGroupDAO
{

    private AvailableGroupDAO()
    {
    }

    public static void insertAvailableGroup(final AvailableGroupEntity age)
    {
        final Session session = DBConnection.getSession();
        final Transaction transaction = session.beginTransaction();

        session.save(age);

        transaction.commit();
        session.close();
    }

    public static void deleteAvailableGroup(final int groupId)
    {
        final Session session = DBConnection.getSession();
        final Transaction transaction = session.beginTransaction();

        final Query query = session.createQuery("delete from AvailableGroupEntity where id = ?");
        query.setParameter(0, groupId);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }
}
