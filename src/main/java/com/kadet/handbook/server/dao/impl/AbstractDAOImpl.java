package com.kadet.handbook.server.dao.impl;

import com.kadet.handbook.server.dao.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 28.08.13
 * Time: 4:34
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDAOImpl<E, I extends Serializable> implements AbstractDAO<E,I> {

    private Class<E> entityClass;

    protected AbstractDAOImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession () {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public E findById(I id) {
        return (E) getSession().get(entityClass, id);
    }

    @Override
    public void saveOrUpdate(E e) {
        getSession().saveOrUpdate(e);
    }

    @Override
    public boolean delete(I id) {
        E ent = (E) getSession().load(entityClass, id);
        if (ent != null) {
            getSession().delete(ent);
            return true;
        }
        return false;
    }

    @Override
    public List<E> findAll () {
        return getSession()
                .createCriteria(entityClass)
                .list();
    }
}