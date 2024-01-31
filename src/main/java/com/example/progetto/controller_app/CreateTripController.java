package com.example.progetto.controller_app;

import com.example.progetto.DAO.TripCreationDAO;
import com.example.progetto.bean.TripCreationBean;
import com.example.progetto.entity.TripCreation;

import java.sql.SQLException;

public class CreateTripController {

    private final TripCreationBean tripbean;

    public CreateTripController(TripCreationBean trip){
        this.tripbean=trip;
    }

    public void upload_trip(){
        TripCreationDAO dao = new TripCreationDAO();

        try {
            dao.add_trip(tripbean.getCity(),tripbean.getAvailable(),tripbean.getData_and(),tripbean.getData_rit(),tripbean.getPrice(), tripbean.getImage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Si spera funzioni");

    }
}
