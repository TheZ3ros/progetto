package com.example.progetto.controller_app;

import com.example.progetto.Exception.AlreadyPrenotedException;
import com.example.progetto.Exception.PlacesTerminatedException;
import com.example.progetto.dao.TripDAO;
import com.example.progetto.dao.UserDAO;
import com.example.progetto.dao.UserTripDAO;
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

    public static int bookTrip(UserBean userbean, TripBean tripbean) throws SQLException, IOException, PlacesTerminatedException, AlreadyPrenotedException {
        TripDAO tripdao = new TripDAO();
        UserDAO userdao = new UserDAO();
        User utente = userdao.execute(userbean.getUsername());
        Trip trip = tripdao.execute(tripbean.getId());
        UserTrip usertrip = new UserTrip();
        usertrip.setIdTrip(trip.getId());
        usertrip.setUsername(utente.getUsername());
        UserTripDAO usertripdao = new UserTripDAO();


        int result;
        if (trip.getAvailable() > 0 && usertripdao.execute(usertrip) != null) {
            tripdao.refreshAvailable(trip.getId());
            result = 1;
        } else if (trip.getAvailable() <= 0) {
            result = 2;
            throw new PlacesTerminatedException("i posti per il viaggio sono terminati");
        } else {
            result = 3;
            throw new AlreadyPrenotedException("sei già prenotato per questo viaggio");
        }

        return result;


    }

    public static Image bytesToImage(byte[] bytes) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return new Image(inputStream);
    }


    public static List<TripBean> GetTripUser(UserBean utente) throws SQLException, IOException {
        TripDAO tripDAO = new TripDAO();
        List<Trip> trip = tripDAO.TripUser(utente.getUsername());
        List<TripBean> tripBeanList = new ArrayList<>();
        for (int i = 0; i < trip.size(); i++) {

            TripBean tripBean = new TripBean(trip.get(i).getCity(), trip.get(i).getAvailable(), trip.get(i).getDataAnd(), trip.get(i).getDataRit(), trip.get(i).getPrice(), trip.get(i).getImage(), trip.get(i).isStato());
            tripBeanList.add(tripBean);
        }


        return tripBeanList;
    }
}



