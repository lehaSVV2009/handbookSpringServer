package com.kadet.handbook.server.service.impl;

import com.kadet.handbook.server.dao.ChapterDAO;
import com.kadet.handbook.server.entity.Chapter;
import com.kadet.handbook.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 25.08.13
 * Time: 5:51
 * To change this template use File | Settings | File Templates.
 */
@org.springframework.stereotype.Service("chapterService")
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    @Transactional
    public void saveOrUpdate (Chapter chapter) {
        chapterDAO.saveOrUpdate(chapter);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chapter> findAll () {
        return chapterDAO.findAll();
    }

    @Override
    @Transactional
    public boolean delete (Long id) {
        return chapterDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Chapter findById (Long id) {
        return chapterDAO.findById(id);
    }

}
