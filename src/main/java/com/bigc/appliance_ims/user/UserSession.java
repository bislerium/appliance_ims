/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims.user;

/**
 *
 * @author bisha
 */
public class UserSession {
    private final SessionInfo sessionInfo;
    private final String companyName;
    private final String userName;

    public UserSession(SessionInfo sessionInfo, String companyName, String userName) {
        this.sessionInfo = sessionInfo;
        this.companyName = companyName;
        this.userName = userName;
    }

    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "SessionInfo{" + "userSession=" + sessionInfo + ", companyName=" + companyName + ", userName=" + userName + '}';
    }
    
}
