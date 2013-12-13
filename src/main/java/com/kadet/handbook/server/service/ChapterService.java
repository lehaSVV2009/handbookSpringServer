package com.kadet.handbook.server.service;


import com.kadet.handbook.entity.Chapter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 29.08.13
 * Time: 3:31
 * To change this template use File | Settings | File Templates.
 */
public interface ChapterService {

    public void saveOrUpdate(Chapter chapter);
    public List<Chapter> findAll();
    public boolean delete(Integer id);
    public Chapter findById(Integer id);

}
