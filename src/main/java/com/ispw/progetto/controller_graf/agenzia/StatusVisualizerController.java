package com.ispw.progetto.controller_graf.agenzia;

import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.TripStatusBean;
import com.ispw.progetto.controller_app.TripStatusController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;

public class StatusVisualizerController {

    private TripStatusBean statusbean;
    private TripBean currentTrip;

    @FXML
    private Label usernameuser;

    @FXML
    private Label state;

    public void setStage(){
        // nuovo campo stage
    }

    public void setTrip(TripBean currentTrip){
        this.currentTrip = currentTrip;
    }

    public void setCurrentUser(){
        //temporaneamente vuoto in attesa di aggiornamento sulla View
    }

    public void createbox(TripStatusBean statusbean){
        this.statusbean = statusbean;
        usernameuser.setText(statusbean.getUsername() + ": ");
        state.setText(String.valueOf(statusbean.isStatus()));
    }

    public void conferma() throws SQLException, IOException {
        TripStatusController statusupdater = new TripStatusController();
        boolean b = statusupdater.updatetripstatus(currentTrip.getId(), statusbean.getUsername());
        if (b) {
            state.setText("True");
        } else {
            Printer.printMessage("Query non eseguita");
        }
    }
}
