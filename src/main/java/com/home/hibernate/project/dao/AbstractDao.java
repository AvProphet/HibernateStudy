package com.home.hibernate.project.dao;

import com.home.hibernate.project.utiles.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class AbstractDao<T> implements Dao<T> {

    private final EntityManager entityManager = EntityManagerUtil.getEntityManager();
    private Class<T> tClass;

    @Override
    public Optional<T> get(long id) {
        return Optional.ofNullable(entityManager.find(tClass, id));
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<T> getAll() {
        String qlString = "FROM " + tClass.getName();
        Query query = entityManager.createQuery(qlString);
        return query.getResultList();
    }

    @Override
    public void save(T t) {
        executeInsideTransaction(entityManager -> entityManager.persist(t));
    }

    @Override
    public void update(T t) {
        executeInsideTransaction(entityManager -> entityManager.merge(t));
    }

    @Override
    public void delete(T t) {
        executeInsideTransaction(entityManager -> entityManager.remove(t));
    }

    public void settClass(Class<T> tClass) {
        this.tClass = tClass;
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
