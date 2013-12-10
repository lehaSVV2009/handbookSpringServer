package com.kadet.handbook.server.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 28.08.13
 * Time: 4:31
 * To change this template use File | Settings | File Templates.
 */
public interface AbstractDAO<E, I extends Serializable> {

    E findById(I id);
    void saveOrUpdate(E e);
    boolean delete(I id);
    List<E> findAll();
}
