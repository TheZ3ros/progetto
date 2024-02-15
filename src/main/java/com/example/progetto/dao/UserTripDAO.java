package com.example.progetto.dao;

import com.example.progetto.model.UserTrip;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTripDAO implements GenericDAO <UserTrip> {
    private final Connectivity connection;
    public UserTripDAO() throws SQLException, IOException {
        connection = Connectivity.getSingletonInstance();
    }
    @Override
    public UserTrip execute(Object... params) throws SQLException {
        UserTrip tab=(UserTrip) params[0];
        int idTrip=tab.getIdTrip();
        String username=tab.getUsername();
        if(!alreadyExist(idTrip,username)){
            CallableStatement cs = connection.conn.prepareCall("{call SetTrip(?,?)}");
            cs.setInt(1, idTrip);
            cs.setString(2, username);
            int rowsAffected = cs.executeUpdate();

            if (rowsAffected > 0) {
                return tab;
            } else {
                return null;
            }
        }
        return null;

    }

    private boolean alreadyExist(int idTrip, String username) throws SQLException {
        CallableStatement cs = connection.conn.prepareCall("{call CheckUserTrip(?,?)}");
        cs.setInt(1,idTrip);
        cs.setString(2, username);
        ResultSet resultSet = cs.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt(1) == 1;
        }
        else{
            return false;
        }
    }

}