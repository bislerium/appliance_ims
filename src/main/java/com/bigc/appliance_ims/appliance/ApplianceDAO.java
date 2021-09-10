/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims.appliance;

import com.bigc.appliance_ims.db.DBConnection;
import com.bigc.appliance_ims.user.SessionInfo;
import java.sql.*;
import java.util.HashMap;
import java.util.Optional;

/**
 *
 * @author bisha
 */
public final class ApplianceDAO implements DAO{
    
    private final Connection connection;
    private final boolean admin;
    private final int clientID;
    
    public ApplianceDAO(SessionInfo sessionInfo) throws Exception {
        if(!sessionInfo.isAuthorized()) {
            throw new Exception("UnAuthorized Access!");
        }
        this.connection = sessionInfo.getConnection();
        this.admin = sessionInfo.isAdmin();
        this.clientID = sessionInfo.getClientID();
    }
    
    @Override
    public int getMaxID() {
        String QUERY = "SELECT MAX(ApplianceID) AS MAX_ID FROM appliance";
        try (Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(QUERY);) {
            while(resultSet.next()) {
                return resultSet.getInt("MAX_ID");
            }               
        } catch (SQLException ex) {
            DBConnection.printSQLException(ex);
        }
        return 0;
    }
    
    @Override
    public Optional<ApplianceBean> getUser(int id) throws SQLException {
        String QUERY = "SELECT * FROM appliance WHERE ApplianceID=";
        try (Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery(QUERY + id);) {            
            ApplianceBean appliance = null;
            while(resultSet.next()) {
                appliance = extractAppliance(resultSet);
            }
            return Optional.ofNullable(appliance);                     
        } catch (SQLException ex) {
            DBConnection.printSQLException(ex);
            throw ex;
        }
    }
    
    @Override
    public HashMap<Integer, ApplianceBean> getAllAppliances() {
        String QUERY;
        if (admin) {
            QUERY = "SELECT * FROM appliance";
        } else {
            QUERY = "SELECT * FROM appliance WHERE AddedBy=" + clientID;
        }
        HashMap<Integer, ApplianceBean> appliances = new HashMap<>();
        try (Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery(QUERY);) {
            while (resultSet.next()) {
                ApplianceBean applianceBean = extractAppliance(resultSet);
                appliances.put(applianceBean.getApplianceID(), applianceBean);
            }
        } catch (SQLException e) {
            DBConnection.printSQLException(e);
        }
        return appliances;
    }

    @Override
    public boolean createAppliance(ApplianceBean applianceBean) {
        String QUERY = "INSERT INTO appliance VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(QUERY);) {           
            ps.setInt(1, applianceBean.getApplianceID());
            ps.setString(2, applianceBean.getApplianceName());
            ps.setString(3, applianceBean.getCategory());
            ps.setString(4, applianceBean.getSubCategory());
            ps.setString(5, applianceBean.getModelNumber());
            ps.setString(6, applianceBean.getWeight());
            ps.setString(7, applianceBean.getBrand());
            ps.setString(8, applianceBean.getService());
            ps.setDouble(9, applianceBean.getPrice());
            ps.setInt(10, applianceBean.getStocks());
            ps.setBoolean(11, applianceBean.isAvailability());
            ps.setString(12, applianceBean.getApplianceSKU());
            ps.setBoolean(13, applianceBean.isDiscontinued());
            ps.setTimestamp(14, applianceBean.getRegDateTime());
            ps.setString(15, applianceBean.getDescription());
            ps.setString(16, applianceBean.getComment());
            ps.setInt(17, applianceBean.getAddedBy());
            int i = ps.executeUpdate();
            return (i == 1);
        } catch (SQLException ex) {
            DBConnection.printSQLException(ex);
        }
        return false;
    }

    @Override
    public boolean updateAppliance(ApplianceBean appliance) {
        String QUERY = "UPDATE appliance SET ApplianceName=?"
                + ", Category=?, SubCategory=?, ModelNumber=?, Weight=?"
                + ", Brand=?, Service=?, Price=?, Stocks=?, Availability=?"
                + ", ApplianceSKU=?, Discontinued=?, RegDateTime=?, ADescription=?"
                + ", AComment=? WHERE ApplianceID=?";
        try (PreparedStatement ps = connection.prepareStatement(QUERY);) {           
            ps.setString(1, appliance.getApplianceName());
            ps.setString(2, appliance.getCategory());
            ps.setString(3, appliance.getSubCategory());
            ps.setString(4, appliance.getModelNumber());
            ps.setString(5, appliance.getWeight());
            ps.setString(6, appliance.getBrand());
            ps.setString(7, appliance.getService());
            ps.setDouble(8, appliance.getPrice());
            ps.setInt(9, appliance.getStocks());
            ps.setBoolean(10, appliance.isAvailability());
            ps.setString(11, appliance.getApplianceSKU());
            ps.setBoolean(12, appliance.isDiscontinued());
            ps.setTimestamp(13, appliance.getRegDateTime());
            ps.setString(14, appliance.getDescription());
            ps.setString(15, appliance.getComment());
            ps.setInt(16, appliance.getApplianceID());
            int i = ps.executeUpdate();
            return (i == 1);
        } catch (SQLException ex) {
            DBConnection.printSQLException(ex);
        }
        return false;
    }

    @Override
    public boolean deleteAppliance(int id) {
        String QUERY = "DELETE FROM appliance WHERE ApplianceID=";
        try (Statement stmt = connection.createStatement()) {            
            int i = stmt.executeUpdate(QUERY + id);
            return (i == 1);
        } catch (SQLException ex) {
           DBConnection.printSQLException(ex);
        }
        return false;
    }
    
    private ApplianceBean extractAppliance(ResultSet resultSet) throws SQLException {
        ApplianceBean applianceBean =  new ApplianceBean();
        applianceBean.setApplianceID(resultSet.getInt("ApplianceID"));
        applianceBean.setApplianceName(resultSet.getString("ApplianceName"));
        applianceBean.setCategory(resultSet.getString("Category"));
        applianceBean.setSubCategory(resultSet.getString("SubCategory"));
        applianceBean.setModelNumber(resultSet.getString("ModelNumber"));
        applianceBean.setWeight(resultSet.getString("Weight"));
        applianceBean.setBrand(resultSet.getString("Brand"));
        applianceBean.setService(resultSet.getString("Service"));
        applianceBean.setPrice(resultSet.getDouble("Price"));
        applianceBean.setStocks(resultSet.getInt("Stocks"));
        applianceBean.setAvailability(resultSet.getBoolean("Availability"));
        applianceBean.setApplianceSKU(resultSet.getString("ApplianceSKU"));
        applianceBean.setDiscontinued(resultSet.getBoolean("Discontinued"));
        applianceBean.setRegDateTime(resultSet.getTimestamp("RegDateTime"));
        applianceBean.setDescription(resultSet.getString("ADescription"));
        applianceBean.setComment(resultSet.getString("AComment"));
        applianceBean.setAddedBy(resultSet.getInt("AddedBy"));
        return applianceBean;
    }    
}
