/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BasicClass.Account;
import Model.AccountModel;
import java.sql.SQLException;

/**
 *
 * @author Yue
 */
public class AccountChecker {
    
    public static final int LOGIN_SUCCESS = 1;
    public static final int INCORRECT_ACCOUNT = 2;
    public static final int INCORRECT_PASSWORD = 3;
    
    public static int checkAccount(String userString, String passwordString) throws SQLException {
        Account account = AccountModel.getAccount(userString);
        if (account != null) {
            if (account.getPassword().equals(passwordString)) {
                return LOGIN_SUCCESS;
            } else {
                return INCORRECT_PASSWORD;
            }
        } else {
            return INCORRECT_ACCOUNT;
        }
    }
}
