package com.ispw.progetto.controller_graf.agenzia;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.TripStatusBean;
import com.ispw.progetto.controller_app.TripStatusController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;

public class StatusVisualizerController {

    private Stage stage;               // nuovo campo stage
    private AgencyBean currentUser;
    private TripStatusBean statusbean;
    private TripBean currentTrip;

    @FXML
    private Label usernameuser;

    @FXML
    private Label state;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setTrip(TripBean currentTrip){
        this.currentTrip = currentTrip;
    }

    public void setCurrentUser(AgencyBean currentUser){
        this.currentUser = currentUser;
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
