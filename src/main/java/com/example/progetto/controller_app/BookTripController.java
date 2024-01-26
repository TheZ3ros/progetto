package com.example.progetto.controller_app;

import com.example.progetto.DAO.TripDAO;
import com.example.progetto.bean.TripBean;
import com.example.progetto.entity.Trip;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTripController {
    public static List<TripBean> show_trip() throws SQLException {
        TripDAO tripdao =new TripDAO();
        List<Trip> viaggi=new ArrayList<>();
        viaggi=tripdao.charge_trip();
        int i;
        List<TripBean> viaggibean=new ArrayList<>();
        for (i=0;i< viaggi.size();i++){
                Trip trip=viaggi.get(i);
                TripBean tripBean=new TripBean(trip.getPlaces(),trip.getCity(), trip.getData_and(), trip.getData_rit(),trip.getPrice(), trip.image());
                viaggibean.add(tripBean);
            System.out.println(viaggibean.get(i).getCity());
        }
        return viaggibean;


    }
}
