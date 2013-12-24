package com.kadet.handbook.server.dao.impl;

import com.kadet.handbook.entity.Entity;
import com.kadet.handbook.server.dao.AbstractDAO;
import com.kadet.handbook.server.util.MysqlManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 28.08.13
 * Time: 4:34
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDAOImpl<E extends Entity, I extends Serializable> implements AbstractDAO<E, I> {

    @Autowired
    private MysqlManager mysqlManager;

    private boolean checkResult(int result) {
        return result == -1 ? false : true;
    }

    public boolean save(E entity) {

        int result = -1;
        String query = createSaveQuery(
                getTableName(),
                getColumnNames()
        );
        try {
//            Connection connection = MysqlManager.getInstance().getConnection();
            Connection connection = mysqlManager.getConnection();
            PreparedStatement statement
                    = connection.prepareStatement(query);
            setPreparedStatement(
                    statement,
                    entity,
                    getColumnNames(),
                    getColumnTypes()
            );
            result = statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            result = -1;
            MysqlManager.log(e);
        }
        return checkResult(result);

    }

    private String createSaveQuery(String tableName, String[] columnNames) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ")
                .append(tableName)
                .append("(")
                .append(columnNames[0]);
        for (int columnNum = 1; columnNum < columnNames.length; ++columnNum) {
            query.append(", ")
                    .append(columnNames[columnNum]);
        }
        query.append(") VALUES(?");
        for (int columnNum = 1; columnNum < columnNames.length; ++columnNum) {
            query.append(",?");
        }
        query.append(")");
        return query.toString();
    }

    public boolean update(E entity) {

        int result = -1;
        String query = createUpdateQuery(
                getTableName(),
                getColumnNames(),
                getIdColumnName()
        );
        try {
//            Connection connection = MysqlManager.getInstance().getConnection();
            Connection connection = mysqlManager.getConnection();
            PreparedStatement statement
                    = connection.prepareStatement(query);
            setPreparedStatement(
                    statement,
                    entity,
                    getColumnNames(),
                    getColumnTypes()
            );
            int idPreparedStatementIndex = getColumnNames().length + 1;
            setPreparedStatementTypes(
                    statement,
                    getValue(getIdColumnNum(), entity),
                    idPreparedStatementIndex,
                    getIdColumnType()
            );
            result = statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            result = -1;
            MysqlManager.log(e);
        }
        return checkResult(result);

    }

    private String createUpdateQuery(String tableName, String[] columnNames, String idColumnName) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(columnNames[0])
                .append(" = ? ");
        for (int columnNum = 1; columnNum < columnNames.length; ++columnNum) {
            query.append(", ")
                    .append(columnNames[columnNum])
                    .append(" = ? ");
        }
        query.append(" WHERE ")
                .append(idColumnName)
                .append(" = ?");
        return query.toString();
    }

    private void setPreparedStatement(PreparedStatement statement, E entity, String[] columnNames, Class[] columnTypes) throws SQLException {
        for (int columnNum = 0; columnNum < columnNames.length; ++columnNum) {
            int columnIndex = columnNum + 1;
            setPreparedStatementTypes(
                    statement,
                    getValue(columnNum, entity),
                    columnIndex,
                    columnTypes[columnNum]
            );
        }
    }

    private void setPreparedStatementTypes(PreparedStatement statement, Object value, Integer columnIndex, Class columnType) throws SQLException {
        if (String.class.equals(columnType)) {
            statement.setString(columnIndex, (String) value);
            return;
        }
        if (Integer.class.equals(columnType)) {
            statement.setInt(columnIndex, (Integer) value);
            return;
        }
        if (Double.class.equals(columnType)) {
            statement.setDouble(columnIndex, (Double) value);
        }
        if (Date.class.equals(columnType)) {
            statement.setDate(columnIndex, (Date) value);
            return;
        }
    }

    public E findByEntity(E entity) {
        List<E> content = findAll();
        int idColumn = getIdColumnNum();
        Object id = getValue(idColumn, entity);
        for (E contentEntity : content) {
            Object contentEntityId = getValue(idColumn, contentEntity);
            if (id.equals(contentEntityId)) {
                return contentEntity;
            }
        }
        return null;
    }

    public E findById(I id) {

        List<E> content = findAll();
        for (E entity : content) {
            Object entityId
                    = getValue(getIdColumnNum(), entity);
            if (id.equals(entityId)) {
                return entity;
            }
        }
        return null;

    }

    private String createDeleteQuery(String tableName, String idColumnName) {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ")
                .append(tableName)
                .append(" WHERE ")
                .append(idColumnName)
                .append(" = ? ");
        return query.toString();
    }

    public boolean delete(E entity) {
        return delete((I)entity.getId());
        /*int result = -1;
        String query = createDeleteQuery(getTableName(), getIdColumnName());
        try {
            Connection connection = MysqlManager.getInstance().getConnection();
            PreparedStatement statement
                    = connection.prepareStatement(query);
            int statementIndex = 1;
            setPreparedStatementTypes(
                    statement,
                    getValue(getIdColumnNum(), entity),
                    statementIndex,
                    getIdColumnType()
            );
            result = statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            result = -1;
            MysqlManager.log(e);
        }
        return result;*/
    }

    public boolean delete (I id) {

        int result = -1;
        String query = createDeleteQuery(getTableName(), getIdColumnName());
        try {
//            Connection connection = MysqlManager.getInstance().getConnection();
            Connection connection = mysqlManager.getConnection();
            PreparedStatement statement
                    = connection.prepareStatement(query);
            int statementIndex = 1;
            setPreparedStatementTypes(
                    statement,
                    id,
                    statementIndex,
                    getIdColumnType()
            );
            result = statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            result = -1;
            MysqlManager.log(e);
        }
        return checkResult(result);

    }

    public List<E> findAll() {

        List<E> result = new ArrayList<E>();
        String query = createSelectAllQuery(getTableName());
        try {
//            Connection connection = MysqlManager.getInstance().getConnection();
            Connection connection = mysqlManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            result
                    = getAllFromResultSet(resultSet);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            result.clear();
            MysqlManager.log(e);
        }
        return result;

    }

    private String createSelectAllQuery(String tableName) {
        return new StringBuilder()
                .append("SELECT * FROM ")
                .append(tableName)
                .toString();
    }

    private List<E> getAllFromResultSet(ResultSet resultSet) throws SQLException {
        List<E> result = new ArrayList<E>();
        Class[] columnTypes = getColumnTypes();
        String[] columnNames = getColumnNames();
        while (resultSet.next()) {
            E entity = createInstance();
            for (int columnNum = 0; columnNum < columnTypes.length; ++columnNum) {
                setEntityColumn(
                        entity,
                        columnNum,
                        getColumnFromResultSet(resultSet, columnTypes[columnNum], columnNames[columnNum])
                );
            }
            result.add(entity);
        }
        return result;
    }

    private Object getColumnFromResultSet(ResultSet resultSet, Class columnType, String columnName) throws SQLException {
        if (String.class.equals(columnType)) {
            return resultSet
                    .getString(columnName);
        }
        if (Integer.class.equals(columnType)) {
            return resultSet
                    .getInt(columnName);
        }
        if (Double.class.equals(columnType)) {
            return resultSet
                    .getDouble(columnName);
        }
        if (Date.class.equals(columnType)) {
            return resultSet
                    .getDate(columnName);
        }
        return new Object();
    }

    private Class getIdColumnType() {
        return getColumnTypes()[getIdColumnNum()];
    }

    private String getIdColumnName() {
        return getColumnNames()[getIdColumnNum()];
    }

    abstract protected String getTableName();

    abstract protected String[] getColumnNames();

    abstract protected Integer getIdColumnNum();

    abstract protected Class[] getColumnTypes();

    abstract protected Object getValue(Integer columnNum, E entity);

    abstract protected E createInstance();

    abstract protected void setEntityColumn(E entity, int columnNum, Object value);

/*
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
        for (E e : list) {
            if (((Chapter)e).getId() == id) {
                return e;
            }
        }
        return null;
//        return (E) getSession().get(entityClass, id);
    }

    @Override
    public void update(E e) {
        list.add(e);
//        getSession().update(e);
    }

    @Override
    public boolean delete(I id) {
        Integer integerId = (Integer) id;
        for (E e : list) {
            if (((Chapter)e).getId() == id) {
                list.remove(e);
            }
        }
//        E ent = (E) getSession().load(entityClass, id);
//        if (ent != null) {
//            getSession().delete(ent);
//            return true;
//        }
//        return false;
        return true;
    }

    @Override
    public List<E> findAll () {
        return list;
//        return getSession()
//                .createCriteria(entityClass)
//                .list();
    }
*/
}