package com.example.progetto.controller_app;

import com.example.progetto.dao.TripDAO;
import com.example.progetto.bean.TripBean;
import com.example.progetto.entity.Trip;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class CreateTripController {

    private final TripBean tripbean;
    private final Trip newTrip = new Trip();

    public CreateTripController(TripBean trip){
        this.tripbean=trip;

        newTrip.setCity(tripbean.getCity());
        newTrip.setAvailable(trip.getAvailable());
        newTrip.setDataAnd(tripbean.getDataAnd());
        newTrip.setDataRit(tripbean.getDataRit());
        newTrip.setPrice(trip.getPrice());
        newTrip.setImage(trip.getImage());
    }

    public void uploadTrip(){
        TripDAO dao = new TripDAO();

        try {
            dao.add_trip(newTrip.getCity(), newTrip.getAvailable(), newTrip.getDataAnd(), newTrip.getDataRit(), newTrip.getPrice(), newTrip.getImage());
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Creazione fallita");
            alert.setHeaderText(null);
            alert.setContentText("Impossibile aggiungere il nuovo itinerario al DB");
            alert.showAndWait();
        }

    }
}
