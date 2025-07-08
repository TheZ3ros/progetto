package com.ispw.progetto.controller_app;

import com.ispw.progetto.bean.SearchBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.dao.TripDAOinMemory;
import com.ispw.progetto.dao.csv_dbms.BookingDAO;
import com.ispw.progetto.dao.csv_dbms.BookingDAOcsv;
import com.ispw.progetto.dao.csv_dbms.BookingDAOinMemory;
import com.ispw.progetto.exception.AlreadyPrenotedException;
import com.ispw.progetto.exception.BookingInitializationException;
import com.ispw.progetto.exception.FailedSearchException;
import com.ispw.progetto.exception.PlacesTerminatedException;
import com.ispw.progetto.bean.BookBean;
import com.ispw.progetto.dao.TripDAO;
import com.ispw.progetto.dao.UserDAO;
import com.ispw.progetto.dao.csv_dbms.BookingDAOdbms;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.model.Trip;
import com.ispw.progetto.model.UserTrip;
import com.ispw.progetto.pattern.decorator.UserTripStatus;
import com.ispw.progetto.pattern.factory.EntityFactory;
import com.ispw.progetto.utils.AppContext;
import com.ispw.progetto.utils.PersistenceMode;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookTripController {

    private final BookingDAO bookingDAO;

    // Costruttore di default (usato da GUI se non viene specificato altro)
    public BookTripController() throws SQLException, IOException {
        AppContext appContext = AppContext.getInstance();
        switch (appContext.getPersistenceMode()) {
            case DB -> {
                try {
                    this.bookingDAO = new BookingDAOdbms();
                } catch (Exception e) {
                    throw new BookingInitializationException("Errore nell'inizializzazione DB", e);
                }
            }
            case CSV -> this.bookingDAO = new BookingDAOcsv();
            case MEMORY -> this.bookingDAO = BookingDAOinMemory.getInstance();
            default -> throw new IllegalArgumentException("Modalità di persistenza non supportata");
        }
    }


    // Costruttore alternativo per test/manuale
    public BookTripController(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public List<TripBean> showTrip() throws SQLException, IOException {
        AppContext appContext = AppContext.getInstance();
        List<TripBean> viaggi = new ArrayList<>();

        if (appContext.getPersistenceMode() == PersistenceMode.MEMORY) {
            // Recupera i viaggi da TripDAOinMemory
            TripDAOinMemory daoInMemory = TripDAOinMemory.getInstance();
            List<Trip> trips = new ArrayList<>(daoInMemory.getAllTrips()); // Assicurati che questo metodo esista
            for (Trip trip : trips) {
                TripBean tripBean = new TripBean(
                        trip.getAvailable(),
                        trip.getCity(),
                        trip.getDataAnd(),
                        trip.getDataRit(),
                        trip.getPrice(),
                        trip.getImage(),
                        trip.getId()
                );
                viaggi.add(tripBean);
            }
        } else {
            // Modalità DB o CSV
            TripDAO tripdao = new TripDAO();
            Trip trip;
            int i = 1;
            while ((trip = tripdao.execute(i)) != null) {
                TripBean tripBean = new TripBean(
                        trip.getAvailable(),
                        trip.getCity(),
                        trip.getDataAnd(),
                        trip.getDataRit(),
                        trip.getPrice(),
                        trip.getImage(),
                        trip.getId()
                );
                viaggi.add(tripBean);
                i++;
            }
        }

        return viaggi;
    }


    public void bookTrip(BookBean book) throws SQLException, IOException, AlreadyPrenotedException {
        TripDAO tripdao = new TripDAO();
        UserDAO userdao = new UserDAO();

        EntityFactory utente = userdao.execute(book.getUsername());
        Trip trip = tripdao.execute(book.getTripId());

        UserTripStatus userTripStatus = new UserTripStatus(utente.getUsername());
        UserTrip usertrip = new UserTrip(userTripStatus, trip.getId());

        bookingDAO.setTripBook(usertrip);

        tripdao.refreshAvailable(trip.getId());
    }

    public List<TripBean> getTripUser(UserBean utente) throws SQLException, IOException {
        AppContext appContext = AppContext.getInstance();
        PersistenceMode mode = appContext.getPersistenceMode();

        List<TripBean> tripBeanList = new ArrayList<>();

        if (mode == PersistenceMode.DB || mode == PersistenceMode.CSV) {
            // ✅ modalità DB o CSV: usa TripDAO
            TripDAO tripDAO = new TripDAO();
            List<Trip> tripList = tripDAO.tripUser(utente.getUsername());

            for (Trip trip : tripList) {
                TripBean tripBean = new TripBean(
                        trip.getCity(), trip.getAvailable(),
                        trip.getDataAnd(), trip.getDataRit(),
                        trip.getPrice(), trip.getImage(), trip.isStato()
                );
                tripBeanList.add(tripBean);
            }
        } else if (mode == PersistenceMode.MEMORY) {
            // ✅ modalità demo: carica tutti i viaggi dalla memoria
            TripDAOinMemory tripDAO = TripDAOinMemory.getInstance();
            Collection<Trip> allTrips = tripDAO.getAllTrips();

            for (Trip trip : allTrips) {
                TripBean tripBean = new TripBean(
                        trip.getCity(), trip.getAvailable(),
                        trip.getDataAnd(), trip.getDataRit(),
                        trip.getPrice(), trip.getImage(), trip.isStato()
                );
                tripBeanList.add(tripBean);
            }
        }

        return tripBeanList;
    }




    public List<TripBean> searchByCity(SearchBean searchBean) throws SQLException, IOException, FailedSearchException {
        TripDAO tripdao = new TripDAO();
        List<Trip> viaggi = tripdao.searchTrip(searchBean.getCitta());

        if (viaggi.isEmpty()) {
            throw new FailedSearchException("Nessun itinerario disponibile per questa città");
        }

        List<TripBean> trips = new ArrayList<>();
        for (Trip trip : viaggi) {
            TripBean tripBean = new TripBean(trip.getAvailable(), trip.getCity(),
                    trip.getDataAnd(), trip.getDataRit(), trip.getPrice(), trip.getImage(), trip.getId());
            trips.add(tripBean);
        }
        return trips;
    }

    public void checkAlready(TripBean trip, UserBean user) throws SQLException, IOException, AlreadyPrenotedException, PlacesTerminatedException {
        String username = user.getUsername();
        int id = trip.getId();
        UserTrip userTrip = new UserTrip(new UserTripStatus(username), id);

        new BookingDAOdbms().alreadyExist(userTrip); // solo in DB
        if (trip.getAvailable() == 0) {
            throw new PlacesTerminatedException("I posti per il viaggio sono terminati");
        }
    }
}


