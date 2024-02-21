package com.ispw.progetto.controller_graf.agenzia;

import com.ispw.progetto.Applicazione;
import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.TripStatusBean;
import com.ispw.progetto.controller_app.GetTripStatusController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import view2.Printer;

import java.io.IOException;
import java.sql.SQLException;

public class StatusVisualizerController {

    protected Applicazione main;
    protected AgencyBean currentUser;
    private TripStatusBean statusbean;
    private TripBean currentTrip;
    @FXML
    private Label usernameuser;
    @FXML
    private Label state;

    @FXML
    public void setMain(Applicazione main){

        this.main = main;
    }

    public void setTrip(TripBean currentTrip){
        this.currentTrip = currentTrip;
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
        GetTripStatusController statusupdater = new GetTripStatusController();
        boolean b = statusupdater.updatetripstatus(currentTrip.getId(), statusbean.getUsername());
        if (b){
            state.setText("True");
        }
        else{
            Printer.printMessage("Query non eseguita");
        }
    }
}