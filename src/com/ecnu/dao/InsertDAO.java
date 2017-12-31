package com.ecnu.dao;

import com.ecnu.entities.CurrentGroupEntity;
import com.ecnu.entities.PlayerEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class InsertDAO {

    public static void insertPlayer(PlayerEntity pe)
    {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();

        s.save(pe);

        t.commit();
        s.close();
    }

    public static void insertCurrentGroup(CurrentGroupEntity cge)
    {
        Session s = DBConnection.getSession();
        Transaction t = s.beginTransaction();

        s.save(cge);

        t.commit();
        s.close();
    }
}
