package com.example.progetto.DAO;

import com.example.progetto.entity.UserTrip;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTripDAO implements GenericDAO <UserTrip> {
    private final Connectivity connection;
    public UserTripDAO() {
        connection = Connectivity.getSingletonInstance();
    }
    @Override
    public UserTrip execute(Object... params) throws SQLException {
        UserTrip tab=(UserTrip) params[0];
        int idTrip=tab.getIdTrip();
        String username=tab.getUsername();
        if(!already_exist(idTrip,username)){
            connection.connected();
            CallableStatement cs = connection.conn.prepareCall("{call SetTrip(?,?)}");
            cs.setInt(1, idTrip);
            cs.setString(2, username);
            int rowsAffected = cs.executeUpdate();

            if (rowsAffected > 0) {
                return tab;
            } else {
                System.out.println("La stored procedure non ha influenzato alcuna riga.");
                return null;
            }
        }
        System.out.println("già prenotato");
        return null;

    }

    private boolean already_exist(int idTrip,String username) throws SQLException {
        connection.connected();
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