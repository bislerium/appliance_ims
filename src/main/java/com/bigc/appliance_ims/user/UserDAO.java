/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims.user;

import com.bigc.appliance_ims.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author bisha
 */
public class UserDAO implements DAO{
    
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public final Optional<UserBean> getUser(int id) throws SQLException {
        String QUERY = "SELECT * FROM user WHERE UserID=";
        try (Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery(QUERY + id);) {            
            UserBean user = null;
            while(resultSet.next()) {
                user = extractUser(resultSet);
            }
            return Optional.ofNullable(user);                     
        } catch (SQLException ex) {
            DBConnection.printSQLException(ex);
            throw ex;
        }
    }

    @Override
    public final List<UserBean> getAllUsers() throws SQLException{
        String QUERY = "SELECT * FROM user";
        try (Statement stmt = connection.createStatement();) {            
            ResultSet resultSet = stmt.executeQuery(QUERY);
            List<UserBean> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(extractUser(resultSet));
            }
            return users;
        } catch (SQLException ex) {
            DBConnection.printSQLException(ex);
            throw ex;
        }
    }

    @Override
    public final boolean createUser(UserBean user) {
        String QUERY = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(QUERY);) {           
            ps.setInt(1, user.getUserID());
            ps.setInt(2, user.getUserPWD());
            ps.setBoolean(3, user.isAdmin());
            ps.setString(4, user.getUserName());
            ps.setString(5, user.getGender());
            ps.setDate(6, user.getdOB());
            ps.setTimestamp(7, user.getRegDate());
            ps.setString(8, user.getCompany());
            ps.setString(9, user.getRecoveryCode());
            return (ps.executeUpdate() == 1);
        } catch (SQLException ex) {
            DBConnection.printSQLException(ex);
        }
        return false;
    }

    @Override
    public final boolean updateUser(UserBean user) {
        String QUERY = "UPDATE user SET UserPWD=?, IsAdmin=?,"
                + " RecoveryCode=? WHERE UserID=?";
        try (PreparedStatement ps = connection.prepareStatement(QUERY);) {           
            ps.setInt(1, user.getUserPWD());
            ps.setBoolean(2, user.isAdmin());
            ps.setString(3, user.getRecoveryCode());   
            ps.setInt(4, user.getUserID());
            return (ps.executeUpdate() == 1);
        } catch (SQLException ex) {
            DBConnection.printSQLException(ex);
        }
        return false;
    }

    @Override
    public final boolean deleteUser(int id) {
        String QUERY = "DELETE FROM user WHERE UserID=";
        try (Statement stmt = connection.createStatement()) {
            return (stmt.executeUpdate(QUERY + id) == 1);
        } catch (SQLException ex) {
           DBConnection.printSQLException(ex);
        }
        return false;
    }
    
    public final UserBean extractUser(ResultSet resultSet) throws SQLException {
        UserBean user = new UserBean();
        user.setUserID(resultSet.getInt("UserID"));
        user.setUserPWD(resultSet.getInt("UserPWD"));
        user.setAdmin(resultSet.getBoolean("IsAdmin"));
        user.setUserName(resultSet.getString("UserName"));
        user.setGender(resultSet.getString("Gender"));
        user.setdOB(resultSet.getDate("DOB"));
        user.setRegDate(resultSet.getTimestamp("RegDate"));
        user.setCompany(resultSet.getString("Company"));
        user.setRecoveryCode(resultSet.getString("RecoveryCode"));
//                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("RegisteredDate"))
        return user;
    }
}
