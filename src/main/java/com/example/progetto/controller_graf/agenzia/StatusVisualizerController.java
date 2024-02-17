package com.example.progetto.controller_graf.agenzia;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.TripStatusBean;
import com.example.progetto.controller_app.GetTripStatusController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class StatusVisualizerController {

    private Applicazione main;
    private AgencyBean currentUser;
    private TripStatusBean statusbean;
    private TripBean currentTrip;
    @FXML
    private Label usernameuser;
    @FXML
    private Label state;
    @FXML
    private Button confirm;

    @FXML
    public void setMain(Applicazione main){

        this.main = main;
    }

    public void setTrip(TripBean trip){
        this.currentTrip =trip;
    }

    public void setCurrentUser(AgencyBean currentUser){
        this.currentUser = currentUser;
    }

    public void createbox(TripStatusBean statusbean){
        this.statusbean =statusbean;
        usernameuser.setText(statusbean.getUsername()+": ");
        state.setText(String.valueOf(statusbean.isStatus()));

    }

    public void conferma() throws SQLException, IOException {
        //state.setText("true");
        GetTripStatusController statusupdater = new GetTripStatusController();
        statusupdater.updatetripstatus(currentTrip.getId(), statusbean.getUsername());
    }
}
