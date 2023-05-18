package com.gb.hw5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtils {
    private static SessionFactory sessionFactory;
    private static SessionFactoryUtils sessionFactoryUtils;

    private SessionFactoryUtils() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static SessionFactoryUtils getSessionFactoryUtils() {
        if (sessionFactoryUtils == null) {
            sessionFactoryUtils = new SessionFactoryUtils();
        }
        return sessionFactoryUtils;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
