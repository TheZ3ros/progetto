package com.example.progetto.dao;
import com.example.progetto.entity.Trip;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripDAO implements GenericDAO<Trip> {
        private final Connectivity connection;
        public TripDAO() throws SQLException, IOException {
            connection = Connectivity.getSingletonInstance();
        }



    @Override
    public Trip execute(Object... params) throws SQLException {
        Trip trip;
        int id=(int)params[0];
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

            String citta=(cs.getString(2));
            Float prezzo=(cs.getFloat(3));
            Date dataA=(cs.getDate(4));
            int available=(cs.getInt(5));
            byte[] image=(cs.getBytes(6));
            Date dataRit=(cs.getDate(7));

            trip =new Trip(available, citta, dataA,dataRit,prezzo,image,id);

            return trip;
        }
        else {
            return null;
        }
    }
    public void refreshAvailable(int id) throws SQLException {
        CallableStatement cs = connection.conn.prepareCall("{call decrementa(?)}");
        cs.setInt(1,id);
        cs.executeQuery();
    }

    public void addTrip(String city, int available, Date dataAnd, Date dataRit, float price, byte[] image) throws SQLException {
        CallableStatement cs = connection.conn.prepareCall("{call AddTrip(?,?,?,?,?,?)}");
        cs.setString(1,city);
        cs.setInt(2,available);
        cs.setDate(3, dataAnd);
        cs.setDate(4, dataRit);
        cs.setFloat(5,price);
        cs.setBytes(6,image);
        cs.executeQuery();
    }

    public List<Trip> TripUser(String username) throws SQLException {
        CallableStatement cs=null;
        List<Trip> trip=new ArrayList<>();
        try {
            cs = connection.getConn().prepareCall("{call GetTripDetailsByUsername(?)}");
            cs.setString(1,username);
            assert cs != null;
            boolean status = cs.execute();
            if (status) {
                ResultSet rs = cs.getResultSet();
                while (rs.next()) {
                    String citta=(rs.getString(1));
                    Float prezzo=(rs.getFloat(2));
                    Date dataA=(rs.getDate(3));
                    int available=(rs.getInt(4));
                    byte[] image=(rs.getBytes(5));
                    Date dataRit=(rs.getDate(6));
                    Trip trips =new Trip(available, citta, dataA,dataRit,prezzo,image);
                    trip.add(trips);
                }

            }
        }
        catch (SQLException e) {
            throw new SQLException("errore durante la lettura: " + e.getMessage());
        }
        finally {
            // Chiudi l'oggetto CallableStatement nel blocco finally
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ignored) {

                }
            }
        }
        return trip;
    }
    }



