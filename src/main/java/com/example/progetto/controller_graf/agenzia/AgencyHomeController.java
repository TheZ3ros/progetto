package com.example.progetto.controller_graf.agenzia;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.AgencyBean;
import com.example.progetto.pattern.Factory.BeanFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AgencyHomeController {
    @FXML
    private Button agency;
    private Applicazione main;
    private BeanFactory currentUser;


    public void setUser(BeanFactory utente){

        currentUser=utente;
    }
    public void setButtonText() {

        agency.setText(currentUser.getUsername());
    }


    public void setMain(Applicazione main){

        this.main = main;
    }
    @FXML
    private void vaiAHome(){

        main.vaiAHome();
    }

    @FXML
    private void viewTripCreation() throws IOException{
        FXMLLoader viewtripcreationLoader = new FXMLLoader(Applicazione.class.getResource("view1/agenzia/view_trip_creation.fxml"));
        Parent viewtripcreationroot = viewtripcreationLoader.load();
        Scene tripcreationscene = new Scene(viewtripcreationroot);
        ViewTripCreationController tripcreation = viewtripcreationLoader.getController();
        tripcreation.setMain(main);
        tripcreation.setUser(currentUser);
        Stage stage = main.getStage();
        stage.setScene(tripcreationscene);
        tripcreation.setButtonText();
        stage.setTitle("Crea itinerario");
    }

    @FXML
    private void agencyTrips() throws IOException, SQLException {
        AgencyTripsController mytrips = new AgencyTripsController();
        mytrips.agencyTrips(main,currentUser);
    }
}
