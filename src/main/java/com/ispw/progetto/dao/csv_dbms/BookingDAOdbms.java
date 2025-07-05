package com.ispw.progetto.dao.csv_dbms;

import com.ispw.progetto.dao.Connectivity;
import com.ispw.progetto.exception.AlreadyPrenotedException;
import com.ispw.progetto.model.UserTrip;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class BookingDAOdbms implements BookingDAO {
    private final Connectivity connection;

    public BookingDAOdbms() throws SQLException, IOException {
        connection = Connectivity.getSingletonInstance();
    }
    @Override
    public void setTripBook(UserTrip book) throws SQLException {
        int idTrip = book.getIdTrip();
        String username = book.getUsername();


            try (CallableStatement cs = connection.getConn().prepareCall("{call SetTrip(?,?)}")) {
                cs.setInt(1, idTrip);
                cs.setString(2, username);
                cs.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException("errore durante esecuzione query" + e.getMessage());
            }
        }
    @Override
    public void alreadyExist(UserTrip booking) throws SQLException,AlreadyPrenotedException {
        try (CallableStatement cs = connection.getConn().prepareCall("{call CheckUserTrip(?,?,?)}")) {
            cs.setInt(1, booking.getIdTrip());
            cs.setString(2, booking.getUsername());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.executeQuery();
            int row=cs.getInt(3);
            if (row==1) {
                throw new AlreadyPrenotedException("già prenotato");
            }
        } catch (SQLException e) {
            throw new SQLException("errore durante esecuzione query" + e.getMessage());
        }

    }
}