package com.ispw.progetto.dao.csv_dbms;

import com.ispw.progetto.exception.AlreadyPrenotedException;
import com.ispw.progetto.model.UserTrip;

import java.util.*;

public class BookingDAOinMemory implements BookingDAO {

    // 🟩 Singleton instance
    private static BookingDAOinMemory instance;

    // 🧠 Mappa che tiene traccia dei viaggi prenotati da ogni utente
    private final Map<String, Set<Integer>> userBookings = new HashMap<>();

    // 🔒 Costruttore privato
    private BookingDAOinMemory() {
    }

    // 🟢 Metodo pubblico per ottenere l'unica istanza
    public static BookingDAOinMemory getInstance() {
        if (instance == null) {
            instance = new BookingDAOinMemory();
        }
        return instance;
    }

    @Override
    public void setTripBook(UserTrip book) throws AlreadyPrenotedException {
        String username = book.getUsername();
        int tripId = book.getIdTrip();

        Set<Integer> bookings = userBookings.computeIfAbsent(username, k -> new HashSet<>());

        if (bookings.contains(tripId)) {
            throw new AlreadyPrenotedException("Hai già prenotato questo viaggio (modalità demo)");
        }

        bookings.add(tripId);
    }

    @Override
    public void alreadyExist(UserTrip booking) throws AlreadyPrenotedException {
        String username = booking.getUsername();
        int tripId = booking.getIdTrip();

        Set<Integer> bookings = userBookings.get(username);
        if (bookings != null && bookings.contains(tripId)) {
            throw new AlreadyPrenotedException("Hai già prenotato questo viaggio (modalità demo)");
        }
    }

    public Set<Integer> getTripIdsByUser(String username) {
        return userBookings.getOrDefault(username, Collections.emptySet());
    }

}
