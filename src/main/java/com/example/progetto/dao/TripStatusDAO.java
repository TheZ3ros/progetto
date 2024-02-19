package com.example.progetto.dao;

import com.example.progetto.model.TripStatus;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripStatusDAO implements GenericDAO<List<TripStatus>> {
    private final Connectivity connection;
    public TripStatusDAO() throws SQLException, IOException {
        connection = Connectivity.getSingletonInstance();
    }

    @Override
    public List<TripStatus> execute(Object... params) throws SQLException {
        List<TripStatus> tripStatuses = new ArrayList<>();
        int id=(int)params[0];
        try(CallableStatement cs = connection.conn.prepareCall("{call GetTripStatus(?)}")){
        cs.setInt(1,id);

        ResultSet rs = cs.executeQuery();

        while (rs.next()){
            String username = rs.getString(2);
            boolean status = rs.getBoolean(1);
            TripStatus tripStatus = new TripStatus(username,status);
            tripStatuses.add(tripStatus);
        }
        }
        catch(SQLException e){
            throw new SQLException(e.getMessage());
        }
        return tripStatuses;
    }


    public boolean update(int id,String username) throws SQLException {

       try( CallableStatement cs = connection.conn.prepareCall("{call UpdateTripStatus(?,?)}")) {

           cs.setInt(1, id);
           cs.setString(2, username);
           ResultSet rs = cs.executeQuery();
           return rs != null;
       }
       catch(SQLException e){
           throw new SQLException(e.getMessage());
       }

    }
}
