package com.example.progetto.controller_app;

import com.example.progetto.dao.TripDAO;
import com.example.progetto.bean.TripBean;
import com.example.progetto.entity.Trip;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            boolean result=checkduplicate(newTrip);
            if (!result){
                throw new IllegalArgumentException();
            }
            dao.addTrip(newTrip.getCity(), newTrip.getAvailable(), newTrip.getDataAnd(), newTrip.getDataRit(), newTrip.getPrice(), newTrip.getImage());
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Creazione fallita");
            alert.setHeaderText(null);
            alert.setContentText("Impossibile aggiungere il nuovo itinerario al DB");
            alert.showAndWait();
        }

    }

    public boolean checkduplicate(Trip newtrip) throws SQLException, IOException {
        boolean b = true;
        TripDAO tripdao = new TripDAO();
        Trip trip;
        List<TripBean> viaggi = new ArrayList<>();
        int i = 1;
        while ((trip = tripdao.execute(i)) != null) {
            i++;
            if(trip.getAvailable()== newtrip.getAvailable() && Objects.equals(trip.getCity(), newtrip.getCity()) && Objects.equals(trip.getDataAnd(),newtrip.getDataAnd()) && Objects.equals(trip.getDataRit(),newtrip.getDataRit()) && trip.getPrice()==newtrip.getPrice()){
                b = false;
                return b;
            }
            TripBean tripBean = new TripBean(trip.getAvailable(), trip.getCity(), trip.getDataAnd(), trip.getDataRit(), trip.getPrice(), trip.getImage(), trip.getId());
            viaggi.add(tripBean);
        }
        return b;
    }
}
