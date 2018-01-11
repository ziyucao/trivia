package com.ecnu.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author hibernate
 */
public final class DBConnection
{
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            SESSION_FACTORY = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private DBConnection()
    {
    }

    public static Session getSession() throws HibernateException {
        return SESSION_FACTORY.openSession();
    }

}