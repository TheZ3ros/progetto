package com.ispw.progetto.controller_graf.agenzia;

import com.ispw.progetto.bean.AgencyBean;
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

    private Stage stage;
    private AgencyBean currentUser;

    public void setUser(AgencyBean utente) {
        currentUser = utente;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setButtonText() {
        agency.setText(currentUser.getUsername());
    }

    @FXML
    public void vaiAHome() {
        stage.setTitle("Home");
        stage.setScene(new Scene(new javafx.scene.Group())); // placeholder
    }

    @FXML
    private void viewTripCreation() throws IOException {
        FXMLLoader loader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/agenzia/view_trip_creation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ViewTripCreationController controller = loader.getController();
        controller.setStage(stage);
        controller.setUser(currentUser);
        controller.setButtonText();
        stage.setScene(scene);
        stage.setTitle("Crea itinerario");
    }

    @FXML
    private void agencyTrips() throws IOException, SQLException {
        AgencyTripsController myTrips = new AgencyTripsController();
        myTrips.agencyTrips(stage, currentUser);
    }
}
