package com.kadet.handbook.server.dao.impl;

import com.kadet.handbook.server.dao.ChapterDAO;
import com.kadet.handbook.entity.Chapter;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 4:11
 * To change this template use File | Settings | File Templates.
 */
@Repository("chapterDAO")
public class ChapterDAOImpl extends AbstractDAOImpl<Chapter, Integer> implements ChapterDAO {


    private final static String CHAPTER_TABLE_NAME = "Chapter";

    private final static String[] COLUMN_NAMES = {
            "id",
            "title",
            "text"
    };

    private final static Class[] COLUMN_TYPES = {
            Integer.class,
            String.class,
            String.class
    };

    private static final Integer ID_COLUMN = 0;


    @Override
    protected String getTableName() {
        return CHAPTER_TABLE_NAME;
    }

    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Integer getIdColumnNum() {
        return ID_COLUMN;
    }

    @Override
    protected Class[] getColumnTypes() {
        return COLUMN_TYPES;
    }

    @Override
    protected Object getValue(Integer columnNum, Chapter entity) {

        switch (columnNum) {
            case 0:
                return entity.getId();
            case 1:
                return entity.getTitle();
            case 2:
                return entity.getText();
        }
        return null;

    }

    @Override
    protected Chapter createInstance() {
        return new Chapter();
    }

    @Override
    protected void setEntityColumn(Chapter entity, int columnNum, Object value) {

        switch (columnNum) {
            case 0:
                entity.setId((Integer) value);
                return;
            case 1:
                entity.setTitle((String) value);
                return;
            case 2:
                entity.setText((String) value);
                return;
        }

    }

}
