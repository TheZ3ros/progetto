package com.example.progetto.controller_app;

import com.example.progetto.Exception.AlreadyPrenotedException;
import com.example.progetto.Exception.PlacesTerminatedException;
import com.example.progetto.bean.BookBean;
import com.example.progetto.dao.TripDAO;
import com.example.progetto.dao.UserDAO;
import com.example.progetto.dao.BookingDAO;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.model.Trip;
import com.example.progetto.model.User;
import com.example.progetto.model.UserTrip;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTripController {
    public BookTripController() {
        throw new IllegalStateException("BookTripController");
    }

    public static List<TripBean> showTrip() throws SQLException, IOException {
        TripDAO tripdao = new TripDAO();
        Trip trip;
        List<TripBean> viaggi = new ArrayList<>();
        int i = 1;
        while ((trip = tripdao.execute(i)) != null) {
            i++;

            TripBean tripBean = new TripBean(trip.getAvailable(), trip.getCity(), trip.getDataAnd(), trip.getDataRit(), trip.getPrice(), trip.getImage(), trip.getId());
            viaggi.add(tripBean);
        }


        return viaggi;


    }

    public static void bookTrip(BookBean booking) throws SQLException, IOException, PlacesTerminatedException, AlreadyPrenotedException {
        TripDAO tripdao = new TripDAO();
        UserDAO userdao = new UserDAO();
        User utente = userdao.execute(booking.getUsername());
        Trip trip = tripdao.execute(booking.getTripId());
        UserTrip usertrip = new UserTrip();
        usertrip.setIdTrip(trip.getId());
        usertrip.setUsername(utente.getUsername());
        BookingDAO usertripdao = new BookingDAO();

        try{
            usertripdao.SetTripBook(usertrip);
        }
        catch(AlreadyPrenotedException e){
            throw new AlreadyPrenotedException(e.getMessage());
        }
        if (trip.getAvailable() > 0) {

            tripdao.refreshAvailable(trip.getId());

        } else {

            throw new PlacesTerminatedException("i posti per il viaggio sono terminati");
        }



    }

    public static Image bytesToImage(byte[] bytes) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return new Image(inputStream);
    }

    public static List<TripBean> GetTripUser(UserBean utente) throws SQLException, IOException {
        TripDAO tripDAO = new TripDAO();
        List<Trip> trip = tripDAO.TripUser(utente.getUsername());
        List<TripBean> tripBeanList = new ArrayList<>();
        for (Trip value : trip) {

            TripBean tripBean = new TripBean(value.getCity(), value.getAvailable(), value.getDataAnd(), value.getDataRit(), value.getPrice(), value.getImage(), value.isStato());
            tripBeanList.add(tripBean);
        }
        return tripBeanList;
    }
}


