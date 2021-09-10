/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims.appliance;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

/**
 *
 * @author bishal
 */
public interface DAO {
    
    int getMaxID();
    Optional<ApplianceBean> getUser(int id) throws SQLException;
    HashMap<Integer, ApplianceBean> getAllAppliances(); // R - Read and fetch all the rows from the table(s)
    boolean createAppliance(ApplianceBean appliance);   // C - Create a new row of Data and insert it in table(s)
    boolean updateAppliance(ApplianceBean appliance);   //U - Update a row of Data in table(s)
    boolean deleteAppliance(int id);    //D - Delete a row of Data in table(s)

}
