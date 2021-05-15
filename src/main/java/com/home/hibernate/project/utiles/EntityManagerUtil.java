package com.home.hibernate.project.utiles;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("HibernateReunion");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }
}
