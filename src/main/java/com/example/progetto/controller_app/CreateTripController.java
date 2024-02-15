package com.example.progetto.controller_app;

import com.example.progetto.dao.TripDAO;
import com.example.progetto.bean.TripBean;
import com.example.progetto.model.Trip;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.SQLException;

public class CreateTripController {

    private Trip newTrip = null;

    public CreateTripController(TripBean trip){

        String citta=trip.getCity();
        int n=(trip.getAvailable());
        java.sql.Date dataA=(trip.getDataAnd());
        java.sql.Date dataR=trip.getDataRit();
        Float prezzo=(trip.getPrice());
        byte[] image=(trip.getImage());
        newTrip=new Trip(n,citta, dataA,dataR,prezzo,image);
    }

    public void uploadTrip() throws SQLException, IOException {
        TripDAO dao = new TripDAO();

        try {
            dao.addTrip(newTrip.getCity(), newTrip.getAvailable(), newTrip.getDataAnd(), newTrip.getDataRit(), newTrip.getPrice(), newTrip.getImage());
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Creazione fallita");
            alert.setHeaderText(null);
            alert.setContentText("Impossibile aggiungere il nuovo itinerario al DB");
            alert.showAndWait();
        }

    }
}
