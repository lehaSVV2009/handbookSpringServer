package com.kadet.handbook.server.dao.impl;

import com.kadet.handbook.server.dao.ChapterDAO;
import com.kadet.handbook.server.entity.Chapter;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 4:11
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ChapterDAOImpl extends AbstractDAOImpl<Chapter, Long> implements ChapterDAO {

    protected ChapterDAOImpl () {
        super(Chapter.class);
    }

}
