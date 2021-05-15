package com.home.hibernate.project.dao;

import com.home.hibernate.project.dominio.Sala;


import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class SalaDao extends AbstractDao<Sala>{

    public SalaDao() {
        settClass(Sala.class);
    }

    public List<Sala> findSalasParaNumOld(int number) {
        String qlString = "FROM " + Sala.class.getName() + " WHERE capacidad >= ?1";
        Query query = getEntityManager().createQuery(qlString);
        query.setParameter(1, number);
        return query.getResultList();
    }

    public List<Sala> findSalasParaNum(int num) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Sala> criteriaQuery = criteriaBuilder.createQuery(Sala.class);
        Root<Sala> root = criteriaQuery.from(Sala.class);
        criteriaQuery.select(root).where(criteriaBuilder.ge(root.get("capacidad"), num));
        Query query = getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Sala> findSalasParaNumUpdated(int num) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Sala> criteriaQuery = criteriaBuilder.createQuery(Sala.class);
        Root<Sala> root = criteriaQuery.from(Sala.class);

        Predicate minCapacity = criteriaBuilder.ge(root.get("capacidad"), num);
        Predicate maxCapacity = criteriaBuilder.lessThanOrEqualTo(root.get("capacidad"), num * 2);
        Predicate capacityRange = criteriaBuilder.and(minCapacity, maxCapacity);

        criteriaQuery.select(root).where(capacityRange);
        Query query = getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }
}
