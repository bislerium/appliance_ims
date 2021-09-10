/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims;

import com.bigc.appliance_ims.gui.DBConfig;
import com.bigc.appliance_ims.gui.IMSFrame;
import com.bigc.appliance_ims.gui.LoginFrame;
import com.bigc.appliance_ims.user.SessionInfo;
import com.bigc.appliance_ims.user.UserSession;
import java.sql.Connection;

/**
 *
 * @author bisha
 */
public class Main {
    
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void runIMSFrame() throws Exception {
        SessionInfo si = new SessionInfo(100023, true, true, connection);
        UserSession su = new UserSession(si, "Neprimo Inc.", "Bishal GC");       
        new IMSFrame(su).setVisible(true);
    }

    public static void main(String args[]) throws Exception {            
        Main main = new Main();
        DBConfig dbConfig = new DBConfig(main);
        dbConfig.runFrame();
    }
    
}
