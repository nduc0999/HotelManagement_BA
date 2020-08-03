/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BasicClass.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author Yue
 */
public class AccountModel {

    private static Account account = null;

    private AccountModel() {

    }

    public static Account getAccount(String userString) throws SQLException {
        if (account == null) {
            Connection conn = DriverManager.getConnection(DbConn.MYSQL_HOTELMANAGEMENT, DbConn.USER, DbConn.PASS);
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM nguoidung WHERE user = '" + userString + "'");
            if (rs.next()) {
                account = new Account(rs.getString(1), rs.getString(2));
            }
            conn.close();
            return account;
        }
        return account;
    }

//    public Account getAccount(String userString) throws SQLException {
//        Connection conn = DriverManager.getConnection(DbConn.MYSQL_HOTELMANAGEMENT, DbConn.USER, DbConn.PASS);
//        Statement ps = conn.createStatement();
//        ResultSet rs = ps.executeQuery("SELECT * FROM nguoidung WHERE user = '" + userString + "'");
//        if (rs.next()) {
//            account = new Account(rs.getString(1), rs.getString(2));
//        }
//        conn.close();
//        return account;
//    }
}
