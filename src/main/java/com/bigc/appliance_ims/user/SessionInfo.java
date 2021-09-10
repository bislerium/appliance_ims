/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims.user;

import java.sql.Connection;

/**
 *
 * @author bisha
 */
public class SessionInfo {
    
    private final int clientID;
    private final boolean authorized;
    private final boolean admin;
    private final Connection connection;

    public SessionInfo(int clientID, boolean authorized, boolean admin, Connection connection) {
        this.clientID = clientID;
        this.authorized = authorized;
        this.admin = admin;
        this.connection = connection;
    }

    public int getClientID() {
        return clientID;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public boolean isAdmin() {
        return admin;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public String toString() {
        return "UserSession{" + "userID=" + clientID + ", authorized=" + authorized + ", admin=" + admin + ", connection=" + connection + '}';
    }    
    
}
