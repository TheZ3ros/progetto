package com.example.progetto.controller_app;

import com.example.progetto.DAO.TripDAO;
import com.example.progetto.DAO.UserDAO;
import com.example.progetto.DAO.UserTripDAO;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.entity.Trip;
import com.example.progetto.entity.User;
import com.example.progetto.entity.UserTrip;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTripController {
    public static List<TripBean> show_trip() throws SQLException {
        TripDAO tripdao =new TripDAO();
        Trip trip;
        List<TripBean> viaggi = new ArrayList<>();
        int i=1;
        while((trip=tripdao.execute(i))!=null){
            i++;
            TripBean tripBean=new TripBean(trip.getAvailable(),trip.getCity(), trip.getData_and(), trip.getData_rit(),trip.getPrice(), trip.image(),trip.getId());
            viaggi.add(tripBean);
        }


        return viaggi;


    }
    public static int book_trip(UserBean userbean, TripBean tripbean) throws SQLException {
        TripDAO tripdao=new TripDAO();
        Trip trip=tripdao.execute(tripbean.getId());
        UserDAO userdao = new UserDAO();
        User utente = userdao.execute(userbean.getUsername());
        UserTrip usertrip = new UserTrip();
        usertrip.setIdTrip(trip.getId());
        usertrip.setUsername(utente.getUsername());
        UserTripDAO usertripdao = new UserTripDAO();
        int result;

        if (trip.getAvailable() > 0 && usertripdao.execute(usertrip) != null) {
            tripdao.refresh_available(trip.getId());
            System.out.println("prenotato");
            result = 1;
        } else if (trip.getAvailable() <= 0) {
            System.out.println("posti finiti");
            result = 2;
        } else {
            System.out.println("già prenotato");
            result = 3;
        }

        return result;


    }
}
