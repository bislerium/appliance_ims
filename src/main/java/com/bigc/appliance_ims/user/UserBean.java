/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims.user;

import java.io.Serializable;
import java.sql.Date;
import java.util.Random;
import java.sql.Timestamp;

/**
 *
 * @author bisha
 */
public final class UserBean implements Serializable{

    private static final long serialVersionUID = 93879827239L;
        
    private int userID;
    private int userPWD;
    private boolean admin;
    private String userName;
    private String gender;
    private Date dOB;
    private Timestamp regDate;
    private String Company;
    private String recoveryCode;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserPWD() {
        return userPWD;
    }

    public void setUserPWD(int userPWD) {
        this.userPWD = userPWD;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getdOB() {
        return dOB;
    }

    public void setdOB(Date dOB) {
        this.dOB = dOB;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public String getRecoveryCode() {
        return recoveryCode;
    }

    public void setRecoveryCode(String recoveryCode) {
        this.recoveryCode = recoveryCode;
    }
    
    public static String generateRecoveryCode(int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int keyCode = 48 + r.nextInt(123-48) ;
            sb.append(Character.toChars(keyCode));
        }
        return sb.toString();
    }

    // For Bean Testing
    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", password=" + userPWD 
                + ", role=" + admin + ", name=" + userName + ", gender=" + gender 
                + ", dOB=" + dOB + ", registeredDate=" + regDate 
                + ", Company=" + Company + ", recoveryCode=" + recoveryCode + '}';
    }    
}
