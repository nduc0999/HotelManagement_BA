/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yue
 */
public class DbConn {

    public static final String MYSQL_HOTELMANAGEMENT = "jdbc:mysql://localhost:3306/hotelmanagement";
    public static final String USER = "root";
    public static final String PASS = "1234567890";

    private static Connection conn = null;

    DbConn() {

    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(MYSQL_HOTELMANAGEMENT, USER, PASS);
            } catch (SQLException ex) {
                Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
