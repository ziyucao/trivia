package com.ecnu.dao;

import com.ecnu.entities.CurrentGroupEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CurrentGroupDAO {

    public static void insertCurrentGroup(CurrentGroupEntity cge)
    {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();

        s.save(cge);

        t.commit();
        s.close();
    }

    /**
     * delete by group_id
     *
     * */
    public static void deleteCurrentGroup(int groupId) {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();

        Query q = s.createQuery("delete from CurrentGroupEntity where id = ?");
        q.setParameter(0, groupId);
        q.executeUpdate();

        t.commit();
        s.close();
    }
}
