package com.kadet.handbook.server.dao.impl;

import com.kadet.handbook.server.dao.AbstractDAO;
import com.kadet.handbook.entity.Chapter;

import java.io.Serializable;
import java.util.ArrayList;
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

        this.list = new ArrayList<E>();
        fillList(list);
    }

    private List<E> list;

    private void fillList (List<E> list) {
        for (int i = 0; i < 10; ++i) {
            list.add(
                    (E) createChapter(i, "title" + i, "text" + i)
            );
        }
    }

    private Chapter createChapter (Integer id, String title, String text) {
        Chapter chapter
                = new Chapter(title, text);
        chapter.setId(id);
        return chapter;
    }

//    @Autowired
//    private SessionFactory sessionFactory;

//    public Session getSession () {
//        return sessionFactory.getCurrentSession();
//    }

    @Override
    public E findById(I id) {
        Integer integerId = (Integer) id;
        return list.get(integerId);
//        return (E) getSession().get(entityClass, id);
    }

    @Override
    public void saveOrUpdate(E e) {
        list.add(e);
//        getSession().saveOrUpdate(e);
    }

    @Override
    public boolean delete(I id) {
        Integer integerId = (Integer) id;
        return list.remove(integerId);
//        E ent = (E) getSession().load(entityClass, id);
//        if (ent != null) {
//            getSession().delete(ent);
//            return true;
//        }
//        return false;
    }

    @Override
    public List<E> findAll () {
        return list;
//        return getSession()
//                .createCriteria(entityClass)
//                .list();
    }
}