package com.example.progetto.controller_graf.agenzia;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.controller_app.BookTripController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AgencyTripsController {
    @FXML
    private Button agency;
    private Applicazione main;
    private AgencyBean currentUser;
    @FXML
    private ListView<VBox> listaview;

    public void setMain(Applicazione main){

        this.main = main;
    }
    @FXML
    private void vaiAHome(){

        main.vaiAHome();
    }

    public void setUser(AgencyBean utente){

        currentUser=utente;
    }

    public void setButtonText() {

        agency.setText(currentUser.getUsername());
    }

    public void charge() throws SQLException, IOException {
        List<TripBean> viaggi = BookTripController.showTrip();

        // Crea un VBox per ciascun elemento nella lista e aggiungilo alla ListView
        for (TripBean viaggio : viaggi) {
            FXMLLoader getagencytripsLoader = new FXMLLoader(Applicazione.class.getResource("view1/agenzia/singleagencytrip.fxml"));
            VBox box=getagencytripsLoader.load();
            GetAgencyTripsController controller = getagencytripsLoader.getController();
            controller.setMain(main);
            controller.setUser(currentUser);
            controller.createbox(viaggio);
            listaview.getItems().add(box);
        }

    }

    public void agencyTrips(Applicazione main, AgencyBean currentUser) throws IOException, SQLException {
        FXMLLoader agencytripsLoader = new FXMLLoader(Applicazione.class.getResource("view1/agenzia/agencytrips.fxml"));
        Parent agencytripsroot = agencytripsLoader.load();
        Scene agencytripscene = new Scene(agencytripsroot);
        AgencyTripsController agencytrips = agencytripsLoader.getController();
        agencytrips.setMain(main);
        agencytrips.setUser(currentUser);
        Stage stage = main.getStage();
        stage.setScene(agencytripscene);
        agencytrips.setButtonText();
        stage.setTitle("Itinerari agenzia");
        agencytrips.charge();
    }

}
