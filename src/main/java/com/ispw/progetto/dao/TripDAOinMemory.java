package com.ispw.progetto.dao;

import com.ispw.progetto.model.Trip;

import java.util.*;

/**
 * DAO in memoria per la modalità demo (MEMORY)
 */
public class TripDAOinMemory {

    // Singleton
    private static TripDAOinMemory instance;

    // Mappa ID → Trip
    private final Map<Integer, Trip> tripsById = new HashMap<>();

    private TripDAOinMemory() {
        // Popolamento iniziale (demo)
        //addTrip(new Trip("Roma", 10, "2025-08-01", "2025-08-10", 500.0, "roma.jpg", true), 1);
        //addTrip(new Trip("Milano", 15, "2025-09-05", "2025-09-12", 450.0, "milano.jpg", true), 2);
        //addTrip(new Trip("Venezia", 8, "2025-07-20", "2025-07-25", 600.0, "venezia.jpg", true), 3);
        // Aggiungi altri viaggi demo se vuoi
    }

    public static TripDAOinMemory getInstance() {
        if (instance == null) {
            instance = new TripDAOinMemory();
        }
        return instance;
    }

    public Trip getTripById(int id) {
        return tripsById.get(id);
    }

    public Collection<Trip> getAllTrips() {
        return tripsById.values();
    }

    public void addTrip(Trip trip, int id) {
        tripsById.put(id, trip);
    }

    public void reset() {
        tripsById.clear();
    }
}
