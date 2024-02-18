package com.example.progetto.dao;

import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.model.UserTrip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface BookingDAO {
    public void setTripBook(UserTrip book) throws SQLException, AlreadyPrenotedException, IOException;
    public void alreadyExist(UserTrip booking) throws SQLException, AlreadyPrenotedException, IOException;
}
