/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims.user;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author bisha
 */
public interface DAO {
    
    Optional<UserBean> getUser(int id) throws SQLException;
    List<UserBean> getAllUsers() throws SQLException; // R - Read and fetch all the rows from the table(s)
    boolean createUser(UserBean user);   // C - Create a new row of Data and insert it in table(s)
    boolean updateUser(UserBean user);   //U - Update a row of Data in table(s)
    boolean deleteUser(int id);    //D - Delete a row of Data in table(s)
}
