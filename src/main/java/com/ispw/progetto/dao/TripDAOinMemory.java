package com.ispw.progetto.dao;

import com.ispw.progetto.model.Trip;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.sql.Date;

/**
 * DAO in memoria per la modalità demo (MEMORY)
 */
public class TripDAOinMemory {
    private static TripDAOinMemory instance;
    private final Map<Integer, Trip> tripsById = new HashMap<>();

    private TripDAOinMemory() {
        byte[] imageBytes = loadImageAsByteArray();

        Trip trip = new Trip(200, "New York", Date.valueOf("2026-09-09"), Date.valueOf("2026-09-15"), 800f, imageBytes, true);
        addTrip(trip, 200);
    }

    public static TripDAOinMemory getInstance() {
        if (instance == null) {
            instance = new TripDAOinMemory();
        }
        return instance;
    }

    public Collection<Trip> getAllTrips() {
        return tripsById.values();
    }

    public void addTrip(Trip trip, int id) {
        tripsById.put(id, trip);
    }

    private byte[] loadImageAsByteArray() {
        try (InputStream is = getClass().getResourceAsStream("/com/ispw/progetto/ImmagineDEMO/NewYork.jpg")) {
            return is != null ? is.readAllBytes() : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
