package com.ecnu.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DeleteDAO {

    public static void deleteAvailableGroup(int groupId)
    {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();

        Query q = s.createQuery("delete from AvailableGroupEntity where id = ?");
        q.setParameter(0, groupId);
        q.executeUpdate();

        t.commit();
        s.close();
    }

    public static void deleteCurrentGroup(int groupId)
    {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();

        Query q = s.createQuery("delete from CurrentGroupEntity where id = ?");
        q.setParameter(0, groupId);
        q.executeUpdate();

        t.commit();
        s.close();
    }
}
