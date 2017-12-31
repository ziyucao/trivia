package com.ecnu.dao;

import com.ecnu.entities.AvailableGroupEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class AvailableGroupDAO {

    public static void insertAvailableGroup(AvailableGroupEntity age)
    {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();

        s.save(age);

        t.commit();
        s.close();
    }

    public static void deleteAvailableGroup(int groupId) {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();

        Query q = s.createQuery("delete from AvailableGroupEntity where id = ?");
        q.setParameter(0, groupId);
        q.executeUpdate();

        t.commit();
        s.close();
    }
}
