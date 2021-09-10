/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims.functionality;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author bishal
 */
public final class CredentialController{
    
    private static CredentialController instance;
    
    private HashMap<Integer, Credential> credentials;
    private Credential credential;
    private File creds_file;

    private CredentialController() {
        credentials = new HashMap<>();       
    }
    
    public static CredentialController getInstance() {
        if(instance == null) {
            instance = new CredentialController();
        }
        return instance;
    }
    
    public void eraseCredential(){
        credential = null;
    }
    
    public boolean checkFileAvaibility() {
        creds_file = new File("src\\main\\java\\com\\bigc\\appliance_ims\\resource\\credentials.ser");                
        return creds_file.exists();
    }
    
    /**
     * 
     * @param user represents UserID.
     * @param password  represents password.
     * @param company represents current working company.
     * @return "True" if Account creation is successful, "False" if account is already present.
     */
    public boolean createAccount(String user, String password, String company) {
        int key = user.toLowerCase().hashCode();
        if(credentials.containsKey(key))
            return false;
        credential = new Credential(user, password, company);
        credentials.put(key, credential);
        return true;
    }
    
    public boolean authenticate(String user, String password) {
        credential = credentials.get(user.toLowerCase().hashCode());
        if(credential == null) return false;
        return credential.compare(new Credential(user, password));
    }
    
    public String[] getUserInfo() {
        if(credential == null) {
            System.err.println("No Credential!");
            return null;
        }
       return new String[]{credential.getUSER(), credential.getCOMPANY()};
    }
   
    public String getBackupCode() {
        return credential.getBackupCode();
    }
    public boolean verifyBackupcode(String user, String code){
        var creds = credentials.get(user.toLowerCase().hashCode());
        if(creds != null && creds.getBackupCode().equals(code)){
            credential = creds;
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param password takes a password string.
     * @return backupCode generated after changing password.
     */
    public String changePassword(String password){
        credential.setPASSWORD(password);
        return credential.getBackupCode();
    }
    
    
        
    @SuppressWarnings("unchecked")
    public void inCredential() throws FileNotFoundException, IOException, ClassNotFoundException{
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(creds_file))) {
            credentials = (HashMap<Integer,Credential>) in.readObject();
            System.out.println(credentials);
        }
    }
    
    public void outCredential() throws FileNotFoundException, IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(creds_file))) {
            out.writeObject(credentials);
            System.out.println(credentials);
        }
    }
}

final class Credential implements Serializable{

        private static final long serialVersionUID = -942376842;
        
        private final String user;
        private int password;
        private String backupCode;
        private String company;

        public Credential(String user, String password) {
            this.user = user.trim();
            this.password = password.trim().hashCode();
        }
        
        public Credential(String user, String password, String company) {
            this(user, password);
            this.company = company.trim();
            this.backupCode = this.generateBackupCode(15);
        }

        public String getUSER() {
            return user;
        }

        public String getBackupCode() {
            return backupCode;
        }

        public String getCOMPANY() {
            return company;
        }      
        
        public void setPASSWORD(String password) {
            this.password = password.hashCode();
            this.backupCode = generateBackupCode(15);
        }
        
        public boolean compare(Credential c) {
            System.out.println(c);
            System.out.println(this);
            return (this.password==c.password && this.user.equalsIgnoreCase(c.user));
        }
        
        private String generateBackupCode(int length) {
            Random r = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int keyCode = r.nextInt(123-30) + 30 ;
                sb.append(Character.toChars(keyCode));
            }
            return sb.toString();
        }

        @Override
        public String toString() {
            return "Credential{" + "USER=" + user + ", password=" + password + ", backupCode=" + backupCode + ", COMPANY=" + company + '}';
        }
    }
