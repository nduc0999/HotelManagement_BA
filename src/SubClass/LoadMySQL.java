/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubClass;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yue
 */
public class LoadMySQL {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Loading driver...");

        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded!");

    }
}
