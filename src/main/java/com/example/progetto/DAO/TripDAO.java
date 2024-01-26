package com.example.progetto.DAO;

import com.example.progetto.entity.Trip;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripDAO implements GenericDAO <Trip> {
        private final Connectivity connection;
        public TripDAO(){
            connection = Connectivity.getSingletonInstance();
        }

        @Override
        public List<Trip> execute(Object... params) throws SQLException {
            connection.connected();
            List<Trip> viaggi=new ArrayList<>();
            CallableStatement cs = connection.conn.prepareCall("{call GetTrip(?,?)}");
            boolean status = cs.execute();
            if(status) {
                ResultSet rs=cs.getResultSet();
                while(rs.next()){
                    Trip trip=new Trip(rs.getInt(1), rs.getString(6),rs.getDate(3),rs.getDate(4),rs.getFloat(2),rs.getString(5));
                    viaggi.add(trip);

                }

            }
return viaggi;
        }
    }


