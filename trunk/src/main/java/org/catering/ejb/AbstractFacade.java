/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catering.ejb;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int from, int to) {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery cq = criteriaBuilder.createQuery();
        cq.select(cq.from(entityClass));
        final Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(to - from);
        q.setFirstResult(from);
        return q.getResultList();
    }

    public List<T> findRange(int from, int to, String sortField, boolean isAscending) {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery cq = criteriaBuilder.createQuery();
        final Root root = cq.from(entityClass);
        cq.select(root);
        final Path sortFieldExpr = root.get(sortField);
        final Order order = isAscending ? criteriaBuilder.asc(sortFieldExpr) : criteriaBuilder.desc(sortFieldExpr);
        cq.orderBy(order);
        final Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(to - from);
        q.setFirstResult(from);
        return q.getResultList();
    }

    public int count() {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery cq = criteriaBuilder.createQuery();
        final Root<T> root = cq.from(entityClass);
        cq.select(criteriaBuilder.count(root));
        final Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
