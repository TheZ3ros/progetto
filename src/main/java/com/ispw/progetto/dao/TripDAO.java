package com.ispw.progetto.dao;
import com.ispw.progetto.model.Trip;

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
        try(CallableStatement cs = connection.conn.prepareCall("{call GetTripDetailsById(?,?,?,?,?,?,?)}")){
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
    }
    public void refreshAvailable(int id) throws SQLException {


            try(CallableStatement cs = connection.conn.prepareCall("{call decrementa(?)}")) {
                cs.setInt(1, id);
                cs.executeQuery();
            }catch(SQLException e){
                throw new SQLException(e.getMessage());
            }
        }

    public void addTrip(String city, int available, Date dataAnd, Date dataRit, float price, byte[] image) throws SQLException {
        try (CallableStatement cs = connection.conn.prepareCall("{call AddTrip(?,?,?,?,?,?)}")){

            cs.setString(1, city);
            cs.setInt(2, available);
            cs.setDate(3, dataAnd);
            cs.setDate(4, dataRit);
            cs.setFloat(5, price);
            cs.setBytes(6, image);
            cs.executeQuery();
        }
        catch(SQLException e){
            throw new SQLException("errore aggiunta trip",e.getMessage());
        }
    }

    public List<Trip> tripUser(String username) throws SQLException {
        List<Trip> trip=new ArrayList<>();
        try (CallableStatement cs = connection.getConn().prepareCall("{call GetTripDetailsByUsername(?)}")){
            cs.setString(1,username);
            boolean status = cs.execute();
            if (status) {
                ResultSet rs = cs.getResultSet();
                while (rs.next()) {
                    String citta=rs.getString(1);
                    Float prezzo=rs.getFloat(2);
                    Date dataA=rs.getDate(3);
                    Date dataRit=rs.getDate(4);
                    int available=rs.getInt(5);
                    byte[] image=rs.getBytes(6);
                    boolean state=rs.getBoolean(7);
                    Trip trips =new Trip(available, citta, dataA,dataRit,prezzo,image,state);
                    trip.add(trips);
                }

            }
        }
        catch (SQLException e) {
            throw new SQLException("errore durante la lettura: " + e.getMessage());
        }
        return trip;
    }
    public List<Trip> searchTrip(String search) throws SQLException {
        List<Trip> trip=new ArrayList<>();
        try (CallableStatement cs = connection.getConn().prepareCall("{call GetTripDetailsByCity(?)}")){
            cs.setString(1, search);
            boolean status = cs.execute();
            if (status) {
                ResultSet rs = cs.getResultSet();
                while (rs.next()) {
                    Float prezzo=rs.getFloat(1);
                    Date dataA=rs.getDate(2);
                    Date dataRit=rs.getDate(3);
                    int available=rs.getInt(4);
                    byte[] image=rs.getBytes(5);
                    Trip trips =new Trip(available, search, dataA,dataRit,prezzo,image);
                    trip.add(trips);
                }

            }
        }
        catch (SQLException e) {
            throw new SQLException("errore durante la lettura: " + e.getMessage());
        }
        return trip;
    }
    }



