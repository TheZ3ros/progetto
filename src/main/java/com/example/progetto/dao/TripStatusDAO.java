package com.example.progetto.dao;

import com.example.progetto.exception.NotValidCouponException;
import com.example.progetto.model.TripStatus;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TripStatusDAO implements GenericDAO<List<TripStatus>> {
    private final Connectivity connection;
    public TripStatusDAO() throws SQLException, IOException {
        connection = Connectivity.getSingletonInstance();
    }

    @Override
    public List<TripStatus> execute(Object... params) throws SQLException, NotValidCouponException {
        List<TripStatus> tripStatuses = new ArrayList<>();
        int id=(int)params[0];
        CallableStatement cs = connection.conn.prepareCall("{call GetTripStatus(?,?,?)}");
        cs.setInt(1,id);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.registerOutParameter(3,Types.BIT);

        ResultSet rs = cs.executeQuery();

        while (rs.next()){
            String username = rs.getString("UsernameUser");
            boolean status = rs.getBoolean("state");
            TripStatus tripStatus = new TripStatus(username,status);
            tripStatuses.add(tripStatus);
        }
        return tripStatuses;
    }
}
