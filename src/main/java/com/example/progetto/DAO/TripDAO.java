package com.example.progetto.DAO;
import com.example.progetto.entity.Trip;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TripDAO implements GenericDAO<Trip> {
        private final Connectivity connection;
        public TripDAO(){
            connection = Connectivity.getSingletonInstance();
        }



    @Override
    public Trip execute(Object... params) throws SQLException {
        Trip trip = new Trip();
        int id=(int)params[0];

        connection.connected();
        CallableStatement cs = connection.conn.prepareCall("{call GetTripDetailsById(?,?,?,?,?,?,?)}");
        cs.setInt(1, id);
        cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.FLOAT);
        cs.registerOutParameter(4, Types.DATE);
        cs.registerOutParameter(5, Types.INTEGER);
        cs.registerOutParameter(6, Types.VARCHAR);
        cs.registerOutParameter(7, Types.DATE);

        cs.executeQuery();
        if (cs.getString(2) != null) {
            trip.setCity(cs.getString(2));
            trip.setPrice(cs.getFloat(3));
            trip.setData_and(cs.getDate(4));
            trip.setPlaces(cs.getInt(5));
            trip.setImage(cs.getString(6));
            trip.setData_rit(cs.getDate(7));
            trip.setId(id);

            return trip;
        }
        else {
            return null;
        }
    }
    public void refresh_available(int id) throws SQLException {
        connection.connected();
        CallableStatement cs = connection.conn.prepareCall("{call decrementa(?)}");
        cs.setInt(1,id);
        cs.executeQuery();
    }

}


