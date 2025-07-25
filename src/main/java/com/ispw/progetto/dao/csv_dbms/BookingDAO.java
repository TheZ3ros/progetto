package com.ispw.progetto.dao.csv_dbms;

import com.ispw.progetto.exception.AlreadyPrenotedException;
import com.ispw.progetto.exception.CSVInteractionException;
import com.ispw.progetto.model.UserTrip;

import java.io.IOException;
import java.sql.SQLException;

public interface BookingDAO {
    void setTripBook(UserTrip book) throws SQLException, AlreadyPrenotedException, IOException;
    void alreadyExist(UserTrip booking) throws SQLException, AlreadyPrenotedException, IOException, CSVInteractionException;
}
