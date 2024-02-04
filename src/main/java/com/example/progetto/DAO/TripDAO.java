package com.example.progetto.DAO;
import com.example.progetto.entity.Trip;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

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
        cs.registerOutParameter(6, Types.BLOB);
        cs.registerOutParameter(7, Types.DATE);

        cs.executeQuery();
        if (cs.getString(2) != null) {
            trip.setCity(cs.getString(2));
            trip.setPrice(cs.getFloat(3));
            trip.setData_and(cs.getDate(4));
            trip.setAvailable(cs.getInt(5));
            trip.setImage(cs.getBytes(6));
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

    public void add_trip(String city, int available, Date data_and, Date data_rit, float price, byte[] image) throws SQLException {
        connection.connected();
        CallableStatement cs = connection.conn.prepareCall("{call AddTrip(?,?,?,?,?,?)}");
        cs.setString(1,city);
        cs.setInt(2,available);
        cs.setDate(3, data_and);
        cs.setDate(4, data_rit);
        cs.setFloat(5,price);
        cs.setBytes(6,image);
        cs.executeQuery();
        System.out.println("Query eseguita");
    }
}


