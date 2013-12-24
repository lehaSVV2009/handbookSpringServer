package com.kadet.handbook.server.service.impl;

import com.kadet.handbook.server.dao.ChapterDAO;
import com.kadet.handbook.entity.Chapter;
import com.kadet.handbook.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;

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
    public boolean save(Chapter chapter) {
        return chapterDAO.save(chapter);
    }

    @Override
    public boolean update(Chapter chapter) {
        return chapterDAO.update(chapter);
    }

    @Override
    public List<Chapter> findAll () {
        return chapterDAO.findAll();
    }

    @Override
    public boolean delete (Integer id) {
        return chapterDAO.delete(id);
    }

    @Override
    public Chapter findById (Integer id) {
        return chapterDAO.findById(id);
    }

}
