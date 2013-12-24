package com.kadet.handbook.server.util;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 15.10.13
 * Time: 1:56
 * To change this template use File | Settings | File Templates.
 */
@Component("mysqlManager")
public class MysqlManager {

//    private static MysqlManager instance;

    private MysqlManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            Logger.getLogger(MysqlManager.class.getName()).log(Level.SEVERE, null, cnfe);
        }
    }


    public java.sql.Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/fxdb", "root", "root");
        } catch (SQLException e) {
            log(e);
            return null;
        }
    }

    private final static Logger logger = Logger.getLogger(MysqlManager.class.getName());

    public static void log (Exception exception) {
        logger.log(Level.SEVERE, null, exception);
    }

    public static void log (String message) {
        logger.log(Level.SEVERE, message);
    }

}


