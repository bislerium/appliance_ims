/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bisha
 */
public final class DBConnection {
  
    private Connection connection;

    //Default Connection Configuration
    public DBConnection() throws SQLException {
        this(3306, "appliance_ims", "root", "root");
    }

    public DBConnection(int port, String databaseName, String userName, String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/" + databaseName, userName, password);
    }
    
    public Connection getConnection() {
        return connection;
    }
        
    public static void printSQLException(SQLException ex) {
        System.err.println("Message : " + ex.getMessage());
        System.err.println("\t SQL State : " + ex.getSQLState());
        System.err.println("\t Error Code : " + ex.getErrorCode());
        System.out.println("-------------------------------------------------");
        ex.printStackTrace(System.out);
        System.out.println("-------------------------------------------------");        
    }
}
