package com.ispw.progetto.controller_graf.agenzia;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.utils.SceneNavigator;
import com.ispw.progetto.utils.StageAware;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AgencyHomeController implements StageAware {
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
    public void vaiAHome() throws IOException {
        SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/home.fxml", this);
    }

    @FXML
    private void viewTripCreation() throws IOException {
        SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/agenzia/view_trip_creation.fxml", this);
    }

    @FXML
    private void agencyTrips() throws IOException, SQLException {
        AgencyTripsController myTrips = new AgencyTripsController();
        myTrips.agencyTrips(stage, currentUser);
    }
}
