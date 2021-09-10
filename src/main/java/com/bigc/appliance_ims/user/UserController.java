/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims.user;

import com.bigc.appliance_ims.db.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author bisha
 */
public class UserController {
    
    private final DAO userDAO;
    private final Connection connection;

    public UserController(Connection connection) {
        this.connection = connection;
        this.userDAO = new UserDAO(connection);
    }
    
    
    public Optional<UserSession> Authenticate(int id, String password){
        UserSession userSession = null;
        try {
            Optional<UserBean> optUser = userDAO.getUser(id);
            if (optUser.isEmpty())
                return null;
            UserBean user = optUser.get();
            if (user.getUserPWD() == password.hashCode()) {
                SessionInfo session;                
                if (user.isAdmin()){
                    session = new SessionInfo(id, true, true, connection);
                } else {
                    session = new SessionInfo(id, true, false, connection);                
                }                    
                userSession = new UserSession(session, user.getCompany(), user.getUserName());
            }
        } catch (SQLException ex) {
            DBConnection.printSQLException(ex);
        }
//        JOptionPane.showMessageDialog(null, "Wrong Credentials", "Either User-ID or Password is Incorrect!", JOptionPane.WARNING_MESSAGE);
        return Optional.ofNullable(userSession);
    }   
}
